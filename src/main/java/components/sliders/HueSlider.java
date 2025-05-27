package components.sliders;

import color_chooser.ColorChooser;

public class HueSlider extends ColorJSlider {

    public HueSlider(ColorChooser colorChooser) {
        super(0, 360, colorChooser);

        this.setValue(0);
        this.addChangeListener(_ -> {
            colorChooser.setHue(this.getValue());
        });
    }
}
