package com.donkeigy.coach.ui.models;

import com.yahoo.objects.team.Team;

import javax.swing.table.AbstractTableModel;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by cedric on 11/19/14.
 */
public class PositionCompareTableModel extends AbstractTableModel
{
    private List<Team> teams;
    private Map<String, Map<String, BigDecimal>> positionWeeklyLeagueAvgs;

    public PositionCompareTableModel(List<Team> teams, Map<String, Map<String, BigDecimal>> positionWeeklyLeagueAvgs)
    {
        this.teams = teams;
        this.positionWeeklyLeagueAvgs = positionWeeklyLeagueAvgs;
    }



    @Override
    public int getRowCount()
    {
        return teams.size();
    }

    @Override
    public int getColumnCount() {
        Object[] teamsArr = positionWeeklyLeagueAvgs.keySet().toArray();
        String teamKey = (String)teamsArr[0];
        return positionWeeklyLeagueAvgs.get(teamKey).size() + 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Team team = teams.get(rowIndex);
        if(columnIndex == 0)
        {
            return team.getName();
        }
        else
        {
            Map<String, BigDecimal> positionWeeklyAvg = positionWeeklyLeagueAvgs.get(team.getTeam_key());
            Object[] possArr = positionWeeklyAvg.keySet().toArray();
            String pos = (String) possArr[columnIndex-1];
            return positionWeeklyAvg.get(pos);
        }


    }

    @Override
    public String getColumnName(int column)
    {
        if(column == 0)
        {
            return "Team Name";
        }
        else
        {
            Object[] teamsArr = positionWeeklyLeagueAvgs.keySet().toArray();
            String teamKey = (String)teamsArr[0];
            Map<String, BigDecimal> positionWeeklyAvg = positionWeeklyLeagueAvgs.get(teamKey);
            Object[] possArr = positionWeeklyAvg.keySet().toArray();
            return (String) possArr[column-1];
        }

    }
    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        if(columnIndex == 0)
        {
            return String.class;
        }
        else
        {
            return BigDecimal.class;
        }
    }
}
