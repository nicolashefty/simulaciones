/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.clientes;

import java.time.LocalTime;
import logica.servidores.Servidor;
import logica.servidores.ServidorNull;

/**
 *
 * @author heftyn
 */
public class Camion {
    
    /**
     * No atendidos serian aquellos que estaban en la cola de recepcion al momento de cierre.
     */
    private EstadoCamion estado;
    private Servidor servidor;
    private LocalTime tiempoLlegada;
    private LocalTime tiempoFin;

    public EstadoCamion getEstado() {
        return estado;
    }

    public void setEstado(EstadoCamion estado) {
        this.estado = estado;
    }

    public Servidor getServidor() {
        return servidor;
    }

    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }

    public LocalTime getTiempoLlegada() {
        return tiempoLlegada;
    }

    public void setTiempoLlegada(LocalTime tiempoLlegada) {
        this.tiempoLlegada = tiempoLlegada;
    }

    public LocalTime getTiempoFin() {
        return tiempoFin;
    }

    public void setTiempoFin(LocalTime tiempoFin) {
        this.tiempoFin = tiempoFin;
    }
    
    public void inicioAtencionRecepcion() {
        estado.inicioAtencionRecepcion();
    }

    public void inicioAtencionDarsena1() {
        estado.inicioAtencionDarsena1();
    }

    public void inicioAtencionDarsena2() {
        estado.inicioAtencionDarsena2();
    }

    public void inicioAtencionPesaje() {
        estado.inicioAtencionPesaje();
    }

    public void finAtencionRecepcion() {
        estado.finAtencionRecepcion();
    }

    public void finAtencionDarsena1() {
        estado.finAtencionDarsena1();
    }

    public void finAtencionDarsena2() {
        estado.finAtencionDarsena2();
    }

    public void finAtencionPesaje() {
        estado.finAtencionPesaje();
    }

    public boolean estaFueraDelSistema() {
        return estado.esFueraDelSistema();
    }

    public Servidor getServidorAtendido() 
    {
        if (servidor == null)
        {
            return new ServidorNull();
        }
        return servidor;
    }

    public void llegadaCamion() {
        setEstado(new EstadoCamionColaRecepcion());
    }
    
    
    
    
    public abstract class EstadoCamion
    {
        public abstract String toString();
        public void inicioAtencionRecepcion()
        {
            
        }
        public void inicioAtencionDarsena1()
        {
            
        }
        public void inicioAtencionDarsena2()
        {
            
        }
        public void inicioAtencionPesaje()
        {
            
        }
        public void finAtencionRecepcion()
        {
            
        }
        public void finAtencionDarsena1()
        {
            
        }
        public void finAtencionDarsena2()
        {
            
        }
        public void finAtencionPesaje()
        {
            
        }

        private boolean esFueraDelSistema()
        {
            return false;
        }
    }
   
    public class EstadoCamionColaRecepcion extends EstadoCamion
    {
        @Override
        public void inicioAtencionRecepcion()
        {
            setEstado(new EstadoCamionRecepcion());
        }

        @Override
        public String toString() {
            return "Esperando para Recepcion";
        }

    }
    
    public class EstadoCamionRecepcion extends EstadoCamion
    {
        @Override
        public void finAtencionRecepcion()
        {
            setEstado(new EstadoCamionColaPesaje());
        }

        @Override
        public String toString() {
            return "Siendo atendido por Recepcion";
        }
    }
    public class EstadoCamionColaPesaje extends EstadoCamion
    {
        @Override
        public void inicioAtencionPesaje()
        {
            setEstado(new EstadoCamionEnPesaje());
        }

        @Override
        public String toString() {
            return "Esperando para ser Pesado";
        }
    }
    
    public class EstadoCamionEnPesaje extends EstadoCamion
    {
        @Override
        public void finAtencionPesaje()
        {
            setEstado(new EstadoCamionColaDescarga());
        }

        @Override
        public String toString() {
            return "Siendo Pesado";
        }
    }
    
    public class EstadoCamionColaDescarga extends EstadoCamion
    {
        @Override
        public void inicioAtencionDarsena1()
        {
            setEstado(new EstadoCamionDescargando());
        }
        
        @Override
        public void inicioAtencionDarsena2()
        {
            setEstado(new EstadoCamionDescargando());
        }

        @Override
        public String toString() {
            return "Esperando para descarga";
        }
    }
    
    public class EstadoCamionDescargando extends EstadoCamion
    {
        @Override
        public void finAtencionDarsena1()
        {
            setEstado(new EstadoCamionFin());
        }
        
        @Override
        public void finAtencionDarsena2()
        {
            setEstado(new EstadoCamionFin());
        }

        @Override
        public String toString() {
            return "Siendo Descargado";
        }
    }
    
    public class EstadoCamionFin extends EstadoCamion
    {

        @Override
        public String toString() {
            return "Fuera del sistema";
        }
        //NOSE
    }
}
