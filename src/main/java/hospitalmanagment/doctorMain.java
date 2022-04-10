package hospitalmanagment;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class doctorMain extends JFrame implements ActionListener{
    
    int staffID;
    String first;
    String last;
    
    doctorMain(int sid, String f, String l)
    {
        super("Hospital Management System for Doctors");
        
        staffID = sid;
        System.out.println(sid);
        first = f;
        last = l;
       
        setSize(1920,1030);
        
        ImageIcon c1 = new ImageIcon("C:/Users/ok/Desktop/HospitalManagment/src/main/java/hospitalmanagment/images/inside.jpg");
        Image i1 = c1.getImage().getScaledInstance(1900, 950, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i1);
        JLabel l1 = new JLabel(i2);
        
        add(l1);
        
        JMenuBar mb = new JMenuBar();
        
        JMenu Appointments = new JMenu("Appointments");
        JMenuItem a1 = new JMenuItem("View Booked Appointments");
//        JMenuItem a2 = new JMenuItem("View Booked Appointments");
        Appointments.setForeground(Color.BLUE);
        a1.setFont(new Font("monospaced",Font.BOLD,16));
        a1.setMnemonic('A');
        a1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        a1.setBackground(Color.WHITE);
//        a2.setFont(new Font("monospaced",Font.BOLD,16));
//        a2.setMnemonic('B');
//        a2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
//        a2.setBackground(Color.WHITE);
        
        a1.addActionListener(this);
//        a2.addActionListener(this);
        
        Appointments.add(a1);
//        Appointments.add(a2);
        
        JMenu Details = new JMenu(first + " " + last);
        JMenuItem p1 = new JMenuItem("Enter Diagnosis");
        JMenuItem p2 = new JMenuItem("Enter Prescription");
        JMenuItem p3 = new JMenuItem("Order Tests");
        JMenuItem p4 = new JMenuItem("View Test Results");
        Details.setForeground(Color.GREEN);
        p1.setFont(new Font("monospaced",Font.BOLD,16));
        p1.setMnemonic('C');
        p1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        p1.setBackground(Color.WHITE);
        
        p2.setFont(new Font("monospaced",Font.BOLD,16));
        p2.setMnemonic('D');
        p2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        p2.setBackground(Color.WHITE);
        
        p3.setFont(new Font("monospaced",Font.BOLD,16));
        p3.setMnemonic('E');
        p3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        p3.setBackground(Color.WHITE);
        
        p4.setFont(new Font("monospaced",Font.BOLD,16));
        p4.setMnemonic('F');
        p4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        p4.setBackground(Color.WHITE);
        
        p1.addActionListener(this);
        p2.addActionListener(this);
        p3.addActionListener(this);
        p4.addActionListener(this);
        
        Details.add(p1);
        Details.add(p2);
        Details.add(p3);
        Details.add(p4);
        
        
        JMenu exit = new JMenu("Exit");
        JMenuItem ex1 = new JMenuItem("Logout");
        JMenuItem ex2 = new JMenuItem("Quit");
        exit.setForeground(Color.BLUE);
        
        ex1.setFont(new Font("monospaced",Font.BOLD,16));
        ex1.setMnemonic('G');
        ex1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        ex1.setBackground(Color.WHITE);
        
        ex2.setFont(new Font("monospaced",Font.BOLD,16));
        ex2.setMnemonic('H');
        ex2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        ex2.setBackground(Color.WHITE);
        
        ex1.addActionListener(this);
        ex2.addActionListener(this);
        exit.add(ex1);
        exit.add(ex2);
        
        mb.add(Appointments);
        mb.add(Details);
        mb.add(exit);
        
        setJMenuBar(mb);
        
        setFont(new Font("Senserif",Font.BOLD,16));
        setLayout(new FlowLayout());
        setVisible(false);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
       
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        String msg = ae.getActionCommand();
        
        if(msg.equals("View Booked Appointments"))
        {
            new viewDoctorAppointments(staffID,"d").setVisible(true);
        }
        else if(msg.equals("Enter Diagnosis"))
        {
            new enterDiagnosis(staffID).setVisible(true);
        }
        else if(msg.equals("Enter Prescription"))
        {
            
        }
        else if(msg.equals("Order Tests"))
        {
            new orderTests(staffID).setVisible(true);
        }
        else if(msg.equals("View Test Results"))
        {
            new viewTestResults().setVisible(true);
        }
        else if(msg.equals("Logout"))
        {
            new staffLogin().setVisible(true);
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
        else if(msg.equals("Quit"))
        {
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
        
    }
}
