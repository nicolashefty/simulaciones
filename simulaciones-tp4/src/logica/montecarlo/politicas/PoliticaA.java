/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.montecarlo.politicas;

import logica.montecarlo.DatosMontecarlo;
import logica.montecarlo.EstadoMontecarlo;
import logica.montecarlo.exceptions.ProbabilidadException;

/**
 *
 * @author heftyn
 */
public class PoliticaA implements IPolitica
{
    DatosMontecarlo datosMontecarlo;
    final int cantidadPedida = 7;
    
    @Override
    public void hacerPedido(EstadoMontecarlo[] vectorEstado, DatosMontecarlo datosMontecarlo) throws ProbabilidadException 
    {
        this.datosMontecarlo = datosMontecarlo;
        if(vectorEstado[0].isHayPedidoEnCurso())
        {
            hayPedidoEnCurso(vectorEstado);
        }
        else
        {
            noHayPedidoEnCurso(vectorEstado);
        }
        // Esta politica si no hay otro pedido en curso (columna dias restantes para que llegue el pedido to check)
        // te crea un pedido... y si es el dia de la llegada del pedido le va aponer la cantidad: 7
        
        //Manejar costo de pedido!
        
        // Manejar Demora!
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String toString()
    {
        return "<b>Politica de Stock A:</b>\n Efectuar un pedido cada 7 días \n"
                + "con una cantidad de 10 decenas\n ";
    }

    @Override
    public String getIDPolitica() {
        return IPolitica.A;
    }

    @Override
    public boolean calculaDemandaAcumulada() {
        return false;
    }

    @Override
    public double getDiasFaltantesParaPedirIniciales() {
        return 7;
    }

    private void hayPedidoEnCurso(EstadoMontecarlo[] vectorEstado) 
            throws ProbabilidadException 
    {
        //Pedido 1 llega hoy?
        if (vectorEstado[0].getDiaLlegadaPedido1() == vectorEstado[1].getDia())
        {
            llegaPedido(1, vectorEstado);
            //Llega pedido 1
            /*
            moverCantidad pedida 1 al stock
            limpiar cantidad pedida
            pedido 1 llega el dia->a 0
            */
            
            // Si no hay pedido 2, poner en false hayPedidoEnCurso
        }
        else if (vectorEstado[0].getDiaLlegadaPedido2() == vectorEstado[1].getDia())
        {
            //Llega pedido 2
            llegaPedido(2,vectorEstado);
            
        }
        //Si hay pedido por llegar en 1, arrastro
        if (vectorEstado[0].getDiaLlegadaPedido1() > 0 && 
                vectorEstado[0].getDiaLlegadaPedido1() != vectorEstado[1].getDia())
        {
            vectorEstado[1].setHayPedidoEnCurso(true);
            vectorEstado[1].setCantPedida1(vectorEstado[0].getCantPedida1());
            vectorEstado[1].setDiaLlegadaPedido1(vectorEstado[0].getDiaLlegadaPedido1());
        }
        //Si hay pedido por llegar en 2, arrastro
        if (vectorEstado[0].getDiaLlegadaPedido2() > 1 &&
                vectorEstado[0].getDiaLlegadaPedido2() != vectorEstado[1].getDia())
        {
            vectorEstado[1].setHayPedidoEnCurso(true);
            vectorEstado[1].setCantPedida2(vectorEstado[0].getCantPedida2());
            vectorEstado[1].setDiaLlegadaPedido2(vectorEstado[0].getDiaLlegadaPedido2());
        }
        if(tengoQuePedir(1,vectorEstado))
        {
            hacerPedido(1,vectorEstado);
        }
        
//        else
//        {
//            vectorEstado[1].setHayPedidoEnCurso(false);
//        }
    }

    private void llegaPedido(int from, EstadoMontecarlo[] vectorEstado) 
    {
        /*
            moverCantidad pedida "from" al stock
            limpiar cantidad pedida
            pedido "from" llega el dia-> N/A? o a 0
            */
        vectorEstado[1].setStock(vectorEstado[1].getStock() + vectorEstado[0].getCantPedida1());
        
        switch(from)
        {
            case 1:
            {
                vectorEstado[0].setCantPedida1(0);
                vectorEstado[0].setDiaLlegadaPedido1(0);
                break;
            }
            case 2:
            {
                vectorEstado[0].setCantPedida2(0);
                vectorEstado[0].setDiaLlegadaPedido2(0);
                break;
            }
        }
    }

    private boolean tengoQuePedir(int i, EstadoMontecarlo[] vectorEstado) 
    {
        switch ((int)vectorEstado[0].getDiasFaltantesParaPedir()) {
            case 1:
                vectorEstado[1].setDiasFaltantesParaPedir(0);
                return true;
            case 0:
                vectorEstado[1].setDiasFaltantesParaPedir(getDiasFaltantesParaPedirIniciales());
                break;
            default:
                vectorEstado[1].setDiasFaltantesParaPedir(vectorEstado[0].getDiasFaltantesParaPedir() -1);
                break;
        }
        return false;
    }

    private void hacerPedido(int i, EstadoMontecarlo[] vectorEstado) 
            throws ProbabilidadException             
    {
        
        vectorEstado[1].setRndDemora(Math.random());
        vectorEstado[1].setDiasDemora(datosMontecarlo.getDemoraParaRandom(vectorEstado[1].getRndDemora()));
        
        if(vectorEstado[0].getDiaLlegadaPedido1()>1)
        {
            //Hay pedido en curso (Pedido1) 
            //Si es 1 significa que llega hoy puedo usar el espacio
            vectorEstado[1].setDiaLlegadaPedido2(vectorEstado[1].getDia() + (int)vectorEstado[1].getDiasDemora());
            vectorEstado[1].setCantPedida2(cantidadPedida);
            
            //Arrastro lo del pedido 1
            vectorEstado[1].setDiaLlegadaPedido1(vectorEstado[0].getDiaLlegadaPedido1() -1);
            vectorEstado[1].setCantPedida1(vectorEstado[1].getCantPedida1());
        }
        else
        {
            vectorEstado[1].setDiaLlegadaPedido1(vectorEstado[1].getDia() + (int)vectorEstado[1].getDiasDemora());
            vectorEstado[1].setCantPedida1(cantidadPedida);
            
            if(vectorEstado[0].getDiaLlegadaPedido2()>1)
            {
                //Arrastro lo del pedido 2
                vectorEstado[1].setDiaLlegadaPedido2(vectorEstado[0].getDiaLlegadaPedido2() -1);
                vectorEstado[1].setCantPedida2(vectorEstado[1].getCantPedida2());
            }
        }
        vectorEstado[1].setHayPedidoEnCurso(true);
        agregarCostoPedido(vectorEstado);
    }

    private void agregarCostoPedido(EstadoMontecarlo[] vectorEstado) 
            throws ProbabilidadException 
    {
        vectorEstado[1].setCostoPorPedido(datosMontecarlo.getCostoParaCantidad(cantidadPedida));
    }

    private void noHayPedidoEnCurso(EstadoMontecarlo[] vectorEstado) 
            throws ProbabilidadException 
    {
        if(tengoQuePedir(0, vectorEstado))
        {
            hacerPedido(0, vectorEstado);
        }
    }
}
