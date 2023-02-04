package com.spa.dao;

import com.spa.entity.DonHangChiTiet;
import com.spa.tienich.Xjdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DonHangChiTietDAO extends SPaDAO<DonHangChiTiet, String> {

    public void insert(DonHangChiTiet model) {
        String sql = "INSERT INTO dhct(madh,makh,tenkh,tongtien,giodat,ngaydat,ttthanhtoan,ttdonhang) VALUES(?,?,?,?,?,?,?,?)";
        Xjdbc.update(sql,
                model.getMadh(),
                model.getMakh(),
                model.getTenkh(),
                model.getTongtien(),
                model.getGiodat(),
                model.getNgaydat(),
                model.isTtThanhToan(),
                model.isTtDonHang());
    }

    public void update(DonHangChiTiet model) {
        String sql = "UPDATE dhct SET ttthanhtoan=? ttdonhang=? WHERE MaDH=?";
        Xjdbc.update(sql,
                model.isTtThanhToan(),
                model.isTtDonHang(),
                model.getMadh());
    }

    public void ThanhToan(String madh) {
        String sql = "UPDATE dhct SET ttthanhtoan=1 WHERE MaDH=?";
        Xjdbc.update(sql, madh);
    }

    public void RThanhToan(String madh) {
        String sql = "UPDATE dhct SET ttthanhtoan=0 WHERE MaDH=?";
        Xjdbc.update(sql, madh);
    }

    public void Huy(String madh) {
        String sql = "UPDATE dhct SET ttdonhang=1 WHERE MaDH=?";
        Xjdbc.update(sql, madh);
    }

    public void RHuy(String madh) {
        String sql = "UPDATE dhct SET ttdonhang=0 WHERE MaDH=?";
        Xjdbc.update(sql, madh);
    }

    public void delete(String maDH) {
        String sql = "DELETE FROM dhct WHERE madh=?";
        Xjdbc.update(sql, maDH);
    }

    public List<DonHangChiTiet> selectAll() {
        String sql = "SELECT * FROM dhct where ttdonhang=0";
        return selectBySql(sql);
    }
    public List<DonHangChiTiet> XemChiTiet(int Thang, int year) {
        String sql = "select * from dhct where month(ngaydat)=? and year(ngaydat)=? and ttthanhtoan=1 and ttdonhang=0";
        return selectBySql(sql,Thang,year);
    }

    public List<DonHangChiTiet> selectDH() {
        String sql = "SELECT * FROM dhct where ttdonhang=1";
        return selectBySql(sql);
    }

    public List<DonHangChiTiet> XemLich(String date) {
        String sql = "SELECT * FROM dhct where ngaydat=? and ttdonhang=0";
        return selectBySql(sql,date);
    }
    public DonHangChiTiet selectById(String madh) {
        String sql = "SELECT * FROM dhct WHERE madh=?";
        List<DonHangChiTiet> list = selectBySql(sql, madh);
        return list.size() > 0 ? list.get(0) : null;
    }

    protected List<DonHangChiTiet> selectBySql(String sql, Object... args) {
        List<DonHangChiTiet> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = Xjdbc.query(sql, args);
                while (rs.next()) {
                    DonHangChiTiet entity = new DonHangChiTiet();
                    entity.setMadh(rs.getString("madh"));
                    entity.setMakh(rs.getString("makh"));
                    entity.setTenkh(rs.getString("tenkh"));
                    entity.setTongtien(rs.getString("tongtien"));
                    entity.setGiodat(rs.getString("giodat"));
                    entity.setNgaydat(rs.getDate("ngaydat"));
                    entity.setTtThanhToan(rs.getBoolean("ttthanhtoan"));
                    entity.setTtDonHang(rs.getBoolean("ttdonhang"));
                    list.add(entity);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

//    public List<DonHangChiTiet> selectByDonHangDichVu(String MaKH, String madv) {
//        String sql = "SELECT * FROM Don_Hang_Dich_Vu WHERE maKH =? and madv=? and trangthai = 1 order by madh desc";
//        return this.selectBySql(sql, MaKH, madv);
//    }
//
//    public List<DonHangChiTiet> XemLich(String date) {
//        String sql = "select * from don_hang_dich_vu where ngaydatlich=? and trangthai=1 order by madh";
//        return this.selectBySql(sql, date);
//    }
}
