package cn.submsg.client.demo;

import java.util.HashMap;
import java.util.Map;


import cn.submsg.client.util.SubMsgSendUtils;

public class MessageXSendDemo {

	public static void main(String[] args) {
	
//		for(int i=0;i<20;i++){
//			new Thread(new Runnable() {
//				@Override
//				public void run() {
//					while(true){
//						try {
//						send();
//						} catch (Exception e) {
//							e.printStackTrace();
//						}
//						try {
//							Thread.sleep(500);
//						} catch (InterruptedException e) {
//							e.printStackTrace();
//						}
//					}
//					
//				}
//			}).start();
//		}
		
		send();
		
	}	
	
	
	public static void send(){
		String to = "15919820372";
		Map<String,String> param = new HashMap<String,String>();
//		String tempId = "sUb981";
//		String date = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
//		param.put("name", "潮哥");
//		param.put("numStr", "20");
//		param.put("userName", "dogdog7788");
//		param.put("date", date);
		
//		String tempId = "BlQ9X"; 
//		String tempId = "R630D1";
//		String tempId = "WKkt32";
		
		String tempId = "NFgnN3";
		param.put("userName", "ddbk789");
		param.put("op", "卖出积分");
		param.put("code", "125356");
		SubMsgSendUtils.sendMessage(to, tempId, param);
	}
}
