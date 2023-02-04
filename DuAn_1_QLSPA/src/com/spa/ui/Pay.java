/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spa.ui;

import com.spa.dao.DichVuDAO;
import com.spa.dao.DonHangChiTietDAO;
import com.spa.dao.SanPhamDAO;
import com.spa.entity.DichVu;
import com.spa.entity.DonHangChiTiet;
import com.spa.entity.KhachHang;
import com.spa.entity.SanPham;
import com.spa.tienich.Find;
import com.spa.tienich.XDate;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class Pay extends javax.swing.JFrame {

    /**
     * Creates new form Reserve
     */
    DefaultTableModel modelCTT, modelDH;
    DichVuDAO dvdao = new DichVuDAO();
    SanPhamDAO spdao = new SanPhamDAO();
    DonHangChiTietDAO dhdao = new DonHangChiTietDAO();
    ThanhCong tc = new ThanhCong();
    Find f = new Find();
     public static void setColor(JPanel j){
         j.setBackground(new Color(26,52,143));
     }
     public static void resetColor(JPanel j){
         j.setBackground(new Color(245,245,245));
     }
    public Pay() {
        initComponents();
        this.setLocationRelativeTo(null);
        DefaultTableCellRenderer head_render = new DefaultTableCellRenderer();
        head_render.setPreferredSize(new Dimension(30, 40));
        head_render.setBackground(new Color(32, 136, 203));
        head_render.setForeground(new Color(255, 255, 255));
        //
        tblCTT.getTableHeader().setDefaultRenderer(head_render);
        tblCTT.setRowHeight(35);
        //
        tblDH.getTableHeader().setDefaultRenderer(head_render);
        tblDH.setRowHeight(35);
        //
        String header0[] = {"Mã DH", "Tên KH", "Tổng tiền", "Giờ đặt", "Ngày đặt", "TT Thanh Toán"};
        modelCTT = new DefaultTableModel(header0, 0);
        tblCTT.setModel(modelCTT);
        //
        String header2[] = {"Mã DH", "Tên KH", "Tổng tiền", "Giờ đặt", "Ngày đặt", "TT Đơn Hàng"};
        modelDH = new DefaultTableModel(header2, 0);
        tblDH.setModel(modelDH);
        ngay.setDate(new java.util.Date());
        fillTableCTT();
        fillTableDH();
    }

    void fillTableCTT() {
        modelCTT.setRowCount(0);
        try {
            List<DonHangChiTiet> list = dhdao.selectAll();
            for (DonHangChiTiet dh : list) {
                Object[] row = {
                    dh.getMadh(),
                    dh.getTenkh(),
                    dh.getTongtien(),
                    dh.getGiodat(),
                    dh.getNgaydat(),
                    dh.isTtThanhToan() ? "Đã thanh toán" : "Chưa thanh toán"
                };
                modelCTT.addRow(row);
            }
            tblCTT.setModel(modelCTT);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void fillTableDH() {
        modelDH.setRowCount(0);
        try {
            List<DonHangChiTiet> list = dhdao.selectDH();
            for (DonHangChiTiet dh : list) {
                Object[] row = {
                    dh.getMadh(),
                    dh.getTenkh(),
                    dh.getTongtien(),
                    dh.getGiodat(),
                    dh.getNgaydat(),
                    "Đã hủy"
                };
                modelDH.addRow(row);
            }
            tblDH.setModel(modelDH);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void thanhToan() {
        int row = tblCTT.getSelectedRow();
        if (row == -1) {
            return;
        }
        dhdao.ThanhToan((String) tblCTT.getValueAt(row, 0));
        fillTableCTT();
    }

    void UndoThanhToan() {
        int row = tblCTT.getSelectedRow();
        if (row == -1) {
            return;
        }
        dhdao.RThanhToan((String) tblCTT.getValueAt(row, 0));
        fillTableCTT();
    }

    void Huy() {
        int row = tblCTT.getSelectedRow();
        if (row == -1) {
            return;
        }
        dhdao.Huy((String) tblCTT.getValueAt(row, 0));
        fillTableCTT();
        fillTableDH();
    }

    void UndoHuy() {
        int row = tblDH.getSelectedRow();
        if (row == -1) {
            return;
        }
        dhdao.RHuy((String) tblDH.getValueAt(row, 0));
        fillTableCTT();
        fillTableDH();
    }

    void Delete() {
        int row = tblDH.getSelectedRow();
        if (row == -1) {
            return;
        }
        dhdao.delete((String) tblDH.getValueAt(row, 0));
        fillTableDH();
    }

    void XemLich(String ngay) {
        modelCTT.setRowCount(0);
        try {
            List<DonHangChiTiet> list = dhdao.XemLich(ngay);
            if (list.size() == 0) {
                JOptionPane.showMessageDialog(rootPane, "Không tìm thấy lịch phù hợp!");
            }
            for (DonHangChiTiet dh : list) {
                Object[] row = {
                    dh.getMadh(),
                    dh.getTenkh(),
                    dh.getTongtien(),
                    dh.getGiodat(),
                    dh.getNgaydat(),
                    dh.isTtThanhToan() ? "Đã thanh toán" : "Chưa thanh toán"
                };
                modelCTT.addRow(row);
            }
            tblCTT.setModel(modelCTT);
        } catch (Exception e) {
            System.out.println(e);
        }
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
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        body = new javax.swing.JPanel();
        jpCTT = new javax.swing.JPanel();
        txtTimkiem = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCTT = new javax.swing.JTable();
        btnThem = new javax.swing.JButton();
        btnThem2 = new javax.swing.JButton();
        btnThem4 = new javax.swing.JButton();
        ngay = new com.toedter.calendar.JDateChooser();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jpDH = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtTimkiem1 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblDH = new javax.swing.JTable();
        btnThem5 = new javax.swing.JButton();
        btnThem6 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        j1 = new javax.swing.JPanel();
        j2 = new javax.swing.JPanel();
        j3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(245, 245, 245));

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jButton1.setText("Đã hủy");
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jButton2.setText("Đơn hàng");
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        body.setLayout(new java.awt.CardLayout());

        jpCTT.setBackground(new java.awt.Color(245, 245, 245));

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

        tblCTT.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        tblCTT.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblCTT);

        btnThem.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconImages/pay.png"))); // NOI18N
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnThem2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btnThem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconImages/cancel.png"))); // NOI18N
        btnThem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem2ActionPerformed(evt);
            }
        });

        btnThem4.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btnThem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconImages/restore.png"))); // NOI18N
        btnThem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem4ActionPerformed(evt);
            }
        });

        ngay.setBackground(new java.awt.Color(122, 184, 225));
        ngay.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconImages/icons8_view_schedule_50px.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconImages/icons8_list_of_parts_50px.png"))); // NOI18N
        jButton5.setToolTipText("");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpCTTLayout = new javax.swing.GroupLayout(jpCTT);
        jpCTT.setLayout(jpCTTLayout);
        jpCTTLayout.setHorizontalGroup(
            jpCTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCTTLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpCTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jpCTTLayout.createSequentialGroup()
                        .addComponent(ngay, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                        .addComponent(txtTimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnThem4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnThem2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jpCTTLayout.setVerticalGroup(
            jpCTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCTTLayout.createSequentialGroup()
                .addGap(0, 40, Short.MAX_VALUE)
                .addGroup(jpCTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpCTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ngay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThem4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThem2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(txtTimkiem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        body.add(jpCTT, "card2");

        jpDH.setBackground(new java.awt.Color(245, 245, 245));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel13.setText("Tìm kiếm :");

        txtTimkiem1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtTimkiem1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txtTimkiem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtTimkiem1MousePressed(evt);
            }
        });
        txtTimkiem1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimkiem1KeyReleased(evt);
            }
        });

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconImages/icons8_search_more_50px.png"))); // NOI18N

        tblDH.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        tblDH.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane5.setViewportView(tblDH);

        btnThem5.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btnThem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconImages/iconXoa.png"))); // NOI18N
        btnThem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem5ActionPerformed(evt);
            }
        });

        btnThem6.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btnThem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconImages/restore.png"))); // NOI18N
        btnThem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpDHLayout = new javax.swing.GroupLayout(jpDH);
        jpDH.setLayout(jpDHLayout);
        jpDHLayout.setHorizontalGroup(
            jpDHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDHLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1060, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jpDHLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTimkiem1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnThem5, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnThem6, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpDHLayout.setVerticalGroup(
            jpDHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDHLayout.createSequentialGroup()
                .addGap(0, 34, Short.MAX_VALUE)
                .addGroup(jpDHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpDHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTimkiem1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13))
                    .addComponent(jLabel14)
                    .addComponent(btnThem5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        body.add(jpDH, "card2");

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jButton3.setText("Hôm nay");
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        j1.setBackground(new java.awt.Color(26, 52, 143));

        javax.swing.GroupLayout j1Layout = new javax.swing.GroupLayout(j1);
        j1.setLayout(j1Layout);
        j1Layout.setHorizontalGroup(
            j1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 144, Short.MAX_VALUE)
        );
        j1Layout.setVerticalGroup(
            j1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        j2.setBackground(new java.awt.Color(245, 245, 245));

        javax.swing.GroupLayout j2Layout = new javax.swing.GroupLayout(j2);
        j2.setLayout(j2Layout);
        j2Layout.setHorizontalGroup(
            j2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        j2Layout.setVerticalGroup(
            j2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 11, Short.MAX_VALUE)
        );

        j3.setBackground(new java.awt.Color(245, 245, 245));

        javax.swing.GroupLayout j3Layout = new javax.swing.GroupLayout(j3);
        j3.setLayout(j3Layout);
        j3Layout.setHorizontalGroup(
            j3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        j3Layout.setVerticalGroup(
            j3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(body, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(j1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                            .addComponent(j2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                            .addComponent(j3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {j1, jButton1, jButton2, jButton3});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(j2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(j1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(j3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(body, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, jButton2, jButton3});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        body.removeAll();
        body.repaint();
        body.revalidate();
        body.add(jpCTT);
        body.repaint();
        body.revalidate();
        setColor(j1);
        resetColor(j2);
        resetColor(j3);
        fillTableCTT();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        body.removeAll();
        body.repaint();
        body.revalidate();
        body.add(jpDH);
        body.repaint();
        body.revalidate();
        setColor(j2);
        resetColor(j1);
        resetColor(j3);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtTimkiemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimkiemMousePressed

    }//GEN-LAST:event_txtTimkiemMousePressed

    private void txtTimkiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimkiemKeyReleased

        f.timKiemDonHang(txtTimkiem.getText(), tblCTT, 0);
    }//GEN-LAST:event_txtTimkiemKeyReleased

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        thanhToan();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnThem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem4ActionPerformed
        UndoThanhToan();
    }//GEN-LAST:event_btnThem4ActionPerformed

    private void btnThem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem2ActionPerformed
        Huy();
    }//GEN-LAST:event_btnThem2ActionPerformed

    private void txtTimkiem1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimkiem1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimkiem1MousePressed

    private void txtTimkiem1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimkiem1KeyReleased
        f.timKiemDonHangDH(txtTimkiem1.getText(), tblDH, 1);
    }//GEN-LAST:event_txtTimkiem1KeyReleased

    private void btnThem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem5ActionPerformed
        Delete();
    }//GEN-LAST:event_btnThem5ActionPerformed

    private void btnThem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem6ActionPerformed
        UndoHuy();
    }//GEN-LAST:event_btnThem6ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        body.removeAll();
        body.repaint();
        body.revalidate();
        body.add(jpCTT);
        body.repaint();
        body.revalidate();
        setColor(j3);
        resetColor(j2);
        resetColor(j1);
        String date = XDate.toString(new java.util.Date(), "MM/dd/YYYY");
        XemLich(date);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String date = XDate.toString(ngay.getDate(), "MM/dd/YYYY");
        XemLich(date);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        fillTableCTT();
    }//GEN-LAST:event_jButton5ActionPerformed

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
            java.util.logging.Logger.getLogger(Pay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pay().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel body;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThem2;
    private javax.swing.JButton btnThem4;
    private javax.swing.JButton btnThem5;
    private javax.swing.JButton btnThem6;
    private javax.swing.JPanel j1;
    private javax.swing.JPanel j2;
    private javax.swing.JPanel j3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JPanel jpCTT;
    private javax.swing.JPanel jpDH;
    private com.toedter.calendar.JDateChooser ngay;
    private javax.swing.JTable tblCTT;
    private javax.swing.JTable tblDH;
    private javax.swing.JTextField txtTimkiem;
    private javax.swing.JTextField txtTimkiem1;
    // End of variables declaration//GEN-END:variables
}
