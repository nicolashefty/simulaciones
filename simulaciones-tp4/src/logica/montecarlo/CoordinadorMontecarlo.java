/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.montecarlo;

import logica.montecarlo.politicas.IPolitica;
import static java.lang.Math.random;
import logica.montecarlo.exceptions.ProbabilidadException;

/**
 *
 * @author heftyn
 */
public class CoordinadorMontecarlo 
{

    public static int DIA_MAXIMO = 120;

    private DatosMontecarlo datosMontecarlo;
    
    //Son necesarios??
    private EstadoMontecarlo[] vectorEstado = new EstadoMontecarlo[2];
    
    private IPolitica politica;
    
    
    public void setPolitica(IPolitica politica)
    {
        this.politica = politica;
    }
    
    public void setDatosMontecarlo(DatosMontecarlo datosMontecarlo)
    {
        this.datosMontecarlo = datosMontecarlo;
    }
    
    public Object[] getRowActual()
    {
        return vectorEstado[1].getVectorParaTabla();
    }
    
    public IPolitica getPolitica()
    {
        return politica;
    }

    public void simularDia() 
            throws ProbabilidadException
    {
        //Mover la que era row actual a la anterior y limpiar la actual
        vectorEstado[0] = vectorEstado[1];
        vectorEstado[1] = new EstadoMontecarlo();
        //Incrementar día
        vectorEstado[1].setDia(vectorEstado[0].getDia() + 1);
        
        setDemanda();
       
        setStock();

        //La politica se va a encargar de la demora y todo lo relacionado con el pedido
        try{
        politica.hacerPedido(vectorEstado);
        }catch(UnsupportedOperationException uoe)
        {
            
        }
        
        //Calcular costos
        setCostos();
        
        //Fin
    }

    private void setDemanda() 
            throws ProbabilidadException
    {
        //Generar y guardar RND Demanda
        vectorEstado[1].setRndDemanda(random());
        // Obtener demanda a partir del random
        vectorEstado[1].setCantDemandada(datosMontecarlo.getDemandaParaRandom(vectorEstado[1].getRndDemanda()));
        //Si la politica necesita la demanda acumulada, calcularla
        if (politica.calculaDemandaAcumulada())
        {
            calcularDemandaAcumulada();
        }
        else
        {
            vectorEstado[1].setDemandaAcumulada(Double.NaN);
        }
    }

    private void calcularDemandaAcumulada() {
        
        vectorEstado[1].setDemandaAcumulada(vectorEstado[0].getDemandaAcumulada() + vectorEstado[1].getCantDemandada());
    }

    private void setStock() {
        vectorEstado[1].setStock(vectorEstado[0].getStock() - vectorEstado[1].getCantDemandada());
    }

    private void setCostos() 
    {
        //El unico costo que no manejariamos aca deberia ser el de pedido que se deberia encargar la politica
        
        //Costo por Faltante:
        calcularCostoFaltante();
        
        //Costo por Almacenamiento
        calcularCostoAlmacenamiento();
        
        //Costo total del dia:
        calcularCostoTotalDelDia();
        
        //Costos Diarios Acumulados:
        calcularCostosDiariosAcumulados();
        
        //Costos Promedio Diario
        calcularCostoPromedioDiario();
    }

    private void calcularCostoFaltante() {
        if (vectorEstado[1].getStock() < 0)
        {
            vectorEstado[1].setCostoPorFaltante(-vectorEstado[1].getStock() * datosMontecarlo.getCostoFaltante());
        }
        else
        {
            vectorEstado[1].setCostoPorFaltante(0);
        }
    }

    private void calcularCostoAlmacenamiento() {
        if (vectorEstado[1].getStock() > 0)
        {
            vectorEstado[1].setCostoPorAlmacenamiento(vectorEstado[1].getStock() * datosMontecarlo.getCostoAlmacenamiento());
        }
        else
        {
            vectorEstado[1].setCostoPorAlmacenamiento(0);
        }
    }

    private void calcularCostoTotalDelDia() {
        double sum = 0;
        if (vectorEstado[1].getCostoPorPedido() >= 0)
        {
            sum += vectorEstado[1].getCostoPorPedido();
        }
        if (vectorEstado[1].getCostoPorFaltante() >= 0)
        {
            sum += vectorEstado[1].getCostoPorFaltante();
        }
        if (vectorEstado[1].getCostoPorAlmacenamiento() >= 0)
        {
            sum += vectorEstado[1].getCostoPorAlmacenamiento();
        }
        vectorEstado[1].setCostoTotalHoy(sum);
    }

    private void calcularCostosDiariosAcumulados() {
        vectorEstado[1].setCostoDiarioAcum(vectorEstado[0].getCostoDiarioAcum() + vectorEstado[1].getCostoTotalHoy());
    }

    private void calcularCostoPromedioDiario() {
        double costoProm = 0;
        costoProm = vectorEstado[1].getCostoDiarioAcum() / vectorEstado[1].getDia();
        vectorEstado[1].setCostoDiarioPromedio(costoProm);
    }

    public void inicializar() {
        vectorEstado[1] = new EstadoMontecarlo();
        vectorEstado[1].setDia(0);
        vectorEstado[1].setStock(20);
        vectorEstado[1].setDemandaAcumulada(0);
        vectorEstado[1].setDiasFaltantesParaPedir(politica.getDiasFaltantesParaPedirIniciales());
        vectorEstado[1].setCostoDiarioAcum(0);
        vectorEstado[1].setCostoDiarioPromedio(0);
    }

    /**
     * Para la ventana - grafico
     * @return 
     */
    public double getCostoPromedioActual() {
        return vectorEstado[1].getCostoDiarioPromedio();
    }
}
