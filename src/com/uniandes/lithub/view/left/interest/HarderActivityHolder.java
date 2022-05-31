package com.uniandes.lithub.view.left.interest;

import com.uniandes.lithub.exceptions.ActivityException;
import com.uniandes.lithub.model.Activity;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;

@SuppressWarnings("serial")
public class HarderActivityHolder extends JPanel {
    public static final String LITACT = "Local_Information_Tracker/img_src/act.png";
    private final JLabel icon;
    private final JLabel actTitle;
    private final JRadioButton active;
    private final JTextField timeTxt;

    public HarderActivityHolder(int s) {
        setBorder(new CompoundBorder(new MatteBorder(3, 0, 0, 0, new Color(0, 0, 0)),
                new EmptyBorder(2, 2, 2, 2)));
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);

        icon = new JLabel("");
        icon.setIcon(new ImageIcon(new ImageIcon(LITACT).getImage().getScaledInstance(s * 1 / 15, s * 1 / 15, Image.SCALE_SMOOTH)));
        GridBagConstraints gbc_icon = new GridBagConstraints();
        gbc_icon.insets = new Insets(0, 0, 5, 5);
        gbc_icon.gridx = 1;
        gbc_icon.gridy = 0;
        add(icon, gbc_icon);

        actTitle = new JLabel("title");
        GridBagConstraints gbc_actTitle = new GridBagConstraints();
        gbc_actTitle.insets = new Insets(0, 0, 5, 5);
        gbc_actTitle.gridx = 1;
        gbc_actTitle.gridy = 1;
        add(actTitle, gbc_actTitle);

        JLabel spentTimelbl = new JLabel("Tiempo gastado");
        GridBagConstraints gbc_spentTimelbl = new GridBagConstraints();
        gbc_spentTimelbl.anchor = GridBagConstraints.EAST;
        gbc_spentTimelbl.insets = new Insets(0, 0, 5, 5);
        gbc_spentTimelbl.gridx = 3;
        gbc_spentTimelbl.gridy = 1;
        add(spentTimelbl, gbc_spentTimelbl);

        timeTxt = new JTextField();
        timeTxt.setEditable(false);
        timeTxt.setColumns(10);
        GridBagConstraints gbc_timeTxt = new GridBagConstraints();
        gbc_timeTxt.insets = new Insets(0, 0, 5, 0);
        gbc_timeTxt.fill = GridBagConstraints.BOTH;
        gbc_timeTxt.gridx = 4;
        gbc_timeTxt.gridy = 1;
        add(timeTxt, gbc_timeTxt);

        active = new JRadioButton("Activa?");
        active.setEnabled(false);
        GridBagConstraints gbc_active = new GridBagConstraints();
        gbc_active.insets = new Insets(0, 0, 0, 5);
        gbc_active.gridx = 1;
        gbc_active.gridy = 2;
        add(active, gbc_active);

    }

    public HarderActivityHolder(Activity object, int s) throws ActivityException {
        this(s);
        if (object == null)
        	throw new ActivityException("Se recomienda que cree actividades");
        
        actTitle.setText(object.getTitle());
        timeTxt.setText(object.getTimed() + "");
        active.setEnabled(object.getEnd() == null);
    }

}
