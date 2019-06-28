/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.exception;

/**
 *
 * @author Acer
 */
public class AlreadyInFloorException extends IllegalArgumentException{

    public AlreadyInFloorException() {
        super("YOU ALREADY IN THIS FLOOR");
    }
    
}
