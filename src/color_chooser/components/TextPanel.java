package color_chooser.components;

import javax.swing.*;
import java.awt.*;

public class TextPanel extends JPanel {
    JTextArea textArea;

    public TextPanel() {
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.BOLD, 18));
        textArea.setBackground(this.getBackground());
        add(textArea);
    }

    public void createText(float[] panelColorHSB, float[] chosenColorHSB) {
        int[] panelColorInt = new int[] {(int) (panelColorHSB[0] * 360), (int) (panelColorHSB[1] * 100), (int) (panelColorHSB[2] * 100)};
        int[] chosenColorInt = new int[] {(int) (chosenColorHSB[0] * 360), (int) (chosenColorHSB[1] * 100), (int) (chosenColorHSB[2] * 100)};

        this.add(textArea);
        textArea.setText(getText(panelColorInt, chosenColorInt));
        repaint();
    }

    public String getText(int[] panelColorInt, int[] chosenColorInt) {
        int hueDiff = panelColorInt[0] - chosenColorInt[0];
        int saturationDiff = panelColorInt[1] - chosenColorInt[1];
        int brightnessDiff = panelColorInt[2] - chosenColorInt[2];

        return "Actual: H: " + panelColorInt[0] + ", S: " + panelColorInt[1] + ", B: " + panelColorInt[2] + "\n" +
                "Picked: H: " + chosenColorInt[0] + ", S: " + chosenColorInt[1] + ", B: " + chosenColorInt[2] + "\n\n" +
                "Diff: H: " + hueDiff + ", S: " + saturationDiff + ", B: " + brightnessDiff;
    }

    public void disableText() {
        this.remove(textArea);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
