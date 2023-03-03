package Billiards;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Ellipse2D;
import java.util.List;

public class Pocket {
	private Component canvas;
	int radius = 30;
	int fallRadius = 24;
	PositionX positionX = PositionX.LEFT;
	PositionY positionY = PositionY.TOP;
	int centerX = 0;
	int centerY = 0;

	public Pocket(Component c, PositionX posX, PositionY posY, int pocketRadius, int pocketFallRadius) {
		this.canvas = c;
		radius = pocketRadius;
		fallRadius = pocketFallRadius;
		positionX = posX;
		positionY = posY;
		c.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				Dimension size = c.getSize();

				centerX = 0 - radius;
				centerY = 0 - radius;

				if (positionX == PositionX.RIGHT) {
					centerX = size.width - radius;
				} else if (positionX == PositionX.MIDDLE) {
					centerX = size.width / 2 - radius;
				}

				if (positionY == PositionY.BOTTOM) {
					centerY = size.height - radius;
				} else if (positionY == PositionY.MIDDLE) {
					centerY = size.height / 2 - radius;
				}
			}
		});
	}

	public static enum PositionX {
		LEFT,
		MIDDLE,
		RIGHT,
	}

	public static enum PositionY {
		TOP,
		MIDDLE,
		BOTTOM,
	}

	public boolean isBallFall(Ball ball) {
		List<Integer> point = ball.getCenterPoint();
		int x = point.get(0);
		int y = point.get(1);

		double distance = Math.sqrt(Math.pow((x - centerX), 2) + Math.pow((y - centerY), 2));
		if (distance <= fallRadius) {
			return true;
		} else {
			return false;
		}
	}

	public void draw(Graphics2D g2) {
		g2.setColor(Color.BLACK);
		g2.fill(new Ellipse2D.Double(centerX, centerY, radius * 2, radius * 2));
	}
}
