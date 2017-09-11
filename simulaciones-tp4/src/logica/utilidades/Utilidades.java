/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.utilidades;

import java.util.logging.Level;
import java.util.logging.Logger;
import logica.montecarlo.DatosMontecarlo;
import logica.montecarlo.ValoresInicialesSimulacion;
import logica.montecarlo.costoPedido.CostoPedido;
import logica.montecarlo.demanda.Demanda;
import logica.montecarlo.demora.Demora;
import logica.montecarlo.exceptions.ProbabilidadException;

/**
 *
 * @author heftyn
 */
public class Utilidades 
{
    public static ValoresInicialesSimulacion transformarAValoresInicialesSimulacion(int desde, int hasta) {
        
        return new ValoresInicialesSimulacion(desde, hasta, getDatosMontecarlo());
    }
    
    public static String formatRND(double rndDouble)
    {
        return "el RND en String - con 4? decimales esta bien";
    }
    
    public static String aEntero(double unDouble)
    {
        if(unDouble == Double.MAX_VALUE)
        {
            return "Infinito";
        }
        return "En realidad no eran con coma asiq chunk";
    }
    
    public static String aCosto(double unCosto)
    {
        return "Ponerle $ y negativo si es necesario. Solo dos decimales";
    }

    public static DatosMontecarlo getDatosMontecarlo() {
        DatosMontecarlo datos = new DatosMontecarlo();
        datos.setCostoFaltante(10);
        datos.setCostoMantenimiento(3);
        datos.setStockInicial(20);
        datos.setCostoPedido(getCostoPedido());
        datos.setDemanda(getDemanda());
        datos.setDemora(getDemora());
        
        return datos;
    }
    
    private static CostoPedido getCostoPedido()
    {
        CostoPedido costoPedido = new CostoPedido();
        costoPedido.agregarCosto(Double.valueOf(21), Double.valueOf(20));
        costoPedido.agregarCosto(Double.valueOf(41), Double.valueOf(25));
        costoPedido.agregarCosto(Double.MAX_VALUE, Double.valueOf(30));
        return costoPedido;
    }
    
    private static Demanda getDemanda()
    {
        Demanda demanda = new Demanda();
        
        try 
        {
            demanda.agregarDemanda(0.05, Double.valueOf(0));
            demanda.agregarDemanda(0.12, Double.valueOf(10));
            demanda.agregarDemanda(0.18, Double.valueOf(20));
            demanda.agregarDemanda(0.25, Double.valueOf(30));
            demanda.agregarDemanda(0.22, Double.valueOf(40));
            demanda.agregarDemanda(0.18, Double.valueOf(50));
        } catch (ProbabilidadException ex) 
        {
            Logger.getLogger(Utilidades.class.getName()).log(Level.SEVERE, null, ex);
        }
        return demanda;
    }
    
    private static Demora getDemora()
    {
        Demora demora = new Demora();
        try 
        {
            demora.agregarDemora(0.301, Double.valueOf(0));
            demora.agregarDemora(0.361, Double.valueOf(2));
            demora.agregarDemora(0.217, Double.valueOf(3));
            demora.agregarDemora(0.087, Double.valueOf(5));
            demora.agregarDemora(0.026, Double.valueOf(6));
            demora.agregarDemora(0.006, Double.valueOf(7));
            demora.agregarDemora(0.001, Double.valueOf(9));
        } catch (ProbabilidadException ex) 
        {
            Logger.getLogger(Utilidades.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return demora;
    }
}
