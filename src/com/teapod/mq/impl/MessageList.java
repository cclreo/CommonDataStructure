package com.teapod.mq.impl;

import java.util.concurrent.ConcurrentHashMap;

import com.teapod.mq.Quene;

public class MessageList{
	
	public ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<String, String>();

}
