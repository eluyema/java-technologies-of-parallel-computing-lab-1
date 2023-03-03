package Counter;

public class CounterThread extends Thread {
	private Counter counter;
	private CounterSyncType syncType;
	private boolean decrement;
	
	public CounterThread(Counter counterInstance, CounterSyncType counterSyncType, boolean decrementable) {
		counter = counterInstance;
		syncType = counterSyncType;
		decrement=decrementable;
	}
	
	@Override
	public void run() {
		for(int i =0; i < 100000; i++) {
			if(decrement) {
				switch(syncType) {
					case ASYNC:{
						counter.asyncDecrement();
						break;
					}
					case SYNC_METHOD:{
						counter.syncMethodDecrement();
						break;
					}
					case SYNC_OBJECT:{
						counter.syncObjectDecrement();
						break;
					}
					case SYNC_BLOCK:{
						counter.syncBlockDecrement();
						break;
					}
				}
			} else {
				switch(syncType) {
					case ASYNC:{
						counter.asyncIncrement();
						break;
					}
					case SYNC_METHOD:{
						counter.syncMethodIncrement();
						break;
					}
					case SYNC_OBJECT:{
						counter.syncObjectIncrement();
						break;
					}
					case SYNC_BLOCK:{
						counter.syncBlockIncrement();
						break;
					}
				}
			}
		}
		
	}
}
