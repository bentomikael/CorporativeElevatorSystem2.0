package br.ufsc.ine5605.corporative_elavator_system2;

import br.ufsc.ine5605.controller.MainControl;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Mikael Bento e Vinicius Hilbert
 */
public class CorporativeElavatorSystem2 {

    
    public static void main(String[] args) throws UnsupportedLookAndFeelException {
       
        //<editor-fold defaultstate="collapsed" desc="Alterar aparencia das janelas">
        try {
            UIManager.setLookAndFeel(       //altera a aparencia das janelas
                    "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CorporativeElavatorSystem2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(CorporativeElavatorSystem2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CorporativeElavatorSystem2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(CorporativeElavatorSystem2.class.getName()).log(Level.SEVERE, null, ex);
        }
//</editor-fold>
        
        new MainControl().start();
        
    
    }
    
}
