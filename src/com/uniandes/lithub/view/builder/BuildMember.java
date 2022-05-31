package com.uniandes.lithub.view.builder;

import com.uniandes.lithub.model.Project;
import com.uniandes.lithub.view.App;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("serial")
public class BuildMember extends JDialog {

    private final JTextField nameTxt;
    private final JTextField emailTxt;

    public BuildMember(App facade, String project) {
        JPanel contentPanel = new JPanel();
        setTitle("Crear participante del proyecto " + project);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        GridBagLayout gbl_contentPanel = new GridBagLayout();
        gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
        gbl_contentPanel.rowHeights = new int[]{0, 0, 0};
        gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        contentPanel.setLayout(gbl_contentPanel);

        JLabel namelbl = new JLabel("Nombre");
        GridBagConstraints gbc_namelbl = new GridBagConstraints();
        gbc_namelbl.anchor = GridBagConstraints.EAST;
        gbc_namelbl.insets = new Insets(0, 0, 5, 5);
        gbc_namelbl.gridx = 0;
        gbc_namelbl.gridy = 0;
        contentPanel.add(namelbl, gbc_namelbl);

        nameTxt = new JTextField();
        GridBagConstraints gbc_nameTxt = new GridBagConstraints();
        gbc_nameTxt.insets = new Insets(0, 0, 5, 0);
        gbc_nameTxt.fill = GridBagConstraints.HORIZONTAL;
        gbc_nameTxt.gridx = 1;
        gbc_nameTxt.gridy = 0;
        contentPanel.add(nameTxt, gbc_nameTxt);
        nameTxt.setColumns(10);

        JLabel emaillbl = new JLabel("E-mail");
        GridBagConstraints gbc_emaillbl = new GridBagConstraints();
        gbc_emaillbl.anchor = GridBagConstraints.EAST;
        gbc_emaillbl.insets = new Insets(0, 0, 0, 5);
        gbc_emaillbl.gridx = 0;
        gbc_emaillbl.gridy = 1;
        contentPanel.add(emaillbl, gbc_emaillbl);

        emailTxt = new JTextField();
        GridBagConstraints gbc_emailTxt = new GridBagConstraints();
        gbc_emailTxt.fill = GridBagConstraints.HORIZONTAL;
        gbc_emailTxt.gridx = 1;
        gbc_emailTxt.gridy = 1;
        contentPanel.add(emailTxt, gbc_emailTxt);
        emailTxt.setColumns(10);
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> {
            String name = nameTxt.getText();
            String email = emailTxt.getText();
            if (!name.equals("") && !email.equals("")) {
                Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
                Matcher matcher = pattern.matcher(email);
                if (!matcher.find()) {
                    JOptionPane.showMessageDialog(null, "Sigue la estructura de un e-mail name@dom.com",
                            "Email erroneo", JOptionPane.ERROR_MESSAGE);
                } else {
                    facade.buildMember(project, name, email);
                    dispose();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Llene todos los campos", "Campo(s) vacio(s)",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

}
