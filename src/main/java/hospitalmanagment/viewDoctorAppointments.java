/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalmanagment;

/**
 *
 * @author ok
 */

import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class viewDoctorAppointments extends JFrame{
    String headings[] = {"Patient","Date","Time"};
    String list[][] = new String[10][3];
    int bid[] = new int[10];
    int i=0,j=0;
    int staffID;
    JTable t1;
    String type;
    
    viewDoctorAppointments(int sid, String ty)
    {
        super("Your Appointments");
        type = ty;
        
        setSize(400,320);
        setLocationRelativeTo(null);
        setLayout(null);
        
        staffID = sid;
        
        try
        {
            Databaseconn c1 = new Databaseconn();
            String q;
            if(type.equals("d"))
            {
                q = "select bookingid, firstname, lastname, date, slot from bookings natural join patient where staffid = "+staffID;
            }
            else
            {
                q = "select bookingid, firstname, lastname, date, slot from bookings natural join patient";
            }
            ResultSet rs = c1.query.executeQuery(q);
            while(rs.next())
            {
                bid[i] = rs.getInt("bookingid");
                list[i][j++] = rs.getString("firstname") + " " + rs.getString("lastname");
                list[i][j++] = rs.getString("date");
                String temp = rs.getString("slot");
                if(temp.equals("1"))
                    list[i][j++] = "10:00 - 12:00";
                else if(temp.equals("2"))
                    list[i][j++] = "12:00 - 02:00";
                else if(temp.equals("3"))
                    list[i][j++] = "02:00 - 04:00";
                else
                    list[i][j++] = "04:00 - 06:00";
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
        
        JScrollPane sp = new JScrollPane(t1);
        sp.setBounds(20,20,350,200);
        add(sp);
        
        getContentPane().setBackground(Color.WHITE);
    }
}
