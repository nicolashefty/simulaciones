/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.montecarlo.demanda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import logica.montecarlo.exceptions.ProbabilidadException;

/**
 *
 * @author heftyn
 */
public class Demanda {
    private List<DemandaRow> mapaProbabilidades;
    
    public void agregarDemanda(Double probabilidad, Double demanda) throws ProbabilidadException
    {
        if (mapaProbabilidades == null)
        {
            mapaProbabilidades = new ArrayList<>();
        }
        if(sePuedeAgregar(probabilidad))
        {
            agregar(probabilidad, demanda);
        }
        else
        {
            throw new ProbabilidadException("No se puede agregar la probabilidad de la demanda: "+ demanda);
        }
    }

    private boolean sePuedeAgregar(Double probabilidad) 
    {
        return !(probabilidad > 1 || probabilidad < 0);
    }

    private void agregar(Double probabilidad, Double demanda)
    {
        double probabilidadAcumulada = probabilidad;
        if (mapaProbabilidades.size() > 0)
        {
            probabilidadAcumulada = 
                    calcularProbabilidadAcumulada(mapaProbabilidades.get(mapaProbabilidades.size()-1), probabilidad);
        }
        mapaProbabilidades.add(new DemandaRow(probabilidadAcumulada, demanda));
    }

    private void agregarOrdenado(Double probabilidad, Double demanda) 
    {
        //Ordenar
        Collections.sort(mapaProbabilidades);
        //Agregar
        int i = 0;
        int indiceDondePoner = mapaProbabilidades.size();
        double probabilidadAcumulada = probabilidad;
        for (DemandaRow cpr : mapaProbabilidades)
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
            mapaProbabilidades.add(new DemandaRow(probabilidadAcumulada, demanda));
        }
        else
        {
            mapaProbabilidades.set(indiceDondePoner, new DemandaRow(probabilidadAcumulada, demanda));
        }
    }

    /**
     * Te devuelve la probabilidad acumulada
     * @param cpr
     * @param probabilidad
     * @return 
     */
    private double calcularProbabilidadAcumulada(DemandaRow cpr, Double probabilidad) 
    {
        if (cpr == null)
        {
            return probabilidad;
        }
        
        return cpr.getProbabilidad() + probabilidad;
    }
    
    public double getDemandaParaRandom(double rnd)
            throws ProbabilidadException
    {
        DemandaRow retDemanda = null;
        //Arrancamos a iterar la lista de abajo para arriba
        for (int i = mapaProbabilidades.size() - 1; i >= 0; i--)
        {
            DemandaRow demanda = mapaProbabilidades.get(i);
            if (rnd <= demanda.getProbabilidad())
            {
                retDemanda = demanda;
            }
        }
        
        if (retDemanda == null)
        {
            //Si por alguna razon no se encuentra tiramos el error
            throw new DemandaNotFoundException("No se encuentra demanda para "+ rnd);
        }
        return retDemanda.getDemanda();
    }
    
    private class DemandaNotFoundException extends ProbabilidadException
    {
        DemandaNotFoundException(String msg)
        {
            super(msg);
        }
    }
}
