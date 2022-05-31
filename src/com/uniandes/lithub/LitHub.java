package com.uniandes.lithub;

import com.uniandes.lithub.controller.LitController;
import com.uniandes.lithub.controller.SavedProjectInfo;
import com.uniandes.lithub.view.App;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class LitHub {

    public static final String DATA = "data";

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                SavedProjectInfo savedInfo = new SavedProjectInfo(DATA);
                String fNames = savedInfo.load();
                JOptionPane.showMessageDialog(null, fNames, "Cargado",
                        JOptionPane.PLAIN_MESSAGE);
                LitController controller = new LitController(savedInfo);
                App frame = new App(controller);
                frame.setVisible(true);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error en carga",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }

}
