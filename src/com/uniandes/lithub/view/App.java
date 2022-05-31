package com.uniandes.lithub.view;

import com.uniandes.lithub.controller.LitController;
import com.uniandes.lithub.exceptions.ActivityException;
import com.uniandes.lithub.exceptions.IllegalRemovalException;
import com.uniandes.lithub.exceptions.MemberException;
import com.uniandes.lithub.exceptions.ProjectException;
import com.uniandes.lithub.model.Activity;
import com.uniandes.lithub.model.Participant;
import com.uniandes.lithub.model.Project;
import com.uniandes.lithub.view.builder.BuildProj;
import com.uniandes.lithub.view.extended.StructureInfo;
import com.uniandes.lithub.view.left.InterestInfo;
import com.uniandes.lithub.view.right.BasicInfo;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Set;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class App extends JFrame implements ActionListener {

    private final LitController model;
    private BasicInfo basicInfo;
    private InterestInfo interestInfo;
    private final JTextField projfield;
    private JButton expandCollapseBtn;

    public App(LitController controller) {
        setIconImage(new ImageIcon("Local_Information_Tracker/img_src/logo.png").getImage());

        this.model = controller;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JSplitPane overview = new JSplitPane();
        interestInfo = new InterestInfo();
        overview.setLeftComponent(interestInfo);
        basicInfo = new BasicInfo();
        overview.setRightComponent(basicInfo);
        overview.setResizeWeight(0.5);
        contentPane.add(overview, BorderLayout.CENTER);

        JPanel header = new JPanel();
        header.setBorder(new EmptyBorder(2, 10, 5, 10));
        contentPane.add(header, BorderLayout.NORTH);
        GridBagLayout gbl_header = new GridBagLayout();
        gbl_header.columnWidths = new int[]{77, 0, 0, 0};
        gbl_header.rowHeights = new int[]{0, 0, 0};
        gbl_header.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0};
        gbl_header.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        header.setLayout(gbl_header);

        JLabel titlelbl = new JLabel("Proyecto:");
        GridBagConstraints gbc_titlelbl = new GridBagConstraints();
        gbc_titlelbl.anchor = GridBagConstraints.EAST;
        gbc_titlelbl.insets = new Insets(0, 0, 5, 5);
        gbc_titlelbl.gridx = 0;
        gbc_titlelbl.gridy = 0;
        header.add(titlelbl, gbc_titlelbl);

        projfield = new JTextField();
        projfield.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getExtendedKeyCode() == KeyEvent.VK_ENTER)
                    searchedProj();
            }
        });
        projfield.setBorder(new EmptyBorder(2, 10, 5, 10));
        GridBagConstraints gbc_projfield = new GridBagConstraints();
        gbc_projfield.gridwidth = 2;
        gbc_projfield.insets = new Insets(0, 0, 0, 5);
        gbc_projfield.fill = GridBagConstraints.HORIZONTAL;
        gbc_projfield.gridx = 0;
        gbc_projfield.gridy = 1;
        header.add(projfield, gbc_projfield);
        projfield.setColumns(10);

        JButton srchBtn = new JButton("S");
        srchBtn.addActionListener(this);
        GridBagConstraints gbc_srchBtn = new GridBagConstraints();
        gbc_srchBtn.insets = new Insets(0, 0, 0, 5);
        gbc_srchBtn.fill = GridBagConstraints.VERTICAL;
        gbc_srchBtn.gridx = 2;
        gbc_srchBtn.gridy = 1;
        header.add(srchBtn, gbc_srchBtn);

        JButton newBtn = new JButton("N");
        newBtn.addActionListener(this);
        GridBagConstraints gbc_newBtn = new GridBagConstraints();
        gbc_newBtn.fill = GridBagConstraints.VERTICAL;
        gbc_newBtn.gridx = 3;
        gbc_newBtn.gridy = 1;
        header.add(newBtn, gbc_newBtn);

        JPanel containerExpandable = new JPanel();
        contentPane.add(containerExpandable, BorderLayout.EAST);
        containerExpandable.setLayout(new BorderLayout(0, 0));

        StructureInfo jxcp = new StructureInfo();
        expandCollapseBtn = new JButton("<");
        expandCollapseBtn.addActionListener(this);
        containerExpandable.add(expandCollapseBtn, BorderLayout.WEST);


        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                try {
                    model.quitExecution();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().getName(),
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        pack();
        setLocationRelativeTo(null);
    }

    public void buildProj(String name, String description, Set<String> types, String ownName, String ownEmail) {
        try {
            model.newProject(name, description, types, ownName, ownEmail, "", "");
        } catch (ProjectException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().getName(),
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void buildAct(String proj, String member, String title, Set<String> typesBrute, String desc) {
        try {
            model.newActivity(proj, member, title, typesBrute, desc);
        } catch (ProjectException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().getName(),
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void buildMember(String project, String name, String email) {
        try {
            model.newMember(project, name, email);
        } catch (ProjectException | MemberException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().getName(),
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void endAct(Project project, Activity key) {
        try {
            model.endActivity(project, key);
        } catch (ProjectException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().getName(),
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void delMember(Project project, Participant selected) {
        try {
            model.remMember(project, selected);
        } catch (ProjectException | IllegalRemovalException | MemberException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().getName(),
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void loadAll(Project project, Participant selected) {
        model.logActMember(project, selected);
    }

    public void endProj(Project project) {
        try {
            model.endProject(project);
        } catch (ProjectException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().getName(),
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void alterStopwatch(Project project, Activity key, char c) {
        try {
            model.changeStatusStopwatch(project, key, c);
        } catch (ProjectException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "S" -> searchedProj();
            case "N" -> new BuildProj(App.this);
            case "E" -> {

            }
        }
    }

    private void searchedProj() {
        String pName = projfield.getText();
        try {
            Project p = model.getProj(pName);
            projfield.setText("");
            basicInfo = new BasicInfo(this, p);
            interestInfo = new InterestInfo(pName, model.interestInformation(pName));
            expandCollapseBtn.setVisible(true);
        } catch (ActivityException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Proyecto sin actividades",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (ProjectException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), ex.getClass().getSimpleName(),
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
