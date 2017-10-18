/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.gestion;

/**
 *
 * @author heftyn
 */
public interface Evento 
{
    String LLEGADA_CAMION = "Llegada Camion";
    String FIN_ATENCION_RECEPCION = "Fin Atc Recepcion";
    String FIN_PESAJE = "Fin Pesaje";
    /** Se retira el camion */
    String FIN_DESCARGA = "Fin Descarga";
    String FIN_CALIBRADO = "Fin Calibrado";
    String APERTURA = "Apertura";
    String CIERRE = "Cierre";
}
