/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.gestion;

import vista.principal.*;
/**
 *
 * @author heftyn
 */
public class GestorPrincipal {
    
    public static void main(String args[])
    {
        GestorVistaMontecarloSimulacion gestorMontec = new GestorVistaMontecarloSimulacion();
        VistaPrincipal ventana = new VistaPrincipal(gestorMontec);
    }
}
