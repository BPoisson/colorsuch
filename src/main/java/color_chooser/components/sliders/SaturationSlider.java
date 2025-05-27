package color_chooser.components.sliders;

import color_chooser.ColorChooser;


public class SaturationSlider extends ColorJSlider {

    public SaturationSlider(ColorChooser colorChooser) {
        super(0, 100, colorChooser);

        this.setValue(0);
        this.addChangeListener(_ -> {
            colorChooser.setSaturation(this.getValue());
        });
    }

    public void updateSaturation(float saturation) {
        this.setValue((int) (saturation * 100f));
    }
}
