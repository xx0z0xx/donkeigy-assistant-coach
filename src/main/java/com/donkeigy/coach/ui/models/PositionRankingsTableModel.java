package com.donkeigy.coach.ui.models;

import javax.swing.table.AbstractTableModel;
import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by cedric on 11/21/14.
 */
public class PositionRankingsTableModel extends AbstractTableModel
{

    private Map<String, Integer> positionRankingMap;

    public PositionRankingsTableModel(Map<String, Integer> positionRankingMap)
    {
        this.positionRankingMap = positionRankingMap;
    }



    @Override
    public int getRowCount()
    {
        Object[] positionStringArray = positionRankingMap.keySet().toArray();
        return positionStringArray.length;
    }

    @Override
    public int getColumnCount()
    {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Object[] positionStringArray = positionRankingMap.keySet().toArray();
        String position = (String) positionStringArray[rowIndex];
        if(columnIndex == 0)
        {
            return position;
        }
        else
        {
            return positionRankingMap.get(position);
        }


    }
    public String getColumnName(int column)
    {
        if(column == 0)
        {
            return "Position";
        }
        else
        {
           return "Rank";
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
            return Integer.class;
        }
    }
}
