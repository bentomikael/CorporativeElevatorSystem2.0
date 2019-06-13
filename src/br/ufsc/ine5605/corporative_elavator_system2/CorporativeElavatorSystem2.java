package br.ufsc.ine5605.corporative_elavator_system2;

import br.ufsc.ine5605.Screen.ScreenControl;
import br.ufsc.ine5605.controller.MainControl;
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
        } catch (Exception ex) {
            System.out.println("erro de aplicar aparencia no frame");
        } 
//</editor-fold>
        
       //new MainControl().start();
        //testes individuais
        ScreenControl s = new ScreenControl();
        s.testes();
    /*
     * PIN
     * 999 CEO
     * 888 EXECUTIVE
     * 777 ADMINISTRATIVE
     * 666 MANAGER
     * 555 SIMPLE EMPLOYEE
     */
    }
    
}
