package components.panels;

import color_chooser.ColorChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class SatValBox extends JPanel {
    private float saturation;
    private float brightness;
    private final ColorChooser colorChooser;

    public SatValBox(ColorChooser colorChooser) {
        this.colorChooser = colorChooser;
        float[] hsb = colorChooser.getColorHSB();
        saturation = hsb[1];
        brightness = hsb[2];

        setPreferredSize(new Dimension(200, 200));

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                updateColor(e.getX(), e.getY());
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                updateColor(e.getX(), e.getY());
            }
        });
    }

    private void updateColor(int x, int y) {
        int w = getWidth();
        int h = getHeight();

        saturation = Math.min(1f, Math.max(0f, x / (float) w));
        brightness = Math.min(1f, Math.max(0f, 1f - y / (float) h)); // Invert Y.

        colorChooser.setSaturation(saturation);
        colorChooser.setBrightness(brightness);
        repaint();
    }

    public void updateSaturation(float saturation) {
        this.saturation = saturation;
    }

    public void updateBrightness(float brightness) {
        this.brightness = brightness;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int w = getWidth();
        int h = getHeight();
        float[] hsb = colorChooser.getColorHSB();

        for (int y = 0; y < h; y++) {
            float brightness = 1f - (float) y / h;

            for (int x = 0; x < w; x++) {
                float saturation = (float) x / w;
                g.setColor(Color.getHSBColor(hsb[0], saturation, brightness));
                g.drawLine(x, y, x, y);
            }
        }

        // Draw selector circle.
        int selX = (int) (hsb[1] * w);
        int selY = (int) ((1f - hsb[2]) * h);
        g.setColor(Color.WHITE);
        g.drawOval(selX - 5, selY - 5, 10, 10);
        g.setColor(Color.BLACK);
        g.drawOval(selX - 4, selY - 4, 8, 8);
    }
}
