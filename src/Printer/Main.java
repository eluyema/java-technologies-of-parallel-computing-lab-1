package Printer;

public class Main {

	public static void main(String[] args) {
		boolean useAsync = false;
		
		boolean startControl = false;
		
		Printer printer = new Printer(startControl);
		
		PrinterThread threadFirst = new PrinterThread(printer, '-', startControl, useAsync);
		PrinterThread threadSecond = new PrinterThread(printer, '|', !startControl, useAsync);
		threadFirst.start();
		threadSecond.start();
	}

}
