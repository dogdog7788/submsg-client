package cn.submsg.client.lib.base;

import cn.submsg.client.entity.DataStore;

public abstract class SenderWapper {

	protected DataStore requestData = new DataStore();

	public abstract ISender getSender();
}
