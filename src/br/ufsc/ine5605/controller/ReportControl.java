package br.ufsc.ine5605.controller;

import br.ufsc.ine5605.entity.Report;
import java.util.ArrayList;

public class ReportControl {

    private ArrayList<Report> reports;

    public enum Type { NAME, ACTIVITY, DATE, HOUR, FLOOR}
    public enum Activity{REGISTERED,REMOVED,CHANGED,GO_TO_FLOOR}

    public ReportControl() {
        reports = new ArrayList();

    }

    public void addReport(String employeeName, Activity activity, String thatName,
            String date, String hour, String floor) {

        reports.add(new Report(employeeName, activity.toString(), thatName, date, hour, floor));
    }

    public ArrayList<Report> getAllReports() {
        return reports;
    }

    public ArrayList getReportSpecific(Type type, String activity) {
        ArrayList array = new ArrayList();

        if (type == Type.NAME) {
            for (Report r : reports) {
                if (r.getEmployeeName().equals(activity)) {
                    array.add(r);
                }
            }

        } else if (type == Type.ACTIVITY) {
            for (Report r : reports) {
                if (r.getActivity().equals(activity)) {
                    array.add(r);
                }
            }
            
        } else if (type == Type.DATE) {
            for (Report r : reports) {
                if (r.getDate().equals(activity)) {
                    array.add(r);
                }
            }
            
        } else if (type == Type.HOUR) {
            for (Report r : reports) {
                if (r.getHour().substring(0, 2).equals(activity)) {
                    array.add(r);
                }
            }
            
        } else if (type == Type.FLOOR) {
            for (Report r : reports) {
                if (r.getFloor().equals(activity)) {
                    array.add(r);
                }
            }
        }
       
        return array ;
    }

    //apagar
    public void printIt (ArrayList<Report> array){
        
        if(array.isEmpty())
            System.out.println("--------NO REPORTS YET--------");
        else{
            System.out.printf("%s %15s %16s %14s %11s %11s\n",
            " _________________", " _______ ","_________ "," ________"," ________"," _________");
            System.out.printf("%s %15s %15s %15s %s %s\n",
            "|       Name      |","|  Action  |","| Altered |","|   Date  |","|   Hour  |","|  Floor  |");
        
            for(Report r : array)
                System.out.printf("%-24s %-15s %-10s %12s %11s %9s \n",
                        r.getEmployeeName(),
                        r.getActivity(),
                        r.getThatName(),
                        r.getDate(),
                        r.getHour(),
                        r.getFloor());
        }
    
    }
}
