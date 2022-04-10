package hospitalmanagment;

import javax.swing.*;
import java.awt.*;

public class Background{
    public static void main(String args[])
    {
        Frame f = new Frame("Hayatabad Medical Complex");
        f.setVisible(true);
        int i;
        int x = 1;
        
        for(i=2;i<=600;i+=4,x+=1)
        {
            f.setLocation((550-((i+x)/2)),350-(i/2));
            f.setSize(i+3*x,i+x/2);
            
            try{
                Thread.sleep(10);
            }catch(Exception e){}
        }
        
        
    }
}

class Frame extends JFrame implements Runnable
{
    Thread t1;
    Frame(String s){
        super(s);
        setLayout(new FlowLayout());
        
//        ImageIcon c1 = new ImageIcon(ClassLoader.getSystemResource("C:\Users\ok\Desktop\HospitalManagment\src\main\java\hospitalmanagment\images\inside.jpg"));
        
        ImageIcon c1 = new ImageIcon("C:/Users/ok/Desktop/HospitalManagment/src/main/java/hospitalmanagment/images/inside.jpg");
        Image i1 = c1.getImage().getScaledInstance(1000, 700, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i1);
        
        JLabel m1 = new JLabel(i2);
        add(m1);
        t1 = new Thread(this);
        t1.start();
    }
    
    public void run()
    {
        try{
            Thread.sleep(4000);
            this.setVisible(true);
            ChooseUser user = new ChooseUser(this);
//            if("P".equals(user.getChoice()))
//            {
//                patientLogin login = new patientLogin();
//            }
//            else
//            {
//                JOptionPane.showMessageDialog(null,"Error");
//                setVisible(false);
//            }
            
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
}
