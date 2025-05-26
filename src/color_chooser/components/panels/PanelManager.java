package color_chooser.components.panels;

import color_chooser.ColorChooser;
import color_chooser.components.buttons.ButtonPanel;

import javax.swing.*;
import java.awt.*;

public class PanelManager {
    ColorPanel colorPanel;
    ColorChooser colorChooser;
    TextPanel textPanel;
    AttemptPanel attemptPanel;
    ScorePanel scorePanel;
    ButtonPanel buttonPanel;
    JPanel leftPanel;
    JPanel rightPanel;
    JPanel buttonHolder;
    JFrame jFrame;

    public PanelManager() {
        colorPanel = new ColorPanel();
        colorChooser = new ColorChooser();
        textPanel = new TextPanel();
        attemptPanel = new AttemptPanel();
        scorePanel = new ScorePanel();
        buttonPanel = new ButtonPanel(colorPanel, colorChooser, textPanel, attemptPanel, scorePanel);
        leftPanel = new JPanel(new BorderLayout());
        rightPanel = new JPanel(new BorderLayout());
        buttonHolder = new JPanel(new BorderLayout());
        jFrame = new JFrame("ColorSuch");
    }

    public void start() {
        SwingUtilities.invokeLater(() -> {
            leftPanel.add(colorPanel, BorderLayout.NORTH);
            leftPanel.add(scorePanel.build(), BorderLayout.SOUTH);

            rightPanel.add(colorChooser.getPanel(), BorderLayout.NORTH);
            rightPanel.add(attemptPanel.build(), BorderLayout.CENTER);
            rightPanel.add(textPanel, BorderLayout.SOUTH);

            buttonHolder.add(buttonPanel.getPanel(), BorderLayout.CENTER);

            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jFrame.setLayout(new BorderLayout());

            jFrame.add(leftPanel, BorderLayout.WEST);
            jFrame.add(rightPanel, BorderLayout.EAST);
            jFrame.add(buttonHolder, BorderLayout.SOUTH);

//            jFrame.setSize(1000, 600);
            jFrame.pack();
            jFrame.setLocationRelativeTo(null);
            jFrame.setVisible(true);
        });
    }
}
