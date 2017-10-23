/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.servidores;

import logica.servidores.colas.Cola;
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
    
    public abstract class EstadoRecepcion implements EstadoServidor
    {
          
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
        
        public class EstadoRecepcionOcupada extends EstadoRecepcion
        {

            @Override
            public String getNombre() {
                return "Ocupada";
            }

            @Override
            public void inicioAtencionRecepcion(Servidor s) {
                //sigue ocupada
                s.getCola().disminuirCola();
            }

            @Override
            public void finAtencionRecepcion(Servidor s) {
                //si no hay mas en la cola setear a libre
                
                if (s.getCola().getCola() == 0)
                {
                    s.setEstado(new EstadoRecepcionLibre());
                }
            }
            
        }
        
        public class EstadoRecepcionLibre extends EstadoRecepcion
        {

            @Override
            public String getNombre() {
                return "Libre";
            }

            @Override
            public void inicioAtencionRecepcion(Servidor s) {
                s.setEstado(new EstadoRecepcionOcupada());
                s.getCola().disminuirCola();
            }

            @Override
            public void finAtencionRecepcion(Servidor s) {
                //No deberia pasar
            }
            
        }
    }
}
