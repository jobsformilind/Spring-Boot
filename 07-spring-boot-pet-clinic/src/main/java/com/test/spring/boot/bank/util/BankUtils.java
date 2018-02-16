package com.test.spring.boot.bank.util;

import java.util.HashSet;
import java.util.Set;

public final class BankUtils {

	public static <T> Set<T> ensureNotNullHashSet(Set<T> set) {
		if (set == null) {
			set = new HashSet<>();
		}
		return set;
	}

}
