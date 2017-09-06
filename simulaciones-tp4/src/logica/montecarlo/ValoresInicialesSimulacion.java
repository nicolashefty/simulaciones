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
    private double diaDesde;
    private double diaHasta;
    private DatosMontecarlo datosMontecarlo;
    
    public ValoresInicialesSimulacion()
    {
        
    }
    
    public ValoresInicialesSimulacion(double diaDesde, double diaHasta, DatosMontecarlo datosMon)
    {
        this.diaDesde = diaDesde;
        this.diaHasta = diaHasta;
        this.datosMontecarlo = datosMon;
    }

    public double getDiaDesde() {
        return diaDesde;
    }

    public void setDiaDesde(double diaDesde) {
        this.diaDesde = diaDesde;
    }

    public double getDiaHasta() {
        return diaHasta;
    }

    public void setDiaHasta(double diaHasta) {
        this.diaHasta = diaHasta;
    }

    public DatosMontecarlo getDatosMontecarlo() {
        return datosMontecarlo;
    }

    public void setDatosMontecarlo(DatosMontecarlo datosMontecarlo) {
        this.datosMontecarlo = datosMontecarlo;
    }
    
    
}
