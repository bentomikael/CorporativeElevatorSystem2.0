package br.ufsc.ine5605.Screen;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
}
