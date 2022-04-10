/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package hospitalmanagment;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;

/**
 *
 * @author ok
 */
public class uploadPreassessment extends JFrame implements ActionListener{
    private JButton upload;
//    private JLabel fnameLabel;
    private JLabel pidLabel;
//    private JLabel lnameLabel;
//    private JLabel emailLabel;
    private JLabel sysLabel;
//    private JLabel pwLabel;
    private JLabel diasLabel;
//    private JLabel bldgrpLabel;
    private JLabel pulseLabel;
    private JTextField pulse;
//    private JLabel genderLabel;
    private JLabel tempLabel;
    private JTextField temp;
//    private JRadioButton maleRadio;
//    private JRadioButton femaleRadio;
//    private JLabel phoneLabel;
//    private JTextField phoneno;
    private JTextField pid;
//    private JTextField lname;
    private JTextField sys;
    private JTextField dias;
    int staffID;
    
    uploadPreassessment(int sid)
    {
        super("Pre-Assessment Results");
        staffID = sid;
        String[] bloodgroupItems = {"A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};

        //construct components
        upload = new JButton ("Upload");
        pidLabel = new JLabel ("Patient ID");
//        lnameLabel = new JLabel ("Last Name");
        sysLabel = new JLabel ("Systolic BP");
        diasLabel = new JLabel ("Diastolic BP");
        pulseLabel = new JLabel ("Pulse Rate");
        pulse = new JTextField (5);
        tempLabel = new JLabel ("Temperature");
//        maleRadio = new JRadioButton ("Male");
//        femaleRadio = new JRadioButton ("Female");
//        phoneLabel = new JLabel ("Phone no.");
//        phoneno = new JTextField (5);
        pid = new JTextField (5);
//        lname = new JTextField (5);
        sys = new JTextField (5);
        dias = new JTextField (5);
        temp = new JTextField(5);

        //adjust size and set layout
        setSize(667, 430);
        setLayout (null);
//        setLocation(500,200);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        //add components
//        add (submit);
//        add (fnameLabel);
//        add (lnameLabel);
//        add (emailLabel);
//        add (pwLabel);
//        add (bldgrpLabel);
//        add (bloodgroup);
//        add (genderLabel);
//        add (maleRadio);
//        add (femaleRadio);
//        add (phoneLabel);
//        add (phoneno);
//        add (fname);
//        add (lname);
//        add (email);
//        add (password);

        add(pidLabel);
        add(pid);
        add(tempLabel);
        add(temp);
        add(sysLabel);
        add(sys);
        add(dias);
        add(diasLabel);
        add(pulseLabel);
        add(pulse);
        add(upload);

        //set component bounds (only needed by Absolute Positioning)
        upload.setBounds (40, 315, 585, 45);
        pidLabel.setBounds (40, 10, 100, 25);
//        lnameLabel.setBounds (345, 10, 100, 25);
        sysLabel.setBounds (40, 80, 100, 25);
        diasLabel.setBounds (345, 80, 100, 25);
        pulseLabel.setBounds (40, 150, 100, 25);
        pulse.setBounds(40,175,180,30);
//        bloodgroup.setBounds (40, 175, 180, 30);
        tempLabel.setBounds (345, 150, 100, 25);
//        maleRadio.setBounds (345, 175, 70, 30);
//        femaleRadio.setBounds (455, 175, 95, 30);
        temp.setBounds(345,175,180,30);
//        phoneLabel.setBounds (40, 220, 100, 25);
//        phoneno.setBounds (40, 250, 185, 30);
        pid.setBounds (40, 35, 180, 30);
//        lname.setBounds (345, 35, 180, 30);
        sys.setBounds (40, 105, 180, 30);
        dias.setBounds (345, 105, 180, 30);
        
        upload.addActionListener(this);
        upload.setActionCommand("upload");
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        String command = ae.getActionCommand();
        if(command.equals("submit"))
        {
            try{
                Databaseconn c1 = new Databaseconn();
                String f = pid.getText();
                String s = sys.getText();
                String d = dias.getText();
                String p = pulse.getText();
                String t = temp.getText();
//                String g;
//                if(maleRadio.isSelected())
//                {
//                    g = "M";
//                }
//                else
//                {
//                    g = "F";
//                }
                
//                String bld = bloodgroup.getSelectedItem().toString();
                
                try{
                    String q = "INSERT INTO preexam VALUES (NULL, '"+f+"', '"+staffID+"', '"+s+"', '"+d+"', '"+p+"', '"+t+"')";
                    c1.query.executeUpdate(q);
                    JOptionPane.showMessageDialog(null, "Results Uploaded!");
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null,"Could not upload results. Please Try Again.");
                }
             
                setVisible(false);
                
                                
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
