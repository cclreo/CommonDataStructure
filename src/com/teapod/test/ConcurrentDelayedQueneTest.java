package com.teapod.test;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

import com.teapod.model.DelayItem;
import com.teapod.mq.factory.QueneFactory;
import com.teapod.mq.impl.ConcurrentDelayedQuene;
import com.teapod.mq.impl.ConcurrentQuene;

public class ConcurrentDelayedQueneTest {
	
	
	public static void main(String[] args) {

		ConcurrentDelayedQuene<String> concurrentDelayedQuene = QueneFactory.newConcurrentDelayedQuene();
		DelayItem<String> item = new DelayItem<>("000", 1000L*1000L*1000L*2);
		
		concurrentDelayedQuene.put("xxx", item);
		
		Number number = new Number();
		
		while(true){
			
			//System.out.println(System.currentTimeMillis());
			//System.out.println(number.setNum(concurrentDelayedQuene.size()));
			//System.out.println(System.currentTimeMillis()+"-"+concurrentDelayedQuene.circleTake(1000L*8,TimeUnit.MILLISECONDS));
			String value = concurrentDelayedQuene.circleTake(1000L*80,TimeUnit.MILLISECONDS);
			//System.out.println(System.currentTimeMillis());
			System.out.println(value);
			
			//concurrentDelayedQuene.remove(value);
			
			
		}
		
		
		
		

		
		
		
		
		
		
		
		
		
	}

}
