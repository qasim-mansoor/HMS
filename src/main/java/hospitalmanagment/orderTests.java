package hospitalmanagment;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;

public class orderTests extends JFrame  implements ActionListener{
    
    JButton order;
    private JLabel pidLabel;
    private JTextField pid;
    private JComboBox test;
    private JLabel testLabel;
    int staffID;
    
    orderTests(int sid)
    {
        String[] testItems = {"Albumin Test", "Allergy Test", "Ammonia Test", "CAT Scan", "CT Scan", "EEG", "Hepatitis A Test", "MRI Scan", "Stress Echo Test", "X-Ray Scan"};

        staffID = sid;
        //construct components
        order = new JButton ("Order Test");
        pidLabel = new JLabel ("Patient ID");
        pid = new JTextField (5);
        test = new JComboBox (testItems);
        testLabel = new JLabel ("Choose Test");

        //adjust size and set layout
        setSize (new Dimension (380, 340));
        setLocationRelativeTo(null);
        setLayout (null);

        //add components
        add (order);
        add (pidLabel);
        add (pid);
        add (test);
        add (testLabel);

        //set component bounds (only needed by Absolute Positioning)
        order.setBounds (105, 235, 150, 35);
        pidLabel.setBounds (60, 30, 100, 25);
        pid.setBounds (60, 60, 235, 35);
        test.setBounds (60, 160, 235, 35);
        testLabel.setBounds (60, 130, 100, 25);
        
        order.addActionListener(this);
        order.setActionCommand("order");
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        String command = ae.getActionCommand();
        
        if(command.equals("order"))
        {
            try
            {
                Databaseconn c1 = new Databaseconn();
                int id = Integer.parseInt(pid.getText());
                String q = "insert into orderedtests values(null, "+id+", "+staffID+",'"+test.getSelectedItem().toString()+"')";
                c1.query.executeUpdate(q);
                JOptionPane.showMessageDialog(null,"Test Ordered");
                setVisible(false);
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,"Could not order test. Please Try Again.");
            }
        }
    }
}
