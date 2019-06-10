package br.ufsc.ine5605.Screen;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;


public class Home extends JPanel implements IPanel{
    private Signal signal;
    private Signal option;
    private JButton bt_floor;
    private JButton bt_adm;
   
    
    public Home() {
        signal = Signal.EMPITY;
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
       
        gbc.gridx = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.ipadx = 15;
        gbc.ipady = 15;
        
        //botao de andar
        add(bt_floor = new JButton("Go to a Floor"),gbc);
        bt_floor.setName(Signal.FLOOR.name());
        bt_floor.addActionListener(new Action());
        
        
        //botao de opcoes administrativas
        add(bt_adm = new JButton("Administrative Options"),gbc);
        bt_adm.setName(Signal.ADMNISTRATIVE.name());
        bt_adm.addActionListener(new Action());
        
        
    }

    public Signal getOption(){
        return option;
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
                option = Signal.valueOf(
                        ( (JButton)e.getSource() ).getName() );     
            signal = Signal.NEXT;
        }  
    }
    
}
