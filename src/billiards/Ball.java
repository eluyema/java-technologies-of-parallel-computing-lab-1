package Billiards;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

class Ball {
	private BallCanvas canvas;
	private static final int XSIZE = 20;
	private static final int YSIZE = 20;
	private int x = 0;
	private int y = 0;
	private int dx = 2;
	private int dy = 2;
	private Color color = Color.white;

	public Ball(BallCanvas c, Color ballColor) {
		this.canvas = c;

		this.color = ballColor;
		if (Math.random() < 0.5) {
			x = new Random().nextInt(this.canvas.getWidth());
			y = 0;
		} else {
			x = 0;
			y = new Random().nextInt(this.canvas.getHeight());
		}
	}

	public static void f() {
		int a = 0;
	}

	public void clearMyself() {
		canvas.removeBall(this);
	}

	public List<Integer> getCenterPoint() {
		return Arrays.asList(x - XSIZE / 2, y - YSIZE / 2);
	}

	public void draw(Graphics2D g2) {
		g2.setColor(this.color);
		g2.fill(new Ellipse2D.Double(x, y, XSIZE, YSIZE));
	}

	public void move() {
		x += dx;
		y += dy;
		if (x < 0) {
			x = 0;
			dx = -dx;
		}
		if (x + XSIZE >= this.canvas.getWidth()) {
			x = this.canvas.getWidth() - XSIZE;
			dx = -dx;
		}
		if (y < 0) {
			y = 0;
			dy = -dy;
		}
		if (y + YSIZE >= this.canvas.getHeight()) {
			y = this.canvas.getHeight() - YSIZE;
			dy = -dy;
		}
		this.canvas.repaint();
	}
}
