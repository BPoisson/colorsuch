package color_chooser.components.panels;

import javax.swing.*;
import java.awt.*;

public class AttemptPanel extends JPanel {
    int boxDimension;
    JPanel attemptPanel;
    JPanel attemptRowPanel;

    public AttemptPanel() {
        boxDimension = 30;
        attemptPanel = new JPanel();
        attemptPanel.setLayout(new BoxLayout(attemptPanel, BoxLayout.Y_AXIS));
        attemptRowPanel = new JPanel();
        attemptPanel.add(attemptRowPanel);
    }

    public JPanel build() {
        return attemptPanel;
    }

    public void addAttempt(float[] colorHSB) {
        JPanel attemptBox = new JPanel();
        attemptBox.setBackground(Color.getHSBColor(colorHSB[0], colorHSB[1], colorHSB[2]));
        attemptBox.setPreferredSize(new Dimension(boxDimension, boxDimension));
        attemptRowPanel.add(attemptBox);

        attemptPanel.repaint();
    }

    public void reset() {
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
