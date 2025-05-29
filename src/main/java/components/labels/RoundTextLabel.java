package components.labels;

import javax.swing.*;
import java.awt.*;

public class RoundTextLabel extends JLabel {
    int round;
    int maxRounds;

    public RoundTextLabel(int maxRounds) {
        round = 1;
        this.maxRounds = maxRounds;

        setFont(new Font("Arial", Font.BOLD, 18));
        setText("Round " + round + " of " + maxRounds);
        setHorizontalAlignment(SwingConstants.CENTER);
    }

    public void incrementRound() {
        round++;
        repaint();
    }

    public void reset() {
        this.round = 1;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        setText("Round " + round + " of " + maxRounds);
    }
}
