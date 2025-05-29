package components.buttons;

import color_chooser.ColorChooser;
import components.panels.ColorPanel;
import components.panels.ColorTextPanel;
import managers.GameManager;

import javax.swing.*;

public class GameButtonPanel extends JPanel {
    int skips;
    boolean skip;
    int maxSkips;
    GameManager gameManager;
    JButton nextColorButton;
    JButton submitButton;
    ColorPanel colorPanel;
    ColorChooser colorChooser;
    ColorTextPanel colorTextPanel;
    String nextColorButtonText;

    public GameButtonPanel(GameManager gameManager, ColorPanel colorPanel, ColorChooser colorChooser, ColorTextPanel colorTextPanel, final int MAX_SKIPS) {
        this.maxSkips = MAX_SKIPS;
        skips = MAX_SKIPS;
        skip = true;
        nextColorButtonText = "Next Color";
        nextColorButton = new JButton(getNextColorButtonText());
        submitButton = new JButton("Submit");
        this.colorPanel = colorPanel;
        this.colorChooser = colorChooser;
        this.colorTextPanel = colorTextPanel;
        this.gameManager = gameManager;

        nextColorButton.addActionListener(_ -> {
            handleNextColorButtonClick();
        });
        submitButton.addActionListener(_ -> {
            colorPanel.setRectangle(colorChooser.getColorHSB());
            colorTextPanel.createText(colorPanel.getColorHSB(), colorChooser.getColorHSB());
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
        skips = maxSkips;
        nextColorButton.setText(getNextColorButtonText());

        enableSubmitButton();
        enableNextColorButton();
    }

    private String getNextColorButtonText() {
        if (skips == 1) {
            return String.format("%s (%d Skip)", nextColorButtonText, skips);
        }
        return String.format("%s (%d Skips)", nextColorButtonText, skips);
    }
}
