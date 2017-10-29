/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.utilidades;

import java.text.DecimalFormat;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author heftyn
 */
public class Utilidades 
{
    
    final static DecimalFormat ENTERO = new DecimalFormat("0");
    final static DecimalFormat COSTO = new DecimalFormat("0.00");
    final static DecimalFormat RANDOM = new DecimalFormat("0.0000");
    
//    public static ValoresInicialesSimulacion transformarAValoresInicialesSimulacion(int desde, int hasta) {
//        
//        return new ValoresInicialesSimulacion(desde, hasta, getDatosMontecarlo());
//    }
    
    public static String formatRND(double rndDouble)
    {
        return RANDOM.format(rndDouble);
    }
    
    public static String aEntero(double unDouble)
    {
        if(unDouble == Double.MAX_VALUE)
        {
            return "Infinito";
        }
        return ENTERO.format(unDouble);
    }
    
    public static String aCosto(double unCosto)
    {
        return "$"+COSTO.format(unCosto); //Ponerle $ y negativo si es necesario. Solo dos decimales;
    }
    
    /**
     * En minutos será el resultado
     * @param media
     * @param random
     * @return 
     */
    public static LocalTime calcularLlegadaCamion(double media, double random){
        //Algo esta mal... no avanza mas.. siempre da 0 minutos
        Double result = (-media)*(Math.log(1-random));
        int hora = 0;
        int minutos = 0;
        int segundos = 0;
        if (result > 59)
        {
            hora = ((Double)Math.floor(result/60)).intValue();
            minutos = (int) (result % 60);
            segundos = ((result % 60) - (int) (result % 60)) > 0 ? (int)((result % 60) - (int) (result % 60))*60 : 0;
        }
        else
        {
            minutos = ((Double)Math.floor(result)).intValue();
            segundos = (int)((result - minutos)*60);
        }
//        Integer resultConverted = ((Double)Math.floor(result)).intValue();
//        Double seconds = (result - resultConverted)*60;
        return LocalTime.of(hora, minutos, segundos);
//        return LocalTime.of(0,2,0);
    }
    
    /* Sirve tanto para atencion de cambion, balanza de peso y las darsenas */
    public static LocalTime uniforme(int a, int b, double random){
        Double result = a + random * (b-a);
        Integer resultConverted = result.intValue();
        Double seconds = (result - resultConverted)*60;
        return LocalTime.of(0, resultConverted,seconds.intValue());
    }
    
    public static LocalTime calcularRecalibramiento(double varianza, double media, double randomUno, double randomDos){
        Double result = Math.abs(((Math.sqrt(-2*Math.log(randomUno))*Math.cos(2*Math.PI*randomDos))*media) + varianza);
        Integer resultConverted = ((Double)Math.floor(result)).intValue();
        Double seconds = (result - resultConverted)*60;
        return LocalTime.of(0, resultConverted,seconds.intValue());
    }
    
    public static String formatParaRandom(double rnd){
        if(rnd == 0){
            return "N/A";
        }
        else{
            return ""+RANDOM.format(rnd);
        }
    }
    
    public static String formatParaLocalTime(LocalTime localTime){
        if(localTime == null){
            return "N/A";
        }
        else{
            return localTime.toString();
        }
    }

//    public static DatosMontecarlo getDatosMontecarlo() {
//        DatosMontecarlo datos = new DatosMontecarlo();
//        datos.setCostoFaltante(10);
//        datos.setCostoAlmacenamiento(3);
//        datos.setStockInicial(20);
//        datos.setCostoPedido(getCostoPedido());
//        datos.setDemanda(getDemanda());
//        datos.setDemora(getDemora());
//        
//        return datos;
//    }
    
//    private static CostoPedido getCostoPedido()
//    {
//        CostoPedido costoPedido = new CostoPedido();
//        costoPedido.agregarCosto(Double.valueOf(21), Double.valueOf(20));
//        costoPedido.agregarCosto(Double.valueOf(41), Double.valueOf(25));
//        costoPedido.agregarCosto(Double.MAX_VALUE, Double.valueOf(30));
//        return costoPedido;
//    }
//    
//    private static Demanda getDemanda()
//    {
//        Demanda demanda = new Demanda();
//        
//        try 
//        {
//            demanda.agregarDemanda(0.05, Double.valueOf(0));
//            demanda.agregarDemanda(0.12, Double.valueOf(10));
//            demanda.agregarDemanda(0.18, Double.valueOf(20));
//            demanda.agregarDemanda(0.25, Double.valueOf(30));
//            demanda.agregarDemanda(0.22, Double.valueOf(40));
//            demanda.agregarDemanda(0.18, Double.valueOf(50));
//        } catch (ProbabilidadException ex) 
//        {
//            Logger.getLogger(Utilidades.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return demanda;
//    }
//    
//    private static Demora getDemora()
//    {
//        Demora demora = new Demora();
//        try 
//        {
//            demora.agregarDemora(0.301, Double.valueOf(0));
//            demora.agregarDemora(0.361, Double.valueOf(2));
//            demora.agregarDemora(0.217, Double.valueOf(3));
//            demora.agregarDemora(0.087, Double.valueOf(5));
//            demora.agregarDemora(0.026, Double.valueOf(6));
//            demora.agregarDemora(0.006, Double.valueOf(7));
//            demora.agregarDemora(0.001, Double.valueOf(9));
//        } catch (ProbabilidadException ex) 
//        {
//            Logger.getLogger(Utilidades.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        return demora;
//    }
}
