package ch.kleemans.javaexamples.timing;

/**
 * A simple Timer for measuring time spent in code.
 * 
 * @author Adrianus Kleemans
 */
public class Timer {
	private long startTime = 0;
	private long endTime = 0;

	/**
	 * Starts or resets the timer.
	 */
	public void start() {
		this.startTime = System.nanoTime();
	}

	/**
	 * Stops the timer.
	 */
	public void stop() {
		this.endTime = System.nanoTime();
	}

	/**
	 * Returns the time after start() and stop() have been called.
	 * 
	 * @return the time in seconds, rounded to 2 decimal places
	 */
	public double getTime() {
		assert this.endTime > this.startTime;
		return (double) Math.round(((this.endTime - this.startTime) / 1000000000.0) * 100) / 100;
	}

	public double getTimeInMilliseconds() {
		assert this.endTime > this.startTime;
		return (double) Math.round(((this.endTime - this.startTime) / 1000000.0) * 100) / 100;
	}
}
