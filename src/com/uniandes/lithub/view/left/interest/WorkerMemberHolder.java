package com.uniandes.lithub.view.left.interest;

import com.uniandes.lithub.model.Participant;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;

@SuppressWarnings("serial")
public class WorkerMemberHolder extends JPanel {
    public static final String LITUSER = "Local_Information_Tracker/img_src/user.png";
    private final JLabel icon;
    private final JLabel memName;
    private final JTextField actNumtxt;

    public WorkerMemberHolder(int s) {
        setBorder(new CompoundBorder(new MatteBorder(3, 0, 0, 0, new Color(0, 0, 0)),
                new EmptyBorder(2, 2, 2, 2)));
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);

        icon = new JLabel("");
        icon.setText("");
        icon.setIcon(new ImageIcon(new ImageIcon(LITUSER).getImage().getScaledInstance(s * 1 / 15, s * 1 / 15, Image.SCALE_SMOOTH)));

        GridBagConstraints gbc_icon = new GridBagConstraints();
        gbc_icon.insets = new Insets(0, 0, 5, 5);
        gbc_icon.gridx = 1;
        gbc_icon.gridy = 0;
        add(icon, gbc_icon);

        memName = new JLabel("name");
        GridBagConstraints gbc_memName = new GridBagConstraints();
        gbc_memName.insets = new Insets(0, 0, 0, 5);
        gbc_memName.gridx = 1;
        gbc_memName.gridy = 1;
        add(memName, gbc_memName);

        JLabel actNumlbl = new JLabel("Numero de Actividades");
        GridBagConstraints gbc_actNumlbl = new GridBagConstraints();
        gbc_actNumlbl.insets = new Insets(0, 0, 0, 5);
        gbc_actNumlbl.anchor = GridBagConstraints.EAST;
        gbc_actNumlbl.gridx = 3;
        gbc_actNumlbl.gridy = 1;
        add(actNumlbl, gbc_actNumlbl);

        actNumtxt = new JTextField();
        actNumtxt.setEditable(false);
        GridBagConstraints gbc_actNumtxt = new GridBagConstraints();
        gbc_actNumtxt.fill = GridBagConstraints.HORIZONTAL;
        gbc_actNumtxt.gridx = 4;
        gbc_actNumtxt.gridy = 1;
        add(actNumtxt, gbc_actNumtxt);
        actNumtxt.setColumns(10);

    }

    public WorkerMemberHolder(Object object, int s) {
        this(s);
        if (object instanceof Participant) {
            Participant member = (Participant) object;
            this.actNumtxt.setText("" + member.getFullPersonalBranch().size());
            this.memName.setText(member.getName());
        }
    }
}
