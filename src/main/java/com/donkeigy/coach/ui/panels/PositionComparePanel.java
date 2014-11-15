package com.donkeigy.coach.ui.panels;

import com.yahoo.objects.team.Team;
import com.yahoo.objects.team.TeamPoints;
import com.yahoo.objects.team.TeamStat;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by cedric on 11/14/14.
 */
public class PositionComparePanel {

    private JFreeChart chart;
    private JPanel mainPanel;
    private ChartPanel chartPanel1;
    //Map<String, BigDecimal> positionWeeklyAvgs;

    public PositionComparePanel()
    {

    }

    private void createUIComponents() {
        chartPanel1 = new ChartPanel(chart, false);
    }
    private CategoryDataset createTeamCompareDataSet(List<Team> teams, Map<String, Map<String, BigDecimal>> positionWeeklyLeagueAvgs)
    {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Team team : teams)
        {
            Map<String, BigDecimal> positionWeeklyAvgs = positionWeeklyLeagueAvgs.get(team.getTeam_key());
            for (String position : positionWeeklyAvgs.keySet())
            {
                BigDecimal positionAvg = positionWeeklyAvgs.get(position);
                dataset.addValue(positionAvg, team.getName(), position);          // Todo: Might add whole league One Day
            }
        }
        return dataset;
    }

    public void init (List<Team> teams, Map<String, Map<String, BigDecimal>> positionWeeklyLeagueAvgs)
    {
        chart = ChartFactory.createBarChart(
                "Player Avg Comparison", // chart title
                "Team", // domain axis label
                "Points", // range axis label
                createTeamCompareDataSet(teams, positionWeeklyLeagueAvgs), // data
                PlotOrientation.VERTICAL, // orientation
                true, // include legend
                true, // tooltips
                false // urls
        );
        if(chartPanel1 != null)
        {
            chartPanel1.setChart(chart);
        }
        CategoryPlot plot = chart.getCategoryPlot();
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setAutoRange(true);

    }
}
