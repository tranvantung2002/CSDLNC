import View.LoginFrame;
import View.MainFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Hiển thị màn hình login
            LoginFrame loginFrame = new LoginFrame();

            // Sử dụng SwingWorker để kiểm tra trạng thái đăng nhập
            new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() {
                    while (!loginFrame.isLoggedIn()) {
                        try {
                            Thread.sleep(1000); // Ngủ 1 giây
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    return null;
                }

                @Override
                protected void done() {
                    // Nếu đăng nhập thành công, mở màn hình chính
                    new MainFrame();
                }
            }.execute();
        });
    }
}