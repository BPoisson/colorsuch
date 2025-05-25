import color_chooser.ColorChooser;
import color_chooser.components.ColorPanel;
import color_chooser.components.TextPanel;
import color_chooser.components.buttons.ButtonPanel;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ColorPanel colorPanel = new ColorPanel();
            ColorChooser colorChooser = new ColorChooser();
            TextPanel textPanel = new TextPanel();
            ButtonPanel buttonPanel = new ButtonPanel(colorPanel, colorChooser, textPanel);

            JPanel rightPanel = new JPanel(new BorderLayout());
            rightPanel.setPreferredSize(new Dimension(250, 400));
            rightPanel.add(colorChooser.getPanel(), BorderLayout.CENTER);
            rightPanel.add(textPanel, BorderLayout.SOUTH);

            JPanel buttonHolder = new JPanel(new BorderLayout());
            buttonHolder.add(buttonPanel.getPanel(), BorderLayout.CENTER);

            JFrame jFrame = new JFrame("ColorSuch");
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jFrame.setLayout(new BorderLayout());

            jFrame.add(colorPanel, BorderLayout.CENTER);
            jFrame.add(rightPanel, BorderLayout.EAST);
            jFrame.add(buttonHolder, BorderLayout.SOUTH);

            jFrame.setSize(800, 600);
            jFrame.setLocationRelativeTo(null);
            jFrame.setVisible(true);
        });
    }

}
