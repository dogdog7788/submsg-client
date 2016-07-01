package cn.submsg.client.util;

import java.io.IOException;
import java.util.Properties;

import cn.submsg.client.config.AppConfig;
import cn.submsg.client.config.MessageConfig;

public class ConfigLoader {

	private static Properties pros = null;
	
	private static AppConfig messageConfig = null;
	
	/**
	 * Loading file while class loading.The operation inside of static block
	 * will be run once.
	 * */
	static {
		pros = new Properties();
		try {
			pros.load(ConfigLoader.class
					.getResourceAsStream("/app_config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * enum define two kinds of configuration.
	 * */
	public static enum ConfigType {
		Message
	};

	/**
	 * A static method for outer class to create configuration by loading file.
	 * 
	 * @param type
	 *            ConfigType
	 * @return If the type is ConfigType#Mail,return the instance of
	 *         {@link MailConfig}. And,if the type is ConfigType#Message,return
	 *         the instance of {@link MessageConfig}.
	 * */
	public static AppConfig load() {
			return createMessageConfig();
	}


	private static AppConfig createMessageConfig() {
		if(messageConfig==null){
			messageConfig  = new MessageConfig();
			messageConfig.setAppId(pros.getProperty(MessageConfig.APP_ID));
			messageConfig.setAppKey(pros.getProperty(MessageConfig.APP_KEY));
			messageConfig.setSignType(pros.getProperty(MessageConfig.APP_SIGNTYPE));
			messageConfig.setOpen(pros.getProperty(MessageConfig.isOpen).equals("true")?true:false);
		}
		return messageConfig;
	}

}
