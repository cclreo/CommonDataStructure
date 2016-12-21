package com.teapod.mq.impl;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

import com.teapod.model.DelayItem;
import com.teapod.mq.DelayedQuene;
import com.teapod.mq.Quene;

public class ConcurrentDelayedQuene<T> implements DelayedQuene<T>{
	
	public BlockingQueue<DelayItem<T>> quene = new DelayQueue<DelayItem<T>>();
	public ConcurrentHashMap<String,T> map = new ConcurrentHashMap<>();
	public ConcurrentHashMap<T,Long> concurrentHashMap = new ConcurrentHashMap<>();
	private DelayItem<T> delayItem = null;

	public T take(String messageId) {
		return this.map.get(messageId);
	}

	public boolean put(String messageId, DelayItem<T> t) {
		try {
			this.quene.put(t);
			this.map.put(messageId, t.getItem());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.concurrentHashMap.put(t.getItem(),t.getSequenceNumber());
		return true;
	}

	public int size() {
		return this.quene.size();
	}

	@Override
	public T circleTake(long delayTime) {
		
		DelayItem<T> delayItem = null;
		try {
			delayItem = this.quene.take();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		delayItem.setDelay(delayTime);
		try {
			this.quene.put(delayItem);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return delayItem.getItem();
	}

	@Override
	public T circleTake(long MaxDelayTime,TimeUnit unit) {
		
		DelayItem<T> delayItem = null;
		try {
			delayItem = this.quene.take();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		if(unit.convert(delayItem.getTimeout()*2, unit.NANOSECONDS)-MaxDelayTime<=0L){
			delayItem.setDelay(delayItem.getTimeout()*2);
			try {
				this.quene.put(delayItem);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return delayItem.getItem();
	}

	@Override
	public boolean remove(T t) {
		
		Long id = this.concurrentHashMap.get(t);
		if(id==null)
			return true;
		delayItem = new DelayItem<T>(t, Integer.MAX_VALUE);
		delayItem.setSequenceNumber(id);
		this.quene.remove(delayItem);
		this.concurrentHashMap.remove(t, id);
		return false;
		
	}
	


}
