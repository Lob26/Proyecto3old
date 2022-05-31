package com.uniandes.lithub.model;

import com.uniandes.lithub.controller.SavedProjectInfo;
import com.uniandes.lithub.exceptions.IllegalRemovalException;
import com.uniandes.lithub.exceptions.MemberException;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.*;

public class Project implements Serializable {

    private static final long serialVersionUID = SavedProjectInfo.genSerial(Project.class.getName());
    private final String name;
    private final String description;
    private final Set<String> availableTypes;
    private DateTime start = new DateTime();
    private DateTime end = null;
    private Participant owner;
    private List<Participant> participants = new LinkedList<Participant>();
    private List<Activity> masterBranch = new LinkedList<Activity>();
    private final WBStruct wbs;

    /**
     * Constructor of Project (only use in-app)
     *
     * @param name           <i>Project's name</i>
     * @param description    <i>Project's description</i>
     * @param availableTypes <i>Project's selected types</i>
     * @param owner          <i>Project's owner (cannot be null)</i>
     * @param wbs            <i>The Work Structure of the project</i>
     * @see Participant
     */
    public Project(String name, String description, Set<String> availableTypes, Participant owner, WBStruct wbs) {
        this.name = name;
        this.description = description;
        this.availableTypes = availableTypes;
        this.owner = owner;
        this.wbs = wbs;
    }

    /**
     * Constructor of Project (only use in the load)
     *
     * @param name           <i>Project's name</i>
     * @param description    <i>Project's description</i>
     * @param availableTypes <i>Project's selected types</i>
     * @param start          <i>Project's start</i>
     * @param end            <i>Project's end (can be null)</i>
     * @param owner          <i>Project's owner (cannot be null)</i>
     * @param participants   <i>Project's member (Not including the owner)</i>
     * @param masterBranch   <i>Project's main branch of activities</i>
     * @param wbs            <i>The Work Structure of the project</i>
     * @see Participant
     * @see Activity
     */
    public Project(String name, String description, Set<String> availableTypes, DateTime start, DateTime end,
                   Participant owner, List<Participant> participants, List<Activity> masterBranch, WBStruct wbs) {
        this(name, description, availableTypes, owner, wbs);
        this.start = start;
        this.end = end;
        this.participants = participants;
        this.masterBranch = masterBranch;
    }

    /**
     * A member does an activity
     *
     * @param member      <i>The member who does the activity</i>
     * @param title       <i>Activity's title</i>
     * @param description <i>Activity's description</i>
     * @param types       <i>Activity's types</i>
     * @throws MemberException If the member doesn't belong to the project
     */
    public void registerAct(Participant member, String title, String description, Set<String> types)
            throws MemberException {
        getActualMember(member).doActivity(title, description, types);
    }

    /**
     * Simple Method to add a participant to the project
     *
     * @param member <i>Member to be added</i>
     * @throws MemberException If member already is in the project
     */
    public void addMember(Participant member) throws MemberException {
        if (!(participants.contains(member) && member.equals(owner)))
            participants.add(member);
        else
            throw new MemberException("El participante " + member.getName() + "ya está en el proyecto");
    }

    /**
     * Simple Method to remove a participant from the project
     *
     * @param member <i>Member to be removed</i>
     * @throws MemberException         If member already is in the project
     * @throws IllegalRemovalException If removing the member results in a project without members
     */
    public void remMember(String member) throws MemberException, IllegalRemovalException {
        member = getActualMember(member);
        if (member.equals(owner)) {
            if (participants.isEmpty())
                throw new IllegalRemovalException("No se puede eliminar a alguien si con esta expulsion el " +
                        "proyecto queda vacio");
            owner = participants.remove(0);
            participants.add(owner);
        }
        participants.remove(member);
    }

    /**
     * Simple method to close all activities from all participants
     */
    public void closeActivities() {
        participants.forEach(Participant::closeAct);
    }

    /**
     * Simple method to push all activities from a member
     *
     * @param member <i>Member who is going to push their activities</i>
     */
    public void push(Participant member) {
        masterBranch.addAll(member.getPersonalBranch());
    }

    /**
     * Use the {@link #push(Participant)} method for all members
     */
    public void pushAll() {
        push(owner);
        participants.forEach(this::push);
    }

    public void endProject() {
        this.closeActivities();
        this.pushAll();
        this.end = new DateTime();
    }

    /**
     * Inside are intersection between two sets and the difference between the two same sets <br>
     * This to verify whether the types are a valid subset or there are a part that isn't
     *
     * @return A tuple of the form (intersection, difference)
     */
    public Map.Entry<Set<String>, Set<String>> verifyTypes(Set<String> intersection) {

        Set<String> availableSet1 = new HashSet<String>(availableTypes); //To intersect
        Set<String> difference = new HashSet<String>(intersection); //To check items that aren't in
        Set<String> availableSet2 = new HashSet<String>(availableSet1); //To check items that aren't in
        //Note: The duplicates are necessary to avoid affecting and deleting types in the set
        //Yes, intersection and difference between sets affect both sets, so the duplicates are important

        intersection.retainAll(availableSet1);
        difference.removeAll(availableSet2);

        return new AbstractMap.SimpleEntry<Set<String>, Set<String>>(intersection, difference);
    }

    /**
     * Method to verify if a member effectively belongs to the project or not
     *
     * @param member <i>The member to check if they do belong to the project or doesn't</i>
     * @return The member with the actual data
     * @throws MemberException If the member doesn't belong to the project
     */
    private Participant getActualMember(String strmember) throws MemberException {
        Participant member = new Participant(strmember, "");
        Participant toRet;
        if (member.equals(owner))
            toRet = owner;
        else if (participants.contains(member)) {
            int i = participants.indexOf(member);
            toRet = participants.get(i);
        } else
            throw new MemberException("El miembro " + member.getName() + " no es parte del proyecto " + this.name);

        return toRet;
    }

    public boolean isMember(String name, String email) {
        Participant temp = new Participant(name, email);
        try {
            getActualMember(temp);
            return true;
        } catch (MemberException e) {
            return false;
        }
    }

    public DateTime getStart() {
        return start;
    }

    public DateTime getEnd() {
        return isClosed() ? null : end;
    }

    public boolean isClosed() {
        return end == null;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Participant> getAllMembers() {
        LinkedList<Participant> ret = new LinkedList<Participant>(participants);
        ret.addFirst(owner);
        return ret;
    }

}
