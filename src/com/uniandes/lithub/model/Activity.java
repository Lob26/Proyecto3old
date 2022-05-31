package com.uniandes.lithub.model;

import com.uniandes.lithub.controller.SavedProjectInfo;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.Set;

public class Activity implements Serializable {

    private static final long serialVersionUID = SavedProjectInfo.genSerial(Activity.class.getName());
    private final String title;
    private final String description;
    private final Set<String> type;
    private final Participant doParticipant;
    private Timer stopwatch = new Timer();
    private DateTime start = new DateTime();
    private DateTime end = null;

    /**
     * Constructor of Activity (only use in-app)
     *
     * @param title         <i>Title of the activity</i>
     * @param description   <i>Description of the activity</i>
     * @param type          <i>Types in which the activity gets developed</i>
     * @param doParticipant <i>The participant which do the activity</i>
     */
    public Activity(String title, String description, Set<String> type, Participant doParticipant) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.doParticipant = doParticipant;
    }

    /**
     * Constructor of Activity (only use in the load)
     *
     * @param title         <i>Title of the activity</i>
     * @param description   <i>Description of the activity</i>
     * @param type          <i>Types in which the activity gets developed</i>
     * @param stopwatch     <i>Stopwatch to time the activity time</i>
     * @param start         <i>Date of start</i>
     * @param end           <i>Date of end</i>
     * @param doParticipant <i>The participant which do the activity</i>
     */
    public Activity(String title, String description, Set<String> type, Timer stopwatch, DateTime start, DateTime end,
                    Participant doParticipant) {
        this(title, description, type, doParticipant);
        this.stopwatch = stopwatch;
        this.start = start;
        this.end = end;
    }

    /**
     * Method to alter the timer<br>
     *
     * <b>Values:</b><br>
     * <p>
     * r -> to resume
     * </p>
     * <p>
     * p -> to pause
     * </p>
     * <p>
     * s -> to stop
     * </p>
     *
     * @param val <i>What action do you want to perform?</i>
     */
    public void changeClockStatus(char val) {
        if (this.stopwatch.isEditable()) {
            switch (val) {
                case 'r' -> this.stopwatch.resume();
                case 'p' -> this.stopwatch.pause();
                case 's' -> this.stopwatch.stop();
            }
        }
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Set<String> getType() {
        return type;
    }

    public DateTime getStart() {
        return start;
    }

    public DateTime getEnd() {
        return end;
    }

    public Participant getDoParticipant() {
        return doParticipant;
    }

    public long getTimed() {
        return stopwatch.getElapsed();
    }
}
