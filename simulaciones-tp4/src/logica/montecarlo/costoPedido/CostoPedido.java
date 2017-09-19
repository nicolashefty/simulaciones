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
        agregar(cantidadPedidaLimite, costo);
    }

    private void agregar(Double cantidadPedidaLimite, Double costo) 
    {
        mapaProbabilidades.add(new CostoPedidoRow(cantidadPedidaLimite, costo));
    }
    
    public double getCostoParaCantidad(double cant)
        throws ProbabilidadException
    {
        CostoPedidoRow retCosto = null;
        //Arrancamos a iterar la lista de abajo para arriba
        for (int i = mapaProbabilidades.size() - 1; i >= 0; i--)
        {
            CostoPedidoRow costo = mapaProbabilidades.get(i);
            if (cant <= costo.getCantidadPedidaLimite())
            {
                retCosto = costo;
            }
        }
        
        if (retCosto == null)
        {
            //Si por alguna razon no se encuentra tiramos el error
            throw new CostoNotFoundException("No se encuentra demora para "+ cant);
        }
        return retCosto.getCosto();
    }
    
    private class CostoNotFoundException extends ProbabilidadException
    {
        CostoNotFoundException(String msg)
        {
            super(msg);
        }
    }
}
