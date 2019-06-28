package br.ufsc.ine5605.Screen;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;

public class Table extends JPanel implements IPanel {

    private Signal signal;
    private JTable table;
    private JButton bt_ok;
    private JScrollPane sp;
    private JLabel lb_empty;

    public Table() {
        signal = Signal.EMPITY;
        InputMap inputM = new InputMap();
        ActionMap actionM = new ActionMap();
        actionM.put(Signal.ACTION, new Action());

        setLayout(new BorderLayout());

        lb_empty = new JLabel("                          EMPTY");
        lb_empty.setFont(new Font("Leelawadee", 1, 38));
        add(lb_empty);
        
        bt_ok = new JButton("OK");
        inputM = bt_ok.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputM.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), Signal.ACTION);
        bt_ok.addActionListener(new Action());
        bt_ok.setActionMap(actionM);
        add(bt_ok, BorderLayout.PAGE_END);
    }

    public void setListConfig(Object[] title, Object[][] values) {
        table = new JTable(values, title);
        sp = new JScrollPane(table);
        table.setEnabled(false);
        add(sp);
    }

    public Object[] setListType(Signal type) {
        Object[] index = null;
        if (type == Signal.LIST) {
            index = new Object[]{"Name", "Code", "Occupation", "Actual Floor"};
        } else if (type == Signal.REPORTS) {
            index = new Object[]{"Name", "Action", "Altered", "Date", "Hour", "Floor"};
        }

        return index;
    }
    
    public void emptyTable(boolean empty){
        if(empty){
            lb_empty.setVisible(true);
        }else{
            lb_empty.setVisible(false);
        }
    }

    @Override
    public Signal getSignal() {
        return signal;
    }

    @Override
    public void resetSignal() {
        signal = Signal.EMPITY;
        table.setVisible(false);
        remove(sp);
    }

    private class Action extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            signal = Signal.NEXT;
        }
    }

}
