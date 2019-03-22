package com.contest.service.compile;
import java.io.IOException;
import java.io.OutputStream;
/**
 * 向子线程输入
 * @author juno
 *
 */
public class RunImplInputThread extends Thread {

	
	// 向子线程输入
	private OutputStream outputStream;
	//casemodel中的input
	private String input;
	
	public RunImplInputThread(OutputStream outputStream, String input) {
		this.outputStream = outputStream;
		this.input = input;
	}
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	

	@Override
	public void run() {
	
			try {
				this.outputStream.write(input.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				try {
					this.outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	
	}
}
