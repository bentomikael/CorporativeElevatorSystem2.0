package br.ufsc.ine5605.Screen;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ScreenControl {
    private MainFrame mainF;
    private Login login;
    private int aux; // variavel auxiliar
    
    public ScreenControl() {
        mainF = new MainFrame();
        login = new Login();
    }

    public int login(ArrayList allCodes){
        
        do{
            mainF.setContentPane(login);
            waitInstruction(login);
            aux = codeFilter(login.getCode());
            if(!allCodes.contains(aux))
                JOptionPane.showConfirmDialog(mainF,
                        "USER NOT FOUND",
                        "NOT FOUND",
                        JOptionPane.ERROR_MESSAGE);
        
        }while(!allCodes.contains(aux));
        
        return aux;
    }
    
    private void waitInstruction(IPane pane){
        do{
            
        }while(pane.getSignal()== Signal.EMPITY.value);
        
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
}
