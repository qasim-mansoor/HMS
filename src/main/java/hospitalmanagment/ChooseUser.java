package hospitalmanagment;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class ChooseUser extends JFrame implements ActionListener {
    
    JButton Patient;
    JButton Staff;
    String Choice;
    JFrame background;
    
    public ChooseUser(JFrame back)
    {
        super("Choose User");
        
        background = back;
//        setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
        setBackground(Color.white);
        setLayout(null);
        
        Icon patienticon = new ImageIcon("C:/Users/ok/Desktop/HospitalManagment/src/main/java/hospitalmanagment/images/patientbutton.jpg");
        Icon stafficon = new ImageIcon("C:/Users/ok/Desktop/HospitalManagment/src/main/java/hospitalmanagment/images/staffbutton.jpg");
        
        Patient = new JButton(patienticon);
        Staff = new JButton(stafficon);
        
        Patient.setBounds(40,55,400,270);
//        Patient.setOpacity(1f);
        add(Patient);
        
        Staff.setBounds(460,55,400,270);
        add(Staff);
        
        Patient.addActionListener(this);
        Staff.addActionListener(this);
        
        Patient.setActionCommand("Patient");
        Staff.setActionCommand("Staff");
        
        getContentPane().setBackground(Color.WHITE);
//        getContentPane().setOpacity(0.5f);
//        setOpaque(false);
        
        setUndecorated(true);
//        setOpacity(0.55f);
        
        setVisible(true);
        setSize(900,370);
        setLocation(250,200);
        
        
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        String command = ae.getActionCommand();
        if(command.equals("Patient"))
        {
            Choice = "P";
            setVisible(false);
            background.setVisible(false);
            patientLogin pet = new patientLogin();
        }
        else if(command.equals("Staff"))
        {
            Choice = "S";
            setVisible(false);
            background.setVisible(false);
            new staffLogin().setVisible(true);
        }
    }
    
    public String getChoice()
    {
        return Choice;
    }
}
