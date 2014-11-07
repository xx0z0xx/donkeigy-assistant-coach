package com.donkeigy.coach.ui.models;

import com.yahoo.objects.team.Team;
import com.yahoo.objects.team.TeamStandings;

import javax.swing.table.AbstractTableModel;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by cedric on 10/28/14.
 */
public class LeagueTeamsTableModel extends AbstractTableModel
{
    private String[] columnNames = {"Name", "Wins", "Losses","Ties","Percentage", "Pts. (For)", "Pts. (Against)"};
    private Class[] columnClasses = {String.class, BigDecimal.class, BigDecimal.class, BigDecimal.class, BigDecimal.class, BigDecimal.class,  BigDecimal.class};
    private List<Team> teams;
    private Map<String, TeamStandings> teamStandingsMap;

    public LeagueTeamsTableModel(List<Team> teams, Map<String, TeamStandings> teamStandingsMap)
    {
        this.teams = teams;
        this.teamStandingsMap = teamStandingsMap;
    }

    @Override
    public int getRowCount() {
        return teams.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       Team team = teams.get(rowIndex);
        TeamStandings teamStandings = teamStandingsMap.get(team.getTeam_key());
        switch(columnIndex)
        {
            case 0: return team.getName();

            case 1: return new BigDecimal(teamStandings.getOutcome_totals().getWins());

            case 2: return new BigDecimal(teamStandings.getOutcome_totals().getLosses());

            case 3: return new BigDecimal(teamStandings.getOutcome_totals().getTies());

            case 4: return new BigDecimal(teamStandings.getOutcome_totals().getPercentage());

            case 5: return new BigDecimal(teamStandings.getPoints_for());

            case 6: return new BigDecimal(teamStandings.getPoints_against());

            default: return null;
        }
    }
    @Override
    public String getColumnName(int column)
    {
        return columnNames[column]; // get list sorted by draft order
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClasses[columnIndex];
    }

}
