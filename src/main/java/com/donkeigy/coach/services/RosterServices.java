package com.donkeigy.coach.services;

import com.yahoo.objects.league.LeagueRosterPosition;
import com.yahoo.objects.league.LeagueRosterPositionList;
import com.yahoo.objects.league.LeagueSettings;
import com.yahoo.objects.team.RosterStats;
import com.yahoo.services.LeagueService;
import com.yahoo.services.TeamService;

import java.util.HashMap;
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

    public  Map<String, String[]> generateBestTeamRoster(String leagueKey, String teamKey, int week)
    {
        LeagueSettings leagueSettings = leagueService.getLeagueSettings(leagueKey);
        Map<String, String[]> result = generateEmptyLeagueRoster(leagueSettings.getRoster_positions());
        List<RosterStats> weeklyTeamRosterPoints = teamService.getWeeklyTeamRosterPoints(teamKey, week);
        for(RosterStats rosterStats : weeklyTeamRosterPoints)
        {
           List<String> eligiblePosList = rosterStats.getEligiblePositions();
           for(String eligiblePos : eligiblePosList)
           {
               //Todo: Algorithm for how to pick the best player in a roster
           }
        }
        return result;
    }
}
