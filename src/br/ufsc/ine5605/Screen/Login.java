package br.ufsc.ine5605.Screen;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Login extends JPanel implements IPanel {

    private JButton bt_confirm;
    private JTextField tf_code;
    private Signal signal;

    public Login() {
        signal = Signal.EMPITY;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(5, 5, 5, 5);

        //label
        add(new JLabel("Insert Your Code Access:"), gbc);

        //text field
        add(tf_code = new JTextField(10), gbc);
        tf_code.addKeyListener(new Key());
        tf_code.setFocusable(true);
        tf_code.setText("999");              //teste, depois apagar

        
        //confirma
        bt_confirm = new JButton("Sign in");
        add(bt_confirm, gbc);
        bt_confirm.addActionListener(new Action());

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

    private class Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            signal = Signal.NEXT;
            tf_code.setText("");
        }
    }

    private class Key implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                signal = Signal.NEXT;
                tf_code.setText("");
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
            keyTyped(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            keyTyped(e);
        }

    }
}
