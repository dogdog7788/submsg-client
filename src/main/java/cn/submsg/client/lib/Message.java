package cn.submsg.client.lib;

import java.util.Map;

import cn.submsg.client.config.AppConfig;
import cn.submsg.client.lib.base.ISender;
import cn.submsg.client.lib.base.Sender;

/**
 * A Sender class define the message mode to send HTTP request.
 * 
 * @see ISender
 * @see Sender
 * @version 1.0 at 2014/10/28
 * */
public class Message extends Sender {
    public static final String API_ROOT = "http://127.0.0.1:8080";
	private static final String API_XSEND = API_ROOT+"/message/xsend.json";
	
	private static final String INTERNATIONAL_API_XSEND = API_ROOT+"/internationalsms/xsend.json";

	public Message(AppConfig config) {
		this.config = config;
	}


	@Override
	public boolean xsend(Map<String, Object> data) {
		return request(API_XSEND, data);
	}
	
	@Override
	public boolean internationalXsend(Map<String, Object> data) {
		return request(INTERNATIONAL_API_XSEND, data);
	}

}
