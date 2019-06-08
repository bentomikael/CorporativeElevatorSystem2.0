package br.ufsc.ine5605.Screen;

import java.awt.CardLayout;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ScreenControl {
    private MainFrame mainF;
    private CardLayout cards_mainF;
    private Login login;
    private Home home;
    private static enum Screen{LOGIN,HOME} // substituir por hashmap, talvez?
    private int aux; // variavel auxiliar
    
    
    public ScreenControl() {
        mainF = new MainFrame();
        login = new Login();
        home = new Home();
        
        addCardsToFrame();
        mainF.start();
    }
    
    
    public int login(ArrayList allCodes) {
        showScreen(Screen.LOGIN);
        
        do{   
            waitInstruction(login);
            aux = codeFilter(login.getCode());
           
            if(!allCodes.contains(aux))
                JOptionPane.showMessageDialog(mainF,
                        "USER NOT FOUND",
                        "NOT FOUND", 
                        JOptionPane.ERROR_MESSAGE);
            
            login.resetSignal();
        
        }while(!allCodes.contains(aux));
        
        return aux;
    }
    
    public int home(int actualUserLevel){
        
        //oculta botão administrativo se não tiver autorizacão
        if(actualUserLevel < 3)
            home.getComponent(1).setVisible(false);
        
        cards_mainF.show(mainF.getCards(), "home");
        
        return aux;
    }
    
    /**
     * Espera que seja apertado um botão da tela
     * @param tela atual
     **/
    private void waitInstruction(IPanel pane) {
        do{
            System.out.print("");   // não executa corretamente quando vazio
        }while(pane.getSignal()== Signal.EMPITY);
    }
    
    /**
     * 
     * @param code recebe o codigo digitado no text field
     * @return codigo como inteiro. Se retornar -1 é invalido
     */
    private int codeFilter(String code){
        int newInt = -1;
        
        try{
            newInt = Integer.valueOf(code); //converte para int
            
            if(newInt <= 99 || newInt > 999){
                System.out.println("Conde Contains only 3 Numbers");
                newInt = -1;
            }
        }catch(NumberFormatException e){
            System.out.println("Code Contains only Numbers"); // alterar depois  
        }
        
        return newInt;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Manipulacao de cards(telas)">
    private void addCardsToFrame(){
        cards_mainF = (CardLayout)(mainF.getCards().getLayout());
        mainF.getCards().add(login,"login");
        mainF.getCards().add(home,"home");
    }
    
    
    private void showScreen(Screen screen){
        cards_mainF.show(mainF.getCards(), screen.name());
    }
//</editor-fold>
    
}
