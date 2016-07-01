package cn.submsg.client.demo;

import java.util.HashMap;
import java.util.Map;

import cn.submsg.client.util.SubMsgSendUtils;

public class MessageXSendDemo {

	public static void main(String[] args) {
		String to = "18516632554";
		String tempId = "MApf82";
		Map<String,String> param = new HashMap<String,String>();
		param.put("code", "123658");
		SubMsgSendUtils.sendMessage(to, tempId, param);
	}	
}
