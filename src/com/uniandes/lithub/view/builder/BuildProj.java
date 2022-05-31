package com.uniandes.lithub.view.builder;

import com.uniandes.lithub.view.App;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class BuildProj extends JDialog {

    private final JTextField projNameTxt;
    private final JTextField ownNameTxt;
    private final JTextField ownEmailTxt;
    private final JTextField projTypeTxt;
    private final Set<String> typesBrute = new HashSet<String>();
    private final JTextArea projDescTxt;
    private final JLabel counterTypes;

    public BuildProj(App facade) {
        JPanel contentPanel = new JPanel();
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        GridBagLayout gbl_contentPanel = new GridBagLayout();
        gbl_contentPanel.columnWidths = new int[]{0, 0};
        gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
        gbl_contentPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        contentPanel.setLayout(gbl_contentPanel);

        JLabel projTitle = new JLabel("Proyecto");
        GridBagConstraints gbc_projTitle = new GridBagConstraints();
        gbc_projTitle.anchor = GridBagConstraints.WEST;
        gbc_projTitle.insets = new Insets(0, 0, 5, 0);
        gbc_projTitle.gridx = 0;
        gbc_projTitle.gridy = 0;
        contentPanel.add(projTitle, gbc_projTitle);

        JPanel projPanel = new JPanel();
        GridBagConstraints gbc_projPanel = new GridBagConstraints();
        gbc_projPanel.insets = new Insets(0, 0, 5, 0);
        gbc_projPanel.fill = GridBagConstraints.BOTH;
        gbc_projPanel.gridx = 0;
        gbc_projPanel.gridy = 1;
        contentPanel.add(projPanel, gbc_projPanel);

        GridBagLayout gbl_projPanel = new GridBagLayout();
        gbl_projPanel.columnWidths = new int[]{0, 0, 0, 0, 0};
        gbl_projPanel.rowHeights = new int[]{0, 145, 0, 0};
        gbl_projPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        gbl_projPanel.rowWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
        projPanel.setLayout(gbl_projPanel);

        JLabel projNamelbl = new JLabel("Nombre");
        GridBagConstraints gbc_projNamelbl = new GridBagConstraints();
        gbc_projNamelbl.anchor = GridBagConstraints.EAST;
        gbc_projNamelbl.insets = new Insets(0, 0, 5, 5);
        gbc_projNamelbl.gridx = 1;
        gbc_projNamelbl.gridy = 0;
        projPanel.add(projNamelbl, gbc_projNamelbl);

        projNameTxt = new JTextField();
        GridBagConstraints gbc_projNameTxt = new GridBagConstraints();
        gbc_projNameTxt.gridwidth = 2;
        gbc_projNameTxt.insets = new Insets(0, 0, 5, 0);
        gbc_projNameTxt.fill = GridBagConstraints.HORIZONTAL;
        gbc_projNameTxt.gridx = 2;
        gbc_projNameTxt.gridy = 0;
        projPanel.add(projNameTxt, gbc_projNameTxt);
        projNameTxt.setColumns(10);

        JLabel projDesclbl = new JLabel("Descripcion");
        GridBagConstraints gbc_projDesclbl = new GridBagConstraints();
        gbc_projDesclbl.anchor = GridBagConstraints.EAST;
        gbc_projDesclbl.insets = new Insets(0, 0, 5, 5);
        gbc_projDesclbl.gridx = 1;
        gbc_projDesclbl.gridy = 1;
        projPanel.add(projDesclbl, gbc_projDesclbl);

        JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.gridwidth = 2;
        gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridx = 2;
        gbc_scrollPane.gridy = 1;
        projPanel.add(scrollPane, gbc_scrollPane);

        projDescTxt = new JTextArea();
        projDescTxt.setWrapStyleWord(true);
        scrollPane.setViewportView(projDescTxt);

        counterTypes = new JLabel("0");
        GridBagConstraints gbc_counterTypes = new GridBagConstraints();
        gbc_counterTypes.insets = new Insets(0, 0, 0, 5);
        gbc_counterTypes.gridx = 0;
        gbc_counterTypes.gridy = 2;
        projPanel.add(counterTypes, gbc_counterTypes);

        JLabel projTypelbl = new JLabel("Tipos");
        GridBagConstraints gbc_projTypelbl = new GridBagConstraints();
        gbc_projTypelbl.anchor = GridBagConstraints.EAST;
        gbc_projTypelbl.insets = new Insets(0, 0, 0, 5);
        gbc_projTypelbl.gridx = 1;
        gbc_projTypelbl.gridy = 2;
        projPanel.add(projTypelbl, gbc_projTypelbl);

        projTypeTxt = new JTextField();
        GridBagConstraints gbc_projTypeTxt = new GridBagConstraints();
        gbc_projTypeTxt.fill = GridBagConstraints.HORIZONTAL;
        gbc_projTypeTxt.insets = new Insets(0, 0, 0, 5);
        gbc_projTypeTxt.gridx = 2;
        gbc_projTypeTxt.gridy = 2;
        projPanel.add(projTypeTxt, gbc_projTypeTxt);
        projTypeTxt.setColumns(10);

        JButton addBtn = new JButton("+");
        addBtn.addActionListener(e -> {
            typesBrute.add(projTypeTxt.getText());
            projTypeTxt.setText("");
            counterTypes.setText("" + typesBrute.size());
        });
        GridBagConstraints gbc_addBtn = new GridBagConstraints();
        gbc_addBtn.gridx = 3;
        gbc_addBtn.gridy = 2;
        projPanel.add(addBtn, gbc_addBtn);

        JLabel ownTitle = new JLabel("Duenio");
        GridBagConstraints gbc_ownTitle = new GridBagConstraints();
        gbc_ownTitle.anchor = GridBagConstraints.WEST;
        gbc_ownTitle.insets = new Insets(0, 0, 5, 0);
        gbc_ownTitle.gridx = 0;
        gbc_ownTitle.gridy = 2;
        contentPanel.add(ownTitle, gbc_ownTitle);

        JPanel ownPanel = new JPanel();
        GridBagConstraints gbc_ownPanel = new GridBagConstraints();
        gbc_ownPanel.fill = GridBagConstraints.BOTH;
        gbc_ownPanel.gridx = 0;
        gbc_ownPanel.gridy = 3;
        contentPanel.add(ownPanel, gbc_ownPanel);

        GridBagLayout gbl_ownPanel = new GridBagLayout();
        gbl_ownPanel.columnWidths = new int[]{0, 0, 0, 0};
        gbl_ownPanel.rowHeights = new int[]{0, 0, 0};
        gbl_ownPanel.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
        gbl_ownPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        ownPanel.setLayout(gbl_ownPanel);

        JLabel ownNamelbl = new JLabel("Nombre");
        GridBagConstraints gbc_ownNamelbl = new GridBagConstraints();
        gbc_ownNamelbl.insets = new Insets(0, 0, 5, 5);
        gbc_ownNamelbl.gridx = 1;
        gbc_ownNamelbl.gridy = 0;
        ownPanel.add(ownNamelbl, gbc_ownNamelbl);

        ownNameTxt = new JTextField();
        GridBagConstraints gbc_ownNameTxt = new GridBagConstraints();
        gbc_ownNameTxt.insets = new Insets(0, 0, 5, 0);
        gbc_ownNameTxt.fill = GridBagConstraints.HORIZONTAL;
        gbc_ownNameTxt.gridx = 2;
        gbc_ownNameTxt.gridy = 0;
        ownPanel.add(ownNameTxt, gbc_ownNameTxt);

        ownNameTxt.setColumns(10);
        JLabel ownEmaillbl = new JLabel("E-mail");
        GridBagConstraints gbc_ownEmaillbl = new GridBagConstraints();
        gbc_ownEmaillbl.insets = new Insets(0, 0, 0, 5);
        gbc_ownEmaillbl.gridx = 1;
        gbc_ownEmaillbl.gridy = 1;
        ownPanel.add(ownEmaillbl, gbc_ownEmaillbl);

        ownEmailTxt = new JTextField();
        GridBagConstraints gbc_ownEmailTxt = new GridBagConstraints();
        gbc_ownEmailTxt.fill = GridBagConstraints.HORIZONTAL;
        gbc_ownEmailTxt.gridx = 2;
        gbc_ownEmailTxt.gridy = 1;
        ownPanel.add(ownEmailTxt, gbc_ownEmailTxt);
        ownEmailTxt.setColumns(10);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        JButton okBtn = new JButton("OK");
        okBtn.addActionListener(e -> {
            String[] project = {projNameTxt.getText(), projDescTxt.getText()};
            String[] owner = {ownNameTxt.getText(), ownEmailTxt.getText()};
            if (!(Arrays.equals(project, new String[]{"", ""}) || typesBrute.size() < 2
                    || Arrays.equals(owner, new String[]{"", ""}))) {
                facade.buildProj(project[0], project[1], typesBrute, owner[0], owner[1]);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Llene todos los campos", "Campo(s) vacio(s)",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        okBtn.setActionCommand("OK");
        buttonPane.add(okBtn);
        getRootPane().setDefaultButton(okBtn);

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

}
