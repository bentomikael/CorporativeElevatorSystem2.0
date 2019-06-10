package br.ufsc.ine5605.Screen;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Floor extends JPanel implements IPanel{
    private Signal signal;
    private Signal option;
    private JButton ground;
    private JButton first;
    private JButton second;
    private JButton third;
    private JButton fourth;
    private JButton fifth;

    public Floor() {
        signal = Signal.EMPITY;
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.ipady = 10;
        
        //ground
        add(ground = new JButton("GROUND FLOOR"),gbc);
        ground.setName(Signal.GROUND_FLOOR.name());
        ground.addActionListener(new Action());
        
        //first
        add(first = new JButton("SIMPLE EMPLOYEE FLOOR"),gbc);
        first.setName(Signal.FIRST_FLOOR.name());
        first.addActionListener(new Action());
        
        //second
        add(second = new JButton("MANAGER FLOOR"),gbc);
        second.setName(Signal.SECOND_FLOOR.name());
        second.addActionListener(new Action());
        //third
        add(third = new JButton("ADMINISTRATIVE FLOOR"),gbc);
        third.setName(Signal.THIRD_FLOOR.name());
        third.addActionListener(new Action());
        //fourth
        add(fourth = new JButton("EXECUTIVE FLOOR"),gbc);
        fourth.setName(Signal.FOURTH_FLOOR.name());
        fourth.addActionListener(new Action());
        //fifth
        add(fifth = new JButton("CEO FLOOR"),gbc);
        fifth.setName(Signal.FIFTH_FLOOR.name());
        fifth.addActionListener(new Action());
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
