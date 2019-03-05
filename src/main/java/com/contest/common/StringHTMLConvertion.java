package com.contest.common;

public class StringHTMLConvertion {

	public static String StringToHTML(String str) {
		//替换所有换行符
		str = str.replaceAll("\n", "<br>");
		str = str.replaceAll("\r\n", "<br>");
		//替换所有空格
		str = str.replace(" ", "&nbsp;");
		
		return str;
	}
}
