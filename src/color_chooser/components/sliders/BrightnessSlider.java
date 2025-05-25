package color_chooser.components.sliders;

import color_chooser.ColorChooser;

public class BrightnessSlider extends ColorJSlider {

    public BrightnessSlider(ColorChooser colorChooser) {
        super(0, 100, colorChooser);

        this.setValue(100);
        this.addChangeListener(_ -> {
            colorChooser.setBrightness(this.getValue());
        });
    }

    public void updateBrightness(float brightness) {
        this.setValue((int) (brightness * 100f));
    }
}
