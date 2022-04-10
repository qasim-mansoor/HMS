/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalmanagment;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FrameLoader extends JFrame implements ActionListener{
        
    JFrame frame;
    JMenu exit;
    JMenuItem logout;
    JMenuItem quit;
    JMenuBar mb;
    
    FrameLoader()
    {
        exit = new JMenu("Exit");
        logout = new JMenuItem("Logout");
        quit = new JMenuItem("Quit");
        exit.setForeground(Color.BLUE);
        
        logout.setFont(new Font("monospaced",Font.BOLD,16));
        logout.setMnemonic('G');
        logout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        logout.setBackground(Color.WHITE);
        
        quit.setFont(new Font("monospaced",Font.BOLD,16));
        quit.setMnemonic('H');
        quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        quit.setBackground(Color.WHITE);
        
        logout.addActionListener(this);
        quit.addActionListener(this);
        
        exit.add(logout);
        exit.add(quit);
        
        mb.add(exit);
        mb.add(exit);
        
        frame.setJMenuBar(mb);
    }
    
    
    
    public void actionPerformed(ActionEvent e)
    {}
}
