package Model;

import java.util.Date;

public class NhanVien {
    private int MaNhanVien;
    private String HovaTen;
    private Date NgaySinh;
    private String GioiTinh;
    private Date NgayVaoLam;
    private String ChucVu;
    private String DiaChi;
    private String SoDT;
    private String GhiChu;
    
    
	public NhanVien(int maNhanVien, String hovaTen, Date ngaySinh, String gioiTinh, Date ngayVaoLam, String chucVu,
			String diaChi, String soDT, String ghiChu) {
		super();
		MaNhanVien = maNhanVien;
		HovaTen = hovaTen;
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


	public String getHovaTen() {
		return HovaTen;
	}


	public void setHovaTen(String hovaTen) {
		HovaTen = hovaTen;
	}


	public Date getNgaySinh() {
		return NgaySinh;
	}


	public void setNgaySinh(Date ngaySinh) {
		NgaySinh = ngaySinh;
	}


	public String getGioiTinh() {
		return GioiTinh;
	}


	public void setGioiTinh(String gioiTinh) {
		GioiTinh = gioiTinh;
	}


	public Date getNgayVaoLam() {
		return NgayVaoLam;
	}


	public void setNgayVaoLam(Date ngayVaoLam) {
		NgayVaoLam = ngayVaoLam;
	}


	public String getChucVu() {
		return ChucVu;
	}


	public void setChucVu(String chucVu) {
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
		return "NhanVien [MaNhanVien=" + MaNhanVien + ", HovaTen=" + HovaTen + ", NgaySinh=" + NgaySinh + ", GioiTinh="
				+ GioiTinh + ", NgayVaoLam=" + NgayVaoLam + ", ChucVu=" + ChucVu + ", DiaChi=" + DiaChi + ", SoDT="
				+ SoDT + ", GhiChu=" + GhiChu + "]";
	}
	
	
	
	




    



	
}
