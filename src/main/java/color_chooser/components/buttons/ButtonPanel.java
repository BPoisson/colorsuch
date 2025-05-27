package color_chooser.components.buttons;

import color_chooser.ColorChooser;
import color_chooser.components.panels.ColorPanel;
import color_chooser.components.panels.TextPanel;
import managers.GameManager;

import javax.swing.*;

public class ButtonPanel extends JPanel {
    int skips;
    boolean skip;
    final int MAX_SKIPS = 2;
    GameManager gameManager;
    JButton nextColorButton;
    JButton submitButton;
    ColorPanel colorPanel;
    ColorChooser colorChooser;
    TextPanel textPanel;
    String nextColorButtonText;

    public ButtonPanel(GameManager gameManager, ColorPanel colorPanel, ColorChooser colorChooser, TextPanel textPanel) {
        skips = MAX_SKIPS;
        skip = true;
        nextColorButtonText = "Next Color";
        nextColorButton = new JButton(getNextColorButtonText());
        submitButton = new JButton("Submit");
        this.colorPanel = colorPanel;
        this.colorChooser = colorChooser;
        this.textPanel = textPanel;
        this.gameManager = gameManager;

        nextColorButton.addActionListener(_ -> {
            handleNextColorButtonClick();
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

    private void handleNextColorButtonClick() {
        textPanel.disableText();
        colorPanel.disableRectangle();
        colorPanel.nextColor();
        gameManager.resetAttempts();

        if (skip) {
            skips--;
            gameManager.skipUsed();
        }
        if (skips == 0) {
            disableNextColorButton();
        }
        nextColorButton.setText(getNextColorButtonText());
        skip = true;
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

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public void reset() {
        skip = true;
        skips = MAX_SKIPS;
        nextColorButton.setText(getNextColorButtonText());

        enableSubmitButton();
        enableNextColorButton();
    }

    private String getNextColorButtonText() {
        return String.format("%s (Skips %d)", nextColorButtonText, skips);
    }
}
