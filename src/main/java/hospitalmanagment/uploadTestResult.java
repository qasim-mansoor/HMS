package hospitalmanagment;

import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.table.DefaultTableModel;
import org.jdatepicker.impl.*;
import org.jdatepicker.util.*;
import org.jdatepicker.*;


public class uploadTestResult extends JFrame implements ActionListener {
    
    JTable t1;
    JLabel doctornameLabel;
    JLabel uploadfileLabel;
    JTextField doctorname;
    JLabel patientnameLabel;
    JTextField patientname;
    JButton upload;
    String headings[] = {"Doctor","Patient","Test"};
    String list[][] = new String[10][3];
    int pid[] = new int[10];
    int stid[] = new int[10];
    String tests[] = new String[10];
    int i=0,j=0;
    int orderedid;
    int ids[] = new int[10];
    String fileID;
    JTextField txtField;
    JButton btnBrowse;
    uploadTestResult()
    {
        
        super("Upload Test Result");
        
        setSize(750,450);
        setLocationRelativeTo(null);
        setLayout(null);
        
        doctornameLabel = new JLabel ("Doctor");
        doctorname = new JTextField (5);
        upload = new JButton ("Upload");
        patientnameLabel = new JLabel ("Patient");
        patientname = new JTextField(5);
        uploadfileLabel = new JLabel ("Upload a file");
        btnBrowse = new JButton("Browse");
        txtField = new JTextField (5);
        
        upload.addActionListener(this);
        upload.setActionCommand("upload");
        btnBrowse.addActionListener(this);
        
        
        add (doctornameLabel);
        add (doctorname);
        add (upload);
        add (patientnameLabel);
        add (patientname);
        add(uploadfileLabel);
        add(btnBrowse);
        add(txtField);
        
        doctornameLabel.setBounds (60, 30, 100, 25);
        doctorname.setBounds (60, 60, 150, 30);
        upload.setBounds (60, 315, 145, 30);
        patientname.setBounds (60, 155, 150, 30);
        patientnameLabel.setBounds (60, 125, 100, 25);
//        datePicker.setBounds(60, 250, 150, 30);
        uploadfileLabel.setBounds(60, 220, 100, 25);
        txtField.setBounds(60,250,150,30);
        btnBrowse.setBounds(210,250,100,30);
//        datePanel.setBounds(60, 250, 150, 30);
        
        try
        {
            Databaseconn c1 = new Databaseconn();
            
            String q = "select orderedid, patientid, staffid, testname from orderedtests where orderedid not in (select orderedid from testresults)";
            ResultSet rs = c1.query.executeQuery(q);
            while(rs.next())
            {
                ids[i] = Integer.parseInt(rs.getString("orderedid"));
                pid[i] = Integer.parseInt(rs.getString("patientid"));
                stid[i] = Integer.parseInt(rs.getString("staffid"));
                list[i][2] = rs.getString("testname");
                i++;
                j = 0;
            }
            
            
            for(int k = 0;k<i;k++)
            {
                String q2 = "select firstname, lastname from staff where staffid = "+stid[k];
                ResultSet rs2 = c1.query.executeQuery(q2);
                rs2.next();
                list[k][0] = rs2.getString("firstname") + " " + rs2.getString("lastname");
            }
            
            for(int k = 0;k<i;k++)
            {
                String q2 = "select firstname, lastname from patient where patientid = "+pid[k];
                ResultSet rs2 = c1.query.executeQuery(q2);
                rs2.next();
                list[k][1] = rs2.getString("firstname") + " " + rs2.getString("lastname");
            }
            
            t1 = new JTable(list,headings);
            t1.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
            t1.getTableHeader().setBackground(new Color(111, 199, 180));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
              
        t1.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent evt)
            {
                if (evt.getClickCount() == 1) {
                    JTable target = (JTable)evt.getSource();
                    int row = target.getSelectedRow();
                    int column = target.getSelectedColumn();
                    doctorname.setText(list[row][0]);
                    patientname.setText(list[row][1]);
//                    department.setSelectedItem(list[row][2]);
//                    staffid = list2[row];
                    orderedid = ids[row];
      // do some action if appropriate column
                }
            }
        });
        
        JScrollPane sp = new JScrollPane(t1);
        sp.setBounds(350,20,350,350);
        add(sp);
        
        
        
        getContentPane().setBackground(Color.WHITE);
        
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        String command = ae.getActionCommand();
        if(command.equals("upload"))
        {
            try{
                Databaseconn c1 = new Databaseconn();
                String q = "insert into testresults values ("+orderedid+",'" +txtField.getText()+"')";
                
                c1.query.executeUpdate(q);
                JOptionPane.showMessageDialog(null,"Results Uploaded Succefully!");
                setVisible(false);
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,"Could not upload file, Please try again.");
            }
        }
        else if(ae.getSource() == btnBrowse)
        {
            JFileChooser chooser = new JFileChooser(new File(System.getProperty("user.home") + "/Desktop/HospitalManagement")); //Downloads Directory as default
            chooser.setDialogTitle("Select Location");
            chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            chooser.setAcceptAllFileFilterUsed(false);

            if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
            { 
                String file = chooser.getSelectedFile().getPath();
                fileID = file.replaceAll("\\\\","/");
   
                txtField.setText(fileID);
//                System.out.println(fileID);
//                System.out.println("\\\\");
            }
        }
    }
    
    
}
