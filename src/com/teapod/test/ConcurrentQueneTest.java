package com.teapod.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.DelayQueue;

import com.teapod.model.DelayItem;
import com.teapod.mq.factory.QueneFactory;
import com.teapod.mq.impl.ConcurrentDelayedQuene;
import com.teapod.mq.impl.ConcurrentQuene;

public class ConcurrentQueneTest {
	
	
	public static void main(String[] args) {
		
		
		ConcurrentQuene<String> concurrentQuene = QueneFactory.newConcurrentQuene();
		
		concurrentQuene.put("000", "000");
		concurrentQuene.put("000", "000");
		concurrentQuene.put("000", "000");
		concurrentQuene.put("000", "000");
		
		concurrentQuene.take("000");
		
		concurrentQuene.take("000");
		concurrentQuene.take("000");
		concurrentQuene.take("000");
		
		//System.out.println(concurrentQuene.size());

		ConcurrentDelayedQuene<String> concurrentDelayedQuene = QueneFactory.newConcurrentDelayedQuene();
		DelayItem<String> item = new DelayItem<>("000", 1000L*1000L*1000L*2);
		
		concurrentDelayedQuene.put("", item);
		
		//System.out.println(concurrentDelayedQuene.take());

		
		
		
		
		
		
		
		
	}

}
