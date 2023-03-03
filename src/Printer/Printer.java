package Printer;

public class Printer {
	public boolean control;
	public int rowIndex;
	
	Printer(boolean startControl){
		control=startControl;
		rowIndex=0;
	}
	
	private synchronized boolean getControl() {
		return control;
	}
	
	public void asyncPrint(char symbol) {
		System.out.print(symbol);
		
		if(rowIndex%100==0) {
			System.out.println('\n');
		}
		rowIndex++;
	}
	
	public synchronized void print(char symbol, boolean controlValue) {
		while(getControl()!=controlValue) {
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println(e);	
			}
		}
		System.out.print(symbol);
		
		if(rowIndex%100==0) {
			System.out.println('\n');
		}
		rowIndex++;
		control=!control;
		notifyAll();
	}
}
