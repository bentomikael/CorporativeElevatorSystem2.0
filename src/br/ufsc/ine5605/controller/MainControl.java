package br.ufsc.ine5605.controller;

import br.ufsc.ine5605.Screen.ScreenControl;
import static java.lang.Thread.sleep;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainControl {
    
    private EmployeeControl eControl;
    private ScreenControl screen;
    private ReportControl rControl;
    private int[] options;  //usada para manipular as opcoes recebidas de tela
    private Date date;
    private SimpleDateFormat format;
    private boolean logged; // usado para saber quando está logado
    
    public MainControl() {
        eControl = new EmployeeControl();
        screen = new ScreenControl();
        rControl = new ReportControl();
        options = new int[5];        
    }
    
    
    //<editor-fold defaultstate="collapsed" desc="Manipulacão de tempo">
    private String getDate() {
        date = new Date();
        format = new SimpleDateFormat("dd/MM");
        return format.format(date);
    }
    
    private String getHour(){
        date = new Date();
        format = new SimpleDateFormat("HH:mm");
        return format.format(date);
    }
    
//</editor-fold>     
     
    //<editor-fold defaultstate="collapsed" desc="Login">
    /** 
     * Inicia nova classe que verifica logout
     * Pega os dados da tela pelo controlador de tela.
     * Atribui o usuario atual e usa o nivel de acesso para iniciar home.
    */
    public void start(){ 
        eControl.login( 
            screen.login(
            eControl.getCodes(
            eControl.getAllEmployees() )) );
        
        home();
        
    }
    
    private void logout(){        
        start();
        
       }

//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Controle de acoes por tela">
    
    /**
     * @param actualAccessLevel nivel do usuario atual(recebe valor de start())
     * Se usuario escolheu opcão 1 vai pra tela de andares.
     * Se usuario escolheru 2 vai pra tela de opcoes administrativas.
    */
    private void home() {
        new LogoutCheck().start();
        
        options[0] = screen.home(eControl.getActualUserName(),
                eControl.getActualUserLevelNumber() );

        switch(options[0]){
            case 1:
                floor();
                break;
            case 2:
                administrativeOptions();
                break;
        }
    }

    private void floor(){
        
        options[0] = screen.floor(eControl.getActualUserLevelNumber());

        if(options[0] != -1){
            try{
                eControl.goToFloor(options[0]);
                reportsRegister(ReportControl.Activity.GO_TO_FLOOR.toString());
                
                if(options[0] == 0)
                    screen.mLeavingFloor();
                else
                    screen.mWentInFloor(options[0]);
                logout(); 
                
            }catch(IllegalArgumentException e){
                screen.mAlreadyInFloor();
                floor();
            }
            
        }
    }

    private void administrativeOptions() {
        
        options[0] = screen.administrativeOptions();
       
        switch(options[0]){
            case 1:
                newEmployee();
                break;
            case 2:
                delEmployee();
                break;
            case 3:
                changeAccess();
                break;
            case 4:
                report();
                break;
            case 5:
                list();
                break;
        }
    }
    
    private void newEmployee() {
        
        String name = screen.addEmployeeName();
        
        if(!name.equals("00")){
            
            options[0] = screen.addEmployeeAge();

            if(options[0] != -1){
                
                options[1] = screen.addEmployeeGender();

                if(options[1] != -1){
                    
                    options[2] = screen.addEmployeeCode(
                        eControl.getCodes( eControl.getAllEmployees() ));

                    if(options[2] != -1){
                        
                        options[3] = screen.addEmployeeOccupation(
                            eControl.getActualUserLevelNumber() );

                        if(options[3] != -1){
                            
                            eControl.registerNewEmployee( 
                                options[2],
                                eControl.convertOccupation(options[3]),
                                name,
                                options[0],
                                eControl.convertGender(options[1]) );

                            reportsRegister(ReportControl.Activity.REGISTERED.toString());
                            screen.mStandBy();
                            home(eControl.getActualUserLevelNumber());
                        }
                    }
                }
            }
        }
    }

    private void delEmployee() {
        
        options[0] = screen.delEmployeeCode(
            eControl.getCodes(eControl.getAllEmployees()) );
       
        if(options[0] != -1){
        options[1] = screen.delEmployeeConfirmation(
            eControl.getActualUserLevelNumber(),
            eControl.getEmployeeByCode(options[0]).getAccessLevelNumber(),
            eControl.getEmployeeByCode(options[0]).getName());
        
            if(options[1] == 1){
                reportsRegister(ReportControl.Activity.REMOVED.toString());
                eControl.removeEmployeeByCode(options[0]);
                screen.mStandBy();
                home(eControl.getActualUserLevelNumber());
            }
        }
    }
     
    private void changeAccess() { 
         
        options[0] = screen.changeEmployeeCode( 
            eControl.getActualUserCode(),
                eControl.getCodes( eControl.getAllEmployees()) );
        
        if(options[0] != -1){
            if(screen.checkAuthorization(
               eControl.getActualUserLevelNumber(),
               eControl.getEmployeeByCode( options[0] ).getAccessLevelNumber() ))     
            
                options[1] = screen.changeEmployeeOccupation( 
                    eControl.getActualUserLevelNumber() );
                
            if(options[1] != -1){
                reportsRegister(ReportControl.Activity.CHANGED.toString());      

                eControl.changeOccupation(
                    options[0], 
                    eControl.convertOccupation(options[1]));

                screen.mStandBy();
                home(eControl.getActualUserLevelNumber());
                }   
            }      
    }

    private void report(){
        options[0] = screen.reportScreenOptions();
        
        switch(options[0]){
            
            case 1:
                options[1] = screen.reportScreenFloor();
                if(options[1] != -1)
                    rControl.printIt(
                    rControl.getReportSpecific(ReportControl.Type.FLOOR,
                            Integer.toString(options[1]) ));
                break;
                
            case 2:
                options[1] = screen.reportScreenCode(
                        eControl.getCodes(
                        eControl.getAllEmployees() ));
                if(options[1] != -1)
                    rControl.printIt(
                    rControl.getReportSpecific(ReportControl.Type.NAME,
                    eControl.getEmployeeByCode(options[1]).getName() ));
                break;
                
            case 3:
                String dat;
                options[1] = screen.reportScreenDay();
                
                if(options[1] != -1){
                    
                    options[2] = screen.reportScreenMonth();
                        
                    if(options[2] != -1){
                        if(options[2] < 10)
                        dat = Integer.toString(options[1])+
                                "/0"+
                                Integer.toString(options[2]);
                        else
                            dat = Integer.toString(options[1])+
                                "/"+
                                Integer.toString(options[2]);

                        rControl.printIt(
                        rControl.getReportSpecific(ReportControl.Type.DATE,dat));
                    }
                }
                break;
                
            case 4:
                options[1] = screen.reportScreenHour();
                if(options[1] != -1)
                    rControl.printIt(
                    rControl.getReportSpecific(ReportControl.Type.HOUR,
                            Integer.toString(options[1]) ));
                break;
                
            case 5:
                rControl.printIt(rControl.getReportSpecific(ReportControl.Type.ACTIVITY,
                        ReportControl.Activity.REGISTERED.toString()) );
                break;
                
            case 6:
                rControl.printIt(rControl.getReportSpecific(ReportControl.Type.ACTIVITY,
                        ReportControl.Activity.REMOVED.toString()));
                break;
                
            case 7:
                rControl.printIt(rControl.getAllReports());
                break;
                
        }
        if(options[0] != -1 && options[1] != -1){ 
            screen.mStandBy();
            home(eControl.getActualUserLevelNumber());
        }
    }
       
    private void list() {
        
        options[0] = screen.employeesList();
        
        switch(options[0]){
            
            case 1:
                eControl.printIt(eControl.getAllEmployees() );                    
                break;
                
            case 2:
                eControl.printIt( 
                eControl.getEmployeesByLevelAccess(
                screen.employeeListOccupation()) );
                break;
                
            case 3:
                eControl.printIt( 
                eControl.getEmployeeByFloor(
                screen.employeeListFloor()) );
                break;
                
            case 4:
                eControl.printIt( eControl.getEmployeesInWork() );
                break;
                
        }
        if(options[0] != -1){ // impede que continue apos deslogar
            screen.mStandBy();
            home(eControl.getActualUserLevelNumber());
        }
    }
    
//</editor-fold>
       
    private void reportsRegister(String rep){
        
        switch(rep){
            
            case "floor":
                                    
                rControl.addReport(eControl.getActualUserName(),
                ReportControl.Activity.GO_TO_FLOOR,
                "-",
                getDate(),
                getHour(),
                Integer.toString(options[0]));
                
                break;
                
            case "new":
                
                rControl.addReport(eControl.getActualUserName(),
                ReportControl.Activity.REGISTERED,
                eControl.getEmployeeByCode(options[2]).getName(),
                getDate(),
                getHour(),
                "-");
                
                break;
                
            case "del":
                
                rControl.addReport(eControl.getActualUserName(),
                ReportControl.Activity.REMOVED,
                eControl.getEmployeeByCode(options[0]).getName(),
                getDate(),
                getHour(),
                "-");
                
                break;
                
            case "change":
                
                rControl.addReport(eControl.getActualUserName(),
                ReportControl.Activity.CHANGED,
                eControl.getEmployeeByCode(options[0]).getName(),
                getDate(),
                getHour(),
                "-");
                
                break;                        
        }
    }
   
    /**
     * Thread que verifica constantemente se foi requisitado o logout  
     */
    private class LogoutCheck extends Thread{
        
        public LogoutCheck() {
            logged = true;
        }
        
        @Override
        public void run(){
            
            while(logged){
                                
                try {
                    if(screen.getLogoutRequest())
                        logged = false;
                    sleep(15);

                } catch (InterruptedException ex) {}
                
            }
            logout();
            //finaliza sempre que fizer logout para economizar processamento quando ocioso
            try {
                finalize();
            }catch (Throwable ex) {}
        }

        
    }
}
