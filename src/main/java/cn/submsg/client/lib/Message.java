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

	private static final String API_XSEND = "http://api.submsg.cn/message/xsend.json";

	public Message(AppConfig config) {
		this.config = config;
	}


	@Override
	public boolean xsend(Map<String, Object> data) {
		return request(API_XSEND, data);
	}

}
