import color_chooser.ColorChooser;
import color_chooser.components.ColorPanel;
import color_chooser.components.TextPanel;
import color_chooser.components.buttons.ButtonPanel;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        ColorPanel colorPanel = new ColorPanel();
        ColorChooser colorChooser = new ColorChooser();
        TextPanel textPanel = new TextPanel();
        ButtonPanel buttonPanel = new ButtonPanel(colorPanel, colorChooser, textPanel);

        JPanel colorPanelHolder = new JPanel();
        colorPanelHolder.add(colorPanel);

        JPanel colorChooserHolder = new JPanel();
        colorChooserHolder.setLayout(new BoxLayout(colorChooserHolder, BoxLayout.Y_AXIS));
        colorChooserHolder.add(colorChooser.getPanel());
        colorChooserHolder.add(textPanel, BorderLayout.WEST);

        JPanel buttonHolder = new JPanel();
        buttonHolder.add(buttonPanel.getPanel());

        JFrame jFrame = new JFrame("Color Matching");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(colorPanelHolder, BorderLayout.WEST);
        jFrame.add(colorChooserHolder, BorderLayout.EAST);
        jFrame.add(buttonHolder, BorderLayout.SOUTH);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }
}
