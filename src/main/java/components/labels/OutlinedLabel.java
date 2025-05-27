package components.labels;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;

public class OutlinedLabel extends JLabel {
    public OutlinedLabel(String text) {
        super(text);
        setFont(new Font("Arial", Font.BOLD, 16));
        setForeground(Color.WHITE);
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        String text = getText();

        FontMetrics fm = g2.getFontMetrics(getFont());
        int x = 0;
        int y = fm.getAscent();

        g2.setFont(getFont());
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(2));
        g2.draw(new TextLayout(text, getFont(), g2.getFontRenderContext()).getOutline(AffineTransform.getTranslateInstance(x, y)));

        g2.setColor(getForeground());
        g2.drawString(text, x, y);

        g2.dispose();
    }
}
