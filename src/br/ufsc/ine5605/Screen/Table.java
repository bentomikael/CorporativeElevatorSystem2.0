package br.ufsc.ine5605.Screen;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class Table extends JPanel implements IPanel{
    private Signal signal;
    private JTable table;
    private JButton bt_ok;

    public Table() {
       setLayout(new BorderLayout());
       
    }
    
    public void setListConfig(Object[] title,Object[][] values){
       table = new JTable(values, title);
       JScrollPane sp = new JScrollPane(table);
       table.setEnabled(false);
       add(sp);
       
       bt_ok = new JButton("OK");
       bt_ok.addActionListener(new Action());
       add(bt_ok,BorderLayout.PAGE_END);
    }
        
    public Object[] setListType(Signal type){
        Object[] index = null;
        if(type == Signal.LIST)
            index = new Object[]{"Name","Code","Occupation","Actual Floor"};
        
       else if(type == Signal.REPORTS)
            index = new Object[]{"Name","Action","Altered","Date","Hour","Floor"};

        return index;
    }
    
    @Override
    public Signal getSignal() {
        return signal;
    }

    @Override
    public void resetSignal() {
        signal = Signal.EMPITY;
    }
    
    private class Action implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            signal = Signal.NEXT;
        }
    }
    
}
