/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spa.ui;

import com.spa.dao.DichVuDAO;
import com.spa.dao.DonHangChiTietDAO;
import com.spa.dao.KhachHangDAO;
import com.spa.dao.SanPhamDAO;
import com.spa.entity.DichVu;
import com.spa.entity.DonHangChiTiet;
import com.spa.entity.KhachHang;
import com.spa.entity.SanPham;
import com.spa.tienich.Find;
import com.spa.tienich.GuiMail;
import com.spa.tienich.PDF;
import com.spa.tienich.XDate;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class Reserve extends javax.swing.JFrame {

    /**
     * Creates new form Reserve
     */
    DefaultTableModel model, modelHoaDon, modelSanPham;
    DichVuDAO dvdao = new DichVuDAO();
    KhachHangDAO khdao= new KhachHangDAO();
    SanPhamDAO spdao = new SanPhamDAO();
    DonHangChiTietDAO dhdao = new DonHangChiTietDAO();
    ThanhCong tc = new ThanhCong();
    Find f = new Find();

    public Reserve() {
        initComponents();
        ngaydl.setDate(new java.util.Date());
        lblMa.setText(Manage_Customer.makhachhang);
        lblTen.setText(Manage_Customer.tenkh);
        DefaultTableCellRenderer head_render = new DefaultTableCellRenderer();
        head_render.setPreferredSize(new Dimension(30, 40));
        head_render.setBackground(new Color(32, 136, 203));
        head_render.setForeground(new Color(255, 255, 255));
        //
        tblDichVu.getTableHeader().setDefaultRenderer(head_render);
        tblDichVu.setRowHeight(35);
        //
        tblSanPham.getTableHeader().setDefaultRenderer(head_render);
        tblSanPham.setRowHeight(35);
        //
        tblHoaDon.getTableHeader().setDefaultRenderer(head_render);
        tblHoaDon.setRowHeight(35);
        this.setLocationRelativeTo(null);
        String header[] = {"Tên dịch vụ", "Chi phí"};
        model = new DefaultTableModel(header, 0);
        //
        String header1[] = {"Tên DV", "Chi Phi", "Số Lượng", "Tổng"};
        modelHoaDon = new DefaultTableModel(header1, 0);
        tblHoaDon.setModel(modelHoaDon);
        //
        String header2[] = {"Tên sản phẩm", "Giá"};
        modelSanPham = new DefaultTableModel(header2, 0);
        tblSanPham.setModel(modelSanPham);
        fillTableSanPham();
        fillTableDichVu();
    }

    void fillTableSanPham() {
        modelSanPham.setRowCount(0);
        try {
            List<SanPham> list = spdao.selectAll();
            for (SanPham sp : list) {
                Object[] row = {
                    sp.getTenSP(),
                    sp.getGia()
                };
                modelSanPham.addRow(row);
            }
            tblSanPham.setModel(modelSanPham);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void fillTableDichVu() {
        model.setRowCount(0);
        try {
            List<DichVu> list = dvdao.selectAll();
            for (DichVu dv : list) {
                Object[] row = {
                    dv.getTenDV(),
                    dv.getChiPhi()
                };
                model.addRow(row);
            }
            tblDichVu.setModel(model);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void fillTableHoaDon() {
        String input = JOptionPane.showInputDialog("Nhập số lượng...").toString();
        int sl = Integer.parseInt(input);
        String tendv = (String) tblDichVu.getValueAt(tblDichVu.getSelectedRow(), 0);
        int chiphi = (Integer) tblDichVu.getValueAt(tblDichVu.getSelectedRow(), 1);
        Object[] row = {
            tendv,
            chiphi,
            sl,
            chiphi * sl
        };
        modelHoaDon.addRow(row);
        tblHoaDon.setModel(modelHoaDon);
        tinhtien();
    }

    void fillTableHoaDon2() {
        String input = JOptionPane.showInputDialog("Nhập số lượng...").toString();
        int sl = Integer.parseInt(input);
        String tensp = (String) tblSanPham.getValueAt(tblSanPham.getSelectedRow(), 0);
        int gia = (Integer) tblSanPham.getValueAt(tblSanPham.getSelectedRow(), 1);
        Object[] row = {
            tensp,
            gia,
            sl,
            gia * sl
        };
        modelHoaDon.addRow(row);
        tblHoaDon.setModel(modelHoaDon);
        tinhtien();
    }

    void tinhtien() {
        int tongtien = 0;
        int n = tblHoaDon.getRowCount();
        for (int i = 0; i < n; i++) {
            tongtien += (Integer) tblHoaDon.getValueAt(i, 3);
        }
        lblTien.setText(Integer.toString(tongtien));
    }

    void Xoa() {
        int i = tblHoaDon.getSelectedRow();
        if (i == -1) {
            return;
        }
        modelHoaDon.removeRow(i);
        tinhtien();
    }

    void AddHoaDon() {
        DonHangChiTiet dh = new DonHangChiTiet();
        dh.setMadh("DH-" + RanDom.ranDomMa());
        dh.setMakh(lblMa.getText());
        dh.setTenkh(lblTen.getText());
        dh.setTongtien(lblTien.getText());
        dh.setGiodat(txtGio.getText());
        dh.setNgaydat(ngaydl.getDate());
        dh.setTtThanhToan(false);
        dh.setTtDonHang(false);
        dhdao.insert(dh);
        tc.show();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        body = new javax.swing.JPanel();
        jpDichVu = new javax.swing.JPanel();
        txtTimkiem = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDichVu = new javax.swing.JTable();
        btnThem = new javax.swing.JButton();
        jpHoaDon = new javax.swing.JPanel();
        ngaydl = new com.toedter.calendar.JDateChooser();
        a = new javax.swing.JLabel();
        lblMa = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        a1 = new javax.swing.JLabel();
        lblTen = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        c = new javax.swing.JLabel();
        lblTien = new javax.swing.JLabel();
        btnXoa = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        b = new javax.swing.JLabel();
        a2 = new javax.swing.JLabel();
        txtGio = new javax.swing.JTextField();
        jpSanPham = new javax.swing.JPanel();
        jpDichVu1 = new javax.swing.JPanel();
        txtTimkiem1 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        btnThem1 = new javax.swing.JButton();
        JTrenCung = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        j1 = new javax.swing.JPanel();
        btnSanPham = new javax.swing.JButton();
        j2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        j3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        body.setBackground(new java.awt.Color(245, 245, 245));
        body.setLayout(new java.awt.CardLayout());

        jpDichVu.setBackground(new java.awt.Color(245, 245, 245));
        jpDichVu.setMaximumSize(new java.awt.Dimension(680, 680));
        jpDichVu.setMinimumSize(new java.awt.Dimension(680, 680));

        txtTimkiem.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtTimkiem.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txtTimkiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtTimkiemMousePressed(evt);
            }
        });
        txtTimkiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimkiemKeyReleased(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconImages/icons8_search_more_50px.png"))); // NOI18N

        jScrollPane1.setMaximumSize(new java.awt.Dimension(452, 402));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(452, 402));

        tblDichVu.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        tblDichVu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblDichVu.setMaximumSize(new java.awt.Dimension(300, 64));
        tblDichVu.setMinimumSize(new java.awt.Dimension(300, 64));
        jScrollPane1.setViewportView(tblDichVu);

        btnThem.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconImages/icons8_add_shopping_cart_50px.png"))); // NOI18N
        btnThem.setContentAreaFilled(false);
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpDichVuLayout = new javax.swing.GroupLayout(jpDichVu);
        jpDichVu.setLayout(jpDichVuLayout);
        jpDichVuLayout.setHorizontalGroup(
            jpDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpDichVuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jpDichVuLayout.createSequentialGroup()
                        .addGap(0, 160, Short.MAX_VALUE)
                        .addComponent(txtTimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addGap(110, 110, 110)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jpDichVuLayout.setVerticalGroup(
            jpDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDichVuLayout.createSequentialGroup()
                .addGap(0, 49, Short.MAX_VALUE)
                .addGroup(jpDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        body.add(jpDichVu, "card2");

        jpHoaDon.setBackground(new java.awt.Color(245, 245, 245));
        jpHoaDon.setMaximumSize(new java.awt.Dimension(680, 680));
        jpHoaDon.setMinimumSize(new java.awt.Dimension(680, 680));

        ngaydl.setBackground(new java.awt.Color(245, 245, 245));
        ngaydl.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        ngaydl.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        ngaydl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ngaydlMouseClicked(evt);
            }
        });

        a.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        a.setText("Mã Kh");

        lblMa.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblMa.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconImages/icons8_Plus_120px.png"))); // NOI18N
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        a1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        a1.setText("Tên Kh");

        lblTen.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblTen.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jScrollPane2.setMaximumSize(new java.awt.Dimension(452, 402));
        jScrollPane2.setMinimumSize(new java.awt.Dimension(452, 402));

        tblHoaDon.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblHoaDon.setMaximumSize(new java.awt.Dimension(300, 64));
        tblHoaDon.setMinimumSize(new java.awt.Dimension(300, 64));
        jScrollPane2.setViewportView(tblHoaDon);

        c.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        c.setText("Tổng tiền");

        lblTien.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        lblTien.setText("0");

        btnXoa.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconImages/iconXoa.png"))); // NOI18N
        btnXoa.setContentAreaFilled(false);
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconImages/iconDatLich2.png"))); // NOI18N
        jButton5.setText("Đặt lịch");
        jButton5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton5.setContentAreaFilled(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        b.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        b.setText("Ngày đặt");

        a2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        a2.setText("Giờ đặt");

        txtGio.setBackground(new java.awt.Color(245, 245, 245));
        txtGio.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtGio.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        javax.swing.GroupLayout jpHoaDonLayout = new javax.swing.GroupLayout(jpHoaDon);
        jpHoaDon.setLayout(jpHoaDonLayout);
        jpHoaDonLayout.setHorizontalGroup(
            jpHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpHoaDonLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jpHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpHoaDonLayout.createSequentialGroup()
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jpHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpHoaDonLayout.createSequentialGroup()
                                .addComponent(c)
                                .addGap(43, 43, 43)
                                .addComponent(lblTien))
                            .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jpHoaDonLayout.createSequentialGroup()
                            .addGroup(jpHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(a2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(a, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jpHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblMa, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtGio, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jpHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(a1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(b, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(12, 12, 12)
                            .addGroup(jpHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(ngaydl, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                                .addComponent(lblTen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 622, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jpHoaDonLayout.setVerticalGroup(
            jpHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpHoaDonLayout.createSequentialGroup()
                .addGroup(jpHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpHoaDonLayout.createSequentialGroup()
                        .addContainerGap(23, Short.MAX_VALUE)
                        .addGroup(jpHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(a, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMa, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(a1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTen, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jpHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jpHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(a2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtGio, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(b, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ngaydl, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34))
                    .addGroup(jpHoaDonLayout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jpHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTien)
                        .addComponent(c))
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jButton5)
                .addGap(26, 26, 26))
        );

        body.add(jpHoaDon, "card3");

        jpSanPham.setBackground(new java.awt.Color(245, 245, 245));
        jpSanPham.setMaximumSize(new java.awt.Dimension(680, 680));
        jpSanPham.setMinimumSize(new java.awt.Dimension(680, 680));

        jpDichVu1.setBackground(new java.awt.Color(245, 245, 245));

        txtTimkiem1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtTimkiem1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txtTimkiem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtTimkiem1MousePressed(evt);
            }
        });
        txtTimkiem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimkiem1ActionPerformed(evt);
            }
        });
        txtTimkiem1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimkiem1KeyReleased(evt);
            }
        });

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconImages/icons8_search_more_50px.png"))); // NOI18N

        jScrollPane3.setMaximumSize(new java.awt.Dimension(452, 402));
        jScrollPane3.setMinimumSize(new java.awt.Dimension(452, 402));

        tblSanPham.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblSanPham.setMaximumSize(new java.awt.Dimension(300, 64));
        tblSanPham.setMinimumSize(new java.awt.Dimension(300, 64));
        jScrollPane3.setViewportView(tblSanPham);

        btnThem1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btnThem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconImages/icons8_add_shopping_cart_50px.png"))); // NOI18N
        btnThem1.setContentAreaFilled(false);
        btnThem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpDichVu1Layout = new javax.swing.GroupLayout(jpDichVu1);
        jpDichVu1.setLayout(jpDichVu1Layout);
        jpDichVu1Layout.setHorizontalGroup(
            jpDichVu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDichVu1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpDichVu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
                    .addGroup(jpDichVu1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTimkiem1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addGap(97, 97, 97)
                        .addComponent(btnThem1)))
                .addContainerGap())
        );
        jpDichVu1Layout.setVerticalGroup(
            jpDichVu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDichVu1Layout.createSequentialGroup()
                .addGap(0, 49, Short.MAX_VALUE)
                .addGroup(jpDichVu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTimkiem1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(btnThem1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpSanPhamLayout = new javax.swing.GroupLayout(jpSanPham);
        jpSanPham.setLayout(jpSanPhamLayout);
        jpSanPhamLayout.setHorizontalGroup(
            jpSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 680, Short.MAX_VALUE)
            .addGroup(jpSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpSanPhamLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jpDichVu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jpSanPhamLayout.setVerticalGroup(
            jpSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 680, Short.MAX_VALUE)
            .addGroup(jpSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpSanPhamLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jpDichVu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        body.add(jpSanPham, "card4");

        JTrenCung.setBackground(new java.awt.Color(245, 245, 245));

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jButton2.setText("Dịch Vụ");
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        j1.setBackground(new java.awt.Color(26, 52, 143));
        j1.setMaximumSize(new java.awt.Dimension(121, 8));
        j1.setPreferredSize(new java.awt.Dimension(121, 8));

        javax.swing.GroupLayout j1Layout = new javax.swing.GroupLayout(j1);
        j1.setLayout(j1Layout);
        j1Layout.setHorizontalGroup(
            j1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 121, Short.MAX_VALUE)
        );
        j1Layout.setVerticalGroup(
            j1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        btnSanPham.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btnSanPham.setText("Sản Phẩm");
        btnSanPham.setContentAreaFilled(false);
        btnSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSanPhamActionPerformed(evt);
            }
        });

        j2.setBackground(new java.awt.Color(245, 245, 245));
        j2.setMaximumSize(new java.awt.Dimension(121, 8));
        j2.setMinimumSize(new java.awt.Dimension(121, 8));
        j2.setPreferredSize(new java.awt.Dimension(121, 8));

        javax.swing.GroupLayout j2Layout = new javax.swing.GroupLayout(j2);
        j2.setLayout(j2Layout);
        j2Layout.setHorizontalGroup(
            j2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 121, Short.MAX_VALUE)
        );
        j2Layout.setVerticalGroup(
            j2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jButton1.setText("Hóa Đơn");
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        j3.setBackground(new java.awt.Color(245, 245, 245));

        javax.swing.GroupLayout j3Layout = new javax.swing.GroupLayout(j3);
        j3.setLayout(j3Layout);
        j3Layout.setHorizontalGroup(
            j3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 121, Short.MAX_VALUE)
        );
        j3Layout.setVerticalGroup(
            j3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout JTrenCungLayout = new javax.swing.GroupLayout(JTrenCung);
        JTrenCung.setLayout(JTrenCungLayout);
        JTrenCungLayout.setHorizontalGroup(
            JTrenCungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JTrenCungLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(JTrenCungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(j1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(JTrenCungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(j2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(JTrenCungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(j3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JTrenCungLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnSanPham, jButton1, jButton2});

        JTrenCungLayout.setVerticalGroup(
            JTrenCungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JTrenCungLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JTrenCungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addGroup(JTrenCungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(btnSanPham)))
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(JTrenCungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(j2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(j1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(j3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JTrenCung, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(JTrenCung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(body, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 678, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        body.removeAll();
        body.repaint();
        body.revalidate();
        body.add(jpDichVu);
        body.repaint();
        body.revalidate();
        Pay.setColor(j1);
        Pay.resetColor(j2);
        Pay.resetColor(j3);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        body.removeAll();
        body.repaint();
        body.revalidate();
        body.add(jpHoaDon);
        txtGio.grabFocus();
        body.repaint();
        body.revalidate();
        Pay.setColor(j3);
        Pay.resetColor(j2);
        Pay.resetColor(j1);
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtTimkiemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimkiemMousePressed

    }//GEN-LAST:event_txtTimkiemMousePressed

    private void txtTimkiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimkiemKeyReleased

        f.timKiemDichVu2(txtTimkiem.getText(), tblDichVu);
    }//GEN-LAST:event_txtTimkiemKeyReleased

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        fillTableHoaDon();
//        ThemVaoChiTiet();
//        tinhtien();

    }//GEN-LAST:event_btnThemActionPerformed

    private void ngaydlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ngaydlMouseClicked

    }//GEN-LAST:event_ngaydlMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.dispose();
        new Manage_Customer().show();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        Xoa();

    }//GEN-LAST:event_btnXoaActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        AddHoaDon();
        PDF.taoFilePdf(lblTen.getText(), tblHoaDon, tblHoaDon.getRowCount(),lblTien.getText(),XDate.toString(ngaydl.getDate(), "dd/MM/yyyy"));
        KhachHang kh=khdao.selectById(lblMa.getText());
        System.out.println(kh.getEmail());
        GuiMail.SendMail(kh.getEmail());
        modelHoaDon.setRowCount(0);
        txtGio.setText("");
        lblTien.setText("");
        lblTen.setText("");
        lblMa.setText("");
       
    }//GEN-LAST:event_jButton5ActionPerformed

    private void txtTimkiem1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimkiem1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimkiem1MousePressed

    private void txtTimkiem1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimkiem1KeyReleased
        f.timKiemSanPham2(txtTimkiem1.getText(), tblSanPham);
    }//GEN-LAST:event_txtTimkiem1KeyReleased

    private void btnThem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem1ActionPerformed
        fillTableHoaDon2();
    }//GEN-LAST:event_btnThem1ActionPerformed

    private void btnSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSanPhamActionPerformed
        body.removeAll();
        body.repaint();
        body.revalidate();
        body.add(jpSanPham);
        body.repaint();
        body.revalidate();
        Pay.setColor(j2);
        Pay.resetColor(j1);
        Pay.resetColor(j3);
    }//GEN-LAST:event_btnSanPhamActionPerformed

    private void txtTimkiem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimkiem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimkiem1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Reserve.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Reserve.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Reserve.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reserve.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Reserve().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JTrenCung;
    private javax.swing.JLabel a;
    private javax.swing.JLabel a1;
    private javax.swing.JLabel a2;
    private javax.swing.JLabel b;
    private javax.swing.JPanel body;
    private javax.swing.JButton btnSanPham;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThem1;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel c;
    private javax.swing.JPanel j1;
    private javax.swing.JPanel j2;
    private javax.swing.JPanel j3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel jpDichVu;
    private javax.swing.JPanel jpDichVu1;
    private javax.swing.JPanel jpHoaDon;
    private javax.swing.JPanel jpSanPham;
    private javax.swing.JLabel lblMa;
    private javax.swing.JLabel lblTen;
    private javax.swing.JLabel lblTien;
    private com.toedter.calendar.JDateChooser ngaydl;
    private javax.swing.JTable tblDichVu;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtGio;
    private javax.swing.JTextField txtTimkiem;
    private javax.swing.JTextField txtTimkiem1;
    // End of variables declaration//GEN-END:variables
}
