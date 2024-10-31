package Model;

public class SanPham {
    private int MaSanPham;
    private String TenSanPham;
    private int LoaiSanPham;
    private int HangSanXuat;
    private Float GiaNhap;
    private Float GiaBan;
    private int TonKho;
    private int TrangThai;
    private String blob;
    private String ChuThich;

    public SanPham(int maSanPham, String tenSanPham, int loaiSanPham, int hangSanXuat, Float giaNhap, Float giaBan, int tonKho, int trangThai, String blob, String chuThich) {
        MaSanPham = maSanPham;
        TenSanPham = tenSanPham;
        LoaiSanPham = loaiSanPham;
        HangSanXuat = hangSanXuat;
        GiaNhap = giaNhap;
        GiaBan = giaBan;
        TonKho = tonKho;
        TrangThai = trangThai;
        this.blob = blob;
        ChuThich = chuThich;
    }

    public int getMaSanPham() {
        return MaSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        MaSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return TenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        TenSanPham = tenSanPham;
    }

    public int getLoaiSanPham() {
        return LoaiSanPham;
    }

    public void setLoaiSanPham(int loaiSanPham) {
        LoaiSanPham = loaiSanPham;
    }

    public int getHangSanXuat() {
        return HangSanXuat;
    }

    public void setHangSanXuat(int hangSanXuat) {
        HangSanXuat = hangSanXuat;
    }

    public Float getGiaNhap() {
        return GiaNhap;
    }

    public void setGiaNhap(Float giaNhap) {
        GiaNhap = giaNhap;
    }

    public Float getGiaBan() {
        return GiaBan;
    }

    public void setGiaBan(Float giaBan) {
        GiaBan = giaBan;
    }

    public int getTonKho() {
        return TonKho;
    }

    public void setTonKho(int tonKho) {
        TonKho = tonKho;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int trangThai) {
        TrangThai = trangThai;
    }

    public String getBlob() {
        return blob;
    }

    public void setBlob(String blob) {
        this.blob = blob;
    }

    public String getChuThich() {
        return ChuThich;
    }

    public void setChuThich(String chuThich) {
        ChuThich = chuThich;
    }

    @Override
    public String toString() {
        return "SanPham{" +
                "MaSanPham=" + MaSanPham +
                ", TenSanPham='" + TenSanPham + '\'' +
                ", LoaiSanPham=" + LoaiSanPham +
                ", HangSanXuat=" + HangSanXuat +
                ", GiaNhap=" + GiaNhap +
                ", GiaBan=" + GiaBan +
                ", TonKho=" + TonKho +
                ", TrangThai=" + TrangThai +
                ", blob='" + blob + '\'' +
                ", ChuThich='" + ChuThich + '\'' +
                '}';
    }
}
