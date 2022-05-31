package com.uniandes.lithub.view.right.acts;

import com.uniandes.lithub.controller.SavedProjectInfo;
import com.uniandes.lithub.model.Activity;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.AbstractMap;
import java.util.Map;

@SuppressWarnings("serial")
public class ActivityHolder extends JPanel implements ActionListener {

    private static final String STP = "stop";
    private static final String PS = "pause";
    private static final String RSM = "resume";

    private final JTextField titletxt;
    private final JTextField starttxt;
    private final JTextField endtxt;
    private final JTextField drtntxt;
    private final JTextArea desctxt;
    private final JPopupMenu popupMenu;
    private ActivityContainer model;
    private Map.Entry<Activity, Boolean> data;

    public ActivityHolder() {

        popupMenu = new JPopupMenu();
        JMenuItem stoper = new JMenuItem("Detener actividad");
        stoper.setActionCommand(STP);
        stoper.addActionListener(this);
        JMenuItem pauser = new JMenuItem("Pausa la actividad");
        pauser.setActionCommand(PS);
        pauser.addActionListener(this);
        JMenuItem resumer = new JMenuItem("Despausa la actividad");
        resumer.setActionCommand(RSM);
        resumer.addActionListener(this);

        popupMenu.add(resumer);
        popupMenu.add(pauser);
        popupMenu.add(stoper);
        add(popupMenu);

        setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new CompoundBorder(
                new LineBorder(Color.BLACK, 2, true), BorderFactory.createDashedBorder(Color.BLACK, 2, 2))));

        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0};
        gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
        gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        setLayout(gbl_panel);

        JLabel titlelbl = new JLabel("Titulo");
        GridBagConstraints gbc_titlelbl = new GridBagConstraints();
        gbc_titlelbl.anchor = GridBagConstraints.NORTHEAST;
        gbc_titlelbl.insets = new Insets(0, 0, 5, 5);
        gbc_titlelbl.gridx = 0;
        gbc_titlelbl.gridy = 0;
        add(titlelbl, gbc_titlelbl);

        titletxt = new JTextField();
        titletxt.setEditable(false);
        titletxt.setColumns(10);
        titletxt.setBorder(null);
        GridBagConstraints gbc_titletxt = new GridBagConstraints();
        gbc_titletxt.fill = GridBagConstraints.HORIZONTAL;
        gbc_titletxt.gridwidth = 3;
        gbc_titletxt.insets = new Insets(0, 0, 5, 0);
        gbc_titletxt.gridx = 1;
        gbc_titletxt.gridy = 0;
        add(titletxt, gbc_titletxt);

        JLabel desclbl = new JLabel("Descripcion");
        GridBagConstraints gbc_desclbl = new GridBagConstraints();
        gbc_desclbl.anchor = GridBagConstraints.NORTHEAST;
        gbc_desclbl.insets = new Insets(0, 0, 5, 5);
        gbc_desclbl.gridx = 0;
        gbc_desclbl.gridy = 1;
        add(desclbl, gbc_desclbl);

        desctxt = new JTextArea("");
        desctxt.setEditable(false);
        desctxt.setBorder(null);
        GridBagConstraints gbc_desctxt = new GridBagConstraints();
        gbc_desctxt.fill = GridBagConstraints.HORIZONTAL;
        gbc_desctxt.gridwidth = 3;
        gbc_desctxt.insets = new Insets(0, 0, 5, 0);
        gbc_desctxt.gridx = 1;
        gbc_desctxt.gridy = 1;
        add(desctxt, gbc_desctxt);

        JLabel startlbl = new JLabel("Fecha Inicio");
        GridBagConstraints gbc_startlbl = new GridBagConstraints();
        gbc_startlbl.anchor = GridBagConstraints.NORTHEAST;
        gbc_startlbl.insets = new Insets(0, 0, 5, 5);
        gbc_startlbl.gridx = 0;
        gbc_startlbl.gridy = 2;
        add(startlbl, gbc_startlbl);

        starttxt = new JTextField();
        starttxt.setEditable(false);
        starttxt.setColumns(10);
        starttxt.setBorder(null);
        GridBagConstraints gbc_starttxt = new GridBagConstraints();
        gbc_starttxt.fill = GridBagConstraints.HORIZONTAL;
        gbc_starttxt.insets = new Insets(0, 0, 5, 5);
        gbc_starttxt.gridx = 1;
        gbc_starttxt.gridy = 2;
        add(starttxt, gbc_starttxt);

        JLabel endlbl = new JLabel("Fecha Fin");
        GridBagConstraints gbc_endlbl = new GridBagConstraints();
        gbc_endlbl.anchor = GridBagConstraints.NORTHEAST;
        gbc_endlbl.insets = new Insets(0, 0, 5, 5);
        gbc_endlbl.gridx = 2;
        gbc_endlbl.gridy = 2;
        add(endlbl, gbc_endlbl);

        endtxt = new JTextField();
        endtxt.setEditable(false);
        endtxt.setColumns(10);
        endtxt.setBorder(null);
        GridBagConstraints gbc_endtxt = new GridBagConstraints();
        gbc_endtxt.fill = GridBagConstraints.HORIZONTAL;
        gbc_endtxt.insets = new Insets(0, 0, 5, 0);
        gbc_endtxt.gridx = 3;
        gbc_endtxt.gridy = 2;
        add(endtxt, gbc_endtxt);

        JLabel drtnlbl = new JLabel("Duracion");
        GridBagConstraints gbc_drtnlbl = new GridBagConstraints();
        gbc_drtnlbl.anchor = GridBagConstraints.NORTHEAST;
        gbc_drtnlbl.insets = new Insets(0, 0, 5, 5);
        gbc_drtnlbl.gridx = 0;
        gbc_drtnlbl.gridy = 3;
        add(drtnlbl, gbc_drtnlbl);

        drtntxt = new JTextField();
        drtntxt.setEditable(false);
        drtntxt.setColumns(10);
        drtntxt.setBorder(null);
        GridBagConstraints gbc_drtntxt = new GridBagConstraints();
        gbc_drtntxt.fill = GridBagConstraints.HORIZONTAL;
        gbc_drtntxt.insets = new Insets(0, 0, 5, 5);
        gbc_drtntxt.gridx = 1;
        gbc_drtntxt.gridy = 3;
        add(drtntxt, gbc_drtntxt);

    }

    public ActivityHolder(Map.Entry<Activity, Boolean> tuple, ActivityContainer ac) {
        this();
        this.model = ac;
        this.data = tuple;

        Activity key = data.getKey();
        this.titletxt.setText(key.getTitle());
        this.desctxt.setText(key.getDescription());
        this.starttxt.setText(SavedProjectInfo.DTF.print(key.getStart()));
        this.drtntxt.setText("" + (key.getTimed()));

        if (data.getValue()) {
            setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new CompoundBorder(
                    new LineBorder(Color.RED, 2, true), BorderFactory.createDashedBorder(Color.RED, 2, 2))));
            this.endtxt.setText("No aun");
        } else
            this.endtxt.setText(SavedProjectInfo.DTF.print(key.getEnd()));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger() && data.getValue())
                    popupMenu.show(ActivityHolder.this, e.getX(), e.getY());
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String k = e.getActionCommand();
        switch (k) {
            case PS -> model.alterStopwatch(data.getKey(), 'p');
            case RSM -> model.alterStopwatch(data.getKey(), 'r');
            case STP -> {
                this.model.triEndAct(this.data.getKey());
                setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new CompoundBorder(
                        new LineBorder(Color.BLACK, 2, true), BorderFactory.createDashedBorder(Color.BLACK, 2, 2))));
                this.data = new AbstractMap.SimpleEntry<Activity, Boolean>(this.data.getKey(), false);
            }
            default -> {
            }
        }
        revalidate();
        repaint();
    }
}
