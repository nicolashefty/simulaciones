/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.montecarlo.demora;

/**
 *
 * @author heftyn
 */
public class DemoraRow implements Comparable<DemoraRow>{
    private double demora;
    private double probabilidad;
    
    public DemoraRow(double probabilidad, double demora)
    {
        this.demora = demora;
        this.probabilidad = probabilidad;
    }

    public double getDemora() {
        return demora;
    }

    public double getProbabilidad() {
        return probabilidad;
    }

    @Override
    public int compareTo(DemoraRow o) 
    {
        if (o.demora == demora)
        {
            return 0;
        }
        else if (o.demora > demora)
        {
            return -1;
        }
        else
        {
            return 1;
        }
    }
    
}
