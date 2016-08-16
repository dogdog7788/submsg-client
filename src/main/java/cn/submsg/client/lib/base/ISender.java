package cn.submsg.client.lib.base;

import java.util.Map;


public interface ISender {


	public boolean xsend(Map<String, Object> data);
	
	public boolean internationalXsend(Map<String, Object> data);
}
