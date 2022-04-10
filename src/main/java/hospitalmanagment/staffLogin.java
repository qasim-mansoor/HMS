package hospitalmanagment;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class staffLogin extends JFrame implements ActionListener{
    JTextField staffid;
    JLabel idLabel;
    JLabel passLabel;
    JTextField password;
    JButton loginBtn;
    
    staffLogin()
    {
        super("Login");
        
        setBackground(Color.white);
        setLayout(null);
        
        staffid = new JTextField (5);
        idLabel = new JLabel ("Staff ID");
        passLabel = new JLabel ("Password");
        password = new JPasswordField (5);
        loginBtn = new JButton ("Login");
//        regBtn = new JButton ("Register");
        
        loginBtn.addActionListener(this);
//        regBtn.addActionListener(this);
        
        loginBtn.setActionCommand("login");
//        regBtn.setActionCommand("reg");
        
        setPreferredSize (new Dimension (353, 393));
        setLayout (null);
        
        add (staffid);
        add (idLabel);
        add (passLabel);
        add (password);
        add (loginBtn);
//        add (regBtn);
        
        staffid.setBounds (95, 95, 165, 30);
        idLabel.setBounds (95, 65, 100, 25);
        passLabel.setBounds (95, 170, 100, 25);
        password.setBounds (95, 200, 165, 30);
        loginBtn.setBounds (120, 305, 100, 25);
//        regBtn.setBounds (130, 325, 100, 25);
        
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
//        setUndecorated(true);
        setSize(380,450);
        setLocation(500,200);
        
    }
    
     public void actionPerformed(ActionEvent ae){
         String command = ae.getActionCommand();
         if(command.equals("login"))
         {
            try{
                Databaseconn c1 = new Databaseconn();
                int id = Integer.parseInt(staffid.getText());
                String pwr = password.getText();
                
                String q = "select * from staff where staffid = "+id+" and password = '"+pwr+"'";
                
                ResultSet rs = c1.query.executeQuery(q);
                
                if(rs.next())
                {
//                    String q2 = "select staffid, firstname, lastname, designation from staff where staffid = '"+id+"'";
//                    ResultSet rs2 = c1.query.executeQuery(q2);
//                    rs2.next();
                    if(rs.getString("designation").equals("doctor"))
                    {
                        new doctorMain(id,rs.getString("firstname"),rs.getString("lastname")).setVisible(true);
                        setVisible(false);
                    }
                    else if(rs.getString("designation").equals("lab"))
                    {
                        new labMain(id,rs.getString("firstname"),rs.getString("lastname")).setVisible(true);
                        setVisible(false);
                    }
//                    new patientMain(rs2.getInt("patientid"),rs2.getString("firstname"),rs2.getString("lastname")).setVisible(true);
//                    p.setpid(rs2.getInt("patientid"));
//                    p.setfname(rs2.getString("firstname"));
//                    p.setlname(rs2.getString("lastname"));
//                    p.setVisible(true);
                    else if(rs.getString("designation").equals("nurse"))
                    {
                        new nurseMain(id,rs.getString("firstname"),rs.getString("lastname")).setVisible(true);
                        setVisible(false);
                    }
                    else if(rs.getString("designation").equals("reception"))
                    {
                        new receptionistMain(id,rs.getString("firstname"),rs.getString("lastname")).setVisible(true);
                        setVisible(false);
                    }
                    setVisible(false);
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Invalid Login");
//                    setVisible(false);
                }
                                
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,"Invalid Login");
            }
         }
     }
  
}
