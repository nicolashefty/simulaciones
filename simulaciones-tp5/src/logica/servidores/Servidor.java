/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.servidores;

import logica.servidores.colas.Cola;
import logica.servidores.exceptions.*;
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
    
    default void apertura()
    {
        getEstado().apertura(this);
    }
    default void cierre() throws NoAtendidos
    {
        getEstado().cierre(this);
    }
    default void inicioAtencionRecepcion()
    {
        getEstado().inicioAtencionRecepcion(this);
    }
    default void finAtencionRecepcion()
    {
        getEstado().finAtencionRecepcion(this);
    }
    default void inicioCalibrado()
    {
        getEstado().inicioCalibrado(this);
    }
    default void finCalibrado()
    {
        getEstado().finCalibrado(this);
    }
    default void inicioDescarga() throws NecesitaCalcularRNDDarsena
    {
        getEstado().inicioDescarga(this);
    }
    default void finDescarga() throws TieneQueCalibrar, NecesitaCalcularRNDDarsena
    {
        getEstado().finDescarga(this);
    }
    default void inicioPesaje()
    {
        getEstado().inicioPesaje(this);
    }
    default void finPesaje() throws NecesitaCalcularRNDPesaje
    {
        getEstado().finPesaje(this);
    } 
    default boolean estaLibre()
    {
        return getEstado().esLibre();
    }
    default boolean estaOcupado()
    {
        return getEstado().esOcupado();
    }
}
