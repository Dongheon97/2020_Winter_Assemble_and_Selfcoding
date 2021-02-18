package app;

public final class Timer {

	// private static variables
	private static long startTime;
	private static long stopTime;
	
	// "private" Constructor since this class is static
	private Timer() {
		
	};
	
	// public methods
	public static void start() {
		Timer.startTime = System.nanoTime();
	}
	public static void stop() {
		Timer.stopTime = System.nanoTime();
	}
	public static long duration() {
		return (Timer.stopTime - Timer.startTime) /1000;
	}
}
