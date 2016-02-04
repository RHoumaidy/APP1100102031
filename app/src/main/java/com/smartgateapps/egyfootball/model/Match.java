package com.smartgateapps.egyfootball.model;

import com.smartgateapps.egyfootball.egy.MyApplication;

import java.text.ParseException;

/**
 * Created by Raafat on 16/12/2015.
 */
public class Match {

    private Team teamL;
    private Team teamR;
    private String time;
    private String resultR;
    private String resultL;
    private String date;
    private int hId;
    private boolean isHeader = false;

    public boolean isHeader() {
        return isHeader;
    }

    public void setIsHeader(boolean isHeader) {
        this.isHeader = isHeader;
    }

    public String getDate() {
        try {
            return MyApplication.converteDate(this.time, this.date);
        } catch (ParseException e) {
            e.printStackTrace();
            return this.date;
        }
    }

    public String getResultL() {
        return resultL;
    }

    public void setResultL(String resultL) {
        this.resultL = resultL;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int gethId() {
        return hId;
    }

    public void sethId(int hId) {
        this.hId = hId;
    }

    public Match(){}

    public Team getTeamL() {
        return teamL;
    }

    public void setTeamL(String teamL) {
        this.teamL = Team.load(null,teamL);
    }

    public Team getTeamR() {
        return teamR;
    }

    public void setTeamR(String teamR) {
        this.teamR = Team.load(null,teamR);
    }

    public String getTime() {
        try {
            return MyApplication.converteTime(this.time,this.date);
        } catch (ParseException e) {
            e.printStackTrace();
            return this.getTime();
        }
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getResultR() {
        return resultR;
    }

    public void setResultR(String resultR) {
        this.resultR = resultR;
    }
}