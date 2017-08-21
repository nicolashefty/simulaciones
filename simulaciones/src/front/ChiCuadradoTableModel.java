/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front;

import javax.swing.table.*;

/**
 *
 * @author nicolashefty
 */
public class ChiCuadradoTableModel extends DefaultTableModel
{
    public static final int COL_INTERVALO = 0;
    public static final int COL_NUM_INT = 1;
    public static final int COL_FREC_OBSERVADA = 2;
    public static final int COL_FREC_ESPERADA = 3;
    public static final int COL_ESTADISTICO = 4;
    
    
    
    public ChiCuadradoTableModel(Object[] colNames, int rowCount)
    {
        super(colNames, rowCount);
    }
    
    public void setAllDisabled()
    {
        //DO IT
    }
    
    @Override
    public boolean isCellEditable(int row, int col){
        return false;
    }
}
