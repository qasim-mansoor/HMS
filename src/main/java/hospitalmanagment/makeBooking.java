package hospitalmanagment;

import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.table.DefaultTableModel;
import org.jdatepicker.impl.*;
import org.jdatepicker.util.*;
import org.jdatepicker.*;


public class makeBooking extends JFrame implements ActionListener {
    
    JTable t1;
    JLabel nameLabel;
    JTextField lname;
    JButton timings;
    JComboBox department;
    JLabel deptLabel;
    JLabel setdate;
    String headings[] = {"First Name","Last Name","Department"};
    String list[][] = new String[10][3];
    int list2[] = new int [10];
    int i=0,j=0;
    int patientID;
    int staffid;
    Date selectedDate;
    JDatePickerImpl datePicker;
    SimpleDateFormat d1;
    makeBooking(int pid)
    {
        
        super("Make a Booking");
        
        String[] depts = {"Cardiologist","ENT","Oncologist","Pediatrician","Dermatologist","Ophthalmologist"};
        
        patientID = pid;
        setSize(750,430);
        setLocationRelativeTo(null);
        setLayout(null);
        
        nameLabel = new JLabel ("Doctor");
        lname = new JTextField (5);
        timings = new JButton ("View Timings");
        department = new JComboBox(depts);
        deptLabel = new JLabel ("Department");
        setdate = new JLabel("Choose date");
        
        
        department.addActionListener(this);
        department.setActionCommand("dept");
        timings.addActionListener(this);
        timings.setActionCommand("submit");
        
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        
        
        add(datePicker);
        add(datePanel);
        
        add (nameLabel);
        add (lname);
        add (timings);
        add (department);
        add (deptLabel);
        add (setdate);
        
        nameLabel.setBounds (60, 30, 100, 25);
        lname.setBounds (60, 60, 150, 30);
        timings.setBounds (60, 320, 145, 30);
        department.setBounds (60, 155, 150, 30);
        deptLabel.setBounds (60, 125, 100, 25);
        datePicker.setBounds(60, 250, 150, 30);
        setdate.setBounds(60, 220, 100, 25);
//        datePanel.setBounds(60, 250, 150, 30);
        
        try
        {
            Databaseconn c1 = new Databaseconn();
            
            String q = "select staffid, firstname, lastname, department from staff natural join doctor order by department";
            ResultSet rs = c1.query.executeQuery(q);
            while(rs.next())
            {
                list2[i] = rs.getInt("staffid");
                list[i][j++] = rs.getString("firstname");
                list[i][j++] = rs.getString("lastname");
                list[i][j++] = rs.getString("department");
                i++;
                j = 0;
            }
            t1 = new JTable(list,headings);
            t1.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
            t1.getTableHeader().setBackground(new Color(111, 199, 180));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
              
        t1.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent evt)
            {
                if (evt.getClickCount() == 1) {
                    JTable target = (JTable)evt.getSource();
                    int row = target.getSelectedRow();
                    int column = target.getSelectedColumn();
                    lname.setText(list[row][0]);
                    department.setSelectedItem(list[row][2]);
                    staffid = list2[row];
      // do some action if appropriate column
                }
            }
        });
        
        JScrollPane sp = new JScrollPane(t1);
        sp.setBounds(350,20,350,350);
        add(sp);
        
        getContentPane().setBackground(Color.WHITE);
        
    }
    
    public class DateLabelFormatter extends AbstractFormatter {

        private String datePattern = "yyyy-MM-dd";
        private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

        @Override
        public Object stringToValue(String text) {
            try{
            return dateFormatter.parseObject(text);
            }
            catch(Exception e){return null;}
            
                
        }

        @Override
        public String valueToString(Object value){
            if (value != null) {
                Calendar cal = (Calendar) value;
                return dateFormatter.format(cal.getTime());
            }

            return "";
        }

    }
    
    public void actionPerformed(ActionEvent ae)
    {
        String command = ae.getActionCommand();
        if(command.equals("submit"))
        {
            selectedDate = (Date) datePicker.getModel().getValue();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String d = formatter.format(selectedDate);
            new confirmBooking(d,staffid,patientID);
            setVisible(false);
        }
    }
    
    
}
