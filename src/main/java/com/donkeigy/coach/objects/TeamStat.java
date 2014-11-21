package com.donkeigy.coach.objects;

import java.math.BigDecimal;

/**
 * Created by cedric on 11/21/14.
 */
public class TeamStat implements Comparable<TeamStat>
{
    String teamKey;
    BigDecimal stat;

    public TeamStat(String teamKey, BigDecimal stat) {
        this.teamKey = teamKey;
        this.stat = stat;
    }

    public String getTeamKey() {
        return teamKey;
    }

    public void setTeamKey(String teamKey) {
        this.teamKey = teamKey;
    }

    public BigDecimal getStat() {
        return stat;
    }

    public void setStat(BigDecimal stat) {
        this.stat = stat;
    }

    @Override
    public int compareTo(TeamStat o)
    {

        return this.getStat().compareTo(o.getStat());
    }
}
