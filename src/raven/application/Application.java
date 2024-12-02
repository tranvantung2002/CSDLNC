package raven.application;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import raven.application.form.LoginForm;
import raven.application.form.MainForm;
import raven.toast.Notifications;

import javax.swing.*;
import java.awt.*;

import static Model.Db4Obj.KhoiTaoDB;
import static Model.Db4Obj.db;

/**
 * @author Raven
 */
public class Application extends javax.swing.JFrame {

    private static Application app;
    private final MainForm mainForm;
    private final LoginForm loginForm;

    public Application() {
        initComponents();
        setSize(new Dimension(1366, 768));
        setLocationRelativeTo(null);
        mainForm = new MainForm();
        loginForm = new LoginForm();
        setContentPane(loginForm);
        getRootPane().putClientProperty(FlatClientProperties.FULL_WINDOW_CONTENT, true);
        Notifications.getInstance().setJFrame(this);
    }

    public static void showForm(Component component) {
        component.applyComponentOrientation(app.getComponentOrientation());
        app.mainForm.showForm(component);
    }

    public static void login() {
        FlatAnimatedLafChange.showSnapshot();
        app.setContentPane(app.mainForm);
        app.mainForm.applyComponentOrientation(app.getComponentOrientation());
        setSelectedMenu(0, 0);
        app.mainForm.hideMenu();
        SwingUtilities.updateComponentTreeUI(app.mainForm);
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
    }

    public static void logout() {
        FlatAnimatedLafChange.showSnapshot();
        app.setContentPane(app.loginForm);
        app.loginForm.applyComponentOrientation(app.getComponentOrientation());
        SwingUtilities.updateComponentTreeUI(app.loginForm);
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
    }

    public static void setSelectedMenu(int index, int subIndex) {
        app.mainForm.setSelectedMenu(index, subIndex);
    }

    public static void main(String[] args) {
        try {
            KhoiTaoDB();
            FlatRobotoFont.install();
            FlatLaf.registerCustomDefaultsSource("raven.theme");
            UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
            FlatMacDarkLaf.setup();

            // Khởi chạy ứng dụng trong EventQueue
            java.awt.EventQueue.invokeLater(() -> {
                app = new Application();
                // Đảm bảo cơ sở dữ liệu được đóng sau khi ứng dụng tắt
                app.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                        // Đóng cơ sở dữ liệu khi ứng dụng đóng
                        if (db != null && !db.ext().isClosed()) {
                            db.close();
                        }
                    }
                });
                app.setVisible(true);
            });
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 719, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 521, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
