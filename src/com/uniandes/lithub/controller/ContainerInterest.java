package com.uniandes.lithub.controller;

import com.uniandes.lithub.model.Activity;
import com.uniandes.lithub.model.Participant;
import org.joda.time.DateTime;

import java.util.Map;

public final class ContainerInterest {

    private Participant bestMember;
    private Map<String, Long> timeByParticipant;
    private Map<DateTime, Long> timeByDay;
    private Map<String, Long> timeByType;
    private Activity harderActivity;
    private long totalTimeAct;
    private long totalTime;

    public Participant getBestMember() {
        return bestMember;
    }

    public void setBestMember(Participant bestMember) {
        this.bestMember = bestMember;
    }

    public Map<String, Long> getTimeByParticipant() {
        return timeByParticipant;
    }

    public void setTimeByParticipant(Map<String, Long> timeByParticipant) {
        this.timeByParticipant = timeByParticipant;
    }

    public Map<DateTime, Long> getTimeByDay() {
        return timeByDay;
    }

    public void setTimeByDay(Map<DateTime, Long> timeByDay) {
        this.timeByDay = timeByDay;
    }

    public Map<String, Long> getTimeByType() {
        return timeByType;
    }

    public void setTimeByType(Map<String, Long> timeByType) {
        this.timeByType = timeByType;
    }

    public Activity getHarderActivity() {
        return harderActivity;
    }

    public void setHarderActivity(Activity harderActivity) {
        this.harderActivity = harderActivity;
    }

    public long getTotalTimeAct() {
        return totalTimeAct;
    }

    public void setTotalTimeAct(long totalTimeAct) {
        this.totalTimeAct = totalTimeAct;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }


}