package color_chooser.components.sliders;

import color_chooser.ColorChooser;

import javax.swing.*;

public class ColorJSlider extends JSlider {
    ColorChooser colorChooser;

    public ColorJSlider(int min, int max, ColorChooser colorChooser) {
        super(min, max);

        this.colorChooser = colorChooser;
    }
}
