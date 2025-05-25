package color_chooser.components.sliders;

import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;

public class GradientSliderUI extends BasicSliderUI {
    ColorJSlider colorJSlider;

    public GradientSliderUI(ColorJSlider colorJSlider) {
        super(colorJSlider);

        this.colorJSlider = colorJSlider;
    }

    @Override
    public void paintTrack(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        if (colorJSlider instanceof HueSlider) {
            for (int x = 0; x < trackRect.width; x++) {
                float hue = x / (float) trackRect.width;
                g2.setColor(Color.getHSBColor(hue, 1f, 1f));
                g2.drawLine(trackRect.x + x, trackRect.y, trackRect.x + x, trackRect.y + trackRect.height);
            }
        } else if (colorJSlider instanceof SaturationSlider) {
            float[] hsb = colorJSlider.colorChooser.getColorHSB();
            float hue = hsb[0];
            float brightness = hsb[2];

            for (int x = 0; x < trackRect.width; x++) {
                float sat = x / (float) trackRect.width;
                g2.setColor(Color.getHSBColor(hue, sat, brightness));
                g2.drawLine(trackRect.x + x, trackRect.y, trackRect.x + x, trackRect.y + trackRect.height);
            }
        } else {
            float[] hsb = colorJSlider.colorChooser.getColorHSB();
            float hue = hsb[0];
            float saturation = hsb[1];

            for (int x = 0; x < trackRect.width; x++) {
                float bright = x / (float) trackRect.width;
                g2.setColor(Color.getHSBColor(hue, saturation, bright));
                g2.drawLine(trackRect.x + x, trackRect.y, trackRect.x + x, trackRect.y + trackRect.height);
            }
        }
        g2.dispose();
    }

}
