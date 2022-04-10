package hospitalmanagment;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
    
public class patientLogin extends JFrame implements ActionListener {
    JTextField email;
    JLabel emailLabel;
    JLabel passLabel;
    JPasswordField password;
    JButton loginBtn;
    JButton regBtn;
    
    patientLogin()
    {
        super("Login");
        
        setBackground(Color.white);
        setLayout(null);
        
        email = new JTextField (5);
        emailLabel = new JLabel ("Email");
        passLabel = new JLabel ("Password");
        password = new JPasswordField (5);
        loginBtn = new JButton ("Login");
        regBtn = new JButton ("Register");
        
        loginBtn.addActionListener(this);
        regBtn.addActionListener(this);
        
        loginBtn.setActionCommand("login");
        regBtn.setActionCommand("reg");
        
        setPreferredSize (new Dimension (353, 393));
        setLayout (null);
        
        add (email);
        add (emailLabel);
        add (passLabel);
        add (password);
        add (loginBtn);
        add (regBtn);
        
        email.setBounds (95, 95, 165, 30);
        emailLabel.setBounds (95, 65, 100, 25);
        passLabel.setBounds (95, 170, 100, 25);
        password.setBounds (95, 200, 165, 30);
        loginBtn.setBounds (130, 270, 100, 25);
        regBtn.setBounds (130, 325, 100, 25);
        
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
                String em = email.getText();
                String pwr = password.getText();
                
                String q = "select * from patient where email = '"+em+"' and password = '"+pwr+"'";
                
                ResultSet rs = c1.query.executeQuery(q);
                
                if(rs.next())
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
                else
                {
                    JOptionPane.showMessageDialog(null,"Invalid Login");
//                    setVisible(false);
                }
                                
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
         }
         else if(command.equals("reg"))
         {
             new patientRegister("p");
             setVisible(false);
         }
     }
}
