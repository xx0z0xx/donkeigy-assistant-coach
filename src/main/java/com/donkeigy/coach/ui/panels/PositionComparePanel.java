package com.donkeigy.coach.ui.panels;

import com.donkeigy.coach.services.PlayerDataServices;
import com.donkeigy.coach.ui.models.PositionCompareTableModel;
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
import java.util.HashMap;
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
    private JTabbedPane tabbedPane1;
    private JTable table1;
    private JComboBox comboBox1;
    private JButton button1;
    private PositionCompareTableModel positionCompareTableModel;
    private PlayerDataServices playerDataServices;
    private List<Team> leagueTeams;
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
                dataset.addValue(positionAvg, team.getName(), position);
            }
        }
        return dataset;
    }

    public void init (List<Team> teams, PlayerDataServices playerDataServices)
    {
        this.playerDataServices = playerDataServices;
        this.leagueTeams = teams;
        Map<String, Map<String, BigDecimal>> positionWeeklyLeagueAvgs = getPositionWeeklyAvgs();
        initCharts(teams, positionWeeklyLeagueAvgs);
        initTable(teams, positionWeeklyLeagueAvgs);
        initComboBox();




    }

    private Map<String, Map<String, BigDecimal>> getPositionWeeklyAvgs()
    {
        Map<String, Map<String, BigDecimal>> positionWeeklyLeagueAvgs = new HashMap<String, Map<String, BigDecimal>>();

        for(Team team : leagueTeams)
        {
            positionWeeklyLeagueAvgs.put(team.getTeam_key(), playerDataServices.getPositionWeeklyAvg(team.getTeam_key(), 1)) ;

        }
        return positionWeeklyLeagueAvgs;
    }

    private void initCharts(List<Team> teams,  Map<String, Map<String, BigDecimal>> positionWeeklyLeagueAvgs)
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

    private void initTable(List<Team> teams,  Map<String, Map<String, BigDecimal>> positionWeeklyLeagueAvgs)
    {
        positionCompareTableModel = new PositionCompareTableModel(teams, positionWeeklyLeagueAvgs);
        table1.setModel(positionCompareTableModel);
    }
    private void initComboBox()
    {
        comboBox1.removeAllItems();
        for (int i = 1 ; i <= 17; i++)
        {
            comboBox1.addItem("Week " + i);
        }
    }

}
