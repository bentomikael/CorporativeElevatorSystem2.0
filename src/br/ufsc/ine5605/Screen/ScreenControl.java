package br.ufsc.ine5605.Screen;

import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class ScreenControl {

    private MainFrame mainF;
    private CardLayout cards_mainF;
    private HashMap<IPanel, String> cardName;
    //Panel
    private Login pLogin;
    private Home pHome;
    private Floor pFloor;
    private Administrative pAdmnistrative;
    private NewEmployee pNew;
    private DelEmployee pDel;
    private ChangeEmployee pChange;
    private ReportOptions pReportOp;
    private ListOptions pListOp;
    //variaveis auxiliares
    private int aux;
    private boolean logoutRequest;

    public ScreenControl() {
        mainF = new MainFrame();
        pLogin = new Login();
        pHome = new Home();
        pFloor = new Floor();
        pAdmnistrative = new Administrative();
        pNew = new NewEmployee();
        pDel = new DelEmployee();
        pChange = new ChangeEmployee();
        pReportOp = new ReportOptions();
        pListOp = new ListOptions();

        cardName = new HashMap();
        addCardsToFrame();
    }

    //depois apagar ---------------------------------------------------------
    public void testes() {
        showScreen(pReportOp);
    }

    public boolean getLogoutRequest() {

        return logoutRequest;
    }

    //<editor-fold defaultstate="collapsed" desc="Telas">
    public int login(ArrayList allCodes) {
        logoutRequest = false;
        //desativa menu bar
        mainF.logged(false, null);

        boolean valid = false;
        showScreen(pLogin);

        do {
            waitButton(pLogin);
            aux = Integer.parseInt(pLogin.getCode());

            if (aux != -1) {
                if (!allCodes.contains(aux)) {
                    mNotFound();
                } else {
                    valid = true;
                }
            }

        } while (!valid);

        return aux;
    }

    public int home(String name, int actualUserLevel) {
        //ativa o menu bar
        mainF.logged(true, name);

        //oculta botão administrativo se não tiver autorizacão
        if (actualUserLevel < 3) {
            pHome.getComponent(1).setVisible(false);
        }

        showScreen(pHome);
        waitButton(pHome);

        if (!logoutRequest) {
            if (pHome.getOption() == Signal.FLOOR) {
                aux = 1;
            } else if (pHome.getOption() == Signal.ADMNISTRATIVE) {
                aux = 2;
            }
        }

        return aux;
    }

    public int floor(int actualUserLevel) {        
        if (actualUserLevel < 2) {
            pFloor.getComponent(2).setVisible(false);
        }
        if (actualUserLevel < 3) {
            pFloor.getComponent(3).setVisible(false);
        }
        if (actualUserLevel < 4) {
            pFloor.getComponent(4).setVisible(false);
        }
        if (actualUserLevel < 5) {
            pFloor.getComponent(5).setVisible(false);
        }

        showScreen(pFloor);
        waitButton(pFloor);

        if (!logoutRequest) {
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
        }

        return aux;
    }

    public int administrativeOptions() {        
        showScreen(pAdmnistrative);
        waitButton(pAdmnistrative);

        if (!logoutRequest) {
            switch (pAdmnistrative.getOption()) {
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
        }else
            aux = -1;
        
        return aux;
    }

    public ArrayList newEmployee(int actualUserLevel,ArrayList allCodes ) {        
        boolean valid = true;
        ArrayList newE = new ArrayList();
        
        do{ 
            showScreen(pNew);
            waitButton(pNew);
            
            if(allCodes.contains(pNew.getTfCode())){
                mAlreadyRegistered();
                valid = false;
            }   
            
        }while(!valid && !logoutRequest );
        
        if(logoutRequest)
            newE = null;
        else{
            newE.add(pNew.getTfName());
            newE.add(pNew.getAge());
            newE.add(pNew.getGender());
            newE.add(pNew.getTfCode());
            newE.add(pNew.getOccupation());
        }
        
        return newE;
    }
    
    public String delEmployee(ArrayList listNames){
        String name = null;
        
        showScreen(pDel);
        pDel.setList(listNames.toArray());
        waitButton(pDel);
        
        if(!logoutRequest)
            name = pDel.getNameSelected();
        
        return name;
    }
    
    public ArrayList changeEmployee(ArrayList listNames){
        
        ArrayList toChange = new ArrayList();        
        
        showScreen(pChange);
        pChange.setList(listNames.toArray());
        waitButton(pChange);
        
        if(!logoutRequest){
            toChange.add(pChange.getNameSelected());
            toChange.add(pChange.getNewOccupation());
        }
        return toChange;
        
    }
    
    public int reportOptions(){
        showScreen(pReportOp);
        waitButton(pReportOp);
        
        if(!logoutRequest){
            switch(pReportOp.getOption()){
                case REPORT_FLOOR:
                    aux = 1;
                    break;
                case REPORT_DAY:
                    aux = 2;
                    break;
                case REPORT_REGISTERED:
                    aux = 3;
                    break;
                case REPORT_REMOVED:
                    aux = 4;
                    break;
                case REPORT_ALL:
                    aux = 5;
                    break;
            }
        }else
            aux = -1;
            
        return aux;
    }
        
    public int listOptions(){
        showScreen(pListOp);
        waitButton(pListOp);
        
        if(!logoutRequest){
            switch(pListOp.getOption()){
                case LIST_ALL:
                    aux = 1;
                    break;
                case LIST_OCCUPATION:
                    aux = 2;
                    break;
                case LIST_FLOOR:
                    aux = 3;
                    break;
                case LIST_WORK:
                    aux = 4;
                    break;
            }
        }else
            aux = -1;
            
        return aux;
    }
//</editor-fold>
    
    /**
     * Espera que seja apertado um botão da tela
     *
     * @param pane tela atual
     *
     */
    private void waitButton(IPanel pane) {
        do {
            System.out.print("");      //precisa pra funcionar (??)
            if (mainF.getSignal() == Signal.LOGOUT) {
                logoutRequest = true;
                aux = -1;
                break;
            }
   
        } while (pane.getSignal() == Signal.EMPITY);
        pane.resetSignal();
        mainF.resetSignal();
    }
    

    //<editor-fold defaultstate="collapsed" desc="Manipulacao de cards(telas)">
    private void addCardsToFrame() {
        cardName.put(pLogin, "LOGIN");
        cardName.put(pHome, "HOME");
        cardName.put(pFloor, "FLOOR");
        cardName.put(pAdmnistrative, "ADMNISTRATIVE");
        cardName.put(pNew, "NEW");
        cardName.put(pDel,"DEL");
        cardName.put(pChange,"CHANGE");
        cardName.put(pReportOp,"REPORT_OPTIONS");
        cardName.put(pListOp,"LIST_OPTIONS");

        cards_mainF = (CardLayout) (mainF.getCards().getLayout());
        
        mainF.getCards().add(pLogin, cardName.get(pLogin));
        mainF.getCards().add(pHome, cardName.get(pHome));
        mainF.getCards().add(pFloor, cardName.get(pFloor));
        mainF.getCards().add(pAdmnistrative, cardName.get(pAdmnistrative));
        mainF.getCards().add(pNew, cardName.get(pNew));
        mainF.getCards().add(pDel, cardName.get(pDel));
        mainF.getCards().add(pChange,cardName.get(pChange));
        mainF.getCards().add(pReportOp,cardName.get(pReportOp));
        mainF.getCards().add(pListOp,cardName.get(pListOp));
    }

    private void showScreen(IPanel panel) {
        cards_mainF.show(mainF.getCards(), cardName.get(panel));
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Dialogs">
    public static String dOccupation(){
        Object[] possibilities = {"Simple Employee",
            "Manager",
            "Admnistrative",
            "Executive",
            "CEO"};
        
        String x = null;
        do {
            x = (String)JOptionPane.showInputDialog(
                    null,
                    "Select the Occupation to get the Report",
                    "Occupation Selection",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    possibilities,
                    "Simple Employee"); 
        } while (x == null);
        
         
        return x;
    }
    
    public static String dFloor(){
        Object[] possibilities = {"1", "2", "3","4","5"};
        
        String x = null;
        do {
            x = (String)JOptionPane.showInputDialog(
                    null,
                    "Select the Floor to get the Report:",
                    "Floor Selection",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    possibilities,
                    "1");  
        } while (x == null);
        
        
        
        return x;
    }
    public static String dDay(){
        Object[] possibilities = new Object[30];
        for (int i = 1; i < possibilities.length; i++) {
            possibilities[i] = i;
        }
        
        String x = null;
        do {
            
                x = (String)JOptionPane.showInputDialog(
                    null,
                    "Select the Floor to get the Report:\n"
                    + "(1-30)",
                    "Floor Selection",
                    JOptionPane.PLAIN_MESSAGE);      
            if(!x.matches("[1-9]+"))
                x="-1";
            
        } while (Integer.parseInt(x) < 0 || Integer.parseInt(x) > 30);
        
        return x;
    }
    
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Mensagens">
    public static void mInvalidNumber() {
        JOptionPane.showMessageDialog(null,
                "INVALID NUMBER! TRY AGAIN",
                "INVALID NUMBER",
                JOptionPane.ERROR_MESSAGE);
    }

    public static void mInvalidCode() {
        JOptionPane.showMessageDialog(null,
                "CODE CONTAINS 3 NUMBERS",
                "INVALID CODE",
                JOptionPane.ERROR_MESSAGE);
    }

    public static void mInvalidName() {
        JOptionPane.showMessageDialog(null,
                "INVALID NAME! TRY AGAIN",
                "INVALID NAME",
                JOptionPane.ERROR_MESSAGE);
    }

    public static void mDontHavePermision() {
        JOptionPane.showMessageDialog(null,
                "YOU DONT HAVE PERMISSION TO EXECUTE THIS OPERATION",
                "ACCESS DANIED",
                JOptionPane.ERROR_MESSAGE);
    }

    public static void mAlreadyRegistered() {
        JOptionPane.showMessageDialog(null,
                "USER ALREADY REGISTERED WITH THIS CODE",
                "ALREADY REGISTERED",
                JOptionPane.ERROR_MESSAGE);
    }

    public static void mNotFound() {
        JOptionPane.showMessageDialog(null,
                "USER NOT FOUND!",
                "NOT FOUND",
                JOptionPane.ERROR_MESSAGE);
    }

    public static void mChangeSelfError() {
        JOptionPane.showMessageDialog(null,
                "YOU CAN'T CHANGE YOURS OWN ACCESS LEVEL",
                "ACCESS DANIED",
                JOptionPane.ERROR_MESSAGE);
    }

    public static void mWentInFloor(int floor) {
        JOptionPane.showMessageDialog(null,
                "YOU WENT TO FLOOR " + floor,
                "SUCCESSFULL",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void mLeavingFloor() {
        JOptionPane.showMessageDialog(null,
                "BYE BYE, SE YOU LATER",
                "SUCCESSFULL",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void mAlreadyInFloor() {
        JOptionPane.showMessageDialog(null,
                "YOU ALREADY IN THIS FLOOR!",
                "ERRO",
                JOptionPane.ERROR_MESSAGE);

    }
    
    public static void mSuccessFulRegistered(String name){
        JOptionPane.showMessageDialog(null,
                "EMPLOYEE "+name+" REGISTERED SUCCESSFUL",
                "SUCCESS",
                JOptionPane.INFORMATION_MESSAGE);
    }
   
    public static void mSuccessFulRemoved(String name){
        JOptionPane.showMessageDialog(null,
                "EMPLOYEE "+name+" REMOVED SUCCESSFUL",
                "SUCCESS",
                JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void mSuccessFulAltered(String name){
        JOptionPane.showMessageDialog(null,
                "EMPLOYEE "+name+" ALTERED SUCCESSFUL",
                "SUCCESS",
                JOptionPane.INFORMATION_MESSAGE);
    }

//</editor-fold>
}
