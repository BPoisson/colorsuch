package components.charts;

import managers.JSONManager;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;

public class ScoreLineChart extends JFrame {
    final String AVERAGE_SCORE_LABEL = "Average Score";

    public ScoreLineChart() {
        setTitle("Score History");

        JFreeChart lineChart = createChart(createDataset());
        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        chartPanel.setMouseWheelEnabled(true);
        setContentPane(chartPanel);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    private DefaultCategoryDataset createDataset() {
        JSONObject gameHistoryJSON = JSONManager.readJSONFile();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        if (gameHistoryJSON == null) {
            return dataset;
        }

        JSONArray gameJSONArray = JSONManager.getPreviousGames();
        for (int i = 0; i < gameJSONArray.length(); i++) {
            JSONObject gameJSONObject = gameJSONArray.getJSONObject(i);
            int gameNumber = JSONManager.getGame(gameJSONObject);
            float averageScore = JSONManager.getAverageScore(gameJSONObject);

            dataset.addValue(averageScore, AVERAGE_SCORE_LABEL, "" + gameNumber);
        }
        return dataset;
    }

    private JFreeChart createChart(DefaultCategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createLineChart(
            "Score History",        // Title.
                "Game",                  // X-Axis.
                AVERAGE_SCORE_LABEL,     // Y-Axis.
                dataset
        );

        CategoryPlot plot = chart.getCategoryPlot();
        NumberAxis yAxis = new NumberAxis(AVERAGE_SCORE_LABEL);
        plot.setRangeAxis(yAxis);

        LineAndShapeRenderer renderer = new LineAndShapeRenderer();
        renderer.setSeriesShapesVisible(0, true);
        renderer.setSeriesLinesVisible(0, true);
        plot.setRenderer(renderer);

        return chart;
    }
}
