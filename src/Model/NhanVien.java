package Model;

import java.util.Date;

public class NhanVien {
    private int MaNhanVien;
    private Date NgaySinh;
    private int GioiTinh;
    private Date NgayVaoLam;
    private int ChucVu;
    private String DiaChi;
    private String SoDT;
    private String GhiChu;

    public NhanVien(int maNhanVien, Date ngaySinh, int gioiTinh, Date ngayVaoLam, int chucVu, String diaChi, String soDT, String ghiChu) {
        MaNhanVien = maNhanVien;
        NgaySinh = ngaySinh;
        GioiTinh = gioiTinh;
        NgayVaoLam = ngayVaoLam;
        ChucVu = chucVu;
        DiaChi = diaChi;
        SoDT = soDT;
        GhiChu = ghiChu;
    }

    public int getMaNhanVien() {
        return MaNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        MaNhanVien = maNhanVien;
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

    public Date getNgayVaoLam() {
        return NgayVaoLam;
    }

    public void setNgayVaoLam(Date ngayVaoLam) {
        NgayVaoLam = ngayVaoLam;
    }

    public int getChucVu() {
        return ChucVu;
    }

    public void setChucVu(int chucVu) {
        ChucVu = chucVu;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getSoDT() {
        return SoDT;
    }

    public void setSoDT(String soDT) {
        SoDT = soDT;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String ghiChu) {
        GhiChu = ghiChu;
    }

    @Override
    public String toString() {
        return "NhanVien{" +
                "MaNhanVien=" + MaNhanVien +
                ", NgaySinh=" + NgaySinh +
                ", GioiTinh=" + GioiTinh +
                ", NgayVaoLam=" + NgayVaoLam +
                ", ChucVu=" + ChucVu +
                ", DiaChi='" + DiaChi + '\'' +
                ", SoDT='" + SoDT + '\'' +
                ", GhiChu='" + GhiChu + '\'' +
                '}';
    }
}
