/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.servidores;

import logica.servidores.colas.Cola;
import logica.servidores.exceptions.NecesitaCalcularRNDPesaje;
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

    @Override
    public Servidor clone() 
    {
        Servidor clon = new ServidorPesaje();
        
        clon.setEstado(estado.clone());
        clon.setCola(cola.clone());
        
        return clon;
    }
    
    public abstract class EstadoPesaje implements EstadoServidor
    {
        @Override
        public abstract EstadoServidor clone();
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
        public void finPesaje(Servidor s) throws NecesitaCalcularRNDPesaje 
        {
            //Si no hay mas en la cola paso a Libre
            if (cola.getCola() == 0)
            {
                estado = new EstadoPesajeLibre();
            }
            else
            {
                cola.disminuirCola();
                throw new NecesitaCalcularRNDPesaje();
            }
        }

        @Override
        public void inicioPesaje(Servidor s) {
            //Sigue ocupado
            //este metodo podria ser llamado para agregar a la cola
            cola.incrementarCola();
        }

        @Override
        public boolean esLibre() {
            return false;
        }

        @Override
        public boolean esOcupado() {
            return true;
        }

        @Override
        public EstadoServidor clone() 
        {
            return new EstadoPesajeOcupado();
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
        public void inicioPesaje(Servidor s) throws NecesitaCalcularRNDPesaje
        {
            s.setEstado(new EstadoPesajeOcupado());
            throw new NecesitaCalcularRNDPesaje();
            //disminiur la cola?
        }

        @Override
        public boolean esLibre() {
            return true;
        }

        @Override
        public boolean esOcupado() {
            return false;
        }

        @Override
        public EstadoServidor clone() 
        {
            return new EstadoPesajeLibre();
        }
    }
}
