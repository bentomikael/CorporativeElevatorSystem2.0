package br.ufsc.ine5605.corporative_elavator_system2;

import br.ufsc.ine5605.controller.MainControl;
import br.ufsc.ine5605.entity.Employee;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class StoreData {
    private static final String EMPLOYEES = "Employees.dat";
    private static final String REPORTS = "Reports.dat";
    private static ArrayList employeesCache;
    private static ArrayList reportsCache;
    
    public StoreData(){}
    
    public static void exportEmployees() throws FileNotFoundException{

        try{
            FileOutputStream fileOut = new FileOutputStream(EMPLOYEES);
            ObjectOutputStream obj = new ObjectOutputStream(fileOut);

            //escreve
            obj.writeObject(employeesCache);
            obj.flush();
            fileOut.flush();
                                                    System.out.println("chegou");

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
    public static void importEmployees() throws FileNotFoundException, ClassNotFoundException{
        try{
            FileInputStream fileIn = new FileInputStream(EMPLOYEES);
            ObjectInputStream obj = new ObjectInputStream(fileIn);
            
            employeesCache.add(obj.readObject());
            
            fileIn.close();
            obj.close();
            fileIn = null;
            obj = null;
            
        }catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public static void exportReports() throws FileNotFoundException{
        
        try{
            FileOutputStream fileOut = new FileOutputStream(REPORTS);
            ObjectOutputStream obj = new ObjectOutputStream(fileOut);
            
            //escreve
            obj.writeObject(MainControl.getReports());
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
    public static void importReports() throws FileNotFoundException, ClassNotFoundException{
        try{
            FileInputStream fileIn = new FileInputStream(REPORTS);
            ObjectInputStream obj = new ObjectInputStream(fileIn);
            
            reportsCache.add(obj.readObject());
            
            fileIn.close();
            obj.close();
            fileIn = null;
            obj = null;
            
        }catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    
}
