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
		// �迭 �ʱ�ȭ
		for(int i=0; i<n; i++) {
			memoizedFibo[i] = -1;
		}
		
		memoizedFibo[0] = 0;	// f0
		memoizedFibo[1] = 1;	// f1
		return memoized(n);
	}
	
	private int memoized(int n) {
		if(memoizedFibo[n] < 0) {
			// �����̸� ������ �ʾ����Ƿ� ���
			memoizedFibo[n] = memoized(n-1) + memoized(n-2);
		}
		// ���Ӱ� ���ǰų� ��� �Ǿ��ִ� �� ��ȯ
		return memoizedFibo[n];
	}
	
	private long durationByRecursion(int n) {
		// ��ͷ� ���� �Ǻ���ġ ���� ��� �ð�
		Timer.start();
		this.fiboByRecursion(n);
		Timer.stop();
		
		return Timer.duration();
	}
	
	private long durationByMemoized(int n) {
		// Dynamic Programming���� ���� �Ǻ���ġ ���� ��� �ð�
		Timer.start();
		this.fiboByMemoized(n);
		Timer.stop();
		
		return Timer.duration();
	}
	
	// Public member method
	public void run() {
		try {
			AppView.output("���� �Ǻ���ġ ������ �� ��ȣ�� �Է��Ͻÿ� : ");
			int inputNum = AppView.inputInt();
			AppView.outputLine("Recursion�� ���� �Ǻ���ġ ������ "+ inputNum + "��° ���� ����ϴµ� �ɸ� �ð��� " + this.durationByRecursion(inputNum) + " ms �Դϴ�.");
			AppView.outputLine("Dynamic Programming�� ���� �Ǻ���ġ ������ "+ inputNum + "��° ���� ����ϴµ� �ɸ� �ð��� " + this.durationByMemoized(inputNum) + " ms �Դϴ�.");
		}
		catch(Exception e) {
			AppView.outputLine("�ùٸ� ���� �Էµ��� �ʾҽ��ϴ�.");
		}
		
		
		
		
	}
	
}