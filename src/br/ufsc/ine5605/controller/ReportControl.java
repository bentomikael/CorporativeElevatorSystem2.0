package br.ufsc.ine5605.controller;

import br.ufsc.ine5605.entity.Report;
import java.util.ArrayList;

public class ReportControl {
    private ArrayList<Report> reports;

    public ReportControl() {
        reports = new ArrayList();
        
    }

    public void addReport(String employeeName, String activity, String thatName, 
            String date, String hour, String floor){
      
        reports.add(new Report(employeeName,activity,thatName,date,hour,floor) );
    }
     
    public ArrayList<Report> getAllReports() {
        return reports;
    }
    
    public ArrayList getReportSpecific(String type,String value){
        ArrayList array = new ArrayList();

        switch(type){
            
            case "name":
                for(Report r:reports)
                    if(r.getEmployeeName().equals(value))
                        array.add(r);
                break;
                
            case "activity":
                for(Report r:reports)
                    if(r.getActivity().equals(value))
                        array.add(r);
                break;
                
            case "date":
                for(Report r:reports)
                    if(r.getDate().equals(value))
                        array.add(r);
                break;
                
            case "hour":
                for(Report r:reports)
                    if(r.getHour().substring(0,2).equals(value))
                        array.add(r);
                break;
                
            case "floor":
                for(Report r:reports)
                    if(r.getFloor().equals(value))
                        array.add(r);
                break;
        }
        return array;
    }
      
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
