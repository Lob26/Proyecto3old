package com.uniandes.lithub.view.right.desc;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class ProjList extends JTextField implements ActionListener {

    private static final String NEW = "new";
    private static final String END = "end";
    private final JPopupMenu popmenu = new JPopupMenu();
    private DescriptionContainer subFac;

    public ProjList() {
        super();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger() && subFac != null) {
                    popmenu.show(ProjList.this, e.getX(), e.getY());
                }
            }
        });
        JMenuItem newMember = new JMenuItem("Nuevo participante");
        newMember.setActionCommand(NEW);
        newMember.addActionListener(this);
        JMenuItem endProj = new JMenuItem("Finalizar proyecto");
        endProj.setActionCommand(END);
        endProj.addActionListener(this);
        popmenu.add(newMember);
        popmenu.add(endProj);
    }

    public ProjList(DescriptionContainer dc) {
        this();
        this.subFac = dc;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String k = e.getActionCommand();
        switch (k) {
            case NEW -> this.subFac.trigNewMem();
            case END -> this.subFac.trigEndPrj();
        }
    }

}