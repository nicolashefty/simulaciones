/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front;

import java.text.DecimalFormat;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.*;
import jfree.Histograma;
import objects.Controller;

/**
 *
 * @author federico
 */
public class TestCongruencial extends javax.swing.JFrame {

    Controller controller;
    int contador;
    float[] valoresGenerados;
    int cantIntervalos;
    int frecEsp;

    public TestCongruencial(Controller cont, int cantIntervalos, float rango, JTable finaltable) {
        controller = cont;

        DecimalFormat in = new DecimalFormat("0.00");
        DecimalFormat c = new DecimalFormat("0.000");
        DecimalFormat aleat = new DecimalFormat("0.0000");
        initComponents();
        float[][] matrizIntervalos = new float[cantIntervalos][2];
        double[] estadisticoParcial = new double[cantIntervalos];
        contador = 0;

        float[] vec = obtenerValoresGeneradosEnTabla(finaltable);

        frecEsp = (int) vec.length / cantIntervalos;

        int[][] response = matrizFrecuencia(vec, cantIntervalos);
        double rangoM;
        DefaultTableModel tm = (DefaultTableModel) tableC.getModel();
        double estadistico = 0, estadisticoTotal = 0;
        for (int i = 0; i < response.length; i++) {
            if (i == 0) {
                rangoM = rango;
                estadistico = estadisticoPrueba(response, frecEsp, i);
                tm.addRow(new Object[]{"0.00 - " + in.format(rangoM), i, response[i][1], frecEsp, c.format(estadistico)});
                estadisticoTotal += estadistico;
                matrizIntervalos[0][0] = 0;
                matrizIntervalos[0][1] = (float) rangoM;
                contador++;
            } else {
                if (contador == 1) {
                    estadistico = estadisticoPrueba(response, frecEsp, i);
                    tm.addRow(new Object[]{in.format(rango) + " - " + in.format(rango + rango), i, response[i][1], frecEsp, c.format(estadistico)});
                    estadisticoTotal += estadistico;
                    matrizIntervalos[i][0] = rango;
                    matrizIntervalos[i][1] = (float) rango + rango;
                    contador++;
                } else {
                    estadistico = estadisticoPrueba(response, frecEsp, i);
                    tm.addRow(new Object[]{in.format(rango * contador) + " - " + in.format((rango * contador) + rango), i, response[i][1], frecEsp, c.format(estadistico)});
                    estadisticoTotal += estadistico;
                    matrizIntervalos[i][0] = rango * contador;
                    matrizIntervalos[i][1] = (rango * contador) + rango;
                    contador++;
                }

            }
        }

        if (frecEsp < 5) {
            DefaultTableModel tm2 = (DefaultTableModel) tableFe.getModel();
            estadisticoTotal = 0;
            int frecEspAcumulada = 0;
            int frecObsAcumulada = 0;
            int inicio = 0;
            float a = 0;

            for (int i = 0; i < tm.getRowCount(); i++) {
                frecObsAcumulada += (int) tm.getValueAt(i, 2);
                frecEspAcumulada += (int) tm.getValueAt(i, 3);

                if (frecEspAcumulada >= 5) {
                    estadisticoParcial[i] += estadisticoPrueba2(frecObsAcumulada, (int) frecEspAcumulada);
                    tm2.addRow(new Object[]{in.format(matrizIntervalos[inicio][0]) + " - " + in.format(matrizIntervalos[i][1]), (int) frecObsAcumulada, frecEspAcumulada, c.format(estadisticoPrueba(response, (int) frecEspAcumulada, i))});
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
            txt_gradosFe.setText("" + gradosLibertad(tm2.getRowCount()));
            txt_estadisticoFe.setText("" + c.format(estadisticoTotal));
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

        String acum = "";
        for (int i = 0; i < vec.length; i++) {
            acum += "Valor " + (i + 1) + ": " + vec[i] + ".\n";
        }
        txt_valoresGenerados.setText(acum);

        //para el calculo de mi estadistico de prueba total
        txt_estadistico.setText("" + estadisticoTotal);
        txt_grados.setText("" + gradosLibertad(cantIntervalos));

        valoresGenerados = vec;
        this.cantIntervalos = cantIntervalos;
        agregarHistograma();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public float[] obtenerValoresGeneradosEnTabla(JTable t) {
        float[] s = new float[t.getRowCount()];
        for (int i = 0; i < s.length; i++) {
            s[i] = Float.parseFloat((String) t.getValueAt(i, 3));
        }
        return s;
    }

    public double estadisticoPrueba2(float frecObs, int frecEsp) {
        return (Math.pow(frecObs - frecEsp, 2))/frecEsp;
    }

    public int[][] matrizFrecuencia(float[] randomVec, int intervalo) {
        int[][] m = new int[intervalo][2];
        float rango = 1f / intervalo;
        float comparador;

        for (int i = 0; i < randomVec.length; i++) {
            comparador = rango;
            for (int j = 0; j < intervalo; j++) {
                if (randomVec[i] < comparador) {
                    m[j][1]++;
                    break;
                } else {
                    comparador = comparador + rango;
                }
            }
        }
        return m;
    }

    public double estadisticoPrueba(int[][] response, int frecEsp, int loop) {
        double res = 0;//(Math.pow((response[i][1]-frecEsp),2))/frecEsp;
        res = (double) (Math.pow(response[loop][1] - frecEsp, 2)) / frecEsp;
        return res;
    }

    public double estadisticoPruebaTotal(int[][] response, int frecEsp) {
        double res = 0, a = 0;//(Math.pow((response[i][1]-frecEsp),2))/frecEsp;
        for (int i = 0; i < response.length; i++) {
            a += (double) (Math.pow(response[i][1] - frecEsp, 2)) / frecEsp;
        }
        res = a;
        return res;
    }

    public int gradosLibertad(int intervalo) {
        return intervalo - 0 - 1;
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
        tableC = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_valoresGenerados = new javax.swing.JTextArea();
        _pnlHistograma = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_estadistico = new javax.swing.JTextField();
        txt_grados = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableFe = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_estadisticoFe = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_gradosFe = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Test de chi cuadrado para el metodo congruencial");

        jScrollPane1.setPreferredSize(new java.awt.Dimension(452, 402));

        tableC.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tableC);

        jLabel1.setText("Valores generados");

        txt_valoresGenerados.setColumns(20);
        txt_valoresGenerados.setRows(5);
        jScrollPane2.setViewportView(txt_valoresGenerados);

        _pnlHistograma.setPreferredSize(new java.awt.Dimension(600, 200));
        _pnlHistograma.setLayout(new java.awt.BorderLayout());

        jLabel2.setText("Estadistico de prueba total");

        jLabel3.setText("Grados de libertad");

        txt_estadistico.setEditable(false);

        txt_grados.setEditable(false);

        tableFe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numeros del intervalo", "Frecuencia", "Frecuencia esperada", "Estadistico de prueba"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tableFe);

        jLabel4.setText("Si la frecuencia esperada es menor a 5");

        jLabel5.setText("Estadistico de prueba total");

        txt_estadisticoFe.setEditable(false);

        jLabel6.setText("Grados de libertad");

        txt_gradosFe.setEditable(false);

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
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 613, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_estadistico, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(172, 172, 172)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_grados, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_estadisticoFe, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(172, 172, 172)
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_gradosFe, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel4))
                                .addGap(0, 67, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(_pnlHistograma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3)
                            .addComponent(jScrollPane2)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txt_estadistico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txt_grados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txt_estadisticoFe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txt_gradosFe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(_pnlHistograma, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel _pnlHistograma;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tableC;
    private javax.swing.JTable tableFe;
    private javax.swing.JTextField txt_estadistico;
    private javax.swing.JTextField txt_estadisticoFe;
    private javax.swing.JTextField txt_grados;
    private javax.swing.JTextField txt_gradosFe;
    private javax.swing.JTextArea txt_valoresGenerados;
    // End of variables declaration//GEN-END:variables

    private void agregarHistograma() {
        // Tenemos que convertir los numeros generados a un vector de double.
        double[] valoresGeneradosEnDouble = obtenerValoresEnDouble();
        Histograma histograma = new Histograma("Frecuencias del generador con el Metodo Congruencial",
                valoresGeneradosEnDouble, cantIntervalos);
        JPanel histoPanel = histograma.obtenerPanel();
        histoPanel.setVisible(true);
        _pnlHistograma.add(histoPanel);
        _pnlHistograma.validate();
    }

    private double[] obtenerValoresEnDouble() {
        double[] ret = new double[valoresGenerados.length];
        for (int i = 0; i < valoresGenerados.length; i++) {
            ret[i] = (double) valoresGenerados[i];
        }
        return ret;
    }
}
