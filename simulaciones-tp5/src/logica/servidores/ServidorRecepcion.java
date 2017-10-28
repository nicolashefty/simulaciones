/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.servidores;

import logica.servidores.colas.Cola;
import logica.servidores.exceptions.NecesitaCalcularRNDInicioAtencion;
import logica.servidores.exceptions.NoAtendidos;
import logica.servidores.state.EstadoServidor;

/**
 *
 * @author heftyn
 */
public class ServidorRecepcion implements Servidor
{

    private EstadoServidor estado;
    private Cola cola;
    
    public ServidorRecepcion()
    {
        cola = new Cola();
        estado = new EstadoRecepcionLibre();
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
        return "Recepcion";
    }

    @Override
    public Servidor clone() 
    {
        ServidorRecepcion clon = new ServidorRecepcion();
        
        clon.setEstado(estado.clone());
        clon.setCola(cola.clone());
        
        return clon;
    }
    
    public abstract class EstadoRecepcion implements EstadoServidor
    {
          
        @Override
        public abstract EstadoServidor clone();
        
        @Override
        public void apertura(Servidor s) 
        {
            s.getCola().inicializar();
        }

        @Override
        public void cierre(Servidor s) 
        throws NoAtendidos
        {
            //Debiera setear los NO atendidos basado en su cola
            throw new NoAtendidos(s.getCola().getCola());
        }

        @Override
        public void inicioCalibrado(Servidor s) {
        }

        @Override
        public void finCalibrado(Servidor s) {
        }

        @Override
        public void inicioDescarga(Servidor s) {
        }

        @Override
        public void finDescarga(Servidor s) {
        }

        @Override
        public void inicioPesaje(Servidor s) {
        }

        @Override
        public void finPesaje(Servidor s) {
        }
        
    }
    
    public class EstadoRecepcionOcupada extends EstadoRecepcion {

        @Override
        public String getNombre() {
            return "Ocupada";
        }

        @Override
        public void inicioAtencionRecepcion(Servidor s) throws NecesitaCalcularRNDInicioAtencion{
            //sigue ocupada
            s.getCola().incrementarCola();
        }

        @Override
        public void finAtencionRecepcion(Servidor s) throws NecesitaCalcularRNDInicioAtencion{
            //si no hay mas en la cola setear a libre

            if (s.getCola().getCola() == 0) {
                s.setEstado(new EstadoRecepcionLibre());
            }
            else
            {
                //Sigue atendiendo:
                cola.disminuirCola();
                throw new NecesitaCalcularRNDInicioAtencion();
            }
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
            return new EstadoRecepcionOcupada();
        }
      
    }

    public class EstadoRecepcionLibre extends EstadoRecepcion {

        @Override
        public String getNombre() {
            return "Libre";
        }

        @Override
        public void inicioAtencionRecepcion(Servidor s) throws NecesitaCalcularRNDInicioAtencion
        {
            s.setEstado(new EstadoRecepcionOcupada());
            throw new NecesitaCalcularRNDInicioAtencion();
        }

        @Override
        public void finAtencionRecepcion(Servidor s) {
            //No deberia pasar
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
            return new EstadoRecepcionLibre();
        }

    }
}
