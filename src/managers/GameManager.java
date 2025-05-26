package managers;

import color_chooser.components.panels.PanelManager;
import data_structures.Pair;

import javax.swing.*;

public class GameManager {
    int maxRounds;
    int roundIndex;
    int totalScore;
    GameRound[] gameRounds;
    PanelManager panelManager;
    AttemptManager attemptManager;

    public GameManager() {
        maxRounds = 10;
        roundIndex = 0;
        totalScore = 0;
        gameRounds = new GameRound[maxRounds];
        panelManager = new PanelManager(this);
        attemptManager = new AttemptManager(panelManager);
    }

    public void play() {
        panelManager.start();
    }

    public void submitAttempt(float[] panelColorHSB, float[] choiceColorHSB) {
        boolean gameOver = false;
        Pair<Boolean, Integer> attemptResult = attemptManager.submitAttempt(panelColorHSB, choiceColorHSB);

        if (attemptResult.getKey()) {
            totalScore += attemptResult.getValue();
            panelManager.getButtonPanel().disableSubmitButton();
            panelManager.getScorePanel().addAttempt(attemptResult.getValue(), panelColorHSB);
            gameOver = addRound(attemptResult.getValue(), choiceColorHSB);
            panelManager.getButtonPanel().enableNextColorButton();
        }

        if (gameOver) {
            handleGameOver();
        }
    }

    public void resetAttempts() {
        attemptManager.resetAttempts();
        panelManager.getButtonPanel().enableSubmitButton();
    }

    private boolean addRound(int score, float[] choiceColorHSB) {
        gameRounds[roundIndex] = new GameRound(score, choiceColorHSB);
        roundIndex++;

        return roundIndex == maxRounds;
    }

    private void handleGameOver() {
        JOptionPane.showMessageDialog(null, "Game Over.\nAverage Score: " + (totalScore / maxRounds));

        resetGame();
    }

    private void resetGame() {
        roundIndex = 0;
        totalScore = 0;
        gameRounds = new GameRound[maxRounds];
        attemptManager.resetAttempts();

        panelManager.resetAll();
    }
}
