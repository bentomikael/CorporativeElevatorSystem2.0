package br.ufsc.ine5605.controller;

import br.ufsc.ine5605.Screen.ScreenControl;
import br.ufsc.ine5605.entity.Employee;
import br.ufsc.ine5605.entity.People;
import static java.lang.Thread.sleep;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainControl {

    private EmployeeControl eControl;
    private ScreenControl screen;
    private ReportControl rControl;
    private int option;  //usada para manipular as opcoes recebidas de tela
    private Date date;
    private SimpleDateFormat format;
    private boolean logged; // usado para saber quando está logado

    public MainControl() {
        eControl = new EmployeeControl();
        screen = new ScreenControl();
        rControl = new ReportControl();
    }

    //<editor-fold defaultstate="collapsed" desc="Manipulacão de tempo">
    private String getDate() {
        date = new Date();
        format = new SimpleDateFormat("dd/MM");
        return format.format(date);
    }

    private String getHour() {
        date = new Date();
        format = new SimpleDateFormat("HH:mm");
        return format.format(date);
    }

//</editor-fold>  
    //<editor-fold defaultstate="collapsed" desc="Login">
    /**
     * Inicia nova classe que verifica logout Pega os dados da tela pelo
     * controlador de tela. Atribui o usuario atual e usa o nivel de acesso para
     * iniciar home.
     */
    public void start() {
        eControl.login(
                screen.login(
                        eControl.getCodes(
                                eControl.getAllEmployees())));

        home();
    }

    private void logout() {
        start();
    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Controle de acoes por tela">
    /**
     * @param actualAccessLevel nivel do usuario atual(recebe valor de start())
     * Se usuario escolheu opcão 1 vai pra tela de andares. Se usuario escolheru
     * 2 vai pra tela de opcoes administrativas.
     */
    private void home() {
        new LogoutCheck().start();

        option = screen.home(eControl.getActualUserName(),
                eControl.getActualUserLevelNumber());

        switch (option) {
            case 1:
                floor();
                break;
            case 2:
                administrativeOptions();
                break;
        }
    }

    private void floor() {

        option = screen.floor(eControl.getActualUserLevelNumber());

        if (option != -1) {
            try {
                eControl.goToFloor(option);
                reportsRegister(ReportControl.Activity.GO_TO_FLOOR);

                if (option == 0) {
                    screen.mLeavingFloor();
                } else {
                    screen.mWentInFloor(option);
                }
                logout();

            } catch (IllegalArgumentException e) {
                screen.mAlreadyInFloor();
                floor();
            }

        }
    }

    private void administrativeOptions() {

        option = screen.administrativeOptions();

        switch (option) {
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
        ArrayList newE = new ArrayList();
        newE = screen.newEmployee(
                eControl.getActualUserLevelNumber(),
                eControl.getCodes(eControl.getAllEmployees()));

        if (newE != null) {

            eControl.registerNewEmployee(
                    Integer.parseInt(newE.get(3).toString()),
                    (People.Occupation) newE.get(4),
                    newE.get(0).toString(),
                    Integer.parseInt(newE.get(2).toString()),
                    (People.Gender) newE.get(1));

            reportsRegister(ReportControl.Activity.REGISTERED);
            screen.mSuccessFulRegistered(newE.get(0).toString());
            home();
        }
    }

    private void delEmployee() {
        String name;
        name = screen.delEmployee(eControl.getNames(
                eControl.getEmployeesByLevelAccess(
                        eControl.getActualUserLevelNumber() - 1)));

        if (name != null) {
            if (eControl.getActualUserLevelNumber()
                    > eControl.getEmployeeByName(name).getAccessLevelNumber()) {

                //reportsRegister(ReportControl.Activity.REMOVED);
                eControl.removeEmployeeByCode(
                        eControl.getEmployeeByName(name).getCodeAccess());
                screen.mSuccessFulRemoved(name);

                home();
            }
        }
    }

    private void changeAccess() {
        ArrayList toChange = new ArrayList();

        toChange = screen.changeEmployee(
                eControl.getNames(eControl.getAllEmployees()));

        if (!toChange.isEmpty()) {
            if (eControl.getEmployeeByName(toChange.get(0).toString()).getAccessLevelNumber()
                    < eControl.getActualUserLevelNumber()
                    && Integer.parseInt(toChange.get(1).toString())
                    < eControl.getActualUserLevelNumber()) {
//                reportsRegister(ReportControl.Activity.CHANGED);

                eControl.changeOccupation(
                        eControl.getEmployeeByName(
                                toChange.get(0).toString()).getCodeAccess(),
                        People.Occupation.ADMINISTRATION);

                screen.mSuccessFulAltered(toChange.get(0).toString());
                home();
            } else {
                screen.mDontHavePermision();
                logout();
            }
        }
    }

    public void report() {
        
        option = screen.reportOptions();

        switch (option) {

            case 1:
                ScreenControl.dFloor();
//                screen.table();
                    rControl.printIt(
                            rControl.getReportSpecific(ReportControl.Type.FLOOR,
                                    Integer.toString(options[1])));
                break;

            case 2:
                ScreenControl.dDay();
                    rControl.printIt(
                            rControl.getReportSpecific(ReportControl.Type.NAME,
                                    eControl.getEmployeeByCode(options[1]).getName()));
                break;

            case 3:
//                screen.table();
                
                
                break;

            case 4:
                //                screen.table();

                options[1] = screen.reportScreenHour();
                if (options[1] != -1) {
                    rControl.printIt(
                            rControl.getReportSpecific(ReportControl.Type.HOUR,
                                    Integer.toString(options[1])));
                }
                break;

            case 5:
                //                screen.table();

                rControl.printIt(rControl.getReportSpecific(ReportControl.Type.ACTIVITY,
                        ReportControl.Activity.REGISTERED.toString()));
                break;

        }
        if (option != -1 && option != -1) {
            home();
        }
    }

    private void list() {

        option = screen.listOptions();

        switch (option) {

            case 1:
                //                screen.table();
                break;

            case 2:
                ScreenControl.dOccupation();
                break;

            case 3:
                ScreenControl.dFloor();
                break;

            case 4:
                //                screen.table();
                break;

        }
        if (option != -1) { // impede que continue apos deslogar
            home();
        }
    }

//</editor-fold>
    private void reportsRegister(ReportControl.Activity rep) {

        switch (rep) {

            case GO_TO_FLOOR:

                rControl.addReport(eControl.getActualUserName(),
                        ReportControl.Activity.GO_TO_FLOOR,
                        "-",
                        getDate(),
                        getHour(),
                        Integer.toString(options[0]));

                break;

            case REGISTERED:

                rControl.addReport(eControl.getActualUserName(),
                        ReportControl.Activity.REGISTERED,
                        eControl.getEmployeeByCode(options[2]).getName(),
                        getDate(),
                        getHour(),
                        "-");

                break;

            case REMOVED:

                rControl.addReport(eControl.getActualUserName(),
                        ReportControl.Activity.REMOVED,
                        eControl.getEmployeeByCode(options[0]).getName(),
                        getDate(),
                        getHour(),
                        "-");

                break;

            case CHANGED:

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
    private class LogoutCheck extends Thread {

        public LogoutCheck() {
            logged = true;
        }

        @Override
        public void run() {

            while (logged) {

                try {
                    if (screen.getLogoutRequest()) {
                        logged = false;
                    }
                    sleep(15);

                } catch (InterruptedException ex) {
                }

            }
            logout();
            //finaliza sempre que fizer logout para economizar processamento quando ocioso
            try {
                finalize();
            } catch (Throwable ex) {
            }
        }

    }
}
