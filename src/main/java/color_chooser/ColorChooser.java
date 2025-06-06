package color_chooser;

import components.panels.SatValBox;
import components.sliders.GradientSliderUI;
import components.sliders.HueSlider;
import components.sliders.SaturationSlider;
import components.sliders.BrightnessSlider;

import javax.swing.*;
import java.awt.*;

public class ColorChooser {
    Color color;
    float hue;
    float saturation;
    float brightness;
    int boxDimension;
    HueSlider hueSlider;
    SaturationSlider saturationSlider;
    BrightnessSlider brightnessSlider;
    SatValBox satValBox;
    JPanel colorChooserPanel;
    JPanel chosenColorPanel;

    public ColorChooser() {
        boxDimension = 30;
        hueSlider = new HueSlider(this);
        saturationSlider = new SaturationSlider(this);
        brightnessSlider = new BrightnessSlider(this);
        satValBox = new SatValBox(this);
        chosenColorPanel = new JPanel(new BorderLayout());
        chosenColorPanel.setPreferredSize(new Dimension(boxDimension, boxDimension));
        chosenColorPanel.setPreferredSize(new Dimension(boxDimension, boxDimension));
        chosenColorPanel.setMinimumSize(new Dimension(boxDimension, boxDimension));
        chosenColorPanel.setMaximumSize(new Dimension(boxDimension, boxDimension));

        hueSlider.setUI(new GradientSliderUI(hueSlider));
        saturationSlider.setUI(new GradientSliderUI(saturationSlider));
        brightnessSlider.setUI(new GradientSliderUI(brightnessSlider));
        hue = hueSlider.getValue() / 360f;
        saturation = saturationSlider.getValue() / 100f;
        brightness = brightnessSlider.getValue() / 100f;

        updateColor();

        colorChooserPanel = buildPanel();
    }

    private JPanel buildPanel() {
        JPanel colorChooserPanel = new JPanel();
        colorChooserPanel.add(satValBox, BorderLayout.WEST);

        JPanel sliderPanel = new JPanel();
        sliderPanel.setLayout(new BoxLayout(sliderPanel, BoxLayout.Y_AXIS));

        JPanel chosenColorWrapper = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        chosenColorWrapper.add(chosenColorPanel);
        sliderPanel.add(chosenColorWrapper);

        sliderPanel.add(new JLabel("Hue") {{
            setFont(getFont().deriveFont(Font.BOLD));
            setForeground(Color.BLACK);
        }});
        sliderPanel.add(hueSlider);
        sliderPanel.add(new JLabel("Saturation") {{
            setFont(getFont().deriveFont(Font.BOLD));
            setForeground(Color.BLACK);
        }});
        sliderPanel.add(saturationSlider);
        sliderPanel.add(new JLabel("Brightness") {{
            setFont(getFont().deriveFont(Font.BOLD));
            setForeground(Color.BLACK);
        }});
        sliderPanel.add(brightnessSlider);

        colorChooserPanel.add(sliderPanel, BorderLayout.EAST);

        return colorChooserPanel;
    }

    public JPanel getPanel() {
        return colorChooserPanel;
    }

    private void updateColor() {
        color = Color.getHSBColor(hue, saturation, brightness);
        chosenColorPanel.setBackground(Color.getHSBColor(hue, saturation, brightness));
        updateSliders();
    }

    public void setSaturation(float saturationValue) {
        saturation = saturationValue;
        satValBox.updateSaturation(saturationValue);
        saturationSlider.updateSaturation(saturationValue);
        updateColor();
    }

    public void setBrightness(float brightnessValue) {
        brightness = brightnessValue;
        satValBox.updateBrightness(brightnessValue);
        brightnessSlider.updateBrightness(brightnessValue);
        updateColor();
    }

    public void setHue(int hueValue) {
        hue = hueValue / 360f;
        updateColor();
    }

    public void setSaturation(int saturationValue) {
        saturation = saturationValue / 100f;
        updateColor();
    }

    public void setBrightness(int brightnessValue) {
        brightness = brightnessValue / 100f;
        updateColor();
    }

    public float[] getColorHSB() {
        return new float[] {hue, saturation, brightness};
    }

    public void updateSliders() {
        hueSlider.repaint();
        saturationSlider.repaint();
        brightnessSlider.repaint();
        satValBox.repaint();
    }

}
