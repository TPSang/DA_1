package com.spa.dao;

import com.spa.entity.KhachHang;
import com.spa.tienich.Xjdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KhachHangDAO extends SPaDAO<KhachHang, String> {

    public void insert(KhachHang model) {
        String sql = "INSERT INTO KhachHang (MaKH, tenkh, sdt, email, ngaysinh, gioitinh, Ngaydangky, MaNV) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Xjdbc.update(sql,
                model.getMaKH(),
                model.getHoTen(),
                model.getDienThoai(),
                model.getEmail(),
                model.getNgaySinh(),
                model.isGioiTinh(),
                model.getNgayDK(),
                model.getMaNV());
    }

    public void update(KhachHang model) {
        String sql = "UPDATE KhachHang SET tenkh=?, sdt=?, email=?, ngaysinh=?, gioitinh=?, ngaydangky=?, MaNV=? WHERE MaKH=?";
        Xjdbc.update(sql,
                model.getHoTen(),
                model.getDienThoai(),
                model.getEmail(),
                model.getNgaySinh(),
                model.isGioiTinh(),
                model.getNgayDK(),
                model.getMaNV(),
                model.getMaKH());
    }

    public void delete(String id) {
        String sql = "DELETE FROM Khachhang WHERE makh=?";
        Xjdbc.update(sql, id);
    }

    public List<KhachHang> selectAll() {
        String sql = "SELECT * FROM khachHang";
        return selectBySql(sql);
    }

    public KhachHang selectById(String makh) {
        String sql = "SELECT * FROM khachhang WHERE makh=?";
        List<KhachHang> list = selectBySql(sql, makh);
        return list.size() > 0 ? list.get(0) : null;
    }

    protected List<KhachHang> selectBySql(String sql, Object... args) {
        List<KhachHang> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = Xjdbc.query(sql, args);
                while (rs.next()) {
                    KhachHang entity = new KhachHang();
                    entity.setMaKH(rs.getString("MaKH"));
                    entity.setHoTen(rs.getString("tenkh"));
                    entity.setDienThoai(rs.getString("sdt"));
                    entity.setEmail(rs.getString("email"));
                    entity.setNgaySinh(rs.getDate("ngaysinh"));
                    entity.setGioiTinh(rs.getBoolean("gioitinh"));
                    entity.setNgayDK(rs.getDate("NgayDangKy"));
                    entity.setMaNV(rs.getString("MaNV"));
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

    public List<KhachHang> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM KhachHang WHERE makh = ?";
        return selectBySql(sql, keyword);
    }

}
