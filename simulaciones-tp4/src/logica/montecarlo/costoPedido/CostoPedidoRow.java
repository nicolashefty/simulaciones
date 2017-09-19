/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.montecarlo.costoPedido;

/**
 *
 * @author heftyn
 */
public class CostoPedidoRow implements Comparable<CostoPedidoRow>
{

    private double cantidadPedidaLimite;
    private double costo;
    
    public CostoPedidoRow(double cantidadPedidaLimite, double costo)
    {
        this.cantidadPedidaLimite = cantidadPedidaLimite;
        this.costo = costo;
    }
    
    public double getCantidadPedidaLimite()
    {
        return cantidadPedidaLimite;
    }
    
    public double getCosto()
    {
        return costo;
    }
    
    @Override
    public int compareTo(CostoPedidoRow o) 
    {
        if (o.costo == costo)
        {
            return 0;
        }
        else if (o.costo > costo)
        {
            return -1;
        }
        else
        {
            return 1;
        }
    }
}
