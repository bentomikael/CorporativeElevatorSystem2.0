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
    private JButton bt_floor;
    private JButton bt_adm;
    private enum bt{FLOOR,ADMINISTRATIVE};
    
    public Home() {
        signal = Signal.EMPITY;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
       
        gbc.gridx = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        //botao de andar
        bt_floor = new JButton("Go to a Floor");
        bt_floor.setName(bt.FLOOR.name());
        bt_floor.addActionListener(new Action());
        add(bt_floor);
        
        //botao de opcoes administrativas
        bt_adm = new JButton("Administrative Options");
        bt_adm.setName(bt.ADMINISTRATIVE.name());
        bt_adm.addActionListener(new Action());
        add(bt_adm);
        
    }

    
    private class Action implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            signal = signal.NEXT;
            if(e.getSource().equals(bt.FLOOR) )
                System.out.println("floor");
            else if(e.getSource().equals(bt.ADMINISTRATIVE) )
                System.out.println("adm");  
        }  
    }
    
    @Override
    public Signal getSignal() {
        return signal;
    }

     
    
}
