/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spa.ui;

import com.spa.dao.NhanVienDAO;
import com.spa.entity.NhanVien;
import com.spa.tienich.BackgroundMenuBar;
import com.spa.tienich.BatLoi;
import com.spa.tienich.Find;
import com.spa.tienich.XImage;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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
public class Manage_Staff extends javax.swing.JFrame {

    Connection con = null;
    Statement st = null;
    ResultSet rs = null;

    void setColorlbl(JButton btn) {
        btn.setBackground(new Color(32, 136, 203));

    }

    void resetColorlbl(JButton btn) {
        btn.setBackground(new Color(204, 204, 204));
    }

    void setColortxt(JTextField txt) {
        txt.setForeground(new Color(0, 0, 0));
        txt.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txt.setText("");
    }

    void resetColortxt(JTextField txt) {
        txt.setForeground(new Color(204, 204, 204));
    }

    /**
     * Creates new form Manage_Staff
     */
    NhanVienDAO dao = new NhanVienDAO();
    ThanhCong tc = new ThanhCong();
    ThatBai tb = new ThatBai();
    int row = -1;
    DefaultTableModel model;
    BatLoi bl = new BatLoi();

    public Manage_Staff() {
        initComponents();
        this.setLocationRelativeTo(null);
        //menu
        BackgroundMenuBar bg = new BackgroundMenuBar();

        DefaultTableCellRenderer head_render = new DefaultTableCellRenderer();
        head_render.setPreferredSize(new Dimension(30, 40));
        head_render.setBackground(new Color(32, 136, 203));
        head_render.setForeground(new Color(255, 255, 255));
        //do rong cac cot
//        setColumnWidths(tblNhanVien,30, 150, 100, 100);
        tblNhanVien.getTableHeader().setDefaultRenderer(head_render);

        tblNhanVien.setRowHeight(35);
        //do rong cac cot
        String header[] = {"MaNV", "Họ Tên", "SDT ", "MatKhau", "Vai trò"};
        model = new DefaultTableModel(header, 0);

        fillTable();
    }

    void fillTable() {
        model.setRowCount(0);
        try {
            List<NhanVien> list = dao.selectAll();
            for (NhanVien nv : list) {
                Object[] row = {
                    nv.getMaNV(),
                    nv.getHoTen(),
                    nv.getDienThoai(),
                    nv.getMatKhau(),
                    nv.isVaiTro() ? "QuanLy" : "NhanVien"
                };
                model.addRow(row);
            }
            tblNhanVien.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Loi truy van du lieu");
            System.out.println(e);
        }
    }

    void fillForm(String manv) {
        setColortxt(txtMa);
        setColortxt(txtTen);
        setColortxt(txtSdt);
        setColortxt(txtMk);
        NhanVien nv = dao.selectById(manv);
        txtMa.setText(nv.getMaNV());
        txtTen.setText(nv.getHoTen());
        txtSdt.setText(nv.getDienThoai());
        txtMk.setText(nv.getMatKhau());
        if (nv.isVaiTro() == true) {
            rdoQuanly.setSelected(true);
        } else {
            rdoNhanvien.setSelected(true);
        }
        if (nv.getHinhanh() != null) {
            lblHinh.setToolTipText(nv.getHinhanh());
            lblHinh.setIcon(XImage.read(nv.getHinhanh()));
        }
    }

    void clearForm() {
        txtMa.setText("");
        txtTen.setText("");
        txtSdt.setText("");
        txtMk.setText("");
        rdoQuanly.setSelected(false);
        rdoNhanvien.setSelected(false);
        txtMa.grabFocus();
    }

    NhanVien getForm() {
        bl.deTrong(txtMa,lblLoiManv);
        bl.deTrong(txtTen,lblLoiTen);
        bl.deTrong(txtSdt,lblLoiSdt);
        bl.deTrong(txtMk,lblLoiMk);
        bl.kiemTraPatternSdt(txtSdt,lblLoiSdt);
        bl.kiemTraPatternTen(txtTen,lblLoiTen);
        NhanVien nv = new NhanVien();
        nv.setMaNV(txtMa.getText());
        nv.setHoTen(txtTen.getText());
        nv.setDienThoai(txtSdt.getText());
        nv.setMatKhau(txtMk.getText());
        if (rdoQuanly.isSelected() == true) {
            nv.setVaiTro(true);
        } else {
            nv.setVaiTro(false);
        }
        if (lblHinh.getToolTipText() == null) {
            lblLoiAnh.setForeground(Color.red);
            lblLoiAnh.setFont(new Font("Tahoma", Font.ITALIC, 20));
            lblLoiAnh.setText("(Vui lòng chọn ảnh!)");
        } else {
            nv.setHinhanh(lblHinh.getToolTipText());
            lblLoiAnh.setText("");
        }
        return nv;
    }

    void add() {
        NhanVien nv = this.getForm();
        if (nv == null) {
            return;
        }
        try {
            dao.insert(nv);
            this.fillTable();
            this.clearForm();
            tc.show();
        } catch (Exception e) {
            tb.show();
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

    void delete() {
        try {
            int xacnhan=JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn xóa nhân viên này?", "Xác Nhận", WIDTH);
            if(xacnhan==0){
            String manv = (String) tblNhanVien.getValueAt(tblNhanVien.getSelectedRow(), 0);
            dao.delete(manv);
            this.fillTable();
            this.clearForm();
            tc.show();
            }else{
                return;
            }
        } catch (Exception e) {
            tb.show();
            System.out.println(e);
        }
    }

    void update() {
        NhanVien nv = getForm();
        try {
            dao.update(nv);
            fillTable();
            clearForm();
            tc.show();
        } catch (Exception e) {
            System.out.println(e);
            tb.show();
        }
    }

    public void LoadData(int row) {
        try {
            String manv = (String) tblNhanVien.getValueAt(row, 0);
            fillForm(manv);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
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
        tblNhanVien = new javax.swing.JTable();
        txtTimkiem = new javax.swing.JTextField();
        btn5 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        lblTable = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtTen = new javax.swing.JTextField();
        txtMa = new javax.swing.JTextField();
        txtSdt = new javax.swing.JTextField();
        txtMk = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        rdoQuanly = new javax.swing.JRadioButton();
        rdoNhanvien = new javax.swing.JRadioButton();
        lblHinh = new javax.swing.JLabel();
        btn1 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnNew = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        lblLoiAnh = new javax.swing.JLabel();
        lblLoiManv = new javax.swing.JLabel();
        lblLoiVT = new javax.swing.JLabel();
        lblLoiMk = new javax.swing.JLabel();
        lblLoiTen = new javax.swing.JLabel();
        lblLoiSdt = new javax.swing.JLabel();
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
        setTitle("Quản Lý Nhân Viên");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        tblNhanVien.setAutoCreateRowSorter(true);
        tblNhanVien.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
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
        tblNhanVien.setFocusable(false);
        tblNhanVien.setGridColor(new java.awt.Color(255, 255, 255));
        tblNhanVien.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblNhanVien.setRowHeight(30);
        tblNhanVien.setSelectionBackground(new java.awt.Color(232, 57, 95));
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblNhanVienMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhanVien);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 1040, 350));

        txtTimkiem.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtTimkiem.setForeground(new java.awt.Color(204, 204, 204));
        txtTimkiem.setText("Nhập tên");
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
        jPanel1.add(txtTimkiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 272, 50));

        btn5.setBackground(new java.awt.Color(204, 204, 204));
        btn5.setBorder(null);
        btn5.setFocusable(false);
        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5ActionPerformed(evt);
            }
        });
        jPanel1.add(btn5, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, 272, 2));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconImages/icons8_search_more_50px.png"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 10, -1, 50));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel8.setText("Tìm kiếm :");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, -1, -1));

        jPanel4.setBackground(new java.awt.Color(32, 136, 203));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconImages/icons8_previous_50px.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, 59, 45));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconImages/icons8_next_50px.png"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, 59, 45));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconImages/icons8_last_50px.png"))); // NOI18N
        jButton3.setBorder(null);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 10, 59, 45));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconImages/icons8_first_50px.png"))); // NOI18N
        jButton4.setBorder(null);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 59, 45));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 1040, 70));

        lblTable.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jPanel1.add(lblTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 10, 360, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, 392, 1030, 500));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTen.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtTen.setForeground(new java.awt.Color(204, 204, 204));
        txtTen.setBorder(null);
        txtTen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtTenMousePressed(evt);
            }
        });
        jPanel2.add(txtTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(242, 178, 272, 47));

        txtMa.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtMa.setForeground(new java.awt.Color(204, 204, 204));
        txtMa.setBorder(null);
        txtMa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtMaMousePressed(evt);
            }
        });
        txtMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaActionPerformed(evt);
            }
        });
        txtMa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaKeyReleased(evt);
            }
        });
        jPanel2.add(txtMa, new org.netbeans.lib.awtextra.AbsoluteConstraints(242, 93, 272, 47));

        txtSdt.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtSdt.setForeground(new java.awt.Color(204, 204, 204));
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
        jPanel2.add(txtSdt, new org.netbeans.lib.awtextra.AbsoluteConstraints(242, 280, 272, 54));

        txtMk.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtMk.setForeground(new java.awt.Color(204, 204, 204));
        txtMk.setBorder(null);
        txtMk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtMkMousePressed(evt);
            }
        });
        jPanel2.add(txtMk, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 93, 272, 47));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel1.setText("Mã NV ");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(242, 52, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel2.setText("Tên NV");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(242, 151, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel3.setText("SDT ");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(242, 246, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel4.setText("Vai trò");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 151, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel5.setText("Mật khẩu");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 52, -1, -1));

        buttonGroup1.add(rdoQuanly);
        rdoQuanly.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        rdoQuanly.setText("Quan Ly");
        jPanel2.add(rdoQuanly, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 201, 110, -1));

        buttonGroup1.add(rdoNhanvien);
        rdoNhanvien.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        rdoNhanvien.setText("Nhan Vien");
        jPanel2.add(rdoNhanvien, new org.netbeans.lib.awtextra.AbsoluteConstraints(697, 201, -1, -1));

        lblHinh.setBackground(new java.awt.Color(51, 51, 255));
        lblHinh.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblHinh.setText("           Chọn Ảnh");
        lblHinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhMouseClicked(evt);
            }
        });
        jPanel2.add(lblHinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 61, 215, 273));

        btn1.setBackground(new java.awt.Color(204, 204, 204));
        btn1.setBorder(null);
        btn1.setFocusable(false);
        jPanel2.add(btn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(242, 140, 272, 2));

        btn2.setBackground(new java.awt.Color(204, 204, 204));
        btn2.setBorder(null);
        btn2.setFocusable(false);
        jPanel2.add(btn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(242, 235, 272, 2));

        btn3.setBackground(new java.awt.Color(204, 204, 204));
        btn3.setBorder(null);
        btn3.setFocusable(false);
        jPanel2.add(btn3, new org.netbeans.lib.awtextra.AbsoluteConstraints(242, 334, 272, 2));

        btn4.setBackground(new java.awt.Color(204, 204, 204));
        btn4.setBorder(null);
        btn4.setFocusable(false);
        jPanel2.add(btn4, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 142, 272, 2));

        jPanel3.setBackground(new java.awt.Color(232, 57, 95));

        btnNew.setBackground(new java.awt.Color(255, 255, 255));
        btnNew.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconImages/icons8_Plus_50px.png"))); // NOI18N
        btnNew.setText("  New");
        btnNew.setBorder(null);
        btnNew.setFocusPainted(false);
        btnNew.setFocusable(false);
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(255, 255, 255));
        btnDelete.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconImages/icons8_delete_50px.png"))); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.setBorder(null);
        btnDelete.setFocusPainted(false);
        btnDelete.setFocusable(false);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(255, 255, 255));
        btnUpdate.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconImages/icons8_refresh_50px.png"))); // NOI18N
        btnUpdate.setText("Updat");
        btnUpdate.setBorder(null);
        btnUpdate.setFocusPainted(false);
        btnUpdate.setFocusable(false);
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnSave.setBackground(new java.awt.Color(255, 255, 255));
        btnSave.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconImages/icons8_save_50px.png"))); // NOI18N
        btnSave.setText("  Save");
        btnSave.setBorder(null);
        btnSave.setFocusPainted(false);
        btnSave.setFocusable(false);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSave, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(842, 0, -1, -1));
        jPanel2.add(lblLoiAnh, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 345, 215, 31));
        jPanel2.add(lblLoiManv, new org.netbeans.lib.awtextra.AbsoluteConstraints(331, 52, 183, 25));
        jPanel2.add(lblLoiVT, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 150, 170, 30));
        jPanel2.add(lblLoiMk, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 50, 170, 30));
        jPanel2.add(lblLoiTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 150, 170, 30));
        jPanel2.add(lblLoiSdt, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 240, 170, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1028, 390));

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
        setColortxt(txtMa);
    }//GEN-LAST:event_txtMaMousePressed

    private void txtTenMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTenMousePressed
        setColorlbl(btn2);
        resetColorlbl(btn1);
        resetColorlbl(btn3);
        resetColorlbl(btn4);
        resetColorlbl(btn5);
        setColortxt(txtTen);
    }//GEN-LAST:event_txtTenMousePressed

    private void txtSdtMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSdtMousePressed
        setColorlbl(btn3);
        resetColorlbl(btn2);
        resetColorlbl(btn1);
        resetColorlbl(btn4);
        resetColorlbl(btn5);
        setColortxt(txtSdt);
    }//GEN-LAST:event_txtSdtMousePressed

    private void txtMkMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMkMousePressed
        setColorlbl(btn4);
        resetColorlbl(btn2);
        resetColorlbl(btn3);
        resetColorlbl(btn1);
        resetColorlbl(btn5);
        setColortxt(txtMk);
    }//GEN-LAST:event_txtMkMousePressed

    private void txtTimkiemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimkiemMousePressed
        setColorlbl(btn5);
        resetColorlbl(btn2);
        resetColorlbl(btn3);
        resetColorlbl(btn1);
        resetColorlbl(btn4);
        setColortxt(txtTimkiem);
    }//GEN-LAST:event_txtTimkiemMousePressed

    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn5ActionPerformed

    private void txtSdtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSdtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSdtActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        clearForm();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        add();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void lblHinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhMouseClicked
        chonAnh();
    }//GEN-LAST:event_lblHinhMouseClicked

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        delete();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        update();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void tblNhanVienMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMousePressed
        String manv = (String) tblNhanVien.getValueAt(tblNhanVien.getSelectedRow(), 0);
        fillForm(manv);
    }//GEN-LAST:event_tblNhanVienMousePressed

    private void txtTimkiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimkiemKeyReleased
        Find f = new Find();
        f.timKiemNhanVien(txtTimkiem.getText(), tblNhanVien);
        if (tblNhanVien.getRowCount() == 0) {
            lblTable.setText("(Không tìm thấy nhân viên nào!)");
        } else {
            lblTable.setText("");
        }
    }//GEN-LAST:event_txtTimkiemKeyReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        LoadData(0);
        tblNhanVien.setRowSelectionInterval(0, 0);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int row = tblNhanVien.getSelectedRow();
        if (row <= 0) {
            return;
        }
        row--;
        tblNhanVien.setRowSelectionInterval(row, row);
        LoadData(row);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int row = tblNhanVien.getSelectedRow();
        int size = tblNhanVien.getRowCount();
        if (row >= size - 1) {
            return;
        }
        row++;
        tblNhanVien.setRowSelectionInterval(row, row);
        LoadData(row);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int vitricuoi = tblNhanVien.getRowCount() - 1;
        LoadData(vitricuoi);
        tblNhanVien.setRowSelectionInterval(vitricuoi, vitricuoi);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtMaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaKeyReleased
        bl.kiemTraManv(txtMa, lblLoiManv);
    }//GEN-LAST:event_txtMaKeyReleased

    private void txtMaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaActionPerformed

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
            java.util.logging.Logger.getLogger(Manage_Staff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Manage_Staff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Manage_Staff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Manage_Staff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Manage_Staff().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHinh;
    private javax.swing.JLabel lblLoiAnh;
    private javax.swing.JLabel lblLoiManv;
    private javax.swing.JLabel lblLoiMk;
    private javax.swing.JLabel lblLoiSdt;
    private javax.swing.JLabel lblLoiTen;
    private javax.swing.JLabel lblLoiVT;
    private javax.swing.JLabel lblTable;
    private javax.swing.JRadioButton rdoNhanvien;
    private javax.swing.JRadioButton rdoQuanly;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtMk;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTimkiem;
    // End of variables declaration//GEN-END:variables
}
