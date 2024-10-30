package Model;

import java.util.Date;

public class HoaDon {
    private int MaHoaDon;
    private int MaKhachHang;
    private int MaNhanVien;
    private Date NgayLaphoaDon;
    private Float TongTien;
    private String GhiChu;

    public HoaDon(int maHoaDon, int maKhachHang, int maNhanVien, Date ngayLaphoaDon, Float tongTien, String ghiChu) {
        MaHoaDon = maHoaDon;
        MaKhachHang = maKhachHang;
        MaNhanVien = maNhanVien;
        NgayLaphoaDon = ngayLaphoaDon;
        TongTien = tongTien;
        GhiChu = ghiChu;
    }

    public int getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        MaHoaDon = maHoaDon;
    }

    public int getMaKhachHang() {
        return MaKhachHang;
    }

    public void setMaKhachHang(int maKhachHang) {
        MaKhachHang = maKhachHang;
    }

    public int getMaNhanVien() {
        return MaNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        MaNhanVien = maNhanVien;
    }

    public Date getNgayLaphoaDon() {
        return NgayLaphoaDon;
    }

    public void setNgayLaphoaDon(Date ngayLaphoaDon) {
        NgayLaphoaDon = ngayLaphoaDon;
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
        return "HoaDon{" +
                "MaHoaDon=" + MaHoaDon +
                ", MaKhachHang=" + MaKhachHang +
                ", MaNhanVien=" + MaNhanVien +
                ", NgayLaphoaDon=" + NgayLaphoaDon +
                ", TongTien=" + TongTien +
                ", GhiChu='" + GhiChu + '\'' +
                '}';
    }
}
