package br.ufsc.ine5605.Screen;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class ChangeEmployee extends JPanel implements IPanel{
    private Signal signal;
    private JButton bt_change;
    private JList list;
    private JSplitPane split;
    private JLabel lb_name;
    private ButtonGroup occupation;
    JPanel rpane = new JPanel();

    public ChangeEmployee() {
        signal = Signal.EMPITY;
        setLayout(new BorderLayout());
        
        //lista
        list = new JList();
        list.addListSelectionListener(new Selection());
        JScrollPane sp = new JScrollPane(list);

        //info do selecionado
        rpane.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.insets = new Insets(25, 0, 25, 0);

        rpane.add(new JLabel("Change this user :"), gbc);

        lb_name = new JLabel("");
        lb_name.setFont(new Font("Leelawadee", 1, 18));
        rpane.add(lb_name, gbc);
        
        //radio do novo cargo
        JRadioButton rb_simple = new JRadioButton("Simple Employee", true);
        JRadioButton rb_manager = new JRadioButton("Manager");
        JRadioButton rb_administrative = new JRadioButton("Admnistrative");
        JRadioButton rb_executive = new JRadioButton("Executive");
        JRadioButton rb_ceo = new JRadioButton("CEO");
        occupation = new ButtonGroup();
        Box boxO = Box.createVerticalBox();
      
        gbc.insets = new Insets(10, 0, 5, 0);
        rpane.add(new JLabel("New Occupation:"),gbc);        
        
        rb_simple.setActionCommand("1");
        rb_manager.setActionCommand("2");
        rb_administrative.setActionCommand("3");
        rb_executive.setActionCommand("4");
        rb_ceo.setActionCommand("5");
        
        occupation.add(rb_simple);
        occupation.add(rb_manager);
        occupation.add(rb_administrative);
        occupation.add(rb_executive);
        occupation.add(rb_ceo);
        boxO.add(rb_simple);
        boxO.add(rb_manager);
        boxO.add(rb_administrative);
        boxO.add(rb_executive);
        boxO.add(rb_ceo);
        rpane.add(boxO,gbc);
        
        gbc.ipadx = 50;
        gbc.ipady = 40;
        bt_change = new JButton("Change");
        bt_change.addActionListener(new Action());

        rpane.add(bt_change, gbc);

        //split 
        split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sp, rpane);
        split.setEnabled(false);  // impede alteração de tamanho
        split.setDividerLocation(190); // tamanho inicial
        add(split);
    }

    public void setList(Object[] names) {
        list.setListData(names);
    }
    
    public String getNameSelected(){
        return list.getSelectedValue().toString();
    }

    @Override
    public Signal getSignal() {
        return signal;
    }

    @Override
    public void resetSignal() {
        signal = Signal.EMPITY;
        lb_name.setText("");
    }

    public String getNewOccupation() {
        return occupation.getSelection().getActionCommand();
    }
    
    
    private class Action extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(!lb_name.getText().equals(""))
                signal = Signal.NEXT; 
        }
    }
    
    private class Selection implements ListSelectionListener{

        @Override
        public void valueChanged(ListSelectionEvent e) {
            //o metodo é executado 2 vezes e na primeira acusa null pointer
            //se for null pointer nao faz nada, vida que segue
            try{         
                lb_name.setText(list.getSelectedValue().toString());
            }catch(NullPointerException ex){}
        }
        
    }
}
