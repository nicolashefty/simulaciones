/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.montecarlo;

import static logica.utilidades.Utilidades.*;
/**
 *
 * @author heftyn
 */
public class EstadoMontecarlo {
    private int dia;
    private double rndDemanda;
    private double cantDemandada;
    private double stock;
    private double rndDemora;
    private double diasDemora;
    private double demandaAcumulada;
    private double diasFaltantesParaPedir;
    private boolean hayPedidoEnCurso;
    private double cantPedida1;
    private int diaLlegadaPedido1;
    private double cantPedida2;
    private int diaLlegadaPedido2;
    
    private double costoPorFaltante;
    private double costoPorPedido;
    private double costoPorAlmacenamiento;
    
    private double costoTotalHoy;
    private double costoDiarioAcum;
    private double costoDiarioPromedio;

    public String[] getVectorParaTabla()
    {
        return new String[]{
        "" + dia, formatRND(rndDemanda), aEntero(cantDemandada), aEntero(stock),
            formatRND(rndDemora), aEntero(diasDemora), aEntero(demandaAcumulada),
            aEntero(diasFaltantesParaPedir), hayPedidoEnCurso?"SI":"NO",
            aEntero(cantPedida1), diaLlegadaPedido1 >= 0 ? "" + diaLlegadaPedido1 : "N/A",
            aEntero(cantPedida2), diaLlegadaPedido2 >= 0 ? "" + diaLlegadaPedido2 : "N/A",
            aCosto(costoPorFaltante), aCosto(costoPorPedido), aCosto(costoPorAlmacenamiento),
            aCosto(costoTotalHoy), aCosto(costoDiarioAcum), aCosto(costoDiarioPromedio)
        };
    }
    
    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public double getRndDemanda() {
        return rndDemanda;
    }

    public void setRndDemanda(double rndDemanda) {
        this.rndDemanda = rndDemanda;
    }

    public double getCantDemandada() {
        return cantDemandada;
    }

    public void setCantDemandada(double cantDemandada) {
        this.cantDemandada = cantDemandada;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public double getRndDemora() {
        return rndDemora;
    }

    public void setRndDemora(double rndDemora) {
        this.rndDemora = rndDemora;
    }

    public double getDiasDemora() {
        return diasDemora;
    }

    public void setDiasDemora(double diasDemora) {
        this.diasDemora = diasDemora;
    }

    public double getDemandaAcumulada() {
        return demandaAcumulada;
    }

    public void setDemandaAcumulada(double demandaAcumulada) {
        this.demandaAcumulada = demandaAcumulada;
    }

    public double getDiasFaltantesParaPedir() {
        return diasFaltantesParaPedir;
    }

    public void setDiasFaltantesParaPedir(double diasFaltantesParaPedir) {
        this.diasFaltantesParaPedir = diasFaltantesParaPedir;
    }

    public boolean isHayPedidoEnCurso() {
        return hayPedidoEnCurso;
    }

    public void setHayPedidoEnCurso(boolean hayPedidoEnCurso) {
        this.hayPedidoEnCurso = hayPedidoEnCurso;
    }

    public double getCantPedida1() {
        return cantPedida1;
    }

    public void setCantPedida1(double cantPedida1) {
        this.cantPedida1 = cantPedida1;
    }

    public int getDiaLlegadaPedido1() {
        return diaLlegadaPedido1;
    }

    public void setDiaLlegadaPedido1(int diaLlegadaPedido1) {
        this.diaLlegadaPedido1 = diaLlegadaPedido1;
    }

    public double getCantPedida2() {
        return cantPedida2;
    }

    public void setCantPedida2(double cantPedida2) {
        this.cantPedida2 = cantPedida2;
    }

    public int getDiaLlegadaPedido2() {
        return diaLlegadaPedido2;
    }

    public void setDiaLlegadaPedido2(int diaLlegadaPedido2) {
        this.diaLlegadaPedido2 = diaLlegadaPedido2;
    }

    public double getCostoPorFaltante() {
        return costoPorFaltante;
    }

    public void setCostoPorFaltante(double costoPorFaltante) {
        this.costoPorFaltante = costoPorFaltante;
    }

    public double getCostoPorPedido() {
        return costoPorPedido;
    }

    public void setCostoPorPedido(double costoPorPedido) {
        this.costoPorPedido = costoPorPedido;
    }

    public double getCostoPorAlmacenamiento() {
        return costoPorAlmacenamiento;
    }

    public void setCostoPorAlmacenamiento(double costoPorAlmacenamiento) {
        this.costoPorAlmacenamiento = costoPorAlmacenamiento;
    }

    public double getCostoTotalHoy() {
        return costoTotalHoy;
    }

    public void setCostoTotalHoy(double costoTotalHoy) {
        this.costoTotalHoy = costoTotalHoy;
    }

    public double getCostoDiarioAcum() {
        return costoDiarioAcum;
    }

    public void setCostoDiarioAcum(double costoDiarioAcum) {
        this.costoDiarioAcum = costoDiarioAcum;
    }

    public double getCostoDiarioPromedio() {
        return costoDiarioPromedio;
    }

    public void setCostoDiarioPromedio(double costoDiarioPromedio) {
        this.costoDiarioPromedio = costoDiarioPromedio;
    }
    
    
}
