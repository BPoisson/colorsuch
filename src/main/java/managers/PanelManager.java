package managers;

import color_chooser.ColorChooser;
import components.buttons.GameButtonPanel;
import components.buttons.MenuButtonPanel;
import components.panels.AttemptPanel;
import components.panels.ColorPanel;
import components.panels.ScorePanel;
import components.panels.TextPanel;

import javax.swing.*;
import java.awt.*;

public class PanelManager {
    GameManager gameManager;
    ColorPanel colorPanel;
    ColorChooser colorChooser;
    TextPanel textPanel;
    AttemptPanel attemptPanel;
    ScorePanel scorePanel;
    MenuButtonPanel menuButtonPanel;
    GameButtonPanel gameButtonPanel;
    JPanel topPanel;
    JPanel leftPanel;
    JPanel rightPanel;
    JPanel buttonHolder;
    JFrame jFrame;

    public PanelManager(GameManager gameManager) {
        this.gameManager = gameManager;
        colorPanel = new ColorPanel();
        colorChooser = new ColorChooser();
        textPanel = new TextPanel();
        attemptPanel = new AttemptPanel();
        scorePanel = new ScorePanel();
        menuButtonPanel = new MenuButtonPanel();
        gameButtonPanel = new GameButtonPanel(gameManager, colorPanel, colorChooser, textPanel);
        topPanel = new JPanel(new BorderLayout());
        leftPanel = new JPanel(new BorderLayout());
        rightPanel = new JPanel(new BorderLayout());
        buttonHolder = new JPanel(new BorderLayout());
        jFrame = new JFrame("ColorSuch");
    }

    public void start() {
        SwingUtilities.invokeLater(() -> {
            topPanel.add(menuButtonPanel, BorderLayout.WEST);

            leftPanel.add(colorPanel, BorderLayout.NORTH);
            leftPanel.add(scorePanel.build(), BorderLayout.SOUTH);

            rightPanel.add(colorChooser.getPanel(), BorderLayout.NORTH);
            rightPanel.add(attemptPanel.build(), BorderLayout.CENTER);
            rightPanel.add(textPanel, BorderLayout.SOUTH);

            buttonHolder.add(gameButtonPanel.getPanel(), BorderLayout.CENTER);

            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jFrame.setLayout(new BorderLayout());

            jFrame.add(topPanel, BorderLayout.NORTH);
            jFrame.add(leftPanel, BorderLayout.WEST);
            jFrame.add(rightPanel, BorderLayout.EAST);
            jFrame.add(buttonHolder, BorderLayout.SOUTH);

            jFrame.pack();
            jFrame.setLocationRelativeTo(null);
            jFrame.setVisible(true);
        });
    }

    public AttemptPanel getAttemptPanel() {
        return attemptPanel;
    }

    public TextPanel getTextPanel() {
        return textPanel;
    }

    public GameButtonPanel getButtonPanel() {
        return gameButtonPanel;
    }

    public ScorePanel getScorePanel() {
        return scorePanel;
    }

    public void resetAll() {
        attemptPanel.reset();
        scorePanel.reset();
        colorPanel.nextColor();
        textPanel.disableText();
        gameButtonPanel.enableSubmitButton();
        gameButtonPanel.reset();
    }
}
