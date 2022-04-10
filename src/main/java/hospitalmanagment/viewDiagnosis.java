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
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import org.jdatepicker.impl.*;
import org.jdatepicker.util.*;
import org.jdatepicker.*;


public class viewDiagnosis extends JFrame{
    
    JTable t1;
    JLabel nameLabel;
    JTextField lname;
    JTextArea diagnosis;
//    JButton timings;
//    JComboBox department;
    JLabel diagLabel;
//    JLabel setdate;
    String headings[] = {"Doctor","Department","Date"};
    String list[][] = new String[10][3];
    String list2[] = new String [10];
    int i=0,j=0;
    int patientID;
    int staffid;
//    Date selectedDate;
//    JDatePickerImpl datePicker;
//    SimpleDateFormat d1;
    viewDiagnosis(int pid)
    {
        
        super("View Your Diagnoses");
        
//        String[] depts = {"Cardiologist","ENT","Oncologist","Pediatrician","Dermatologist","Ophthalmologist"};
        
        patientID = pid;
        setSize(750,500);
        setLocationRelativeTo(null);
        setLayout(null);
        
        nameLabel = new JLabel ("Doctor");
        lname = new JTextField (5);
//        timings = new JButton ("View Timings");
//        department = new JComboBox(depts);
        diagLabel = new JLabel ("Diagnosis");
        diagnosis = new JTextArea(5,5);
//        setdate = new JLabel("Choose date");
        
        
//        department.addActionListener(this);
//        department.setActionCommand("dept");
//        timings.addActionListener(this);
//        timings.setActionCommand("submit");
        
//        UtilDateModel model = new UtilDateModel();
        //model.setDate(20,04,2014);
        // Need this...
//        Properties p = new Properties();
//        p.put("text.today", "Today");
//        p.put("text.month", "Month");
//        p.put("text.year", "Year");
//        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
//        // Don't know about the formatter, but there it is...
//        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
//        
////        LocalDate local = (Date) datePicker.getModel().getValue();
//        
//        add(datePicker);
//        add(datePanel);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        add (nameLabel);
        add (lname);
        add(diagLabel);
        add(diagnosis);
//        add (timings);
//        add (department);
//        add (deptLabel);
//        add (setdate);
        
        nameLabel.setBounds (60, 210, 100, 25);
        lname.setBounds (60, 240, 200, 30);
        diagLabel.setBounds(60,270,100,25);
        diagnosis.setBounds(60, 300, 600, 100);
        diagnosis.setBorder(border);
        diagnosis.setBorder(BorderFactory.createCompoundBorder(
        diagnosis.getBorder(), 
        BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        lname.setBorder(border);
        lname.setBorder(BorderFactory.createCompoundBorder(
        lname.getBorder(), 
        BorderFactory.createEmptyBorder(5, 5, 5, 5)));
//        timings.setBounds (60, 320, 145, 30);
//        department.setBounds (60, 155, 150, 30);
//        deptLabel.setBounds (60, 125, 100, 25);
//        datePicker.setBounds(60, 250, 150, 30);
//        setdate.setBounds(60, 220, 100, 25);
//        datePanel.setBounds(60, 250, 150, 30);
        
        try
        {
            Databaseconn c1 = new Databaseconn();
            
            String q = "select firstname, lastname, department, diagnosis, date from staff natural join diagnosis natural join doctor where patientid = "+patientID;
            ResultSet rs = c1.query.executeQuery(q);
            while(rs.next())
            {
                list2[i] = rs.getString("diagnosis");
                list[i][j++] = rs.getString("firstname") + " " + rs.getString("lastname");
                list[i][j++] = rs.getString("department");
                list[i][j++] = rs.getString("date");
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
                    diagnosis.setText(list2[row]);
      // do some action if appropriate column
                }
            }
        });
        
        JScrollPane sp = new JScrollPane(t1);
        sp.setBounds(60,60,600,150);
        add(sp);
    
//        add(t1);
        
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
    
}
