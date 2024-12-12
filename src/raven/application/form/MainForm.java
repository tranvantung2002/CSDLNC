package raven.application.form;

import View.*;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.util.UIScale;
import raven.application.Application;
import raven.menu.Menu;
import raven.menu.MenuAction;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author Raven
 */
public class MainForm extends JLayeredPane {

    private Menu menu;
    private JPanel panelBody;
    private JButton menuButton;

    public MainForm() {
        init();
    }

    private void init() {
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new MainFormLayout());
        menu = new Menu();
        panelBody = new JPanel(new BorderLayout());
        initMenuArrowIcon();
        menuButton.putClientProperty(FlatClientProperties.STYLE, "background:$Menu.button.background;"
                + "arc:999;"
                + "focusWidth:0;"
                + "borderWidth:0");
        menuButton.addActionListener((ActionEvent e) -> {
            setMenuFull(!menu.isMenuFull());
        });
        initMenuEvent();
        setLayer(menuButton, JLayeredPane.POPUP_LAYER);
        add(menuButton);
        add(menu);
        add(panelBody);
    }

    @Override
    public void applyComponentOrientation(ComponentOrientation o) {
        super.applyComponentOrientation(o);
        initMenuArrowIcon();
    }

    private void initMenuArrowIcon() {
        if (menuButton == null) {
            menuButton = new JButton();
        }
        String icon = (getComponentOrientation().isLeftToRight()) ? "menu_left.svg" : "menu_right.svg";
        menuButton.setIcon(new FlatSVGIcon("raven/icon/svg/" + icon, 0.8f));
    }

    private void initMenuEvent() {
        menu.addMenuEvent((int index, int subIndex, MenuAction action) -> {
            // Application.mainForm.showForm(new DefaultForm("Form : " + index + " " + subIndex));
            if (index == 0) {
                Application.showForm(new Dashboard());
            } else if (index == 1) {
                if (subIndex == 1) {
                    Application.showForm(new QuanLySanPham());
                } else if (subIndex == 2) {
                    Application.showForm(new QuanLyLoaiSanPham());
                } else if (subIndex == 3) {
                    Application.showForm(new QuanLyHangSanXuat());
                } else if (subIndex == 4) {
                    Application.showForm(new QuanLyChiTietPhieuNhap());
                } else if (subIndex == 5) {
                    Application.showForm(new QuanLyPhieuNhap());
                } else {
                    action.cancel();
                }
            } else if (index == 2) {
                if (subIndex == 1) {
                    Application.showForm(new QuanLyChucVu());
                } else if (subIndex == 2) {
                    Application.showForm(new QuanLyNhanVien());
                }
            } else if (index == 3) {
                if (subIndex == 1) {
                    Application.showForm(new QuanLyKhachHang());
                } else if (subIndex == 2) {
                    Application.showForm(new QuanLyLoaiKhachHang());
                }
            } else if (index == 4) {
           	 if (subIndex == 1) {
                 Application.showForm(new QuanLyNhaPhanPhoi());
             } else {
                 action.cancel();
             }
            } else if (index == 5) {
            	 if (subIndex == 1) {
            		 Application.showForm(new QuanLyNhanVien());
                 } else if (subIndex == 2) {
                     
                 } else {
                     action.cancel();
                 }
            } else if (index == 6) {
                if (subIndex == 1) {
                    Application.showForm(new QuanLyHoaDon());
                } else if (subIndex == 2) {
                    Application.showForm(new QuanLyChiTietHoaDon());
                }
            } else if (index == 7) {
                if (subIndex == 1) {
                    Application.showForm(new BaoCaoDoanhThuTheoQuy());
                } else if (subIndex == 2) {
                    Application.showForm(new BaoCaoDoanhThuTheoSanPham());
                } else if (subIndex == 3) {
                    Application.showForm(new BaoCaoLoiNhuan());
                } else if (subIndex == 4) {
                    Application.showForm(new BaoCaoNhapHang());
                } else if (subIndex == 5) {
                    Application.showForm(new BaoCaoKhachHangTheoLoai());
                } else if (subIndex == 6) {
                    Application.showForm(new BaoCaoTonKho());
                }
            } else if (index == 8) {
                if (subIndex == 1) {
                    Application.showForm(new QuanLyNguoiDung());
                }
            } else if (index == 9) {
                Application.logout();
            } else {
                action.cancel();
            }
        });
    }

    private void setMenuFull(boolean full) {
        String icon;
        if (getComponentOrientation().isLeftToRight()) {
            icon = (full) ? "menu_left.svg" : "menu_right.svg";
        } else {
            icon = (full) ? "menu_right.svg" : "menu_left.svg";
        }
        menuButton.setIcon(new FlatSVGIcon("raven/icon/svg/" + icon, 0.8f));
        menu.setMenuFull(full);
        revalidate();
    }

    public void hideMenu() {
        menu.hideMenuItem();
    }

    public void showForm(Component component) {
        panelBody.removeAll();
        panelBody.add(component);
        panelBody.repaint();
        panelBody.revalidate();
    }

    public void setSelectedMenu(int index, int subIndex) {
        menu.setSelectedMenu(index, subIndex);
    }

    private class MainFormLayout implements LayoutManager {

        @Override
        public void addLayoutComponent(String name, Component comp) {
        }

        @Override
        public void removeLayoutComponent(Component comp) {
        }

        @Override
        public Dimension preferredLayoutSize(Container parent) {
            synchronized (parent.getTreeLock()) {
                return new Dimension(5, 5);
            }
        }

        @Override
        public Dimension minimumLayoutSize(Container parent) {
            synchronized (parent.getTreeLock()) {
                return new Dimension(0, 0);
            }
        }

        @Override
        public void layoutContainer(Container parent) {
            synchronized (parent.getTreeLock()) {
                boolean ltr = parent.getComponentOrientation().isLeftToRight();
                Insets insets = UIScale.scale(parent.getInsets());
                int x = insets.left;
                int y = insets.top;
                int width = parent.getWidth() - (insets.left + insets.right);
                int height = parent.getHeight() - (insets.top + insets.bottom);
                int menuWidth = UIScale.scale(menu.isMenuFull() ? menu.getMenuMaxWidth() : menu.getMenuMinWidth());
                int menuX = ltr ? x : x + width - menuWidth;
                menu.setBounds(menuX, y, menuWidth, height);
                int menuButtonWidth = menuButton.getPreferredSize().width;
                int menuButtonHeight = menuButton.getPreferredSize().height;
                int menubX;
                if (ltr) {
                    menubX = (int) (x + menuWidth - (menuButtonWidth * (menu.isMenuFull() ? 0.5f : 0.3f)));
                } else {
                    menubX = (int) (menuX - (menuButtonWidth * (menu.isMenuFull() ? 0.5f : 0.7f)));
                }
                menuButton.setBounds(menubX, UIScale.scale(30), menuButtonWidth, menuButtonHeight);
                int gap = UIScale.scale(5);
                int bodyWidth = width - menuWidth - gap;
                int bodyHeight = height;
                int bodyx = ltr ? (x + menuWidth + gap) : x;
                int bodyy = y;
                panelBody.setBounds(bodyx, bodyy, bodyWidth, bodyHeight);
            }
        }
    }
}
