package com.spa.entity;

public class DichVu {
    private String maDV;
    private String tenDV;
    private int chiPhi;


    @Override
    public String toString() {
        return this.tenDV;
    }

    @Override
    public boolean equals(Object obj) {
        DichVu other = (DichVu) obj;
        return other.getMaDV().equals(this.getMaDV());
    }

    public String getMaDV() {
        return maDV;
    }

    public void setMaDV(String maDV) {
        this.maDV = maDV;
    }

    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }

    public int getChiPhi() {
        return chiPhi;
    }

    public void setChiPhi(int chiPhi) {
        this.chiPhi = chiPhi;
    }

    
}
