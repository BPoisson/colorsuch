package color_chooser.components.panels;

import color_chooser.components.labels.OutlinedLabel;

import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel {
    int boxDimension;
    int attemptCounter;
    JPanel scorePanel;

    public ScorePanel() {
        boxDimension = 30;
        attemptCounter = 0;
        scorePanel = new JPanel();
        scorePanel.setPreferredSize(new Dimension(boxDimension * 10, boxDimension));
    }

    public JPanel build() {
        return scorePanel;
    }

    public void addAttempt(int attemptScore, float[] colorHSB) {
        attemptCounter += attemptScore;

        JPanel attemptBox = new JPanel();
        attemptBox.setBackground(Color.getHSBColor(colorHSB[0], colorHSB[1], colorHSB[2]));
        attemptBox.setPreferredSize(new Dimension(boxDimension, boxDimension));
        attemptBox.add(new OutlinedLabel("" + attemptScore));
        scorePanel.add(attemptBox);

        scorePanel.repaint();
    }

    public void reset() {
        scorePanel.removeAll();
        attemptCounter = 0;
        scorePanel.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
