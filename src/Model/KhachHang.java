package Model;

import java.util.Date;

public class KhachHang {
    private int MaKhachHang;
    private String TenKhachHang;
    private Date NgaySinh;
    private int GioiTinh;
    private String DiaChi;
    private String SDT;
    private String LoaiKhachHang;
    private String GhiChu;

    public KhachHang(int maKhachHang, String tenKhachHang, Date ngaySinh, int gioiTinh, String diaChi, String SDT, String loaiKhachHang, String ghiChu) {
        MaKhachHang = maKhachHang;
        TenKhachHang = tenKhachHang;
        NgaySinh = ngaySinh;
        GioiTinh = gioiTinh;
        DiaChi = diaChi;
        this.SDT = SDT;
        LoaiKhachHang = loaiKhachHang;
        GhiChu = ghiChu;
    }

    public int getMaKhachHang() {
        return MaKhachHang;
    }

    public void setMaKhachHang(int maKhachHang) {
        MaKhachHang = maKhachHang;
    }

    public String getTenKhachHang() {
        return TenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        TenKhachHang = tenKhachHang;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        NgaySinh = ngaySinh;
    }

    public int getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        GioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getLoaiKhachHang() {
        return LoaiKhachHang;
    }

    public void setLoaiKhachHang(String loaiKhachHang) {
        LoaiKhachHang = loaiKhachHang;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String ghiChu) {
        GhiChu = ghiChu;
    }

    @Override
    public String toString() {
        return "KhachHang{" +
                "MaKhachHang=" + MaKhachHang +
                ", TenKhachHang='" + TenKhachHang + '\'' +
                ", NgaySinh=" + NgaySinh +
                ", GioiTinh=" + GioiTinh +
                ", DiaChi='" + DiaChi + '\'' +
                ", SDT='" + SDT + '\'' +
                ", LoaiKhachHang='" + LoaiKhachHang + '\'' +
                ", GhiChu='" + GhiChu + '\'' +
                '}';
    }
}
