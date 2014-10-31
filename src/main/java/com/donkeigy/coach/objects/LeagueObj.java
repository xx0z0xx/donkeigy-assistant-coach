package com.donkeigy.coach.objects;

import com.yahoo.objects.league.League;

/**
 * Created by cedric on 10/31/14.
 */
public class LeagueObj
{
    private String leagueKey;
    private String leagueId;
    private String leagueChatId;
    private String name;
    private String url;
    private String draftStatus;
    private Integer numTeams;
    private String editKey;
    private String weeklyDeadline;
    private String leagueUpdateTimestamp;
    private String scoringType;
    private String leagueType;
    private Integer currentWeek;
    private Integer startWeek;
    private String startWeekStartDate;
    private Integer endWeek;
    private String isFinished;
    private String startDate;
    private String endDate;

    public LeagueObj(League yahooLeague)
    {

        this.leagueKey = yahooLeague.getLeague_key();
        this.leagueId = yahooLeague.getLeague_id();
        this.leagueChatId = yahooLeague.getLeague_chat_id();
        this.name = yahooLeague.getName();
        this.url = yahooLeague.getUrl();
        this.draftStatus = yahooLeague.getDraft_status();
        this.numTeams = Integer.parseInt(yahooLeague.getNum_teams());
        this.editKey = yahooLeague.getEdit_key();
        this.weeklyDeadline = yahooLeague.getWeekly_deadline();
        this.leagueUpdateTimestamp = yahooLeague.getLeague_update_timestamp();
        this.scoringType = yahooLeague.getScoring_type();
        this.leagueType = yahooLeague.getLeague_type();
        this.currentWeek = Integer.parseInt(yahooLeague.getCurrent_week());
        this.startWeek = Integer.parseInt(yahooLeague.getStart_week());
        this.startWeekStartDate = yahooLeague.getStart_week_start_date();
        this.endWeek = Integer.parseInt(yahooLeague.getEnd_week());
        this.isFinished = yahooLeague.getIs_finished();
        this.startDate = yahooLeague.getStart_date();
        this.endDate = yahooLeague.getEnd_date();
    }

    public String getLeagueKey() {
        return leagueKey;
    }

    public void setLeagueKey(String leagueKey) {
        this.leagueKey = leagueKey;
    }

    public String getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    public String getLeagueChatId() {
        return leagueChatId;
    }

    public void setLeagueChatId(String leagueChatId) {
        this.leagueChatId = leagueChatId;
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

    public String getDraftStatus() {
        return draftStatus;
    }

    public void setDraftStatus(String draftStatus) {
        this.draftStatus = draftStatus;
    }

    public Integer getNumTeams() {
        return numTeams;
    }

    public void setNumTeams(Integer numTeams) {
        this.numTeams = numTeams;
    }

    public String getEditKey() {
        return editKey;
    }

    public void setEditKey(String editKey) {
        this.editKey = editKey;
    }

    public String getWeeklyDeadline() {
        return weeklyDeadline;
    }

    public void setWeeklyDeadline(String weeklyDeadline) {
        this.weeklyDeadline = weeklyDeadline;
    }

    public String getLeagueUpdateTimestamp() {
        return leagueUpdateTimestamp;
    }

    public void setLeagueUpdateTimestamp(String leagueUpdateTimestamp) {
        this.leagueUpdateTimestamp = leagueUpdateTimestamp;
    }

    public String getScoringType() {
        return scoringType;
    }

    public void setScoringType(String scoringType) {
        this.scoringType = scoringType;
    }

    public String getLeagueType() {
        return leagueType;
    }

    public void setLeagueType(String leagueType) {
        this.leagueType = leagueType;
    }

    public Integer getCurrentWeek() {
        return currentWeek;
    }

    public void setCurrentWeek(Integer currentWeek) {
        this.currentWeek = currentWeek;
    }

    public Integer getStartWeek() {
        return startWeek;
    }

    public void setStartWeek(Integer startWeek) {
        this.startWeek = startWeek;
    }

    public String getStartWeekStartDate() {
        return startWeekStartDate;
    }

    public void setStartWeekStartDate(String startWeekStartDate) {
        this.startWeekStartDate = startWeekStartDate;
    }

    public Integer getEndWeek() {
        return endWeek;
    }

    public void setEndWeek(Integer endWeek) {
        this.endWeek = endWeek;
    }

    public String getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(String isFinished) {
        this.isFinished = isFinished;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }
}
