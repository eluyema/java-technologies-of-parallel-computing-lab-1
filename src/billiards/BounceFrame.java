package Billiards;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BounceFrame extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private BallCanvas canvas;
    public static final int WIDTH = 450;
    public static final int HEIGHT = 350;

    public BounceFrame() {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Bounce programm");
        this.canvas = new BallCanvas();
        canvas.setBackground(new Color(0, 128, 0));
        System.out.println("In Frame Thread name = " +
                Thread.currentThread().getName());
        Container content = this.getContentPane();
        content.add(this.canvas, BorderLayout.CENTER);

        ArrayList<Pocket> pockets = new ArrayList<Pocket>();
        int pocketRadius = 30;
        int pocketFallRadius = 30;

        int boardWidth = WIDTH;
        int boardHeight = HEIGHT;

        pockets.add(new Pocket(canvas, Pocket.PositionX.RIGHT, Pocket.PositionY.TOP, pocketRadius, pocketFallRadius));
        pockets.add(new Pocket(canvas, Pocket.PositionX.LEFT, Pocket.PositionY.TOP, pocketRadius, pocketFallRadius));
        pockets.add(
                new Pocket(canvas, Pocket.PositionX.RIGHT, Pocket.PositionY.BOTTOM, pocketRadius, pocketFallRadius));
        pockets.add(new Pocket(canvas, Pocket.PositionX.LEFT, Pocket.PositionY.BOTTOM, pocketRadius, pocketFallRadius));

        CounterDisplay counterDisplay = new CounterDisplay();

        canvas.addPockets(pockets);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);
        JButton buttonRedBall = new JButton("Add 1 red boll");
        JButton buttonBlueBall = new JButton("Add 1 blue boll");
        JButton buttonRedAndBlueBall = new JButton("Add 1 red ball and 1 blue ball dependent on it");
        JButton buttonTenBlueBall = new JButton("Add 20 blue boll");

        JButton buttonStop = new JButton("Stop");

        buttonRedBall.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ball ball = new Ball(canvas, Color.red);
                canvas.addBall(ball);
                BallThread thread = new BallThread(ball, pockets, counterDisplay);
                thread.setPriority(Thread.MAX_PRIORITY);
                thread.start();

                System.out.println("Thread name = " +
                        thread.getName());
            }
        });

        buttonRedAndBlueBall.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ball redBall = new Ball(canvas, Color.red);
                canvas.addBall(redBall);
                BallThread redThread = new BallThread(redBall, pockets, counterDisplay);
                redThread.setPriority(Thread.MAX_PRIORITY);
                redThread.start();

                System.out.println("Thread name = " +
                        redThread.getName());

                Ball blueBall = new Ball(canvas, Color.blue);
                canvas.addBall(blueBall);
                BallThread blueThread = new BallThread(blueBall, pockets, counterDisplay, redThread);
                blueThread.setPriority(Thread.MAX_PRIORITY);
                blueThread.start();

                System.out.println("Thread name = " +
                        blueThread.getName());
            }
        });

        buttonBlueBall.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ball ball = new Ball(canvas, Color.blue);
                canvas.addBall(ball);
                BallThread thread = new BallThread(ball, pockets, counterDisplay);
                thread.setPriority(Thread.MIN_PRIORITY);
                thread.start();

                System.out.println("Thread name = " +
                        thread.getName());
            }
        });
        buttonTenBlueBall.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 20; i++) {
                    Ball ball = new Ball(canvas, Color.blue);
                    canvas.addBall(ball);
                    BallThread thread = new BallThread(ball, pockets, counterDisplay);
                    thread.setPriority(Thread.MIN_PRIORITY);
                    thread.start();

                    System.out.println("Thread name = " +
                            thread.getName());
                }
            }
        });
        buttonStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);
            }
        });

        buttonPanel.add(counterDisplay.getLabel());
        buttonPanel.add(buttonTenBlueBall);
        buttonPanel.add(buttonBlueBall);
        buttonPanel.add(buttonRedBall);
        buttonPanel.add(buttonRedAndBlueBall);
        buttonPanel.add(buttonStop);

        content.add(buttonPanel, BorderLayout.SOUTH);
    }
}