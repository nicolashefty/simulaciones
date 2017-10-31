/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.tablas;

import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import logica.gestion.Simulador;

/**
 *
 * @author heftyn
 */
public class VentanaColas extends javax.swing.JFrame {

    /**
     * Creates new form VentanaColas
     */
    public VentanaColas(int diaInicio, int diaFin) {
                initComponents();
                
                acomodarTablas();
                System.out.println(System.currentTimeMillis());
                Runnable thread1 = new Runnable() {
                    @Override
                    public void run() {
                        new Simulador(LocalTime.NOON, tbl_politica_principal, diaInicio, diaFin);
                    }
                };
                new Thread(thread1).start();
                Runnable runn2 = new Runnable() {
                    @Override
                    public void run() {
                        new Simulador(LocalTime.of(5,0,0), tbl_politica_alternativa, diaInicio, diaFin);
                    }
                };
                Thread thread2 = new Thread(runn2);
                thread2.start();
        try 
        {
            thread2.join();
        } 
        catch (InterruptedException ex) 
        {
            Logger.getLogger(VentanaColas.class.getName()).log(Level.SEVERE, null, ex);
        }
                
                System.out.println(System.currentTimeMillis());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        vector_estado = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_politica_principal = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_politica_alternativa = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        vector_estado.setBorder(javax.swing.BorderFactory.createTitledBorder("Vector de estado"));

        tbl_politica_principal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbl_politica_principal.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane2.setViewportView(tbl_politica_principal);

        vector_estado.addTab("Politica principal", jScrollPane2);

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tbl_politica_alternativa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbl_politica_alternativa.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane3.setViewportView(tbl_politica_alternativa);

        vector_estado.addTab("Politica alternativa", jScrollPane3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(vector_estado, javax.swing.GroupLayout.DEFAULT_SIZE, 1143, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(vector_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tbl_politica_alternativa;
    private javax.swing.JTable tbl_politica_principal;
    private javax.swing.JTabbedPane vector_estado;
    // End of variables declaration//GEN-END:variables

    private void acomodarTablas() 
    {
        jScrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    }
}
