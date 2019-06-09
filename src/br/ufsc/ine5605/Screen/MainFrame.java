package br.ufsc.ine5605.Screen;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class MainFrame extends JFrame implements IPanel{
    private JMenuBar menuBar;
    private JButton bt_logout;
    private JLabel userName;
    private JPanel cards;
    private Signal signal;
    
    public MainFrame(){
        
        super("Corporative Elevator System"); 
        Container c = getContentPane();
        signal = Signal.EMPITY;
        
        menuBar = new JMenuBar();
        cards = new JPanel(new CardLayout());
        userName = new JLabel("");
        bt_logout = new JButton("Logout");
        menuBar.setLayout(new BorderLayout());
        
        bt_logout.addActionListener(new Action());

        c.add(cards);
        menuBar.add(userName,BorderLayout.LINE_START); 
        menuBar.add(bt_logout,BorderLayout.LINE_END);
        
        setJMenuBar(menuBar);
        setSize(300, 300);
        setLocation(500, 300);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public JPanel getCards(){
        return cards;
    }
    
    public void logged(boolean logged ,String name){
        if(logged){
            bt_logout.setVisible(true);
            userName.setText("Hello " + name.toUpperCase());
        }else{
            bt_logout.setVisible(false);
            userName.setText(" ");
            signal = Signal.EMPITY;
        }
    }
    
    @Override
    public Signal getSignal() {
        return signal;
    }

    @Override
    public void resetSignal() {
        signal = Signal.EMPITY;
    }
    
    private class Action implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e) {
            signal = Signal.LOGOUT; 
        }
    }
    
}
