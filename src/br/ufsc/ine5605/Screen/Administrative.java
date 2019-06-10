package br.ufsc.ine5605.Screen;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

class Administrative extends JPanel implements IPanel{
    private Signal signal;
    private Signal option;
    private JButton bt_new;
    private JButton bt_del;
    private JButton bt_change;
    private JButton bt_report;
    private JButton bt_list;

    public Administrative() {
        signal = Signal.EMPITY;
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridx = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.ipadx = 15;
        gbc.ipady = 15;
        
        //botao cadastrar funcionario
        add(bt_new = new JButton("Register New Employee"),gbc);
        bt_new.setName(Signal.NEW_EMPLOYEE.name());
        bt_new.addActionListener(new Action());
        
        //botao remover funcionario
        add(bt_del = new JButton("Remove one Employee"),gbc);
        bt_del.setName(Signal.DEL_EMPLOYEE.name());
        bt_del.addActionListener(new Action());
        
        //botao alterar funcionario
        add(bt_change = new JButton("Change Employee Occupation"),gbc);
        bt_change.setName(Signal.CHANGE_EMPLOYEE.name());
        bt_change.addActionListener(new Action());
        
        //botao relatorios
        add(bt_report = new JButton("Reports"),gbc);
        bt_report.setName(Signal.REPORTS.name());
        bt_report.addActionListener(new Action());
        
        //botao listas
        add(bt_list = new JButton("Lists"),gbc);
        bt_list.setName(Signal.LIST.name());
        bt_list.addActionListener(new Action());
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
