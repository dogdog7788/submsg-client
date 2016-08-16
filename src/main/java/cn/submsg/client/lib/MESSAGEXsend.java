package cn.submsg.client.lib;

import cn.submsg.client.config.AppConfig;
import cn.submsg.client.config.MessageConfig;
import cn.submsg.client.lib.base.ISender;
import cn.submsg.client.lib.base.SenderWapper;

public class MESSAGEXsend extends SenderWapper {

	/**
	 * If the mode is mail,it's an instance of {@link MailConfig}. If the mode
	 * is message,it's an instance of {@link MessageConfig}
	 * */
	protected AppConfig config = null;
	public static final String TO = "to";
	public static final String TEMPID = "tempid";
	public static final String VARS = "vars";
	public static final String REGION_CODE = "region_code";

	public MESSAGEXsend(AppConfig config) {
		this.config = config;
	}

	public void addTo(String address) {
		requestData.addWithComma(TO, address);
	}


	public void setTempId(String tempId) {
		requestData.put(TEMPID, tempId);
	}
	
	public void addVar(String key, String val) {
		requestData.addWithJson(VARS, key, val);
	}
	
	public void addRegionCode(String regionCode) {
		requestData.put(REGION_CODE, regionCode);
	}
	
	@Override
	public ISender getSender() {
		return new Message(this.config);
	}

	public boolean xsend(){
		return getSender().xsend(requestData);
	}
	
	public boolean interNationalXsend(){
		return getSender().internationalXsend(requestData);
	}
	
}
