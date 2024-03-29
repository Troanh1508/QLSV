/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.panel;

import App.controller.Class_Controller;
import App.controller.Course_Controller;
import App.controller.Subject_Controller;
import App.model.tbl_Class;
import App.model.tbl_Course;
import App.model.tbl_Subject;
import App.model.tbl_Teacher;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.text.DateFormat;
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
public class Panel_Subject extends javax.swing.JPanel{

    /**
     * Creates new form Panel_Subject
     */
    tbl_Subject subject;
    tbl_Course course_Edit;
    tbl_Class edit_class;
    DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public Panel_Subject(Connection conn, tbl_Teacher acc) {
        this.conn = conn;
        this.acc = acc;
        initComponents();
        load_Subject_Table("");
        load_Course_Table("");
        set_Cbx_Subject();
        try {
            BeginDate.setDate(sdf.parse("2020-01-01"));
            End_Date.setDate(sdf.parse("2020-01-01"));
        } catch (ParseException ex) {
            Logger.getLogger(Panel_Subject.class.getName()).log(Level.SEVERE, null, ex);
        }
        Panel_Edit_Subject.setVisible(false);
        panel_status.setVisible(false);
        Save_Edit_Course.setVisible(false);
        End_Edit_Course.setVisible(false);

    }

    Connection conn;
    tbl_Teacher acc;

    List<tbl_Teacher> LT;
    List<tbl_Subject> LS;
    List<tbl_Course> LCA;
    List<tbl_Class> LC;
    List<tbl_Course> LCC;

    

    private void load_Subject_Table(String check) {
        Subject_Controller SC = new Subject_Controller(conn);
        LS = SC.select(check);
        DefaultTableModel DTM = new DefaultTableModel();
        DTM.addColumn("STT");
        DTM.addColumn("Tên môn");
        DTM.addColumn("Số tín");
        DTM.addColumn("Học phí");
        DTM.addColumn("Trạng thái");
        for (int i = 0; i < LS.size(); i++) {
            tbl_Subject s = LS.get(i);
            if (s.getStatus() == 1) {
                Object o[] = {
                    (i + 1), s.getName(), s.getAccredit(), s.getPrice(), "Đang giảng dạy"
                };
                DTM.addRow(o);
            } else {
                Object o[] = {
                    (i + 1), s.getName(), s.getAccredit(), s.getPrice(), "Đã ngưng giảng dạy"
                };
                DTM.addRow(o);
            }
        }
        Subject_Table.setAutoCreateRowSorter(true);
        Subject_Table.setModel(DTM);
        Subject_Table.setRowHeight(25);
    }

    private void load_Course_Table(String check) {
        Course_Controller CC = new Course_Controller(conn);
        Class_Controller Class_C = new Class_Controller(conn);
        LCC = CC.select(check);
        LCA = CC.select("");
        DefaultTableModel DTM = new DefaultTableModel();
        DTM.addColumn("STT");
        DTM.addColumn("Tên");
        DTM.addColumn("Số lớp");
        DTM.addColumn("Khai giảng");
        DTM.addColumn("Kết thúc");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dfS = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < LCC.size(); i++) {
            String begindate = "";
            String enddate = "";
            tbl_Course c = LCC.get(i);
            try {
                begindate = dfS.format(df.parse(c.getBegin_date()));
                enddate = dfS.format(df.parse(c.getEnd_date()));
            } catch (ParseException ex) {
                Logger.getLogger(Panel_Subject.class.getName()).log(Level.SEVERE, null, ex);
            }
            Object o[] = {
                (i + 1), c.getName(), Class_C.select(" WHERE Course_ID = " + c.getId()).size(), begindate, enddate
            };
            DTM.addRow(o);
        }
        Course_Table.setAutoCreateRowSorter(true);
        Course_Table.setModel(DTM);
        Course_Table.setRowHeight(25);
    }

    private void set_Cbx_Subject() {
        DefaultComboBoxModel DCM = new DefaultComboBoxModel();
        DCM.addElement("Đang giảng dạy");
        DCM.addElement("Đã ngưng giảng dạy");
        Cbx_Subject.setModel(DCM);
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

        Subject_Popup = new javax.swing.JPopupMenu();
        Subject_Edit = new javax.swing.JMenuItem();
        Course_Popup = new javax.swing.JPopupMenu();
        Course_Edit = new javax.swing.JMenuItem();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        Subject_Search = new javax.swing.JTextField();
        Cbx_Subject = new javax.swing.JComboBox<>();
        Search_Subject = new javax.swing.JButton();
        Reload_Subject = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Subject_Table = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        Sub_Name = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        Accredit = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        Price = new javax.swing.JFormattedTextField();
        panel_status = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        now = new javax.swing.JRadioButton();
        unnow = new javax.swing.JRadioButton();
        jPanel13 = new javax.swing.JPanel();
        Panel_Add_Subject = new javax.swing.JPanel();
        Add_Subject = new javax.swing.JButton();
        Refesh_Subject = new javax.swing.JButton();
        Delete_Subject = new javax.swing.JButton();
        ExportSubjects = new javax.swing.JButton();
        Panel_Edit_Subject = new javax.swing.JPanel();
        Save_Edit = new javax.swing.JButton();
        End_Edit = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        Seach_Course = new javax.swing.JTextField();
        Search_Course = new javax.swing.JButton();
        Reload_Course = new javax.swing.JButton();
        jPanel30 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        Course_Table = new javax.swing.JTable();
        jPanel32 = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        Name_Course = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        BeginDate = new com.toedter.calendar.JDateChooser();
        End_Date = new com.toedter.calendar.JDateChooser();
        jPanel34 = new javax.swing.JPanel();
        Add_Course = new javax.swing.JButton();
        Reset = new javax.swing.JButton();
        Save_Edit_Course = new javax.swing.JButton();
        End_Edit_Course = new javax.swing.JButton();
        Delete_Course = new javax.swing.JButton();
        ExportCourse = new javax.swing.JButton();

        Subject_Edit.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        Subject_Edit.setText("Sửa thông tin môn học");
        Subject_Edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Subject_EditActionPerformed(evt);
            }
        });
        Subject_Popup.add(Subject_Edit);

        Course_Edit.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        Course_Edit.setText("Sửa thông tin");
        Course_Edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Course_EditActionPerformed(evt);
            }
        });
        Course_Popup.add(Course_Edit);

        jDesktopPane1.setBackground(new java.awt.Color(240, 240, 240));

        jTabbedPane1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel2.setText("Subject name:");

        Subject_Search.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        Cbx_Subject.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        Cbx_Subject.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        Search_Subject.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        Search_Subject.setText("Search");
        Search_Subject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Search_SubjectActionPerformed(evt);
            }
        });

        Reload_Subject.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        Reload_Subject.setText("Reload List");
        Reload_Subject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Reload_SubjectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Subject_Search, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Cbx_Subject, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Search_Subject, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Reload_Subject, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(Search_Subject, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Cbx_Subject)
                    .addComponent(Subject_Search, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Reload_Subject, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        Subject_Table.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Subject_Table.setModel(new javax.swing.table.DefaultTableModel(
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
        Subject_Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Subject_TableMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(Subject_Table);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Thêm Môn Học Mới", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 16))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel4.setText("Subject Name");

        Sub_Name.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        Sub_Name.setToolTipText("");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel5.setText("Tín Chỉ:");

        Accredit.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel6.setText("Học Phí:");

        Price.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###0"))));
        Price.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        Price.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        Price.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PriceActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel7.setText("Status:");

        buttonGroup1.add(now);
        now.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        now.setText("Đang giảng dạy");
        now.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nowActionPerformed(evt);
            }
        });

        buttonGroup1.add(unnow);
        unnow.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        unnow.setText("Đã Ngưng giảng dạy");

        javax.swing.GroupLayout panel_statusLayout = new javax.swing.GroupLayout(panel_status);
        panel_status.setLayout(panel_statusLayout);
        panel_statusLayout.setHorizontalGroup(
            panel_statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_statusLayout.createSequentialGroup()
                .addGroup(panel_statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_statusLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(now, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(unnow, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        panel_statusLayout.setVerticalGroup(
            panel_statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_statusLayout.createSequentialGroup()
                .addGroup(panel_statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(now, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(unnow)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(panel_status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Sub_Name, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Accredit, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Price, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel4)
                .addGap(10, 10, 10)
                .addComponent(Sub_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel5)
                    .addComponent(Accredit, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel6)
                    .addComponent(Price, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 15, Short.MAX_VALUE)
                .addComponent(panel_status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Add_Subject.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        Add_Subject.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/Button-Add-icon.png"))); // NOI18N
        Add_Subject.setText("Thêm môn");
        Add_Subject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Add_SubjectActionPerformed(evt);
            }
        });

        Refesh_Subject.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        Refesh_Subject.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/Reset-icon.png"))); // NOI18N
        Refesh_Subject.setText("Reset");
        Refesh_Subject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Refesh_SubjectActionPerformed(evt);
            }
        });

        Delete_Subject.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        Delete_Subject.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/delete.png"))); // NOI18N
        Delete_Subject.setText("Xoá môn");
        Delete_Subject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Delete_SubjectActionPerformed(evt);
            }
        });

        ExportSubjects.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        ExportSubjects.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/excel.png"))); // NOI18N
        ExportSubjects.setText("Xuất Excel");
        ExportSubjects.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExportSubjectsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Panel_Add_SubjectLayout = new javax.swing.GroupLayout(Panel_Add_Subject);
        Panel_Add_Subject.setLayout(Panel_Add_SubjectLayout);
        Panel_Add_SubjectLayout.setHorizontalGroup(
            Panel_Add_SubjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_Add_SubjectLayout.createSequentialGroup()
                .addGroup(Panel_Add_SubjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Refesh_Subject, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ExportSubjects, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Panel_Add_SubjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Add_Subject, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Delete_Subject, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Panel_Add_SubjectLayout.setVerticalGroup(
            Panel_Add_SubjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_Add_SubjectLayout.createSequentialGroup()
                .addGroup(Panel_Add_SubjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Add_Subject)
                    .addComponent(Refesh_Subject))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Panel_Add_SubjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Delete_Subject)
                    .addComponent(ExportSubjects))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        Save_Edit.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        Save_Edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/edit.png"))); // NOI18N
        Save_Edit.setText("Lưu lại");
        Save_Edit.setToolTipText("");
        Save_Edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Save_EditActionPerformed(evt);
            }
        });

        End_Edit.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        End_Edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/Button-Close-icon.png"))); // NOI18N
        End_Edit.setText("Hủy bỏ");
        End_Edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                End_EditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Panel_Edit_SubjectLayout = new javax.swing.GroupLayout(Panel_Edit_Subject);
        Panel_Edit_Subject.setLayout(Panel_Edit_SubjectLayout);
        Panel_Edit_SubjectLayout.setHorizontalGroup(
            Panel_Edit_SubjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_Edit_SubjectLayout.createSequentialGroup()
                .addComponent(End_Edit, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Save_Edit, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        Panel_Edit_SubjectLayout.setVerticalGroup(
            Panel_Edit_SubjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_Edit_SubjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(Save_Edit)
                .addComponent(End_Edit))
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Panel_Add_Subject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Panel_Edit_Subject, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Panel_Add_Subject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Panel_Edit_Subject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(148, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Môn học", jPanel3);

        jLabel15.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel15.setText("Tên Khóa:");

        Seach_Course.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        Search_Course.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        Search_Course.setText("Search");
        Search_Course.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Search_CourseActionPerformed(evt);
            }
        });

        Reload_Course.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        Reload_Course.setText("Reload List");
        Reload_Course.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Reload_CourseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Seach_Course, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(Search_Course, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Reload_Course, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(Search_Course, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Seach_Course, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Reload_Course, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        Course_Table.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Course_Table.setModel(new javax.swing.table.DefaultTableModel(
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
        Course_Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Course_TableMousePressed(evt);
            }
        });
        jScrollPane4.setViewportView(Course_Table);

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 634, Short.MAX_VALUE)
        );

        jPanel32.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Thêm Khóa Học Mới", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 16))); // NOI18N

        jLabel16.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel16.setText("Tên Khóa Học:");

        Name_Course.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        Name_Course.setToolTipText("");

        jLabel17.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel17.setText("Ngày bắt đầu khóa:");

        jLabel18.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel18.setText("Ngày kết thúc khóa:");

        BeginDate.setDateFormatString("dd/MM/yyyy");
        BeginDate.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        End_Date.setDateFormatString("dd/MM/yyyy");
        End_Date.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Name_Course)
                    .addComponent(BeginDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(End_Date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel16)
                .addGap(10, 10, 10)
                .addComponent(Name_Course, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel17)
                .addGap(10, 10, 10)
                .addComponent(BeginDate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel18)
                .addGap(10, 10, 10)
                .addComponent(End_Date, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Add_Course.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        Add_Course.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/Button-Add-icon.png"))); // NOI18N
        Add_Course.setText("Thêm khoá");
        Add_Course.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Add_CourseActionPerformed(evt);
            }
        });

        Reset.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        Reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/Reset-icon.png"))); // NOI18N
        Reset.setText("Reset");
        Reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetActionPerformed(evt);
            }
        });

        Save_Edit_Course.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        Save_Edit_Course.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/edit.png"))); // NOI18N
        Save_Edit_Course.setText("Lưu lại");
        Save_Edit_Course.setToolTipText("");
        Save_Edit_Course.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Save_Edit_CourseActionPerformed(evt);
            }
        });

        End_Edit_Course.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        End_Edit_Course.setText("Thoát");
        End_Edit_Course.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                End_Edit_CourseActionPerformed(evt);
            }
        });

        Delete_Course.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        Delete_Course.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/delete.png"))); // NOI18N
        Delete_Course.setText("Xoá khoá");
        Delete_Course.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Delete_CourseActionPerformed(evt);
            }
        });

        ExportCourse.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        ExportCourse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/excel.png"))); // NOI18N
        ExportCourse.setText("Xuất Excel");
        ExportCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExportCourseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel34Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(End_Edit_Course, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(Save_Edit_Course, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Reset, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ExportCourse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Delete_Course, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel34Layout.createSequentialGroup()
                                .addComponent(Add_Course, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Reset)
                    .addComponent(Add_Course, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Delete_Course)
                    .addComponent(ExportCourse))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(End_Edit_Course, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Save_Edit_Course))
                .addContainerGap(217, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Khóa học", jPanel7);

        jDesktopPane1.setLayer(jTabbedPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 816, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPane1)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void Reload_SubjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Reload_SubjectActionPerformed
        load_Subject_Table("");
    }//GEN-LAST:event_Reload_SubjectActionPerformed

    private void Reload_CourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Reload_CourseActionPerformed
        load_Course_Table("");
    }//GEN-LAST:event_Reload_CourseActionPerformed

    private void Search_SubjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Search_SubjectActionPerformed
        String seach = Subject_Search.getText();
        int sta = Cbx_Subject.getSelectedIndex() + 1;
        load_Subject_Table(" WHERE Name LIKE N'%" + seach + "%'" + " AND status = " + sta);
    }//GEN-LAST:event_Search_SubjectActionPerformed

    private void Search_CourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Search_CourseActionPerformed
        String search = Seach_Course.getText();
        load_Course_Table(" WHERE Name LIKE N'%" + search + "%'");
    }//GEN-LAST:event_Search_CourseActionPerformed

    private void Refesh_SubjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Refesh_SubjectActionPerformed
        Sub_Name.setText("");
        Accredit.setValue(0);
        Price.setText("0");
    }//GEN-LAST:event_Refesh_SubjectActionPerformed

    private void ResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetActionPerformed
        Name_Course.setText("");
        try {
            BeginDate.setDate(sdf.parse("2020-01-01"));
            End_Date.setDate(sdf.parse("2020-01-01"));
        } catch (ParseException ex) {
            Logger.getLogger(Panel_Subject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ResetActionPerformed

    private void Add_SubjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Add_SubjectActionPerformed
        if (acc.getRole_ID() == 1 || acc.getRole_ID() == 2) {
            String name = Sub_Name.getText();
            int accredit = Integer.parseInt(String.valueOf(Accredit.getValue()));
            float price;
            if("".equals(Price.getText())){
            JOptionPane.showMessageDialog(null, "Học phí không thể để trống");
            } else {
            price = Float.parseFloat(Price.getText());
            if (price < 0) {
            JOptionPane.showMessageDialog(null, "Học phí không thể nhỏ hơn 0");
            } else if (accredit < 1){
            JOptionPane.showMessageDialog(null, "Số tín không thể nhỏ hơn 1");
            } else if ("".equals(name)) {
            JOptionPane.showMessageDialog(null, "Tên môn không được để trống");
            } else {
                tbl_Subject newsubject = new tbl_Subject(name, accredit, price, 1);
                Subject_Controller SC = new Subject_Controller(conn);
                if (SC.insert(newsubject) == 1) {
                    JOptionPane.showMessageDialog(null, "Thêm thành công");
                    Sub_Name.setText("");
                    Accredit.setValue(0);
                    Price.setText("0");
                } else {
                    JOptionPane.showMessageDialog(null, "Lỗi rồi!");
                }
            } 
            }
        }    else {
            JOptionPane.showMessageDialog(null, "Bạn không đủ quyền hạn để thực hiện");
        }
        load_Subject_Table("");
            
    }//GEN-LAST:event_Add_SubjectActionPerformed

    private void Add_CourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Add_CourseActionPerformed
        if (acc.getRole_ID() == 1 || acc.getRole_ID() == 2) {
            String name = Name_Course.getText();
            String begindate = sdf.format(BeginDate.getDate());
            String enddate = sdf.format(End_Date.getDate());
            if (!"".equals(name)) {
                tbl_Course new_course = new tbl_Course(name, begindate, enddate);
                Course_Controller CC = new Course_Controller(conn);
                if (CC.insert(new_course) == 1) {
                    JOptionPane.showMessageDialog(null, "Bạn đã thêm thành công");
                    Name_Course.setText("");
                    try {
                        BeginDate.setDate(sdf.parse("2020-01-01"));
                        End_Date.setDate(sdf.parse("2020-01-01"));
                    } catch (ParseException ex) {
                        Logger.getLogger(Panel_Subject.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Lỗi rồi");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Tên đang trống");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Bạn không đủ quyền hạn để thực hiện");
        }
        load_Course_Table("");
    }//GEN-LAST:event_Add_CourseActionPerformed

    private void Save_EditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Save_EditActionPerformed
        String name = (Sub_Name.getText());
        int accredit = Integer.parseInt(String.valueOf(Accredit.getValue()));
        float price;   
        if("".equals(Price.getText())){
            JOptionPane.showMessageDialog(null, "Học phí không thể để trống");
        } else {
            price = Float.parseFloat(Price.getText());
            if (price < 0) {
            JOptionPane.showMessageDialog(null, "Học phí không thể nhỏ hơn 0");
            } else{
                if (accredit < 1) {
            JOptionPane.showMessageDialog(null, "Số tín không thể bằng nhỏ hơn 1");
                } else if ("".equals(name)) {
            JOptionPane.showMessageDialog(null, "Tên môn không được để trống");
                } else {
            if (now.isSelected()) {
                    subject.setStatus(1);
                } else {
                subject.setStatus(2);
                }        
            subject.setName(name);
            subject.setAccredit(accredit);
            subject.setPrice(price);
            Subject_Controller SC = new Subject_Controller(conn);
            if (SC.update(subject)) {
                JOptionPane.showMessageDialog(null, "Bạn đã cập nhập dữ liệu thành công");
                load_Subject_Table("");
                Panel_Add_Subject.setVisible(true);
                Panel_Edit_Subject.setVisible(false);
                jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Thêm Môn Học Mới", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 16)));
                Sub_Name.setText("");
                Accredit.setValue(0);
                Price.setText("0");
                panel_status.setVisible(false);
                subject = null;
            } else {
                JOptionPane.showMessageDialog(null, "Lỗi rồi!");
            }
                
            }
        }
        }
    }//GEN-LAST:event_Save_EditActionPerformed

    private void End_EditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_End_EditActionPerformed
        Panel_Add_Subject.setVisible(true);
        Panel_Edit_Subject.setVisible(false);
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Thêm Môn Học Mới", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 16)));
        Sub_Name.setText("");
        Accredit.setValue(0);
        Price.setText("0");
        panel_status.setVisible(false);
        subject = null;
    }//GEN-LAST:event_End_EditActionPerformed

    private void Subject_TableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Subject_TableMousePressed
        if (acc.getRole_ID() == 1 || acc.getRole_ID() == 2) {
            Subject_Popup.show(Subject_Table, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_Subject_TableMousePressed

    private void Subject_EditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Subject_EditActionPerformed
        Panel_Add_Subject.setVisible(false);
        Panel_Edit_Subject.setVisible(true);
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Sửa Thông Tin Môn Học", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 16)));
        subject = LS.get(Subject_Table.getSelectedRow());
        Sub_Name.setText(subject.getName());
        Accredit.setValue(subject.getAccredit());
        Price.setText(String.valueOf(subject.getPrice()));
        if (subject.getStatus() == 1) {
            now.setSelected(true);
        } else {
            unnow.setSelected(true);
        }
        panel_status.setVisible(true);
    }//GEN-LAST:event_Subject_EditActionPerformed

    private void nowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nowActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nowActionPerformed

    private void Course_TableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Course_TableMousePressed
        if (acc.getRole_ID() == 1 || acc.getRole_ID() == 2) {
            Course_Popup.show(Course_Table, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_Course_TableMousePressed

    private void Save_Edit_CourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Save_Edit_CourseActionPerformed
        String name_course = Name_Course.getText();
        String begin_date = sdf.format(BeginDate.getDate());
        String end_date = sdf.format(End_Date.getDate());
        if ("".equals(name_course)) {
            JOptionPane.showMessageDialog(null, "Tên khóa học đang để trống");
        } else {
            course_Edit.setName(name_course);
            course_Edit.setBegin_date(begin_date);
            course_Edit.setEnd_date(end_date);
            Course_Controller CC = new Course_Controller(conn);
            if (CC.update(course_Edit)) {
                JOptionPane.showMessageDialog(null, "Đã Cập nhập thông tin thành công");
                course_Edit = null;
                jPanel32.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Thêm khóa học mới", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 16)));
                Reset.setVisible(true);
                Add_Course.setVisible(true);
                End_Edit_Course.setVisible(false);
                Save_Edit_Course.setVisible(false);
                load_Course_Table("");
                Name_Course.setText("");
                try {
                    BeginDate.setDate(sdf.parse("2020-01-01"));
                    End_Date.setDate(sdf.parse("2020-01-01"));
                } catch (ParseException ex) {
                    Logger.getLogger(Panel_Subject.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Lỗi rồi");
            }
        }
    }//GEN-LAST:event_Save_Edit_CourseActionPerformed

    private void End_Edit_CourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_End_Edit_CourseActionPerformed
        course_Edit = null;
        jPanel32.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Thêm khóa học mới", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 16)));
        Reset.setVisible(true);
        Add_Course.setVisible(true);
        Delete_Course.setVisible(true);
        End_Edit_Course.setVisible(false);
        Save_Edit_Course.setVisible(false);
        Name_Course.setText("");
        try {
            BeginDate.setDate(sdf.parse("2020-01-01"));
            End_Date.setDate(sdf.parse("2020-01-01"));
        } catch (ParseException ex) {
            Logger.getLogger(Panel_Subject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_End_Edit_CourseActionPerformed

    private void Course_EditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Course_EditActionPerformed
        course_Edit = LCC.get(Course_Table.getSelectedRow());
        jPanel32.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Thông tin khóa học", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 16)));
        Reset.setVisible(false);
        Add_Course.setVisible(false);
        Delete_Course.setVisible(false);
        End_Edit_Course.setVisible(true);
        Save_Edit_Course.setVisible(true);
        Name_Course.setText(course_Edit.getName());
        try {
            BeginDate.setDate(sdf.parse(course_Edit.getBegin_date()));
            End_Date.setDate(sdf.parse(course_Edit.getEnd_date()));
        } catch (ParseException ex) {
            Logger.getLogger(Panel_Subject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Course_EditActionPerformed

    private void PriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PriceActionPerformed

    private void Delete_SubjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Delete_SubjectActionPerformed
        tbl_Subject subjectDelete = LS.get(Subject_Table.getSelectedRow());
        int id = subjectDelete.getID();
        int deleteItem = JOptionPane.showConfirmDialog(null,"Bạn có chắc muốn xoá học phần? Điểm môn này của tất cả sinh viên cũng sẽ bị xoá", "Warning",JOptionPane.YES_NO_OPTION);
        if(deleteItem == JOptionPane.YES_OPTION){
            Subject_Controller SC = new Subject_Controller(conn);
            if(SC.delete(id)){
                JOptionPane.showMessageDialog(this,"Xoá thành công");
                Sub_Name.setText("");
                Accredit.setValue(0);
                Price.setText("0");
                load_Subject_Table("");
            } else {
                JOptionPane.showMessageDialog(null, "Lỗi rồi!");
            }
                
            
        }
    }//GEN-LAST:event_Delete_SubjectActionPerformed

    private void Delete_CourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Delete_CourseActionPerformed
        tbl_Course courseDelete = LCC.get(Course_Table.getSelectedRow());
        int id = courseDelete.getId();
        Class_Controller ClassC = new Class_Controller(conn);
        LC = ClassC.select(" WHERE Course_ID = " + courseDelete.getId());
        if (LC.size() > 0){
            JOptionPane.showMessageDialog(null, "Chỉ được xoá khoá không có lớp nào!");
        } else{
            int deleteItem = JOptionPane.showConfirmDialog(null,"Bạn có chắc muốn xoá khoá?", "Warning",JOptionPane.YES_NO_OPTION);
            if(deleteItem == JOptionPane.YES_OPTION){
                Course_Controller CC = new Course_Controller(conn);
                CC.delete(id);
                JOptionPane.showMessageDialog(this,"Xoá thành công");
                Name_Course.setText("");
                try {
                    BeginDate.setDate(sdf.parse("2020-01-01"));
                    End_Date.setDate(sdf.parse("2020-01-01"));
                } catch (ParseException ex) {
                        Logger.getLogger(Panel_Subject.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
         
    }//GEN-LAST:event_Delete_CourseActionPerformed

    private void ExportSubjectsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExportSubjectsActionPerformed

        try{
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.showSaveDialog(this);
        File saveFile = jFileChooser.getSelectedFile();
        if (saveFile != null) {
            saveFile = new File(saveFile.toString()+ ".xlsx");
            Workbook wb = new XSSFWorkbook();
            Sheet sheet = wb.createSheet("Danh sách môn học");
            
            Row rowCol = sheet.createRow(0);
            for (int i=0; i<Subject_Table.getColumnCount();i++) {
                Cell cell = rowCol.createCell(i);
                cell.setCellValue(Subject_Table.getColumnName(i));
            }
            for (int j=0; j<Subject_Table.getRowCount();j++) {
                Row row = sheet.createRow(j+1);
                for (int k=0; k<Subject_Table.getColumnCount();k++) {
                    Cell cell = row.createCell(k);
                    if(Subject_Table.getValueAt(j, k) != null) {
                        cell.setCellValue(Subject_Table.getValueAt(j, k).toString());
                    }
                }
            }
            FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
            wb.write(out);
            wb.close();
            out.close();
            openFile(saveFile.toString());
        } else {
            JOptionPane.showMessageDialog(null, "Lỗi thư mục");
        }
    }catch (FileNotFoundException e) {
        System.out.println(e);
    }catch (IOException io) {
        System.out.println(io);
    }    
                      
    }//GEN-LAST:event_ExportSubjectsActionPerformed

    private void ExportCourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExportCourseActionPerformed
        try{
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.showSaveDialog(this);
        File saveFile = jFileChooser.getSelectedFile();
        if (saveFile != null) {
            saveFile = new File(saveFile.toString()+ ".xlsx");
            Workbook wb = new XSSFWorkbook();
            Sheet sheet = wb.createSheet("Danh sách khoá học");
            
            Row rowCol = sheet.createRow(0);
            for (int i=0; i<Course_Table.getColumnCount();i++) {
                Cell cell = rowCol.createCell(i);
                cell.setCellValue(Course_Table.getColumnName(i));
            }
            for (int j=0; j<Course_Table.getRowCount();j++) {
                Row row = sheet.createRow(j+1);
                for (int k=0; k<Course_Table.getColumnCount();k++) {
                    Cell cell = row.createCell(k);
                    if(Course_Table.getValueAt(j, k) != null) {
                        cell.setCellValue(Course_Table.getValueAt(j, k).toString());
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
    }//GEN-LAST:event_ExportCourseActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner Accredit;
    private javax.swing.JButton Add_Course;
    private javax.swing.JButton Add_Subject;
    private com.toedter.calendar.JDateChooser BeginDate;
    private javax.swing.JComboBox<String> Cbx_Subject;
    private javax.swing.JMenuItem Course_Edit;
    private javax.swing.JPopupMenu Course_Popup;
    private javax.swing.JTable Course_Table;
    private javax.swing.JButton Delete_Course;
    private javax.swing.JButton Delete_Subject;
    private com.toedter.calendar.JDateChooser End_Date;
    private javax.swing.JButton End_Edit;
    private javax.swing.JButton End_Edit_Course;
    private javax.swing.JButton ExportCourse;
    private javax.swing.JButton ExportSubjects;
    private javax.swing.JTextField Name_Course;
    private javax.swing.JPanel Panel_Add_Subject;
    private javax.swing.JPanel Panel_Edit_Subject;
    private javax.swing.JFormattedTextField Price;
    private javax.swing.JButton Refesh_Subject;
    private javax.swing.JButton Reload_Course;
    private javax.swing.JButton Reload_Subject;
    private javax.swing.JButton Reset;
    private javax.swing.JButton Save_Edit;
    private javax.swing.JButton Save_Edit_Course;
    private javax.swing.JTextField Seach_Course;
    private javax.swing.JButton Search_Course;
    private javax.swing.JButton Search_Subject;
    private javax.swing.JTextField Sub_Name;
    private javax.swing.JMenuItem Subject_Edit;
    private javax.swing.JPopupMenu Subject_Popup;
    private javax.swing.JTextField Subject_Search;
    private javax.swing.JTable Subject_Table;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton now;
    private javax.swing.JPanel panel_status;
    private javax.swing.JRadioButton unnow;
    // End of variables declaration//GEN-END:variables

}
