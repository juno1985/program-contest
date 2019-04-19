package com.contest.common;

import org.thymeleaf.util.StringUtils;

import com.contest.exception.ContestCommonException;

public class GetOSInfor {

	public static String getOSName() {
		String os = System.getProperty("os.name");
		
		if(StringUtils.isEmpty(os)) throw new ContestCommonException("获取操作系统信息异常");
		//判断是windows平台
		else if (os.startsWith("Windows")) {
		
			return "WINTEL";
		}
		//还是unix平台
		else {
		
			return "UNIX";
		}
		
	}
}
