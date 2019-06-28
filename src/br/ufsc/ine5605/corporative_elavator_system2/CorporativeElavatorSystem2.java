package br.ufsc.ine5605.corporative_elavator_system2;

import br.ufsc.ine5605.controller.MainControl;
import javax.swing.UIManager;

/**
 *
 * @author Mikael Bento
 */
public class CorporativeElavatorSystem2 {

    public static void main(String[] args) {

        //<editor-fold defaultstate="collapsed" desc="Alterar aparencia das janelas">
        try {
            UIManager.setLookAndFeel( //altera a aparencia das janelas
                    "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ex) {
            System.out.println("erro ao aplicar aparencia no frame");
        }
//</editor-fold>
        MainControl.getInstance();
        
        /*
     *          PRÉ REGISTRADOS
     * PIN
     * 999 CEO
     * 888 EXECUTIVE
     * 777 ADMINISTRATIVE
     * 666 MANAGER
     * 555 SIMPLE EMPLOYEE
         */
    }

}
