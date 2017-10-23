/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.gestion;

import com.sun.xml.internal.ws.client.ContentNegotiation;
import java.time.*;
import java.util.*;
import logica.clientes.*;
import logica.servidores.*;

/**
 *
 * @author heftyn
 */
public class Sistema 
{
    private int dia;
    private LocalTime reloj;
    private String evento;
    
    private double rndLlegadaCamiones;
    private LocalTime proximaLlegada;
    private LocalTime horaProxLlegada;
    
    private double rndProxAtencion;
    private LocalTime proximaAtencion;
    private LocalTime horaProxAtencion;
    
    private ServidorRecepcion recepcionista;
    
    private double rndTiempoPesado;
    private LocalTime tiempoPesado;
    private LocalTime horaFinPesado;
    
    private ServidorPesaje balanza;
    
    private double rndTiempoDescarga1;
    private LocalTime tiempoDescarga1;
    private LocalTime horaFinDescarga1;
    
    private double rndTiempoCalibrado1;
    private LocalTime tiempoRecalibrado1;
    private LocalTime horaFinRecalibrado1;
    private ServidorDarsena darsena1;
    
    private double rndTiempoDescarga2;
    private LocalTime tiempoDescarga2;
    private LocalTime horaFinDescarga2;
    
    private double rndTiempoCalibrado2;
    private LocalTime tiempoRecalibrado2;
    private LocalTime horaFinRecalibrado2;
    private ServidorDarsena darsena2;
    
    private int acCantAtendidos;
    private int acCantNOAtendidos;
    /**
     * Como mostrar:
     * System.out.println(acumulador.toHours() + ":" + acumulador.minusHours(acumulador.toHours()).toMinutes());
     */
    private Duration acTiempoPermanencia;
    
    
    private List<Camion> camiones;

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public LocalTime getReloj() {
        return reloj;
    }

    public void setReloj(LocalTime reloj) {
        this.reloj = reloj;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public double getRndLlegadaCamiones() {
        return rndLlegadaCamiones;
    }

    public void setRndLlegadaCamiones(double rndLlegadaCamiones) {
        this.rndLlegadaCamiones = rndLlegadaCamiones;
    }

    public LocalTime getProximaLlegada() {
        return proximaLlegada;
    }

    public void setProximaLlegada(LocalTime proximaLlegada) {
        this.proximaLlegada = proximaLlegada;
    }

    public LocalTime getHoraProxLlegada() {
        return horaProxLlegada;
    }

    public void setHoraProxLlegada(LocalTime horaProxLlegada) {
        this.horaProxLlegada = horaProxLlegada;
    }

    public double getRndProxAtencion() {
        return rndProxAtencion;
    }

    public void setRndProxAtencion(double rndProxAtencion) {
        this.rndProxAtencion = rndProxAtencion;
    }

    public LocalTime getProximaAtencion() {
        return proximaAtencion;
    }

    public void setProximaAtencion(LocalTime proximaAtencion) {
        this.proximaAtencion = proximaAtencion;
    }

    public LocalTime getHoraProxAtencion() {
        return horaProxAtencion;
    }

    public void setHoraProxAtencion(LocalTime horaProxAtencion) {
        this.horaProxAtencion = horaProxAtencion;
    }

    public ServidorRecepcion getRecepcionista() {
        return recepcionista;
    }

    public void setRecepcionista(ServidorRecepcion recepcionista) {
        this.recepcionista = recepcionista;
    }

    public double getRndTiempoPesado() {
        return rndTiempoPesado;
    }

    public void setRndTiempoPesado(double rndTiempoPesado) {
        this.rndTiempoPesado = rndTiempoPesado;
    }

    public LocalTime getTiempoPesado() {
        return tiempoPesado;
    }

    public void setTiempoPesado(LocalTime tiempoPesado) {
        this.tiempoPesado = tiempoPesado;
    }

    public LocalTime getHoraFinPesado() {
        return horaFinPesado;
    }

    public void setHoraFinPesado(LocalTime horaFinPesado) {
        this.horaFinPesado = horaFinPesado;
    }

    public ServidorPesaje getBalanza() {
        return balanza;
    }

    public void setBalanza(ServidorPesaje balanza) {
        this.balanza = balanza;
    }

    public double getRndTiempoDescarga1() {
        return rndTiempoDescarga1;
    }

    public void setRndTiempoDescarga1(double rndTiempoDescarga1) {
        this.rndTiempoDescarga1 = rndTiempoDescarga1;
    }

    public LocalTime getTiempoDescarga1() {
        return tiempoDescarga1;
    }

    public void setTiempoDescarga1(LocalTime tiempoDescarga1) {
        this.tiempoDescarga1 = tiempoDescarga1;
    }

    public LocalTime getHoraFinDescarga1() {
        return horaFinDescarga1;
    }

    public void setHoraFinDescarga1(LocalTime horaFinDescarga1) {
        this.horaFinDescarga1 = horaFinDescarga1;
    }

    public double getRndTiempoCalibrado1() {
        return rndTiempoCalibrado1;
    }

    public void setRndTiempoCalibrado1(double rndTiempoCalibrado1) {
        this.rndTiempoCalibrado1 = rndTiempoCalibrado1;
    }

    public LocalTime getTiempoRecalibrado1() {
        return tiempoRecalibrado1;
    }

    public void setTiempoRecalibrado1(LocalTime tiempoRecalibrado1) {
        this.tiempoRecalibrado1 = tiempoRecalibrado1;
    }

    public LocalTime getHoraFinRecalibrado1() {
        return horaFinRecalibrado1;
    }

    public void setHoraFinRecalibrado1(LocalTime horaFinRecalibrado1) {
        this.horaFinRecalibrado1 = horaFinRecalibrado1;
    }

    public ServidorDarsena getDarsena1() {
        return darsena1;
    }

    public void setDarsena1(ServidorDarsena darsena1) {
        this.darsena1 = darsena1;
    }

    public double getRndTiempoDescarga2() {
        return rndTiempoDescarga2;
    }

    public void setRndTiempoDescarga2(double rndTiempoDescarga2) {
        this.rndTiempoDescarga2 = rndTiempoDescarga2;
    }

    public LocalTime getTiempoDescarga2() {
        return tiempoDescarga2;
    }

    public void setTiempoDescarga2(LocalTime tiempoDescarga2) {
        this.tiempoDescarga2 = tiempoDescarga2;
    }

    public LocalTime getHoraFinDescarga2() {
        return horaFinDescarga2;
    }

    public void setHoraFinDescarga2(LocalTime horaFinDescarga2) {
        this.horaFinDescarga2 = horaFinDescarga2;
    }

    public double getRndTiempoCalibrado2() {
        return rndTiempoCalibrado2;
    }

    public void setRndTiempoCalibrado2(double rndTiempoCalibrado2) {
        this.rndTiempoCalibrado2 = rndTiempoCalibrado2;
    }

    public LocalTime getTiempoRecalibrado2() {
        return tiempoRecalibrado2;
    }

    public void setTiempoRecalibrado2(LocalTime tiempoRecalibrado2) {
        this.tiempoRecalibrado2 = tiempoRecalibrado2;
    }

    public LocalTime getHoraFinRecalibrado2() {
        return horaFinRecalibrado2;
    }

    public void setHoraFinRecalibrado2(LocalTime horaFinRecalibrado2) {
        this.horaFinRecalibrado2 = horaFinRecalibrado2;
    }

    public ServidorDarsena getDarsena2() {
        return darsena2;
    }

    public void setDarsena2(ServidorDarsena darsena2) {
        this.darsena2 = darsena2;
    }

    public int getAcCantAtendidos() {
        return acCantAtendidos;
    }

    public void setAcCantAtendidos(int acCantAtendidos) {
        this.acCantAtendidos = acCantAtendidos;
    }

    public int getAcCantNOAtendidos() {
        return acCantNOAtendidos;
    }

    public void setAcCantNOAtendidos(int acCantNOAtendidos) {
        this.acCantNOAtendidos = acCantNOAtendidos;
    }

    /**
     * Como mostrar:
     * System.out.println(acumulador.toHours() + ":" + acumulador.minusHours(acumulador.toHours()).toMinutes());
     * @return Duration (inmutable!)
     */
    public Duration getAcTiempoPermanencia() {
        return acTiempoPermanencia;
    }

    public void setAcTiempoPermanencia(Duration acTiempoPermanencia) {
        this.acTiempoPermanencia = acTiempoPermanencia;
    }

    public List<Camion> getCamiones() {
        return camiones;
    }

    public void setCamiones(List<Camion> camiones) {
        this.camiones = camiones;
    }
    
    public String[] getVectorFila()
    {
        //Se deberia usar de utilidades un metodo q nos ponga en N/A los q no se tienen q mostrar por ejemplo los rnd cuando no se calcularon
        String[] vectorEstado = {
        ""+dia, ""+reloj, evento, 
            ""+rndLlegadaCamiones, proximaLlegada.toString(), horaProxLlegada.toString(),
            ""+rndProxAtencion, proximaAtencion.toString(), horaProxAtencion.toString(), recepcionista.getEstado().getNombre(), ""+recepcionista.getCola().getCola(),
            ""+rndTiempoPesado, tiempoPesado.toString(), horaFinPesado.toString(), balanza.getEstado().getNombre(), ""+balanza.getCola().getCola(),
            ""+rndTiempoDescarga1, tiempoDescarga1.toString(), horaFinDescarga1.toString(), ""+darsena1.getCantAtendidos(), 
            ""+rndTiempoCalibrado1, tiempoRecalibrado1.toString(), horaFinRecalibrado1.toString(), darsena1.getEstado().getNombre(), ""+darsena1.getCola().getCola(),
            ""+rndTiempoCalibrado2, tiempoRecalibrado2.toString(), horaFinRecalibrado2.toString(), darsena2.getEstado().getNombre(),
            ""+ acCantAtendidos, ""+acCantNOAtendidos, formatDuration(acTiempoPermanencia)
        };
        vectorEstado = agregarCamiones(vectorEstado);
        return vectorEstado;
    }

    private String formatDuration(Duration acTiempoPermanencia) {
        return acTiempoPermanencia.toHours() + ":" + acTiempoPermanencia.minusHours(acTiempoPermanencia.toHours()).toMinutes();
    }

    private String[] agregarCamiones(String[] vectorEstado) {
        
        if(camiones.isEmpty())
        {
            return vectorEstado;
        }
        ArrayList<String> lista = new ArrayList<>();
        for(Camion camion: camiones)
        {
            if (camion.estaFueraDelSistema())
            {
                lista.add("");
                lista.add("");
                lista.add("");
                lista.add("");
            }
            else
            {
                lista.add(camion.getEstado().toString());
                lista.add(camion.getServidorAtendido().getNombre());
                lista.add(camion.getTiempoLlegada().toString());
                lista.add(camion.getTiempoFin().toString());
            }
        }
        
        List<String> nuevaLista = Arrays.asList(vectorEstado);
        nuevaLista.addAll(lista);
        
        return nuevaLista.toArray(new String[0]);
    }
    
}
