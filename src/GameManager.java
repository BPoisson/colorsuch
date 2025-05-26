import color_chooser.components.panels.PanelManager;

public class GameManager {
    int roundIndex;
    GameRound[] gameRounds;
    PanelManager panelManager;

    public GameManager() {
        roundIndex = 0;
        gameRounds = new GameRound[10];
        panelManager = new PanelManager();
    }

    public void play() {
        panelManager.start();
    }

    public void addRound(int score, float[] chosenColorHSB) {
        gameRounds[roundIndex] = new GameRound(score, chosenColorHSB);
    }
}
