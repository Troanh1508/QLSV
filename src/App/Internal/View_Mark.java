/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.Internal;

import App.controller.Mark_Controller;
import App.controller.Subject_Controller;
import App.mail.Mail;
import App.model.tbl_Mark;
import App.model.tbl_Student;
import App.model.tbl_Subject;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
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
public class View_Mark extends javax.swing.JInternalFrame {

    /**
     * Creates new form View_Mark
     */
    Connection conn;
    tbl_Student s;
    List<tbl_Mark> LM;
    List<tbl_Subject> LS;
    tbl_Mark mark_edit;

    public View_Mark(Connection conn, tbl_Student s) {
        this.conn = conn;
        this.s = s;
        initComponents();
        Name.setText(s.getName());
        Student_Name.setText("Sinh viên " + s.getName());
        MaSV.setText(s.getMasv());
        getListMark(" WHERE Student_ID = " + s.getId());
        getListSubject();
        viewTable();
        setLayer();
        setComboStatus();
    }

    private void getListMark(String check) {
        Mark_Controller MC = new Mark_Controller(conn);
        LM = MC.select(check);
    }

    private void getListSubject() {
        Subject_Controller SC = new Subject_Controller(conn);
        LS = SC.select("");
    }

    private void setLayer() {
        jLayeredPane.removeAll();
        if (LM.size() == 0) {
            jLayeredPane.add(Update_Mark);
        } else {
            jLayeredPane.add(View_Mark);
        }
        jLayeredPane.repaint();
        jLayeredPane.revalidate();
    }

    private void viewTable() {
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Môn Học");
        dtm.addColumn("Số TC");
        dtm.addColumn("Điểm");
        dtm.addColumn("Trạng thái");
        dtm.addColumn("Ghi chú");
        for (tbl_Mark m : LM) {
            for (tbl_Subject sub : LS) {
                if (m.getSubject_ID() == sub.getID()) {
                    if(m.getStatus()== 1) {
                            Object o[] = {
                                sub.getName(), sub.getAccredit(), m.getMark(), "Đã Thi", m.getNote()
                            };
                            dtm.addRow(o);
                        }
                    else{
                            Object o[] = {
                                sub.getName(), sub.getAccredit(), m.getMark(), "Chưa thi", m.getNote()
                            };
                            dtm.addRow(o);
                    }
                }
            }
        }
        jTable.setAutoCreateRowSorter(true);
        jTable.setModel(dtm);
        jTable.setRowHeight(25);
    }

    private void setComboStatus() {
        DefaultComboBoxModel dcms = new DefaultComboBoxModel();
        dcms.addElement("Đã thi");
        dcms.addElement("Chưa thi");
        jComboStatus.setModel(dcms);
    }

    private void get_Mark_edit() {
        mark_edit = LM.get(jTable.getSelectedRow());
        if (mark_edit == null) {
            JOptionPane.showMessageDialog(null, "Đã có lỗi ở đâu đó");
        } else {
            for (tbl_Subject subject_Mark_Edit : LS) {
                if (subject_Mark_Edit.getID() == mark_edit.getSubject_ID()) {
                    Subject_Edit_Mark.setText("Môn " + subject_Mark_Edit.getName());
                }
            }
            jMark_Student.setText(String.valueOf(mark_edit.getMark()));
            jNote.setText(mark_edit.getNote());
            jComboStatus.setSelectedIndex(mark_edit.getStatus()-1);
            try {
                Ex_date.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(mark_edit.getEx_date()));
            } catch (ParseException ex) {
                Logger.getLogger(View_Mark.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void reloadFormEdit() {
        mark_edit = null;
        jMark_Student.setText("0.0");
        jComboStatus.setSelectedIndex(0);
        jNote.setText("");
        Subject_Edit_Mark.setText("Môn học");
        try {
            Ex_date.setDate(new SimpleDateFormat("yyyy-MM-dd").parse("2022-02-22"));
        } catch (ParseException ex) {
            Logger.getLogger(View_Mark.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void saveMark() {
        if (mark_edit == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn môn học cần sửa điểm để tiến hành");
        } else {
            float new_mark = Float.parseFloat(jMark_Student.getText());
            int new_Status_Mark = jComboStatus.getSelectedIndex() + 1;
            String new_note_mark = jNote.getText();
            if (new_mark > 10 || new_mark < 0) {
                JOptionPane.showMessageDialog(null, "Điểm không hợp lệ");
            } else if (new_mark != 0 && new_Status_Mark == 2) {
                JOptionPane.showMessageDialog(null, "Cập nhập điểm không chính xác, vui lòng kiểm tra lại");
            } else if (new_note_mark.length() > 490) {
                JOptionPane.showMessageDialog(null, "Ghi chú bạn thêm quá dài, không thể cập nhập");
            } else {
                mark_edit.setMark(new_mark);
                mark_edit.setNote(new_note_mark);
                mark_edit.setEx_date(new SimpleDateFormat("yyyy/MM/dd").format(Ex_date.getDate()));
                mark_edit.setStatus(new_Status_Mark);
                Mark_Controller MC = new Mark_Controller(conn);
                if (MC.update(mark_edit)) {
                    JOptionPane.showMessageDialog(null, "Bạn đã cập nhập điểm thành công");
                    getListMark(" WHERE Student_ID = " + s.getId());
                    getListSubject();
                    viewTable();
                    reloadFormEdit();
                } else {
                    JOptionPane.showMessageDialog(null, "Đã có lỗi xảy ra", "Cảnh báo", JOptionPane.ERROR);
                }
            }
        }
    }
    
    private String tinhCPA(){
        float sum = 0, cpa = 0;
        int sumTC = 0;
        getListMark("WHERE Student_ID = " + s.getId());
        for (tbl_Mark m : LM) {
            for (tbl_Subject sub : LS) {
                if (m.getSubject_ID() == sub.getID()) {
                    if(m.getStatus()==1){
                        sum += m.getMark() * sub.getAccredit();
                        sumTC += sub.getAccredit();
                    }
                }
            }
        }
        
        cpa = sum / sumTC;
        DecimalFormat df = new DecimalFormat("#.##");
        String truncatedCPA = df.format(cpa);
        return truncatedCPA;
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Name = new javax.swing.JLabel();
        MaSV = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLayeredPane = new javax.swing.JLayeredPane();
        Update_Mark = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        Mark_Update = new javax.swing.JButton();
        Exit = new javax.swing.JButton();
        View_Mark = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jComboBox = new javax.swing.JComboBox<>();
        Filter = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        Subject_Edit_Mark = new javax.swing.JLabel();
        Student_Name = new javax.swing.JLabel();
        jTextmark = new javax.swing.JLabel();
        jTextStatusMark = new javax.swing.JLabel();
        jComboStatus = new javax.swing.JComboBox<>();
        jTextNoteMark = new javax.swing.JLabel();
        Save_Edit = new javax.swing.JButton();
        UnchangeMark = new javax.swing.JButton();
        jMark_Student = new javax.swing.JFormattedTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jNote = new javax.swing.JEditorPane();
        jTextStatusMark1 = new javax.swing.JLabel();
        Ex_date = new com.toedter.calendar.JDateChooser();
        TinhCPA = new javax.swing.JButton();
        jLabelCPA = new javax.swing.JLabel();
        ExportMarks = new javax.swing.JButton();

        setClosable(true);
        setTitle("Mark of student");

        jLabel1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel1.setText("Full name:");

        Name.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        Name.setText("Nguyễn Văn A");

        MaSV.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        MaSV.setText("21212");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel4.setText("Mã SV");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(Name)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(MaSV, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(Name)
                    .addComponent(MaSV)
                    .addComponent(jLabel4)))
        );

        jLayeredPane.setLayout(new java.awt.CardLayout());

        jLabel2.setFont(new java.awt.Font("Arial", 0, 22)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 51, 51));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Sinh viên chưa được cập nhập danh sách điểm môn");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 51));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Vui Lòng Ấn \"Update Mark\" để thêm điểm các môn vào danh sách");

        Mark_Update.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        Mark_Update.setText("Update Mark");
        Mark_Update.setToolTipText("Cập nhập danh sách điểm môn học của sinh viên");
        Mark_Update.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Mark_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Mark_UpdateActionPerformed(evt);
            }
        });

        Exit.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        Exit.setText("Cancel");
        Exit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Exit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Mark_Update)
                .addGap(190, 190, 190))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Mark_Update, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Exit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Update_MarkLayout = new javax.swing.GroupLayout(Update_Mark);
        Update_Mark.setLayout(Update_MarkLayout);
        Update_MarkLayout.setHorizontalGroup(
            Update_MarkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Update_MarkLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Update_MarkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 864, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        Update_MarkLayout.setVerticalGroup(
            Update_MarkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Update_MarkLayout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(230, Short.MAX_VALUE))
        );

        jLayeredPane.add(Update_Mark, "card2");

        jTable.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTableMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable);

        jComboBox.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất Cả", "Chưa Thi", "Dưới 5", "Từ 5 đến 6.5", "Từ 6.5 đến 8", "Từ 8-10" }));

        Filter.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        Filter.setText("Lọc danh sách");
        Filter.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Filter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FilterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addComponent(Filter, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 2, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Filter)))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED), "Sửa điểm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 16))); // NOI18N

        Subject_Edit_Mark.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        Subject_Edit_Mark.setText("Môn học ");

        Student_Name.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        Student_Name.setText("Sinh viên Nguyễn Văn A");

        jTextmark.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jTextmark.setText("Điểm môn:");

        jTextStatusMark.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jTextStatusMark.setText("Trạng thái:");

        jComboStatus.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jComboStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTextNoteMark.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jTextNoteMark.setText("Ghi chú:");

        Save_Edit.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        Save_Edit.setText("Lưu chỉnh sửa");
        Save_Edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Save_EditActionPerformed(evt);
            }
        });

        UnchangeMark.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        UnchangeMark.setText("Hủy bỏ");
        UnchangeMark.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UnchangeMarkActionPerformed(evt);
            }
        });

        jMark_Student.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.0"))));
        jMark_Student.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jMark_Student.setText("0.0");
        jMark_Student.setToolTipText("");
        jMark_Student.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jMark_Student.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jMark_Student.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMark_StudentActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(jNote);

        jTextStatusMark1.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jTextStatusMark1.setText("Ngày thi:");

        Ex_date.setDateFormatString("dd/MM/yyyy");
        Ex_date.setPreferredSize(new java.awt.Dimension(69, 24));

        TinhCPA.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        TinhCPA.setText("Tính CPA");
        TinhCPA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TinhCPAActionPerformed(evt);
            }
        });

        jLabelCPA.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabelCPA.setText("CPA");

        ExportMarks.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        ExportMarks.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/excel.png"))); // NOI18N
        ExportMarks.setText("Xuất Excel");
        ExportMarks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExportMarksActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ExportMarks, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Student_Name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Subject_Edit_Mark, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TinhCPA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(UnchangeMark, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Save_Edit, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabelCPA))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextmark)
                            .addComponent(jTextStatusMark)
                            .addComponent(jTextStatusMark1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextNoteMark))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Ex_date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jMark_Student))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(Student_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(Subject_Edit_Mark, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jTextmark, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jMark_Student, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jComboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextStatusMark, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(Ex_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextStatusMark1))
                .addGap(5, 5, 5)
                .addComponent(jTextNoteMark, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UnchangeMark, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Save_Edit, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCPA, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TinhCPA, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ExportMarks, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout View_MarkLayout = new javax.swing.GroupLayout(View_Mark);
        View_Mark.setLayout(View_MarkLayout);
        View_MarkLayout.setHorizontalGroup(
            View_MarkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(View_MarkLayout.createSequentialGroup()
                .addGroup(View_MarkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, View_MarkLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        View_MarkLayout.setVerticalGroup(
            View_MarkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(View_MarkLayout.createSequentialGroup()
                .addGroup(View_MarkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(View_MarkLayout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLayeredPane.add(View_Mark, "card3");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLayeredPane)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_ExitActionPerformed

    private void Mark_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mark_UpdateActionPerformed
        Mark_Controller MC = new Mark_Controller(conn);
        boolean check = false;
        for (tbl_Subject sub : LS) {
            if (sub.getStatus() == 1) {
                tbl_Mark m = new tbl_Mark(s.getId(), sub.getID(), 0, 2, "", "2022/02/22");
                if (MC.insert(m) == 1) {
                    check = true;
                } else {
                    check = false;
                }
            }
        }
        if (check) {
            JOptionPane.showMessageDialog(null, "Bạn đã cập nhập điểm môn thành công");
            getListMark("WHERE Student_ID = " + s.getId());
            viewTable();
            setLayer();
        }
    }//GEN-LAST:event_Mark_UpdateActionPerformed

    private void FilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FilterActionPerformed
        int select_index = jComboBox.getSelectedIndex();
        switch (select_index) {
            case 0:
                getListMark(" WHERE Student_ID = " + s.getId());
                break;
            case 1:
                getListMark(" WHERE Student_ID = " + s.getId() + " AND Status = 2");
                break;
            case 2:
                getListMark(" WHERE Student_ID = " + s.getId() + " AND Mark <5 AND Status = 1");
                break;
            case 3:
                getListMark(" WHERE Student_ID = " + s.getId() + " AND Mark >=5 AND Mark < 6.5");
                break;
            case 4:
                getListMark(" WHERE Student_ID = " + s.getId() + " AND Mark >=6.5 AND Mark <8");
                break;
            default:
                getListMark(" WHERE Student_ID = " + s.getId() + " AND Mark >=8");
                break;
        }
        viewTable();
    }//GEN-LAST:event_FilterActionPerformed

    private void jTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMousePressed
        get_Mark_edit();
    }//GEN-LAST:event_jTableMousePressed

    private void UnchangeMarkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UnchangeMarkActionPerformed
        reloadFormEdit();
    }//GEN-LAST:event_UnchangeMarkActionPerformed

    private void Save_EditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Save_EditActionPerformed
        saveMark();
    }//GEN-LAST:event_Save_EditActionPerformed

    private void jMark_StudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMark_StudentActionPerformed
        jComboStatus.setSelectedIndex(0);
        saveMark();
    }//GEN-LAST:event_jMark_StudentActionPerformed

    private void TinhCPAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TinhCPAActionPerformed
       jLabelCPA.setText(tinhCPA() + "/10");
       if (Float.parseFloat(tinhCPA())<3) {
           int canhbao = JOptionPane.showConfirmDialog(null,"CPA nhỏ hơn 3. Gửi cảnh báo tới sinh viên?", "Warning",JOptionPane.YES_NO_OPTION);
           if(canhbao == JOptionPane.YES_OPTION){
               try {
                   File bangDiem = new File("Bảng điểm của sinh viên " + s.getName() + ".xlsx");
                   Workbook wb = new XSSFWorkbook();
                   Sheet sheet = wb.createSheet("Bảng điểm");
                   Row title = sheet.createRow(0);
                   title.createCell(0).setCellValue("Bảng điểm của sinh viên " + s.getName());
                   title.createCell(1).setCellValue("Mã SV: " + s.getMasv());
                   title.createCell(2).setCellValue("CPA: " + tinhCPA() + "/10");
                   Row rowCol = sheet.createRow(1);
                   for (int i=0; i<jTable.getColumnCount();i++) {
                   Cell cell = rowCol.createCell(i);
                   cell.setCellValue(jTable.getColumnName(i));
                   }
                   for (int j=0; j<jTable.getRowCount();j++) {
                       Row row = sheet.createRow(j+2);
                   for (int k=0; k<jTable.getColumnCount();k++) {
                   Cell cell = row.createCell(k);
                   if(jTable.getValueAt(j, k) != null) {
                       cell.setCellValue(jTable.getValueAt(j, k).toString());
                    }
                }
            }
            FileOutputStream out = new FileOutputStream(new File(bangDiem.toString()));
            wb.write(out);
            wb.close();
            out.close();
                   
                   Mail mail = new Mail();
                   mail.setupServerProperties();
                   mail.draftEmail(s.getEmail(),bangDiem.toString());
                   mail.sendEmail();
                   JOptionPane.showMessageDialog(null, "Gửi cảnh báo thành công");
               } catch (MessagingException | IOException ex) {
                   Logger.getLogger(View_Mark.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
       }
    }//GEN-LAST:event_TinhCPAActionPerformed

    private void ExportMarksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExportMarksActionPerformed
        try{
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.showSaveDialog(this);
        File saveFile = jFileChooser.getSelectedFile();
        if (saveFile != null) {
            saveFile = new File(saveFile.toString()+ ".xlsx");
            Workbook wb = new XSSFWorkbook();
            Sheet sheet = wb.createSheet("Bảng điểm");
            Row title = sheet.createRow(0);
            title.createCell(0).setCellValue("Bảng điểm của sinh viên " + s.getName());
            title.createCell(1).setCellValue("Mã SV: " + s.getMasv());
            title.createCell(2).setCellValue("CPA: " + tinhCPA() + "/10");
            Row rowCol = sheet.createRow(1);
            for (int i=0; i<jTable.getColumnCount();i++) {
                Cell cell = rowCol.createCell(i);
                cell.setCellValue(jTable.getColumnName(i));
            }
            for (int j=0; j<jTable.getRowCount();j++) {
                Row row = sheet.createRow(j+2);
                for (int k=0; k<jTable.getColumnCount();k++) {
                    Cell cell = row.createCell(k);
                    if(jTable.getValueAt(j, k) != null) {
                        cell.setCellValue(jTable.getValueAt(j, k).toString());
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
    }//GEN-LAST:event_ExportMarksActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser Ex_date;
    private javax.swing.JButton Exit;
    private javax.swing.JButton ExportMarks;
    private javax.swing.JButton Filter;
    private javax.swing.JLabel MaSV;
    private javax.swing.JButton Mark_Update;
    private javax.swing.JLabel Name;
    private javax.swing.JButton Save_Edit;
    private javax.swing.JLabel Student_Name;
    private javax.swing.JLabel Subject_Edit_Mark;
    private javax.swing.JButton TinhCPA;
    private javax.swing.JButton UnchangeMark;
    private javax.swing.JPanel Update_Mark;
    private javax.swing.JPanel View_Mark;
    private javax.swing.JComboBox<String> jComboBox;
    private javax.swing.JComboBox<String> jComboStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelCPA;
    private javax.swing.JLayeredPane jLayeredPane;
    private javax.swing.JFormattedTextField jMark_Student;
    private javax.swing.JEditorPane jNote;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable;
    private javax.swing.JLabel jTextNoteMark;
    private javax.swing.JLabel jTextStatusMark;
    private javax.swing.JLabel jTextStatusMark1;
    private javax.swing.JLabel jTextmark;
    // End of variables declaration//GEN-END:variables
}
