package com.donkeigy.coach.ui.models;

import com.yahoo.objects.league.LeagueRosterPosition;
import com.yahoo.objects.league.LeagueRosterPositionList;
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
    private LeagueRosterPositionList leagueRosterPositionList;

    public PositionCompareTableModel(List<Team> teams, Map<String, Map<String, BigDecimal>> positionWeeklyLeagueAvgs, LeagueRosterPositionList leagueRosterPositionList)
    {
        this.teams = teams;
        this.positionWeeklyLeagueAvgs = positionWeeklyLeagueAvgs;
        this.leagueRosterPositionList = leagueRosterPositionList;
    }



    @Override
    public int getRowCount()
    {
        return teams.size();
    }

    @Override
    public int getColumnCount() {
        return  leagueRosterPositionList.getRoster_position().size()+ 1;
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
            LeagueRosterPosition leaugePos = leagueRosterPositionList.getRoster_position().get(columnIndex - 1);
            String pos = leaugePos.getPosition();
            BigDecimal result = positionWeeklyAvg.get(pos);
            if (result == null)
            {
                result = new BigDecimal(0);
            }
            return result;
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

            LeagueRosterPosition leaugePos = leagueRosterPositionList.getRoster_position().get(column - 1);
            String pos = leaugePos.getPosition();
            return pos;
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
