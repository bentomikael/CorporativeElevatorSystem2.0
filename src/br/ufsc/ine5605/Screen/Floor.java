package br.ufsc.ine5605.Screen;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class Floor extends JPanel implements IPanel {

    private Signal signal;
    private Signal option;
    private JButton bt_ground;
    private JButton bt_first;
    private JButton bt_second;
    private JButton bt_third;
    private JButton bt_fourth;
    private JButton bt_fifth;

    public Floor() {
        signal = Signal.EMPITY;
        InputMap inputM = new InputMap();
        ActionMap actionM = new ActionMap();
        actionM.put(Signal.ACTION, new Action()); 
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.ipady = 10;

        //ground
        add(bt_ground = new JButton("0 - GROUND FLOOR"), gbc);
        bt_ground.setName(Signal.GROUND_FLOOR.name());
        inputM = bt_ground.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputM.put(KeyStroke.getKeyStroke('0'), Signal.ACTION);
        bt_ground.addActionListener(new Action());
        bt_ground.setActionMap(actionM);

        //first
        add(bt_first = new JButton("1 - SIMPLE EMPLOYEE FLOOR"), gbc);
        bt_first.setName(Signal.FIRST_FLOOR.name());
        inputM = bt_first.getInputMap(WHEN_IN_FOCUSED_WINDOW);
        inputM.put(KeyStroke.getKeyStroke('1'), Signal.ACTION);
        bt_first.addActionListener(new Action());
        bt_first.setActionMap(actionM);

        //second
        add(bt_second = new JButton("2 - MANAGER FLOOR"), gbc);
        bt_second.setName(Signal.SECOND_FLOOR.name());
        inputM = bt_second.getInputMap(WHEN_IN_FOCUSED_WINDOW);
        inputM.put(KeyStroke.getKeyStroke('2'), Signal.ACTION);
        bt_second.addActionListener(new Action());
        bt_second.setActionMap(actionM);
        
        //third
        add(bt_third = new JButton("3 - ADMINISTRATIVE FLOOR"), gbc);
        bt_third.setName(Signal.THIRD_FLOOR.name());
        inputM = bt_third.getInputMap(WHEN_IN_FOCUSED_WINDOW);
        inputM.put(KeyStroke.getKeyStroke('3'), Signal.ACTION);
        bt_third.addActionListener(new Action());
        bt_third.setActionMap(actionM);
        
        //fourth
        add(bt_fourth = new JButton("4 - EXECUTIVE FLOOR"), gbc);
        bt_fourth.setName(Signal.FOURTH_FLOOR.name());
        inputM = bt_fourth.getInputMap(WHEN_IN_FOCUSED_WINDOW);
        inputM.put(KeyStroke.getKeyStroke('4'), Signal.ACTION);
        bt_fourth.addActionListener(new Action());
        bt_fourth.setActionMap(actionM);
        
        //fifth
        add(bt_fifth = new JButton("5 - CEO FLOOR"), gbc);
        bt_fifth.setName(Signal.FIFTH_FLOOR.name());
        inputM = bt_fifth.getInputMap(WHEN_IN_FOCUSED_WINDOW);
        inputM.put(KeyStroke.getKeyStroke('5'), Signal.ACTION);
        bt_fifth.addActionListener(new Action());
        bt_fifth.setActionMap(actionM);
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
