package com.teapod.mq;

import java.util.concurrent.TimeUnit;

public interface DelayedQuene<T> {
	
	public T circleTake(long delayTime);
	
	public T circleTake(long MaxDelayTime,TimeUnit unit);
	
	public boolean remove(T t);
	

}
