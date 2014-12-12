package com.donkeigy.coach.comparators;

import com.yahoo.objects.team.RosterStats;

import java.math.BigDecimal;
import java.util.Comparator;

/**
 * Created by cedric on 12/11/14.
 */
public class PlayerActualPointsComparator implements Comparator<RosterStats>
{

    @Override
    public int compare(RosterStats o1, RosterStats o2) {
        BigDecimal playerPoints1 = o1.getPlayerPoints();
        BigDecimal playerPoints2 = o2.getPlayerPoints();
        return playerPoints1.compareTo(playerPoints2);
    }


}
