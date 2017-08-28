/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.*;
import jfree.*;
import objects.Controller;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.category.*;
import org.jfree.data.category.*;
import org.jfree.ui.*;

/**
 *
 * @author federico
 */
public class TestTable extends javax.swing.JFrame {

    Controller controller;
    int contador;
    float[] valoresGenerados;
    int cantIntervalos;

    public TestTable(Controller cont, int[][] response, float[] vec, float rango, int cantIntervalos) {

        controller = cont;
        contador = response.length;
        initComponents();

        DecimalFormat in = new DecimalFormat("0.00");
        DecimalFormat aleat = new DecimalFormat("0.0000");
        DecimalFormat c = new DecimalFormat("0.000");
        int contador = 0;
        int frecEsp = (int) vec.length / cantIntervalos;
        double rangoM;
        float[][] matrizIntervalos = new float[cantIntervalos][2];
        double[] estadisticoParcial = new double[cantIntervalos];
        DefaultTableModel tm = (DefaultTableModel) table.getModel();

        for (int i = 0; i < response.length; i++) {
            if (i == 0) {
                rangoM = rango;
                tm.addRow(new Object[]{"0.00 - " + in.format(rangoM), i, response[i][1], frecEsp, c.format(estadisticoPrueba(response, frecEsp, i))});
                contador++;
                matrizIntervalos[0][0] = 0;
                matrizIntervalos[0][1] = (float) rangoM;

            } else {
                if (contador == 1) {
                    tm.addRow(new Object[]{in.format(rango) + " - " + in.format(rango + rango), i, response[i][1], frecEsp, c.format(estadisticoPrueba(response, frecEsp, i))});

                    matrizIntervalos[i][0] = rango;
                    matrizIntervalos[i][1] = (float) rango + rango;
                    contador++;

                } else {
                    tm.addRow(new Object[]{in.format(rango * contador) + " - " + in.format((rango * contador) + rango), i, response[i][1], frecEsp, c.format(estadisticoPrueba(response, frecEsp, i))});
                    matrizIntervalos[i][0] = rango * contador;
                    matrizIntervalos[i][1] = (rango * contador) + rango;
                    contador++;
                }

            }
        }

        if (frecEsp < 5) {
            DefaultTableModel tm2 = (DefaultTableModel) tablaFe.getModel();
            double estadisticoTotal = 0;
            int frecEspAcumulada = 0;
            int frecObsAcumulada = 0;
            int inicio = 0;
            float a = 0;

            for (int i = 0; i < tm.getRowCount(); i++) {
                frecEspAcumulada += (int) tm.getValueAt(i, 2);
                frecObsAcumulada += (int) tm.getValueAt(i, 1);
                
                if (frecEspAcumulada >= 5) {
                    estadisticoParcial[i] += estadisticoPrueba2(frecObsAcumulada, (int) frecEspAcumulada);
                    tm2.addRow(new Object[]{in.format(matrizIntervalos[inicio][0]) + " - " + in.format(matrizIntervalos[i][1]), (int) frecObsAcumulada, frecEspAcumulada, c.format(estadisticoParcial[i])});
                    a = matrizIntervalos[inicio][0];
                    inicio = i + 1;

                    frecEspAcumulada = 0;
                    frecObsAcumulada = 0;
                } else {
                    if (i == tm.getRowCount() - 1) {
                        int filaAUnir = tm2.getRowCount() - 1;
                        //actualizo intervalo
                        tm2.setValueAt(in.format(a) + " - " + in.format(matrizIntervalos[i][1]), filaAUnir, 0);
                        //frec observada
                        tm2.setValueAt(frecObsAcumulada + (int) tm2.getValueAt(filaAUnir, 1), filaAUnir, 1);
                        //frec esperada
                        tm2.setValueAt(frecEspAcumulada + (int) tm2.getValueAt(filaAUnir, 2), filaAUnir, 2);

                        int frecObsUltima = (int) tm2.getValueAt(filaAUnir, 1);
                        int frecEspUltima = (int) tm2.getValueAt(filaAUnir, 2);
                        
                        estadisticoParcial[i - 1] = estadisticoPrueba2(frecObsUltima, frecEspUltima);
                        tm2.setValueAt(estadisticoParcial[i - 1], filaAUnir, 3);
                        break;
                    }
                }
            }

            for (int i = 0; i < estadisticoParcial.length; i++) {
                estadisticoTotal += estadisticoParcial[i];
            }
            txt_gradosNuevo.setText("" + gradosLibertad(tm2.getRowCount()));
            txt_estadisticoNuevo.setText("" + c.format(estadisticoTotal));

            //para mostrar los valores generados
            String acum = "";
            for (int i = 0; i < vec.length; i++) {
                acum += "Valor " + (i + 1) + ": " + aleat.format(vec[i]) + ".\n";
            }
            txt_valoresGenerados.setText(acum);

            //para el calculo de mi estadistico de prueba total
            txt_estadistico.setText("" + c.format(estadisticoPruebaTotal(response, frecEsp)));
            txt_grados.setText("" + gradosLibertad(cantIntervalos));

            valoresGenerados = vec;
            this.cantIntervalos = cantIntervalos;
            agregarHistograma();
        }
    }

    public double estadisticoPrueba(int[][] response, int frecEsp, int loop) {
        double res = 0;//(Math.pow((response[i][1]-frecEsp),2))/frecEsp;
        res = (double) (Math.pow(response[loop][1] - frecEsp, 2)) / frecEsp;
        return res;
    }

    public double estadisticoPrueba2(float frecObs, int frecEsp) {
        return (Math.pow(frecObs - frecEsp, 2))/frecEsp;
    }

    public int gradosLibertad(int intervalo) {
        return intervalo - 0 - 1;
    }

    public double estadisticoPruebaTotal(int[][] response, int frecEsp) {
        double res = 0, a = 0;//(Math.pow((response[i][1]-frecEsp),2))/frecEsp;
        for (int i = 0; i < response.length; i++) {
            a += (double) (Math.pow(response[i][1] - frecEsp, 2)) / frecEsp;
        }
        res = a;
        return res;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_valoresGenerados = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        txt_estadistico = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_grados = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        panelHistograma = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaFe = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_estadisticoNuevo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_gradosNuevo = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Prueba de chi cuadrado");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numeros del intervalo", "Intervalo", "Frecuencia", "Frecuencia esperada", "Estadistico de prueba"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);

        txt_valoresGenerados.setEditable(false);
        txt_valoresGenerados.setColumns(20);
        txt_valoresGenerados.setRows(5);
        jScrollPane2.setViewportView(txt_valoresGenerados);

        jLabel1.setText("Valores Generados");

        txt_estadistico.setEditable(false);
        txt_estadistico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_estadisticoActionPerformed(evt);
            }
        });

        jLabel2.setText("Estadistico de prueba total:");

        jLabel3.setText("Grados de libertad:");

        txt_grados.setEditable(false);

        jButton1.setText("Volver");
        jButton1.setToolTipText("");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        panelHistograma.setPreferredSize(new java.awt.Dimension(800, 800));
        panelHistograma.setLayout(new java.awt.BorderLayout());

        tablaFe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No Intervalo", "Frecuencia", "Frecuencia Esperada", "Estadistico de prueba"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tablaFe);

        jLabel4.setText("Si la frecuencia esperada es menor que 5");

        jLabel5.setText("Estadistico de prueba total:");

        txt_estadisticoNuevo.setEditable(false);
        txt_estadisticoNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_estadisticoNuevoActionPerformed(evt);
            }
        });

        jLabel6.setText("Grados de libertad:");

        txt_gradosNuevo.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_estadistico, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_grados, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_estadisticoNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(40, 40, 40)
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_gradosNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel4))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(438, 438, 438)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(panelHistograma, javax.swing.GroupLayout.PREFERRED_SIZE, 1053, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_estadistico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(txt_grados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_estadisticoNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel6)
                        .addComponent(txt_gradosNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(panelHistograma, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowActivated

    private void txt_estadisticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_estadisticoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_estadisticoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton1ActionPerformed
    {//GEN-HEADEREND:event_jButton1ActionPerformed
        // TODO add your handling code here:
        controller.volverDeTestRandomJava();
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_estadisticoNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_estadisticoNuevoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_estadisticoNuevoActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel panelHistograma;
    private javax.swing.JTable tablaFe;
    private javax.swing.JTable table;
    private javax.swing.JTextField txt_estadistico;
    private javax.swing.JTextField txt_estadisticoNuevo;
    private javax.swing.JTextField txt_grados;
    private javax.swing.JTextField txt_gradosNuevo;
    private javax.swing.JTextArea txt_valoresGenerados;
    // End of variables declaration//GEN-END:variables

    private void agregarHistograma() {
        // Tenemos que convertir los numeros generados a un vector de double.
        double[] valoresGeneradosEnDouble = obtenerValoresEnDouble();
        Histograma histograma = new Histograma("Frecuencias del generador", valoresGeneradosEnDouble, cantIntervalos);
        JPanel histoPanel = histograma.obtenerPanel();
        histoPanel.setVisible(true);
        panelHistograma.add(histoPanel);
        panelHistograma.validate();
    }

    private double[] obtenerValoresEnDouble() {
        double[] ret = new double[valoresGenerados.length];
        for (int i = 0; i < valoresGenerados.length; i++) {
            ret[i] = (double) valoresGenerados[i];
        }
        return ret;
    }
}
