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
public interface IPolitica {
    String A = "A";
    String B = "B";
    String ALTR = "Alternativa";
    
    String getIDPolitica();
    void hacerPedido(EstadoMontecarlo[] vectorEstado);
}
