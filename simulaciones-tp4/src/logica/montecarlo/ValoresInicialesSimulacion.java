/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.montecarlo;

/**
 *
 * @author heftyn
 */
public class ValoresInicialesSimulacion 
{
    private int diaDesde;
    private int diaHasta;
    private DatosMontecarlo datosMontecarlo;
    
    public ValoresInicialesSimulacion()
    {
        
    }
    
    public ValoresInicialesSimulacion(int diaDesde, int diaHasta, DatosMontecarlo datosMon)
    {
        this.diaDesde = diaDesde;
        this.diaHasta = diaHasta;
        this.datosMontecarlo = datosMon;
    }

    public int getDiaDesde() {
        return diaDesde;
    }

    public void setDiaDesde(int diaDesde) {
        this.diaDesde = diaDesde;
    }

    public int getDiaHasta() {
        return diaHasta;
    }

    public void setDiaHasta(int diaHasta) {
        this.diaHasta = diaHasta;
    }

    public DatosMontecarlo getDatosMontecarlo() {
        return datosMontecarlo;
    }

    public void setDatosMontecarlo(DatosMontecarlo datosMontecarlo) {
        this.datosMontecarlo = datosMontecarlo;
    }
    
    
}
