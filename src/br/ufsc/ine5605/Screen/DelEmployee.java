package br.ufsc.ine5605.Screen;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

class DelEmployee extends JPanel implements IPanel {

    private Signal signal;
    private JButton bt_remove;
    private JList list;
    private JSplitPane split;
    private JLabel lb_name;
    JPanel rpane = new JPanel();

    public DelEmployee() {
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
        gbc.insets = new Insets(55, 0, 25, 0);

        rpane.add(new JLabel("Remove this user :"), gbc);

        lb_name = new JLabel("");
        lb_name.setFont(new Font("Leelawadee", 1, 18));
        rpane.add(lb_name, gbc);

        gbc.ipadx = 50;
        gbc.ipady = 50;
        bt_remove = new JButton("Remove");
        bt_remove.addActionListener(new Action());

        rpane.add(bt_remove, gbc);

        //split 
        split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sp, rpane);
        split.setEnabled(false);  // impede alteração de tamanho
        split.setDividerLocation(190); // tamanho inicial
        add(split);
    }

    public void setList(Object[] names) {
        list.setListData(names);
        //list.repaint();
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
    private class Action extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
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
