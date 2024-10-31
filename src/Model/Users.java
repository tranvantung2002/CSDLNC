package Model;

public class Users {
    private int ID;
    private int MaNhanVien;
    private String TenDangNhap;
    private String Password;
    private int Quyen;
    private String ChuThich;

    public Users(int ID, int maNhanVien, String tenDangNhap, String password, int quyen, String chuThich) {
        this.ID = ID;
        MaNhanVien = maNhanVien;
        TenDangNhap = tenDangNhap;
        Password = password;
        Quyen = quyen;
        ChuThich = chuThich;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getMaNhanVien() {
        return MaNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        MaNhanVien = maNhanVien;
    }

    public String getTenDangNhap() {
        return TenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        TenDangNhap = tenDangNhap;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getQuyen() {
        return Quyen;
    }

    public void setQuyen(int quyen) {
        Quyen = quyen;
    }

    public String getChuThich() {
        return ChuThich;
    }

    public void setChuThich(String chuThich) {
        ChuThich = chuThich;
    }

    @Override
    public String toString() {
        return "Users{" +
                "ID=" + ID +
                ", MaNhanVien=" + MaNhanVien +
                ", TenDangNhap=" + TenDangNhap +
                ", Password=" + Password +
                ", Quyen=" + Quyen +
                ", ChuThich=" + ChuThich +
                '}';
    }
}
