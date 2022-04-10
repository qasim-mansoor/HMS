package hospitalmanagment;
   
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class printNum extends JFrame{
    
    printNum(int counter)
    {
        
//        try{
//            File f = new File("C:\\Users\\ok\\Desktop\\HospitalManagment\\src\\main\\java\\hospitalmanagment\\docs\\counter.jpg");
//            f.delete();
//        }catch(Exception e)
//        {
//            System.out.println("No cant do");
//        }
        Integer y = new Integer(counter);
        System.out.println(y);
        String text = y.toString();
        JLabel num = new JLabel(text);
        num.setFont(new Font("Calibri", Font.BOLD, 100));
        add(num);
        num.setBounds(120,40,200,200);
        setLayout(null);
        setSize(300,300);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        
//        String text = y.toString();
//        BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);// Represents an image with 8-bit RGBA color components packed into integer pixels.
//        Graphics2D graphics2d = image.createGraphics();
//        Font font = new Font("TimesNewRoman", Font.BOLD, 50);
//        graphics2d.setFont(font);
//        FontMetrics fontmetrics = graphics2d.getFontMetrics();
//        int width = fontmetrics.stringWidth(text);
//        int height = fontmetrics.getHeight();
//        graphics2d.dispose();
//
//        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
//        graphics2d = image.createGraphics();
//        graphics2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
//        graphics2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
//        graphics2d.setFont(font);
//        graphics2d.setBackground(Color.WHITE);
//        fontmetrics = graphics2d.getFontMetrics();
//        graphics2d.setColor(Color.BLACK);
//        graphics2d.drawString(text, 0, fontmetrics.getAscent());
//        graphics2d.dispose();
//        try {
//            ImageIO.write(image, "png", new File("C:\\Users\\ok\\Desktop\\HospitalManagment\\src\\main\\java\\hospitalmanagment\\docs\\counter.jpg"));
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        
////        String filepath = rs.getString("testresult");
////        System.out.println(filepath);
//        
////        try{
////        Thread.sleep(4000);}catch(Exception e){}
////        
////        f.setVisible(false);
//        new openImage();
//        try{
//            File f = new File("C:\\Users\\ok\\Desktop\\HospitalManagment\\src\\main\\java\\hospitalmanagment\\docs\\counter.jpg");
//            f.delete();
//        }catch(Exception e)
//        {
//            System.out.println("No cant do");
//        }
        
    }  
}  
