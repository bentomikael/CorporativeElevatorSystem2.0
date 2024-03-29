package br.ufsc.ine5605.Screen;

import br.ufsc.ine5605.entity.Employee;
import br.ufsc.ine5605.entity.People;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class NewEmployee extends JPanel implements IPanel{
  private JTextField tf_name, 
                     tf_code;
  private ButtonGroup gender,
                      occupation;
  private JComboBox age;
  private JButton bt_register;
  private Signal signal;

    public NewEmployee() {
        signal = Signal.EMPITY;
        InputMap inputM = new InputMap();
        ActionMap actionM = new ActionMap();
        actionM.put(Signal.ACTION,new Action());
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5 , 5, 5);        
        
        // campo nome
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Name:"),gbc);
        gbc.gridx = 1;
        tf_name = new JTextField(8);
        add(tf_name,gbc);
       
        //campo idade
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Age:"),gbc);
        gbc.gridx = 1;
        age = new JComboBox(createAgeList());
        add(age,gbc);
        
        //box genero 
        JRadioButton rb_male = new JRadioButton(People.Gender.MALE.toString(),true);
        JRadioButton rb_female = new JRadioButton(People.Gender.FEMALE.toString());
        gender = new ButtonGroup();
        Box boxG = Box.createVerticalBox();
      
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Gender :"),gbc);
        
        rb_male.setActionCommand(People.Gender.MALE.toString());
        rb_female.setActionCommand(People.Gender.FEMALE.toString());
        gender.add(rb_male);
        gender.add(rb_female);
        boxG.add(rb_male);
        boxG.add(rb_female);
        
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(boxG,gbc);
        
        //campo codigo
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(new JLabel("code:"),gbc);
        gbc.gridx = 1;
        tf_code = new JTextField(8);
        add(tf_code,gbc);
        
        // campo cargo
        JRadioButton rb_simple = new JRadioButton("Simple Employee", true);
        JRadioButton rb_manager = new JRadioButton("Manager");
        JRadioButton rb_administrative = new JRadioButton("Admnistrative");
        JRadioButton rb_executive = new JRadioButton("Executive");
        JRadioButton rb_ceo = new JRadioButton("CEO");
        occupation = new ButtonGroup();
        Box boxO = Box.createVerticalBox();
      
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(new JLabel("Occupation:"),gbc);        
        
        rb_simple.setActionCommand(Employee.Occupation.SIMPLE_EMPLOYEE.toString());
        rb_manager.setActionCommand(Employee.Occupation.MANAGER.toString());
        rb_administrative.setActionCommand(Employee.Occupation.ADMINISTRATION.toString());
        rb_executive.setActionCommand(Employee.Occupation.EXECUTIVE.toString());
        rb_ceo.setActionCommand(Employee.Occupation.CEO.toString());
        
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
        
        gbc.gridx = 1;
        add(boxO,gbc);
                
        
        //botao para registrar
        
        gbc.gridy = 7;                          //linha 7
        gbc.gridx = 0;                          //coluna 0
        gbc.ipadx = 90;                         //largura 90
        gbc.ipady = 15;                         //altura 15
        gbc.gridwidth = 2;                      //ocupa 2 grids
        gbc.insets = new Insets(15, 0, 0, 0);   //da um espaço 15 em cima
        bt_register = new JButton("Register");
        add(bt_register,gbc);
        //vai reconhecer o teclado quando a janela estiver focada
        inputM = bt_register.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        //reconhece o ENTER como tecla que aciona este botão
        inputM.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0), Signal.ACTION);
        bt_register.addActionListener(new Action());
        bt_register.setActionMap(actionM);
        
    }

    //<editor-fold defaultstate="collapsed" desc="Gets">
    public String getTfName() {
        return tf_name.getText();
    }

    public String getTfCode() {
        return tf_code.getText();
    }

    public String getGender() {
        return gender.getSelection().getActionCommand();
    }

    public String getOccupation() {
        return occupation.getSelection().getActionCommand();
    }

    public String getAge() {
        return age.getSelectedItem().toString();
    }
//</editor-fold>

    
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
            boolean valid = true;

            if(!getTfName().matches(
                    "[A-Z a-z cc]{"+getTfName().length()+"}")){
                ScreenView.mInvalidName();
                valid = false;
            }
            if(!getTfCode().matches("[0-9]+")){
                ScreenView.mInvalidCode();
                valid = false;
            }
            
            if(valid)
            signal = Signal.NEXT;
        }

    }
    
    private String[] createAgeList(){
        String[] list = new String[83];
        for(int i = 0;i < list.length;i++)
            list[i] = Integer.toString(i+18);
        return list;
    } 
    
}
