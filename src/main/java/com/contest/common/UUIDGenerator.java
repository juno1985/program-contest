package com.contest.common;

import java.util.UUID;

public class UUIDGenerator {
	
	public static String getUUID32() {
		String uuid = UUID.randomUUID().toString().replace("-","").toLowerCase();
		return uuid;
	}

}
