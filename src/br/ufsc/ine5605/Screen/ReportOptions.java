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

public class ReportOptions extends JPanel implements IPanel{
    private Signal signal,
                   option;
    private JButton bt_rFloor,
                    bt_day,
                    bt_reg,
                    bt_rem,
                    bt_all;

    public ReportOptions() {
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

        //relatorio por andar
        add(bt_rFloor = new JButton("1 - Report of Floor"), gbc);
        bt_rFloor.setName(Signal.REPORT_FLOOR.name());
        inputM = bt_rFloor.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputM.put(KeyStroke.getKeyStroke('1'), Signal.ACTION);
        bt_rFloor.addActionListener(new Action());
        bt_rFloor.setActionMap(actionM);

        //botao remover funcionario
        add(bt_day = new JButton("2 - Report of Day"), gbc);
        bt_day.setName(Signal.REPORT_DAY.name());
        inputM = bt_day.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputM.put(KeyStroke.getKeyStroke('2'), Signal.ACTION);
        bt_day.addActionListener(new Action());
        bt_day.setActionMap(actionM);

        //botao alterar funcionario
        add(bt_reg = new JButton("3 - Report of registered employees"), gbc);
        bt_reg.setName(Signal.REPORT_REGISTERED.name());
        inputM = bt_reg.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputM.put(KeyStroke.getKeyStroke('3'), Signal.ACTION);
        bt_reg.addActionListener(new Action());
        bt_reg.setActionMap(actionM);

        //botao relatorios
        add(bt_rem = new JButton("4 - Report of removed employees"), gbc);
        bt_rem.setName(Signal.REPORT_REMOVED.name());
        inputM = bt_rem.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputM.put(KeyStroke.getKeyStroke('4'), Signal.ACTION);
        bt_rem.addActionListener(new Action());
        bt_rem.setActionMap(actionM);

        //botao listas
        add(bt_all = new JButton("5 - Report General"), gbc);
        bt_all.setName(Signal.REPORT_ALL.name());
        inputM = bt_all.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputM.put(KeyStroke.getKeyStroke('5'), Signal.ACTION);
        bt_all.addActionListener(new Action());
        bt_all.setActionMap(actionM);
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
