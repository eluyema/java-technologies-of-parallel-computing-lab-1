package Billiards;

import javax.swing.JLabel;

public class CounterDisplay {
    private int count;
    private JLabel counterLabel;

    public CounterDisplay() {
        count = 0;
        counterLabel = new JLabel("Count: " + count);
    }

    public JLabel getLabel() {
        return counterLabel;
    }

    public void increment() {
        count++;
        counterLabel.setText("Count: " + count);
    }

    public void decrement() {
        count--;
    }

    public int getCount() {
        return count;
    }
}
