package com.contest.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class FileUtils {

	
	
	
	public static void saveStrToFile(String filename, String content) throws IOException {
		
		File file = new File(filename);
		if(!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsolutePath());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(content);
		bw.close();
		fw.close();
	}

	

	/**
	 * 使用@Value向静态变量注入必须满足
	 * 1. 类使用@Component
	 * 2. 静态变量使用set方法,并且不能为静态方法
	 * 3. @Value写在set方法上
	 * 
	 */
/*	@Value("${usercode.submit.path}")
	public void setRootPath(String rootPath) {
		FileUtils.rootPath = rootPath;
	}
*/
	
	
	
}
