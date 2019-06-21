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

public class ListOptions extends JPanel implements IPanel{
    private Signal signal,
                   option;
    private JButton bt_all,
                    bt_ocp,
                    bt_floor,
                    bt_work;

    public ListOptions() {
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

        //listar todos
        add(bt_all = new JButton("1 - List of All Employee"), gbc);
        bt_all.setName(Signal.LIST_ALL.name());
        inputM = bt_all.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputM.put(KeyStroke.getKeyStroke('1'), Signal.ACTION);
        bt_all.addActionListener(new Action());
        bt_all.setActionMap(actionM);

        //listar por cargo
        add(bt_ocp = new JButton("2 - Employees Per Occupation"), gbc);
        bt_ocp.setName(Signal.LIST_OCCUPATION.name());
        inputM = bt_ocp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputM.put(KeyStroke.getKeyStroke('2'), Signal.ACTION);
        bt_ocp.addActionListener(new Action());
        bt_ocp.setActionMap(actionM);

        //listar por andar
        add(bt_floor = new JButton("3 - Employees Per Floor"), gbc);
        bt_floor.setName(Signal.LIST_FLOOR.name());
        inputM = bt_floor.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputM.put(KeyStroke.getKeyStroke('3'), Signal.ACTION);
        bt_floor.addActionListener(new Action());
        bt_floor.setActionMap(actionM);

        //listar os que estao em algum andar
        add(bt_work = new JButton("4 - Employees In Work"), gbc);
        bt_work.setName(Signal.LIST_WORK.name());
        inputM = bt_work.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputM.put(KeyStroke.getKeyStroke('4'), Signal.ACTION);
        bt_work.addActionListener(new Action());
        bt_work.setActionMap(actionM);

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

    private class Action extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            option = Signal.valueOf(
                    ((JButton) e.getSource()).getName());
            signal = Signal.NEXT;
        }

    }
}
