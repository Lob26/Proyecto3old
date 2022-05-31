package com.uniandes.lithub.view.left.interest;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

@SuppressWarnings("serial")
public class TypeTimeHolder extends JPanel {
    private final JList<Map.Entry<String, Long>> list;

    public TypeTimeHolder() {
        setBorder(new CompoundBorder(new MatteBorder(3, 0, 0, 0, new Color(0, 0, 0)),
                new EmptyBorder(2, 2, 2, 2)));

        Map<String, Long> defo = new HashMap<String, Long>();
        defo.put("Test", 0L);
        Vector<Map.Entry<String, Long>> defoVect = new Vector<Map.Entry<String, Long>>(defo.entrySet());

        DefaultListCellRenderer dlcr = new DefaultListCellRenderer() {
            @SuppressWarnings("unchecked")
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                                                          boolean cellHasFocus) {

                PanelListComponent component = null;

                if (value instanceof Map.Entry) {
                    Map.Entry<String, Long> e = (Map.Entry<String, Long>) value;
                    component = new PanelListComponent(e.getKey(), e.getValue());
                }

                return component;
            }
        };
        setLayout(new GridLayout(0, 1, 0, 0));
        list = new JList<Map.Entry<String, Long>>(defoVect);
        list.setVisibleRowCount(2);
        add(list);

        this.list.setCellRenderer(dlcr);

    }

    public TypeTimeHolder(Map<String, Long> timeByType) {
        this();
        DefaultListModel<Map.Entry<String, Long>> dlm = new DefaultListModel<Map.Entry<String, Long>>();
        timeByType.entrySet().forEach(entry -> dlm.addElement(entry));

        this.list.setModel(dlm);
    }

}

@SuppressWarnings("serial")
class PanelListComponent extends JPanel {
    public PanelListComponent(String left, Long right) {
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0, 0, 0};
        gbl.rowHeights = new int[]{0, 0};
        gbl.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{1.0, Double.MIN_VALUE};
        setLayout(gbl);

        JLabel leftlbl = new JLabel(left);
        GridBagConstraints gbcleft = new GridBagConstraints();
        gbcleft.fill = GridBagConstraints.HORIZONTAL;
        gbcleft.insets = new Insets(0, 0, 0, 5);
        gbcleft.gridx = 0;
        gbcleft.gridy = 0;
        add(leftlbl, gbcleft);

        JLabel rightlbl = new JLabel(right.toString());
        rightlbl.setHorizontalAlignment(SwingConstants.TRAILING);
        GridBagConstraints gbcright = new GridBagConstraints();
        gbcright.fill = GridBagConstraints.HORIZONTAL;
        gbcright.gridx = 1;
        gbcright.gridy = 0;
        add(rightlbl, gbcright);
    }
}
