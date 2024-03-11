/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.panel;

import App.Internal.Edit_Student;
import App.Internal.View_Info_Student;
import App.Internal.View_Mark;
import App.controller.Class_Controller;
import App.controller.Student_Controller;
import App.mail.EmailValidation;
import App.model.tbl_Class;
import App.model.tbl_Student;
import App.model.tbl_Teacher;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Troanh
 */
public class Panel_Student extends javax.swing.JPanel implements Edit_Student.reload {

    /**
     * Creates new form Panel_Student
     */
    tbl_Teacher acc;
    Connection conn;
    List<tbl_Student> LS;
    List<tbl_Class> LC;
    EmailValidation ev = new EmailValidation();

    public Panel_Student(tbl_Teacher acc, Connection conn) {
        this.acc = acc;
        this.conn = conn;
        initComponents();
        if (acc == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng đăng nhập để sử dụng chức năng");
        } else if (conn == null) {
            JOptionPane.showMessageDialog(null, "Kết nối đã có lỗi, vui lòng thử lại");
        } else {
            initComponents();
            setCombobox();
            getStudent("");
            loadTable();
            Nam.doClick();
            try {
                jDate.setDate(new SimpleDateFormat("yyyy/MM/dd").parse("2000/01/01"));
            } catch (ParseException ex) {
                Logger.getLogger(Panel_User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void setCombobox() {
        Class_Controller CC = new Class_Controller(conn);
        LC = CC.select("");
        DefaultComboBoxModel dcm_Class = new DefaultComboBoxModel();
        DefaultComboBoxModel dcm_Search = new DefaultComboBoxModel();
        dcm_Search.addElement("Tất cả");
        for (tbl_Class c : LC) {
            dcm_Class.addElement(c.getName());
            dcm_Search.addElement(c.getName());
        }
        Class_Search.setModel(dcm_Search);
        JClass_Student.setModel(dcm_Class);
    }

    private void getStudent(String check) {
        Student_Controller SC = new Student_Controller(conn);
        LS = SC.select(check + " ORDER BY ID DESC");
    }

    private void loadTable() {
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("STT");
        dtm.addColumn("Mã SV");
        dtm.addColumn("Tên SV");
        dtm.addColumn("Địa chỉ");
        dtm.addColumn("Trạng thái");
        dtm.addColumn("Lớp");
        for (int i = 0; i < LS.size(); i++){
            tbl_Student s = LS.get(i);
            for (tbl_Class c : LC) {
                if (c.getId() == s.getClass_ID()) {
                    if (s.getStatus() == 1) {
                        Object o[] = {
                             (i + 1), s.getMasv(), s.getName(), s.getAddress(), "Đang học", c.getName()
                        };
                        dtm.addRow(o);
                    } else {
                        Object o[] = {
                             (i + 1), s.getMasv(), s.getName(), s.getAddress(), "Bảo lưu", c.getName()
                        };
                        dtm.addRow(o);
                    }
                }
            }
        }
        Data_Table.setAutoCreateRowSorter(true);
        Data_Table.setModel(dtm);
        Data_Table.setRowHeight(25);
        
    }
    
    private boolean check_Masv(String masv) {
        Student_Controller SC = new Student_Controller(conn);
        List<tbl_Student> LS_check = SC.select(" WHERE MaSV = N'" + masv + "'");
        boolean check = LS_check.size() > 0;
        return check;
    }

    private boolean check_Mail_Phone(String email, String phone) {
        Student_Controller SC = new Student_Controller(conn);
        List<tbl_Student> LS_check = SC.select(" WHERE Email = N'" + email + "' OR Phone = N'" + phone + "'");
        boolean check = LS_check.size() > 0;
        return check;
    }
    
    private void openFile(String file){
        try {
            File path = new File(file);
            Desktop.getDesktop().open(path);
        } catch (IOException iOException) {
            System.out.println(iOException);
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

        Gender = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        View = new javax.swing.JMenuItem();
        Edit = new javax.swing.JMenuItem();
        View_Mark = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jDesktopPane = new javax.swing.JDesktopPane();
        jPanel6 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        Search = new javax.swing.JTextField();
        Button_Search = new javax.swing.JButton();
        Class_Search = new javax.swing.JComboBox<>();
        ReloadTable = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Data_Table = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        JName = new javax.swing.JTextField();
        jDate = new com.toedter.calendar.JDateChooser();
        Nam = new javax.swing.JRadioButton();
        Nu = new javax.swing.JRadioButton();
        Khac = new javax.swing.JRadioButton();
        JClass_Student = new javax.swing.JComboBox<>();
        jPanel12 = new javax.swing.JPanel();
        add = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        Refesh = new javax.swing.JButton();
        ExportExcel = new javax.swing.JButton();
        JEmail = new javax.swing.JTextField();
        JAddress = new javax.swing.JTextField();
        JPhone = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        JMasv = new javax.swing.JTextField();

        View.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        View.setText("Xem ");
        View.setToolTipText("Xem đầy đủ thông tin");
        View.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewActionPerformed(evt);
            }
        });
        jPopupMenu1.add(View);

        Edit.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        Edit.setText("Sửa ");
        Edit.setToolTipText("Sửa thông tin sinh viên");
        Edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditActionPerformed(evt);
            }
        });
        jPopupMenu1.add(Edit);

        View_Mark.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        View_Mark.setText("Xem Điểm");
        View_Mark.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                View_MarkActionPerformed(evt);
            }
        });
        jPopupMenu1.add(View_Mark);

        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jPanel5.setBackground(new java.awt.Color(204, 255, 255));

        jPanel9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel3.setText("Student name");

        Search.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        Button_Search.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        Button_Search.setText("Search student");
        Button_Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_SearchActionPerformed(evt);
            }
        });

        Class_Search.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        Class_Search.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        ReloadTable.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        ReloadTable.setText("Reload table");
        ReloadTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReloadTableActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Search, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Class_Search, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Button_Search)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ReloadTable)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(Button_Search, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ReloadTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Class_Search)
                    .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(5, 5, 5))
        );

        jPanel7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        Data_Table.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        Data_Table.setModel(new javax.swing.table.DefaultTableModel(
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
        Data_Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Data_TableMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(Data_Table);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
        );

        jLabel5.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Add student");
        jLabel5.setToolTipText("");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setText("Gender");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel8.setText("Full Name");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel9.setText("Address");

        jLabel10.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel10.setText("BoD");

        jLabel11.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel11.setText("Phone");

        jLabel12.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel12.setText("Email:");

        jLabel13.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel13.setText("Lớp:");

        JName.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        jDate.setDateFormatString("dd/MM/yyyy");
        jDate.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        Gender.add(Nam);
        Nam.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        Nam.setText("Nam");

        Gender.add(Nu);
        Nu.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        Nu.setText("Nữ");

        Gender.add(Khac);
        Khac.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        Khac.setText("Khác");

        JClass_Student.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        JClass_Student.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        add.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/Button-Add-icon.png"))); // NOI18N
        add.setText("Thêm SV");
        add.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        add.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        delete.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/delete.png"))); // NOI18N
        delete.setText("Xoá SV");
        delete.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        delete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        Refesh.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        Refesh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/Reset-icon.png"))); // NOI18N
        Refesh.setText("Reset");
        Refesh.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Refesh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Refesh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefeshActionPerformed(evt);
            }
        });

        ExportExcel.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        ExportExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/excel.png"))); // NOI18N
        ExportExcel.setText("Xuất Excel");
        ExportExcel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        ExportExcel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ExportExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExportExcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(add, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .addComponent(Refesh, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(delete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ExportExcel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(add)
                    .addComponent(delete))
                .addGap(7, 7, 7)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Refesh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ExportExcel)))
        );

        JEmail.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        JAddress.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        JPhone.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel14.setText("Mã SV");

        JMasv.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JClass_Student, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                                .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(Nam)
                                .addGap(29, 29, 29)
                                .addComponent(Nu)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                                .addComponent(Khac)
                                .addGap(10, 10, 10))
                            .addComponent(JName, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(JMasv)
                            .addComponent(JAddress, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(JEmail, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(JPhone)
                            .addComponent(jDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JMasv, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(JName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(JPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(JAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Nam)
                    .addComponent(Nu)
                    .addComponent(Khac))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(JClass_Student, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(123, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(7, 7, 7))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jDesktopPane.setLayer(jPanel6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPaneLayout = new javax.swing.GroupLayout(jDesktopPane);
        jDesktopPane.setLayout(jDesktopPaneLayout);
        jDesktopPaneLayout.setHorizontalGroup(
            jDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 883, Short.MAX_VALUE)
            .addGroup(jDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jDesktopPaneLayout.setVerticalGroup(
            jDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 679, Short.MAX_VALUE)
            .addGroup(jDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDesktopPaneLayout.createSequentialGroup()
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jDesktopPane)
                .addGap(0, 0, 0))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jDesktopPane))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewActionPerformed
        int num = Data_Table.getSelectedRow();
        tbl_Student st = LS.get(num);
        View_Info_Student vis = new View_Info_Student(conn, st);
        jDesktopPane.add(vis);
        vis.setVisible(true);
    }//GEN-LAST:event_ViewActionPerformed

    private void Data_TableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Data_TableMousePressed
        if(acc.getRole_ID() != 1){
            View_Mark.setVisible(false);
        }
        jPopupMenu1.show(Data_Table, evt.getX(), evt.getY());
    }//GEN-LAST:event_Data_TableMousePressed

    private void ReloadTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReloadTableActionPerformed
        Search.setText("");
        Class_Search.setSelectedIndex(0);
        getStudent("");
        loadTable();
    }//GEN-LAST:event_ReloadTableActionPerformed

    private void Button_SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_SearchActionPerformed
        String search = Search.getText();
        int select_cl = Class_Search.getSelectedIndex() - 1;
        if (select_cl >= 0) {
            int id_class = LC.get(select_cl).getId();
            getStudent(" where Name like N'%" + search + "%' AND Class_ID = " + id_class);
        } else {
            getStudent(" where Name like N'%" + search + "%'");
        }
        loadTable();
    }//GEN-LAST:event_Button_SearchActionPerformed

    private void EditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditActionPerformed
        int number_edit = Data_Table.getSelectedRow();
        tbl_Student student_edit = LS.get(number_edit);
        Edit_Student es = new Edit_Student(conn, student_edit, acc.getID(), acc.getRole_ID(), this);
        jDesktopPane.add(es);
        es.setVisible(true);
    }//GEN-LAST:event_EditActionPerformed

    private void View_MarkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_View_MarkActionPerformed
        int number_view_mark = Data_Table.getSelectedRow();
        tbl_Student student_view_mark = LS.get(number_view_mark);
        View_Mark vm =  new View_Mark(conn, student_view_mark);
        jDesktopPane.add(vm);
        vm.setVisible(true);
    }//GEN-LAST:event_View_MarkActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        String regex_Phone = "^0[0-9]{9,10}$";
        String Masv_New_Student = JMasv.getText();
        String Name_New_Student = JName.getText();
        String Phone_New_Student = JPhone.getText();
        String Email_New_Student = JEmail.getText();
        String Address_New_Student = JAddress.getText();
        String DOB_New_Student = String.valueOf(new SimpleDateFormat("yyyy/MM/dd").format(jDate.getDate()));
        int gender_New_Student = 1;
        if (Nam.isSelected()) {
            gender_New_Student = 1;
        } else if (Nu.isSelected()) {
            gender_New_Student = 2;
        } else if (Khac.isSelected()) {
            gender_New_Student = 3;
        }
        int Class_ID_New_Student = LC.get(JClass_Student.getSelectedIndex()).getId();
        if (check_Masv(Masv_New_Student)) {
            JOptionPane.showMessageDialog(null, "Mã SV bị trùng");
        } else if (Name_New_Student.length() == 0 || Phone_New_Student.length() == 0 || Email_New_Student.length() == 0 || Address_New_Student.length() == 0 || Masv_New_Student.length() == 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin để tiến hành thêm mới");
        } else if (Phone_New_Student.length() > 13 || Phone_New_Student.length() < 8 || regex_Phone.matches(Phone_New_Student)) {
            JOptionPane.showMessageDialog(null, "SĐT phải từ 8 đến 13 số");
        } else if (ev.isNotValidEmail(Email_New_Student)) {
            JOptionPane.showMessageDialog(null, "Hãy kiểm tra lại email!");
        } else if (check_Mail_Phone(Email_New_Student, Phone_New_Student)) {
            JOptionPane.showMessageDialog(null, "Email hoặc Số điện thoại đã được sử dụng!");
        } else {
            tbl_Student New_Student = new tbl_Student(Masv_New_Student, Name_New_Student, Phone_New_Student,
                Email_New_Student, Address_New_Student, DOB_New_Student, 1, Class_ID_New_Student, "", gender_New_Student);
            Student_Controller SC = new Student_Controller(conn);
            if (SC.insert(New_Student) == 1) {
                JOptionPane.showMessageDialog(null, "Bạn đã thêm sinh viên mới thành công");
                getStudent("");
                JName.setText("");
                JMasv.setText("");
                JPhone.setText("");
                JEmail.setText("");
                Nam.doClick();
                try {
                    jDate.setDate(new SimpleDateFormat("yyyy/MM/dd").parse("2000/01/01"));
                } catch (ParseException ex) {
                    Logger.getLogger(Panel_User.class.getName()).log(Level.SEVERE, null, ex);
                }
                JAddress.setText("");
                JClass_Student.setSelectedIndex(0);
                loadTable();
            } else {
                JOptionPane.showMessageDialog(null, "Đã có lỗi xảy ra, vui lòng kiểm tra lại");
            }
        }
    }//GEN-LAST:event_addActionPerformed

    private void RefeshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefeshActionPerformed
        JName.setText("");
        JMasv.setText("");
        JPhone.setText("");
        JEmail.setText("");
        Nam.doClick();
        try {
            jDate.setDate(new SimpleDateFormat("yyyy/MM/dd").parse("2000/01/01"));
        } catch (ParseException ex) {
            Logger.getLogger(Panel_User.class.getName()).log(Level.SEVERE, null, ex);
        }
        JAddress.setText("");
        JClass_Student.setSelectedIndex(0);
    }//GEN-LAST:event_RefeshActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        tbl_Student student = LS.get(Data_Table.getSelectedRow());
        int id = student.getId();
        int deleteItem = JOptionPane.showConfirmDialog(null,"Bạn có chắc muốn xoá sinh viên? Tất cả điểm của sinh viên cũng sẽ bị xoá", "Warning",JOptionPane.YES_NO_OPTION);
        if(deleteItem == JOptionPane.YES_OPTION)
        {
            Student_Controller SC = new Student_Controller(conn);
            SC.delete(id);
            JOptionPane.showMessageDialog(this,"Xoá thành công");
            getStudent("");
            JName.setText("");
            JMasv.setText("");
            JPhone.setText("");
            JEmail.setText("");
            Nam.doClick();
            try 
            {
                    jDate.setDate(new SimpleDateFormat("yyyy/MM/dd").parse("2000/01/01"));
            } catch (ParseException ex) {
                    Logger.getLogger(Panel_User.class.getName()).log(Level.SEVERE, null, ex);
                }
            JAddress.setText("");
            JClass_Student.setSelectedIndex(0);
            loadTable();
        }
        
        
    }//GEN-LAST:event_deleteActionPerformed

    private void ExportExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExportExcelActionPerformed
    
        try{
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.showSaveDialog(this);
        File saveFile = jFileChooser.getSelectedFile();
        if (saveFile != null) {
            saveFile = new File(saveFile.toString()+ ".xlsx");
            Workbook wb = new XSSFWorkbook();
            Sheet sheet = wb.createSheet("Danh sách sinh viên");
            
            Row rowCol = sheet.createRow(0);
            for (int i=0; i<Data_Table.getColumnCount();i++) {
                Cell cell = rowCol.createCell(i);
                cell.setCellValue(Data_Table.getColumnName(i));
            }
            for (int j=0; j<Data_Table.getRowCount();j++) {
                Row row = sheet.createRow(j+1);
                for (int k=0; k<Data_Table.getColumnCount();k++) {
                    Cell cell = row.createCell(k);
                    if(Data_Table.getValueAt(j, k) != null) {
                        cell.setCellValue(Data_Table.getValueAt(j, k).toString());
                    }
                }
            }
            FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
            wb.write(out);
            wb.close();
            out.close();
            openFile(saveFile.toString());
        } 
    }catch (FileNotFoundException e) {
        System.out.println(e);
    }catch (IOException io) {
        System.out.println(io);
    }    
    }//GEN-LAST:event_ExportExcelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Button_Search;
    private javax.swing.JComboBox<String> Class_Search;
    private javax.swing.JTable Data_Table;
    private javax.swing.JMenuItem Edit;
    private javax.swing.JButton ExportExcel;
    private javax.swing.ButtonGroup Gender;
    private javax.swing.JTextField JAddress;
    private javax.swing.JComboBox<String> JClass_Student;
    private javax.swing.JTextField JEmail;
    private javax.swing.JTextField JMasv;
    private javax.swing.JTextField JName;
    private javax.swing.JTextField JPhone;
    private javax.swing.JRadioButton Khac;
    private javax.swing.JRadioButton Nam;
    private javax.swing.JRadioButton Nu;
    private javax.swing.JButton Refesh;
    private javax.swing.JButton ReloadTable;
    private javax.swing.JTextField Search;
    private javax.swing.JMenuItem View;
    private javax.swing.JMenuItem View_Mark;
    private javax.swing.JButton add;
    private javax.swing.JButton delete;
    private com.toedter.calendar.JDateChooser jDate;
    private javax.swing.JDesktopPane jDesktopPane;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void load_table() {
        if (acc.getRole_ID() == 1 || acc.getRole_ID() == 2) {
            getStudent("");
        } else {
            getStudent(" where Class_ID in (select ID from tbl_Class where Teacher_ID = " + acc.getID() + " )");
        }
        loadTable();
    }
}
