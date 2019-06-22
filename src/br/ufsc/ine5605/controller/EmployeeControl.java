package br.ufsc.ine5605.controller;

import br.ufsc.ine5605.entity.Employee;
import br.ufsc.ine5605.entity.Report;
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
    public ArrayList getEmployeesByFloor(int floor){
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
    
    public Employee getEmployeeByName(String name) {
        Employee employee = null;
        for(Employee e: employees)
            if(e.getName()== name){
                employee = e;
                break;
            }
        return employee;
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
    
    public ArrayList getNames(ArrayList<Employee> array){
        ArrayList<String> names = new ArrayList();
        for(Employee e : array)
            names.add(e.getName());
        return names;
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
    
    public Object[][] getList (ArrayList<Employee> array){
        
        Object[][] list = new Object[array.size()][5];
        
        for (int i = 0; i < array.size(); i++) {
            list[i][0] = array.get(i).getName().toUpperCase();
            list[i][1] = array.get(i).getCodeAccess();
            list[i][2] = array.get(i).getCurrentFloor();
            list[i][3] = array.get(i).getOccupation();
            list[i][4] = array.get(i).getCurrentFloor();  
        }
        
        return list;   
    }

//</editor-fold>

    
}
