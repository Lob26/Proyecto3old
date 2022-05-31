package com.uniandes.lithub.view.builder;

import com.uniandes.lithub.model.Participant;
import com.uniandes.lithub.model.Project;
import com.uniandes.lithub.view.App;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class BuildAct extends JDialog {

    private final JTextField actTypeTxt;
    private final JTextField memNameTxt;
    private final JTextField projTitleTxt;
    private final JTextField actTitleTxt;
    private final Set<String> typesBrute = new HashSet<String>();
    private final JTextArea actDesctTxt;

    public BuildAct(App facade, String member, String proj) {
        JPanel contentPanel = new JPanel();
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        GridBagLayout gbl_contentPanel = new GridBagLayout();
        gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0};
        gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
        gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
        gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        contentPanel.setLayout(gbl_contentPanel);

        JLabel projNamelbl = new JLabel("Nombre del Projecto");
        GridBagConstraints gbc_projNamelbl = new GridBagConstraints();
        gbc_projNamelbl.insets = new Insets(0, 0, 5, 5);
        gbc_projNamelbl.anchor = GridBagConstraints.EAST;
        gbc_projNamelbl.gridx = 0;
        gbc_projNamelbl.gridy = 0;
        contentPanel.add(projNamelbl, gbc_projNamelbl);

        projTitleTxt = new JTextField(proj);
        projTitleTxt.setEditable(false);
        projTitleTxt.setColumns(10);
        GridBagConstraints gbc_projTitleTxt = new GridBagConstraints();
        gbc_projTitleTxt.gridwidth = 2;
        gbc_projTitleTxt.insets = new Insets(0, 0, 5, 0);
        gbc_projTitleTxt.fill = GridBagConstraints.HORIZONTAL;
        gbc_projTitleTxt.gridx = 1;
        gbc_projTitleTxt.gridy = 0;
        contentPanel.add(projTitleTxt, gbc_projTitleTxt);

        JLabel memNamelbl = new JLabel("Nombre del Miembro");
        GridBagConstraints gbc_memNamelbl = new GridBagConstraints();
        gbc_memNamelbl.anchor = GridBagConstraints.EAST;
        gbc_memNamelbl.insets = new Insets(0, 0, 5, 5);
        gbc_memNamelbl.gridx = 0;
        gbc_memNamelbl.gridy = 1;
        contentPanel.add(memNamelbl, gbc_memNamelbl);

        memNameTxt = new JTextField(member);
        memNameTxt.setEditable(false);
        memNameTxt.setColumns(10);
        GridBagConstraints gbc_memNameTxt = new GridBagConstraints();
        gbc_memNameTxt.gridwidth = 2;
        gbc_memNameTxt.insets = new Insets(0, 0, 5, 0);
        gbc_memNameTxt.fill = GridBagConstraints.HORIZONTAL;
        gbc_memNameTxt.gridx = 1;
        gbc_memNameTxt.gridy = 1;
        contentPanel.add(memNameTxt, gbc_memNameTxt);

        JLabel actTitlelbl = new JLabel("Titulo de la actividad");
        GridBagConstraints gbc_actTitlelbl = new GridBagConstraints();
        gbc_actTitlelbl.anchor = GridBagConstraints.EAST;
        gbc_actTitlelbl.insets = new Insets(0, 0, 5, 5);
        gbc_actTitlelbl.gridx = 0;
        gbc_actTitlelbl.gridy = 2;
        contentPanel.add(actTitlelbl, gbc_actTitlelbl);

        actTitleTxt = new JTextField();
        actTitleTxt.setColumns(10);
        GridBagConstraints gbc_actTitleTxt = new GridBagConstraints();
        gbc_actTitleTxt.gridwidth = 2;
        gbc_actTitleTxt.insets = new Insets(0, 0, 5, 0);
        gbc_actTitleTxt.fill = GridBagConstraints.HORIZONTAL;
        gbc_actTitleTxt.gridx = 1;
        gbc_actTitleTxt.gridy = 2;
        contentPanel.add(actTitleTxt, gbc_actTitleTxt);

        JLabel actTypelbl = new JLabel("Tipos de la actividad");
        GridBagConstraints gbc_actTypelbl = new GridBagConstraints();
        gbc_actTypelbl.insets = new Insets(0, 0, 5, 5);
        gbc_actTypelbl.anchor = GridBagConstraints.EAST;
        gbc_actTypelbl.gridx = 0;
        gbc_actTypelbl.gridy = 3;
        contentPanel.add(actTypelbl, gbc_actTypelbl);

        actTypeTxt = new JTextField();
        GridBagConstraints gbc_actTypeTxt = new GridBagConstraints();
        gbc_actTypeTxt.insets = new Insets(0, 0, 5, 5);
        gbc_actTypeTxt.fill = GridBagConstraints.HORIZONTAL;
        gbc_actTypeTxt.gridx = 1;
        gbc_actTypeTxt.gridy = 3;
        contentPanel.add(actTypeTxt, gbc_actTypeTxt);
        actTypeTxt.setColumns(10);

        JButton addBtn = new JButton("+");
        addBtn.addActionListener(e -> {
            typesBrute.add(actTypeTxt.getText());
            actTypeTxt.setText("");
        });
        GridBagConstraints gbc_addBtn = new GridBagConstraints();
        gbc_addBtn.insets = new Insets(0, 0, 5, 0);
        gbc_addBtn.gridx = 2;
        gbc_addBtn.gridy = 3;
        contentPanel.add(addBtn, gbc_addBtn);

        JLabel actDesclbl = new JLabel("Descripcion del actividad");
        GridBagConstraints gbc_actDesclbl = new GridBagConstraints();
        gbc_actDesclbl.anchor = GridBagConstraints.EAST;
        gbc_actDesclbl.insets = new Insets(0, 0, 0, 5);
        gbc_actDesclbl.gridx = 0;
        gbc_actDesclbl.gridy = 4;
        contentPanel.add(actDesclbl, gbc_actDesclbl);

        actDesctTxt = new JTextArea();
        GridBagConstraints gbc_actDesctTxt = new GridBagConstraints();
        gbc_actDesctTxt.gridwidth = 2;
        gbc_actDesctTxt.fill = GridBagConstraints.BOTH;
        gbc_actDesctTxt.gridx = 1;
        gbc_actDesctTxt.gridy = 4;
        contentPanel.add(actDesctTxt, gbc_actDesctTxt);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton okBtn = new JButton("OK");
        okBtn.addActionListener(e -> {
            String title = actTitleTxt.getText();
            String desc = actDesctTxt.getText();
            if (!(title.equals("") || desc.equals("") || typesBrute.size() < 2)) {
                facade.buildAct(proj, member, title, typesBrute, desc);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Llene todos los campos", "Campo(s) vacio(s)",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        buttonPane.add(okBtn);
        getRootPane().setDefaultButton(okBtn);

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

}
