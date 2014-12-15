package com.donkeigy.coach.utils;

import com.yahoo.objects.league.LeagueRosterPosition;
import com.yahoo.objects.league.LeagueRosterPositionList;
import com.yahoo.objects.league.LeagueSettings;
import com.yahoo.objects.team.Team;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by cedric on 12/15/14.
 */
public class LeagueUtil
{
    public static List<String> retrievePositionTypeList(String positionType, LeagueSettings settings)
    {
        List<String> result = new LinkedList<String>();
        LeagueRosterPositionList rosterPositionListObj = settings.getRoster_positions();
        List<LeagueRosterPosition>  rosterPositionList = rosterPositionListObj.getRoster_position();
        for(LeagueRosterPosition leagueRosterPosition: rosterPositionList)
        {
            String currentPositionType = leagueRosterPosition.getPosition_type();
            if(currentPositionType!= null && currentPositionType.equals(positionType))
            {
                int count = Integer.parseInt(leagueRosterPosition.getCount());
                for(int i = 0; i < count; i++)
                {
                    result.add(leagueRosterPosition.getPosition());
                }
            }
        }
        return result;
    }

    public static Team retrieveUserTeam(List<Team> teams)
    {

            for (Team team : teams)
            {
                if(team.getIs_owned_by_current_login() != null && team.getIs_owned_by_current_login().equals("1"))
                {
                   return team;
                }
            }
        return null;
    }
}
