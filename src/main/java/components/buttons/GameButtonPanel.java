package components.buttons;

import color_chooser.ColorChooser;
import components.panels.ColorPanel;
import components.panels.ColorTextPanel;
import managers.GameManager;

import javax.swing.*;

public class GameButtonPanel extends JPanel {
    int skips;
    final int MAX_SKIPS;
    int attempts;
    final int MAX_ATTEMPTS;
    boolean skip;
    GameManager gameManager;
    JButton nextColorButton;
    JButton submitButton;
    ColorPanel colorPanel;
    ColorChooser colorChooser;
    ColorTextPanel colorTextPanel;
    String nextColorButtonText;
    String submitButtonText;

    public GameButtonPanel(GameManager gameManager, ColorPanel colorPanel, ColorChooser colorChooser, ColorTextPanel colorTextPanel, final int MAX_SKIPS, final int MAX_ATTEMPTS) {
        skips = MAX_SKIPS;
        this.MAX_SKIPS = MAX_SKIPS;
        skip = true;
        attempts = MAX_ATTEMPTS;
        this.MAX_ATTEMPTS = MAX_ATTEMPTS;
        nextColorButtonText = "Next Color";
        submitButtonText = "Submit";
        nextColorButton = new JButton(getNextColorButtonText());
        submitButton = new JButton(getSubmitButtonText());
        this.colorPanel = colorPanel;
        this.colorChooser = colorChooser;
        this.colorTextPanel = colorTextPanel;
        this.gameManager = gameManager;

        nextColorButton.addActionListener(_ -> {
            handleNextColorButtonClick();
        });
        submitButton.addActionListener(_ -> {
            handleSubmitButtonClick();
        });
    }

    public JPanel getPanel() {
        JPanel jPanel = new JPanel();

        jPanel.add(nextColorButton);
        jPanel.add(submitButton);

        return jPanel;
    }

    private void handleNextColorButtonClick() {
        attempts = MAX_ATTEMPTS;
        colorTextPanel.disableText();
        colorPanel.disableRectangle();
        colorPanel.nextColor();
        gameManager.resetAttempts();

        if (!skip) {
            gameManager.incrementRound();
        }

        if (skip) {
            skips--;
            gameManager.skipUsed();
        }
        if (skips == 0) {
            disableNextColorButton();
        }
        nextColorButton.setText(getNextColorButtonText());
        submitButton.setText(getSubmitButtonText());
        skip = true;
    }

    private void handleSubmitButtonClick() {
        attempts--;
        colorPanel.setRectangle(colorChooser.getColorHSB());
        colorTextPanel.createText(colorPanel.getColorHSB(), colorChooser.getColorHSB());
        gameManager.submitAttempt(colorPanel.getColorHSB(), colorChooser.getColorHSB());
        submitButton.setText(getSubmitButtonText());
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
        attempts = MAX_ATTEMPTS;
        nextColorButton.setText(getNextColorButtonText());
        submitButton.setText(getSubmitButtonText());

        enableSubmitButton();
        enableNextColorButton();
    }

    private String getNextColorButtonText() {
        if (skips == 1) {
            return String.format("%s (%d Skip)", nextColorButtonText, skips);
        }
        return String.format("%s (%d Skips)", nextColorButtonText, skips);
    }

    private String getSubmitButtonText() {
        if (attempts == 1) {
            return String.format("%s (%d Attempt Remaining)", submitButtonText, attempts);
        }
        return String.format("%s (%d Attempts Remaining)", submitButtonText, attempts);
    }
}
