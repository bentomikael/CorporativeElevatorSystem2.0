package br.ufsc.ine5605.Screen;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Acer
 */
class DelEmployee extends JPanel implements IPanel{
    private Signal signal;
    private JButton select;
    
    public DelEmployee(String[] names){
        signal = Signal.EMPITY;
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        
    }
    
    
    @Override
    public Signal getSignal() {
        return signal;
    }

    @Override
    public void resetSignal() {
        signal = Signal.EMPITY;
    }
    
}
