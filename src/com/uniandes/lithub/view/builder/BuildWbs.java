package com.uniandes.lithub.view.builder;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class BuildWbs extends JDialog {
    public BuildWbs() {
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0};
        gridBagLayout.rowHeights = new int[]{0};
        gridBagLayout.columnWeights = new double[]{Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{Double.MIN_VALUE};
        getContentPane().setLayout(gridBagLayout);
    }
}
