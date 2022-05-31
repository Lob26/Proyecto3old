package com.uniandes.lithub.view.left;

import com.uniandes.lithub.controller.ContainerInterest;
import com.uniandes.lithub.exceptions.ActivityException;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class InterestInfo extends JPanel {

    private final InterestContainer container;

    public InterestInfo() {
        setLayout(new GridLayout(1, 1));

        JScrollPane scrollPane = new JScrollPane();
        add(scrollPane);

        container = new InterestContainer();
        scrollPane.setViewportView(container);
    }
    
    public InterestInfo(String proyName, ContainerInterest ninthOption) throws ActivityException {
    	this();
    	container.loadInterestSet(proyName, ninthOption);
    }
}
