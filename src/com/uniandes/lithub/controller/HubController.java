package com.uniandes.lithub.controller;

import com.uniandes.lithub.exceptions.MemberException;
import com.uniandes.lithub.exceptions.ProjectException;
import com.uniandes.lithub.model.Participant;
import com.uniandes.lithub.model.Project;
import com.uniandes.lithub.model.WBStruct;

import java.util.Set;

public final class HubController {

    private SavedProjectInfo spi;

    public HubController(SavedProjectInfo spi) {
        this.spi = spi;
    }


    public Exception execNewProject(String[] projectBase, String[] owner, String[] wbs, Set<String> types) {
        WBStruct newWBS = new WBStruct(wbs[0], wbs[1]);
        Participant newOwner = new Participant(owner[0], owner[1]);
        Project newProject = new Project(projectBase[0], projectBase[1], types, newOwner, newWBS);
        if (spi.search(projectBase[0]) != null)
            return new ProjectException("El proyecto " + projectBase[0] + " ya existe");
        spi.addProject(newProject);
        return null;
    }

    public Exception execNewParticipant(String projectName, String[] member) {
        Project proj = spi.search(projectName);
        Exception thrown = projectVerification(proj);
        try {
            Participant participant = new Participant(member[0], member[1]);
            proj.addMember(participant);
        } catch (MemberException e) { return e; }
        return thrown ;
    }

    public Exception execRemoveParticipant(String projectName, String member) {
        Project proj = spi.search(projectName);
        Exception thrown = projectVerification(proj);
        try {
            proj.remMember(member);
        } catch (MemberException e) { return e; }
        return thrown ;

    }

}