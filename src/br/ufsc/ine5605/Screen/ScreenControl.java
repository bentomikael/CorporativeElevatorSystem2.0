package br.ufsc.ine5605.Screen;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ScreenControl {
    private MainFrame mainF;
    private Login login;
    private Home home;
    private int aux; // variavel auxiliar
    
    public ScreenControl() {
        mainF = new MainFrame();
        login = new Login();
        home = new Home();
        
        addPanelsToFrame();
        mainF.start();
    }
    
    
    public int login(ArrayList allCodes) {
        mainF.setContentPane(login);
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
        
        //oculta botão administrativo se não tiver autorização
        if(actualUserLevel < 3)
            home.getComponent(1).setVisible(false);
        
        mainF.setContentPane(home);
        
        return aux;
    }

    
    
    
    
    private void waitInstruction(IPanel pane) {
        do{
            System.out.print("");   // não executa corretamente quando vazio
        }while(pane.getSignal()== Signal.EMPITY);
    }
    
    private int codeFilter(String code){
        int newInt = -1;
        boolean valid;
        
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
    
    private void addPanelsToFrame(){
        mainF.add(login);
        mainF.add(home);
        
    }
}
