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
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import logica.servidores.ServidorDarsena;
import logica.servidores.ServidorPesaje;
import logica.servidores.ServidorRecepcion;

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
    
    public Simulador(LocalTime inicioLlCamiones, DefaultTableModel tableModel)
    {
        horaInicioLlegadaCamiones = inicioLlCamiones;
        this.tableModel = tableModel;
    }
     
    public void rutinaDeInicializacion()
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
    
    public void rutinaDeTiempo()
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
    public void rutinaGeneradoraDeReportes()
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void rutinaFinCalibrado(LocalTime newTime) 
    {
        throw new UnsupportedOperationException("Nico"); //To change body of generated methods, choose Tools | Templates.
    }

    private void rutinaFinDescarga(LocalTime newTime) 
    {
        throw new UnsupportedOperationException("Nico"); //To change body of generated methods, choose Tools | Templates.
    }

    private void rutinaFinPesaje(LocalTime newTime) 
    {
        vectorActual = vectorAnterior.copy();
        vectorActual.setDia(vectorAnterior.getDia());
        vectorActual.setReloj(newTime);
        vectorActual.setEvento(Evento.FIN_PESAJE);
        
        //Arrastro lo que no calculo:
        vectorActual.setHoraFinAtencion(vectorAnterior.getHoraFinAtencion());
        vectorActual.setHoraFinDescarga1(vectorAnterior.getHoraFinDescarga1());
        vectorActual.setHoraFinDescarga2(vectorAnterior.getHoraFinDescarga2());
        vectorActual.setHoraFinRecalibrado1(vectorAnterior.getHoraFinRecalibrado1());
        vectorActual.setHoraFinRecalibrado2(vectorAnterior.getHoraFinRecalibrado2());
        vectorActual.setHoraProxLlegada(vectorAnterior.getHoraProxLlegada());
        vectorActual.setAcCantAtendidos(vectorAnterior.getAcCantAtendidos());
        vectorActual.setAcCantNOAtendidos(vectorAnterior.getAcCantNOAtendidos());
        vectorActual.setAcTiempoPermanencia(vectorAnterior.getAcTiempoPermanencia());
        
        vectorActual.getBalanza().finPesaje();
        throw new UnsupportedOperationException("Nico"); //To change body of generated methods, choose Tools | Templates.
    }

    private void rutinaLlegadaCamion(LocalTime newTime) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
}
