package com.mycompany.portfolio_tracker.io;

import java.io.*;

/**
 * 
 * @author Colin
 */
public class Writer {
	
	private BufferedWriter bufferedWriter;
	
	/**
	 * Constructor
	 * 
	 * @param fileName
	 * @throws IOException
	 */
	public Writer(File fileName) throws IOException {
		
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(fileName));
		}
		catch(IOException e) {
			System.out.println("IOException:");
            e.printStackTrace();
		}
	}
	
	public void write(String string) {
		try {
			bufferedWriter.write(string);
		}
		catch (IOException e) {
			System.out.println("IOException:");
            e.printStackTrace();
		}
		
	}
	
	public void newLine() throws IOException{
		bufferedWriter.newLine();
	}
	
	public void close() throws IOException{
			bufferedWriter.close();
	}
	

}
