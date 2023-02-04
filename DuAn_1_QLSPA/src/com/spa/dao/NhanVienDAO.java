package com.spa.dao;

import com.spa.tienich.Xjdbc;
import com.spa.entity.NhanVien;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO extends SPaDAO<NhanVien, String> {

    private Object tblNhanVien;

    public void insert(NhanVien model) {
        String sql = "INSERT INTO NhanVien (manv, hoten, sdt, matkhau,vaitro,hinhanh) VALUES (?, ?, ?, ?,?,?)";
        Xjdbc.update(sql,
                model.getMaNV(),
                model.getHoTen(),
                model.getDienThoai(),
                model.getMatKhau(),
                model.isVaiTro(),
                model.getHinhanh());
    }

    public void update(NhanVien model) {
        String sql = "UPDATE NhanVien SET hoten=?, sdt=?, matkhau=? ,vaitro=? ,hinhanh=? WHERE MaNV= ?";
        Xjdbc.update(sql,
                model.getHoTen(),
                model.getDienThoai(),
                model.getMatKhau(),
                model.isVaiTro(),    
                model.getHinhanh(),
                model.getMaNV());
        System.out.println(sql);
    }

    public void delete(String MaNV) {
        String sql = "DELETE FROM NhanVien WHERE MaNV=?";
        Xjdbc.update(sql, MaNV);
    }

    public List<NhanVien> selectAll() {
        String sql = "SELECT * FROM NhanVien";
        return this.selectBySql(sql);
    }

    public NhanVien selectById(String manv) {
        String sql = "SELECT * FROM NhanVien WHERE MaNV=?";
        List<NhanVien> list = this.selectBySql(sql, manv);
        return list.size() > 0 ? list.get(0) : null;
    }

    protected List<NhanVien> selectBySql(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = Xjdbc.query(sql, args);
                while (rs.next()) {
                    NhanVien entity = new NhanVien();
                    entity.setMaNV(rs.getString("MaNV"));
                    entity.setHoTen(rs.getString("hoten"));
                    entity.setDienThoai(rs.getString("sdt"));
                    entity.setMatKhau(rs.getString("matkhau"));
                    entity.setVaiTro(rs.getBoolean("vaitro"));
                     entity.setHinhanh(rs.getString("hinhanh"));
                    list.add(entity);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return list;
    }

}
