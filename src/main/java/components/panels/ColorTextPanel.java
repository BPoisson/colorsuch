package components.panels;

import constants.Constants;

import javax.swing.*;
import java.awt.*;

public class ColorTextPanel extends JPanel {
    JTextArea textArea;

    public ColorTextPanel() {
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.BOLD, 18));
        textArea.setBackground(this.getBackground());
        add(textArea);
    }

    public void createText(float[] panelColorHSB, float[] chosenColorHSB) {
        int[] panelColorInt = new int[] {(int) (panelColorHSB[0] * 360), (int) (panelColorHSB[1] * 100), (int) (panelColorHSB[2] * 100)};
        int[] chosenColorInt = new int[] {(int) (chosenColorHSB[0] * 360), (int) (chosenColorHSB[1] * 100), (int) (chosenColorHSB[2] * 100)};

        this.add(textArea);
        textArea.setText(getText(panelColorInt, chosenColorInt));
        repaint();
    }

    public String getText(int[] panelColorInt, int[] chosenColorInt) {
        return getHueText(panelColorInt[0], chosenColorInt[0])
                + getSaturationText(panelColorInt[1], chosenColorInt[1])
                + getBrightnessText(panelColorInt[2], chosenColorInt[2]);
    }

    public String getHueText(int panelHue, int chosenHue) {
        int hueDiff = panelHue - chosenHue;
        int hueDiffAbs = Math.abs(hueDiff);
        String nextColorName = getNextColorName(panelHue, chosenHue);

        if (hueDiffAbs <= Constants.PERFECT_HUE_DIFF) {
            return "Hue Perfect!\n";
        } else if (hueDiffAbs <= 15) {
            return "A bit more " + nextColorName + "\n";
        } else if (hueDiffAbs <= 60) {
            return "A little more " + nextColorName + "\n";
        } else if (hueDiffAbs <= 120) {
            return "More " + nextColorName + "\n";
        } else {
            return "Much more " + nextColorName + "\n";
        }
    }

    public String getSaturationText(int panelSaturation, int chosenSaturation) {
        int saturationDiff = panelSaturation - chosenSaturation;
        int saturationDiffAbs = Math.abs(saturationDiff);

        if (saturationDiffAbs <= Constants.PERFECT_SATURATION_DIFF) {
            return "Saturation Perfect!\n";
        } else if (saturationDiffAbs <= 3) {
            if (saturationDiff < 0) {
                return "A bit too saturated.\n";
            }
            return "A bit too unsaturated.\n";
        } else if (saturationDiffAbs <= 10) {
            if (saturationDiff < 0) {
                return "A little too saturated.\n";
            }
            return "A little too unsaturated.\n";
        } else if (saturationDiffAbs <= 50) {
            if (saturationDiff < 0) {
                return "Too saturated.\n";
            }
            return "Too unsaturated.\n";
        } else {
            if (saturationDiff < 0) {
                return "Much too saturated.\n";
            }
            return "Much too unsaturated.\n";
        }
    }

    public String getBrightnessText(int panelBrightness, int chosenBrightness) {
        int brightnessDiff = panelBrightness - chosenBrightness;
        int brightnessDiffAbs = Math.abs(brightnessDiff);

        if (brightnessDiffAbs <= Constants.PERFECT_BRIGHTNESS_DIFF) {
            return "Brightness Perfect!\n";
        } else if (brightnessDiffAbs <= 3) {
            if (brightnessDiff < 0) {
                return "A bit too bright.\n";
            }
            return "A bit too dark.\n";
        } else if (brightnessDiffAbs <= 10) {
            if (brightnessDiff < 0) {
                return "A little too bright.\n";
            }
            return "A little too dark.\n";
        } else if (brightnessDiffAbs <= 50) {
            if (brightnessDiff < 0) {
                return "Too bright.\n";
            }
            return "Too dark.\n";
        } else {
            if (brightnessDiff < 0) {
                return "Much too bright.\n";
            }
            return "Much too dark.\n";
        }
    }

    public String getNextColorName(int panelHue, int chosenHue) {
        int hueOffset = getHueOffset(panelHue, chosenHue);
        int nextColorHue = panelHue < chosenHue ? Math.max(0, chosenHue - hueOffset) : Math.min(360, chosenHue + hueOffset);

        if (nextColorHue < 15) {
            return "Low Red";
        } else if (nextColorHue < 45) {
            return "Orange";
        } else if (nextColorHue < 75) {
            return "Yellow";
        } else if (nextColorHue < 105) {
            return "Lime";
        } else if (nextColorHue < 135) {
            return "Green";
        } else if (nextColorHue < 165) {
            return "Turquoise";
        } else if (nextColorHue < 195) {
            return "Cyan";
        } else if (nextColorHue < 225) {
            return "Blue";
        } else if (nextColorHue < 255) {
            return "Indigo";
        } else if (nextColorHue < 285) {
            return "Violet";
        } else if (nextColorHue < 315) {
            return "Magenta";
        } else if (nextColorHue < 345){
            return "Pink";
        } else {
            return "High Red";
        }
    }

    private int getHueOffset(int panelHue, int chosenHue) {
        int hueDiff = panelHue - chosenHue;
        int hueDiffAbs = Math.abs(hueDiff);

        if (hueDiffAbs <= 30) {
            return 15;
        } else if (hueDiffAbs <= 60) {
            return 30;
        } else if (hueDiffAbs <= 120){
            return 60;
        } else if (hueDiffAbs <= 240){
            return 120;
        } else {
            return 240;
        }
    }

    public void disableText() {
        this.remove(textArea);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
