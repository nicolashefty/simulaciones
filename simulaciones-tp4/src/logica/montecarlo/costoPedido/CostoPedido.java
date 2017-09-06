/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.montecarlo.costoPedido;

import java.util.*;
import logica.montecarlo.exceptions.ProbabilidadException;

/**
 *
 * @author heftyn
 */
public class CostoPedido 
{
    private List<CostoPedidoRow> mapaProbabilidades;
    
    public void agregarCosto(Double cantidadPedidaLimite, Double costo)
    {
        if (mapaProbabilidades == null)
        {
            mapaProbabilidades = new ArrayList<>();
        }
        agregarOrdenado(cantidadPedidaLimite, costo);
    }

    private void agregarOrdenado(Double cantidadPedidaLimite, Double costo) 
    {
        //Ordenar
        Collections.sort(mapaProbabilidades);
        //Agregar
        int i = 0;
        int indiceDondePoner = mapaProbabilidades.size();
        double probabilidadAcumulada = cantidadPedidaLimite;
        for (CostoPedidoRow cpr : mapaProbabilidades)
        {
            if (cpr.getCantidadPedidaLimite() >= costo)
            {
                indiceDondePoner = i;
                probabilidadAcumulada = calcularProbabilidadAcumulada(cpr,cantidadPedidaLimite);
            }
            
            i++;
        }
        if(indiceDondePoner == mapaProbabilidades.size())
        {
            mapaProbabilidades.add(new CostoPedidoRow(probabilidadAcumulada, costo));
        }
        else
        {
            mapaProbabilidades.set(indiceDondePoner, new CostoPedidoRow(probabilidadAcumulada, costo));
        }
    }

    /**
     * Te devuelve la probabilidad acumulada
     * @param cpr
     * @param probabilidad
     * @return 
     */
    private double calcularProbabilidadAcumulada(CostoPedidoRow cpr, Double probabilidad) 
    {
        if (cpr == null)
        {
            return probabilidad;
        }
        
        return cpr.getProbabilidad() + probabilidad;
    }
}
