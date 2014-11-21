package com.donkeigy.coach.ui.panels;

import com.donkeigy.coach.services.PlayerDataServices;
import com.donkeigy.coach.ui.dialogs.PositionCompareDialog;
import com.donkeigy.coach.ui.dialogs.TeamCompareDialog;
import com.donkeigy.coach.ui.models.LeagueTeamsTableModel;
import com.yahoo.objects.league.League;
import com.yahoo.objects.team.Team;
import com.yahoo.objects.team.TeamStandings;
import com.yahoo.objects.team.TeamStat;
import com.yahoo.services.LeagueService;
import com.yahoo.services.TeamService;
import com.yahoo.services.YahooServiceFactory;
import com.yahoo.services.enums.ServiceType;
import com.yahoo.utils.yql.YQLQueryUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by cedric on 10/28/14.
 */
public class LeaguePanel extends JPanel {

    private JComboBox leagueComboBox;
    private JPanel mainPanel;
    private JTable table1;
    private JButton leagueTeamCompareButton;
    private JButton positionAvgCompareButton;
    private JButton button3;
    LeagueService leagueService;
    TeamService teamService;
    LeagueTeamsTableModel leagueTeamsTableModel;
    private League selectedLeague;
    Map<String, TeamStandings> teamStandingsMap = new HashMap<String, TeamStandings>();
    List<Team> leagueTeams = new LinkedList<Team>();

    public LeaguePanel()
    {

    }

    public void populateLeagueComboBox() {
        List<League> leagues = leagueService.getUserLeagues("nfl");
        leagueComboBox.removeAllItems();
        for (League league : leagues) {
            leagueComboBox.addItem(league);
        }
        populateLeagueTeamTable();
    }

    public void init(YahooServiceFactory factory)
    {
        leagueService = (LeagueService)factory.getService(ServiceType.LEAGUE);
        teamService = (TeamService)factory.getService(ServiceType.TEAM);
        leagueTeamsTableModel = new LeagueTeamsTableModel(leagueTeams, teamStandingsMap);
        table1.setModel(leagueTeamsTableModel);
        addActionListeners();
    }

    private void populateLeagueTeamTable()
    {
        selectedLeague = (League)leagueComboBox.getSelectedItem();
        List<Team> selectedLeagueTeams = teamService.getLeagueTeams(selectedLeague.getLeague_key());
        Map<String, TeamStandings> selectedStandingsMap = leagueService.getLeagueStandings(selectedLeague.getLeague_key());
        leagueTeams.clear();
        teamStandingsMap.clear();

        leagueTeams.addAll(selectedLeagueTeams);
        teamStandingsMap.putAll(selectedStandingsMap);
        leagueTeamsTableModel.fireTableDataChanged();

    }
    private void addActionListeners() {

        leagueTeamCompareButton.setActionCommand("COMP_LEAGUE");
        positionAvgCompareButton.setActionCommand("COMP_POS");
        leagueTeamCompareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                if (cmd.equals("COMP_LEAGUE")) //action for load button;
                {
                    showLeagueCompareDialog();

                }
            }
        });
        positionAvgCompareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                if (cmd.equals("COMP_POS")) //action for load button;
                {
                    showPositionCompareDialog();

                }
            }
        });
    }
    private void showLeagueCompareDialog()
    {
        Map<String, List<TeamStat>> teamStatMap = new HashMap<String, List<TeamStat>>();
        for(Team team : leagueTeams)
        {
            List<TeamStat> teamWeeklyStats = teamService.getWeeklyTeamPointsForSeason(team.getTeam_key());
            teamStatMap.put(team.getTeam_key(), teamWeeklyStats);
        }

        TeamCompareDialog dialog = new TeamCompareDialog(leagueTeams, teamStatMap, Integer.parseInt(selectedLeague.getCurrent_week()));
        dialog.pack();
        dialog.setVisible(true);
    }
    private void showPositionCompareDialog()
    {
        PlayerDataServices playerDataServices = new PlayerDataServices(teamService);
        int currentWeek = (new BigDecimal(selectedLeague.getCurrent_week())).intValue();

       // playerDataServices.getPositionWeeklyAvg(userTeam.getTeam_key(), 1);
        PositionCompareDialog dialog = new PositionCompareDialog(leagueTeams, playerDataServices,currentWeek);
        dialog.pack();
        dialog.setVisible(true);
    }

}
