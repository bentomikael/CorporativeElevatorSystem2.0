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

public class Administrative extends JPanel implements IPanel {

    private Signal signal,
                   option;
    private JButton bt_new,
                    bt_del,
                    bt_change,
                    bt_report,
                    bt_list;

    public Administrative() {
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

        //botao cadastrar funcionario
        add(bt_new = new JButton("1 - Register New Employee"), gbc);
        bt_new.setName(Signal.NEW_EMPLOYEE.name());
        inputM = bt_new.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputM.put(KeyStroke.getKeyStroke('1'), Signal.ACTION);
        bt_new.addActionListener(new Action());
        bt_new.setActionMap(actionM);

        //botao remover funcionario
        add(bt_del = new JButton("2 - Remove one Employee"), gbc);
        bt_del.setName(Signal.DEL_EMPLOYEE.name());
        inputM = bt_del.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputM.put(KeyStroke.getKeyStroke('2'), Signal.ACTION);
        bt_del.addActionListener(new Action());
        bt_del.setActionMap(actionM);

        //botao alterar funcionario
        add(bt_change = new JButton("3 - Change Employee Occupation"), gbc);
        bt_change.setName(Signal.CHANGE_EMPLOYEE.name());
        inputM = bt_change.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputM.put(KeyStroke.getKeyStroke('3'), Signal.ACTION);
        bt_change.addActionListener(new Action());
        bt_change.setActionMap(actionM);

        //botao relatorios
        add(bt_report = new JButton("4 - Reports"), gbc);
        bt_report.setName(Signal.REPORTS.name());
        inputM = bt_report.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputM.put(KeyStroke.getKeyStroke('4'), Signal.ACTION);
        bt_report.addActionListener(new Action());
        bt_report.setActionMap(actionM);

        //botao listas
        add(bt_list = new JButton("5 - Lists"), gbc);
        bt_list.setName(Signal.LIST.name());
        inputM = bt_list.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputM.put(KeyStroke.getKeyStroke('5'), Signal.ACTION);
        bt_list.addActionListener(new Action());
        bt_list.setActionMap(actionM);
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
