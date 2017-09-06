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
            agregarOrdenado(probabilidad, demora);
        }
        else
        {
            throw new ProbabilidadException("No se puede agregar la probabilidad de la demora: "+ demora);
        }
    }

    private boolean sePuedeAgregar(Double probabilidad) 
    {
        if (probabilidad > 1 || probabilidad < 0)
        {
            return false;
        }
        return true;
    }

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
            if (cpr.getDemora() >= demora)
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
}
