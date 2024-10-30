package Model;

public class ChucVu {
    private int MaChucVu;
    private String TenChucVu;
    private String GhiChu;

    public ChucVu(int maChucVu, String tenChucVu, String ghiChu) {
        MaChucVu = maChucVu;
        TenChucVu = tenChucVu;
        GhiChu = ghiChu;
    }

    public int getMaChucVu() {
        return MaChucVu;
    }

    public void setMaChucVu(int maChucVu) {
        MaChucVu = maChucVu;
    }

    public String getTenChucVu() {
        return TenChucVu;
    }

    public void setTenChucVu(String tenChucVu) {
        TenChucVu = tenChucVu;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String ghiChu) {
        GhiChu = ghiChu;
    }

    @Override
    public String toString() {
        return "ChucVu{" +
                "MaChucVu=" + MaChucVu +
                ", TenChucVu='" + TenChucVu + '\'' +
                ", GhiChu='" + GhiChu + '\'' +
                '}';
    }
}
