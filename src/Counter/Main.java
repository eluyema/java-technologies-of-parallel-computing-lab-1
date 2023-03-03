package Counter;

public class Main {

	public static void main(String[] args) {
		try {
			Counter counter = new Counter();
			
			CounterSyncType syncType = CounterSyncType.SYNC_OBJECT;
			
			CounterThread threadIncAsync = new CounterThread(counter, syncType, false);
			CounterThread threadDecAsync = new CounterThread(counter, syncType, true);
			threadIncAsync.start();
			threadDecAsync.start();
			
			threadIncAsync.join();
			threadDecAsync.join();
			
			System.out.println("Counter result: " + counter.getCount());
		}
		catch(InterruptedException e) {
			System.out.println("Error appeared: " + e);
		}
	}

}
