/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.gestion;

import java.time.*;
import java.util.*;
import logica.clientes.*;
import logica.servidores.*;
import logica.utilidades.Utilidades;

/**
 *
 * @author heftyn
 */
public class Sistema implements Comparable<Sistema>
{
    private int dia;
    private LocalTime reloj;
    private String evento;
    
    private double rndLlegadaCamiones;
    private LocalTime proximaLlegada;
    private LocalTime horaProxLlegada;
    
    private double rndTiempoAtencion;
    private LocalTime tiempoAtencion;
    private LocalTime horaFinAtencion;
    
    private ServidorRecepcion recepcionista;
    
    private double rndTiempoPesado;
    private LocalTime tiempoPesado;
    private LocalTime horaFinPesado;
    
    private ServidorPesaje balanza;
    
    private double rndTiempoDescarga1;
    private LocalTime tiempoDescarga1;
    private LocalTime horaFinDescarga1;
    
    private double rndTiempoCalibrado11;
    private double rndTiempoCalibrado12;
    private LocalTime tiempoRecalibrado1;
    private LocalTime horaFinRecalibrado1;
    private ServidorDarsena darsena1;
    
    private double rndTiempoDescarga2;
    private LocalTime tiempoDescarga2;
    private LocalTime horaFinDescarga2;
    
    private double rndTiempoCalibrado21;
    private double rndTiempoCalibrado22;
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

    public double getRndTiempoAtencion() {
        return rndTiempoAtencion;
    }

    public void setRndTiempoAtencion(double rndTiempoAtencion) {
        this.rndTiempoAtencion = rndTiempoAtencion;
    }

    public LocalTime getTiempoAtencion() {
        return tiempoAtencion;
    }

    public void setTiempoAtencion(LocalTime tiempoAtencion) {
        this.tiempoAtencion = tiempoAtencion;
    }

    public LocalTime getHoraFinAtencion() {
        return horaFinAtencion;
    }

    public void setHoraFinAtencion(LocalTime horaFinAtencion) {
        this.horaFinAtencion = horaFinAtencion;
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

    public double getRndTiempoCalibrado11() {
        return rndTiempoCalibrado11;
    }

    public void setRndTiempoCalibrado11(double rndTiempoCalibrado11) {
        this.rndTiempoCalibrado11 = rndTiempoCalibrado11;
    }
    public double getRndTiempoCalibrado12() {
        return rndTiempoCalibrado12;
    }

    public void setRndTiempoCalibrado12(double rndTiempoCalibrado12) {
        this.rndTiempoCalibrado12 = rndTiempoCalibrado12;
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

    public double getRndTiempoCalibrado21() {
        return rndTiempoCalibrado21;
    }

    public void setRndTiempoCalibrado21(double rndTiempoCalibrado21) {
        this.rndTiempoCalibrado21 = rndTiempoCalibrado21;
    }
    public double getRndTiempoCalibrado22() {
        return rndTiempoCalibrado22;
    }

    public void setRndTiempoCalibrado22(double rndTiempoCalibrado22) {
        this.rndTiempoCalibrado22 = rndTiempoCalibrado22;
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
        ""+dia, ""+Utilidades.formatParaLocalTime(reloj), evento, 
            ""+Utilidades.formatParaRandom(rndLlegadaCamiones), Utilidades.formatParaLocalTime(proximaLlegada), Utilidades.formatParaLocalTime(horaProxLlegada),
            ""+Utilidades.formatParaRandom(rndTiempoAtencion), Utilidades.formatParaLocalTime(tiempoAtencion), Utilidades.formatParaLocalTime(horaFinAtencion), recepcionista.getEstado().getNombre(), ""+recepcionista.getCola().getCola(),
            ""+Utilidades.formatParaRandom(rndTiempoPesado), Utilidades.formatParaLocalTime(tiempoPesado), Utilidades.formatParaLocalTime(horaFinPesado), balanza.getEstado().getNombre(), ""+balanza.getCola().getCola(),
            ""+Utilidades.formatParaRandom(rndTiempoDescarga1), Utilidades.formatParaLocalTime(tiempoDescarga1), Utilidades.formatParaLocalTime(horaFinDescarga1), ""+darsena1.getCantAtendidos(), 
            ""+Utilidades.formatParaRandom(rndTiempoCalibrado11), ""+Utilidades.formatParaRandom(rndTiempoCalibrado12), Utilidades.formatParaLocalTime(tiempoRecalibrado1), Utilidades.formatParaLocalTime(horaFinRecalibrado1), darsena1.getEstado().getNombre(), ""+darsena1.getCola().getCola(),
            ""+Utilidades.formatParaRandom(rndTiempoDescarga2), Utilidades.formatParaLocalTime(tiempoDescarga2), Utilidades.formatParaLocalTime(horaFinDescarga2), ""+darsena2.getCantAtendidos(), 
            ""+Utilidades.formatParaRandom(rndTiempoCalibrado21), ""+Utilidades.formatParaRandom(rndTiempoCalibrado22), Utilidades.formatParaLocalTime(tiempoRecalibrado2), Utilidades.formatParaLocalTime(horaFinRecalibrado2), darsena2.getEstado().getNombre(),
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
                lista.add(Utilidades.formatParaLocalTime(camion.getTiempoLlegada()));
                lista.add(Utilidades.formatParaLocalTime(camion.getTiempoFin()));
            }
        }
        
        List<String> nuevaLista = Arrays.asList(vectorEstado);
        nuevaLista.addAll(lista);
        
        return nuevaLista.toArray(new String[0]);
    }

    /**
     * Copia pero no mantiene mas que los acumulados
     * @return 
     */
    public Sistema copy() 
    {
        Sistema sistCopy = new Sistema();
        sistCopy.acCantAtendidos = this.acCantAtendidos;
        sistCopy.acCantNOAtendidos = this.acCantNOAtendidos;
        sistCopy.acTiempoPermanencia = this.acTiempoPermanencia;
        sistCopy.balanza = new ServidorPesaje();
        sistCopy.camiones = new ArrayList<>();
        sistCopy.darsena1 = new ServidorDarsena();
        sistCopy.darsena2 = new ServidorDarsena();
        sistCopy.recepcionista = new ServidorRecepcion();
        return sistCopy;
    }

    public Sistema clone()
    {
        Sistema clone = new Sistema();
        
        clone.dia = this.dia;
        clone.reloj = this.reloj;
        clone.evento = this.evento;
        clone.horaProxLlegada = this.horaProxLlegada;
        clone.horaFinAtencion = this.horaFinAtencion;
        clone.recepcionista = this.recepcionista;
        clone.horaFinPesado = this.horaFinPesado;
        clone.balanza = this.balanza;
        clone.horaFinDescarga1 = this.horaFinDescarga1;
        clone.darsena1 = this.darsena1;
        clone.horaFinRecalibrado1 = this.horaFinRecalibrado1;
        clone.horaFinDescarga2 = this.horaFinDescarga2;
        clone.darsena2 = this.darsena2;
        clone.horaFinRecalibrado2 = this.horaFinRecalibrado2;
        clone.acCantAtendidos = this.acCantAtendidos;
        clone.acCantNOAtendidos = this.acCantNOAtendidos;
        clone.acTiempoPermanencia = this.acTiempoPermanencia;
        clone.camiones = this.camiones;             
       
        return clone;
    }
    
    BeanEventoHora getProximoEvento() 
    {
        List<BeanEventoHora> lista = new ArrayList<>();
        lista.add(new BeanEventoHora(Evento.LLEGADA_CAMION, horaProxLlegada));
        lista.add(new BeanEventoHora(Evento.FIN_ATENCION_RECEPCION, horaFinAtencion));
        lista.add(new BeanEventoHora(Evento.FIN_CALIBRADO, horaFinRecalibrado1));
        lista.add(new BeanEventoHora(Evento.FIN_CALIBRADO, horaFinRecalibrado2));
        lista.add(new BeanEventoHora(Evento.FIN_DESCARGA, horaFinDescarga1));
        lista.add(new BeanEventoHora(Evento.FIN_DESCARGA, horaFinDescarga2));
        lista.add(new BeanEventoHora(Evento.FIN_PESAJE, horaFinPesado));
        lista.sort(null);
        
        return lista.get(0);
    }

    @Override
    public int compareTo(Sistema o) 
    {
        if (o == null || o.camiones == null || o.camiones.isEmpty())
        {
            return 1;
        }
        else if (this.camiones == null || this.camiones.isEmpty())
        {
            return -1;
        }
        return this.camiones.size() - o.camiones.size();
    }

    public class BeanEventoHora implements Comparable<BeanEventoHora>
    {
        String evento;
        LocalTime hora;
        public BeanEventoHora(String e, LocalTime h)
        {
            evento = e;
            hora = h;
        }

        @Override
        public int compareTo(BeanEventoHora o) 
        {
            if (o == null)
            {
                return 1;
            }
            return this.hora.compareTo(o.hora);
        }           
    }
}
