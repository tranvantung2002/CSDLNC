package Model;

public class LoaiSanPham {
    private int MaLoaiSanPham;
    private String TenLoaiSanPham;

    public LoaiSanPham(int maLoaiSanPham, String tenLoaiSanPham) {
        MaLoaiSanPham = maLoaiSanPham;
        TenLoaiSanPham = tenLoaiSanPham;

    }

    public int getMaLoaiSanPham() {
        return MaLoaiSanPham;
    }

    public void setMaLoaiSanPham(int maLoaiSanPham) {
        MaLoaiSanPham = maLoaiSanPham;
    }

    public String getTenLoaiSanPham() {
        return TenLoaiSanPham;
    }
    public void setTenLoaiSanPham(String tenLoaiSanPham) {
        TenLoaiSanPham = tenLoaiSanPham;
    }

    @Override
    public String toString() {
        return "LoaiSanPham{" +
                "MaLoaiSanPham=" + MaLoaiSanPham +
                ", TenLoaiSanPham='" + TenLoaiSanPham + '\'' +
                '}';
    }
}
