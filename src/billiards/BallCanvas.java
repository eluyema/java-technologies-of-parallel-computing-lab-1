package Billiards;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class BallCanvas extends JPanel {
	private static final long serialVersionUID = 1L;
	private ArrayList<Ball> balls = new ArrayList<>();
	private ArrayList<Pocket> pockets = new ArrayList<>();

	public void addBall(Ball b) {
		this.balls.add(b);
	}

	public void removeBall(Ball b) {
		this.balls.remove(b);
	}

	public void addPockets(ArrayList<Pocket> listPockets) {
		pockets = listPockets;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for (int i = 0; i < balls.size(); i++) {
			Ball b = balls.get(i);
			b.draw(g2);
		}
		for (int i = 0; i < pockets.size(); i++) {
			Pocket pocket = pockets.get(i);
			pocket.draw(g2);
		}
	}
}
