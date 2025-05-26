package managers;

public class GameRound {
    int score;
    float[] chosenColorHSB;

    public GameRound(int score, float[] chosenColorHSB) {
        this.score = score;
        this.chosenColorHSB = chosenColorHSB;
    }

    public int getScore() {
        return score;
    }

    public float[] getChosenColorHSB() {
        return chosenColorHSB;
    }
}
