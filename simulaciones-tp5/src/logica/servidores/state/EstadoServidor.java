/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.servidores.state;

import logica.servidores.Servidor;
import logica.servidores.exceptions.*;

/**
 *
 * @author heftyn
 */
public interface EstadoServidor 
{
    String getNombre();
    
    void apertura(Servidor s);
    void cierre(Servidor s) throws NoAtendidos;
    void inicioAtencionRecepcion(Servidor s) throws NecesitaCalcularRNDInicioAtencion;
    void finAtencionRecepcion(Servidor s) throws NecesitaCalcularRNDInicioAtencion;
    void inicioCalibrado(Servidor s);
    void finCalibrado(Servidor s) throws NecesitaCalcularRNDDarsena;
    void inicioDescarga(Servidor s) throws NecesitaCalcularRNDDarsena;
    void finDescarga(Servidor s) throws TieneQueCalibrar, NecesitaCalcularRNDDarsena;
    void inicioPesaje(Servidor s) throws NecesitaCalcularRNDPesaje;
    void finPesaje(Servidor s) throws NecesitaCalcularRNDPesaje;  

    public boolean esLibre();
    public boolean esOcupado();
}
