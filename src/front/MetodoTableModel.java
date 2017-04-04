/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nicolashefty
 */
public class MetodoTableModel extends DefaultTableModel{
    public MetodoTableModel (Object[] objects, int rowcount)
    {
        super(objects, rowcount);
    }
    @Override
    public boolean isCellEditable(int row, int col){
        return false;
    }
}
