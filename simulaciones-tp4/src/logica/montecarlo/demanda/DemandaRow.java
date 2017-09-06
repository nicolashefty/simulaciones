/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.montecarlo.demanda;

/**
 *
 * @author heftyn
 */
public class DemandaRow implements Comparable<DemandaRow>{
    private double demanda;
    private double probabilidad;
    
    public DemandaRow(double probabilidad, double demanda)
    {
        this.demanda = demanda;
        this.probabilidad = probabilidad;
    }

    public double getDemanda() {
        return demanda;
    }

    public double getProbabilidad() {
        return probabilidad;
    }

    @Override
    public int compareTo(DemandaRow o) 
    {
        if (o.demanda == demanda)
        {
            return 0;
        }
        else if (o.demanda > demanda)
        {
            return -1;
        }
        else
        {
            return 1;
        }
    }
    
}
