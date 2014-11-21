package com.donkeigy.coach.services;

import com.donkeigy.coach.objects.TeamStat;
import com.yahoo.objects.team.RosterStats;
import com.yahoo.objects.team.Team;
import com.yahoo.services.TeamService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

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
            BigDecimal avg = total.divide(new BigDecimal(positionPts.size()),5, RoundingMode.HALF_UP);
            result.put(position, avg);

        }
        return result;
    }

    public Map<String, Integer> getPlayerWeeklyPositionRanking(List<Team> leagueTeams,  Team userTeam, Map<String, Map<String, BigDecimal>> positionWeeklyLeagueAvgs)
    {
        Map<String, Integer> result = new HashMap<String, Integer>();

        Team tempTeam =  leagueTeams.get(0);
        Map<String, BigDecimal> positionWeeklyAvgs = positionWeeklyLeagueAvgs.get(tempTeam.getTeam_key());
        Map<String, List<TeamStat>> positionPointsMap =  new HashMap<String, List<TeamStat>>();
        Object[] positionsStringArr =  positionWeeklyAvgs.keySet().toArray();

        for(Team team : leagueTeams)
        {
           positionWeeklyAvgs = positionWeeklyLeagueAvgs.get(team.getTeam_key());
           for(Object positionStrObj : positionsStringArr)
           {
              String position = (String)positionStrObj;
              BigDecimal avg = positionWeeklyAvgs.get(position);
              TeamStat tempStat = new TeamStat(team.getTeam_key(), avg);
              List<TeamStat> positionTeamStatList = positionPointsMap.get(position);
               if(positionTeamStatList == null)
               {
                   positionTeamStatList = new LinkedList<TeamStat>();
               }
               positionTeamStatList.add(tempStat);
               positionPointsMap.put(position, positionTeamStatList);
           }
        }

        for(Object positionStrObj : positionsStringArr)
        {
            String position = (String)positionStrObj;
            List<TeamStat> positionTeamStatList = positionPointsMap.get(position);
            TeamStat[] positionTeamStatArr =  positionTeamStatList.toArray(new TeamStat[positionTeamStatList.size()]);
            Arrays.sort(positionTeamStatArr,Collections.reverseOrder());

            for(int i=0; i<= positionTeamStatArr.length ;i++)
            {
                if(userTeam.getTeam_key().equals(positionTeamStatArr[i].getTeamKey()))
                {
                    result.put(position,new Integer(i+1));
                    break;
                }

            }
        }
        return  result;
    }



}
