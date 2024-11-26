/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package TeacherUI;

import Data.Controller.AddData;
import static Data.Controller.PopulateTable.populateCourseTable;
import Data.Database.DatabaseConnection;
import Data.Models.ModelCourse;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

/**
 *
 * @author User
 */
public class TeacherAddCourse extends javax.swing.JPanel {
    
       private AddData datacontroller;
    private DefaultTableModel courseTableModel;
    
    public TeacherAddCourse(String teacheriddata) {
        
        initComponents();
        
        idcourselist.setVisible(false);
        teacherid.setVisible(false);
        datacontroller = new AddData(courseTableModel);
        populateCourseTable(courseTable, teacheriddata);
    }
    public JTable getCourseTable() {
    return courseTable; // Return the JTable instance
}
    public void addBtn() {
        String teacherId = teacherid.getText();
        ModelCourse newdata = new ModelCourse(courseCode.getText(), courseName.getText(), description.getText(), teacherid.getText());

        // Add course to the database
        datacontroller.addCourseToDatabase(newdata);
        populateCourseTable(courseTable, teacherId);
        TextFieldEmpty();

        // Dynamically add the course to teacherStudent panel
        teacherStudent parentPanel = (teacherStudent) SwingUtilities.getAncestorOfClass(teacherStudent.class, this);
        if (parentPanel != null) {
            // Call addCourseBox with the new course details
            parentPanel.addCourseBox(courseCode.getText(), courseName.getText());
        }
    }

    public void deleteBtn() {
    String teacherId = teacherid.getText();
    String courseCodeToDelete = courseCode.getText(); // Identify the course to delete
    ModelCourse newdata = new ModelCourse(courseCodeToDelete, courseName.getText(), description.getText(), teacherId);

    // Delete the course from the database
    datacontroller.deleteCourseToDatabase(newdata);

    // Update the table
    populateCourseTable(courseTable, teacherId);
    TextFieldEmpty();

    // Remove the corresponding CourseBox from the container
    teacherStudent parentPanel = (teacherStudent) SwingUtilities.getAncestorOfClass(teacherStudent.class, this);
    if (parentPanel != null) {
        parentPanel.removeCourseBox(courseCodeToDelete);
    }
}

   public void updateBtn() {
    String teacherId = teacherid.getText();
    int idData = Integer.parseInt(idcourselist.getText());
    String courseCodeToUpdate = courseCode.getText();
    ModelCourse updatedData = new ModelCourse(courseCodeToUpdate, courseName.getText(), description.getText(), teacherId);

    // Update the course in the database
    datacontroller.updateCourseToDatabase(updatedData, idData);

    // Update the table
    populateCourseTable(courseTable, teacherId);
    TextFieldEmpty();

    // Update the corresponding CourseBox in the container
    teacherStudent parentPanel = (teacherStudent) SwingUtilities.getAncestorOfClass(teacherStudent.class, this);
    if (parentPanel != null) {
        parentPanel.updateCourseBox(courseCodeToUpdate, courseName.getText());
    }
}

    public void TextFieldEmpty(){
        courseCode.setText("");
        courseName.setText("");
        description.setText("");
        teacherid.setText("");
    }
    public void updateTeacherID(String teacherID) {
    teacherid.setText(teacherID); // Update the teacher ID field
    populateCourseTable(courseTable, teacherID); // Refresh the table for the new teacher
}
    
    public void GenerateCode() {
    String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    StringBuilder code;
    java.util.Random random = new java.util.Random();
    boolean isUnique;

    do {
        code = new StringBuilder();

        // Generate a 7-character code
        for (int i = 0; i < 7; i++) {
            int index = random.nextInt(chars.length());
            code.append(chars.charAt(index));
        }

        // Check if the code is unique in the database
        isUnique = checkCodeUniqueness(code.toString());
    } while (!isUnique); // Repeat if the code exists

    // Set the generated code to the JLabel courseCode
    courseCode.setText(code.toString());
}

private boolean checkCodeUniqueness(String code) {
    String sql = "SELECT COUNT(*) FROM courselist WHERE coursecode = ?";
    try (PreparedStatement ps = DatabaseConnection.getInstance().getConnection().prepareStatement(sql)) {
        ps.setString(1, code);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) == 0; // Returns true if no matching record is found
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false; // Assume not unique if an error occurs
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        courseTable = new javax.swing.JTable();
        jlabel = new javax.swing.JLabel();
        courseCode = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        courseName = new javax.swing.JTextField();
        jlabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        description = new javax.swing.JTextPane();
        generateCode = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        updateBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        idcourselist = new javax.swing.JLabel();
        teacherid = new javax.swing.JLabel();

        courseTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Course Code", "Course Name", "Description", "idcourselist", "teacherid"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        courseTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                courseTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(courseTable);
        if (courseTable.getColumnModel().getColumnCount() > 0) {
            courseTable.getColumnModel().getColumn(0).setResizable(false);
            courseTable.getColumnModel().getColumn(1).setResizable(false);
            courseTable.getColumnModel().getColumn(2).setResizable(false);
            courseTable.getColumnModel().getColumn(3).setMinWidth(0);
            courseTable.getColumnModel().getColumn(3).setPreferredWidth(0);
            courseTable.getColumnModel().getColumn(3).setMaxWidth(0);
            courseTable.getColumnModel().getColumn(4).setMinWidth(0);
            courseTable.getColumnModel().getColumn(4).setPreferredWidth(0);
            courseTable.getColumnModel().getColumn(4).setMaxWidth(0);
        }

        jlabel.setText("Course Code");

        jLabel1.setText("Course Name");

        jlabel1.setText("Description");

        jScrollPane2.setViewportView(description);

        generateCode.setText("Generate");
        generateCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateCodeActionPerformed(evt);
            }
        });

        addBtn.setText("Add");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        updateBtn.setText("Update");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });

        deleteBtn.setText("Delete");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jlabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                    .addComponent(courseName)
                    .addComponent(courseCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(generateCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(updateBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(deleteBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(idcourselist, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(teacherid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(courseCode, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(generateCode))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(courseName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jlabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(addBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(updateBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(deleteBtn)
                                .addGap(18, 18, 18)
                                .addComponent(idcourselist, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(teacherid, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void courseTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_courseTableMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) courseTable.getModel();
        int selectIndex = courseTable.getSelectedRow();
        courseCode.setText(model.getValueAt(selectIndex, 0).toString());
        courseName.setText(model.getValueAt(selectIndex, 1).toString());
         description.setText(model.getValueAt(selectIndex, 2).toString());
         idcourselist.setText(model.getValueAt(selectIndex , 3).toString());
         teacherid.setText(model.getValueAt(selectIndex, 4).toString());
    }//GEN-LAST:event_courseTableMouseClicked
    
    private void generateCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateCodeActionPerformed
        // TODO add your handling code here:
        GenerateCode();
    }//GEN-LAST:event_generateCodeActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        // TODO add your handling code here:
        addBtn();
    }//GEN-LAST:event_addBtnActionPerformed

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        // TODO add your handling code here:
        updateBtn();
    }//GEN-LAST:event_updateBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        // TODO add your handling code here:
        deleteBtn();
    }//GEN-LAST:event_deleteBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JLabel courseCode;
    private javax.swing.JTextField courseName;
    private javax.swing.JTable courseTable;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JTextPane description;
    private javax.swing.JButton generateCode;
    private javax.swing.JLabel idcourselist;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jlabel;
    private javax.swing.JLabel jlabel1;
    private javax.swing.JLabel teacherid;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables
}
