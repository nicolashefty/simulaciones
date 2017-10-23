/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.servidores.exceptions;

/**
 *
 * @author heftyn
 */
public class NoAtendidos extends Exception
{

    private int noAtendidos;
    
    public NoAtendidos(int noAtendidos)
    {
        this.noAtendidos = noAtendidos;
    }
    
    public int getNoAtendidos()
    {
        return noAtendidos;
    }
}
