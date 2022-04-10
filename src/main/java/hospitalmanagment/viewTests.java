package hospitalmanagment;

import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class viewTests extends JFrame{
    String headings[] = {"Doctor","Patient","Test"};
    String list[][] = new String[10][3];
    int pid[] = new int[10];
    int stid[] = new int[10];
    String tests[] = new String[10];
    int i=0,j=0;
    int staffID;
    JTable t1;
    
    viewTests(int sid)
    {
        super("Tests Ordered");
        
        setSize(400,320);
        setLocationRelativeTo(null);
        setLayout(null);
        
        staffID = sid;
        
        try
        {
            Databaseconn c1 = new Databaseconn();
            
            String q = "select patientid, staffid, testname from orderedtests  where orderedid not in (select orderedid from testresults)";
            ResultSet rs = c1.query.executeQuery(q);
            while(rs.next())
            {
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
        
        JScrollPane sp = new JScrollPane(t1);
        sp.setBounds(20,20,350,200);
        add(sp);
        
        getContentPane().setBackground(Color.WHITE);
        
    }
}
