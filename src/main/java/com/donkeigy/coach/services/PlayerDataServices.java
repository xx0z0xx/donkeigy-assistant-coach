package com.donkeigy.coach.services;

import com.yahoo.objects.team.RosterStats;
import com.yahoo.services.TeamService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by cedric on 11/4/14.
 */
public class PlayerDataServices
{
    TeamService teamService;

    public PlayerDataServices(TeamService teamService)
    {
        this.teamService = teamService;
    }

    public Map<String, BigDecimal>  getPositionWeeklyAvg(String teamKey, int week)
    {
        List<RosterStats> weeklyTeamRosterPoints = teamService.getWeeklyTeamRosterPoints(teamKey, week);
        Map<String, BigDecimal> result = new HashMap<String, BigDecimal>();
        Map<String, List<BigDecimal>> positionDataPoints =  new HashMap<String, List<BigDecimal>>();
        for(RosterStats weekRosterStats : weeklyTeamRosterPoints)
        {
            String position = weekRosterStats.getSelectedPosition();
            BigDecimal positionPoints = weekRosterStats.getPlayerPoints();
            List positionPointsList = null;
            if (positionDataPoints.containsKey(position))
            {
                positionPointsList = positionDataPoints.get(position);
            }
            else
            {
                positionPointsList = new LinkedList();
            }
            positionPointsList.add(positionPoints);
            positionDataPoints.put(position,positionPointsList);

        }


        return calculateAvgPositionPts(positionDataPoints);

    }

    private  Map<String, BigDecimal>  calculateAvgPositionPts (Map<String, List<BigDecimal>> positionPointsListMap )
    {
        Map<String, BigDecimal> result = new HashMap<String, BigDecimal>();
        for(String position : positionPointsListMap.keySet())
        {
            List<BigDecimal> positionPts = positionPointsListMap.get(position);
            BigDecimal total = new BigDecimal(0);
            for (BigDecimal playerPts : positionPts)
            {
                total = total.add(playerPts);
            }
            BigDecimal avg = total.divide(new BigDecimal(positionPts.size()));
            result.put(position, avg);

        }
        return result;
    }
}
