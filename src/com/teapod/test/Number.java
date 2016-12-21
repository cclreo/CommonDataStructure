package com.teapod.test;

import org.omg.CORBA.PUBLIC_MEMBER;

public class Number {

	public int max = Integer.MIN_VALUE;
	public int min = Integer.MAX_VALUE;;
	public int common = 0;

	private static long time = System.currentTimeMillis();

	public Number() {
		initialize();
	}

	public String setNum(int num) {

		if (System.currentTimeMillis() - time > 1000L * 10) {
			time = System.currentTimeMillis();
			initialize();
		}

		if (num > this.max)
			this.max = num;
		if (num < this.min)
			this.min = num;
		this.common = (this.max + this.min) / 2;

		return this.toString();

	}

	@Override
	public String toString() {
		return "Number [max=" + max + ", min=" + min + ", common=" + common
				+ "]";
	}

	public void initialize() {

		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		common = 0;

	}

}
