/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spa.tienich;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class Find {

    Connection con = null;
    Statement st = null;
    ResultSet rs = null;
    DefaultTableModel model;
    public static int doanhthusp, doanhthudv, doanhthungaydv, doanhthungaysp;
    int a, b;

    public void timKiemNhanVien(String txtTimkiem, JTable table) {
        String header[] = {"MaNV", "Họ Tên", "SDT ", "MatKhau", "Vai trò"};
        model = new DefaultTableModel(header, 0);
        try {
            model.setRowCount(0);
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=DUAN_1_QLSPA", "duan1", "123");
            String sql = "select * from nhanvien where hoten like N'%" + txtTimkiem + "%'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object[] row = {
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getBoolean(5) ? "QuanLy" : "NhanVien",};
                model.addRow(row);
            }
            table.setModel(model);
        } catch (Exception e) {

        }
    }

    public void timKiemSanPham(String txtTimkiem, JTable table) {
        String header[] = {"MaSP", "TenSP", "Gia"};
        model = new DefaultTableModel(header, 0);
        try {
            model.setRowCount(0);
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=DUAN_1_QLSPA", "duan1", "123");
            String sql = "select * from sanpham where tensp like N'%" + txtTimkiem + "%'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object[] row = {
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3)};
                model.addRow(row);
            }
            table.setModel(model);
        } catch (Exception e) {

        }
    }

    public void timKiemKhachHang(String txtTimkiem, JTable table) {
        String header[] = {"MaKH", "TenKH", "SDT ", "Email", "NgaySinh", "GioiTinh", "NgayDK", "MaNV"};
        model = new DefaultTableModel(header, 0);
        try {
            model.setRowCount(0);
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=DUAN_1_QLSPA", "duan1", "123");
            String sql = "select * from khachhang where tenkh like N'%" + txtTimkiem + "%'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object[] row = {
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8)};
                model.addRow(row);
            }
            table.setModel(model);
        } catch (Exception e) {

        }
    }

    public void timKiemDichVu(String txtTimkiem, JTable table) {
        String header[] = {"MaDV", "TenDV", "ChiPhi"};
        model = new DefaultTableModel(header, 0);
        try {
            model.setRowCount(0);
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=DUAN_1_QLSPA", "duan1", "123");
            String sql = "select * from dichvu where Tendv like N'%" + txtTimkiem + "%' or MaDV like N'%" + txtTimkiem + "%'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object[] row = {
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3)};
                model.addRow(row);
            }
            table.setModel(model);
        } catch (Exception e) {

        }
    }

    public void timKiemDichVu2(String txtTimkiem, JTable table) {
        String header[] = {"Tên dịch vụ", "Chi phí"};
        model = new DefaultTableModel(header, 0);
        try {
            model.setRowCount(0);
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=DUAN_1_QLSPA", "duan1", "123");
            String sql = "select * from dichvu where tendv like N'%" + txtTimkiem + "%'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object[] row = {
                    rs.getString(2),
                    rs.getInt(3)};
                model.addRow(row);
            }
            table.setModel(model);
        } catch (Exception e) {
        }
    }

    public void timKiemSanPham2(String txtTimkiem, JTable table) {
        String header[] = {"Tên sản phẩm", "Giá"};
        model = new DefaultTableModel(header, 0);
        try {
            model.setRowCount(0);
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=DUAN_1_QLSPA", "duan1", "123");
            String sql = "select * from sanpham where tensp like N'%" + txtTimkiem + "%'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object[] row = {
                    rs.getString(2),
                    rs.getInt(3)};
                model.addRow(row);
            }
            table.setModel(model);
        } catch (Exception e) {
        }
    }

    public int doanhThuSanPham(int Thang) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=DUAN_1_QLSPA", "duan1", "123");
            String sql = "select sum(tongtien)tien from DON_HANG_SAN_PHAM_phu where MONTH(ngaydatlich)= '" + Thang + "'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                doanhthusp = rs.getInt("tien");
            };

        } catch (Exception e) {
        }
        return doanhthusp;
    }

    public int doanhThuDichVu(int Thang) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=DUAN_1_QLSPA", "duan1", "123");
            String sql = "select sum(tongtien)tien from DON_HANG_DICH_VU_phu where MONTH(ngaydatlich)= '" + Thang + "'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                doanhthudv = rs.getInt("tien");
            };

        } catch (Exception e) {
        }
        return doanhthudv;
    }

    public int doanhThuNgayDichVu(String date) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=DUAN_1_QLSPA", "duan1", "123");
            String sql = "select sum(tongtien)tien from DON_HANG_DICH_VU_phu where ngaydatlich='" + date + "'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                doanhthungaydv = rs.getInt("tien");
            };

        } catch (Exception e) {
        }
        return doanhthungaydv;
    }

    public int doanhThuNgaySanPham(String date) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=DUAN_1_QLSPA", "duan1", "123");
            String sql = "select sum(tongtien)tien from DON_HANG_SAN_PHAM_phu where ngaydatlich='" + date + "'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                doanhthungaysp = rs.getInt("tien");
            };

        } catch (Exception e) {
        }
        return doanhthungaysp;
    }

    public int doanhThudv(int Thang, String year) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=DUAN_1_QLSPA", "duan1", "123");
            String sql = "select sum(tongtien)tien from DON_HANG_DICH_VU_phu where MONTH(ngaydatlich)= '"
                    + Thang + "'" + " and year(ngaydatlich)='" + year + "'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                a = rs.getInt("tien");
            };

        } catch (Exception e) {
        }
        return a;
    }

    public int doanhThusp(int Thang, String year) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=DUAN_1_QLSPA", "duan1", "123");
            String sql = "select sum(tongtien)tien from DON_HANG_SAN_PHAM_phu where MONTH(ngaydatlich)= '"
                    + Thang + "'" + " and year(ngaydatlich)='" + year + "'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                b = rs.getInt("tien");
            };

        } catch (Exception e) {
        }
        return b;
    }

    public void timKiemDonHang(String txtTimkiem, JTable table, int so) {
        String header0[] = {"Mã DH", "Tên KH", "Tổng tiền", "Giờ đặt", "Ngày đặt", "TT Thanh Toán"};
        model = new DefaultTableModel(header0, 0);
        try {
            model.setRowCount(0);
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=DUAN_1_QLSPA", "duan1", "123");
            String sql = "select * from dhct where tenkh like N'%" + txtTimkiem + "%' and ttdonhang="+ so;
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object[] row = {
                    rs.getString(1),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getBoolean(7) ? "Đã thanh toán" : "Chưa thanh toán"};
                model.addRow(row);
            }
            table.setModel(model);
        } catch (Exception e) {
        }
    }
     public void timKiemDonHangDH(String txtTimkiem, JTable table, int so) {
        String header0[] = {"Mã DH", "Tên KH", "Tổng tiền", "Giờ đặt", "Ngày đặt", "TT Đơn Hàng"};
        model = new DefaultTableModel(header0, 0);
        try {
            model.setRowCount(0);
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=DUAN_1_QLSPA", "duan1", "123");
            String sql = "select * from dhct where tenkh like N'%" + txtTimkiem + "%' and ttdonhang="+ so;
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object[] row = {
                    rs.getString(1),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getString(5),
                    rs.getString(6),
                    "Đã hủy"};
                model.addRow(row);
            }
            table.setModel(model);
        } catch (Exception e) {
        }
    }

}
