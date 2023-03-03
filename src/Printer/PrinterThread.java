package Printer;

public class PrinterThread extends Thread {
	private Printer printer;
	private boolean control;
	private char symbol;
	private boolean useAsync;
	
	public PrinterThread(Printer syncPrinter, char symbolValue, boolean controlValue, boolean async) {
		printer = syncPrinter;
		symbol = symbolValue;
		control=controlValue;
		useAsync=async;
	}	
	
	@Override
	public void run() {
		for(int i= 0;i<10000;i++) {
			if(useAsync) {
				printer.asyncPrint(symbol);
			} {
				printer.print(symbol, control);
			}
		}
	}
}
