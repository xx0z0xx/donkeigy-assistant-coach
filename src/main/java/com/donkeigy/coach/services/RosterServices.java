package com.donkeigy.coach.services;

import com.donkeigy.coach.utils.PlayerUtil;
import com.yahoo.objects.league.LeagueRosterPosition;
import com.yahoo.objects.league.LeagueRosterPositionList;
import com.yahoo.objects.league.LeagueSettings;
import com.yahoo.objects.team.RosterStats;
import com.yahoo.services.LeagueService;
import com.yahoo.services.TeamService;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by cedric on 12/9/14.
 */
public class RosterServices
{
    TeamService teamService;
    LeagueService leagueService;

    public RosterServices(TeamService teamService, LeagueService leagueService)
    {
        this.teamService = teamService;
        this.leagueService = leagueService;
    }

    private Map<String, String[]> generateEmptyLeagueRoster(LeagueRosterPositionList leagueRosterPositionList)
    {
        Map<String, String[]> result = new HashMap<String, String[]>();
        for(LeagueRosterPosition position : leagueRosterPositionList.getRoster_position())
        {
            result.put(position.getPosition(), new String[Integer.parseInt(position.getCount())]);
        }
        return result;
    }

    public  Map<String, String[]> generateBestTeamRosterforWeek(String leagueKey, String teamKey, int week)
    {
        LeagueSettings leagueSettings = leagueService.getLeagueSettings(leagueKey);
        Map<String, String[]> result = generateEmptyLeagueRoster(leagueSettings.getRoster_positions());
        List<RosterStats> weeklyTeamRosterPoints = teamService.getWeeklyTeamRosterPoints(teamKey, week);
        result = populateRosterWithBestPlayers(result, weeklyTeamRosterPoints);
        return result;
    }
    public  Map<String, String[]> generateActualTeamRosterforWeek(String leagueKey, String teamKey, int week)
    {
        LeagueSettings leagueSettings = leagueService.getLeagueSettings(leagueKey);
        Map<String, String[]> result = generateEmptyLeagueRoster(leagueSettings.getRoster_positions());
        List<RosterStats> weeklyTeamRosterPoints = teamService.getWeeklyTeamRosterPoints(teamKey, week);
        result = populateRosterWithActualPlayers(result, weeklyTeamRosterPoints);
        return result;
    }

    private Map<String, String[]> populateRosterWithBestPlayers (Map<String, String[]> roster, List<RosterStats> players)
    {
        Map<String, String[]> result = roster;
        Object[] rosterPositionObjArr = result.keySet().toArray();
        List <RosterStats> playerListCpy = new LinkedList<RosterStats>();
        playerListCpy.addAll(players);
        for(Object rosterPositionObj : rosterPositionObjArr)
        {

            String rosterPosition = (String)rosterPositionObj;
            if(!(rosterPosition.equals("IR")||rosterPosition.equals("BN")))
            {
                String[] rosterPositionSlots = result.get(rosterPosition);
                for (int i = 0; i < rosterPositionSlots.length; i++) {
                    RosterStats bestPositionPlayerPossible = PlayerUtil.getBestPositionPlayerPossible(rosterPosition, playerListCpy);
                    rosterPositionSlots[i] = bestPositionPlayerPossible.getPlayerKey();
                    playerListCpy.remove(bestPositionPlayerPossible);
                }
            }
        }
        return result;
    }
    private Map<String, String[]> populateRosterWithActualPlayers(Map<String, String[]> roster, List<RosterStats> players)
    {
        Map<String, String[]> result = roster;
        for(RosterStats player : players)
        {
            String position = player.getSelectedPosition();
            String[] positionPlayers = roster.get(position);
            if(positionPlayers != null)
            {
                boolean isPlaced = false;
                int i = 0;
                while(!isPlaced && i<positionPlayers.length)
                {
                    if(positionPlayers[i] == null || positionPlayers[i].isEmpty())
                    {
                        positionPlayers[i] = player.getPlayerKey();
                        isPlaced = true;
                    }
                    else
                    {
                        i++;
                    }

                }
            }
        }
        return result;
    }
}
