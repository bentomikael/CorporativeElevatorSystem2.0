package br.ufsc.ine5605.Screen;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class MainFrame extends JFrame implements IPanel {

    private JMenuBar menuBar;
    private JButton bt_logout;
    private JLabel lb_userName;
    private JPanel cards;
    private Signal signal;

    public MainFrame() {

        super("Corporative Elevator System");
        Container c = getContentPane();
        InputMap inputM = new InputMap();
        ActionMap actionM = new ActionMap();
        actionM.put(Signal.ACTION, new Action());  
       
        menuBar = new JMenuBar();
        cards = new JPanel(new CardLayout());
        lb_userName = new JLabel("");
        menuBar.setLayout(new BorderLayout());
        bt_logout = new JButton("ESC - Logout");
        inputM = bt_logout.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputM.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0), Signal.ACTION);
        
        bt_logout.setActionMap(actionM);
        bt_logout.addActionListener(new Action());
        c.add(cards);
        menuBar.add(lb_userName, BorderLayout.LINE_START);
        menuBar.add(bt_logout, BorderLayout.LINE_END);

        setJMenuBar(menuBar);
        setSize(650, 450);
        setLocation(450, 200);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public JPanel getCards() {
        return cards;
    }

    /**
     * Quando logado mostrar menu bar personalizado
     *
     * @param show define se sera visivel
     * @param name nome do usuario atual
     */
    public void menuBar(boolean show, String name) {
        if (show) {
            bt_logout.setVisible(true);
            lb_userName.setText("Hello " + name.toUpperCase());
        } else {
            bt_logout.setVisible(false);
            lb_userName.setText(" ");
        }
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
            signal = Signal.LOGOUT;
        }
    }
    
}
