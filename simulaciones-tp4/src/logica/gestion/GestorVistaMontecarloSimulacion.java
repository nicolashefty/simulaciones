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

    void setValoresInicialesSimulacion(ValoresInicialesSimulacion valoresInicialesSimulacion) 
    {
        VistaMontecarloSimulacion vistaMontecarlo = new VistaMontecarloSimulacion();
        try
        {
            for (int i = 0 ; i < 3 ; i++)
            {
                CoordinadorMontecarlo coord = new CoordinadorMontecarlo();
                //vamos tmb a tener q mandarle los datos montecarlo
                coord.setPolitica(getPoliticaParaIteracion(i));
                coord.setDatosMontecarlo(Utilidades.getDatosMontecarlo());
                for(int dia = 0; dia < CoordinadorMontecarlo.DIA_MAXIMO; i++)
                {
                    coord.simularDia();
                    //Solo lo agregamos a la tabla si esta dentro del rango
                    // osi es la ultima fila.
                    if((i > valoresInicialesSimulacion.getDiaDesde()
                       && i < valoresInicialesSimulacion.getDiaHasta()) 
                      || i == CoordinadorMontecarlo.DIA_MAXIMO -1 )
                    {
                        vistaMontecarlo.addRowToTable(coord.getRowActual(), coord.getPolitica());
                    }
                    
                    //Probablemente sin condicion le tengamos que mandar el costo promedio
                    // diario en cada iteracion para que tenga como graficarlo despues.
                }
            }
        }
        catch(NoPolicyYetException npye)
        {
            JOptionPane.showMessageDialog(null, npye.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
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
