package com.teapod.mq.impl;

import java.util.concurrent.ConcurrentHashMap;

import com.teapod.mq.Quene;

public class ConcurrentQuene<T> implements Quene<T>{
	
	public ConcurrentHashMap<String, T> map = new ConcurrentHashMap<>();//存储数据 
	//public ConcurrentHashMap<String, T> willDeleteMap = new ConcurrentHashMap<>();//待删除数据

	/**
	 * 取数据
	 */
	@Override
	public T take(String messageId) {
		
		T t = this.map.get(messageId);
		this.map.remove(messageId);
		return t;
		
	}

	/**
	 * 回滚数据
	 * 
	 */
	
	@Override
	public boolean roollBack(String messageId,T t) {
		this.map.put(messageId, t);
		return true;
	}

	/**
	 * 放数据
	 */
	@Override
	public boolean put(String messageId, T t) {
		
		this.map.put(messageId, t);
		return true;
		
	}
	

	@Override
	public int size() {
		
		return this.map.size();
		
	}

	@Override
	public T take() {
		if(this.map.isEmpty())
			return null;
		String messageId = this.map.keySet().iterator().next();
		return take(messageId);
	}



}
