package br.ufsc.ine5605.Screen;

import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

public class ScreenControl {
    private MainFrame mainF;
    private CardLayout cards_mainF;
    private HashMap<IPanel,String> cardName;
    //Panel
    private Login pLogin;
    private Home pHome;
    private Floor pFloor;
    private Administrative pAdmnistrative;
    //variaveis auxiliares
    private int aux; // variavel auxiliar
    private boolean logoutRequest;
    
    public ScreenControl() {
        mainF = new MainFrame();
        pLogin = new Login();
        pHome = new Home();
        pFloor = new Floor();
        pAdmnistrative = new Administrative();
        cardName = new HashMap();
        
        addCardsToFrame();
    }
    
    public boolean getLogoutRequest(){
            
        return logoutRequest;
    }
    
    public int login(ArrayList allCodes) {
        mainF.resetSignal();
        logoutRequest = false;
        //desativa menu bar
        mainF.logged(false, null);
        
        boolean valid = false;
        showScreen(pLogin);
        
        do{   
            waitButton(pLogin);
            aux = codeFilter(pLogin.getCode());
            
            if(aux != -1)
                if(!allCodes.contains(aux))
                    mNotFound();
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
            pHome.getComponent(1).setVisible(false);
        
        showScreen(pHome);
        waitButton(pHome);
        
        if(!logoutRequest)
            if(pHome.getOption() == Signal.FLOOR)
                aux = 1;
            else if(pHome.getOption() == Signal.ADMNISTRATIVE )
                aux = 2;
        
        return aux;
    }
    
    public int floor(int actualUserLevel){
        
        if(actualUserLevel < 2 )
            pFloor.getComponent(2).setVisible(false); 
        if(actualUserLevel < 3 )
            pFloor.getComponent(3).setVisible(false); 
        if(actualUserLevel < 4 )
            pFloor.getComponent(4).setVisible(false); 
        if(actualUserLevel < 5 )
            pFloor.getComponent(5).setVisible(false); 
        
        showScreen(pFloor);
        waitButton(pFloor);
        
        if(!logoutRequest)
            switch (pFloor.getOption()) {
            case GROUND_FLOOR:
                aux = 0;
                break;
            case FIRST_FLOOR:
                aux = 1;
                break;
            case SECOND_FLOOR:
                aux = 2;
                break;
            case THIRD_FLOOR:
                aux = 3;
                break;
            case FOURTH_FLOOR:
                aux = 4;
                break;
            case FIFTH_FLOOR:
                aux = 5;
                break;
        }
        
        return aux;
    }
    
    public int administrativeOptions(){
        showScreen(pAdmnistrative);
        waitButton(pAdmnistrative);
        
        if(!logoutRequest)
            switch(pAdmnistrative.getOption()){
                case NEW_EMPLOYEE:
                    aux = 1;
                    break;
                case DEL_EMPLOYEE:
                    aux = 2;
                    break;
                case CHANGE_EMPLOYEE:
                    aux = 3;
                    break;
                case REPORTS:
                    aux = 4;
                    break;
                case LIST:
                    aux = 5;
                    break;
            }
        return aux;
    }
    
    /**
     * Espera que seja apertado um botão da tela
     * @param pane tela atual
     **/
    private void waitButton(IPanel pane) {
        do{
            System.out.print("");      //precisa pra funcionar (??)
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
                mInvalidCode();
                newInt = -1;
            }
        }catch(NumberFormatException e){
            mInvalidNumber();
        }
        
        return newInt;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Manipulacao de cards(telas)">
    private void addCardsToFrame(){
        cardName.put(pLogin, "LOGIN");
        cardName.put(pHome, "HOME");
        cardName.put(pFloor, "FLOOR");
        cardName.put(pAdmnistrative, "ADMNISTRATIVE");
        
        cards_mainF = (CardLayout)(mainF.getCards().getLayout());
        
        mainF.getCards().add(pLogin,cardName.get(pLogin));
        mainF.getCards().add(pHome,cardName.get(pHome));
        mainF.getCards().add(pFloor,cardName.get(pFloor));
        mainF.getCards().add(pAdmnistrative,cardName.get(pAdmnistrative));
    }
    
    private void showScreen(IPanel panel){
        cards_mainF.show(mainF.getCards(), cardName.get(panel));
    }
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Mensagens">
    public void mInvalidNumber(){
        JOptionPane.showMessageDialog(mainF,
                        "INVALID NUMBER! TRY AGAIN",
                        "INVALID NUMBER", 
                        JOptionPane.ERROR_MESSAGE);
    }
    public void mInvalidCode(){
        JOptionPane.showMessageDialog(mainF,
                        "CODE CONTAINS 3 NUMBERS",
                        "INVALID CODE", 
                        JOptionPane.ERROR_MESSAGE);
    }
    public void mInvalidName(){
        JOptionPane.showMessageDialog(mainF,
                        "INVALID NAME! TRY AGAIN",
                        "INVALID NAME", 
                        JOptionPane.ERROR_MESSAGE);
    }
    public void mDontHavePermision(){
        JOptionPane.showMessageDialog(mainF,
                        "YOU DONT HAVE PERMISSION TO EXECUTE THIS OPERATION",
                        "ACCESS DANIED", 
                        JOptionPane.ERROR_MESSAGE);
    }
    public void mAlreadyRegistered(){
        JOptionPane.showMessageDialog(mainF,
                        "USER ALREADY REGISTERED",
                        "ALREADY REGISTERED", 
                        JOptionPane.ERROR_MESSAGE);
    }
    public void mNotFound(){
        JOptionPane.showMessageDialog(mainF,
                        "USER NOT FOUND!",
                        "NOT FOUND", 
                        JOptionPane.ERROR_MESSAGE);
    }
    public void mChangeSelfError(){
        JOptionPane.showMessageDialog(mainF,
                        "YOU CAN'T CHANGE YOURS OWN ACCESS LEVEL",
                        "ACCESS DANIED", 
                        JOptionPane.ERROR_MESSAGE);
    }
    public void mWentInFloor(int floor){
        JOptionPane.showMessageDialog(mainF,
                        "YOU WENT TO FLOOR "+ floor,
                        "SUCCESSFULL", 
                        JOptionPane.INFORMATION_MESSAGE);
    }
    public void mLeavingFloor(){
        JOptionPane.showMessageDialog(mainF,
                        "BYE BYE, SE YOU LATER",
                        "SUCCESSFULL", 
                        JOptionPane.INFORMATION_MESSAGE);
    }
    public void mAlreadyInFloor(){
        JOptionPane.showMessageDialog(mainF,
                        "YOU ALREADY IN THIS FLOOR!",
                        "ERRO", 
                        JOptionPane.ERROR_MESSAGE);
        
    }
            
            
//</editor-fold>
    
}
