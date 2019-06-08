package br.ufsc.ine5605.controller;

import br.ufsc.ine5605.entity.Employee;
import java.util.ArrayList;

public class EmployeeControl {
    private ArrayList<Employee> employees;
    private Employee actualUser; 


    public EmployeeControl() {
        employees = new ArrayList(); 
        employees.add(new Employee(999,Employee.Occupation.CEO,"goku",23,Employee.Gender.MALE)); //TESTE, apagar depois
        employees.add(new Employee(888,Employee.Occupation.EXECUTIVE,"vegeta",22,Employee.Gender.MALE)); //TESTE, apagar depois
        employees.add(new Employee(777,Employee.Occupation.ADMINISTRATION,"joao amoedo",10,Employee.Gender.MALE)); //TESTE, apagar depois
        employees.add(new Employee(666,Employee.Occupation.MANAGER,"bolsonaro",40,Employee.Gender.MALE)); //TESTE, apagar depois
        employees.add(new Employee(555,Employee.Occupation.SIMPLE_EMPLOYEE,"dilma",30,Employee.Gender.FEMALE)); //TESTE, apagar depois

    }
    
    //<editor-fold defaultstate="collapsed" desc="Usuario Logado">
   
    /**
     * 
     * @param code codigo do usuario tentando fazer login
     * @return se existir, define usuario atual e retorna ele,se nao, retorna null
     */
    public Employee login(int code){
        
        if(getEmployeeByCode(code) == null){
            actualUser = null;
            return null;
        }else{
            actualUser = getEmployeeByCode(code);
            return actualUser;
        }
    }
    
    public Employee getActualUser() {
        return actualUser;
    }
    
    public int getActualUserLevelNumber(){
        return getActualUser().getAccessLevelNumber();
    }
    
    public int getActualUserFloor(){
        return getActualUser().getCurrentFloor();
    }
    
    public int getActualUserCode(){
        return getActualUser().getCodeAccess();
    }
    
    public String getActualUserName(){
        return getActualUser().getName();
    }
    
    //</editor-fold>
        
    //<editor-fold desc="gets de funcionarios">
    
    // retorna todos funcionarios cadastrados
    public ArrayList getAllEmployees(){
        return employees;
    }
    
    //retorna todos funcionarios que estão em algum andar
    public ArrayList getEmployeesInWork(){
        ArrayList<Employee> list = new ArrayList();
        for(Employee e: employees)
            if(e.getCurrentFloor() != 0)
                list.add(e);
        return list;
    }
    
    //retorna funcionarios de determinado andar
    public ArrayList getEmployeeByFloor(int floor){
        ArrayList<Employee> list = new ArrayList();
        for(Employee e: employees)
            if(e.getCurrentFloor() == floor)
                list.add(e);
        return list;
    }
    
    //retorna uma lista com funcionarios com determinado nivel de acesso
    public ArrayList getEmployeesByLevelAccess(int level){
        ArrayList<Employee> list = new ArrayList();
        
        for(Employee e : employees)
            if(e.getAccessLevelNumber() == level)
                list.add(e);
        return list;
        }
    
    //procura funcionario cadastrado pelo codigo
    public Employee getEmployeeByCode(int code){
        Employee employee = null;
        for(Employee e: employees)
            if(e.getCodeAccess() == code){
                employee = e;
                break;
            }
        return employee;                     
    }
    
    //imprime nomes e codigo do array de funcionarios
    public void printIt(ArrayList<Employee> array){
        int i = 1;
        if(array.isEmpty())
            System.out.println("--------EMPTY LIST--------");
        else{
            System.out.printf("%s %15s %17s %16s \n", " _________________", " _______ "," _____________ "," _____________");
            System.out.printf("%s %15s %15s %15s\n","|       Name      |","|  Code  |","| Actual Floor |","|   Occupation  |");
        
            for(Employee e : array)
                System.out.printf("%2d %-17s %9d  %13d %9s %s\n",
                i++,
                e.getName().toUpperCase(),
                e.getCodeAccess(),
                e.getCurrentFloor(),
                "",
                e.getOccupation());
        }
    }  
    
    /**
     * Obtem codigo de todos funcionarios de um array
     * @param array entre com array de funcionarios
     * @return array com codigos
     */
    public ArrayList getCodes(ArrayList<Employee> array){
        ArrayList<Integer> codes = new ArrayList();
        for(Employee e : array)
            codes.add(e.getCodeAccess());
        return codes;
    }
    
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Manipulacão de usuarios">
    
    //registra funcionario
    public Employee registerNewEmployee
    (int code, Employee.Occupation accessLevel, String name, int age, Employee.Gender gender){
        employees.add(new Employee(code,accessLevel,name,age,gender));
              
        return getEmployeeByCode(code);
    }
    
    //remove funcionario pelo codigo
    public void removeEmployeeByCode(int code){
        
        System.out.println("User Removed:\n\n "+ getEmployeeByCode(code).getName());     
        employees.remove(getEmployeeByCode(code));
        
    }
    
    //altera nivel de acesso de outro funcionario
    public void changeOccupation(int code,Employee.Occupation newAccessLevel){
        getEmployeeByCode(code).setOccupation(newAccessLevel);
    }
   
    //</editor-fold>
        
    //entra no andar
    public void goToFloor(int floor) {
        getActualUser().setCurrentFloor(floor);   
    }
      
    //<editor-fold defaultstate="collapsed" desc="Conversões">
    
    public Employee.Occupation convertOccupation(int level){
        Employee.Occupation occupation = null;
       
        switch(level){
            case 1:
                occupation = Employee.Occupation.SIMPLE_EMPLOYEE;
                break;
            case 2:
                occupation = Employee.Occupation.MANAGER;
                break;
            case 3:
                occupation = Employee.Occupation.ADMINISTRATION;
                break;
            case 4:
                occupation = Employee.Occupation.EXECUTIVE;
                break;
            case 5:
                occupation = Employee.Occupation.CEO;
                break;
        }
          return occupation;  
    }
    public Employee.Gender convertGender(int number){
        Employee.Gender gender = null;
        switch(number){
            case 0:
                gender = Employee.Gender.MALE;
                break;
            case 1:
                gender = Employee.Gender.FEMALE;
                break;  
        }
        return gender;
    }

//</editor-fold>
    
}
