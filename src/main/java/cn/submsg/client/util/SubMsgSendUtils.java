package cn.submsg.client.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import cn.submsg.client.config.AppConfig;
import cn.submsg.client.lib.MESSAGEXsend;

public class SubMsgSendUtils {
	
	
	public static boolean sendMessage(String to,String tempId,Map<String,String> param){
		AppConfig config = ConfigLoader.load();
		if(config.isOpen()){
			MESSAGEXsend submsg = new MESSAGEXsend(config);
			submsg.addTo(to);
			submsg.setTempId(tempId);
			for(Entry<String,String> entity:param.entrySet()){
				submsg.addVar(entity.getKey(),entity.getValue());
			}
			return submsg.xsend();
		}else{
			System.out.println("测试阶段，不真正发送验证码:code="+param.get("code"));
			return true;
		}
	}
	
	
	public static void main(String[] args) {
		Map<String,String> param = new HashMap<String,String>();
		param.put("code", "1234567");
		SubMsgSendUtils.sendMessage("15919820372", "aGTtt3", param);
	}
}
