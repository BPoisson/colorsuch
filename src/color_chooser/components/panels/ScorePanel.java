package color_chooser.components.panels;

import color_chooser.components.labels.OutlinedLabel;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class ScorePanel extends JPanel {
    int boxDimension;
    int attemptCounter;
    JPanel scorePanel;

    public ScorePanel() {
        boxDimension = 30;
        attemptCounter = 0;
        scorePanel = new JPanel();
    }

    public JPanel build() {
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            addAttempt(0, new float[] {rand.nextFloat(), rand.nextFloat(), rand.nextFloat()});
        }
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

    public void writeScore() {

    }
}
