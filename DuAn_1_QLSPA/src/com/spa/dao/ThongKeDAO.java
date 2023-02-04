package com.spa.dao;

import com.spa.tienich.Xjdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ThongKeDAO {

    private List<Object[]> getListOfArray(String sql, String[] cols, Object... args) {
        try {
            List<Object[]> list = new ArrayList<>();
            ResultSet rs = Xjdbc.query(sql, args);
            while (rs.next()) {
                Object[] vals = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Object[]> Top3SanPham(int thang) {
        String sql = "{CALL sp_top3sanpham(?)}";
        String[] cols = {"masp", "tensp", "solandat"};
        return this.getListOfArray(sql, cols, thang);
    }

    public List<Object[]> DoanhThu(int thang, int nam) {
        String sql = "{CALL sp_doanhthu(?,?)}";
        String[] cols = {"thang", "sodh", "tien"};
        return this.getListOfArray(sql, cols, thang, nam);
    }

    public List<Object[]> DoanhThuNgay(String date) {
        String sql = "{CALL sp_doanhthungay(?)}";
        String[] cols = {"ngaydat", "sodh", "tien"};
        return this.getListOfArray(sql, cols, date);
    }

}
