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
    void inicioAtencionRecepcion(Servidor s);
    void finAtencionRecepcion(Servidor s);
    void inicioCalibrado(Servidor s);
    void finCalibrado(Servidor s);
    void inicioDescarga(Servidor s);
    void finDescarga(Servidor s) throws TieneQueCalibrar;
    void inicioPesaje(Servidor s);
    void finPesaje(Servidor s);  
}
