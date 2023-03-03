package Billiards;

import java.util.ArrayList;

public class BallThread extends Thread {
	private Ball b;
	private ArrayList<Pocket> pockets;
	private CounterDisplay counter;
	private BallThread thread = null;

	public BallThread(Ball ball, ArrayList<Pocket> listPockets, CounterDisplay counterInstance,
			BallThread highPriorityThread) {
		b = ball;
		pockets = listPockets;
		counter = counterInstance;
		thread = highPriorityThread;
	}

	public BallThread(Ball ball, ArrayList<Pocket> listPockets, CounterDisplay counterInstance) {
		b = ball;
		pockets = listPockets;
		counter = counterInstance;
	}

	@Override
	public void run() {
		try {
			for (int i = 1; i < 10000; i++) {
				if (thread != null && thread.isAlive()) {
					thread.join();
				}

				b.move();
				System.out.println("Thread name = " + Thread.currentThread().getName());

				boolean ballFallen = false;
				for (int j = 0; j < pockets.size(); j++) {
					Pocket pocket = pockets.get(j);
					if (pocket.isBallFall(b)) {
						ballFallen = true;
						break;
					}
				}
				if (ballFallen) {
					b.clearMyself();
					counter.increment();
					break;
				}
				Thread.sleep(5);
			}
		} catch (InterruptedException ex) {

		}
	}
}