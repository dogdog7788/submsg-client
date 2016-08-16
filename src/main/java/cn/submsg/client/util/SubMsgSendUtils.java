package cn.submsg.client.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import cn.submsg.client.config.AppConfig;
import cn.submsg.client.lib.MESSAGEXsend;

public class SubMsgSendUtils {
	
	/**
	 * 国内短信
	 * @param to
	 * @param tempId
	 * @param param
	 * @return
	 */
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
	
	/**
	 * 国际短信
	 * @param regionCode   国家区域码   如 86  不需要加＋号
	 * @param to           号码
	 * @param tempId       模版id
	 * @param param        参数
	 * @return
	 */
	public static boolean sendInternationalMessage(String regionCode,String to,String tempId,Map<String,String> param){
		AppConfig config = ConfigLoader.load();
		if(config.isOpen()){
			MESSAGEXsend submsg = new MESSAGEXsend(config);
			submsg.addRegionCode(regionCode);
			submsg.addTo(to);
			submsg.setTempId(tempId);
			for(Entry<String,String> entity:param.entrySet()){
				submsg.addVar(entity.getKey(),entity.getValue());
			}
			return submsg.interNationalXsend();
		}else{
			System.out.println("测试阶段，不真正发送验证码:code="+param.get("code"));
			return true;
		}
	}
	
	
	public static void main(String[] args) {
		Map<String,String> param = new HashMap<String,String>();
		param.put("code", "1234567");
//		SubMsgSendUtils.sendMessage("15919820372", "sUb981", param);
//		SubMsgSendUtils.sendInternationalMessage("93","15919820372", "sUb981", param);
//		SubMsgSendUtils.sendInternationalMessage("93","15919820372", "sUb981", param);
	}
}
