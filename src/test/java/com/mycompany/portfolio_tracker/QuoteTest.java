/**
 * 
 */
package com.mycompany.portfolio_tracker;

import com.mycompany.portfolio_tracker.exceptions.MethodException;
import com.mycompany.portfolio_tracker.model.QuoteImpl;

/**
 * @author colin
 *
 */
public class QuoteTest {

	public static void main(String[] args) {

		QuoteImpl q = new QuoteImpl(true);

		try {

			// pass in ticker to get the new values works for worldwide markets
			// note '.l' for UK shares
			q.setValues("LGEN.L");
			// q.getStockName("MSFT");

		} catch (Exception e) {

			System.err.println(e);

		}// end try catch

		// test to see that it works
		try {

			System.out.println("name: " + q.getStockName());
			System.out.println("ticker: " + q.getTicker());

			System.out.println("latest value: " + q.getLatest());

			System.out.println("date: " + q.getDate());

			System.out.println("time: " + q.getTime());

			System.out.println("change: " + q.getChange());

			System.out.println("day range max: " + q.getRangeMax());

			System.out.println("day range min: " + q.getRangeMin());

			System.out.println("open: " + q.getOpen());

			System.out.println("volume: " + q.getVolume());

		} catch (MethodException e) {
			e.printStackTrace();
		}
	}
}
