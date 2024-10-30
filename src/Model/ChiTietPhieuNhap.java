package Model;

public class ChiTietPhieuNhap {
    private int MaCTPN;
    private int MaPhieuNhap;
    private int MaSanPham;
    private int SoLuong;
    private Float TongTien;
    private String GhiChu;

    public ChiTietPhieuNhap(int maCTPN, int maPhieuNhap, int maSanPham, int soLuong, Float tongTien, String ghiChu) {
        MaCTPN = maCTPN;
        MaPhieuNhap = maPhieuNhap;
        MaSanPham = maSanPham;
        SoLuong = soLuong;
        TongTien = tongTien;
        GhiChu = ghiChu;
    }

    public int getMaCTPN() {
        return MaCTPN;
    }

    public void setMaCTPN(int maCTPN) {
        MaCTPN = maCTPN;
    }

    public int getMaPhieuNhap() {
        return MaPhieuNhap;
    }

    public void setMaPhieuNhap(int maPhieuNhap) {
        MaPhieuNhap = maPhieuNhap;
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
        return "ChiTietPhieuNhap{" +
                "MaCTPN=" + MaCTPN +
                ", MaPhieuNhap=" + MaPhieuNhap +
                ", MaSanPham=" + MaSanPham +
                ", SoLuong=" + SoLuong +
                ", TongTien=" + TongTien +
                ", GhiChu='" + GhiChu + '\'' +
                '}';
    }
}
