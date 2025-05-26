package color_chooser.components.panels;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class AttemptPanel extends JPanel {
    int attemptNumber;
    int boxDimension;
    float[][] attempts;
    JPanel attemptPanel;
    JPanel attemptRowPanel;
    Random rand;

    public AttemptPanel() {
        attemptNumber = 0;
        boxDimension = 30;
        attempts = new float[8][3];
        attemptPanel = new JPanel();
        attemptPanel.setLayout(new BoxLayout(attemptPanel, BoxLayout.Y_AXIS));
        attemptRowPanel = new JPanel();
        attemptPanel.add(attemptRowPanel);
        rand = new Random();
    }

    public JPanel build() {
        return attemptPanel;
    }

    public int submitAttempt(float[] colorHSB) {
        if (attemptNumber == 8) {
            attemptNumber = 0;
            attempts = new float[8][3];
            resetAttempts();
        }
        attempts[attemptNumber] = colorHSB;
        attemptNumber++;
        addAttempt(colorHSB);

        if (attemptNumber == 8) {
            return attemptNumber;
        }
        return 0;
    }

    private void addAttempt(float[] colorHSB) {
        JPanel attemptBox = new JPanel();
        attemptBox.setBackground(Color.getHSBColor(colorHSB[0], colorHSB[1], colorHSB[2]));
        attemptBox.setPreferredSize(new Dimension(boxDimension, boxDimension));
        attemptRowPanel.add(attemptBox);

        attemptPanel.repaint();
    }

    public void resetAttempts() {
        attemptNumber = 0;
        attempts = new float[8][3];
        attemptPanel.remove(attemptRowPanel);
        attemptRowPanel = new JPanel();
        attemptPanel.add(attemptRowPanel);

        attemptPanel.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
