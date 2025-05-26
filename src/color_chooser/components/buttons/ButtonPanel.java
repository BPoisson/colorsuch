package color_chooser.components.buttons;

import color_chooser.ColorChooser;
import color_chooser.components.panels.AttemptPanel;
import color_chooser.components.panels.ColorPanel;
import color_chooser.components.panels.ScorePanel;
import color_chooser.components.panels.TextPanel;

import javax.swing.*;

public class ButtonPanel extends JPanel {
    JButton nextButton;
    JButton submitButton;
    ColorPanel colorPanel;
    ColorChooser colorChooser;
    TextPanel textPanel;
    AttemptPanel attemptPanel;

    public ButtonPanel(ColorPanel colorPanel, ColorChooser colorChooser, TextPanel textPanel, AttemptPanel attemptPanel, ScorePanel scorePanel) {
        nextButton = new JButton("Next Color");
        submitButton = new JButton("Submit");
        this.colorPanel = colorPanel;
        this.colorChooser = colorChooser;
        this.textPanel = textPanel;
        this.attemptPanel = attemptPanel;

        nextButton.addActionListener(_ -> {
            textPanel.disableText();
            colorPanel.disableRectangle();
            colorPanel.nextColor();
            attemptPanel.resetAttempts();
        });
        submitButton.addActionListener(_ -> {
            colorPanel.setRectangle(colorChooser.getColorHSB());
            textPanel.createText(colorPanel.getColorHSB(), colorChooser.getColorHSB());
            attemptPanel.submitAttempt(colorChooser.getColorHSB());
        });
    }

    public JPanel getPanel() {
        JPanel jPanel = new JPanel();

        jPanel.add(nextButton);
        jPanel.add(submitButton);

        return jPanel;
    }
}
