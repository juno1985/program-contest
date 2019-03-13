package com.contest.service.compile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ComplieImplSubThread extends Thread {

	private List<String> resultString = new ArrayList<String>();
	// 从子线程得到输出
	private InputStream inputStream;
	// 从子线程得到输入
	// private OutputStream outputStream;

	public ComplieImplSubThread(Process process) {
		this.inputStream = process.getInputStream();
		// this.outputStream = process.getOutputStream();
	}

	@Override
	public void run() {
		BufferedReader br = new BufferedReader(new InputStreamReader(this.inputStream));
		String line;
		try {
			while ((line = br.readLine()) != null) {
				this.resultString.add(line);
			}
			this.inputStream.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public List<String> getResultString() {
		return this.resultString;
	}
	
	

}
