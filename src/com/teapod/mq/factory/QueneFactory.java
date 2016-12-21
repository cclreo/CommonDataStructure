



package com.teapod.mq.factory;

import java.util.Collections;

import com.teapod.mq.impl.ConcurrentDelayedQuene;
import com.teapod.mq.impl.ConcurrentQuene;


public class QueneFactory{
	
	public static <T> ConcurrentQuene<T> newConcurrentQuene(){
		return new ConcurrentQuene<T>();
	}
	
	public static <T> ConcurrentDelayedQuene<T> newConcurrentDelayedQuene(){
		return new ConcurrentDelayedQuene<T>();
	}
	
	
}
