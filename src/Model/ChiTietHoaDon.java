package Model;

public class ChiTietHoaDon {
    private int MaCTHD;
    private int MaHoaDon;
    private int MaSanPham;
    private int SoLuong;
    private Float TongTien;
    private String GhiChu;

    public ChiTietHoaDon(int maCTHD, int maHoaDon, int maSanPham, int soLuong, Float tongTien, String ghiChu) {
        MaCTHD = maCTHD;
        MaHoaDon = maHoaDon;
        MaSanPham = maSanPham;
        SoLuong = soLuong;
        TongTien = tongTien;
        GhiChu = ghiChu;
    }

    public int getMaCTHD() {
        return MaCTHD;
    }

    public void setMaCTHD(int maCTHD) {
        MaCTHD = maCTHD;
    }

    public int getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        MaHoaDon = maHoaDon;
    }

    public int getMaSanPham() {
        return MaSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        MaSanPham = maSanPham;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    public Float getTongTien() {
        return TongTien;
    }

    public void setTongTien(Float tongTien) {
        TongTien = tongTien;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String ghiChu) {
        GhiChu = ghiChu;
    }

    @Override
    public String toString() {
        return "ChiTietHoaDon{" +
                "MaCTHD=" + MaCTHD +
                ", MaHoaDon=" + MaHoaDon +
                ", MaSanPham=" + MaSanPham +
                ", SoLuong=" + SoLuong +
                ", TongTien=" + TongTien +
                ", GhiChu='" + GhiChu + '\'' +
                '}';
    }
}
