package managers;

import color_chooser.ColorChooser;
import components.buttons.GameButtonPanel;
import components.buttons.MenuButtonPanel;
import components.labels.RoundTextLabel;
import components.panels.*;

import javax.swing.*;
import java.awt.*;

public class PanelManager {
    GameManager gameManager;
    ColorPanel colorPanel;
    ColorChooser colorChooser;
    ColorTextPanel colorTextPanel;
    RoundTextLabel roundTextLabel;
    AttemptPanel attemptPanel;
    ScorePanel scorePanel;
    MenuButtonPanel menuButtonPanel;
    GameButtonPanel gameButtonPanel;
    JPanel topMenuPanel;
    JPanel topLabelPanel;
    JPanel topPanel;
    JPanel leftPanel;
    JPanel rightPanel;
    JPanel buttonHolder;
    JFrame jFrame;

    public PanelManager(GameManager gameManager, final int MAX_SKIPS, final int MAX_ROUNDS, final int MAX_ATTEMPTS) {
        this.gameManager = gameManager;
        colorPanel = new ColorPanel();
        colorChooser = new ColorChooser();
        colorTextPanel = new ColorTextPanel();
        roundTextLabel = new RoundTextLabel(MAX_ROUNDS);
        attemptPanel = new AttemptPanel();
        scorePanel = new ScorePanel();
        menuButtonPanel = new MenuButtonPanel();
        gameButtonPanel = new GameButtonPanel(gameManager, colorPanel, colorChooser, colorTextPanel, MAX_SKIPS, MAX_ATTEMPTS);
        topMenuPanel = new JPanel(new BorderLayout());
        topLabelPanel = new JPanel(new BorderLayout());
        topPanel = new JPanel(new BorderLayout());
        leftPanel = new JPanel(new BorderLayout());
        rightPanel = new JPanel(new BorderLayout());
        buttonHolder = new JPanel(new BorderLayout());
        jFrame = new JFrame("ColorSuch");
    }

    public void start() {
        SwingUtilities.invokeLater(() -> {
            topMenuPanel.add(menuButtonPanel, BorderLayout.WEST);
            topLabelPanel.add(roundTextLabel, BorderLayout.CENTER);
            topPanel.add(topMenuPanel, BorderLayout.NORTH);
            topPanel.add(topLabelPanel, BorderLayout.SOUTH);

            leftPanel.add(colorPanel, BorderLayout.NORTH);
            leftPanel.add(scorePanel.build(), BorderLayout.SOUTH);

            rightPanel.add(colorChooser.getPanel(), BorderLayout.NORTH);
            rightPanel.add(attemptPanel.build(), BorderLayout.CENTER);
            rightPanel.add(colorTextPanel, BorderLayout.SOUTH);

            buttonHolder.add(gameButtonPanel.getPanel(), BorderLayout.CENTER);

            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jFrame.setLayout(new BorderLayout());

            jFrame.add(topPanel, BorderLayout.NORTH);
            jFrame.add(leftPanel, BorderLayout.WEST);
            jFrame.add(rightPanel, BorderLayout.EAST);
            jFrame.add(buttonHolder, BorderLayout.SOUTH);

            jFrame.pack();
            jFrame.setLocationRelativeTo(null);
            jFrame.setResizable(false);
            jFrame.setVisible(true);
        });
    }

    public AttemptPanel getAttemptPanel() {
        return attemptPanel;
    }

    public ColorTextPanel getColorTextPanel() {
        return colorTextPanel;
    }

    public void incrementRound() {
        roundTextLabel.incrementRound();
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
        colorTextPanel.disableText();
        roundTextLabel.reset();
        gameButtonPanel.enableSubmitButton();
        gameButtonPanel.reset();
    }
}
