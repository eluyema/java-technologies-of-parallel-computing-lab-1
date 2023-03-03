package Counter;

public class Counter {
	private int count = 0;
	private Object sync = new Object();
	
	public void asyncIncrement() {
		count++;
	}
	
	public void asyncDecrement() {
		count--;
	}
	
	public synchronized void syncMethodIncrement() {
		count++;
	}
	
	public synchronized void syncMethodDecrement() {
		count--;
	}
	
	public void syncBlockIncrement() {
		synchronized(this) {
			count++;
		}
	}
	
	public void syncBlockDecrement() {
		synchronized(this) {
			count--;
		}
	}
	
	public void syncObjectIncrement() {
		synchronized(sync) {
			count++;
		}
	}
	
	public void syncObjectDecrement() {
		synchronized(sync) {
			count--;
		}
	}
	
	public synchronized int getCount() {
		return count;
	}
	
	public synchronized void reset () {
		count = 0;
	}
}
