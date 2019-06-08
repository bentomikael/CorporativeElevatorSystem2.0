package br.ufsc.ine5605.Screen;


public enum Signal {
    EMPITY(0),
    NEXT(1),
    BACK(-1);
    
    public int value;
    
    Signal(int value){
        this.value = value;
    }
    
        
    
}
