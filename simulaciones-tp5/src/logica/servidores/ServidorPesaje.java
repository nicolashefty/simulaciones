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
public class ServidorPesaje implements Servidor
{

    EstadoServidor estado;
    Cola cola;
    
    public ServidorPesaje()
    {
        estado = new EstadoPesajeLibre();
        cola = new Cola();
    }
    
    @Override
    public EstadoServidor getEstado() {
        return estado;
    }

    @Override
    public void setEstado(EstadoServidor es) {
        estado = es;
    }

    @Override
    public Cola getCola() {
        return cola;
    }

    @Override
    public void setCola(Cola c) {
        cola = c;
    }

    @Override
    public String getNombre() {
        return "Pesaje";
    }
    
    public abstract class EstadoPesaje implements EstadoServidor
    {
        
        @Override
        public void apertura(Servidor s) 
        {
            s.getCola().inicializar();
        }

        @Override
        public void cierre(Servidor s) {
            //No hace nada
        }
        
        @Override
        public void finAtencionRecepcion(Servidor s) {
            //No hace nada
        }
        
        @Override
        public void finCalibrado(Servidor s) 
        {
            //No hace nada
        }
        
        @Override
        public void finDescarga(Servidor s) {
            //NO hace nada
        }
        
        @Override
        public void inicioAtencionRecepcion(Servidor s) {
            //No hace nada;
        }
        
        @Override
        public void inicioCalibrado(Servidor s) {
        }

        @Override
        public void inicioDescarga(Servidor s) {
        }

    }
    
    public class EstadoPesajeOcupado extends EstadoPesaje
    {

        @Override
        public String getNombre() 
        {
            return "Ocupado";
        }

        @Override
        public void finPesaje(Servidor s) {
            //Si no hay mas en la cola paso a Libre
            
        }

        @Override
        public void inicioPesaje(Servidor s) {
            //Sigue ocupado
        }

    }
    
    public class EstadoPesajeLibre extends EstadoPesaje
    {

        @Override
        public String getNombre() 
        {
            return "Libre";
        }

        @Override
        public void finPesaje(Servidor s) {
            //No deberia pasar
        }

        @Override
        public void inicioPesaje(Servidor s) {
            s.setEstado(new EstadoPesajeOcupado());
            //disminiur la cola?
        }
    }
}
