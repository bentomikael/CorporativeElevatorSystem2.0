package br.ufsc.ine5605.controller;

import br.ufsc.ine5605.Screen.ScreenView;
import br.ufsc.ine5605.Screen.Signal;
import br.ufsc.ine5605.exception.AlreadyInFloorException;
import br.ufsc.ine5605.entity.People;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import static java.lang.Thread.sleep;

public final class MainControl {

    private static final MainControl INSTANCE = new MainControl();

    private static EmployeeControl eControl;
    private ScreenView screen;
    private static ReportControl rControl;
    private Date date;
    private SimpleDateFormat format;
    private int option;  //usada para manipular as opcoes recebidas de tela
    private boolean logged; // usado para saber quando está logado

    public static MainControl getInstance() {
        return INSTANCE;
    }

    private MainControl() {
        eControl = EmployeeControl.getIstance();
        screen = ScreenView.getIstance();
        rControl = ReportControl.getIstance();
        
        start();
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
    private void start() {        
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
                
                reportsRegister(ReportControl.Activity.GO_TO_FLOOR, null);
                
                if (option == 0) {
                    screen.mLeavingFloor();
                } else {
                    screen.mWentInFloor(option);
                }
                logout();

            } catch (AlreadyInFloorException e) {
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
                    People.Occupation.valueOf(newE.get(4).toString()),
                    newE.get(0).toString(),
                    Integer.parseInt(newE.get(1).toString()),
                    People.Gender.valueOf(newE.get(2).toString()));

             reportsRegister(ReportControl.Activity.REGISTERED, newE.get(0));

            screen.mSuccessFulRegistered(newE.get(0).toString());
            home();
        }
    }

    private void delEmployee() {
        String name;
        name = screen.delEmployee(eControl.getNames(
                eControl.getAllEmployees()));

        if (name != null) {
            if (eControl.getActualUserLevelNumber()
                    > eControl.getEmployeeByName(name).getAccessLevelNumber()) {

                reportsRegister(ReportControl.Activity.REMOVED, name);

                eControl.removeEmployeeByCode(
                        eControl.getEmployeeByName(name).getCodeAccess());
                screen.mSuccessFulRemoved(name);

                home();
            }
            else{
                screen.mDontHavePermision();
                home();
            }
        }
    }

    private void changeAccess() {
        ArrayList toChange = new ArrayList();

        toChange = screen.changeEmployee(
                eControl.getNames(eControl.getAllEmployees()));

        if (!toChange.isEmpty()) {
            if (eControl.getEmployeeByName(
                    toChange.get(0).toString()).getAccessLevelNumber()
                    < eControl.getActualUserLevelNumber()
                    && People.Occupation.valueOf(toChange.get(1).toString()).accessLevel
                    < eControl.getActualUserLevelNumber()) {

                reportsRegister(ReportControl.Activity.CHANGED, toChange.get(0));

                eControl.changeOccupation(
                        eControl.getEmployeeByName(
                                toChange.get(0).toString()).getCodeAccess(),
                        People.Occupation.valueOf(toChange.get(1).toString()));

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

        if (option != -1) {
            switch (option) {

                case 1:
                    String floor;
                    floor = ScreenView.dFloor();

                    screen.table(Signal.REPORTS,
                            rControl.getList(
                                    rControl.getReportSpecific(ReportControl.Type.FLOOR,
                                            floor)));
                    break;

                case 2:
                    String date;
                    date = ScreenView.dDay();

                    screen.table(Signal.REPORTS,
                            rControl.getList(
                                    rControl.getReportSpecific(ReportControl.Type.DATE,
                                            date)));
                    break;

                case 3:
                    screen.table(Signal.REPORTS,
                            rControl.getList(
                                    rControl.getReportSpecific(ReportControl.Type.ACTIVITY,
                                            ReportControl.Activity.REGISTERED.toString())));

                    break;

                case 4:
                    screen.table(Signal.REPORTS,
                            rControl.getList(
                                    rControl.getReportSpecific(ReportControl.Type.ACTIVITY,
                                            ReportControl.Activity.REMOVED.toString())));

                    break;

                case 5:
                    screen.table(Signal.REPORTS,
                            rControl.getList(rControl.getAllReports()));
                    break;

            }
        }
        if (option != -1) {
            home();
        }
    }

    private void list() {

        option = screen.listOptions();

        switch (option) {

            case 1:
                screen.table(Signal.LIST,
                        eControl.getList(eControl.getAllEmployees()));
                break;

            case 2:
                int ocp;
                ocp = ScreenView.dOccupation();
                screen.table(Signal.LIST, eControl.getList(
                        eControl.getEmployeesByLevelAccess(ocp)));
                break;

            case 3:
                String floor;
                floor = ScreenView.dFloor();
                screen.table(Signal.LIST, eControl.getList(
                        eControl.getEmployeesByFloor(Integer.parseInt(floor))));
                break;

            case 4:
                screen.table(Signal.LIST, eControl.getList(
                        eControl.getEmployeesInWork()));
                break;

        }
        if (option != -1) { // impede que continue apos deslogar
            home();
        }
    }

//</editor-fold>
    private void reportsRegister(ReportControl.Activity rep, Object nameThat) {

        switch (rep) {

            case GO_TO_FLOOR:

                rControl.addReport(eControl.getActualUserName(),
                        ReportControl.Activity.GO_TO_FLOOR,
                        "-",
                        getDate(),
                        getHour(),
                        Integer.toString(option));

                break;

            case REGISTERED:

                rControl.addReport(eControl.getActualUserName(),
                        ReportControl.Activity.REGISTERED,
                        nameThat.toString(),
                        getDate(),
                        getHour(),
                        "-");

                break;

            case REMOVED:

                rControl.addReport(eControl.getActualUserName(),
                        ReportControl.Activity.REMOVED,
                        nameThat.toString(),
                        getDate(),
                        getHour(),
                        "-");

                break;

            case CHANGED:

                rControl.addReport(eControl.getActualUserName(),
                        ReportControl.Activity.CHANGED,
                        nameThat.toString(),
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
