package managers;

import color_chooser.components.panels.PanelManager;
import data_structures.Pair;

public class GameManager {
    int roundIndex;
    int totalScore;
    GameRound[] gameRounds;
    PanelManager panelManager;
    AttemptManager attemptManager;

    public GameManager() {
        roundIndex = 0;
        totalScore = 0;
        gameRounds = new GameRound[10];
        panelManager = new PanelManager(this);
        attemptManager = new AttemptManager(panelManager);
    }

    public void play() {
        panelManager.start();
    }

    public void submitAttempt(float[] panelColorHSB, float[] choiceColorHSB) {
        Pair<Boolean, Integer> attemptResult = attemptManager.submitAttempt(panelColorHSB, choiceColorHSB);

        if (attemptResult.getKey()) {
            panelManager.getButtonPanel().disableSubmitButton();
            panelManager.getScorePanel().addAttempt(attemptResult.getValue(), panelColorHSB);
        }
    }

    public void resetAttempts() {
        attemptManager.resetAttempts();
        panelManager.getButtonPanel().enableSubmitButton();
    }

    public void addRound(int score, float[] chosenColorHSB) {
        gameRounds[roundIndex] = new GameRound(score, chosenColorHSB);
        panelManager.getScorePanel().addAttempt(score, chosenColorHSB);
        roundIndex++;
    }
}
