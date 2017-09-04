/*
 * To change this license header, choose License Headers formatIntervalos Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template formatIntervalos the editor.
 */
package front.exponencial;

import java.text.*;
import javax.swing.*;
import javax.swing.table.*;
import jfree.*;
import objects.*;

/**
 *
 * @author nicolashefty
 */
public class ExponencialTestTable extends javax.swing.JFrame {

    Controller controller;
    float rango;
    float minimo, maximo;
    int N;
    float[] numsAleatorios;
    
    private final static int COL_DESDE = 0;
    private final static int COL_HASTA = 1;
    private final static int COL_FREC_OBS = 2;
    private final static int COL_PROB = 3;
    private final static int COL_FREC_ESP = 4;
    private final static int COL_ESTAD = 5;

    private final float media;

    private final float lambda;
    
    private int cantIntervalos;
    private static final int IND_MATRIZ_DESDE = 0;
    private static final int IND_MATRIZ_HASTA = 1;
    private static final int IND_MATRIZ_FREC = 2;

    /**
     * Este constructor se utiliza para crear la instancia de la ventana desde el generador.
     * @param parent el generador
     * @param cont el controlador de la app
     * @param numsAleatorios los numeros aleatorios
     * @param datosDistribucion datosDistribucion como ser N y la media.
     * @param intervalos la cantidad de intervalos
     */
    public ExponencialTestTable(JFrame parent, Controller cont, float[] numsAleatorios, String[] datosDistribucion, int intervalos) 
    {
        cantIntervalos = intervalos;
        this.numsAleatorios = numsAleatorios;
        float[] ordenadosValues = new float[this.numsAleatorios.length];
        //Dejará en odenadosValues el array con los numeros aleatorios ordenados de menor a mayor.
        System.arraycopy(this.numsAleatorios, 0, ordenadosValues, 0, this.numsAleatorios.length);
        rango = calcularRango(ordenadosValues, cantIntervalos);
        controller = cont;
        float[][] matrizFrecuencia = Calculator.matrizFrecuencia(ordenadosValues, rango, cantIntervalos, minimo);
        N = Integer.parseInt(datosDistribucion[0]);
        media = Float.parseFloat(datosDistribucion[1]);
        lambda = (float) 1/media;
        

        initComponents();
        DecimalFormat formatIntervalos = new DecimalFormat("0.00");
        DecimalFormat formatEstadistico = new DecimalFormat("0.000");
        DefaultTableModel modelo = (DefaultTableModel) _tabla.getModel();

        for (int i = 0; i < matrizFrecuencia.length; i++)
        {
            float probabilidad = calcularProbabilidad(matrizFrecuencia[i][0],matrizFrecuencia[i][1]);
            float frecEsp = calcularFrecuenciaEsperada(probabilidad);
            double estadistico = estadisticoPrueba(matrizFrecuencia[i][2], frecEsp);
            modelo.addRow(new Object[]
            {
                formatIntervalos.format(matrizFrecuencia[i][IND_MATRIZ_DESDE]),
                formatIntervalos.format(matrizFrecuencia[i][IND_MATRIZ_HASTA]),
                matrizFrecuencia[i][IND_MATRIZ_FREC], 
                formatEstadistico.format(probabilidad),
                formatEstadistico.format(frecEsp),
                formatEstadistico.format(estadistico)
            });
        }
        
        agregarTablaAcumulada();
        
        String valoresGenerados = obtenerValoresGeneradosParaMostrar(this.numsAleatorios);
        txt_valoresGenerados.setText(valoresGenerados);

        //para el calculo de mi estadistico de prueba total
        txt_estadistico.setText("" + formatEstadistico.format(estadisticoPruebaTotal()));
        txt_grados.setText("" + gradosLibertad(cantIntervalos));

        //     valoresGenerados = vec;
//        agregarHistograma(numsAleatorios);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    public double estadisticoPrueba(float frecObs, float frecEsp) 
    {
       return (double) (Math.pow(frecObs - frecEsp, 2)) / frecEsp;
    }

    public int gradosLibertad(int intervalos) 
    {
        //Restamos uno por definicion
        // y uno mas por el parametro lambda
        return intervalos - 1 - 1;
    }

    public double estadisticoPruebaTotal() 
    {
        double estadisticoTotal = 0;
        DefaultTableModel tm = (DefaultTableModel) _tabla.getModel();
        for (int i = 0; i < tm.getRowCount(); i++)
        {
            Double estadistico = 0.0;
            if (tm.getValueAt(i, 5) instanceof String)
            {
                String estadStr = (String) tm.getValueAt(i, 5);
                if (estadStr.indexOf(',') > 0)
                {
                    //Si tiene coma genera error de parseo 
                    estadistico = Double.parseDouble(estadStr.replace(',', '.'));
                }
            }
            estadisticoTotal += estadistico;
        }
        return estadisticoTotal;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        txt_valoresGenerados = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        txt_estadistico = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_grados = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_nuevo_estadistico = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        _gradosLib_agrupado = new javax.swing.JTextField();
        _scpTabla = new javax.swing.JScrollPane();
        _tabla = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        _tablaAcumulada = new javax.swing.JTable();
        _btnGrafico = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Prueba de chi cuadrado");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

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

        jLabel4.setText("Si la frecuencia esperada es menor a 5");

        jLabel5.setText("Estadistico de prueba total:");

        txt_nuevo_estadistico.setEditable(false);
        txt_nuevo_estadistico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nuevo_estadisticoActionPerformed(evt);
            }
        });

        jLabel6.setText("Grados de Libertad:");

        _gradosLib_agrupado.setEditable(false);

        _tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Desde", "Hasta", "Frec Observada", "Probabilidad", "Frec Esperada", "Estadistico"
            }
        ));
        _scpTabla.setViewportView(_tabla);

        _tablaAcumulada.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Desde", "Hasta", "Frec Obs", "Prob", "Frec Esp", "Estadistico"
            }
        ));
        jScrollPane1.setViewportView(_tablaAcumulada);

        _btnGrafico.setText("Ver Grafico");
        _btnGrafico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _btnGraficoActionPerformed(evt);
            }
        });

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
                                .addGap(8, 8, 8)
                                .addComponent(_scpTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_nuevo_estadistico, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(116, 116, 116)
                                        .addComponent(jLabel6)
                                        .addGap(18, 18, 18)
                                        .addComponent(_gradosLib_agrupado, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel4)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(385, 385, 385)
                        .addComponent(jButton1)
                        .addGap(146, 146, 146)
                        .addComponent(_btnGrafico)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                            .addComponent(_scpTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_estadistico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(txt_grados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txt_nuevo_estadistico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(_gradosLib_agrupado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
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
        mostrarHistograma(numsAleatorios);
    }//GEN-LAST:event__btnGraficoActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton _btnGrafico;
    private javax.swing.JTextField _gradosLib_agrupado;
    private javax.swing.JScrollPane _scpTabla;
    private javax.swing.JTable _tabla;
    private javax.swing.JTable _tablaAcumulada;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txt_estadistico;
    private javax.swing.JTextField txt_grados;
    private javax.swing.JTextField txt_nuevo_estadistico;
    private javax.swing.JTextArea txt_valoresGenerados;
    // End of variables declaration//GEN-END:variables

    private void mostrarHistograma(float[] valoresGenerados) 
    {
        // Tenemos que convertir los numeros generados a un vector de double.
        double[] valoresGeneradosEnDouble = obtenerValoresEnDouble(valoresGenerados);
        
        new Histograma("Histograma Distribucion Exponencial",
                "Frecuencia de numeros aleatorios", valoresGeneradosEnDouble,
                cantIntervalos, (double) minimo, (double) maximo);
    }

    private double[] obtenerValoresEnDouble(float[] valoresGenerados) 
    {
        double[] ret = new double[valoresGenerados.length];
        for (int i = 0; i < valoresGenerados.length; i++) {
            ret[i] = (double) valoresGenerados[i];
        }
        return ret;
    }

    private float calcularRango(float[] values, int cantIntervalos) 
    {
        Calculator.quicksort(values, 0, values.length-1);
        minimo = (float) Math.floor(values[0]);
        maximo = (float) Math.ceil(values[values.length-1]);
        return (maximo - minimo) / cantIntervalos;
    }

    private String obtenerValoresGeneradosParaMostrar(float[] valores) 
    {
        //para mostrar los valores generados
        String acum = "";
        DecimalFormat aleat = new DecimalFormat("0.0000");
        for (int i = 0; i < valores.length; i++) {
            acum += "Valor " + (i + 1) + ": " + aleat.format(valores[i]) + ".\n";
        }
        return acum;
    }

    private float calcularProbabilidad(float limiteInferior, float limiteSuperior)
    {
        float expInferior = (float) (1 - (Math.exp((-lambda * limiteInferior))));
        float expSuperior = (float) (1 - (Math.exp((-lambda * limiteSuperior))));
        return expSuperior - expInferior;
    }

    private float calcularFrecuenciaEsperada(float probabilidad)
    {
        return probabilidad*N;
    }

    private void agregarTablaAcumulada()
    {
        if (algunaFrecuenciaEsperadaMenorA5())
        {
            DefaultTableModel tmAgrupado = (DefaultTableModel) _tablaAcumulada.getModel();
            TableModel tmOriginal = (DefaultTableModel) _tabla.getModel();
            
            DecimalFormat formatterEstadistico = new DecimalFormat("0.000");
            
            float frecEspAcumulada = 0;
            float frecEsperadaActual = 0;
            float frecObsAcumulada = 0;
            float frecObsActual = 0;
            float estadisticoTotal = 0;
            int filaConElHasta = tmOriginal.getRowCount() - 1;

            for (int i = (tmOriginal.getRowCount() - 1) ; i >= 0; i--) 
            {
                if (tmOriginal.getValueAt(i, COL_FREC_ESP) instanceof String)
                {
                    String frecEsperada = (String) tmOriginal.getValueAt(i, COL_FREC_ESP);
                    if (frecEsperada.indexOf(',') > 0)
                    {
                        frecEsperadaActual = Float.parseFloat(frecEsperada.replace(',', '.'));
                    }
                    else
                    {
                        frecEsperadaActual = Float.parseFloat(frecEsperada);
                    }
                }
                
                frecEspAcumulada += frecEsperadaActual;
                
                if (tmOriginal.getValueAt(i, COL_FREC_OBS) instanceof Float)
                {
                    frecObsActual = (float) tmOriginal.getValueAt(i, COL_FREC_OBS);
                }
                else
                {
                    throw new NullPointerException("La frecuencia Observada no era FLOAT!!!");
                }
                
                frecObsAcumulada += frecObsActual;
                
                if (frecEspAcumulada >= 5) 
                {
                    double estadisticoPruebaAcumActual = estadisticoPrueba(frecObsAcumulada, frecEspAcumulada);
                    estadisticoTotal += estadisticoPruebaAcumActual;
                    //poner Probabilidad a N/A xq no se usa aca
                    tmAgrupado.addRow(new Object[]{tmOriginal.getValueAt(i, COL_DESDE), 
                        tmOriginal.getValueAt(filaConElHasta, COL_HASTA), 
                        frecObsAcumulada,
                        "N/A",
                        frecEspAcumulada,
                        formatterEstadistico.format(estadisticoPruebaAcumActual)});
                    filaConElHasta = i - 1;
                    frecEspAcumulada = 0;
                    frecObsAcumulada = 0;
                } 
                else if (i == 0 && tmAgrupado.getRowCount() > 0)
                        // siguiente vuelta y no suma 5, le appendeo lo acumulado a la ultima fila que armé
                {
                    
                    int filaAUnir = tmAgrupado.getRowCount() - 1;
                    //actualizo intervalo (HASTA)
                    tmAgrupado.setValueAt(tmOriginal.getValueAt(i, COL_HASTA), filaAUnir, COL_HASTA);
                    //frec observada

                    tmAgrupado.setValueAt(frecObsAcumulada + obtenerValorEnFloat(tmAgrupado.getValueAt(filaAUnir, COL_FREC_OBS)), filaAUnir, COL_FREC_OBS);
                    //frec esperada
                    tmAgrupado.setValueAt(frecEspAcumulada + obtenerValorEnFloat(tmAgrupado.getValueAt(filaAUnir, COL_FREC_ESP)), filaAUnir, COL_FREC_ESP);

                    frecObsActual = (float) tmAgrupado.getValueAt(filaAUnir, COL_FREC_OBS);
                    frecEsperadaActual = (float) tmAgrupado.getValueAt(filaAUnir, COL_FREC_ESP);

                    
                    estadisticoTotal -= obtenerValorEnFloat(tmAgrupado.getValueAt((tmAgrupado.getRowCount() - 1), COL_ESTAD));
                    
                    double estadistico = estadisticoPrueba(frecObsActual, frecEsperadaActual);
                    tmAgrupado.setValueAt(formatterEstadistico.format(estadistico), filaAUnir, COL_ESTAD);
                    estadisticoTotal += estadistico;

                    break;
                }
                else if (i == 0 && tmAgrupado.getRowCount() <= 0)
                {
                    //Esto significa q no hay como actualizar xq la tabla nunca tuvo ninguna fila, asiq agregarla es la opcion.
                    
                    double estadisticoPruebaAcumActual = estadisticoPrueba(frecObsAcumulada, frecEspAcumulada);
                    estadisticoTotal += estadisticoPruebaAcumActual;
                    //poner Probabilidad a N/A xq no se usa aca
                    tmAgrupado.addRow(new Object[]{tmOriginal.getValueAt(tmOriginal.getRowCount() - 1, COL_DESDE), 
                        tmOriginal.getValueAt(i, COL_HASTA), 
                        frecObsAcumulada,
                        "N/A",
                        frecEspAcumulada,
                        formatterEstadistico.format(estadisticoPruebaAcumActual)});
                }
            }
            int aMover = tmAgrupado.getRowCount() - 1;
            for (int j = 0; j < tmAgrupado.getRowCount(); j++)
            {
                tmAgrupado.moveRow(aMover, aMover, j);
            }
            _gradosLib_agrupado.setText("" + gradosLibertad(tmAgrupado.getRowCount()));
            txt_nuevo_estadistico.setText("" + formatterEstadistico.format(estadisticoTotal));
        }
    }

    private boolean algunaFrecuenciaEsperadaMenorA5()
    {
        boolean rv = false;
        
        TableModel tmOriginal = (DefaultTableModel) _tabla.getModel();
        for (int i = 0; i < tmOriginal.getRowCount(); i++)
        {
            Double frecEsperada = 0.0;
            if (tmOriginal.getValueAt(i, COL_FREC_ESP) instanceof String)
            {
                String estadStr = (String) tmOriginal.getValueAt(i, COL_FREC_ESP);
                if (estadStr.indexOf(',') > 0)
                {
                    frecEsperada = Double.parseDouble(estadStr.replace(',', '.'));
                }
            }
            else
            {
                throw new NullPointerException("NO ERA STRING!");
            }
            if (frecEsperada < 5)
            {
                return true;
            }
        }
        return rv;
    }
    
    
    private float obtenerValorEnFloat(Object valueAt)
    {
        float toReturn = 0;
        if (valueAt instanceof String)
        {
            String val = (String) valueAt;
            if (val.indexOf(',') > 0)
            {
                toReturn = Float.parseFloat(val.replace(',', '.'));
            }
            else
            {
                toReturn = Float.parseFloat(val);
            }
        }
        else if (valueAt instanceof Float)
        {
            toReturn = (Float) valueAt;
        }
        return toReturn;
    }
}
