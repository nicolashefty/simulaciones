/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.gestion;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import logica.servidores.ServidorDarsena;
import logica.servidores.ServidorPesaje;
import logica.servidores.ServidorRecepcion;
import logica.servidores.exceptions.NecesitaCalcularRNDDarsena;
import logica.servidores.exceptions.NecesitaCalcularRNDFinAtencion;
import logica.servidores.exceptions.NecesitaCalcularRNDInicioAtencion;
import logica.servidores.exceptions.NecesitaCalcularRNDPesaje;
import logica.servidores.exceptions.TieneQueCalibrar;
import logica.utilidades.Utilidades;

/**
 *
 * @author heftyn
 */
public class Simulador 
{

    List<Sistema> datos;
    Sistema vectorActual;
    Sistema vectorAnterior;
    LocalTime horaInicioLlegadaCamiones;
    DefaultTableModel tableModel;

    public Simulador(LocalTime inicioLlCamiones, DefaultTableModel tableModel, int diaDesde, int diaHasta)
    {
        horaInicioLlegadaCamiones = inicioLlCamiones;
        this.tableModel = tableModel;
        datos = new ArrayList<>();
        rutinaDeInicializacion();
        while(!fin())
        {
            rutinaDeTiempo();
        }
        rutinaGeneradoraDeReportes();
    }

    public final void rutinaDeInicializacion()
    {
        vectorActual = new Sistema();
        vectorActual.setDia(1);
        vectorActual.setReloj(LocalTime.of(5, 0));
        vectorActual.setAcCantAtendidos(0);
        vectorActual.setAcCantNOAtendidos(0);
        vectorActual.setAcTiempoPermanencia(Duration.ZERO);
        vectorActual.setRecepcionista(new ServidorRecepcion());
        vectorActual.setBalanza(new ServidorPesaje());
        vectorActual.setDarsena1(new ServidorDarsena());
        vectorActual.setDarsena2(new ServidorDarsena());
        vectorActual.setEvento(Evento.APERTURA);
        //Reloj en 0
        // Crear servidores 
        //Iniciar contadores
        //Iniciar archivo de eventos
        rotacionVector();
    }

    public final void rutinaDeTiempo()
    {
        if(Evento.APERTURA.equals(vectorAnterior.getEvento()))
        {
            //Proxima llegada debiera ser la hora inicio llegada de camiones
            //Ojo q la llegada camion deberia modificar el reloj en todos los casos menos este
            rutinaLlegadaCamion(LocalTime.of(12,0));

            rotacionVector();
            return;
        }
        else if (Evento.CIERRE.equals(vectorAnterior.getEvento()))
        {
            vectorActual = vectorAnterior.copy();
            vectorActual.setDia(vectorAnterior.getDia()+1);
            vectorActual.setReloj(LocalTime.of(5,0));
            vectorActual.setEvento(Evento.APERTURA);
            //informar a los servidores del cierre
            rotacionVector();
            return;
        }

        Sistema.BeanEventoHora eventoSig = vectorAnterior.getProximoEvento();
        switch (eventoSig.evento)
        {
            case Evento.FIN_ATENCION_RECEPCION:
                rutinaFinAtencionRecepcion(eventoSig.hora);
                break;
            case Evento.FIN_CALIBRADO:
                rutinaFinCalibrado(eventoSig.hora);
                break;
            case Evento.FIN_DESCARGA:
                rutinaFinDescarga(eventoSig.hora);
                break;
            case Evento.FIN_PESAJE:
                rutinaFinPesaje(eventoSig.hora);
                break;
            case Evento.LLEGADA_CAMION:
                rutinaLlegadaCamion(eventoSig.hora);
                break;
        }

        rotacionVector();
    }

    private void rotacionVector()
    {
        datos.add(vectorActual);
        vectorAnterior = vectorActual;
        vectorActual = null;
    }

    /**
     * Estructura
     */
    public void rutinaDeEvento()
    {
        //Actualizar estado del Sistema (Avanzar Reloj)
        //Actualizar contadores estadisticos
        //Generar eventos futuros y adicionarlos al archivo de eventos
    }

    /**
     * Arma como devolver los datos al cliente
     */
    public final void rutinaGeneradoraDeReportes()
    {
        calcularParametrosDeInteres();
        mostrarResultados();
    }

    public void calcularParametrosDeInteres()
    {

    }

    public void mostrarResultados()
    {
        tableModel.setColumnIdentifiers(getColumnNames());
        for(Sistema row: datos)
        {
            tableModel.addRow(row.getVectorFila());
        }
    }

    private void rutinaFinAtencionRecepcion(LocalTime newTime) {
        vectorActual = vectorAnterior.clone();
        vectorActual.setDia(vectorAnterior.getDia());
        vectorActual.setReloj(newTime);
        vectorActual.setEvento(Evento.FIN_ATENCION_RECEPCION);

        try {
            vectorActual.getRecepcionista().finAtencionRecepcion();
        } catch (NecesitaCalcularRNDFinAtencion e) {
            
            try {

                vectorActual.getRecepcionista().inicioAtencionRecepcion();

            } catch (NecesitaCalcularRNDInicioAtencion ex) {

                double rndP = new Random().nextDouble();

                vectorActual.setRndTiempoAtencion(rndP);
                vectorActual.setTiempoAtencion(Utilidades.uniforme(3, 7, rndP));
                vectorActual.setHoraFinAtencion(vectorActual.getReloj()
                        .plusSeconds(vectorActual.getTiempoAtencion().getSecond())
                        .plusMinutes(vectorActual.getTiempoAtencion().getMinute())
                        .plusHours(vectorActual.getTiempoAtencion().getHour()));
            
                  
            
            
        }

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
          
        }
    }

    private void rutinaFinCalibrado(LocalTime newTime) 
    {
        vectorActual = vectorAnterior.clone();
        vectorActual.setReloj(newTime);
        vectorActual.setEvento(Evento.FIN_CALIBRADO);
        
        //Cual dársena!!?
        if (vectorActual.getHoraFinDescarga1().equals(newTime))
        {
            try
            {
                vectorActual.getDarsena1().finCalibrado();
            }
            catch (NecesitaCalcularRNDDarsena ncrndD)
            {
                //TODO: ver qué camion pasa a estar Descargando!
                
                double rndD = new Random().nextDouble();
                vectorActual.setRndTiempoDescarga1(rndD);
                vectorActual.setTiempoDescarga1(Utilidades.uniforme(20, 25, rndD));
                vectorActual.setHoraFinDescarga1(vectorActual.getReloj()
                        .plusSeconds(vectorActual.getTiempoDescarga1().getSecond())
                        .plusMinutes(vectorActual.getTiempoDescarga1().getMinute())
                        .plusHours(vectorActual.getTiempoDescarga1().getHour()));
            }
        }
        else
        {
            try
            {
                vectorActual.getDarsena2().finCalibrado();
            }
            catch(NecesitaCalcularRNDDarsena ncrndD)
            {
                //TODO: Ver que camion pasa a estar descargando!!
                double rndD = new Random().nextDouble();
                vectorActual.setRndTiempoDescarga2(rndD);
                vectorActual.setTiempoDescarga2(Utilidades.uniforme(20, 25, rndD));
                vectorActual.setHoraFinDescarga2(vectorActual.getReloj()
                        .plusSeconds(vectorActual.getTiempoDescarga2().getSecond())
                        .plusMinutes(vectorActual.getTiempoDescarga2().getMinute())
                        .plusHours(vectorActual.getTiempoDescarga2().getHour()));
            }
        }
        
        throw new UnsupportedOperationException("Nico"); //To change body of generated methods, choose Tools | Templates.
    }

    private void rutinaFinDescarga(LocalTime newTime) 
    {
        vectorActual = vectorAnterior.clone();
        vectorActual.setReloj(newTime);
        vectorActual.setEvento(Evento.FIN_DESCARGA);
        
        //Cual dársena!!?
        if (vectorActual.getHoraFinDescarga1().equals(newTime))
        {
            try
            {
                vectorActual.getDarsena1().finDescarga();
                
            }
            catch(TieneQueCalibrar tqc)
            {
                //Calibrar
                vectorActual.getDarsena1().inicioCalibrado();
                //Siempre que hay inicio de calibrado calculo los rnd
                double rndC1 = new Random().nextDouble();
                double rndC2 = new Random().nextDouble();
                vectorActual.setRndTiempoCalibrado11(rndC1);
                vectorActual.setRndTiempoCalibrado12(rndC2);
                vectorActual.setTiempoRecalibrado1(Utilidades.calcularRecalibramiento(0.7, 10, rndC1, rndC2));
            }
            catch(NecesitaCalcularRNDDarsena ncrndd)
            {
                //TODO: ver qué camion pasa a estar Descargando!
                
                double rndD = new Random().nextDouble();
                vectorActual.setRndTiempoDescarga1(rndD);
                vectorActual.setTiempoDescarga1(Utilidades.uniforme(20, 25, rndD));
                vectorActual.setHoraFinDescarga1(vectorActual.getReloj()
                        .plusSeconds(vectorActual.getTiempoDescarga1().getSecond())
                        .plusMinutes(vectorActual.getTiempoDescarga1().getMinute())
                        .plusHours(vectorActual.getTiempoDescarga1().getHour()));
            }
            //Se va el camion que estaba descargando en darsena 1
        }
        else
        {
            try
            {
                vectorActual.getDarsena2().finDescarga();
                
            }
            catch(TieneQueCalibrar tqc)
            {
                //Calibrar
                vectorActual.getDarsena2().inicioCalibrado();
                //Siempre que hay inicio de calibrado calculo los rnd
                double rndC1 = new Random().nextDouble();
                double rndC2 = new Random().nextDouble();
                vectorActual.setRndTiempoCalibrado21(rndC1);
                vectorActual.setRndTiempoCalibrado22(rndC2);
                vectorActual.setTiempoRecalibrado1(Utilidades.calcularRecalibramiento(0.7, 10, rndC1, rndC2));
            }
            catch(NecesitaCalcularRNDDarsena ncrndd)
            {
                //TODO: Ver que camion pasa a estar descargando!!
                double rndD = new Random().nextDouble();
                vectorActual.setRndTiempoDescarga2(rndD);
                vectorActual.setTiempoDescarga2(Utilidades.uniforme(20, 25, rndD));
                vectorActual.setHoraFinDescarga2(vectorActual.getReloj()
                        .plusSeconds(vectorActual.getTiempoDescarga2().getSecond())
                        .plusMinutes(vectorActual.getTiempoDescarga2().getMinute())
                        .plusHours(vectorActual.getTiempoDescarga2().getHour()));
            }
            //Se va el camion que estaba descargando en Darsena2
        }
        
        throw new UnsupportedOperationException("Nico"); //To change body of generated methods, choose Tools | Templates.
    }

    private void rutinaFinPesaje(LocalTime newTime) 
    {
        vectorActual = vectorAnterior.clone();
        vectorActual.setReloj(newTime);
        vectorActual.setEvento(Evento.FIN_PESAJE);
        
        try
        {
            vectorActual.getBalanza().finPesaje();
        }
        catch(NecesitaCalcularRNDPesaje ncrndp)
        {
            double rndP = new Random().nextDouble();
            vectorActual.setRndTiempoPesado(rndP);
            vectorActual.setTiempoPesado(Utilidades.uniforme(5, 7, rndP));
            vectorActual.setHoraFinPesado(vectorActual.getReloj()
                    .plusSeconds(vectorActual.getTiempoPesado().getSecond())
                    .plusMinutes(vectorActual.getTiempoPesado().getMinute())
                    .plusHours(vectorActual.getTiempoPesado().getHour()));
        }
        //Siguiente en el proceso es Descarga
        if(vectorActual.getDarsena1().estaLibre())
        {
            try
            {
                vectorActual.getDarsena1().inicioDescarga();
            }
            catch (NecesitaCalcularRNDDarsena ncrndd)
            {
                double rndD = new Random().nextDouble();
                vectorActual.setRndTiempoDescarga1(rndD);
                vectorActual.setTiempoDescarga1(Utilidades.uniforme(20, 25, rndD));
                vectorActual.setHoraFinDescarga1(vectorActual.getReloj()
                        .plusSeconds(vectorActual.getTiempoDescarga1().getSecond())
                        .plusMinutes(vectorActual.getTiempoDescarga1().getMinute())
                        .plusHours(vectorActual.getTiempoDescarga1().getHour()));
            }
        }
        else if (vectorActual.getDarsena2().estaLibre())
        {
            try
            {
                vectorActual.getDarsena2().inicioDescarga();
            }
            catch (NecesitaCalcularRNDDarsena ncrndd)
            {
                double rndD = new Random().nextDouble();
                vectorActual.setRndTiempoDescarga2(rndD);
                vectorActual.setTiempoDescarga2(Utilidades.uniforme(20, 25, rndD));
                vectorActual.setHoraFinDescarga2(vectorActual.getReloj()
                        .plusSeconds(vectorActual.getTiempoDescarga2().getSecond())
                        .plusMinutes(vectorActual.getTiempoDescarga2().getMinute())
                        .plusHours(vectorActual.getTiempoDescarga2().getHour()));
            }
        }
        else
        {
            //A cualquiera es lo mismo pero vamos a la 1
            try 
            {
                vectorActual.getDarsena1().inicioDescarga();
            }
            catch (NecesitaCalcularRNDDarsena ncrndd)
            {
                double rndD = new Random().nextDouble();
                vectorActual.setRndTiempoDescarga1(rndD);
                vectorActual.setTiempoDescarga1(Utilidades.uniforme(20, 25, rndD));
                vectorActual.setHoraFinDescarga1(vectorActual.getReloj()
                        .plusSeconds(vectorActual.getTiempoDescarga1().getSecond())
                        .plusMinutes(vectorActual.getTiempoDescarga1().getMinute())
                        .plusHours(vectorActual.getTiempoDescarga1().getHour()));
            }
        }
        //Faltaria actualizar el camion al q se le termino de atender en Pesaje y se lo paso a Descarga
        //Al igual q el q seguia en la cola de Pesado decirle q lo estan atendiendo en Pesaje
        throw new UnsupportedOperationException("Nico"); //To change body of generated methods, choose Tools | Templates.
    }

    private void rutinaLlegadaCamion(LocalTime newTime) {

        vectorActual = vectorAnterior.clone();
        vectorActual.setDia(vectorAnterior.getDia());
        vectorActual.setReloj(newTime);
        vectorActual.setEvento(Evento.LLEGADA_CAMION);

        try {
            vectorActual.getRecepcionista().inicioAtencionRecepcion();
        } catch (NecesitaCalcularRNDInicioAtencion e) {

            double rnd = new Random().nextDouble();
            vectorActual.setRndTiempoAtencion(rnd);

            vectorActual.setTiempoAtencion(Utilidades.uniforme(3, 7, rnd));
            vectorActual.setHoraFinAtencion(vectorActual.getReloj()
                    .plusSeconds(vectorActual.getTiempoAtencion().getSecond())
                    .plusMinutes(vectorActual.getTiempoAtencion().getMinute())
                    .plusHours(vectorActual.getTiempoAtencion().getHour()));
        }
        double rnd = new Random().nextDouble();
        vectorActual.setRndLlegadaCamiones(rnd);

        vectorActual.setProximaLlegada(Utilidades.calcularLlegadaCamion(15, rnd));
        vectorActual.setHoraProxLlegada(vectorActual.getReloj()
                .plusSeconds(vectorActual.getProximaLlegada().getSecond())
                .plusMinutes(vectorActual.getProximaLlegada().getMinute())
                .plusHours(vectorActual.getProximaLlegada().getHour()));

    }
    private String[] getColumnNames() 
    {
        List<String> col = new ArrayList<>();
        col.add("Dia");
        col.add("Hora");
        col.add("Evento");
        col.add("RND LL CA");
        col.add("Tiempo Prox Llegada");
        col.add("Hora Prox Llegada");
        col.add("RND T ATN");
        col.add("Tiempo Atencion");
        col.add("Hora Fin Atencion");
        col.add("Estado Recepcion");
        col.add("Cola Recepcion");
        col.add("RND Pesado");
        col.add("Tiempo Pesado");
        col.add("Hora Fin Pesado");
        col.add("Estado Balanza");
        col.add("Cola Balanza");
        col.add("RND Descarga 1");
        col.add("Tiempo Descarga 1");
        col.add("Hora Fin Descarga 1");
        col.add("Cant Atendidos 1");
        col.add("RND Calib 1 1");
        col.add("RND Calib 1 2");
        col.add("Tiempo Calibr 1");
        col.add("Hora fin Calibr 1");
        col.add("Estado Darsena 1");
        col.add("Cola Darsenas");
        col.add("RND Descarga 2");
        col.add("Tiempo Descarga 2");
        col.add("Hora Fin Descarga 2");
        col.add("Cant Atendidos 2");
        col.add("RND Calib 2 1");
        col.add("RND Calib 2 2");
        col.add("Tiempo Calibr 2");
        col.add("Hora fin Calibr 2");
        col.add("Estado Darsena 2");
        col.add("AC Cant Atendidos");
        col.add("AC Cant NO Atendidos");
        col.add("AC Tiempo Permanencia");

        agregarColumnasCamiones(col);
        return col.toArray(new String[0]);
    }

    private void agregarColumnasCamiones(List<String> col) 
    {
        int maximaCantCamiones = buscarCantidadMaximaDeCamiones();

        for(int i = 0; i < maximaCantCamiones; i++)
        {
            col.add("Estado Camion");
            col.add("Servidor");
            col.add("Hora Llegada");
            col.add("Hora Salida");
        }
    }

    private int buscarCantidadMaximaDeCamiones() 
    {
        List<Sistema> listCopy = new ArrayList<Sistema>();
        Collections.copy(listCopy, datos);
        listCopy.sort(null);
        return listCopy.get(0).getCamiones().size();
    }

    private boolean fin() {
        if (vectorAnterior.getDia() == 30 && vectorAnterior.getEvento().equals(Evento.CIERRE)) {
            return true;
        }
        return false;
    }
}
