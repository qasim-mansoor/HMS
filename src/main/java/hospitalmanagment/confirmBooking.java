package hospitalmanagment;

//import com.google.protobuf.TextFormat.ParseException;
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
import org.jdatepicker.impl.*;
import org.jdatepicker.util.*;
import org.jdatepicker.*;

public class confirmBooking extends JFrame implements ActionListener {
    
    Date selectedDate;
    String applist[] = new String[4];
    JLabel l1,l2,l3,l4;
    JButton s1,s2,s3,s4;
    JLabel b1,b2,b3,b4;
    String date;
    int staffid,patientid;
    confirmBooking(String d, int sid, int pid)
    {
        super("Make a Booking");
        
        date = d;
        staffid = sid;
        patientid = pid;
        
        System.out.println(d);
        System.out.println(sid);
        System.out.println(pid);
        l1 = new JLabel ("10:00 - 12:00");
        l2 = new JLabel ("12:00 - 02:00");
        l3 = new JLabel ("02:00 - 04:00");
        l4 = new JLabel ("04:00 - 06:00");
        s1 = new JButton ("Book");
        s2 = new JButton ("Book");
        s3 = new JButton ("Book");
        s4 = new JButton ("Book");
        b1 = new JLabel ("Booked");
        b2 = new JLabel ("Booked");
        b3 = new JLabel ("Booked");
        b4 = new JLabel ("Booked");
                
        
        setSize(400,400);
        setLocationRelativeTo(null);
        setLayout(null);
        
        try
        {
            Databaseconn c1 = new Databaseconn();
            
            String q = "select staffid, slot from bookings where date = '"+d+"' and staffid = "+sid+" order by slot";
            ResultSet rs = c1.query.executeQuery(q);
            int i = 0;
            while(rs.next())
            {
                applist[i] = rs.getString("slot");
//                System.out.println(applist[i]);
                i++;
                
            }
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        l1.setBounds (60, 90, 100, 30);
        l2.setBounds (60, 150, 100, 30);
        l3.setBounds (60, 210, 100, 30);
        l4.setBounds (60, 270, 100, 30);
        
        s1.setBounds (200, 90, 100, 30);
        s2.setBounds (200, 150, 100, 30);
        s3.setBounds (200, 210, 100, 30);
        s4.setBounds (200, 270, 100, 30);
        
        s1.addActionListener(this);
        s2.addActionListener(this);
        s3.addActionListener(this);
        s4.addActionListener(this);
        
        s1.setActionCommand("s1");
        s2.setActionCommand("s2");
        s3.setActionCommand("s3");
        s4.setActionCommand("s4");
        
        b1.setBounds (230, 90, 100, 30);
        b2.setBounds (230, 150, 100, 30);
        b3.setBounds (230, 210, 100, 30);
        b4.setBounds (230, 270, 100, 30);
        b1.setForeground(Color.RED);
        b2.setForeground(Color.RED);
        b3.setForeground(Color.RED);
        b4.setForeground(Color.RED);
        
        add (l1);
        add (l2);
        add (l3);
        add (l4);
        
        for(int i = 0;i<4;i++)
        {
            if (applist[i] == null)
            {
                applist[i] = "garbage";
            }
        }
        
//        System.out.println(applist[0] + " " + applist[1] + " " + applist[2] + " "+ applist[3]);
        if(applist[0].equals("1") || applist[1].equals("1") || applist[2].equals("1") || applist[3].equals("1"))
        {
            System.out.println("Here");
            add(b1);
        }
        else
        {
            add(s1);
        }
        
        if(applist[0].equals("2") || applist[1].equals("2") || applist[2].equals("2") || applist[3].equals("2"))
        {
            add(b2);
        }
        else
        {
            add(s2);
        }
        
        if(applist[0].equals("3") || applist[1].equals("3") || applist[2].equals("3") || applist[3].equals("3"))
        {
            add(b3);
        }
        else
        {
            add(s3);
        }
        
        if(applist[0].equals("4") || applist[1].equals("4") || applist[2].equals("4") || applist[3].equals("4"))
        {
            add(b4);
        }
        else
        {
            add(s4);
        }
        
        
//        UtilDateModel model = new UtilDateModel();
//        //model.setDate(20,04,2014);
//        // Need this...
//        Properties p = new Properties();
//        p.put("text.today", "Today");
//        p.put("text.month", "Month");
//        p.put("text.year", "Year");
//        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
//        // Don't know about the formatter, but there it is...
//        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
//        
////        LocalDate local = (Date) datePicker.getModel().getValue();
//        Date selectedDate = (Date) datePicker.getModel().getValue();
        
//        add(datePicker);
//        add(datePanel);
//        
//        datePicker.setBounds(60, 65, 100, 25);
//        datePanel.setBounds(60, 95, 150, 30);
        
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
        
        
    }
    
//    public class DateLabelFormatter extends AbstractFormatter {
//
//        private String datePattern = "yyyy-MM-dd";
//        private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
//
//        @Override
//        public Object stringToValue(String text) {
//            try{
//            return dateFormatter.parseObject(text);
//            }
//            catch(Exception e){return null;}
//            
//                
//        }
//
//        @Override
//        public String valueToString(Object value){
//            if (value != null) {
//                Calendar cal = (Calendar) value;
//                return dateFormatter.format(cal.getTime());
//            }
//
//            return "";
//        }
//
//    }
    
    public void actionPerformed(ActionEvent ae)
    {
        String command = ae.getActionCommand();
        if(command.equals("s1"))
        {
            try
            {
                Databaseconn c1 = new Databaseconn();

                String q = "insert into bookings values(null,'"+patientid+"','"+staffid+"','1','"+date+"')";
                c1.query.executeUpdate(q);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
            setVisible(false);
            JOptionPane.showMessageDialog(null,"Appointment Booked");
            
        }
        else if(command.equals("s2"))
        {
            try
            {
                Databaseconn c1 = new Databaseconn();

                String q = "insert into bookings values(null,'"+patientid+"','"+staffid+"','2','"+date+"')";
                c1.query.executeUpdate(q);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            setVisible(false);
            JOptionPane.showMessageDialog(null,"Appointment Booked");
        }
        else if(command.equals("s3"))
        {
            try
            {
                Databaseconn c1 = new Databaseconn();

                String q = "insert into bookings values(null,'"+patientid+"','"+staffid+"','3','"+date+"')";
                c1.query.executeUpdate(q);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            setVisible(false);
            JOptionPane.showMessageDialog(null,"Appointment Booked");
        }
        if(command.equals("s4"))
        {
            try
            {
                Databaseconn c1 = new Databaseconn();

                String q = "insert into bookings values(null,'"+patientid+"','"+staffid+"','4','"+date+"')";
                c1.query.executeUpdate(q);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            setVisible(false);
            JOptionPane.showMessageDialog(null,"Appointment Booked");
        }
    }
}
