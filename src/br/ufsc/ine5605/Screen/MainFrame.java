package br.ufsc.ine5605.Screen;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class MainFrame extends JFrame{
    private JMenuBar menuBar;
    private JLabel userName;
    private JButton bt_logout;
    private JPanel cards;
    
    public MainFrame(){
       super("Corporative Elevator System"); 
       Container c = getContentPane();
       cards = new JPanel(new CardLayout());
       c.add(cards);
    }
    
    public void start(){
        
        //menu
        
        menuBar = new JMenuBar();
        menuBar.setLayout(new BorderLayout());
        userName = new JLabel("teste");
        bt_logout = new JButton("Logout");
        menuBar.add(userName,BorderLayout.LINE_START); 
        menuBar.add(bt_logout,BorderLayout.LINE_END);
        setJMenuBar(menuBar);
        
        setSize(300, 300);
        setLocation(500, 300);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void setUser(String name){
        userName.setText("Hi "+name);
    }
    
    public JPanel getCards(){
        return cards;
    }
    
}
