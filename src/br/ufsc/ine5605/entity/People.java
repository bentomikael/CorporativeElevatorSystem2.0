package br.ufsc.ine5605.entity;

public abstract class People {
    public String name;
    public int age;
    public enum Gender {
        MALE(1),
        FEMALE(2);
        
        public int number;
        Gender(int number){
            this.number = number;
        }
        
        public void setGenderNumber(int number){
            this.number = number;
        }
    }
    public enum Occupation {
    
        SIMPLE_EMPLOYEE(1),
        MANAGER(2),
        ADMINISTRATION(3),
        EXECUTIVE(4),
        CEO(5);

        public int accessLevel;
        Occupation(int access){
            this.accessLevel = access;
        }
        
        public void setAccessLevel(int access){
            accessLevel = access;
        }
        
    
    }
    
    public String getName(){
        return name;
    }
    
    public int getAge(){
        return age;
    }
    
    public void setAge(int age){
        this.age = age;
    }
    
       
}
