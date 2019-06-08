package br.ufsc.ine5605.Screen;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MainFrame extends JFrame{
    JMenuBar menuBar;
    JMenuItem t;
    JMenu user;
    Login l = new Login();
    
    public MainFrame(){
       super("Corporative Elevator System"); 
    }
    
    public void start(){
        
        menuBar = new JMenuBar();
        user = new JMenu("Nome do Usuario logado aqui");
        
        menuBar.add(user);
        user.add(new JLabel("teste"));
        user.add(new JLabel("teste2"));
        user.add(new JMenuItem("teste3"));
        add(l);
        
        setJMenuBar(menuBar);
        setSize(300, 300);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void setUser(String name){
        user.setText(name);
    }
    
    public void setPanel(JPanel pane){
        setContentPane(pane);
    }
    
}
