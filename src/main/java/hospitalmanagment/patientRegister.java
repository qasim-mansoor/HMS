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
public class patientRegister extends JFrame implements ActionListener{
    private JButton submit;
    private JLabel fnameLabel;
    private JLabel lnameLabel;
    private JLabel emailLabel;
    private JLabel pwLabel;
    private JLabel bldgrpLabel;
    private JComboBox bloodgroup;
    private JLabel genderLabel;
    private JRadioButton maleRadio;
    private JRadioButton femaleRadio;
    private JLabel phoneLabel;
    private JTextField phoneno;
    private JTextField fname;
    private JTextField lname;
    private JTextField email;
    private JTextField password;
    
    String type;
    
    patientRegister(String t)
    {
        type = t;
        String[] bloodgroupItems = {"A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};

        //construct components
        submit = new JButton ("Submit");
        fnameLabel = new JLabel ("First Name");
        lnameLabel = new JLabel ("Last Name");
        emailLabel = new JLabel ("Email");
        pwLabel = new JLabel ("Password");
        bldgrpLabel = new JLabel ("Blood Group");
        bloodgroup = new JComboBox (bloodgroupItems);
        genderLabel = new JLabel ("Gender");
        maleRadio = new JRadioButton ("Male");
        femaleRadio = new JRadioButton ("Female");
        phoneLabel = new JLabel ("Phone no.");
        phoneno = new JTextField (5);
        fname = new JTextField (5);
        lname = new JTextField (5);
        email = new JTextField (5);
        password = new JTextField (5);

        //adjust size and set layout
        setSize(667, 430);
        setLayout (null);
//        setLocation(500,200);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        //add components
        add (submit);
        add (fnameLabel);
        add (lnameLabel);
        add (emailLabel);
        add (pwLabel);
        add (bldgrpLabel);
        add (bloodgroup);
        add (genderLabel);
        add (maleRadio);
        add (femaleRadio);
        add (phoneLabel);
        add (phoneno);
        add (fname);
        add (lname);
        add (email);
        add (password);

        //set component bounds (only needed by Absolute Positioning)
        submit.setBounds (40, 315, 585, 45);
        fnameLabel.setBounds (40, 10, 100, 25);
        lnameLabel.setBounds (345, 10, 100, 25);
        emailLabel.setBounds (40, 80, 100, 25);
        pwLabel.setBounds (345, 80, 100, 25);
        bldgrpLabel.setBounds (40, 150, 100, 25);
        bloodgroup.setBounds (40, 175, 180, 30);
        genderLabel.setBounds (345, 150, 100, 25);
        maleRadio.setBounds (345, 175, 70, 30);
        femaleRadio.setBounds (455, 175, 95, 30);
        phoneLabel.setBounds (40, 220, 100, 25);
        phoneno.setBounds (40, 250, 185, 30);
        fname.setBounds (40, 35, 180, 30);
        lname.setBounds (345, 35, 180, 30);
        email.setBounds (40, 105, 180, 30);
        password.setBounds (345, 105, 180, 30);
        
        submit.addActionListener(this);
        submit.setActionCommand("submit");
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        String command = ae.getActionCommand();
        if(command.equals("submit"))
        {
            try{
                Databaseconn c1 = new Databaseconn();
                String f = fname.getText();
                String l = lname.getText();
                String em = email.getText();
                String pwr = password.getText();
                String phone = phoneno.getText();
                String g;
                if(maleRadio.isSelected())
                {
                    g = "M";
                }
                else
                {
                    g = "F";
                }
                
                String bld = bloodgroup.getSelectedItem().toString();
                
                try{
                    String q = "INSERT INTO `patient` (`patientid`, `firstname`, `lastname`, `phone`, `email`, `password`, `gender`, `bloodgroup`) VALUES (NULL, '"+f+"', '"+l+"', '"+phone+"', '"+em+"', '"+pwr+"', '"+g+"', '"+bld+"')";
                    c1.query.executeUpdate(q);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
                
                
                if(type.equals("p"))
                {
                    String q2 = "select patientid, firstname, lastname from patient where email = '"+em+"'";
                    ResultSet rs2 = c1.query.executeQuery(q2);
                    rs2.next();
                    new patientMain(rs2.getInt("patientid"),rs2.getString("firstname"),rs2.getString("lastname")).setVisible(true);
//                    p.setpid(rs2.getInt("patientid"));
//                    p.setfname(rs2.getString("firstname"));
//                    p.setlname(rs2.getString("lastname"));
//                    p.setVisible(true);
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
