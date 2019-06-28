package br.ufsc.ine5605.exception;

public class NoNumberException extends NumberFormatException{

    public NoNumberException() {
        super("INVALID NUMBER");
    }
    
}
