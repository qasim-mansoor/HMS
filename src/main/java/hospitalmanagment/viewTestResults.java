package hospitalmanagment;

import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class viewTestResults extends JFrame implements ActionListener{
    
    JButton viewResults;
    JLabel pidLabel;
    JTextField pid;
    String headings[] = {"Patient","Test"};
    String list[][] = new String[10][2];
    JTable t1;
    int ids[] = new int[10];
    int pids[] = new int[10];
    int resultID;
    int i =0,j=0;
    
    viewTestResults()
    {
        viewResults = new JButton("View Test Results");
        pidLabel = new JLabel("Patient ID");
        pid = new JTextField(5);
        
        setSize (new Dimension (520, 400));
        setLayout (null);
        setLocationRelativeTo(null);
        
        viewResults.addActionListener(this);
        viewResults.setActionCommand("view");
        
        add(viewResults);
        add(pidLabel);
        add(pid);
        
        pidLabel.setBounds(60,30,100,25);
        pid.setBounds(60,60,235,35);
        viewResults.setBounds(300,60,150,35);
        
        getContentPane().setBackground(Color.WHITE);
        
        t1 = new JTable(list,headings);
        t1.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
        t1.getTableHeader().setBackground(new Color(111, 199, 180));
        
        JScrollPane sp = new JScrollPane(t1);
        sp.setBounds(60,130,390,180);
        add(sp);
        
        t1.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent evt)
            {
                if (evt.getClickCount() == 1) {
                    JTable target = (JTable)evt.getSource();
                    int row = target.getSelectedRow();
                    int column = target.getSelectedColumn();
                    resultID = ids[row];
                    
                    try{
                        Databaseconn c1 = new Databaseconn();
                        
                        String q = "select testresult from testresults where orderedid ="+resultID;
                        ResultSet rs = c1.query.executeQuery(q);
                        rs.next();
                        
                        String filepath = rs.getString("testresult");
                        System.out.println(filepath);
                        ImageIcon image = new ImageIcon(filepath);
                        Image i1 = image.getImage().getScaledInstance(600, 600, Image.SCALE_DEFAULT);
                        ImageIcon i2 = new ImageIcon(i1);
                        JLabel label = new JLabel(i2);
                        JFrame f = new JFrame();
//                        label.setLocationRelativeTo(null);
                        label.setBounds(50, 30, 600, 600);
                        label.setVisible(true);
//                        add(label);
                        f.add(label);
                        f.setSize(700,700);
                        f.setLocationRelativeTo(null);
                        f.setLayout(null);
                        f.getContentPane().setBackground(Color.WHITE);
                        f.setVisible(true);
                        
//                        JScrollPane scrollPane = new JScrollPane(label);
//                        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//                        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//                        add(scrollPane, BorderLayout.CENTER);
//                        pack();
                        
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });  
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        String command = ae.getActionCommand();
        
        if(command.equals("view"))
        {
            try
            {
                Databaseconn c1 = new Databaseconn();
                
                int patid = Integer.parseInt(pid.getText());
                String q = "select orderedid, firstname, lastname, testname from patient natural join testresults natural join orderedtests where patientid =" +patid;
                ResultSet rs = c1.query.executeQuery(q);
                while(rs.next())
                {
                    ids[i] = Integer.parseInt(rs.getString("orderedid"));
                    list[i][0] = rs.getString("firstname") + " " + rs.getString("lastname");
//                    pids[i] = Integer.parseInt(rs.getString("patientid"));
//                    stid[i] = Integer.parseInt(rs.getString("staffid"));
                    list[i][1] = rs.getString("testname");
                    i++;
                    j = 0;
                }


//                for(int k = 0;k<i;k++)
//                {
//                    String q2 = "select firstname, lastname from patient where patientid = "+pids[k];
//                    ResultSet rs2 = c1.query.executeQuery(q2);
//                    rs2.next();
//                    list[k][0] = rs2.getString("firstname") + " " + rs2.getString("lastname");
//                }
                
                t1 = new JTable(list,headings);
                t1.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
                t1.getTableHeader().setBackground(new Color(111, 199, 180));

                JScrollPane sp = new JScrollPane(t1);
                sp.setBounds(60,130,390,180);
                add(sp);
                
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }  
    
}
