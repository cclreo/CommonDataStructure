package com.teapod.mq;

public interface Quene<T> {

	public T take(String messageId);

	public T take();

	public boolean roollBack(String messageId, T t);

	public boolean put(String messageId, T t);

	public int size();

}
