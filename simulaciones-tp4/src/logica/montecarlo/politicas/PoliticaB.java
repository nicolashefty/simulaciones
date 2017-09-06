/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.montecarlo.politicas;

import logica.montecarlo.EstadoMontecarlo;

/**
 *
 * @author heftyn
 */
public class PoliticaB implements IPolitica{

    @Override
    public void hacerPedido(EstadoMontecarlo[] vectorEstado) {
        //Esta politica en cambio, hace pedidos cada 10 dias.
        // se pide en relacion a la
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String toString()
    {
        return "<b>Politica de Stock B:</b>\n Efectuar un pedido cada 10 días con una cantidad\n "
                + "igual a la demanda en los 10 días anteriores\n"
                + "(incluido el día que se hace el pedido)\n";
    }

    @Override
    public String getIDPolitica() {
        return IPolitica.B;
    }
}
