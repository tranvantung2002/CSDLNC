package Model;

import java.util.Date;

public class PhieuNhap {
    private int MaPhieuNhap;
    private int MaNhanVien;
    private int MaNhaPhanPhoi;
    private Float TongTien;
    private Date NgayNhap;
    private String ChuThich;

    public PhieuNhap(int maPhieuNhap, int maNhanVien, int maNhaPhanPhoi, Float tongTien, Date ngayNhap, String chuThich) {
        MaPhieuNhap = maPhieuNhap;
        MaNhanVien = maNhanVien;
        MaNhaPhanPhoi = maNhaPhanPhoi;
        TongTien = tongTien;
        NgayNhap = ngayNhap;
        ChuThich = chuThich;
    }

    public int getMaPhieuNhap() {
        return MaPhieuNhap;
    }

    public int getMaNhanVien() {
        return MaNhanVien;
    }

    public int getMaNhaPhanPhoi() {
        return MaNhaPhanPhoi;
    }

    public Float getTongTien() {
        return TongTien;
    }

    public Date getNgayNhap() {
        return NgayNhap;
    }

    public String getChuThich() {
        return ChuThich;
    }

    public void setMaPhieuNhap(int maPhieuNhap) {
        MaPhieuNhap = maPhieuNhap;
    }

    public void setMaNhanVien(int maNhanVien) {
        MaNhanVien = maNhanVien;
    }

    public void setMaNhaPhanPhoi(int maNhaPhanPhoi) {
        MaNhaPhanPhoi = maNhaPhanPhoi;
    }

    public void setTongTien(Float tongTien) {
        TongTien = tongTien;
    }

    public void setNgayNhap(Date ngayNhap) {
        NgayNhap = ngayNhap;
    }

    public void setChuThich(String chuThich) {
        ChuThich = chuThich;
    }

    @Override
    public String toString() {
        return "PhieuNhap{" +
                "MaPhieuNhap=" + MaPhieuNhap +
                ", MaNhanVien=" + MaNhanVien +
                ", MaNhaPhanPhoi=" + MaNhaPhanPhoi +
                ", TongTien=" + TongTien +
                ", NgayNhap=" + NgayNhap +
                ", ChuThich='" + ChuThich + '\'' +
                '}';
    }
}
