/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spa.entity;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class DonHangChiTiet {
    private String madh;
    private String makh;
    private String tenkh;
    private String tongtien;
    private String giodat;
    private Date ngaydat;
    private boolean ttThanhToan;
    private boolean ttDonHang;

    public String getMadh() {
        return madh;
    }

    public void setMadh(String madh) {
        this.madh = madh;
    }

    public String getMakh() {
        return makh;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }

    public String getTenkh() {
        return tenkh;
    }

    public void setTenkh(String tenkh) {
        this.tenkh = tenkh;
    }

    public String getTongtien() {
        return tongtien;
    }

    public void setTongtien(String tongtien) {
        this.tongtien = tongtien;
    }

    public String getGiodat() {
        return giodat;
    }

    public void setGiodat(String giodat) {
        this.giodat = giodat;
    }

    public Date getNgaydat() {
        return ngaydat;
    }

    public void setNgaydat(Date ngaydat) {
        this.ngaydat = ngaydat;
    }

    public boolean isTtThanhToan() {
        return ttThanhToan;
    }

    public void setTtThanhToan(boolean ttThanhToan) {
        this.ttThanhToan = ttThanhToan;
    }

    public boolean isTtDonHang() {
        return ttDonHang;
    }

    public void setTtDonHang(boolean ttDonHang) {
        this.ttDonHang = ttDonHang;
    }



    
    
    
}
