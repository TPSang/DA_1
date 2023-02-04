package com.spa.dao;

import com.spa.tienich.Xjdbc;
import com.spa.entity.DichVu;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DichVuDAO extends SPaDAO<DichVu, String> {

    public void insert(DichVu model) {
        String sql = "INSERT INTO DichVu (MaDV, TenDV, ChiPhi) VALUES (?, ?, ?)";
        Xjdbc.update(sql,
                model.getMaDV(),
                model.getTenDV(),
                model.getChiPhi());
    }

    public void update(DichVu model) {
        String sql = "UPDATE DichVu SET TenDV=?,ChiPhi=? WHERE MaDV=?";
        Xjdbc.update(sql,
                model.getTenDV(),
                model.getChiPhi(),
                model.getMaDV());

    }

    public void delete(String MaDV) {
        String sql = "DELETE FROM DichVu WHERE MaDV=?";
        Xjdbc.update(sql, MaDV);
    }

    public List<DichVu> selectAll() {
         // String sql = "SELECT * FROM DichVu order by chiphi desc";
        String sql = "SELECT * FROM DichVu ";
        return selectBySql(sql);
    }
    public DichVu selectById(String madv) {
        String sql = "SELECT * FROM DichVu WHERE MaDV=?";
        List<DichVu> list = selectBySql(sql, madv);
        return list.size() > 0 ? list.get(0) : null;
    }

    protected List<DichVu> selectBySql(String sql, Object... args) {
        List<DichVu> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = Xjdbc.query(sql, args);
                while (rs.next()) {
                    DichVu entity = new DichVu();
                    entity.setMaDV(rs.getString("MaDV"));
                    entity.setTenDV(rs.getString("TenDV"));
                    entity.setChiPhi(rs.getInt("ChiPhi"));
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
