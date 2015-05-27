package com.mycompany.portfolio_tracker;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.mycompany.portfolio_tracker.util.StrathQuoteServer;

public class testClass {

	public static void main(String[] args) {
		try {

			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader reader = new BufferedReader(isr);
			String line = null;
			System.out.print("Input a ticker symbol: ");
			while ((line = reader.readLine()) != null) {
				String str = StrathQuoteServer.getCompanyName(line);
				System.out.println(line + " has a stock value of " + str);
				System.out.print("Input a ticker symbol: ");
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
