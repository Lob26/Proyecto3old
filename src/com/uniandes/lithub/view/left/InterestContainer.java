package com.uniandes.lithub.view.left;

import com.uniandes.lithub.controller.ContainerInterest;
import com.uniandes.lithub.exceptions.ActivityException;
import com.uniandes.lithub.view.left.interest.*;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class InterestContainer extends JPanel {

    private final JPanel panel;
    private int s = Math.min(this.getWidth(), this.getHeight());

    public InterestContainer() {
        s = s < 200 ? s + 200 : s;
        setLayout(new GridLayout(0, 1, 0, 0));
        panel = new JPanel();
        add(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new WorkerMemberHolder(s));
        panel.add(new HarderActivityHolder(s));
        panel.add(new ParticipantTimeHolder());
        panel.add(new DayTimeHolder());
        panel.add(new TypeTimeHolder());
        panel.add(new TotalTimeHolder());
    }

    public void loadInterestSet(String proyN, ContainerInterest data) throws ActivityException {
        panel.removeAll();
        panel.add(new WorkerMemberHolder(data.getBestMember(), s));
        panel.add(new HarderActivityHolder(data.getHarderActivity(), s));
        panel.add(new ParticipantTimeHolder(data.getTimeByParticipant()));
        panel.add(new DayTimeHolder(data.getTimeByDay()));
        panel.add(new TypeTimeHolder(data.getTimeByType()));
        panel.add(new TotalTimeHolder(proyN, data.getTotalTime()));
    }

}
