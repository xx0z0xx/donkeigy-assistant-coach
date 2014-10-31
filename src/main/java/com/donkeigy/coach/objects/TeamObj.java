package com.donkeigy.coach.objects;

import java.util.List;

/**
 * Created by cedric on 10/31/14.
 */
public class TeamObj
{
    private String gameKey;
    private String leaugeId;
    private String teamKey;
    private String teamId;
    private String name;
    private String url;
    private String teamLogoUrl;
    private String isOwnedByCurrentLogin;
    private String isClinchedPlayoffs;
    private List<PlayerObj> teamPlayers;
    private TeamStandingsObj standings;

    public String getGameKey() {
        return gameKey;
    }

    public void setGameKey(String gameKey) {
        this.gameKey = gameKey;
    }

    public String getLeaugeId() {
        return leaugeId;
    }

    public void setLeaugeId(String leaugeId) {
        this.leaugeId = leaugeId;
    }

    public String getTeamKey() {
        return teamKey;
    }

    public void setTeamKey(String teamKey) {
        this.teamKey = teamKey;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTeamLogoUrl() {
        return teamLogoUrl;
    }

    public void setTeamLogoUrl(String teamLogoUrl) {
        this.teamLogoUrl = teamLogoUrl;
    }

    public String getIsOwnedByCurrentLogin() {
        return isOwnedByCurrentLogin;
    }

    public void setIsOwnedByCurrentLogin(String isOwnedByCurrentLogin) {
        this.isOwnedByCurrentLogin = isOwnedByCurrentLogin;
    }

    public String getIsClinchedPlayoffs() {
        return isClinchedPlayoffs;
    }

    public void setIsClinchedPlayoffs(String isclinchedPlayoffs) {
        this.isClinchedPlayoffs = isclinchedPlayoffs;
    }

    public List<PlayerObj> getTeamPlayers() {
        return teamPlayers;
    }

    public void setTeamPlayers(List<PlayerObj> teamPlayers) {
        this.teamPlayers = teamPlayers;
    }

    public TeamStandingsObj getStandings() {
        return standings;
    }

    public void setStandings(TeamStandingsObj standings) {
        this.standings = standings;
    }
}
