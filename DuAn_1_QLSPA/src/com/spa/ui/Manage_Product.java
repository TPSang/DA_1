/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spa.ui;

import com.spa.dao.NhanVienDAO;
import com.spa.dao.SanPhamDAO;
import com.spa.entity.NhanVien;
import com.spa.entity.SanPham;
import com.spa.tienich.BackgroundMenuBar;
import com.spa.tienich.Find;
import com.spa.tienich.XImage;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author ADMIN
 */
public class Manage_Product extends javax.swing.JFrame {

    
    void setColorlbl(JButton btn) {
        btn.setBackground(new Color(32, 136, 203));
        
    }
    void setColortxt(JTextField txt){
        txt.setForeground(new Color(0,0,0));
        txt.setText("");
    }
     void resetColortxt(JTextField txt){
        txt.setForeground(new Color(204,204,204));
    }

    void resetColorlbl(JButton btn) {
        btn.setBackground(new Color(204,204,204));
    }
    /**
     * Creates new form Manage_Staff
     */
     SanPhamDAO dao = new SanPhamDAO();
     ThanhCong tc= new ThanhCong();
    ThatBai tb= new ThatBai();
    int row = -1;
    DefaultTableModel model;
    public Manage_Product() {
        initComponents();
        this.setLocationRelativeTo(null);
        //menu
        BackgroundMenuBar bg= new BackgroundMenuBar();
        
        DefaultTableCellRenderer head_render = new DefaultTableCellRenderer();
        head_render.setPreferredSize(new Dimension(30, 40));
        head_render.setBackground(new Color(32, 136, 203));
        head_render.setForeground(new Color(255, 255, 255));
        //do rong cac cot
//        setColumnWidths(tblNhanVien,30, 150, 100, 100);
        tblSanPham.getTableHeader().setDefaultRenderer(head_render);
        
        tblSanPham.setRowHeight(35);
        //do rong cac cot
        String header[] = {"MaSP", "TenSP", "Gia"};
        model = new DefaultTableModel(header, 0);
        
         
        fillTable();
    }
//   public static void setColumnWidths(JTable table, int... widths) {
//    TableColumnModel columnModel = table.getColumnModel();
//    for (int i = 0; i < widths.length; i++) {
//        if (i < columnModel.getColumnCount()) {
//            columnModel.getColumn(i).setMaxWidth(widths[i]);
//        }
//        else break;
//    }
//}
    SanPham getForm() {
        SanPham sp = new SanPham();
        sp.setMaSP(txtMa.getText());
        boolean kt=false;
        for(int i=0;i<tblSanPham.getRowCount();i++){
            if(txtTen.getText().equalsIgnoreCase((String)tblSanPham.getValueAt(i, 1))){
                JOptionPane.showMessageDialog(rootPane, "ten sp da co");
                kt=true;
                break;
            }           
        }
     
      
            
        sp.setTenSP(txtTen.getText());
        sp.setGia(new Integer(txtGia.getText()));       
        sp.setHinhAnh(lblHinh.getToolTipText());
        return sp;
    }

    void fillTable() {

      
        model.setRowCount(0);
        try {

            List<SanPham> list = dao.selectAll();
            for (SanPham sp : list) {
                Object[] row = {
                    sp.getMaSP(),
                    sp.getTenSP(),
                    sp.getGia()
                };
                model.addRow(row);
            }
            tblSanPham.setModel(model);
        } catch (Exception e) {
            tb.show();
            System.out.println(e);
        }
    }
     void chonAnh() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            XImage.save(file); // lưu hình vào thư mục logos
            ImageIcon icon = XImage.read(file.getName()); // đọc hình từ logos
            lblHinh.setIcon(icon);
            lblHinh.setToolTipText(file.getName()); // giữ tên hình trong tooltip
        }
    }
     void add() {
        
        SanPham sp = this.getForm();
        try {
            dao.insert(sp);
            this.fillTable();
            this.clearForm();
            tc.show();
        } catch (Exception e) {
            tb.show();
        }
    }
       void clearForm() {
        setColortxt(txtMa);
        setColortxt(txtTen);
        setColortxt(txtGia);
        txtMa.setText("");
        txtTen.setText("");
        txtGia.setText("");
        txtMa.grabFocus();
        
    }
      void fillForm(String masp) {
        setColortxt(txtMa);
        setColortxt(txtTen);
        setColortxt(txtGia);
        SanPham sp = dao.selectById(masp);
        txtMa.setText(sp.getMaSP());
        txtTen.setText(sp.getTenSP());
        txtGia.setText(Integer.toString(sp.getGia()));

        if (sp.getHinhAnh() != null) {
            lblHinh.setToolTipText(sp.getHinhAnh());
            lblHinh.setIcon(XImage.read(sp.getHinhAnh()));
        }
    }
        void delete() {
        String masp = (String) tblSanPham.getValueAt(tblSanPham.getSelectedRow(), 0);
        try {
            dao.delete(masp);
            this.fillTable();
            this.clearForm();
            tc.show();
                    
        } catch (Exception e) {
            tb.show();
            System.out.println(e);
        }
    }
        void update() {
        SanPham sp = getForm();
        try {
            dao.update(sp);
            fillTable();
            clearForm();
            tc.show();
        } catch (Exception e) {
            System.out.println(e);
            tb.show();
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        fileChooser = new javax.swing.JFileChooser();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        txtTimkiem = new javax.swing.JTextField();
        btn5 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtTen = new javax.swing.JTextField();
        txtMa = new javax.swing.JTextField();
        txtGia = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblHinh = new javax.swing.JLabel();
        btn1 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jMenu = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Quản Lý Sản Phẩm");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        tblSanPham.setAutoCreateRowSorter(true);
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
        tblSanPham.setFocusable(false);
        tblSanPham.setGridColor(new java.awt.Color(255, 255, 255));
        tblSanPham.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblSanPham.setRowHeight(30);
        tblSanPham.setSelectionBackground(new java.awt.Color(232, 57, 95));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblSanPhamMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 1030, 340));

        txtTimkiem.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtTimkiem.setForeground(new java.awt.Color(204, 204, 204));
        txtTimkiem.setText("Nhap ten");
        txtTimkiem.setBorder(null);
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
        jPanel1.add(txtTimkiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, 272, 50));

        btn5.setBackground(new java.awt.Color(204, 204, 204));
        btn5.setBorder(null);
        btn5.setFocusable(false);
        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5ActionPerformed(evt);
            }
        });
        jPanel1.add(btn5, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, 272, 2));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconImages/icons8_search_more_50px.png"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 10, -1, 50));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel8.setText("Tìm kiếm :");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 392, 1028, 490));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTen.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtTen.setForeground(new java.awt.Color(204, 204, 204));
        txtTen.setText("Nhap ten");
        txtTen.setBorder(null);
        txtTen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtTenMousePressed(evt);
            }
        });
        jPanel2.add(txtTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(239, 264, 272, 47));

        txtMa.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtMa.setForeground(new java.awt.Color(204, 204, 204));
        txtMa.setText("Nhap ma");
        txtMa.setBorder(null);
        txtMa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtMaMousePressed(evt);
            }
        });
        jPanel2.add(txtMa, new org.netbeans.lib.awtextra.AbsoluteConstraints(242, 107, 272, 47));

        txtGia.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtGia.setForeground(new java.awt.Color(204, 204, 204));
        txtGia.setText("Nhap gia");
        txtGia.setBorder(null);
        txtGia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtGiaMousePressed(evt);
            }
        });
        jPanel2.add(txtGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(555, 107, 272, 47));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel1.setText("Mã SP");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(242, 66, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel2.setText("Tên SP");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(239, 221, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel5.setText("Giá");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(555, 66, -1, -1));

        lblHinh.setBackground(new java.awt.Color(51, 51, 255));
        lblHinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhMouseClicked(evt);
            }
        });
        jPanel2.add(lblHinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 65, 215, 248));

        btn1.setBackground(new java.awt.Color(204, 204, 204));
        btn1.setBorder(null);
        btn1.setFocusable(false);
        jPanel2.add(btn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(242, 154, 272, 2));

        btn2.setBackground(new java.awt.Color(204, 204, 204));
        btn2.setBorder(null);
        btn2.setFocusable(false);
        jPanel2.add(btn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(239, 311, 272, 2));

        btn4.setBackground(new java.awt.Color(204, 204, 204));
        btn4.setBorder(null);
        btn4.setFocusable(false);
        jPanel2.add(btn4, new org.netbeans.lib.awtextra.AbsoluteConstraints(555, 156, 272, 2));

        jPanel3.setBackground(new java.awt.Color(232, 57, 95));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconImages/icons8_Plus_50px.png"))); // NOI18N
        jButton1.setText("  New");
        jButton1.setBorder(null);
        jButton1.setFocusPainted(false);
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconImages/icons8_delete_50px.png"))); // NOI18N
        jButton2.setText("Delete");
        jButton2.setBorder(null);
        jButton2.setFocusPainted(false);
        jButton2.setFocusable(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconImages/icons8_refresh_50px.png"))); // NOI18N
        jButton3.setText("Updat");
        jButton3.setBorder(null);
        jButton3.setFocusPainted(false);
        jButton3.setFocusable(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconImages/icons8_save_50px.png"))); // NOI18N
        jButton4.setText("  Save");
        jButton4.setBorder(null);
        jButton4.setFocusPainted(false);
        jButton4.setFocusable(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(845, -6, 190, 400));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1030, 390));

        jMenu.setBackground(new java.awt.Color(32, 136, 203));
        jMenu.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N

        jMenu1.setText("File");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jMenuItem1.setText("New");
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jMenuItem2.setText("Save");
        jMenu1.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jMenuItem3.setText("Delete");
        jMenu1.add(jMenuItem3);

        jMenu.add(jMenu1);

        jMenu2.setText("Edit");
        jMenu2.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jMenuItem4.setText("Update");
        jMenu2.add(jMenuItem4);

        jMenu.add(jMenu2);

        jMenu3.setText("Tools");
        jMenu3.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jMenu.add(jMenu3);

        jMenu4.setText("Help");
        jMenu4.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jMenu.add(jMenu4);

        setJMenuBar(jMenu);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMaMousePressed
         setColorlbl(btn1);
         resetColorlbl(btn2);
         resetColorlbl(btn4);
         resetColorlbl(btn5);
         setColortxt(txtMa);
    }//GEN-LAST:event_txtMaMousePressed

    private void txtTenMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTenMousePressed
          setColorlbl(btn2);
         resetColorlbl(btn1);
         resetColorlbl(btn4);
         resetColorlbl(btn5);
         setColortxt(txtTen);
    }//GEN-LAST:event_txtTenMousePressed

    private void txtGiaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGiaMousePressed
         setColorlbl(btn4);
         resetColorlbl(btn2);
         resetColorlbl(btn1);
         resetColorlbl(btn5);
         setColortxt(txtGia);
    }//GEN-LAST:event_txtGiaMousePressed

    private void txtTimkiemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimkiemMousePressed
         setColorlbl(btn5);
         resetColorlbl(btn2);
         resetColorlbl(btn1);
         resetColorlbl(btn4);
         setColortxt(txtTimkiem);
    }//GEN-LAST:event_txtTimkiemMousePressed

    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn5ActionPerformed

    private void lblHinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhMouseClicked
        chonAnh();
    }//GEN-LAST:event_lblHinhMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        add();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void tblSanPhamMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMousePressed
        String masp = (String) tblSanPham.getValueAt(tblSanPham.getSelectedRow(), 0);
        fillForm(masp);
    }//GEN-LAST:event_tblSanPhamMousePressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       clearForm();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        delete();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       update();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtTimkiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimkiemKeyReleased
        Find f= new Find();
        f.timKiemSanPham(txtTimkiem.getText(), tblSanPham);
    }//GEN-LAST:event_txtTimkiemKeyReleased

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
            java.util.logging.Logger.getLogger(Manage_Product.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Manage_Product.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Manage_Product.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Manage_Product.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Manage_Product().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenuBar jMenu;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHinh;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTimkiem;
    // End of variables declaration//GEN-END:variables
}
