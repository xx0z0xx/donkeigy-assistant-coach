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
import org.jfree.data.general.Dataset;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by cedric on 10/29/14.
 */
public class TeamComparePanel extends JPanel
{

    private JFreeChart chart;
    private ChartPanel chartPanel1;
    private JPanel mainPanel;
    private List<Team> teams;
    private Map<String, List<TeamStat>> teamStatMap;

    public TeamComparePanel(List<Team> teams, Map<String, List<TeamStat>> teamStatMap)
    {
        super();
        this.teams = teams;
        this.teamStatMap = teamStatMap;
        init(teams, teamStatMap);
    }

    private void createUIComponents()
    {

        chartPanel1 = new ChartPanel(chart, false);

    }

    private CategoryDataset createTeamCompareDataSet(List<Team> teams, Map<String, List<TeamStat>> teamStatMap)
    {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(Team team : teams)
        {
            List<TeamStat> teamStats = teamStatMap.get(team.getTeam_key());
            for(TeamStat teamStat : teamStats)
            {
                TeamPoints teamPoints= teamStat.getTeam_points();
                BigDecimal pointsValue = new BigDecimal(teamPoints.getTotal());
                dataset.addValue(pointsValue,team.getName() , teamPoints.getWeek());
            }
        }
        return dataset;
    }

    public void init (List<Team> teams, Map<String, List<TeamStat>> teamStatMap)
    {
        chart = ChartFactory.createLineChart(
                "League Comparison", // chart title
                "Week", // domain axis label
                "Points", // range axis label
                createTeamCompareDataSet(teams,teamStatMap), // data
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
