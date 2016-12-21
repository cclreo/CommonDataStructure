package com.teapod.model;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class DelayItem<T> implements Delayed {
	/** Base of nanosecond timings, to avoid wrapping */
	private final long NANO_ORIGIN = System.nanoTime();
	private long timeout;

	/**
	 * Returns nanosecond time offset by origin
	 */
	final long now() {
		return System.nanoTime() - NANO_ORIGIN;
	}

	/**
	 * Sequence number to break scheduling ties, and in turn to guarantee FIFO
	 * order among tied entries.
	 */
	private static final AtomicLong sequencer = new AtomicLong(0);

	/** Sequence number to break ties FIFO */
	private long sequenceNumber;

	/** The time the task is enabled to execute in nanoTime units */
	private long time;

	public final T item;

	public DelayItem(T submit, long timeoutSecond) {

		this.timeout = timeoutSecond*1000L*1000L*1000L;
		this.time = System.nanoTime() + timeout;
		this.item = submit;
		this.sequenceNumber = sequencer.getAndIncrement();
	}

	public long getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public void setDelay(long timeout) {
		this.timeout = timeout;
		this.time = System.nanoTime() + timeout;
	}

	public long getTimeout() {
		return this.timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	public T getItem() {
		return this.item;
	}

	@Override
	public int compareTo(Delayed other) {
		if (other == this) // compare zero ONLY if same object
			return 0;
		if (other instanceof DelayItem) {
			DelayItem x = (DelayItem) other;
			if (sequenceNumber - x.sequenceNumber == 0)
				return 0;

		}
		long d = (getDelay(TimeUnit.NANOSECONDS) - other
				.getDelay(TimeUnit.NANOSECONDS));
		return (d == 0) ? 0 : ((d < 0) ? -1 : 1);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (sequenceNumber ^ (sequenceNumber >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DelayItem other = (DelayItem) obj;
		if (sequenceNumber != other.sequenceNumber)
			return false;
		return true;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		long d = unit.convert(time - System.nanoTime(), unit);
		return d;
	}

}
