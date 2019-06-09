package br.ufsc.ine5605.Screen;

import java.awt.CardLayout;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ScreenControl {
    private MainFrame mainF;
    private CardLayout cards_mainF;
    private Login login;
    private Home home;
    private Floor floor;
    private static enum Screen{LOGIN,HOME,FLOOR} // substituir por hashmap, talvez?
    private int aux; // variavel auxiliar
    private boolean logoutRequest;
    
    
    public ScreenControl() {
        mainF = new MainFrame();
        login = new Login();
        home = new Home();
        floor = new Floor();
        addCardsToFrame();
    }
    
    public boolean getLogoutRequest(){
            
        return logoutRequest;
    }
    
    public int login(ArrayList allCodes) {
        logoutRequest = false;
        mainF.logged(false, null);
        
        boolean valid = false;
        showScreen(Screen.LOGIN);
        
        do{   
            waitInstruction(login);
            aux = codeFilter(login.getCode());
            if(aux != -1)
                if(!allCodes.contains(aux))
                    JOptionPane.showMessageDialog(mainF,
                            "USER NOT FOUND",
                            "NOT FOUND", 
                            JOptionPane.ERROR_MESSAGE);
                else
                    valid = true;
            
        }while(!valid);
        
        return aux;
    }
    
    public int home(String name,int actualUserLevel){
        
        //ativa o menu bar
        mainF.logged(true, name);
        
        //oculta botão administrativo se não tiver autorizacão
        if(actualUserLevel < 3)
            home.getComponent(1).setVisible(false);
        
        showScreen(Screen.HOME);
        waitInstruction(home);
        if(home.getOption() == Signal.FLOOR)
            aux = 1;
        else
            aux = 2;
        
        return aux;
    }
    
    public int floor(int actualUserLevel){
        showScreen(Screen.FLOOR);
        return aux;
    }
    
    /**
     * Espera que seja apertado um botão da tela
     * @param tela atual
     **/
    private void waitInstruction(IPanel pane) {
        do{
            System.out.print("");      //precisa pra funcionar
            if(mainF.getSignal() == Signal.LOGOUT){
                logoutRequest = true;
                aux = -1;
                break;
            }
        }while(pane.getSignal()== Signal.EMPITY ); 
        pane.resetSignal();
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
                JOptionPane.showMessageDialog(mainF,
                        "CODE CONTAINS 3 NUMBERS",
                        "INVALID CODE", 
                        JOptionPane.ERROR_MESSAGE);
                newInt = -1;
            }
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(mainF,
                        "CODE CONTAINS ONLY NUMBERS",
                        "INVALID CODE", 
                        JOptionPane.ERROR_MESSAGE);       
        }
        
        return newInt;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Manipulacao de cards(telas)">
    private void addCardsToFrame(){
        cards_mainF = (CardLayout)(mainF.getCards().getLayout());
        mainF.getCards().add(login,Screen.LOGIN.toString());
        mainF.getCards().add(home,Screen.HOME.toString());
        mainF.getCards().add(floor,Screen.FLOOR.toString());
    }
    
    
    private void showScreen(Screen screen){
        cards_mainF.show(mainF.getCards(), screen.name());
    }
//</editor-fold>
    
}
