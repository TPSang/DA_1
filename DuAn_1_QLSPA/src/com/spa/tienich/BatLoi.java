/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spa.tienich;

import com.spa.dao.DichVuDAO;
import com.spa.dao.KhachHangDAO;
import com.spa.dao.NhanVienDAO;
import com.spa.dao.SanPhamDAO;
import com.spa.entity.DichVu;
import com.spa.entity.KhachHang;
import com.spa.entity.NhanVien;
import com.spa.entity.SanPham;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author ADMIN
 */
public class BatLoi {
    String patternTen = "([A-Za-z]+\\s?)+";
    String patternEmail = "\\w+@\\w+(.\\w+){1,2}";
    String patternSDT = "0\\d{9}";
    NhanVienDAO nvdao = new NhanVienDAO();
    KhachHangDAO khdao= new KhachHangDAO();
    SanPhamDAO spdao= new SanPhamDAO();
    DichVuDAO dvdao= new DichVuDAO();
    public void deTrong(JTextField txt,JLabel lbl){
        if( txt.getText().isEmpty()){
            lbl.setForeground(Color.red);
            lbl.setFont(new Font("Tahoma",Font.ITALIC,20));
            lbl.setText("Không được để trống!");
            return;
        }else{
            lbl.setText("");
        }
    }
     public void kiemTraManv(JTextField txt,JLabel lbl) {
        String ma = txt.getText();       
        NhanVien nv = nvdao.selectById(ma);
        if(nv != null){
            lbl.setForeground(Color.red);
            lbl.setFont(new Font("Tahoma",Font.ITALIC,20));
            lbl.setText("(Mã đã tồn tại)");
        }
        else{
            lbl.setText("");
        }
    }
      public void kiemTraMakh(JTextField txt,JLabel lbl) {
        String ma = txt.getText();       
        KhachHang nv = khdao.selectById(ma);
        if(nv != null){
            lbl.setForeground(Color.red);
            lbl.setFont(new Font("Tahoma",Font.ITALIC,20));
            lbl.setText("(Mã đã tồn tại)");
        }
        else{
            lbl.setText("");
        }
    }
       public void kiemTraMadv(JTextField txt,JLabel lbl) {
        String ma = txt.getText();       
        DichVu nv =dvdao.selectById(ma);
        if(nv != null){
            lbl.setForeground(Color.red);
            lbl.setFont(new Font("Tahoma",Font.ITALIC,20));
            lbl.setText("(Mã đã tồn tại)");
        }
        else{
            lbl.setText("");
        }
    }
        public void kiemTraMasp(JTextField txt,JLabel lbl) {
        String ma = txt.getText();       
        SanPham nv = spdao.selectById(ma);
        if(nv != null){
            lbl.setForeground(Color.red);
            lbl.setFont(new Font("Tahoma",Font.ITALIC,20));
            lbl.setText("(Mã đã tồn tại)");
        }
        else{
            lbl.setText("");
        }
    }
    public void kiemTraPatternTen(JTextField txt,JLabel lbl){
        if (txt.getText().matches(patternTen) == false) {
            lbl.setForeground(Color.red);
            lbl.setFont(new Font("Tahoma",Font.ITALIC,20));
            lbl.setText("Tên không đúng định dạng!");
            return;
        }else{
            lbl.setText("");
        }
    }
    public void kiemTraPatternSdt(JTextField txt,JLabel lbl){
        if (txt.getText().matches(patternSDT) == false) {
            lbl.setForeground(Color.red);
            lbl.setFont(new Font("Tahoma",Font.ITALIC,20));
            lbl.setText("SDT không đúng định dạng!");
            return;
        }else{
            lbl.setText("");
        }
    }
    public void kiemTraPatternEmail(JTextField txt,JLabel lbl){
        if (txt.getText().matches(patternEmail) == false) {
            lbl.setForeground(Color.red);
            lbl.setFont(new Font("Tahoma",Font.ITALIC,20));
            lbl.setText("Email không đúng định dạng!");
            return;
        }else{
            lbl.setText("");
        }
    }
}
