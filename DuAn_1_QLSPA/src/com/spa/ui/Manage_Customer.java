/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spa.ui;

import com.spa.dao.KhachHangDAO;
import com.spa.entity.KhachHang;
import com.spa.tienich.BackgroundMenuBar;
import com.spa.tienich.Find;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
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


public class Manage_Customer extends javax.swing.JFrame {
    public static String tenkh;
    public static String makhachhang;
    
    void setColorlbl(JButton btn) {
        btn.setBackground(new Color(32, 136, 203));

    }

    void setColortxt(JTextField txt) {
        txt.setForeground(new Color(0, 0, 0));
        txt.setText("");
    }

    void resetColortxt(JTextField txt) {
        txt.setForeground(new Color(204, 204, 204));
    }

    void resetColorlbl(JButton btn) {
        btn.setBackground(new Color(204, 204, 204));
    }
    /**
     * Creates new form Manage_Staff
     */
    KhachHangDAO dao = new KhachHangDAO();
    ThanhCong tc= new ThanhCong();
    ThatBai tb= new ThatBai();
    int row = -1;
    DefaultTableModel model;

    public Manage_Customer() {
        initComponents();
        this.setLocationRelativeTo(null);
        //menu
        BackgroundMenuBar bg = new BackgroundMenuBar();

        DefaultTableCellRenderer head_render = new DefaultTableCellRenderer();
        head_render.setPreferredSize(new Dimension(30, 40));
        head_render.setBackground(new Color(32, 136, 203));
        head_render.setForeground(new Color(255, 255, 255));
        tblKhachHang.getTableHeader().setDefaultRenderer(head_render);
        tblKhachHang.setRowHeight(35);
        //do rong cac cot
        String header[] = {"MaKH", "TenKH", "SDT ", "Email", "NgaySinh", "GioiTinh", "NgayDK", "MaNV"};
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

    void fillTable() {

        model.setRowCount(0);
        try {

            List<KhachHang> list = dao.selectAll();
            for (KhachHang kh : list) {
                Object[] row = {
                    kh.getMaKH(),
                    kh.getHoTen(),
                    kh.getDienThoai(),
                    kh.getEmail(),
                    kh.getNgaySinh(),                  
                    kh.isGioiTinh()? "Nam" : "Nu",                   
                    kh.getNgayDK(),
                    kh.getMaNV()
                };
                model.addRow(row);
            }
            tblKhachHang.setModel(model);
        } catch (Exception e) {
            tb.show();
            System.out.println(e);
        }
    }
    
    void fillForm(String makh) {
        setColortxt(txtMa);
        setColortxt(txtTen);
        setColortxt(txtSdt);
        setColortxt(txtEmail);
        setColortxt(txtManv);
        KhachHang kh = dao.selectById(makh);
        txtMa.setText(kh.getMaKH());
        txtTen.setText(kh.getHoTen());
        txtSdt.setText(kh.getDienThoai());
        txtEmail.setText(kh.getEmail());
        ngaydk.setDate(kh.getNgayDK());
        ngaydl.setDate(kh.getNgaySinh());
        if(kh.isGioiTinh()==true){
            rdoNam.setSelected(true);
        }else{
            rdoNu.setSelected(true);
        }
        txtManv.setText(kh.getMaNV());
    }
     void clearForm() {
        txtMa.setText("");
        txtTen.setText("");
        txtSdt.setText("");
        txtEmail.setText("");
        txtManv.setText("");
        rdoNam.setSelected(false);
        rdoNu.setSelected(false);
        ngaydl.setDate(new java.util.Date());
        ngaydk.setDate(new java.util.Date());
        txtMa.grabFocus();
    }
     void delete() {
        String makh = (String) tblKhachHang.getValueAt(tblKhachHang.getSelectedRow(), 0);
        try {
            dao.delete(makh);
            this.fillTable();
            this.clearForm();
            tc.show();
        } catch (Exception e) {
            tb.show();
            System.out.println(e);
        }
    }
     KhachHang getForm() {
        KhachHang kh = new KhachHang();
        kh.setMaKH(txtMa.getText());
        kh.setHoTen(txtTen.getText());
        kh.setDienThoai(txtSdt.getText());
        kh.setEmail(txtEmail.getText());
        kh.setNgaySinh(ngaydl.getDate());
        if(rdoNam.isSelected()){
            kh.setGioiTinh(true);
        }else{
            kh.setGioiTinh(false);
        }
        kh.setNgayDK(ngaydk.getDate());       
        kh.setMaNV(txtManv.getText());      
        return kh;
    }
     void add() {
        KhachHang kh = this.getForm();
        System.out.println(kh);
        try {
            dao.insert(kh);
            this.fillTable();
            this.clearForm();
            tc.show();
        } catch (Exception e) {
            tb.show();
        }

    }
      void update() {
        KhachHang kh = getForm();
        try {
            dao.update(kh);
            fillTable();
            clearForm();
            tc.show();
        } catch (Exception e) {
            tb.show();
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        txtTimkiem = new javax.swing.JTextField();
        btn6 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnThemVaoDatLich = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtTen = new javax.swing.JTextField();
        txtMa = new javax.swing.JTextField();
        txtSdt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btn1 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        txtEmail = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btn4 = new javax.swing.JButton();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        txtManv = new javax.swing.JTextField();
        btn5 = new javax.swing.JButton();
        ngaydl = new com.toedter.calendar.JDateChooser();
        ngaydk = new com.toedter.calendar.JDateChooser();
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
        setTitle("Quản Lý Khách Hàng");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        tblKhachHang.setAutoCreateRowSorter(true);
        tblKhachHang.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
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
        tblKhachHang.setFocusable(false);
        tblKhachHang.setGridColor(new java.awt.Color(255, 255, 255));
        tblKhachHang.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblKhachHang.setRowHeight(30);
        tblKhachHang.setSelectionBackground(new java.awt.Color(232, 57, 95));
        tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblKhachHangMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblKhachHang);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 1290, 340));

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
        jPanel1.add(txtTimkiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 10, 272, 50));

        btn6.setBackground(new java.awt.Color(204, 204, 204));
        btn6.setBorder(null);
        btn6.setFocusable(false);
        btn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn6ActionPerformed(evt);
            }
        });
        jPanel1.add(btn6, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 60, 272, 2));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconImages/icons8_search_more_50px.png"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 10, -1, 50));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel8.setText("Tìm kiếm :");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, -1, -1));

        btnThemVaoDatLich.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btnThemVaoDatLich.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconImages/iconDatLich.png"))); // NOI18N
        btnThemVaoDatLich.setText("Đặt Lịch");
        btnThemVaoDatLich.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemVaoDatLichActionPerformed(evt);
            }
        });
        jPanel1.add(btnThemVaoDatLich, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 10, -1, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 392, 1290, 490));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        txtTen.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtTen.setForeground(new java.awt.Color(204, 204, 204));
        txtTen.setText("Nhập tên");
        txtTen.setBorder(null);
        txtTen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtTenMousePressed(evt);
            }
        });

        txtMa.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtMa.setForeground(new java.awt.Color(204, 204, 204));
        txtMa.setText("Nhập mã");
        txtMa.setBorder(null);
        txtMa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtMaMousePressed(evt);
            }
        });

        txtSdt.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtSdt.setForeground(new java.awt.Color(204, 204, 204));
        txtSdt.setText("Nhập sdt");
        txtSdt.setBorder(null);
        txtSdt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtSdtMousePressed(evt);
            }
        });
        txtSdt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSdtActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel1.setText("Mã KH");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel2.setText("Tên KH");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel3.setText("SDT ");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel5.setText("Giới tính");

        btn1.setBackground(new java.awt.Color(204, 204, 204));
        btn1.setBorder(null);
        btn1.setFocusable(false);

        btn2.setBackground(new java.awt.Color(204, 204, 204));
        btn2.setBorder(null);
        btn2.setFocusable(false);

        btn3.setBackground(new java.awt.Color(204, 204, 204));
        btn3.setBorder(null);
        btn3.setFocusable(false);

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
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        txtEmail.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtEmail.setForeground(new java.awt.Color(204, 204, 204));
        txtEmail.setText("Nhập email");
        txtEmail.setBorder(null);
        txtEmail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtEmailMousePressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel6.setText("Email");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel9.setText("Ngày sinh");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel10.setText("Ngày ĐK");

        btn4.setBackground(new java.awt.Color(204, 204, 204));
        btn4.setBorder(null);
        btn4.setFocusable(false);

        buttonGroup1.add(rdoNam);
        rdoNam.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        rdoNam.setText("Nam");

        buttonGroup1.add(rdoNu);
        rdoNu.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        rdoNu.setText("Nữ");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel11.setText("Mã NV");

        txtManv.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtManv.setForeground(new java.awt.Color(204, 204, 204));
        txtManv.setText("Nhập mã nv");
        txtManv.setBorder(null);
        txtManv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtManvMousePressed(evt);
            }
        });

        btn5.setBackground(new java.awt.Color(204, 204, 204));
        btn5.setBorder(null);
        btn5.setFocusable(false);

        ngaydl.setBackground(new java.awt.Color(122, 184, 225));
        ngaydl.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N

        ngaydk.setBackground(new java.awt.Color(122, 184, 225));
        ngaydk.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                    .addComponent(jLabel10)
                    .addComponent(btn4, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9)
                    .addComponent(ngaydl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ngaydk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(112, 112, 112)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(rdoNam)
                        .addGap(18, 18, 18)
                        .addComponent(rdoNu))
                    .addComponent(jLabel11)
                    .addComponent(btn5, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtManv, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(72, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btn4, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addGap(2, 2, 2)
                        .addComponent(ngaydl, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ngaydk, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addGap(18, 18, 18)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(rdoNam)
                                .addComponent(rdoNu))
                            .addGap(29, 29, 29)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtManv, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(1, 1, 1)
                            .addComponent(btn5, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(9, 9, 9)
                            .addComponent(jLabel2)
                            .addGap(2, 2, 2)
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(1, 1, 1)
                            .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel3)
                            .addGap(11, 11, 11)
                            .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, 0)
                .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1290, 390));

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
        resetColorlbl(btn3);
        resetColorlbl(btn4);
        resetColorlbl(btn5);
        resetColorlbl(btn6);
        setColortxt(txtMa);
    }//GEN-LAST:event_txtMaMousePressed

    private void txtTenMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTenMousePressed
        setColorlbl(btn2);
        resetColorlbl(btn1);
        resetColorlbl(btn3);
        resetColorlbl(btn4);
        resetColorlbl(btn5);
        resetColorlbl(btn6);
        setColortxt(txtTen);
    }//GEN-LAST:event_txtTenMousePressed

    private void txtSdtMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSdtMousePressed
        setColorlbl(btn3);
        resetColorlbl(btn2);
        resetColorlbl(btn1);
        resetColorlbl(btn4);
        resetColorlbl(btn5);
        resetColorlbl(btn6);
        setColortxt(txtSdt);
    }//GEN-LAST:event_txtSdtMousePressed

    private void txtTimkiemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimkiemMousePressed
        setColorlbl(btn6);
        resetColorlbl(btn2);
        resetColorlbl(btn3);
        resetColorlbl(btn1);
        resetColorlbl(btn4);
        resetColorlbl(btn5);
        setColortxt(txtTimkiem);
    }//GEN-LAST:event_txtTimkiemMousePressed

    private void btn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn6ActionPerformed

    private void txtSdtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSdtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSdtActionPerformed

    private void txtEmailMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtEmailMousePressed
        setColorlbl(btn4);
        resetColorlbl(btn2);
        resetColorlbl(btn1);
        resetColorlbl(btn3);
        resetColorlbl(btn5);
        resetColorlbl(btn6);
        setColortxt(txtEmail);
    }//GEN-LAST:event_txtEmailMousePressed

    private void txtManvMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtManvMousePressed
       setColorlbl(btn5);
        resetColorlbl(btn2);
        resetColorlbl(btn1);
        resetColorlbl(btn4);
        resetColorlbl(btn3);
        resetColorlbl(btn6);
        setColortxt(txtManv);
    }//GEN-LAST:event_txtManvMousePressed

    private void tblKhachHangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMousePressed
         String makh = (String) tblKhachHang.getValueAt(tblKhachHang.getSelectedRow(), 0);
            fillForm(makh);
    }//GEN-LAST:event_tblKhachHangMousePressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       clearForm();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        delete();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        add();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        update();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtTimkiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimkiemKeyReleased
        Find f = new Find();
        f.timKiemKhachHang(txtTimkiem.getText(), tblKhachHang);
    }//GEN-LAST:event_txtTimkiemKeyReleased

    private void btnThemVaoDatLichActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemVaoDatLichActionPerformed
        tenkh=(String) tblKhachHang.getValueAt(tblKhachHang.getSelectedRow(), 1);
        makhachhang=(String) tblKhachHang.getValueAt(tblKhachHang.getSelectedRow(),0);
        this.dispose();
        new Reserve().show();
    }//GEN-LAST:event_btnThemVaoDatLichActionPerformed

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
            java.util.logging.Logger.getLogger(Manage_Customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Manage_Customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Manage_Customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Manage_Customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Manage_Customer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btn6;
    private javax.swing.JButton btnThemVaoDatLich;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
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
    private com.toedter.calendar.JDateChooser ngaydk;
    private com.toedter.calendar.JDateChooser ngaydl;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtManv;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTimkiem;
    // End of variables declaration//GEN-END:variables
}
