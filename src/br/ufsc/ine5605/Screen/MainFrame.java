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
    private JLabel lb_userName;
    private JPanel cards;
    private Signal signal;
    
    public MainFrame(){
        
        super("Corporative Elevator System"); 
        Container c = getContentPane();
        menuBar = new JMenuBar();
        cards = new JPanel(new CardLayout());
        lb_userName = new JLabel("");
        bt_logout = new JButton("Logout");
        menuBar.setLayout(new BorderLayout());
        
        bt_logout.addActionListener(new Action());

        c.add(cards);
        menuBar.add(lb_userName,BorderLayout.LINE_START); 
        menuBar.add(bt_logout,BorderLayout.LINE_END);
        
        setJMenuBar(menuBar);
        setSize(400, 400);
        setLocation(500, 200);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public JPanel getCards(){
        return cards;
    }
    
    /**
     * Quando logado mostrar menu bar personalizado
     * @param logged está logado?
     * @param name nome do usuario atual
     */
    public void logged(boolean logged ,String name){
        if(logged){
            bt_logout.setVisible(true);
            lb_userName.setText("Hello " + name.toUpperCase());
        }else{
            bt_logout.setVisible(false);
            lb_userName.setText(" ");
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
