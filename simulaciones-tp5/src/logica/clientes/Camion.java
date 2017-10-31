/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.clientes;

import java.time.LocalTime;

/**
 *
 * @author heftyn
 */
public class Camion {
    
    /**
     * No atendidos serian aquellos que estaban en la cola de recepcion al momento de cierre.
     */
    private EstadoCamion estado;
    private String servidor;
    private LocalTime tiempoLlegada;
    private LocalTime tiempoFin;

    public EstadoCamion getEstado() {
        return estado;
    }

    public void setEstado(EstadoCamion estado) {
        this.estado = estado;
    }

    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
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

    public String getServidorAtendido() 
    {
        return servidor;
    }

    public void llegadaCamion() {
        setEstado(new EstadoCamionColaRecepcion());
    }

    public boolean estaEnRecepcion() {
        return estado.estaEnRecepcion();
    }

    public boolean estaEnColaRecepcion() 
    {
        return estado.estaEnColaRecepcion();
    }

    public boolean estaEnColaPesaje() 
    {
        return estado.estaEnColaPesaje();
    }

    public boolean estaEnColaDescarga() 
    {
        return estado.estaEnColaDescarga();
    }

    public boolean estaEnBalanza() 
    {
        return estado.estaEnBalanza();
    }

    public boolean estaEnDarsena() 
    {
        return estado.estaEnDarsena();
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

        public boolean esFueraDelSistema()
        {
            return false;
        }

        public boolean estaEnRecepcion()
        {
            return false;
        }

        public boolean estaEnColaRecepcion() 
        {
            return false;
        }

        public boolean estaEnColaPesaje() 
        {
            return false;
        }

        public boolean estaEnColaDescarga() 
        {
            return false;
        }

        public boolean estaEnBalanza() 
        {
            return false;
        }

        public boolean estaEnDarsena() 
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
            setServidor("Recepcion");
        }

        @Override
        public String toString() {
            return "Cola Recepcion";
        }

        public boolean estaEnColaRecepcion() 
        {
            return true;
        }
    }
    
    public class EstadoCamionRecepcion extends EstadoCamion
    {
        @Override
        public void finAtencionRecepcion()
        {
            setEstado(new EstadoCamionColaPesaje());
            setServidor("Ninguno");
        }

        @Override
        public String toString() {
            return "En Recepcion";
        }
        
        @Override
        public boolean estaEnRecepcion()
        {
            return true;
        }
    }
    public class EstadoCamionColaPesaje extends EstadoCamion
    {
        @Override
        public void inicioAtencionPesaje()
        {
            setEstado(new EstadoCamionEnPesaje());
            setServidor("Balanza");
        }

        @Override
        public String toString() {
            return "Cola Pesaje";
        }
        
        public boolean estaEnColaPesaje() 
        {
            return true;
        }
    }
    
    public class EstadoCamionEnPesaje extends EstadoCamion
    {
        @Override
        public void finAtencionPesaje()
        {
            setEstado(new EstadoCamionColaDescarga());
            setServidor("Ninguno");
        }

        @Override
        public String toString() {
            return "En Pesado";
        }
        
        public boolean estaEnBalanza() 
        {
            return true;
        }
    }
    
    public class EstadoCamionColaDescarga extends EstadoCamion
    {
        @Override
        public void inicioAtencionDarsena1()
        {
            setEstado(new EstadoCamionDescargando());
            setServidor("Darsena");
        }
        
        @Override
        public void inicioAtencionDarsena2()
        {
            setEstado(new EstadoCamionDescargando());
            setServidor("Darsena");
        }

        @Override
        public String toString() {
            return "Cola Descarga";
        }
        
        public boolean estaEnColaDescarga() 
        {
            return true;
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
            return "En Darsena";
        }
        
        public boolean estaEnDarsena() 
        {
            return true;
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
