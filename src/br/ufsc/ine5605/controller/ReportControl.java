package br.ufsc.ine5605.controller;

import br.ufsc.ine5605.entity.Report;
import java.util.ArrayList;

public final class ReportControl {

    private static final ReportControl INSTANCE = new ReportControl();
    
    
    private ArrayList<Report> reports;
    public enum Type { NAME, ACTIVITY, DATE, HOUR, FLOOR}
    public enum Activity{REGISTERED,REMOVED,CHANGED,GO_TO_FLOOR}
    
    public static ReportControl getIstance(){
            return INSTANCE;
        }
    private ReportControl() {
        reports = new ArrayList();

    }

    public void addReport(String employeeName, Activity activity, String thatName,
            String date, String hour, String floor) {

        reports.add(new Report(employeeName, activity.toString(), thatName, date, hour, floor));
    }

    public ArrayList<Report> getAllReports() {
        return reports;
    }

    /**
     * 
     * @param type informaçoes da açao
     * @param activity açao realizada pelo usuario
     * @return lista com relatorios referente aos parametros
     */
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

    public Object[][] getList (ArrayList<Report> array){
        
        Object[][] list = new Object[array.size()][6];
        
        for (int i = 0; i < array.size(); i++) {
            list[i][0] = array.get(i).getEmployeeName();
            list[i][1] = array.get(i).getActivity();
            list[i][2] = array.get(i).getThatName();
            list[i][3] = array.get(i).getDate();
            list[i][4] = array.get(i).getHour();
            list[i][5] = array.get(i).getFloor();  
        }
        
        return list;   
    }
    
    
}
