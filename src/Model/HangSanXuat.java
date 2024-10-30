package Model;

public class HangSanXuat {
    private int MaHangSanXuat;
    private String TenHangSanXuat;

    public HangSanXuat(int maHangSanXuat, String tenHangSanXuat) {
        MaHangSanXuat = maHangSanXuat;
        TenHangSanXuat = tenHangSanXuat;
    }

    public int getMaHangSanXuat() {
        return MaHangSanXuat;
    }

    public void setMaHangSanXuat(int maHangSanXuat) {
        MaHangSanXuat = maHangSanXuat;
    }

    public String getTenHangSanXuat() {
        return TenHangSanXuat;
    }

    public void setTenHangSanXuat(String tenHangSanXuat) {
        TenHangSanXuat = tenHangSanXuat;
    }

    @Override
    public String toString() {
        return "HangSanXuat{" +
                "MaHangSanXuat=" + MaHangSanXuat +
                ", TenHangSanXuat='" + TenHangSanXuat + '\'' +
                '}';
    }
}
