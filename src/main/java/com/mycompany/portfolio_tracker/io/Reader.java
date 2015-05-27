package com.mycompany.portfolio_tracker.io;

import java.io.*;

/**
 * 
 * @author colin
 */
public class Reader {

	private BufferedReader bufferedReader;
	
	/**
	 * Constructor
	 * 
	 * @param fileName
	 * @throws IOException
	 */
	public Reader(File fileName) throws IOException {
		try {
			bufferedReader = new BufferedReader(new FileReader(fileName));
		}
		catch(IOException e) {
			System.out.println("IOException:");
		}
	}
	
	public String read() throws IOException {
		return bufferedReader.readLine();
	}
	
	public void reset() throws IOException {
		bufferedReader.reset();
	}
	
	public void close() throws IOException {
			bufferedReader.close();
	}

}
