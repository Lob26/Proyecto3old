package com.uniandes.lithub.model;

import com.uniandes.lithub.controller.ContainerInterest;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Minutes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class ActivityManager {

    private ActivityManager() {
    }

    /**
     * <b>Information of interest</b><br>
     * <p>
     * To get the time spent by member in the project
     * </p>
     *
     * @param p <i>The project to analyze</i>
     */
    public static void memberAnalysis(Project p, ContainerInterest ci) {
        List<Participant> members = p.getAllMembers();

        Map<String, Long> timeParticipant = new HashMap<String, Long>();
        Participant best = members.get(0);
        long totalTime = 0L;

        for (Participant mem : members) {
            timeParticipant.put(mem.getName(), mem.getTotalTime());
            if (mem.getTotalTime() > best.getTotalTime())
                best = mem;
            totalTime += mem.getTotalTime();
        }
        ci.setBestMember(best);
        ci.setTimeByParticipant(timeParticipant);
        ci.setTotalTimeAct(totalTime);
    }

    /**
     * <b>Information of interest</b><br>
     * <p>
     * To get the total time spent in the project
     * </p>
     *
     * @param p <i>The project to analyze</i>
     */
    public static void totalTime(Project p, ContainerInterest ci) {

        int mins;
        if (p.getEnd() != null) {
            mins = Minutes.minutesBetween(p.getStart(), p.getEnd()).getMinutes();
        } else {
            mins = 0;
        }
        ci.setTotalTime(mins);
    }

    /**
     * <b>Information of interest</b><br>
     * <p>
     * To get the total time spent in activities of the project and the total time
     * spent in any day
     * </p>
     *
     * @param p <i>The project to analyze</i>
     */
    public static void activityAnalysis(Project p, ContainerInterest ci) {
        List<Participant> members = p.getAllMembers();

        Map<String, Long> timeType = new HashMap<String, Long>();
        Map<DateTime, Long> timeDay = new HashMap<DateTime, Long>();
        Activity harder = null;

        for (Participant mem : members) {
            List<Activity> activities = mem.getFullPersonalBranch();
            if (!activities.isEmpty()) {
                for (Activity act : activities) {
                    extractedByType(timeType, act);
                    extractedByDay(timeDay, act);
                    harder = harder == null ? act : extractedAct(harder, act);
                }
            }
        }
        ci.setHarderActivity(harder);
        ci.setTimeByDay(timeDay);
        ci.setTimeByType(timeType);
    }

    /**
     * Extracted method to organize getting of a harder activity
     *
     * @param harder <i>The actual harder activity</i>
     * @param act    <i>The possible harder activity</i>
     * @return The harder between the two
     */
    private static Activity extractedAct(Activity harder, Activity act) {
        if (harder != null) {
            if (act != null) {
                if (harder.getTimed() < act.getTimed())
                    harder = act;
            }
        } else
            harder = act;
        return harder;
    }

    /**
     * Extracted method to organize the putting of time by day
     *
     * @param timeDay <i>The map to be loaded</i>
     * @param act     <i>The activity being analyzed</i>
     */
    private static void extractedByDay(Map<DateTime, Long> timeDay, Activity act) {
        DateTime startAct = act.getStart();
        DateTime endAct = act.getEnd() == null ? DateTime.now() : act.getEnd();
        int range = Days.daysBetween(startAct, endAct).getDays();
        for (int i = 0; i <= range; i++) {
            DateTime key = startAct.plusDays(i);
            long value = timeDay.getOrDefault(key, 0L) + act.getTimed();
            timeDay.put(key, value);
        }
    }

    /**
     * Extracted method to organize the putting of time by type
     *
     * @param timeType <i>The map to be loaded</i>
     * @param act      <i>The activity being analyzed</i>
     */
    private static void extractedByType(Map<String, Long> timeType, Activity act) {
        Set<String> actTypes = act.getType();
        for (String type : actTypes) {
            long val = timeType.getOrDefault(type, 0L) + act.getTimed();
            timeType.put(type, val);
        }
    }
}
