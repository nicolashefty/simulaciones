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
        
    }
    public class EstadoDarsenaCalibrando extends EstadoDarsena
    {

        @Override
        public String getNombre() 
        {
            return "Calibrando";
        }

        @Override
        public void finCalibrado(Servidor s) 
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
