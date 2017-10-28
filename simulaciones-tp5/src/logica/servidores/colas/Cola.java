/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.servidores.colas;

/**
 *
 * @author heftyn
 */
public class Cola 
{
    private int enCola;
    
    public Cola()
    {
        inicializar();
    }
    
    public void incrementarCola()
    {
        enCola++;
    }
    
    public void disminuirCola()
    {
        enCola--;
    }
    
    public int getCola()
    {
        return enCola;
    }
    
    public final void inicializar()
    {
        enCola = 0;
    }
    
    @Override
    public Cola clone()
    {
        Cola clon = new Cola();
        clon.enCola = this.enCola;
        
        return clon;
    }
}
