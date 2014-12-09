package com.donkeigy.coach.ui.panels;

import com.donkeigy.coach.services.PlayerDataServices;
import com.donkeigy.coach.ui.models.PositionCompareTableModel;
import com.donkeigy.coach.ui.models.PositionRankingsTableModel;
import com.yahoo.objects.league.LeagueRosterPositionList;
import com.yahoo.objects.team.Team;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.HashMap;
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
    private JTable positionPointsTable;
    private JComboBox comboBox1;
    private JButton loadWeekButton;
    private JTable positionRankingTable;
    private PositionCompareTableModel positionCompareTableModel;
    PositionRankingsTableModel positionRankingTableModel;
    private PlayerDataServices playerDataServices;
    private List<Team> leagueTeams;
    private LeagueRosterPositionList leagueRosterPositionList;
    private Team userTeam;
    private int currentWeek;
    private int selectedWeek;
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

    public void init (LeagueRosterPositionList leagueRosterPositionList, List<Team> teams, PlayerDataServices playerDataServices, int currentWeek)
    {
        this.playerDataServices = playerDataServices;
        this.leagueTeams = teams;
        this.currentWeek = currentWeek;
        this.selectedWeek = currentWeek-1;
        this.leagueRosterPositionList = leagueRosterPositionList;
        initComboBox();
        initUserTeam();
        Map<String, Map<String, BigDecimal>> positionWeeklyLeagueAvgs = getPositionWeeklyAvgs();
        initCharts(positionWeeklyLeagueAvgs);
        initTables(positionWeeklyLeagueAvgs);


        addActionListeners();



    }

    private void initUserTeam()
    {
        for (Team team : leagueTeams)
        {
            if(team.getIs_owned_by_current_login() != null && team.getIs_owned_by_current_login().equals("1"))
            {
                this.userTeam = team;
                break;
            }
        }
    }

    private Map<String, Map<String, BigDecimal>> getPositionWeeklyAvgs()
    {
        Map<String, Map<String, BigDecimal>> positionWeeklyLeagueAvgs = new HashMap<String, Map<String, BigDecimal>>();

        for(Team team : leagueTeams)
        {
            positionWeeklyLeagueAvgs.put(team.getTeam_key(), playerDataServices.getPositionWeeklyAvg(team.getTeam_key(), selectedWeek)) ;

        }
        return positionWeeklyLeagueAvgs;
    }



    private void initCharts(Map<String, Map<String, BigDecimal>> positionWeeklyLeagueAvgs)
    {
        chart = ChartFactory.createBarChart(
                "Player Avg Comparison", // chart title
                "Team", // domain axis label
                "Points", // range axis label
                createTeamCompareDataSet(leagueTeams, positionWeeklyLeagueAvgs), // data
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

    private void initTables(Map<String, Map<String, BigDecimal>> positionWeeklyLeagueAvgs )
    {
        positionCompareTableModel = new PositionCompareTableModel(leagueTeams, positionWeeklyLeagueAvgs, leagueRosterPositionList);
        positionPointsTable.setModel(positionCompareTableModel);
        Map<String, Integer> playerRanks = playerDataServices.getPlayerWeeklyPositionRanking(leagueTeams, userTeam, positionWeeklyLeagueAvgs);
        positionRankingTableModel = new PositionRankingsTableModel(playerRanks);
        positionRankingTable.setModel(positionRankingTableModel);
    }
    private void initComboBox()
    {
        comboBox1.removeAllItems();
        for (int i = currentWeek - 1 ; i > 0; i--)
        {
            comboBox1.addItem(new ComboBoxWeek(i));
        }

    }
    private void addActionListeners()
    {
        loadWeekButton.setActionCommand("LOAD_WEEK");
        loadWeekButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                if (cmd.equals("LOAD_WEEK")) //action for load button;
                {
                    ComboBoxWeek comboBoxWeek = (ComboBoxWeek)comboBox1.getSelectedItem();
                    selectedWeek = comboBoxWeek.getWeek();
                    Map<String, Map<String, BigDecimal>> positionWeeklyLeagueAvgs = getPositionWeeklyAvgs();
                    initCharts(positionWeeklyLeagueAvgs);
                    initTables(positionWeeklyLeagueAvgs);
                }
            }
        });
    }

    private class ComboBoxWeek
    {
        int week;

        private ComboBoxWeek(int week)
        {
            this.week = week;
        }

        public int getWeek() {
            return week;
        }

        public void setWeek(int week) {
            this.week = week;
        }

        @Override
        public String toString() {
            return "Week "+ week;
        }
    }
}
