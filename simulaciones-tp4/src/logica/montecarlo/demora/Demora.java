/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.montecarlo.demora;

import java.util.*;
import logica.montecarlo.exceptions.ProbabilidadException;

/**
 *
 * @author heftyn
 */
public class Demora {
    private List<DemoraRow> mapaProbabilidades;
    
    public void agregarDemora(Double probabilidad, Double demora) throws ProbabilidadException
    {
        if (mapaProbabilidades == null)
        {
            mapaProbabilidades = new ArrayList<>();
        }
        if(sePuedeAgregar(probabilidad))
        {
//            agregarOrdenado(probabilidad, demora);
            agregar(probabilidad, demora);
        }
        else
        {
            throw new ProbabilidadException("No se puede agregar la probabilidad de la demora: "+ demora);
        }
    }

    private boolean sePuedeAgregar(Double probabilidad) 
    {
        return !(probabilidad > 1 || probabilidad < 0);
    }

    private void agregar(Double probabilidad, Double demora)
    {
        double probabilidadAcumulada = probabilidad;
        if (mapaProbabilidades.size() > 0)
        {
            probabilidadAcumulada = 
                    calcularProbabilidadAcumulada(mapaProbabilidades.get(mapaProbabilidades.size()-1), probabilidad);
        }
        mapaProbabilidades.add(new DemoraRow(probabilidadAcumulada, demora));
    }
    
    /**NO ES NECESARIO*/
    private void agregarOrdenado(Double probabilidad, Double demora) 
    {
        //Ordenar
        Collections.sort(mapaProbabilidades);
        //Agregar
        int i = 0;
        int indiceDondePoner = mapaProbabilidades.size();
        double probabilidadAcumulada = probabilidad;
        for (DemoraRow cpr : mapaProbabilidades)
        {
            if (cpr.getProbabilidad() >= probabilidad)
            {
                indiceDondePoner = i;
                probabilidadAcumulada = calcularProbabilidadAcumulada(cpr,probabilidad);
            }
            
            i++;
        }
        if(indiceDondePoner == mapaProbabilidades.size())
        {
            mapaProbabilidades.add(new DemoraRow(probabilidadAcumulada, demora));
        }
        else
        {
            mapaProbabilidades.set(indiceDondePoner, new DemoraRow(probabilidadAcumulada, demora));
        }
    }

    /**
     * Te devuelve la probabilidad acumulada
     * @param cpr
     * @param probabilidad
     * @return 
     */
    private double calcularProbabilidadAcumulada(DemoraRow cpr, Double probabilidad) 
    {
        if (cpr == null)
        {
            return probabilidad;
        }
        
        return cpr.getProbabilidad() + probabilidad;
    }
    
    public double getDemoraParaRandom(double rnd)
        throws ProbabilidadException
    {
        DemoraRow retDemora = null;
        //Arrancamos a iterar la lista de abajo para arriba
        for (int i = mapaProbabilidades.size() - 1; i >= 0; i--)
        {
            DemoraRow demora = mapaProbabilidades.get(i);
            if (rnd <= demora.getProbabilidad())
            {
                retDemora = demora;
            }
        }
        
        if (retDemora == null)
        {
            //Si por alguna razon no se encuentra tiramos el error
            throw new DemoraNotFoundException("No se encuentra demora para "+ rnd);
        }
        return retDemora.getDemora();
    }
    
    private class DemoraNotFoundException extends ProbabilidadException
    {
        DemoraNotFoundException(String msg)
        {
            super(msg);
        }
    }
}
