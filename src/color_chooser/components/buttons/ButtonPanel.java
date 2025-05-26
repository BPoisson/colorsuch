package color_chooser.components.buttons;

import color_chooser.ColorChooser;
import color_chooser.components.panels.ColorPanel;
import color_chooser.components.panels.TextPanel;
import managers.GameManager;

import javax.swing.*;

public class ButtonPanel extends JPanel {
    GameManager gameManager;
    JButton nextColorButton;
    JButton submitButton;
    ColorPanel colorPanel;
    ColorChooser colorChooser;
    TextPanel textPanel;

    public ButtonPanel(GameManager gameManager, ColorPanel colorPanel, ColorChooser colorChooser, TextPanel textPanel) {
        nextColorButton = new JButton("Next Color");
        disableNextColorButton();
        submitButton = new JButton("Submit");
        this.colorPanel = colorPanel;
        this.colorChooser = colorChooser;
        this.textPanel = textPanel;
        this.gameManager = gameManager;

        nextColorButton.addActionListener(_ -> {
            disableNextColorButton();
            textPanel.disableText();
            colorPanel.disableRectangle();
            colorPanel.nextColor();
            gameManager.resetAttempts();
        });
        submitButton.addActionListener(_ -> {
            colorPanel.setRectangle(colorChooser.getColorHSB());
            textPanel.createText(colorPanel.getColorHSB(), colorChooser.getColorHSB());
            gameManager.submitAttempt(colorPanel.getColorHSB(), colorChooser.getColorHSB());
        });
    }

    public JPanel getPanel() {
        JPanel jPanel = new JPanel();

        jPanel.add(nextColorButton);
        jPanel.add(submitButton);

        return jPanel;
    }

    public void disableNextColorButton() {
        nextColorButton.setEnabled(false);
    }

    public void enableNextColorButton() {
        nextColorButton.setEnabled(true);
    }

    public void disableSubmitButton() {
        submitButton.setEnabled(false);
    }

    public void enableSubmitButton() {
        submitButton.setEnabled(true);
    }
}
