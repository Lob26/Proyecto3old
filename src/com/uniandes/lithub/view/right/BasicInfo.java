package com.uniandes.lithub.view.right;

import com.uniandes.lithub.controller.SavedProjectInfo;
import com.uniandes.lithub.model.Activity;
import com.uniandes.lithub.model.Participant;
import com.uniandes.lithub.model.Project;
import com.uniandes.lithub.view.App;
import com.uniandes.lithub.view.builder.BuildAct;
import com.uniandes.lithub.view.builder.BuildMember;
import com.uniandes.lithub.view.right.acts.ActivityContainer;
import com.uniandes.lithub.view.right.desc.DescriptionContainer;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@SuppressWarnings("serial")
public class BasicInfo extends JPanel {
    private final ActivityContainer actPan;
    private final DescriptionContainer descPan;
    private App app;
    private Project project;

    public BasicInfo() {
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{450, 0};
        gridBagLayout.rowHeights = new int[]{150, 150, 0};
        gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);

        descPan = new DescriptionContainer(this);
        GridBagConstraints gbc_descPan = new GridBagConstraints();
        gbc_descPan.fill = GridBagConstraints.BOTH;
        gbc_descPan.insets = new Insets(0, 0, 5, 0);
        gbc_descPan.gridx = 0;
        gbc_descPan.gridy = 0;
        add(descPan, gbc_descPan);

        actPan = new ActivityContainer(this);
        GridBagConstraints gbc_actPan = new GridBagConstraints();
        gbc_actPan.fill = GridBagConstraints.BOTH;
        gbc_actPan.gridx = 0;
        gbc_actPan.gridy = 1;
        add(actPan, gbc_actPan);
    }
    
    public BasicInfo(App app, Project proj) {
    	this();
    	this.app = app;
    	this.project = proj;
        descPan.display(proj.getAllMembers(), proj.getName(), proj.getDescription(), SavedProjectInfo.DTF.print(proj.getStart()),
                proj.getEnd() == null ? "--" : SavedProjectInfo.DTF.print(proj.getEnd()));
    }

    public void setActs(Participant p) {
        Activity current = p.getCurrentAct();
        List<Activity> memberActs = p.getPersonalBranch();
        Activity temp;
        actPan.clean();
        if (current != null)
            actPan.addToModel(current, true);

        for (int i = memberActs.size() - 1; 0 <= i; i--) {
            temp = memberActs.get(i);
            actPan.addToModel(temp, false);
        }
        actPan.display();
    }

    public void buildAct(Participant p) {
        new BuildAct(app, p, project);
    }

    public void endAct(Activity key) {
        app.endAct(project, key);
    }

    public void newMember() {
        new BuildMember(app, project);
    }

    public void removeMember(Participant selected) {
        app.delMember(project, selected);
    }

    public void loadAll(Participant selected) {
        app.loadAll(project, selected);
    }

    public void endProject() {
        app.endProj(project);
    }

    public void alterStopwatch(Activity key, char c) {
        app.alterStopwatch(project, key, c);
    }
}
