package cn.submsg.client.util;

import java.util.Map;
import java.util.Map.Entry;

import cn.submsg.client.config.AppConfig;
import cn.submsg.client.lib.MESSAGEXsend;

public class SubMsgSendUtils {
	
	/**
	 * 国内短信
	 * @param to      手机号码
	 * @param tempId  模板Id
	 * @param param   模板中的参数变量及值
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

}
