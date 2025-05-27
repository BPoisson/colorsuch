package components.panels;

import java.awt.*;
import java.util.Random;

public class ColorMaker {
    float hue;
    float saturation;
    float brightness;
    Random rand;

    public ColorMaker() {
        rand = new Random();
        nextColor();
    }

    public Color nextColor() {
        hue = rand.nextInt(0, 361) / 360f;
        saturation = rand.nextInt(10, 101) / 100f;
        brightness = rand.nextInt(15, 101) / 100f;

        return Color.getHSBColor(hue, saturation, brightness);
    }
}
