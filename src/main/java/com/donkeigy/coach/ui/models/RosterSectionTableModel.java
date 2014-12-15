package com.donkeigy.coach.ui.models;

import com.yahoo.objects.players.Name;
import com.yahoo.objects.players.Player;
import com.yahoo.objects.team.RosterStats;

import javax.swing.table.AbstractTableModel;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cedric on 12/15/14.
 */
public class RosterSectionTableModel extends AbstractTableModel
{
    private Map<String, RosterStats> rosterStatsMap;
    private List<RosterStats> rosterStatsList;
    private List<String> rosterPositionList;
    private Map<String, String[]> positionToRosterPlayerMap;
    private Map<String, Player> playerNameMap;
    private String sectionName;
    private String[] columnNames = {"Pos", sectionName, "Fan Pts"};
    private Class[] columnClasses = {String.class, String.class, BigDecimal.class};

    public RosterSectionTableModel(String sectionName, List<RosterStats> rosterStatsList, List<String> rosterPositionList, Map<String, String[]> positionToRosterPlayerMap, Map<String, Player> playerNameMap)
    {
        this.rosterStatsList = rosterStatsList;
        this.rosterPositionList = rosterPositionList;
        this.positionToRosterPlayerMap = positionToRosterPlayerMap;
        this.playerNameMap = playerNameMap;
        this.rosterStatsMap = new HashMap<String, RosterStats>();
        this.sectionName = sectionName;
        columnNames[1] = sectionName;
        for(RosterStats playerStats: rosterStatsList)
        {
            rosterStatsMap.put(playerStats.getPlayerKey(),playerStats);
        }

    }

    @Override
    public int getRowCount() {
        return rosterPositionList.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        String position = rosterPositionList.get(rowIndex);
        switch(columnIndex)
        {
            case 0 : return position;

            case 1 : return retrieveRosterPositionPlayerName(position, rowIndex);

            case 2 : return retrieveRosterPositionPlayerStats(position, rowIndex).getPlayerPoints();

            default: return null;
        }

    }
    private String retrieveRosterPositionPlayer(String position ,int rowIndex)
    {
        String[] positionPlayers = positionToRosterPlayerMap.get(position);
        int i = 0;
        for(int j = 0; j<rosterPositionList.size(); j++)
        {
            String availablePosition = rosterPositionList.get(j);
            if(availablePosition.equals(position))
            {
                if(rowIndex == j)
                {
                    return positionPlayers [i];
                }
                else
                {
                    i++;
                }
            }
        }
        return positionPlayers[0];
    }

    private String retrieveRosterPositionPlayerName(String position ,int rowIndex)
    {
        String result = "";
        String playerKey  = retrieveRosterPositionPlayer(position, rowIndex);
        Player player = playerNameMap.get(playerKey);
        Name name = player.getName();
        if(name != null)
        {
            result = name.getFull();
        }
        return result;
    }

    private RosterStats retrieveRosterPositionPlayerStats(String position ,int rowIndex)
    {
        String playerKey  = retrieveRosterPositionPlayer(position, rowIndex);
        return rosterStatsMap.get(playerKey);
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
