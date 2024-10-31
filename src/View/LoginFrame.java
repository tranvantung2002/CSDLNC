package View;

import javax.swing.*;
import java.awt.event.*;

public class LoginFrame extends JFrame {
    private JPanel contentPane;
    private JTextField userNameField;
    private JButton loginButton;
    private JPasswordField passwordField;

    private  boolean isLogin =false;
    public LoginFrame(){
        setContentPane(contentPane);
        setSize(700, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    public String getUserName() {
        return userNameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public void addLoginButtonListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }
}
