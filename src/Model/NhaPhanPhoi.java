package Model;

public class NhaPhanPhoi {
    private int MaNhaPhanPhoi;
    private String TenNhaPhanPhoi;
    private String DiaChi;
    private String SDT;
    private String Email;
    private String ChuThich;

    public NhaPhanPhoi(int maNhaPhanPhoi, String tenNhaPhanPhoi, String diaChi, String SDT, String email, String chuThich) {
        MaNhaPhanPhoi = maNhaPhanPhoi;
        TenNhaPhanPhoi = tenNhaPhanPhoi;
        DiaChi = diaChi;
        this.SDT = SDT;
        Email = email;
        ChuThich = chuThich;
    }

    public int getMaNhaPhanPhoi() {
        return MaNhaPhanPhoi;
    }

    public void setMaNhaPhanPhoi(int maNhaPhanPhoi) {
        MaNhaPhanPhoi = maNhaPhanPhoi;
    }

    public String getTenNhaPhanPhoi() {
        return TenNhaPhanPhoi;
    }

    public void setTenNhaPhanPhoi(String tenNhaPhanPhoi) {
        TenNhaPhanPhoi = tenNhaPhanPhoi;
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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getChuThich() {
        return ChuThich;
    }

    public void setChuThich(String chuThich) {
        ChuThich = chuThich;
    }

    @Override
    public String toString() {
        return "NhaPhanPhoi{" +
                "MaNhaPhanPhoi=" + MaNhaPhanPhoi +
                ", TenNhaPhanPhoi='" + TenNhaPhanPhoi + '\'' +
                ", DiaChi='" + DiaChi + '\'' +
                ", SDT='" + SDT + '\'' +
                ", Email='" + Email + '\'' +
                ", ChuThich='" + ChuThich + '\'' +
                '}';
    }
}
