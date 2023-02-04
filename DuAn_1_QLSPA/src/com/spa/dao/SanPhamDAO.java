package com.spa.dao;

import com.spa.tienich.Xjdbc;
import com.spa.entity.SanPham;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SanPhamDAO extends SPaDAO<SanPham, String> {

    @Override
    public void insert(SanPham model) {
        String sql = "INSERT INTO SanPham (MaSP, TenSP, gia,hinhanh) VALUES (?, ?, ?, ?)";
        Xjdbc.update(sql,
                model.getMaSP(),
                model.getTenSP(),
                model.getGia(),
                model.getHinhAnh());
    }

    public void update(SanPham model) {
        String sql = "UPDATE SanPham SET TenSP=?, gia=?,hinhanh=? WHERE MaSP=?";
        Xjdbc.update(sql,
                model.getTenSP(),
                model.getGia(),
                model.getHinhAnh(),
                model.getMaSP());
    }

    public void delete(String MaSP) {
        String sql = "DELETE FROM SanPham WHERE MaSp=?";
        Xjdbc.update(sql, MaSP);
    }

    public List<SanPham> selectAll() {
        String sql = "SELECT * FROM SanPham";
        return selectBySql(sql);
    }

    public SanPham selectById(String masp) {
        String sql = "SELECT * FROM sanpham WHERE masp=?";
        List<SanPham> list = selectBySql(sql, masp);
        return list.size() > 0 ? list.get(0) : null;
    }

    protected List<SanPham> selectBySql(String sql, Object... args) {
        List<SanPham> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = Xjdbc.query(sql, args);
                while (rs.next()) {
                    SanPham entity = new SanPham();
                    entity.setMaSP(rs.getString("masp"));
                    entity.setTenSP(rs.getString("tensp"));
                    entity.setGia(rs.getInt("gia"));
                    entity.setHinhAnh(rs.getString("hinhanh"));
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

}
