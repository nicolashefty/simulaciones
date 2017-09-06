/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.montecarlo;

import logica.montecarlo.politicas.IPolitica;

/**
 *
 * @author heftyn
 */
public class CoordinadorMontecarlo 
{

    public static int DIA_MAXIMO = 120;

    private DatosMontecarlo datosMontecarlo;
    
    //Son necesarios??
    private EstadoMontecarlo[] vectorEstado = new EstadoMontecarlo[2];
    
    private IPolitica politica;
    
    
    public void setPolitica(IPolitica politica)
    {
        this.politica = politica;
    }
    
    public void setDatosMontecarlo(DatosMontecarlo datosMontecarlo)
    {
        this.datosMontecarlo = datosMontecarlo;
    }
    
    public Object[] getRowActual()
    {
        return vectorEstado[1].getVectorParaTabla();
    }
    
    public IPolitica getPolitica()
    {
        return politica;
    }

    public void simularDia() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
