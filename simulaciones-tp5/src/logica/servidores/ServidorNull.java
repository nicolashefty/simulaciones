/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.servidores;

import logica.servidores.colas.Cola;
import logica.servidores.exceptions.NecesitaCalcularRNDDarsena;
import logica.servidores.exceptions.NecesitaCalcularRNDInicioAtencion;
import logica.servidores.exceptions.NecesitaCalcularRNDPesaje;
import logica.servidores.exceptions.NoAtendidos;
import logica.servidores.exceptions.TieneQueCalibrar;
import logica.servidores.state.EstadoServidor;

/**
 *
 * @author heftyn
 */
public class ServidorNull implements Servidor
{

    @Override
    public EstadoServidor getEstado() 
    {
        return new EstadoNull();
    }

    @Override
    public void setEstado(EstadoServidor es) {
        //N/A
    }

    @Override
    public Cola getCola() {
        return new Cola();
    }

    @Override
    public void setCola(Cola c) {
        //N/A
    }

    @Override
    public Servidor clone() {
        return new ServidorNull();
    }

    @Override
    public String getNombre() {
        return "Ninguno";
    }
    
    public class EstadoNull implements EstadoServidor
    {

        @Override
        public String getNombre() {
            return "Ninguno";
        }

        @Override
        public void apertura(Servidor s) {
        }

        @Override
        public void cierre(Servidor s) throws NoAtendidos {
        }

        @Override
        public void inicioAtencionRecepcion(Servidor s) throws NecesitaCalcularRNDInicioAtencion {
        }

        @Override
        public void finAtencionRecepcion(Servidor s) throws NecesitaCalcularRNDInicioAtencion {
        }

        @Override
        public void inicioCalibrado(Servidor s) {
        }

        @Override
        public void finCalibrado(Servidor s) throws NecesitaCalcularRNDDarsena {
        }

        @Override
        public void inicioDescarga(Servidor s) throws NecesitaCalcularRNDDarsena {
        }

        @Override
        public void finDescarga(Servidor s) throws TieneQueCalibrar, NecesitaCalcularRNDDarsena {
        }

        @Override
        public void inicioPesaje(Servidor s) throws NecesitaCalcularRNDPesaje {
        }

        @Override
        public void finPesaje(Servidor s) throws NecesitaCalcularRNDPesaje {
        }

        @Override
        public boolean esLibre() {
            return false;
        }

        @Override
        public boolean esOcupado() {
            return false;
        }

        @Override
        public EstadoServidor clone() {
            return new EstadoNull();
        }
        
    }
}
