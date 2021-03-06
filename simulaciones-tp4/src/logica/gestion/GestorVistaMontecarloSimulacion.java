/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.gestion;

import javax.swing.JOptionPane;
import logica.montecarlo.CoordinadorMontecarlo;
import logica.montecarlo.ValoresInicialesSimulacion;
import logica.montecarlo.exceptions.NoPolicyYetException;
import logica.montecarlo.exceptions.ProbabilidadException;
import logica.montecarlo.politicas.IPolitica;
import logica.montecarlo.politicas.PoliticaA;
import logica.montecarlo.politicas.PoliticaAlternativa;
import logica.montecarlo.politicas.PoliticaB;
import logica.utilidades.Utilidades;
import vista.montecarlo.VistaMontecarloSimulacion;

/**
 *
 * @author heftyn
 */
public class GestorVistaMontecarloSimulacion {

    public static void mostrarVentanaPrincipal() {
        GestorPrincipal.mostrarVentanaPrincipal();
    }

    public void setValoresInicialesSimulacion(ValoresInicialesSimulacion valoresInicialesSimulacion) 
    {
        VistaMontecarloSimulacion vistaMontecarlo = new VistaMontecarloSimulacion();
        //populateDemandaDemora(vistaMontecarlo);
        try
        {
            double[][] costosPromedio = new double[3][120];
            for (int politica = 0 ; politica < 3 ; politica++)
            {
                CoordinadorMontecarlo coord = new CoordinadorMontecarlo();
                //vamos tmb a tener q mandarle los datos montecarlo
                coord.setPolitica(getPoliticaParaIteracion(politica));
                coord.setDatosMontecarlo(valoresInicialesSimulacion.getDatosMontecarlo());
                coord.inicializar();
                //Agregamos row 0
                vistaMontecarlo.addRowToTable(coord.getRowActual(), coord.getPolitica());
                for(int dia = 1; dia <= CoordinadorMontecarlo.DIA_MAXIMO; dia++)
                {
                    coord.simularDia();
                    //Solo lo agregamos a la tabla si esta dentro del rango
                    // osi es la ultima fila.
                    if((dia >= valoresInicialesSimulacion.getDiaDesde()
                       && dia <= valoresInicialesSimulacion.getDiaHasta()) 
                      || dia == CoordinadorMontecarlo.DIA_MAXIMO)
                    {
                        vistaMontecarlo.addRowToTable(coord.getRowActual(), coord.getPolitica());
                    }
                    costosPromedio[politica][dia-1] = coord.getCostoPromedioActual();
                    //Probablemente sin condicion le tengamos que mandar el costo promedio
                    // diario en cada iteracion para que tenga como graficarlo despues.
                }
            }
            vistaMontecarlo.addRowsToDemanda(valoresInicialesSimulacion.getDatosMontecarlo().getDemanda());
            vistaMontecarlo.addRowsToDemora(valoresInicialesSimulacion.getDatosMontecarlo().getDemora());
            vistaMontecarlo.addRowsToCosto(valoresInicialesSimulacion.getDatosMontecarlo().getCostoPedido());
            vistaMontecarlo.setCostosPromedios(costosPromedio);
        }
        catch(NoPolicyYetException|ProbabilidadException npye)
        {
            JOptionPane.showMessageDialog(null, npye.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        vistaMontecarlo.showPoliticaElegida();
        vistaMontecarlo.setVisible(true);
        //Y angora?
    }

    private IPolitica getPoliticaParaIteracion(int i) throws NoPolicyYetException {
        switch(i)
        {
            case 0:
            {
                return new PoliticaA();
            }
            case 1:
            {
                return new PoliticaB();
            }
            case 2:
            {
                return new PoliticaAlternativa();
            }
        }
        throw new NoPolicyYetException("Aun no tenemos esta politica!");
    }
    
    }
