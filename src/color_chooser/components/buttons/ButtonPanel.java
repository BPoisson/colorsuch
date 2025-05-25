package color_chooser.components.buttons;

import color_chooser.ColorChooser;
import color_chooser.components.ColorPanel;
import color_chooser.components.TextPanel;

import javax.swing.*;

public class ButtonPanel extends JPanel {
    JButton nextButton;
    JButton submitButton;
    ColorPanel colorPanel;
    ColorChooser colorChooser;
    TextPanel textPanel;

    public ButtonPanel(ColorPanel colorPanel, ColorChooser colorChooser, TextPanel textPanel) {
        nextButton = new JButton("Next Color");
        submitButton = new JButton("Submit");
        this.colorPanel = colorPanel;
        this.colorChooser = colorChooser;
        this.textPanel = textPanel;

        nextButton.addActionListener(_ -> {
            textPanel.disableText();
            colorPanel.disableRectangle();
            colorPanel.nextColor();
        });
        submitButton.addActionListener(_ -> {
            colorPanel.setRectangle(colorChooser.getColorHSB());
            textPanel.createText(colorPanel.getColorHSB(), colorChooser.getColorHSB());
        });
    }

    public JPanel getPanel() {
        JPanel jPanel = new JPanel();

        jPanel.add(nextButton);
        jPanel.add(submitButton);

        return jPanel;
    }
}
