package com.uniandes.lithub.view.left.interest;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;

@SuppressWarnings("serial")
public class TotalTimeHolder extends JPanel {
    private final JTextField totalTimeTxt;
    private final JLabel projNamelbl;

    public TotalTimeHolder() {
        setBorder(new CompoundBorder(new MatteBorder(3, 0, 3, 0, new Color(0, 0, 0)),
                new EmptyBorder(2, 2, 2, 2)));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        projNamelbl = new JLabel("Total de tiempo en el proyecto");
        add(projNamelbl);

        totalTimeTxt = new JTextField();
        totalTimeTxt.setEditable(false);
        totalTimeTxt.setColumns(10);
        add(totalTimeTxt);

    }

    public TotalTimeHolder(String proyN, long totalTime) {
        this();
        projNamelbl.setText("Total de tiempo en " + proyN);
        totalTimeTxt.setText("" + totalTime);
    }
}
