package com.donkeigy.coach.objects;

/**
 * Created by cedric on 10/31/14.
 */
public class TeamStandingsObj
{
    private String wins;
    private String losses;
    private String ties;
    private String percentage;
    private String pointsFor;
    private String pointsAgainst;
    private String rank;
    private TeamObj team;

    public String getWins() {
        return wins;
    }

    public void setWins(String wins) {
        this.wins = wins;
    }

    public String getLosses() {
        return losses;
    }

    public void setLosses(String losses) {
        this.losses = losses;
    }

    public String getTies() {
        return ties;
    }

    public void setTies(String ties) {
        this.ties = ties;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getPointsFor() {
        return pointsFor;
    }

    public void setPointsFor(String pointsFor) {
        this.pointsFor = pointsFor;
    }

    public String getPointsAgainst() {
        return pointsAgainst;
    }

    public void setPointsAgainst(String pointsAgainst) {
        this.pointsAgainst = pointsAgainst;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public TeamObj getTeam() {
        return team;
    }

    public void setTeam(TeamObj team) {
        this.team = team;
    }
}
