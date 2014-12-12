package com.donkeigy.coach.utils;

import com.donkeigy.coach.comparators.PlayerActualPointsComparator;
import com.yahoo.objects.team.RosterStats;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cedric on 12/11/14.
 */
public class PlayerUtil
{
    public static RosterStats getBestPositionPlayerPossible(String position, List<RosterStats> players)
    {
        RosterStats result = null;
        List<RosterStats> eligiblePlayers = getListofPositionPlayers(position,players);

        if(!eligiblePlayers.isEmpty())
        {
            Collections.sort(eligiblePlayers, new PlayerActualPointsComparator());
            result = eligiblePlayers.get(0);
        }
        return result;
    }
    public static List<RosterStats> getListofPositionPlayers(String position, List<RosterStats> players)
    {
        List<RosterStats> result = new LinkedList<RosterStats>();
        for(RosterStats player : players)
        {
            List<String> eligiblePos = player.getEligiblePositions();
            for(String pos : eligiblePos)
            {
                if(pos.equals(position))
                {
                   result.add(player);
                    break;
                }
            }

        }

        return result;
    }
}
