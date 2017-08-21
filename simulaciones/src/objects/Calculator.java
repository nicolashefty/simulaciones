/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

/**
 *
 * @author nicolashefty
 */
public class Calculator {

    public static String[][] calculate(Datos datos, int loops) {
        //0 : Xi
        //1 : Xi+1
        //2 : Num Aleat (entre 0 y 1)
        String[][] seeds = new String[loops][3];
        for (int i = 0; i < loops; i++) 
        {
            seeds[i][0] = String.valueOf(datos.getSeed());
            datos.setSeed(((datos.getA() * datos.getSeed()) + datos.getB()) % datos.getM());
            seeds[i][1] = String.valueOf(datos.getSeed());
            float div = ((float)datos.getSeed()) / ((float)datos.getM());
            seeds[i][2] = String.valueOf(formatRandomNumber(div));
        }
        return seeds;
    }
    
    public static String formatRandomNumber(float nbr)
    {
        String rv = String.valueOf(nbr);
        if (rv != null && rv.indexOf('.') > -1)
        {
            int pos = rv.indexOf('.');
            if (rv.length() > pos + 5)
            {
                rv = rv.substring(0, pos+5);
            }
        }
        return rv;
    }
}
