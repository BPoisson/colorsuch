public class GameRound {
    int score;
    float[] chosenColorHSB;

    public GameRound() {
        score = 0;
        chosenColorHSB = new float[3];
    }

    public GameRound(int score, float[] chosenColorHSB) {
        this.score = score;
        this.chosenColorHSB = chosenColorHSB;
    }

    public void play() {

    }

    public int getScore() {
        return score;
    }

    public float[] getChosenColorHSB() {
        return chosenColorHSB;
    }
}
