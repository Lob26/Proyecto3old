package com.uniandes.lithub.model;

import com.uniandes.lithub.controller.SavedProjectInfo;

import java.io.Serializable;

public final class Timer implements Serializable {

    private static final long serialVersionUID = SavedProjectInfo.genSerial(Timer.class.getName());
    private long start = 0L;
    private long stop = 0L;
    private long elapsed = 0L;
    private boolean running;
    private boolean editable = true;

    /**
     * Constructor of Timer (only use in-app)
     */
    public Timer() {
        this.start();
    }

    /**
     * Constructor of Timer (only use in the load)
     *
     * @param start   <i>The actual start of the timer</i>
     * @param stop    <i>The actual stop of the timer</i>
     * @param elapsed <i>The time elapsed between to instants with the timer
     *                running</i>
     * @param running <i>Is the timer running?</i>
     */
    public Timer(long start, long stop, long elapsed, boolean running, boolean editable) {
        this.start = start;
        this.stop = stop;
        this.elapsed = elapsed;
        this.running = running;
        this.editable = editable;
    }

    private void start() {
        start = System.currentTimeMillis();
        running = true;
    }

    /**
     * From pause to resume
     */
    public void resume() {
        if (!running) {
            this.start = System.currentTimeMillis();
            this.stop = start;
            this.running = true;
        }
    }

    /**
     * From resume to pause
     */
    public void pause() {
        if (!running) {
            this.stop = System.currentTimeMillis();
            this.elapsed += (stop - start);
            this.start = stop;
            this.running = false;
        }
    }

    /**
     * Making the timer uneditable (Activity ended)
     */
    public void stop() {
        pause();
        this.editable = false;
    }

    public long getElapsed() {
        return elapsed / 60000;
    }// Time in minutes

    public boolean isEditable() {
        return editable;
    }
}
