package br.ufsc.ine5605.Screen;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class Home extends JPanel implements IPanel {

    private Signal signal;
    private Signal option;
    private JButton bt_floor;
    private JButton bt_adm;

    public Home() {
        signal = Signal.EMPITY;
        InputMap inputM = new InputMap();
        ActionMap actionM = new ActionMap();
        actionM.put(Signal.ACTION, new Action());  
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridx = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.ipadx = 15;
        gbc.ipady = 15;

        //botao de andar
        add(bt_floor = new JButton("1 - Go to a Floor"), gbc);
        bt_floor.setName(Signal.FLOOR.name());
        inputM = bt_floor.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputM.put(KeyStroke.getKeyStroke('1'), Signal.ACTION);
        bt_floor.addActionListener(new Action());
        bt_floor.setActionMap(actionM);

        //botao de opcoes administrativas
        add(bt_adm = new JButton("2 - Administrative Options"), gbc);
        bt_adm.setName(Signal.ADMNISTRATIVE.name());
        bt_adm.addActionListener(new Action());
        inputM = bt_adm.getInputMap(WHEN_IN_FOCUSED_WINDOW);
        inputM.put(KeyStroke.getKeyStroke('2'), Signal.ACTION);
        bt_adm.setActionMap(actionM);
        
    }

    public Signal getOption() {
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

//    private class Action implements ActionListener {
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            option = Signal.valueOf(
//                    ((JButton) e.getSource()).getName());
//            signal = Signal.NEXT;
//        }
//    }

    private class Action extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            option = Signal.valueOf(
                    ((JButton) e.getSource()).getName());
            signal = Signal.NEXT;
        }

    }

}
