package cn.submsg.client.lib.base;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import cn.submsg.client.config.AppConfig;
import cn.submsg.client.lib.Message;
import cn.submsg.client.util.RequestEncoder;

/**
 * A class implements ISender contains common methods to process something that
 * occured before sending.That is, create a signature and build the request
 * data.
 * 
 * @see Mail
 * @see Message
 * @version 1.0 at 2014/10/30
 * */
public class Sender implements ISender {

	protected AppConfig config = null;
	private static final String API_TIMESTAMP = Message.API_ROOT+"/service/timestamp.json";
	public static final String APPID = "appid";
	public static final String TIMESTAMP = "timestamp";
	public static final String SIGN_TYPE = "sign_type";
	public static final String SIGNATURE = "signature";
	private CloseableHttpClient closeableHttpClient = null;
	
	private static final String FLAG = "flag";
	private static final String MSG = "msg";

	public Sender() {
		closeableHttpClient = HttpClientBuilder.create().build();
	}
	@Override
	public boolean xsend(Map<String, Object> data) {
		return false;
	}
	
	private JSONObject getResult(String jsonStr){
		JSONObject json = JSONObject.fromObject(jsonStr);
		int flag = json.getInt(FLAG);
		if(flag==0){
		//success
		  return json.getJSONObject("rc");
		}else{
		 //fail
		  String msg = json.getString(MSG);
		  System.out.println("something wrong!!errorCode="+flag+",msg="+msg);
		  return null;
		}
	}
	
	
	/**
	 * Send a request to require timestamp from server.
	 * @return timestamp
	 * */
	protected String getTimestamp() {
		HttpGet httpget = new HttpGet(API_TIMESTAMP);
		HttpResponse response;
		try {
			response = closeableHttpClient.execute(httpget);
			HttpEntity httpEntity = response.getEntity();
			String jsonStr = EntityUtils.toString(httpEntity, "UTF-8");
			System.out.println(jsonStr);
			if (jsonStr != null) {
				JSONObject json = getResult(jsonStr);
				if(json!=null){
					return json.getString("timestamp");
				}else{
					throw new RuntimeException("some thing wrong happend");
				}
			}
		} catch (ClientProtocolException e) {
			try {
				closeableHttpClient.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (IOException e) {
			try {
				closeableHttpClient.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			
		}
		return null;
	}

	protected String createSignature(String data) {
		if (AppConfig.TYPE_NORMAL.equals(config.getSignType())) {
			return config.getAppKey();
		} else {
			return buildSignature(data);
		}
	}

	/**
	 * When the {@link AppConfig#setSignType(String)} is not normal,create a
	 * signature with sign type.
	 * 
	 * @param data
	 *            is the request data
	 * @return signature
	 * */
	private String buildSignature(String data) {
		String app = config.getAppId();
		String appKey = config.getAppKey();
		// order is confirmed
		String jointData = app + appKey + data + app + appKey;
		if (AppConfig.TYPE_MD5.equals(config.getSignType())) {
			return RequestEncoder.encode(RequestEncoder.MD5, jointData);
		} else if (AppConfig.TYPE_SHA1.equals(config.getSignType())) {
			return RequestEncoder.encode(RequestEncoder.SHA1, jointData);
		}
		return null;
	}

	/**
	 * Request to url with data.
	 * 
	 * @param url
	 * @param data
	 * @return boolean
	 * */
	protected boolean request(String url, Map<String, Object> data) {
		HttpPost httppost = new HttpPost(url);
		httppost.addHeader("charset", "UTF-8");
		httppost.setEntity(build(data));
		try {
			HttpResponse response = closeableHttpClient.execute(httppost);
			HttpEntity httpEntity = response.getEntity();
			if (httpEntity != null) {
				String jsonStr = EntityUtils.toString(httpEntity, "UTF-8");
				System.out.println(jsonStr);
				JSONObject json = getResult(jsonStr);
				if(json!=null){
					return true;
				}else{
					return false;
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				closeableHttpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * Transform the request data to HttpEntity
	 * 
	 * @param data
	 * @return HttpEntity
	 * */
	@SuppressWarnings("deprecation")
	protected HttpEntity build(Map<String, Object> data) {
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//		builder.addTextBody(APPID, config.getAppId());
		String timeStamp = this.getTimestamp();
//		if(timeStamp==null){
//			
//		}
//		builder.addTextBody(TIMESTAMP,timeStamp);
//		builder.addTextBody(SIGN_TYPE, config.getSignType());
		data.put(APPID, config.getAppId());
		data.put(TIMESTAMP, timeStamp);
		data.put(SIGN_TYPE, config.getSignType());
		ContentType contentType = ContentType.create(HTTP.PLAIN_TEXT_TYPE, HTTP.UTF_8); 
		builder.addTextBody(SIGNATURE,
				createSignature(RequestEncoder.formatRequest(data)), contentType);
		for (Map.Entry<String, Object> entry : data.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			if (value instanceof String) {
				builder.addTextBody(key, String.valueOf(value), contentType);
			} else if (value instanceof File) {
				builder.addBinaryBody(key, (File) value);
			}
		}
		return builder.build();
	}
	@Override
	public boolean internationalXsend(Map<String, Object> data) {
		return false;
	}
}
