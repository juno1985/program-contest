package com.contest.service.compile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 从子线程获得输出
 * 
 * @author juno
 *
 */
public class RunImplOutputThread extends Thread {

	private String resultString = "";
	// 从子线程得到输出
	private InputStream inputStream;

	public RunImplOutputThread(InputStream inputStream) {
		this.inputStream = inputStream;

	}

	public String getResultString() {
		return resultString;
	}

	@Override
	public void run() {
		BufferedReader br = new BufferedReader(new InputStreamReader(this.inputStream));
		String line;
		try {

			while ((line = br.readLine()) != null) {

				this.resultString += line;

				// System.out.println("->" + line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				this.inputStream.close();
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

}
