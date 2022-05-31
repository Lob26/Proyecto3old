package com.uniandes.lithub.view.right.acts;

import com.uniandes.lithub.model.Activity;
import com.uniandes.lithub.view.right.BasicInfo;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class ActivityContainer extends JPanel {
    private final JPanel panel;
    private final List<ActivityHolder> activitiesPanel = new ArrayList<ActivityHolder>();
    private BasicInfo subFac;

    public ActivityContainer() {
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0};
        gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);

        JLabel lblActividad = new JLabel("Actividades");
        lblActividad.setBorder(new EmptyBorder(10, 20, 7, 0));
        GridBagConstraints gbc_lblActividad = new GridBagConstraints();
        gbc_lblActividad.insets = new Insets(0, 0, 5, 0);
        gbc_lblActividad.anchor = GridBagConstraints.WEST;
        gbc_lblActividad.gridx = 0;
        gbc_lblActividad.gridy = 0;
        add(lblActividad, gbc_lblActividad);

        JScrollPane scrollPane = new JScrollPane();
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridx = 0;
        gbc_scrollPane.gridy = 1;
        add(scrollPane, gbc_scrollPane);

        panel = new JPanel();
        panel.setFocusable(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        activitiesPanel.add(new ActivityHolder());
        activitiesPanel.add(new ActivityHolder());

        display();

        scrollPane.setViewportView(panel);
    }

    public ActivityContainer(BasicInfo bi) {
        this();
        this.subFac = bi;
    }

    public void addToModel(Activity act, boolean b) {
        Map.Entry<Activity, Boolean> tuple = new AbstractMap.SimpleEntry<Activity, Boolean>(act, b);
        this.activitiesPanel.add(new ActivityHolder(tuple, this));
    }

    public void clean() {
        panel.removeAll();
        this.activitiesPanel.clear();
    }

    public void display() {
        this.activitiesPanel.forEach(ah -> panel.add(ah));
        revalidate();
        repaint();
    }

    public void triEndAct(Activity key) {
        this.subFac.endAct(key);
    }

    public void alterStopwatch(Activity key, char c) {
        this.subFac.alterStopwatch(key, c);
    }

}