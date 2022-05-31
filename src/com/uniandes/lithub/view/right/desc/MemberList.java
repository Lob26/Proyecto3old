package com.uniandes.lithub.view.right.desc;

import com.uniandes.lithub.model.Participant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class MemberList extends JList<Participant> implements ActionListener {

    private static final String NEW = "new";
    private static final String GET = "get";
    private static final String DEL = "del";
    private static final String LOA = "loa";
    private final JPopupMenu popmenu = new JPopupMenu();
    private final DefaultListModel<Participant> dlm = new DefaultListModel<Participant>();
    private DescriptionContainer subFac;

    public MemberList() {
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setSelectionBackground(Color.RED);
        setCellRenderer(new ListRender());
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger() && subFac != null) {
                    MemberList.this.subFac.trigger(getFocusedItem(e));
                    popmenu.show(MemberList.this, e.getX(), e.getY());
                }
            }
        });
        setLayoutOrientation(JList.HORIZONTAL_WRAP);
        setBorder(null);

        JMenuItem getAct = new JMenuItem("Dar actividades");
        getAct.setActionCommand(GET);
        getAct.addActionListener(this);
        JMenuItem newAct = new JMenuItem("Crear actividad");
        newAct.setActionCommand(NEW);
        newAct.addActionListener(this);
        JMenuItem remMem = new JMenuItem("Eliminar miembro");
        remMem.setActionCommand(DEL);
        remMem.addActionListener(this);
        JMenuItem loadAll = new JMenuItem("Cargar todo al proyecto");
        loadAll.setActionCommand(LOA);
        loadAll.addActionListener(this);
        popmenu.add(getAct);
        popmenu.add(newAct);
        popmenu.add(remMem);
        popmenu.add(loadAll);

        setModel(dlm);
    }

    public MemberList(DescriptionContainer dc) {
        this();
        this.subFac = dc;
    }

    protected Participant getFocusedItem(MouseEvent e) {
        Object c = e.getSource();
        if (c instanceof MemberList) {
            MemberList list = (MemberList) c;
            int index = list.locationToIndex(e.getPoint());
            if (index >= 0) {
                return list.getModel().getElementAt(index);
            }
        }
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String k = e.getActionCommand();
        switch (k) {
            case GET -> this.subFac.trigGetAct();
            case NEW -> this.subFac.triNewAct();
            case DEL -> this.subFac.triDelMem();
            case LOA -> this.subFac.triLoad();
        }
    }

    public void addToModel(Participant member) {
        this.dlm.addElement(member);
    }

    public void cleanModel() {
        this.dlm.clear();
    }

}

@SuppressWarnings("serial")
class ListRender extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                                                  boolean cellHasFocus) {
        JComponent c = (JComponent) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        c.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        if (value instanceof Participant) {
            Participant obj = (Participant) value;
            setText(obj.getName());
        }

        return c;
    }

}
