/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalmanagment;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.*;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;

public class enterDiagnosis extends JFrame implements ActionListener{
    JLabel pidLabel;
    JTextField pid;
    JLabel diagLabel;
    JTextArea diagnosis;
    JButton upload;
    int staffid;
    
    enterDiagnosis(int sid)
    {
        super("Upload Diagnosis");
        
        staffid = sid;
        pidLabel = new JLabel ("Patient ID");
        pid = new JTextField (5);
        diagLabel = new JLabel ("Diagnosis");
        diagnosis = new JTextArea (5, 5);
        upload = new JButton ("Upload Diagnosis");
        
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        //adjust size and set layout
        setSize (581, 400);
        setLayout (null);
        setLocationRelativeTo(null);

        //add components
        add (pidLabel);
        add (pid);
        add (diagLabel);
        add (diagnosis);
        add (upload);

        //set component bounds (only needed by Absolute Positioning)
        pidLabel.setBounds (55, 40, 100, 25);
        pid.setBounds (55, 70, 175, 30);
        diagLabel.setBounds (55, 120, 100, 25);
        diagnosis.setBounds (55, 150, 465, 120);
        upload.setBounds (215, 295, 140, 35);
        diagnosis.setBorder(border);
        pid.setBorder(border);
        
        
//        t.setBorder(BorderFactory.createCompoundBorder(border,
//        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        
        upload.addActionListener(this);
        upload.setActionCommand("Upload");
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        String command = ae.getActionCommand();
        if(command.equals("Upload"))
        {
            try{
                Databaseconn c1 = new Databaseconn();
                int id = Integer.parseInt(pid.getText());
                String d = diagnosis.getText();
//                String diagnosis = 
//                String pwr = password.getText();
                
                String q = "select * from patient where patientid = "+id;
                
                ResultSet rs = c1.query.executeQuery(q);
                
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
                LocalDateTime now = LocalDateTime.now();  
//                System.out.println(dtf.format(now));
                
                if(rs.next())
                {
                    String q2 = "insert into diagnosis values( null,"+id+","+staffid+",'"+d+"','"+dtf.format(now)+"')" ;
//                    ResultSet rs2 = c1.query.executeQuery(q2);
//                    rs2.next();
                    try{
                        c1.query.executeUpdate(q2);
                        JOptionPane.showMessageDialog(null,"Diagnosis Uploaded Successfully!");
                    }
                    catch(Exception e){
                        JOptionPane.showMessageDialog(null,"Could not upload diagnosis. Please try again.");
//                        e.printStackTrace();
                    }
//                    new patientMain(rs2.getInt("patientid"),rs2.getString("firstname"),rs2.getString("lastname")).setVisible(true);
//                    p.setpid(rs2.getInt("patientid"));
//                    p.setfname(rs2.getString("firstname"));
//                    p.setlname(rs2.getString("lastname"));
//                    p.setVisible(true);
                    setVisible(false);
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Invalid Patient ID");
                    setVisible(false);
                }
                                
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        
    }
    
}
