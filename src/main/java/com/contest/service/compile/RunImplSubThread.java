package com.contest.service.compile;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class RunImplSubThread extends Thread {

	private String resultString = "";
	// 从子线程得到输出
	private InputStream inputStream;
	// 向子线程得到输入
	private OutputStream outputStream;
	//casemodel中的input
	private String input;
	//casemodel中的output
	private String output;
	
	public RunImplSubThread(Process process) {
		this.inputStream = process.getInputStream();
		this.outputStream = process.getOutputStream();
	}
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}
	public String getResultString() {
		return resultString;
	}

	@Override
	public void run() {
		BufferedReader br = new BufferedReader(new InputStreamReader(this.inputStream));
		String line;
		try {
			
//			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(this.outputStream);
			this.outputStream.write(input.getBytes());
			this.outputStream.close();
//			bufferedOutputStream.write(input.getBytes());
			while ((line = br.readLine()) != null) {
				
				this.resultString += line + "\r\n";
			}
			this.inputStream.close();
			br.close();
			this.outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	

}
