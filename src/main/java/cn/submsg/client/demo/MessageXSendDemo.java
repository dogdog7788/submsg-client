package cn.submsg.client.demo;

import java.util.HashMap;
import java.util.Map;
import cn.submsg.client.util.SubMsgSendUtils;
public class MessageXSendDemo {

	public static void main(String[] args) {
		//模板中定义的 code 参数变量设置
		Map<String,String> param = new HashMap<String,String>();
		param.put("code", "156872");
		//发送国内短信  第一个参数为 手机号码  第二个参数为 模板ID  第四个参数为 模板中的变量参数与值
		SubMsgSendUtils.sendMessage("18920652358", "1mg1O", param);
		//发送国内短信 第一个参数为国家代码 如86代表中国，第二个参数为手机号码  第三个参数为模板ID，第四个参数为 模板中的变量参数与值
		SubMsgSendUtils.sendInternationalMessage("93","18920652358", "sUb981", param);
	}	
}
