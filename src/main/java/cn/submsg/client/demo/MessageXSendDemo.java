package cn.submsg.client.demo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.time.DateFormatUtils;

import cn.submsg.client.util.SubMsgSendUtils;

public class MessageXSendDemo {

	public static void main(String[] args) {
		String to = "18516632554";
		String tempId = "sUb981";
		String date = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
		Map<String,String> param = new HashMap<String,String>();
		param.put("name", "潮哥");
		param.put("numStr", "20");
		param.put("userName", "dogdog7788");
		param.put("date", date);
		SubMsgSendUtils.sendMessage(to, tempId, param);
	}	
}
