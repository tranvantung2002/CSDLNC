package Model;

import java.util.Date;

public class PhieuNhap {
    private final int MaPhieuNhap;
    private final int MaNhanVien;
    private final int MaNhaPhanPhoi;
    private final Float TongTien;
    private final Date NgayNhap;
    private final String ChuThich;

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
