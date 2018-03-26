package com.test.spring.utils;

public class ThreadUtils {

	public static void sleepSilently(long pause) {
		try {
			Thread.sleep(pause);
		} catch (Exception e) {
			// do nothing
		}
	}
}
