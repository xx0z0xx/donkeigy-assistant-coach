package com.donkeigy.coach.ui.panels;

import com.donkeigy.coach.objects.ui.ComboBoxWeek;
import com.donkeigy.coach.services.RosterServices;
import com.donkeigy.coach.ui.models.RosterSectionTableModel;
import com.donkeigy.coach.utils.LeagueUtil;
import com.donkeigy.coach.utils.PlayerUtil;
import com.yahoo.objects.league.League;
import com.yahoo.objects.league.LeagueSettings;
import com.yahoo.objects.players.Player;
import com.yahoo.objects.team.Roster;
import com.yahoo.objects.team.RosterStats;
import com.yahoo.objects.team.Team;
import com.yahoo.services.LeagueService;
import com.yahoo.services.TeamService;
import com.yahoo.services.YahooServiceFactory;
import com.yahoo.services.enums.ServiceType;

import javax.swing.*;
import java.util.List;
import java.util.Map;

/**
 * Created by cedric on 12/15/14.
 */
public class RosterPanel extends JPanel
{
    private JTable offenseTable;
    private JPanel panel1;
    private JTable kickerTable;
    private JTable defenseTable;
    private JPanel rosterOffensePanel;
    private JPanel rosterKickerPanel;
    private JPanel rosterDefensePanel;
    private JComboBox comboBox1;
    private RosterSectionTableModel offenseTableModel;
    private RosterSectionTableModel defenseTableModel;
    private RosterSectionTableModel kickerTableModel;
    private RosterServices rosterServices;
    private TeamService teamService;
    private LeagueService leagueService;
    private int currentWeek;
    private int selectedWeek;

    public RosterPanel()
    {

    }

    public void init(YahooServiceFactory factory)
    {
        this.teamService = (TeamService)factory.getService(ServiceType.TEAM);
        this.leagueService = (LeagueService)factory.getService(ServiceType.LEAGUE);
        this.rosterServices = new RosterServices(teamService, leagueService);

    }

    public void populatePanelData(League league)
    {
        this.currentWeek = Integer.parseInt(league.getCurrent_week());
        this.selectedWeek = currentWeek-1;
        initComboBox();
        List<Team> leagueTeams = teamService.getLeagueTeams(league.getLeague_key());
        Team userTeam = LeagueUtil.retrieveUserTeam(leagueTeams);
        LeagueSettings leagueSettings = leagueService.getLeagueSettings(league.getLeague_key());
        List< RosterStats> rosterStatsList = teamService.getWeeklyTeamRosterPoints(userTeam.getTeam_key(), selectedWeek);
        Map<String, String[]> positionToRosterPlayerMap = rosterServices.generateActualTeamRosterforWeek(league.getLeague_key(), userTeam.getTeam_key(), selectedWeek);
       // Map<String, String[]> positionToRosterPlayerMap = rosterServices.generateBestTeamRosterforWeek(league.getLeague_key(), userTeam.getTeam_key(), selectedWeek);
        Roster roster = teamService.getTeamRoster(userTeam.getTeam_key(), selectedWeek);
        List<Player> players = roster.getPlayers().getPlayer();
        Map<String, Player> playerMap = PlayerUtil.generatePlayerKeyToPlayerMap(players);

        List<String> offensivePositionsList = LeagueUtil.retrievePositionTypeList("O", leagueSettings);
        offenseTableModel = new RosterSectionTableModel("Offense", rosterStatsList,offensivePositionsList, positionToRosterPlayerMap, playerMap);
        offenseTable.setModel(offenseTableModel);

        List<String> defensePositionsList = LeagueUtil.retrievePositionTypeList("DT", leagueSettings);
        defenseTableModel = new RosterSectionTableModel("Defense/Special Teams", rosterStatsList,defensePositionsList, positionToRosterPlayerMap, playerMap);
        defenseTable.setModel(defenseTableModel);

        List<String> kickerPositionsList = LeagueUtil.retrievePositionTypeList("K", leagueSettings);
        kickerTableModel = new RosterSectionTableModel("Kickers", rosterStatsList, kickerPositionsList, positionToRosterPlayerMap, playerMap);
        kickerTable.setModel(kickerTableModel);
    }
    private void initComboBox()
    {
        comboBox1.removeAllItems();
        for (int i = currentWeek - 1 ; i > 0; i--)
        {
            comboBox1.addItem(new ComboBoxWeek(i));
        }

    }


}
