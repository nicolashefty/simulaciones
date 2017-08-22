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
    
    public TestCongruencial(Controller cont,  int cantIntervalos, float rango, JTable finaltable) {
        controller = cont;
        
        DecimalFormat in = new DecimalFormat("0.00");
        DecimalFormat c = new DecimalFormat("0.000");
        DecimalFormat aleat = new DecimalFormat("0.0000");
        initComponents();
        contador = 0;
        
        float[] vec = obtenerValoresGeneradosEnTabla(finaltable);
        
        frecEsp = (int) vec.length/cantIntervalos;
        
        int [][] response = matrizFrecuencia(vec,cantIntervalos); 
        double rangoM;
        DefaultTableModel tm = (DefaultTableModel) tableC.getModel();
        double estadistico = 0, estadisticoTotal = 0;
        for (int i = 0; i < response.length; i++) {
            if (i == 0) {
                rangoM = rango;
                estadistico = estadisticoPrueba(response,frecEsp,i);
                tm.addRow(new Object[]{"0.00 - "+in.format(rangoM),i, response[i][1], frecEsp, c.format(estadistico)});
                estadisticoTotal += estadistico;
                contador++;
            }
            else{
                if (contador == 1) {
                    estadistico = estadisticoPrueba(response,frecEsp,i);
                    tm.addRow(new Object[]{in.format(rango)+" - "+in.format(rango+rango),i, response[i][1], frecEsp, c.format(estadistico)});
                    estadisticoTotal += estadistico;
                    contador ++;
                }
                else{
                   estadistico = estadisticoPrueba(response,frecEsp,i);
                   tm.addRow(new Object[]{in.format(rango*contador)+" - "+in.format((rango*contador)+rango),i, response[i][1], frecEsp, c.format(estadistico)});
                   estadisticoTotal += estadistico;
                   contador++; 
                }
                
            }
        }
        
        
        String acum = "";
        for (int i = 0; i < vec.length; i++) {
            acum += "Valor "+(i+1)+": "+vec[i]+".\n";
        }
        txt_valoresGenerados.setText(acum);
        
        //para el calculo de mi estadistico de prueba total
        txt_estadistico.setText(""+estadisticoTotal);
        txt_grados.setText(""+gradosLibertad(cantIntervalos));
        
        valoresGenerados = vec;
        this.cantIntervalos = cantIntervalos;
        agregarHistograma();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    public float[] obtenerValoresGeneradosEnTabla(JTable t){
        float [] s = new float[t.getRowCount()];
        for (int i = 0; i < s.length; i++) {
            s[i]=Float.parseFloat((String)t.getValueAt(i, 3));
        }
        return s;
    }
    
    public int[][] matrizFrecuencia(float[] randomVec, int intervalo){
        int[][] m = new int[intervalo][2];
        float rango = 1f/intervalo;
        float comparador;
        
        for (int i = 0; i < randomVec.length; i++) {
            comparador = rango;
            for (int j = 0; j < intervalo; j++) {
                if (randomVec[i]<comparador) {
                    m[j][1]++;
                    break;
                }
                else{
                    comparador = comparador + rango;
                }
            }
        }
        return m;
    }
    public double estadisticoPrueba(int[][] response, int frecEsp, int loop){
        double res = 0;//(Math.pow((response[i][1]-frecEsp),2))/frecEsp;
        res = (double)(Math.pow(response[loop][1] - frecEsp,2))/frecEsp;
        return res;
    }
    
    public int gradosLibertad(int intervalo){
        return intervalo - 0 -1;
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 613, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_estadistico, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(172, 172, 172)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_grados, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addComponent(_pnlHistograma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txt_estadistico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txt_grados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(_pnlHistograma, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tableC;
    private javax.swing.JTextField txt_estadistico;
    private javax.swing.JTextField txt_grados;
    private javax.swing.JTextArea txt_valoresGenerados;
    // End of variables declaration//GEN-END:variables

private void agregarHistograma()
    {
        // Tenemos que convertir los numeros generados a un vector de double.
        double[] valoresGeneradosEnDouble = obtenerValoresEnDouble();
        Histograma histograma = new Histograma("Frecuencias del generador con el Metodo Congruencial",
                valoresGeneradosEnDouble, cantIntervalos);
        JPanel histoPanel = histograma.obtenerPanel();
        histoPanel.setVisible(true);
        _pnlHistograma.add(histoPanel);
        _pnlHistograma.validate();
    }

    private double[] obtenerValoresEnDouble()
    {
        double[] ret = new double[valoresGenerados.length];
        for (int i = 0; i < valoresGenerados.length; i++)
        {
            ret[i] = (double) valoresGenerados[i];
        }
        return ret;
    }
}
