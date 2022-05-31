package com.uniandes.lithub.view.right.desc;

import com.uniandes.lithub.model.Participant;
import com.uniandes.lithub.view.right.BasicInfo;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;

@SuppressWarnings("serial")
public class DescriptionContainer extends JPanel {
    private final JTextField starttxt;
    private final ProjList nametxt;
    private final JTextField endtxt;
    private final JTextArea desctxt;
    private final MemberList memlst;
    private final MemberList ownlst;

    private BasicInfo subFac;
    private Participant selected;

    public DescriptionContainer() {
        GridBagLayout gblGeneral = new GridBagLayout();
        gblGeneral.columnWidths = new int[]{0, 0};
        gblGeneral.rowHeights = new int[]{0, 0, 0};
        gblGeneral.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gblGeneral.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        setLayout(gblGeneral);

        JLabel title = new JLabel("Descripcion");
        title.setBorder(new EmptyBorder(10, 20, 7, 0));
        GridBagConstraints gbc_title = new GridBagConstraints();
        gbc_title.anchor = GridBagConstraints.WEST;
        gbc_title.insets = new Insets(0, 0, 5, 0);
        gbc_title.gridx = 0;
        gbc_title.gridy = 0;
        add(title, gbc_title);

        JPanel panel = new JPanel();
        panel.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new LineBorder(new Color(0, 0, 0), 3, true)));
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 1;
        add(panel, gbc_panel);
        GridBagLayout gblSub = new GridBagLayout();
        gblSub.columnWidths = new int[]{0, 0, 0, 0, 0};
        gblSub.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
        gblSub.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
        gblSub.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        panel.setLayout(gblSub);

        JLabel namelbl = new JLabel("Nombre");
        GridBagConstraints gbc_namelbl = new GridBagConstraints();
        gbc_namelbl.insets = new Insets(0, 0, 5, 5);
        gbc_namelbl.anchor = GridBagConstraints.NORTHEAST;
        gbc_namelbl.gridx = 0;
        gbc_namelbl.gridy = 0;
        panel.add(namelbl, gbc_namelbl);

        nametxt = new ProjList(this);
        nametxt.setEditable(false);
        nametxt.setBorder(null);
        nametxt.setColumns(10);
        GridBagConstraints gbc_nametxt = new GridBagConstraints();
        gbc_nametxt.gridwidth = 3;
        gbc_nametxt.insets = new Insets(0, 0, 5, 0);
        gbc_nametxt.fill = GridBagConstraints.HORIZONTAL;
        gbc_nametxt.gridx = 1;
        gbc_nametxt.gridy = 0;
        panel.add(nametxt, gbc_nametxt);

        JLabel desclbl = new JLabel("Descripcion");
        GridBagConstraints gbc_desclbl = new GridBagConstraints();
        gbc_desclbl.anchor = GridBagConstraints.NORTHEAST;
        gbc_desclbl.insets = new Insets(0, 0, 5, 5);
        gbc_desclbl.gridx = 0;
        gbc_desclbl.gridy = 1;
        panel.add(desclbl, gbc_desclbl);

        desctxt = new JTextArea("");
        desctxt.setEditable(false);
        desctxt.setBorder(null);
        GridBagConstraints gbc_desctxt = new GridBagConstraints();
        gbc_desctxt.fill = GridBagConstraints.HORIZONTAL;
        gbc_desctxt.gridwidth = 3;
        gbc_desctxt.insets = new Insets(0, 0, 5, 0);
        gbc_desctxt.gridx = 1;
        gbc_desctxt.gridy = 1;
        panel.add(desctxt, gbc_desctxt);

        JLabel startlbl = new JLabel("Fecha Inicio");
        GridBagConstraints gbc_startlbl = new GridBagConstraints();
        gbc_startlbl.anchor = GridBagConstraints.NORTHEAST;
        gbc_startlbl.insets = new Insets(0, 0, 5, 5);
        gbc_startlbl.gridx = 0;
        gbc_startlbl.gridy = 2;
        panel.add(startlbl, gbc_startlbl);

        starttxt = new JTextField();
        starttxt.setEditable(false);
        starttxt.setBorder(null);
        GridBagConstraints gbc_starttxt = new GridBagConstraints();
        gbc_starttxt.insets = new Insets(0, 0, 5, 5);
        gbc_starttxt.fill = GridBagConstraints.HORIZONTAL;
        gbc_starttxt.gridx = 1;
        gbc_starttxt.gridy = 2;
        panel.add(starttxt, gbc_starttxt);
        starttxt.setColumns(10);

        JLabel endlbl = new JLabel("Fecha Fin");
        GridBagConstraints gbc_endlbl = new GridBagConstraints();
        gbc_endlbl.anchor = GridBagConstraints.NORTHEAST;
        gbc_endlbl.insets = new Insets(0, 0, 5, 5);
        gbc_endlbl.gridx = 2;
        gbc_endlbl.gridy = 2;
        panel.add(endlbl, gbc_endlbl);

        endtxt = new JTextField();
        endtxt.setEditable(false);
        endtxt.setBorder(null);
        endtxt.setColumns(10);
        GridBagConstraints gbc_endtxt = new GridBagConstraints();
        gbc_endtxt.insets = new Insets(0, 0, 5, 0);
        gbc_endtxt.fill = GridBagConstraints.HORIZONTAL;
        gbc_endtxt.gridx = 3;
        gbc_endtxt.gridy = 2;
        panel.add(endtxt, gbc_endtxt);

        JLabel ownlbl = new JLabel("Duenio");
        GridBagConstraints gbc_ownlbl = new GridBagConstraints();
        gbc_ownlbl.anchor = GridBagConstraints.NORTHEAST;
        gbc_ownlbl.insets = new Insets(0, 0, 5, 5);
        gbc_ownlbl.gridx = 0;
        gbc_ownlbl.gridy = 3;
        panel.add(ownlbl, gbc_ownlbl);

        ownlst = new MemberList(this);
        GridBagConstraints gbc_ownlst = new GridBagConstraints();
        gbc_ownlst.insets = new Insets(0, 0, 5, 0);
        gbc_ownlst.gridwidth = 3;
        gbc_ownlst.fill = GridBagConstraints.HORIZONTAL;
        gbc_ownlst.gridx = 1;
        gbc_ownlst.gridy = 3;
        panel.add(ownlst, gbc_ownlst);

        JLabel memblbl = new JLabel("Participantes");
        GridBagConstraints gbc_memblbl = new GridBagConstraints();
        gbc_memblbl.anchor = GridBagConstraints.NORTHEAST;
        gbc_memblbl.insets = new Insets(0, 0, 0, 5);
        gbc_memblbl.gridx = 0;
        gbc_memblbl.gridy = 4;
        panel.add(memblbl, gbc_memblbl);

        memlst = new MemberList(this);

        GridBagConstraints gbc_memlst = new GridBagConstraints();
        gbc_memlst.gridwidth = 3;
        gbc_memlst.insets = new Insets(0, 0, 0, 5);
        gbc_memlst.fill = GridBagConstraints.BOTH;
        gbc_memlst.gridx = 1;
        gbc_memlst.gridy = 4;
        panel.add(memlst, gbc_memlst);
    }

    public DescriptionContainer(BasicInfo basicInfo) {
        this();
        this.subFac = basicInfo;
    }

    public void display(List<Participant> allMembers, String... data) {
        cleanPanel();
        this.nametxt.setText(data[0]);
        this.desctxt.setText(data[1]);
        this.starttxt.setText(data[2]);
        this.endtxt.setText(data[3]);
        ownlst.addToModel(allMembers.remove(0));
        allMembers.forEach(memlst::addToModel);
    }

    private void cleanPanel() {
        ownlst.cleanModel();
        memlst.cleanModel();
    }

    public void trigger(Participant focused) {
        this.selected = focused;
    }

    public void trigGetAct() {
        if (selected != null)
            this.subFac.setActs(selected);
    }

    public void triNewAct() {
        if (selected != null)
            this.subFac.buildAct(selected);
    }

    public void triDelMem() {
        if (selected != null)
            this.subFac.removeMember(selected);
    }

    public void triLoad() {
        if (selected != null)
            this.subFac.loadAll(selected);
    }

    public void trigNewMem() {
        this.subFac.newMember();
    }

    public void trigEndPrj() {
        this.subFac.endProject();

    }

}