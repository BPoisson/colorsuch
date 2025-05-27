package managers;

import data_structures.Pair;

import javax.swing.*;

public class GameManager {
    int skipsUsed;
    int maxRounds;
    int roundIndex;
    int totalScore;
    Round[] rounds;
    PanelManager panelManager;
    AttemptManager attemptManager;
    ScoreManager scoreManager;

    public GameManager() {
        skipsUsed = 0;
        maxRounds = 5;
        roundIndex = 0;
        totalScore = 0;
        rounds = new Round[maxRounds];
        panelManager = new PanelManager(this);
        attemptManager = new AttemptManager(panelManager);
        scoreManager = new ScoreManager();
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
            panelManager.getButtonPanel().setSkip(false);
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
        rounds[roundIndex] = new Round(score, choiceColorHSB);
        roundIndex++;

        return roundIndex == maxRounds;
    }

    private void handleGameOver() {
        JOptionPane.showMessageDialog(null, String.format("Game Over.\nAverage Score: %.2f.", (float) totalScore / maxRounds));
        scoreManager.saveRound(rounds);

        resetGame();
    }

    private void resetGame() {
        roundIndex = 0;
        totalScore = 0;
        rounds = new Round[maxRounds];
        attemptManager.resetAttempts();

        panelManager.resetAll();
    }

    public void skipUsed() {
        skipsUsed++;
    }
}
