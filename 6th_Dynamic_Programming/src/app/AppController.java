package app;


public class AppController {
	
	// Constant
	private static final int MaxInputNumber = 1000000;
	private static final int[] memoizedFibo = new int[MaxInputNumber];

	// Constructor
	public AppController() {
		
	}
	
	// private methods
	private int fiboByRecursion(int n) {
		if(n==0) {
			return 0;	// f0
		}
		else if(n==1) {
			return 1;	// f1
		}
		else {
			return (fiboByRecursion(n-1) + fiboByRecursion(n-2));
		}
	}

	private int fiboByMemoized(int n) {
		// 배열 초기화
		for(int i=0; i<n; i++) {
			memoizedFibo[i] = -1;
		}
		
		memoizedFibo[0] = 0;	// f0
		memoizedFibo[1] = 1;	// f1
		return memoized(n);
	}
	
	private int memoized(int n) {
		if(memoizedFibo[n] < 0) {
			// 음수이면 계산되지 않았으므로 계산
			memoizedFibo[n] = memoized(n-1) + memoized(n-2);
		}
		// 새롭게 계산되거나 계산 되어있는 수 반환
		return memoizedFibo[n];
	}
	
	private long durationByRecursion(int n) {
		// 재귀로 인한 피보나치 수열 계산 시간
		Timer.start();
		this.fiboByRecursion(n);
		Timer.stop();
		
		return Timer.duration();
	}
	
	private long durationByMemoized(int n) {
		// Dynamic Programming으로 인한 피보나치 수열 계산 시간
		Timer.start();
		this.fiboByMemoized(n);
		Timer.stop();
		
		return Timer.duration();
	}
	
	// Public member method
	public void run() {
		try {
			AppView.output("구할 피보나치 수열의 항 번호를 입력하시오 : ");
			int inputNum = AppView.inputInt();
			AppView.outputLine("Recursion을 통한 피보나치 수열의 "+ inputNum + "번째 항을 계산하는데 걸린 시간은 " + this.durationByRecursion(inputNum) + " ms 입니다.");
			AppView.outputLine("Dynamic Programming을 통한 피보나치 수열의 "+ inputNum + "번째 항을 계산하는데 걸린 시간은 " + this.durationByMemoized(inputNum) + " ms 입니다.");
		}
		catch(Exception e) {
			AppView.outputLine("올바른 수가 입력되지 않았습니다.");
		}
		
		
		
		
	}
	
}