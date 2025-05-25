package color_chooser.components;

import javax.swing.*;
import java.awt.*;

public class ColorPanel extends JPanel {
    ColorMaker colorMaker;
    Color color;
    float[] rectangleHSB;
    boolean showRectangle;

    public ColorPanel() {
        colorMaker = new ColorMaker();
        color = nextColor();

        setPreferredSize(new Dimension(400, 400));
    }

    public Color nextColor() {
        color = colorMaker.nextColor();
        this.setBackground(color);
        showRectangle = false;

        repaint();
        return color;
    }

    public void setRectangle(float[] hsbColor) {
        rectangleHSB = hsbColor;
        showRectangle = true;
        repaint();
    }

    public void disableRectangle() {
        showRectangle = false;
        repaint();
    }

    public float[] getColorHSB() {
        return new float[] {colorMaker.hue, colorMaker.saturation, colorMaker.brightness};
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (showRectangle) {
            g.setColor(Color.getHSBColor(rectangleHSB[0], rectangleHSB[1], rectangleHSB[2]));
            g.fillRect(this.getWidth() / 2 - 100, this.getHeight() / 2 - 100, 200, 200);
        }
    }
}
