/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.servidores;

import logica.servidores.colas.Cola;
import logica.servidores.state.EstadoServidor;

/**
 *
 * @author heftyn
 */
public interface Servidor 
{
    EstadoServidor getEstado();
    void setEstado(EstadoServidor es);
    Cola getCola();
    void setCola(Cola c);

    String getNombre();
}
