package managers;

public class Round {
    int score;
    float[] chosenColorHSB;

    public Round(int score, float[] chosenColorHSB) {
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
