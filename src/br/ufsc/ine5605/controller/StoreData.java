package br.ufsc.ine5605.controller;

import br.ufsc.ine5605.entity.Employee;
import br.ufsc.ine5605.entity.Report;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class StoreData {
    private static final StoreData INSTANCE = new StoreData();
    
    private static final String EMPLOYEES = "Employees.dat";
    private static final String REPORTS = "Reports.dat";
    private static HashMap<Integer,Report> reportsListCache;
    private static HashMap<Integer,Employee> employeesListCache;
    private static int repCount;
    
    private StoreData() {
        reportsListCache = new HashMap();
        employeesListCache = new HashMap();
        repCount = 0;
        
        try {
            importEmployees();
        }catch (FileNotFoundException ex) {} 
        catch (ClassNotFoundException ex) {}
        try {
            importReports();
        }catch (FileNotFoundException ex) {} 
        catch (ClassNotFoundException ex) {}
        
    }
    
    private static void exportEmployees() throws FileNotFoundException{

        try{
            FileOutputStream fileOut = new FileOutputStream(EMPLOYEES);
            ObjectOutputStream obj = new ObjectOutputStream(fileOut);

            
            //escreve
            obj.writeObject(employeesListCache);            
            obj.flush();
            fileOut.flush();

            //fecha
            obj.close();
            fileOut.close();
            obj = null;
            fileOut = null;
            
        }catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        
            
    }
    private static void importEmployees() throws FileNotFoundException, ClassNotFoundException{
        try{
            FileInputStream fileIn = new FileInputStream(EMPLOYEES);
            ObjectInputStream obj = new ObjectInputStream(fileIn);
            
            employeesListCache = (HashMap<Integer, Employee>) obj.readObject();
            fileIn.close();
            obj.close();
            fileIn = null;
            obj = null;
            
        }catch(FileNotFoundException ex){
           new FileOutputStream(EMPLOYEES);
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    private static void exportReports() throws FileNotFoundException{
        
        try{
            FileOutputStream fileOut = new FileOutputStream(REPORTS);
            ObjectOutputStream obj = new ObjectOutputStream(fileOut);
            
            //escreve
            obj.writeObject(reportsListCache);
            obj.flush();
            fileOut.flush();
            
            //fecha
            obj.close();
            fileOut.close();
            obj = null;
            fileOut = null;
            
        }catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        
            
    }
    private static void importReports() throws FileNotFoundException, ClassNotFoundException{
        try{
            FileInputStream fileIn = new FileInputStream(REPORTS);
            ObjectInputStream obj = new ObjectInputStream(fileIn);
            
            reportsListCache = (HashMap<Integer, Report>) obj.readObject();
            
            fileIn.close();
            obj.close();
            fileIn = null;
            obj = null;
            
        }catch(FileNotFoundException ex){
            new FileOutputStream(REPORTS);
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
        
    public static ArrayList<Report> getReportsCache() {
        return new ArrayList(reportsListCache.values());
    }
    public static ArrayList<Employee> getEmployeesCache(){
        return new ArrayList(employeesListCache.values());
    }
    
    public static void addEmployee(Employee e) throws FileNotFoundException{
        employeesListCache.put(e.getCodeAccess(),e);
        exportEmployees();
    }
    public static void modifyEmployee(int code,Employee.Occupation newAccessLevel) throws FileNotFoundException{
        employeesListCache.get(code).setOccupation(newAccessLevel);
        exportEmployees();
    }
    public static void delEmployeeByCode(int code) throws FileNotFoundException{
        employeesListCache.remove(code);
        exportEmployees();
    }
    public static void addReport(Report r) throws FileNotFoundException{
        reportsListCache.put(++repCount,r);
        exportReports();
    }
    
       
}
