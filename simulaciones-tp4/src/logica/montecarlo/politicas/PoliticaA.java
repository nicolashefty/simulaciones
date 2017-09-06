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
public class PoliticaA implements IPolitica
{
    
    @Override
    public void hacerPedido(EstadoMontecarlo[] vectorEstado) {
        // Esta politica si no hay otro pedido en curso (columna dias restantes para que llegue el pedido to check)
        // te crea un pedido... y si es el dia de la llegada del pedido le va aponer la cantidad: 7
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
}
