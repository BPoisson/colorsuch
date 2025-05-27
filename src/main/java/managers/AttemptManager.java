package managers;

import constants.Constants;
import data_structures.Pair;

public class AttemptManager {
    int attemptNumber;
    float[][] attempts;
    PanelManager panelManager;

    public AttemptManager(PanelManager panelManager) {
        attemptNumber = 0;
        attempts = new float[8][3];
        this.panelManager = panelManager;
    }

    public Pair<Boolean, Integer> submitAttempt(float[] panelColorHSB, float[] choiceColorHSB) {
        boolean isPerfect = checkAttempt(panelColorHSB, choiceColorHSB);

        if (attemptNumber == 8) {
            attemptNumber = 0;
            attempts = new float[8][3];
            resetAttempts();
        }
        attempts[attemptNumber] = choiceColorHSB;
        attemptNumber++;
        addAttempt(panelColorHSB, choiceColorHSB);

        if (attemptNumber == 8 || isPerfect) {
            return new Pair<>(true, attemptNumber);
        }
        return new Pair<>(false, 0);
    }

    private void addAttempt(float[] panelColorHSB, float[] choiceColorHSB) {
        panelManager.getAttemptPanel().addAttempt(choiceColorHSB);
        panelManager.getTextPanel().createText(panelColorHSB, choiceColorHSB);
    }

    public void resetAttempts() {
        attemptNumber = 0;
        attempts = new float[8][3];
        panelManager.getAttemptPanel().reset();
    }

    private boolean checkAttempt(float[] panelColorHSB, float[] choiceColorHSB) {
        int[] panelColorInt = new int[] {(int) (panelColorHSB[0] * 360), (int) (panelColorHSB[1] * 100), (int) (panelColorHSB[2] * 100)};
        int[] choiceColorInt = new int[] {(int) (choiceColorHSB[0] * 360), (int) (choiceColorHSB[1] * 100), (int) (choiceColorHSB[2] * 100)};

        int hueDiffAbs = getHueDiffAbs(panelColorInt[0], choiceColorInt[0]);
        int saturationDiffAbs = getSaturationDiffAbs(panelColorInt[1], choiceColorInt[1]);
        int brightnessDiffAbs = getBrightnessDiffAbs(panelColorInt[2], choiceColorInt[2]);

        return hueDiffAbs <= Constants.PERFECT_HUE_DIFF
                && saturationDiffAbs <= Constants.PERFECT_SATURATION_DIFF
                && brightnessDiffAbs <= Constants.PERFECT_BRIGHTNESS_DIFF;
    }

    public int getHueDiffAbs(int panelHue, int choiceHue) {
        int hueDiff = panelHue - choiceHue;
        return Math.abs(hueDiff);
    }

    public int getSaturationDiffAbs(int panelSaturation, int choiceSaturation) {
        int saturationDiff = panelSaturation - choiceSaturation;
        return Math.abs(saturationDiff);
    }

    public int getBrightnessDiffAbs(int panelBrightness, int choiceBrightness) {
        int brightnessDiff = panelBrightness - choiceBrightness;
        return Math.abs(brightnessDiff);
    }
}
