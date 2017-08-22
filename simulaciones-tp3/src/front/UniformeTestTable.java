/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front;

import java.text.DecimalFormat;
import javax.swing.*;
import javax.swing.table.*;
import jfree.*;
import objects.Controller;
import objects.Uniforme;
import objects.Calculator;

/**
 *
 * @author federico
 */
public class UniformeTestTable extends javax.swing.JFrame {

    Controller controller;
    int contador;
    Calculator calculator = new Calculator();
    Uniforme valores;
    int intervalos;

    //public UniformeTestTable(Controller cont, int[][] response, float[]vec, float rango, int cantIntervalos) {
    public UniformeTestTable(Controller cont, Uniforme uniformeValues, int cantIntervalos) {
        valores = uniformeValues;
        intervalos = cantIntervalos;
        float rango = calcularRango(uniformeValues.getDesde(), uniformeValues.getHasta(), intervalos);
        controller = cont;
        float[][][] matriz = calculator.matrizFrecuenciaUniforme(uniformeValues, rango, intervalos);

        initComponents();
        DecimalFormat in = new DecimalFormat("0.00");
        DecimalFormat c = new DecimalFormat("0.000");
        int contador = 0;
        int frecEsp = (int) uniformeValues.getVecValores().length / intervalos;

        DefaultTableModel tm = (DefaultTableModel) table.getModel();
        double estadisticoAcumulado=0;
        for (int i = 0; i < matriz.length; i++) {
            double estadisticoIntervalo = estadisticoPrueba(matriz[i][i][2], frecEsp);
            estadisticoAcumulado += estadisticoIntervalo;
            tm.addRow(new Object[]{in.format(matriz[i][i][0]) + " - " + in.format(matriz[i][i][1]), i + 1, (int) matriz[i][i][2], frecEsp, c.format(estadisticoIntervalo)});
        }

        String valoresGenerados = valoresGenerados(uniformeValues);
        txt_valoresGenerados.setText(valoresGenerados);
        //para el calculo de mi estadistico de prueba total
        txt_grados.setText("" + gradosLibertad(tm.getRowCount()));
        //txt_estadistico.setText("" + c.format(estadisticoPruebaTotal(tm)));
        txt_estadistico.setText("" + c.format(estadisticoAcumulado));
        if (frecEsp < 5) {
            DefaultTableModel tm2 = (DefaultTableModel) tablaFE.getModel();
            double estadisticoTotal = 0;
            int frecEspAcumulada = 0;
            int frecObsAcumulada = 0;
            int inicio = 0;

            for (int i = 0; i < tm.getRowCount(); i++) {
                frecEspAcumulada += (int) tm.getValueAt(i, 3);
                frecObsAcumulada += matriz[i][i][2];
                if (frecEspAcumulada >= 5) {
                    estadisticoTotal += estadisticoPrueba(frecObsAcumulada, (int) frecEspAcumulada);
                    tm2.addRow(new Object[]{in.format(matriz[inicio][inicio][0]) + " - " + in.format(matriz[i][i][1]), (int) frecObsAcumulada, frecEspAcumulada, c.format(estadisticoTotal)});
                    inicio = i + 1;
                    frecEspAcumulada = 0;
                    frecObsAcumulada = 0;
                } else // ultima vuelta y no suma 5, le appendeo lo acumulado a la ultima fila que armé
                {
                    if (i == tm.getRowCount() - 1) {
                        int filaAUnir = tm2.getRowCount() - 1;
                        //actualizo intervalo
                        tm2.setValueAt(in.format(matriz[inicio][inicio][0]) + " - " + in.format(matriz[i][i][1]), filaAUnir, 0);
                        //frec observada
                        tm2.setValueAt(frecObsAcumulada + (int) tm2.getValueAt(filaAUnir, 1), filaAUnir, 1);
                        //frec esperada
                        tm2.setValueAt(frecEspAcumulada + (int) tm2.getValueAt(filaAUnir, 2), filaAUnir, 2);
                       
                        int frecObsUltima = (int) tm2.getValueAt(filaAUnir, 1);
                        int frecEspUltima = (int) tm2.getValueAt(filaAUnir, 2);
                        
                        tm2.setValueAt(estadisticoPrueba(frecObsUltima, frecEspUltima), filaAUnir, 3);
                        estadisticoTotal +=estadisticoPrueba(frecObsUltima, frecEspUltima);
                        break;
                    }
                }
            }
            _gradosLib_agrupado.setText("" + gradosLibertad(tm2.getRowCount()));
            txt_nuevo_estadistico.setText("" + c.format(estadisticoTotal));
        }

//        agregarHistograma(intervalos, uniformeValues);
        this.setVisible(true);
    }

    public double estadisticoPrueba(float frecObs, int frecEsp) {
        return (double) (Math.pow(frecObs - frecEsp, 2)) / frecEsp;
    }

    public int gradosLibertad(int intervalo) {
        return intervalo - 0 - 1;
    }

    public double estadisticoPruebaTotal(DefaultTableModel tm) {
        DecimalFormat c = new DecimalFormat("0.000");
        int paraTabla = 4;
        //si es la primer tabla suma los de la columna 4, si es la segunda tabla suma los de la columna 3
        if (tm.getColumnCount() == 4) {
            paraTabla = 3;
        }
        double res = 0;
        String output;
        for (int i = 0; i < tm.getRowCount(); i++) {
          // output += (String)tm.getValueAt(i, paraTabla);
        }
        return res;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

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
        scfe = new javax.swing.JScrollPane();
        tablaFE = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_nuevo_estadistico = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        _gradosLib_agrupado = new javax.swing.JTextField();
        _btnGrafico = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Prueba de chi cuadrado");
        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowActivated(java.awt.event.WindowEvent evt)
            {
                formWindowActivated(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "Numeros del intervalo", "Intervalo", "Frecuencia", "Frecuencia esperada", "Estadistico de prueba"
            }
        )
        {
            boolean[] canEdit = new boolean []
            {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
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
        txt_estadistico.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                txt_estadisticoActionPerformed(evt);
            }
        });

        jLabel2.setText("Estadistico de prueba total:");

        jLabel3.setText("Grados de libertad:");

        txt_grados.setEditable(false);

        jButton1.setText("Volver");
        jButton1.setToolTipText("");
        jButton1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton1ActionPerformed(evt);
            }
        });

        tablaFE.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "Intervalo", "Frecuencia observada", "Frecuencia esperada", "Estadistico de prueba"
            }
        )
        {
            boolean[] canEdit = new boolean []
            {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit [columnIndex];
            }
        });
        scfe.setViewportView(tablaFE);

        jLabel4.setText("Si la frecuencia esperada es menor a 5");

        jLabel5.setText("Estadistico de prueba total:");

        txt_nuevo_estadistico.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                txt_nuevo_estadisticoActionPerformed(evt);
            }
        });

        jLabel6.setText("Grados de Libertad:");

        _btnGrafico.setText("Ver Grafico");
        _btnGrafico.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                _btnGraficoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_estadistico, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_grados, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scfe)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_nuevo_estadistico, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(116, 116, 116)
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(_gradosLib_agrupado, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(416, 416, 416)
                .addComponent(jButton1)
                .addGap(231, 231, 231)
                .addComponent(_btnGrafico)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1))
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scfe, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_estadistico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(txt_grados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txt_nuevo_estadistico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(_gradosLib_agrupado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(_btnGrafico))
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
        //  controller.volverDeTestRandomJava();
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_nuevo_estadisticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nuevo_estadisticoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nuevo_estadisticoActionPerformed

    private void _btnGraficoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event__btnGraficoActionPerformed
    {//GEN-HEADEREND:event__btnGraficoActionPerformed
        agregarHistograma(intervalos, valores);
    }//GEN-LAST:event__btnGraficoActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton _btnGrafico;
    private javax.swing.JTextField _gradosLib_agrupado;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane scfe;
    private javax.swing.JTable tablaFE;
    private javax.swing.JTable table;
    private javax.swing.JTextField txt_estadistico;
    private javax.swing.JTextField txt_grados;
    private javax.swing.JTextField txt_nuevo_estadistico;
    private javax.swing.JTextArea txt_valoresGenerados;
    // End of variables declaration//GEN-END:variables

    private void agregarHistograma(int cantIntervalos, Uniforme uniformeValues) {
        // Tenemos que convertir los numeros generados a un vector de double.
        Histograma histograma = new Histograma("Histograma Distribucion Uniforme",
        "Frecuencia de numeros aleatorios", obtenerValoresEnDouble(uniformeValues), cantIntervalos, uniformeValues.getDesde(), uniformeValues.getHasta());
//        JPanel histoPanel = histograma.obtenerPanel();
//        histoPanel.setVisible(true);
//        panelHistograma.add(histoPanel);
//        panelHistograma.validate();
    }

    private double[] obtenerValoresEnDouble(Uniforme uniformeValues) {
        float valoresAleatorios[] = uniformeValues.getVecValores();
        double[] ret = new double[valoresAleatorios.length];
        for (int i = 0; i < valoresAleatorios.length; i++) {
            ret[i] = (double) valoresAleatorios[i];
        }
        return ret;
    }

    private float calcularRango(int desde, int hasta, int cantIntervalos) {
        return (float)(hasta - desde) / cantIntervalos;
    }

    private String valoresGenerados(Uniforme uniformeValues) {
        //para mostrar los valores generados
        String acum = "";
        DecimalFormat aleat = new DecimalFormat("0.0000");
        float[] numerosAleatorios = uniformeValues.getVecValores();
        for (int i = 0; i < numerosAleatorios.length; i++) {
            acum += "Valor " + (i + 1) + ": " + aleat.format(numerosAleatorios[i]) + ".\n";
        }
        return acum;
    }
}
