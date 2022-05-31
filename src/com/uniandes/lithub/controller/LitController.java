package com.uniandes.lithub.controller;

import com.uniandes.lithub.exceptions.IllegalRemovalException;
import com.uniandes.lithub.exceptions.MemberException;
import com.uniandes.lithub.exceptions.ProjectException;
import com.uniandes.lithub.model.*;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public final class LitController {

    private final Map<String, Project> info;
    private final SavedProjectInfo spi;

    /**
     * @param spi
     */
    public LitController(SavedProjectInfo spi) {
        this.info = spi.getRepositories();
        this.spi = spi;
    }

    public void newProject(String name, String description, Set<String> types, String ownerName, String ownerEmail,
                           String rootName, String rootDesc) throws ProjectException {
        WBStruct wbs = new WBStruct(rootName, rootDesc);
        Participant owner = new Participant(ownerName, ownerEmail);
        Project project = new Project(name, description, types, owner, wbs);

        if (info.containsKey(name))
            throw new ProjectException("El proyecto " + name + " ya existe");
        info.put(name, project);
    }

    public void newMember(Project proj, String newMemName, String newMemEmail) throws MemberException, ProjectException {
        projVer(proj);
        if (proj.isMember(newMemName, newMemEmail)) {
            throw new MemberException("El miembro " + newMemName + " ya esta en " + proj.getName());
        }
        proj.addMember(new Participant(newMemName, newMemEmail));
    }

    public void remMember(Project proj, Participant member) throws ProjectException, IllegalRemovalException, MemberException {
        projVer(proj);
        proj.remMember(member);
    }

    public void newActivity(Project proj, Participant member, String title, Set<String> types, String description)
            throws ProjectException {
        projVer(proj);
        types = proj.verifyTypes(types).getValue();
        member.doActivity(title, description, types);
    }

    public void changeStatusStopwatch(Project proj, Activity act, char mode) throws ProjectException {
        projVer(proj);
        act.changeClockStatus(mode);
    }

    public void endActivity(Project proj, Activity act) throws ProjectException {
        projVer(proj);
        act.getDoParticipant().closeAct();
    }

    public void endProject(Project proj) throws ProjectException {
        projVer(proj);
        proj.endProject();
    }

    public void logActMember(Project proj, Participant mem) {
        proj.push(mem);
    }

    public ContainerInterest interestInformation(String projName) throws ProjectException {
        Project proj = getProj(projName);
        ContainerInterest ci = new ContainerInterest();
        ActivityManager.memberAnalysis(proj, ci);
        ActivityManager.totalTime(proj, ci);
        ActivityManager.activityAnalysis(proj, ci);
        return ci;
    }

    public void quitExecution() throws IOException {
        info.values().forEach(Project::pushAll);
        spi.setRepositories(info);
        spi.save();
    }

    public Project getProj(String projName) throws ProjectException {
        if (info.containsKey(projName)) return info.get(projName);
        throw new ProjectException("El proyecto " + projName + " no existe");
    }

    private void projVer(Project proj) throws ProjectException {
        if (proj.isClosed())
            throw new ProjectException("El proyecto " + proj.getName() + " esta cerrado\nNo puedes modificarlo " +
                    "de ninguna forma");
    }
}
