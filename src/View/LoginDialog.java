package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginDialog extends JDialog {
    private boolean loggedIn = false;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;

    public LoginDialog(JFrame parent) {
        super(parent, "Login", true);

        // Initialize components
        contentPane = new JPanel();
        buttonOK = new JButton("OK");
        buttonCancel = new JButton("Cancel");

        buttonOK.setVisible(false);
        buttonCancel.setVisible(false);

        // Set up layout
        contentPane.setLayout(new BorderLayout());

        // Create user input panel
        JPanel panel = new JPanel();
        JLabel userLabel = new JLabel("Username:");
        JTextField userField = new JTextField(15);
        JLabel passwordLabel = new JLabel("Password:");
        JTextField passwordField = new JTextField(15);
        JButton loginButton = new JButton("Login");

        panel.add(userLabel);
        panel.add(userField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        contentPane.add(panel, BorderLayout.CENTER);

        // Set up button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(buttonOK);
        buttonPanel.add(buttonCancel);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(contentPane);
        setSize(300, 150);
        setLocationRelativeTo(parent);

        // Set default button
        getRootPane().setDefaultButton(buttonOK);

        // Login button action
        loginButton.addActionListener(e -> {
            if (userField.getText().equals("admin") && passwordField.getText().equals("123456")) {
                loggedIn = true;
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // OK button action (optional functionality)
        buttonOK.addActionListener(e -> {
            // Here you can add any additional action if needed
            dispose(); // Close dialog
        });

        // Cancel button action
        buttonCancel.addActionListener(e -> {
            dispose(); // Close dialog
        });

        setVisible(true);
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
}