package com.uniandes.lithub.model;

import com.uniandes.lithub.controller.SavedProjectInfo;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Participant implements Serializable {

    private static final long serialVersionUID = SavedProjectInfo.genSerial(Participant.class.getName());
    private final String name;
    private final String email;
    private Activity currentAct;
    private List<Activity> personalBranch = new LinkedList<Activity>();

    /**
     * Constructor of Participant (only use in-app)
     *
     * @param name  <i>Participant's name</i>
     * @param email <i>Participant's email</i>
     */
    public Participant(String name, String email) {
        this.name = name;
        this.email = email;
    }

    /**
     * Constructor of Participant (only use in the load)
     *
     * @param name       <i>Participant's name</i>
     * @param email      <i>Participant's email</i>
     * @param currentAct <i>Participant's current activity (can be null)</i>
     * @param activities <i>Participant's completed activities (can be empty)</i>
     */
    public Participant(String name, String email, Activity currentAct, List<Activity> activities) {
        this(name, email);
        this.currentAct = currentAct;
        this.personalBranch = (activities == null) ? new LinkedList<Activity>() : activities;
    }

    /**
     * The participant starts a new activity
     *
     * @param title       <i>The title of the new activity</i>
     * @param description <i>The description of the new activity</i>
     * @param types       <i>The types of the new activity</i>
     * @see Activity
     */
    public void doActivity(String title, String description, Set<String> types) {
        closeAct();
        currentAct = new Activity(title, description, types, this);
    }

    /**
     * The participant closes it's current activity
     */
    public void closeAct() {
        if (currentAct != null) {
            currentAct.changeClockStatus('s');
            personalBranch.add(currentAct);
            currentAct = null;
        }
    }

    /**
     * Get the total time spent in activities by the participant
     *
     * @return The total time spent
     */
    public long getTotalTime() {
        long toRet = 0L;

        if (currentAct != null) toRet += currentAct.getTimed();
        if (!personalBranch.isEmpty()) toRet += personalBranch.stream().map(Activity::getTimed).reduce(0L, Long::sum);

        return toRet;
    }

    /**
     * Get the total number of activities under the participant's doing
     *
     * @return The size of activities including if there are a current activity or
     * doesn't
     */
    public int trueSize() {
        return getFullPersonalBranch().size();
    }

    public String getName() {
        return name;
    }

    public List<Activity> getPersonalBranch() {
        return personalBranch;
    }

    public List<Activity> getFullPersonalBranch() {
        LinkedList<Activity> ret = new LinkedList<Activity>(personalBranch);
        if (currentAct != null) ret.addFirst(currentAct);
        return ret;
    }

    public Activity getCurrentAct() {
        return currentAct;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
            return true;
		if (!(obj instanceof Participant))
			return false;
		Participant other = (Participant) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	
    
}
