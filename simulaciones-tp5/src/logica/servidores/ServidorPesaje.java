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
======== Seguir desde aca para abajo.
        
        @Override
        public void finCalibrado(Servidor s) 
        {
            //No hace nada
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
        public void finDescarga(Servidor s) 
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void finPesaje(Servidor s) 
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        public void finCalibrado(Servidor s) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void finDescarga(Servidor s) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void finPesaje(Servidor s) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        public void finCalibrado(Servidor s) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void finDescarga(Servidor s) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void finPesaje(Servidor s) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }        
    }
}
