/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.servidores;

import logica.servidores.colas.Cola;
import logica.servidores.exceptions.TieneQueCalibrar;
import logica.servidores.state.EstadoServidor;

/**
 *
 * @author heftyn
 */
public class ServidorDarsena implements Servidor
{

    private EstadoServidor estado;
    private int cantAtendidos;
    private static Cola cola;
    
    public ServidorDarsena()
    {
        estado = new EstadoDarsenaLibre();
        cantAtendidos = 0;
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

    public int getCantAtendidos() {
        return cantAtendidos;
    }

    public void setCantAtendidos(int cantAtendidos) {
        this.cantAtendidos = cantAtendidos;
    }
    
    @Override
    public String getNombre() {
        return "Darsena";
    }
    
    public abstract class EstadoDarsena implements EstadoServidor
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
        public void inicioAtencionRecepcion(Servidor s) {
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
        public void finDescarga(Servidor s) throws TieneQueCalibrar{
        }

        @Override
        public void inicioPesaje(Servidor s) {
        }

        @Override
        public void finPesaje(Servidor s) {
        }
        
    }
    public class EstadoDarsenaCalibrando extends EstadoDarsena
    {

        @Override
        public String getNombre() 
        {
            return "Calibrando";
        }

        @Override
        public void inicioCalibrado(Servidor s) {
            //No deberia pasar
        }

        @Override
        public void finCalibrado(Servidor s) {
            if(s.getCola().getCola() > 0)
            {
                s.getCola().disminuirCola();
                s.setEstado(new EstadoDarsenaOcupada());
            }
            else
            {
                s.setEstado(new EstadoDarsenaLibre());
            }
            cantAtendidos = 0;
        }

        @Override
        public void inicioDescarga(Servidor s) {
        }

        @Override
        public void finDescarga(Servidor s) {
        }


    }
    
    public class EstadoDarsenaOcupada extends EstadoDarsena
    {

        @Override
        public String getNombre() 
        {
            return "Ocupada";
        }
        
        @Override
        public void inicioCalibrado(Servidor s) {
            s.setEstado(new EstadoDarsenaCalibrando());
        }

        @Override
        public void finCalibrado(Servidor s) {
        }

        @Override
        public void inicioDescarga(Servidor s) {
            s.getCola().disminuirCola();
            cantAtendidos ++;
        }

        @Override
        public void finDescarga(Servidor s) 
        throws TieneQueCalibrar{
            
            cantAtendidos++;
            if (cantAtendidos == 15)
            {
                throw new TieneQueCalibrar();
            }
            if(s.getCola().getCola() > 0)
            {
                s.getCola().disminuirCola();
            }
            else
            {
                s.setEstado(new EstadoDarsenaLibre());
            }
        }

    }
    
    public class EstadoDarsenaLibre extends EstadoDarsena
    {

        @Override
        public String getNombre() 
        {
            return "Libre";
        }
     
        @Override
        public void inicioCalibrado(Servidor s) {
            
        }

        @Override
        public void finCalibrado(Servidor s) {
        }

        @Override
        public void inicioDescarga(Servidor s) {
            s.getCola().disminuirCola();
            s.setEstado(new EstadoDarsenaOcupada());
        }

        @Override
        public void finDescarga(Servidor s) {
        }

    }
}
