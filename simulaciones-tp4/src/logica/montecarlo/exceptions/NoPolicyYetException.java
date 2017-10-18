/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.montecarlo.exceptions;

/**
 *
 * @author heftyn
 */
public class NoPolicyYetException extends Exception{
    
    public NoPolicyYetException(String msg)
    {
        super(msg);
    }
}
