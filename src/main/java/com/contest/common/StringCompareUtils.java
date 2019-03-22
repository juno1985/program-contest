package com.contest.common;

public class StringCompareUtils {
	
	public static String ReplaceEnterChars(String str) {
		str = str.replaceAll("\n", "");
		str = str.replaceAll("\r", "");
		return str;
	}

}
