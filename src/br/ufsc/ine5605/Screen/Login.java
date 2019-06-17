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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class Login extends JPanel implements IPanel {

    private JButton bt_confirm;
    private JTextField tf_code;
    private Signal signal;

    public Login() {
        signal = Signal.EMPITY;
        InputMap inputM = new InputMap();
        ActionMap actionM = new ActionMap();
        actionM.put(Signal.ACTION, new Action()); 
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridx = 0;
        gbc.insets = new Insets(5, 5, 5, 5);

        //label
        add(new JLabel("Insert Your Code Access:"), gbc);

        //text field
        add(tf_code = new JTextField(10), gbc);
        tf_code.setFocusable(true);
        tf_code.setText("999");              //teste, depois apagar

        
        //confirma
        bt_confirm = new JButton("Sign in");
        add(bt_confirm, gbc);
        inputM = bt_confirm.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputM.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0), Signal.ACTION);
        bt_confirm.addActionListener(new Action());
        bt_confirm.setActionMap(actionM);

    }

    public String getCode() {
        return tf_code.getText();
    }

    @Override
    public void resetSignal() {
        signal = Signal.EMPITY;
    }

    @Override
    public Signal getSignal() {
        return signal;
    }


   private class Action extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean valid = true;
           
            if(!getCode().matches("[0-9]+")){
                ScreenControl.mInvalidCode();
                valid = false;
            }
               
            if(valid)
            signal = Signal.NEXT;
            
        }
    }
}
