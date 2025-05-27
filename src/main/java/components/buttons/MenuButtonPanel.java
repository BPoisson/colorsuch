package components.buttons;

import components.charts.ScoreLineChart;

import javax.swing.*;

public class MenuButtonPanel extends JPanel {
    JButton chartButton;

    public MenuButtonPanel() {
        chartButton = new JButton("Score Chart");
        chartButton.addActionListener(_ -> {
            ScoreLineChart chartWindow = new ScoreLineChart();
            chartWindow.setVisible(true);
        });

        add(chartButton);
    }
}
