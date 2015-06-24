package ch.kleemans.javaexamples.timing;

import java.util.ArrayList;
import java.util.Random;

/**
 * Example for {@link Timer}. Does some useless calculating and times it.
 * 
 * @author Adrianus Kleemans
 *
 */
public class TimerExample {

	public static void main(String[] args) {
		Timer t = new Timer();
		System.out.println("Doing some calculations...");
		Random random = new Random();

		// timed part ------------------
		t.start();
		for (int i = 0; i < 1000; i++) {
			ArrayList<String> data = new ArrayList<String>();
			for (int j = 0; j < 100; j++) {
				data.add("test" + Integer.toString(random.nextInt(10000000)));
			}
			data.add(data.get(random.nextInt(100))); // some reading access
		}
		t.stop();
		// -----------------------------

		System.out.println("Finished in t = " + t.getTimeInMilliseconds() + "ms");
	}
}