/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.montecarlo;

import logica.montecarlo.demora.Demora;
import logica.montecarlo.demanda.Demanda;
import logica.montecarlo.costoPedido.CostoPedido;

/**
 *
 * @author heftyn
 */
public class DatosMontecarlo {
    private double costoMantenimiento = 0;
    private double costoFaltante = 0;
    private double stockInicial = 0;
    private CostoPedido costoPedido;
    private Demora demora;
    private Demanda demanda;

    public double getCostoMantenimiento() {
        return costoMantenimiento;
    }

    public void setCostoMantenimiento(double costoMantenimiento) {
        this.costoMantenimiento = costoMantenimiento;
    }

    public double getCostoFaltante() {
        return costoFaltante;
    }

    public void setCostoFaltante(double costoFaltante) {
        this.costoFaltante = costoFaltante;
    }

    public double getStockInicial() {
        return stockInicial;
    }

    public void setStockInicial(double stockInicial) {
        this.stockInicial = stockInicial;
    }

    public CostoPedido getCostoPedido() {
        return costoPedido;
    }

    public void setCostoPedido(CostoPedido costoPedido) {
        this.costoPedido = costoPedido;
    }

    public Demora getDemora() {
        return demora;
    }

    public void setDemora(Demora demora) {
        this.demora = demora;
    }

    public Demanda getDemanda() {
        return demanda;
    }

    public void setDemanda(Demanda demanda) {
        this.demanda = demanda;
    }
    
    
}
