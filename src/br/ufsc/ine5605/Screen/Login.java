package br.ufsc.ine5605.Screen;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Login extends JPanel implements IPane{
    
    private JButton bt_confirm;
    private JTextField code;
    private int signal;
    
    public Login() {
        
        signal = Signal.EMPITY.value;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx=0;
        gbc.insets= new Insets(5, 5, 5, 5);
        
        //label
        add(new JLabel("Insert Your Code Access:"),gbc);
        
        //text field
        add(code = new JTextField(10),gbc);
        
        //confirma
        bt_confirm = new JButton("Sign in");
        add(bt_confirm,gbc);
        bt_confirm.addActionListener(new Action());
    }
    private class Action implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            signal = Signal.NEXT.value;     
        }
    }
   
    public String getCode(){
        return code.getText();
    }    
    
    @Override
    public int getSignal(){
        return signal;
    }
}
