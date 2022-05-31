package com.uniandes.lithub.view.left.interest;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.Map;
import java.util.Vector;

@SuppressWarnings("serial")
public class ParticipantTimeHolder extends JPanel {
    private final JTable table;
    private final DefaultTableModel dtm;

    public ParticipantTimeHolder() {
        setBorder(new CompoundBorder(new MatteBorder(3, 0, 0, 0, new Color(0, 0, 0)),
                new EmptyBorder(2, 2, 2, 2)));
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0};
        gridBagLayout.rowHeights = new int[]{0, -144};
        gridBagLayout.columnWeights = new double[]{Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{Double.MIN_VALUE, 0.0};
        setLayout(gridBagLayout);

        dtm = new DefaultTableModel(0, 0);

        table = new JTable();
        table.setShowVerticalLines(false);
        table.setShowHorizontalLines(false);
        table.setPreferredSize(null);
        table.setPreferredScrollableViewportSize(new Dimension(450, 20));
        table.setEnabled(false);

        dtm.setColumnIdentifiers(new String[]{"Participante", "Tiempo dedicado"});
        JTableHeader jth = new JTableHeader(table.getColumnModel());
        GridBagConstraints gbc_jth = new GridBagConstraints();
        gbc_jth.anchor = GridBagConstraints.NORTH;
        gbc_jth.fill = GridBagConstraints.HORIZONTAL;
        gbc_jth.gridy = 0;
        gbc_jth.gridx = 0;
        add(jth, gbc_jth);
        jth.setTable(table);
        GridBagConstraints gbc_table = new GridBagConstraints();
        gbc_table.anchor = GridBagConstraints.NORTH;
        gbc_table.fill = GridBagConstraints.HORIZONTAL;
        gbc_table.gridy = 1;
        gbc_table.gridx = 0;
        add(table, gbc_table);

    }

    public ParticipantTimeHolder(Map<String, Long> timeByParticipant) {
        this();

        timeByParticipant.entrySet().forEach(entry -> {
            Vector<Object> row = new Vector<Object>(2);
            row.add(entry.getKey());
            row.add(entry.getValue());
            dtm.addRow(row);
        });

        table.setModel(dtm);
    }
}
