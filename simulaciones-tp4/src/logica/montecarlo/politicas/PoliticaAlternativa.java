/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.montecarlo.politicas;

import logica.montecarlo.DatosMontecarlo;
import logica.montecarlo.EstadoMontecarlo;

/**
 *
 * @author heftyn
 */
public class PoliticaAlternativa implements IPolitica{

    
    @Override
    public void hacerPedido(EstadoMontecarlo[] vectorEstado, DatosMontecarlo datosMontecarlo) {
        //TODO: To think about :P
        
        //Manejar costo de pedido!
        
        // Manejar Demora!
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String toString()
    {
        //TODO:
        return "<b>Politica de Stock B:</b>\n Efectuar un pedido cada 10 días con una cantidad\n "
                + "igual a la demanda en los 10 días anteriores\n"
                + "(incluido el día que se hace el pedido)\n";
    }

    @Override
    public String getIDPolitica() {
        return IPolitica.ALTR;
    }

    @Override
    public boolean calculaDemandaAcumulada() {
        return false;
    }

    @Override
    public double getDiasFaltantesParaPedirIniciales() {
        return 1;
    }
}
