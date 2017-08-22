/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.poisson;

import java.math.BigInteger;
import java.text.*;
import javax.swing.*;
import javax.swing.table.*;
import jfree.*;
import objects.*;

/**
 *
 * @author federico
 */
public class PoissonTestTable extends javax.swing.JFrame {

    Controller controller;
    float[] valores;
    int N;
    float media;
    float[][] matrizDatos;

    private final static int COL_DESDE = 0;
    private final static int COL_HASTA = 1;
    private final static int COL_FREC_OBS = 2;
    private final static int COL_PROB = 3;
    private final static int COL_FREC_ESP = 4;
    private final static int COL_ESTAD = 5;
    
    private final int cantIntervalos;
    
    public PoissonTestTable(JFrame parent, Controller cont, float[] values, String[] datos, int intervalos) {
        
        controller=cont;
        valores = values;
        cantIntervalos = intervalos;
        N = Integer.parseInt(datos[0]);
        media = Calculator.obtenerValorEnFloat(datos[1]);
        
        matrizDatos = Calculator.matrizFrecuenciaPoisson(values);
        initComponents();
        
        
        
        DecimalFormat in = new DecimalFormat("0.00");
        DecimalFormat entero = new DecimalFormat("0");
        DecimalFormat c = new DecimalFormat("0.000");
        
        DefaultTableModel tm = (DefaultTableModel) _tabla.getModel();
        float generados = Float.parseFloat(datos[0]),frecEsp = 0, estadistico = 0;
        
        double lambdaValor = 0,eValor = 0, prob = 0, estadisticoT = 0;
        BigInteger fac;
        
        for (int i = 0; i < matrizDatos.length; i++) {
            lambdaValor = Math.pow(media,matrizDatos[i][0]);
            eValor = Math.exp(media*-1);
            fac = fact((int)matrizDatos[i][0]);
            
            prob = (lambdaValor * eValor)/fac.doubleValue();
            frecEsp = generados * (float) prob;
            estadistico = (float) (Math.pow(matrizDatos[i][1] - frecEsp, 2))/frecEsp;
            estadisticoT += estadistico;
            tm.addRow(new Object[]
                {
                    in.format(matrizDatos[i][0]),
                    in.format(matrizDatos[i][1]),
                    c.format(prob),
                    entero.format(frecEsp),
                    c.format(estadistico)
                });
        }
        int gr = tm.getRowCount()-1;
        txt_grados.setText(""+gr);
        txt_estadistico.setText(""+c.format(estadisticoT));
        
        int aux = 0;
        for (int i = 0; i < tm.getRowCount(); i++) {
            if(Float.parseFloat((String)tm.getValueAt(i, 3)) < 5){
                aux++;
            }
        }
        
        DefaultTableModel tm2 = (DefaultTableModel) _tablaAcumulada.getModel();
        float frecSumaEsp = 0, probSuma=0, estadisticoTotal = 0, frecSumaObservadas = 0, frecEsperadaActual = 0, frecObservadaActual = 0, probActual = 0; 
        int vueltas = 1; float hasta = 0, hastaActual = 0;
        float dd = 0;
        //si al menos existe un valor menor que 5
        if(aux > 0){
            //recorro las filas de la tabla _tabla
            for (int i = 0; i < tm.getRowCount(); i++) {
                if(Float.parseFloat((String)tm.getValueAt(i, 3)) < 5){
                    //recorro las filas a acumular
                    for (int j = i; j <= tm.getRowCount(); j++) {
                        if(frecSumaEsp < 5){
                            if(j==tm.getRowCount())//significa que termino de buscar en las filas y aun la fe es menor a 5
                            {
                                float valorO=0,valorE=0, valorP = 0,voo=0, vpp = 0, vee=0;
                                float valorH = 0; 
                                if (tm2.getValueAt((tm2.getRowCount()-1), 2) instanceof String)
                                {
                                    String vo = (String)tm2.getValueAt((tm2.getRowCount()-1), 2);
                                    if (vo.indexOf(',') > 0)
                                    {
                                        voo = Float.parseFloat(vo.replace(',', '.'));
                                    }
                                    else
                                    {
                                        voo = Float.parseFloat(vo);
                                    }
                                    valorO = voo;
                                }
                                else{
                                    valorO =   (float) tm2.getValueAt((tm2.getRowCount()-1), 2);
                                }
                                
                                
                                if (tm2.getValueAt(tm2.getRowCount()-1, 3) instanceof String)
                                {
                                    String vp = (String) tm2.getValueAt(tm2.getRowCount()-1, 3);
                                    if (vp.indexOf(',') > 0)
                                    {
                                        vpp = Float.parseFloat(vp.replace(',', '.'));
                                    }
                                    else
                                    {
                                        vpp = Float.parseFloat(vp);
                                    }
                                    valorP =    vpp;
                                }
                                else{
                                    valorP =    (float)tm2.getValueAt(tm2.getRowCount()-1, 3);
                                }
                                        
                                
                                if (tm2.getValueAt((tm2.getRowCount()-1), 4) instanceof String)
                                {
                                    String ve = (String) tm2.getValueAt((tm2.getRowCount()-1), 4);
                                    if (ve.indexOf(',') > 0)
                                    {
                                        vee = Float.parseFloat(ve.replace(',', '.'));
                                    }
                                    else
                                    {
                                        vee = Float.parseFloat(ve);
                                    }
                                    valorE =   vee;
                                }
                                else{
                                    valorE =   (float)tm2.getValueAt((tm2.getRowCount()-1), 4);
                                }
                                
                                
                                valorH = hasta;
                                valorP += probSuma;
                                valorE += frecSumaEsp;
                                valorO += frecSumaObservadas;
                                
                                tm2.setValueAt(valorH, (tm2.getRowCount()-1), 1);
                                tm2.setValueAt(valorO, (tm2.getRowCount()-1), 2);
                                tm2.setValueAt(valorP, (tm2.getRowCount()-1), 3);
                                tm2.setValueAt(valorE, (tm2.getRowCount()-1), 4);

                                double estadis= (double) Math.pow((valorE - valorO),2)/valorE;
                                tm2.setValueAt(estadis, (tm2.getRowCount()-1), 5);
                                i = tm.getRowCount();

                                break;
                            }
                            if (tm.getValueAt(j, 3) instanceof String)
                            {
                                String frecEsperada = (String) tm.getValueAt(j, 3);
                                if (frecEsperada.indexOf(',') > 0)
                                {
                                    frecEsperadaActual = Float.parseFloat(frecEsperada.replace(',', '.'));
                                }
                                else
                                {
                                    frecEsperadaActual = Float.parseFloat(frecEsperada);
                                }
                            }
                            
                            frecSumaEsp += frecEsperadaActual;
                            
                            if (tm.getValueAt(j, 1) instanceof String)
                            {
                                String fo = (String) tm.getValueAt(j, 1);
                                if (fo.indexOf(',') > 0)
                                {
                                    frecObservadaActual = Float.parseFloat(fo.replace(',', '.'));
                                }
                                else
                                {
                                    frecObservadaActual = Float.parseFloat(fo);
                                }
                            }
                            frecSumaObservadas += frecObservadaActual;
                            
                            if (tm.getValueAt(j, 2) instanceof String)
                            {
                                String p = (String) tm.getValueAt(j, 2);
                                if (p.indexOf(',') > 0)
                                {
                                    probActual = Float.parseFloat(p.replace(',', '.'));
                                }
                                else
                                {
                                    probActual = Float.parseFloat(p);
                                }
                            }
                            probSuma += probActual;
                            
                            if (tm.getValueAt(j, 0) instanceof String)
                            {
                                String h = (String) tm.getValueAt(j, 0);
                                if (h.indexOf(',') > 0)
                                {
                                    hastaActual = Float.parseFloat(h.replace(',', '.'));
                                }
                                else
                                {
                                    hastaActual = Float.parseFloat(h);
                                }
                            }
                            hasta = hastaActual;
                            vueltas++;
                        }
                        else{
                            double estadisticoParcial=(double)(Math.pow((frecSumaObservadas-frecSumaEsp),2))/frecSumaEsp;
                            if(tm2.getRowCount()==0){
                                tm2.addRow(new Object[]{hasta-(j-1), hasta, frecSumaObservadas, probSuma, frecSumaEsp, c.format(estadisticoParcial)});
                            }
                            else{
                                if (tm2.getValueAt(tm2.getRowCount()-1, 1) instanceof String)
                                {
                                    String d = (String) tm2.getValueAt(tm2.getRowCount()-1, 1);
                                    if (d.indexOf(',') > 0)
                                    {
                                        dd = Float.parseFloat(d.replace(',', '.'));
                                    }
                                    else
                                    {
                                        dd = Float.parseFloat(d);
                                    }
                                    tm2.addRow(new Object[]{dd+1, hasta, frecSumaObservadas, probSuma, frecSumaEsp, c.format(estadisticoParcial)});
                                }
                                else{
                                    tm2.addRow(new Object[]{(float)tm2.getValueAt(tm2.getRowCount()-1, 1)+1, hasta, frecSumaObservadas, probSuma, frecSumaEsp, c.format(estadisticoParcial)});
                                }
                                
                            }
                            
                            frecSumaEsp = 0;
                            frecSumaObservadas = 0;
                            probSuma = 0;
                            i = j-1;
                            break;
                        }
                    }
                }
                else{
                    tm2.addRow(new Object[]{tm.getValueAt(i, 0), tm.getValueAt(i, 0), tm.getValueAt(i, 1), tm.getValueAt(i, 2), tm.getValueAt(i, 3), tm.getValueAt(i, 4)});
                }
            }
        }
        
        
        
        
        
//        for (int i = 0; i < m.length; i++) {
//            tm.addRow(new Object[]
//            {
//                in.format(m[i][0]),
//                in.format(m[i][1]),
//                m[i][2], 
//                c.format(calcularProbabilidad(m,getRango(values,intervalos),datos)),
////                c.format(frecEsp),
////                c.format(estadistico)
//            });
//        }
//        completarFrecuencia();
//        
//        agregarAgrupado();
        int grados = tm2.getRowCount()-1;
        double estTot=0 , et=0;
        for (int i = 0; i < tm2.getRowCount(); i++) {
            
            if (tm2.getValueAt(i, 5) instanceof String)
            {
                String e = (String) tm2.getValueAt(i, 5);
                if (e.indexOf(',') > 0)
                {
                    et = Double.parseDouble(e.replace(',', '.'));
                }
                else
                {
                    et = Double.parseDouble(e);
                }
                estTot = et;
            }
            else{
                estTot += (double)tm2.getValueAt(i, 5);
            }
            
        }
        String valoresGenerados = valoresGenerados(values);
        txt_valoresGenerados.setText(valoresGenerados);
        txt_nuevo_estadistico.setText(""+c.format(estTot));
        _gradosLib_agrupado.setText(""+grados);
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

        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_nuevo_estadistico = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        _gradosLib_agrupado = new javax.swing.JTextField();
        _scpTabla = new javax.swing.JScrollPane();
        _tabla = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_valoresGenerados = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        _tablaAcumulada = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txt_estadistico = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_grados = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        _btnGrafico = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel4.setText("Si la frecuencia esperada es menor a 5");

        jLabel5.setText("Estadistico de prueba total:");

        txt_nuevo_estadistico.setEditable(false);
        txt_nuevo_estadistico.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                txt_nuevo_estadisticoActionPerformed(evt);
            }
        });

        jLabel6.setText("Grados de Libertad:");

        _gradosLib_agrupado.setEditable(false);

        _tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "Valor", "Frec Observada", "Probabilidad", "Frec Esperada", "Estadistico"
            }
        )
        {
            boolean[] canEdit = new boolean []
            {
                false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit [columnIndex];
            }
        });
        _scpTabla.setViewportView(_tabla);

        txt_valoresGenerados.setEditable(false);
        txt_valoresGenerados.setColumns(20);
        txt_valoresGenerados.setRows(5);
        jScrollPane2.setViewportView(txt_valoresGenerados);

        _tablaAcumulada.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "Desde", "Hasta", "Frec Obs", "Prob", "Frec Esp", "Estadistico"
            }
        )
        {
            boolean[] canEdit = new boolean []
            {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(_tablaAcumulada);

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
                        .addGap(8, 8, 8)
                        .addComponent(_scpTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_estadistico, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(jLabel3)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_grados, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(215, 215, 215)
                                .addComponent(_btnGrafico)))))
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
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(227, Short.MAX_VALUE))
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
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(_btnGrafico))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_nuevo_estadisticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nuevo_estadisticoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nuevo_estadisticoActionPerformed

    private void txt_estadisticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_estadisticoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_estadisticoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        //  controller.volverDeTestRandomJava();
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void _btnGraficoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event__btnGraficoActionPerformed
    {//GEN-HEADEREND:event__btnGraficoActionPerformed
        new GraficoDeBarras("Histograma Distribucion Poisson", "Frecuencia de numeros aleatorios", matrizDatos);
    }//GEN-LAST:event__btnGraficoActionPerformed

    public static BigInteger fact(int a)
    {
        BigInteger factorial = BigInteger.ONE; 
        for (int i = a; i > 0; i--) {
            factorial = factorial.multiply(BigInteger.valueOf(i)); 
        } 
        return factorial;


//        BigInteger factorial = BigInteger.ONE;
//        BigInteger factz = BigInteger.valueOf(a);
//
//        if(a == 1)
//        {
//            return factorial;
//        }
//
//        else
//        {
//            return factz.multiply(fact(a-1));
//        }
    }
    
    public long factorial (double numero) {
        if (numero==0)
            return 1;
        else
            return (long) numero * factorial(numero-1);
    }
    
    public int getRango(float[]values, int intervalo){
         float min = 0, max = 0;
        for (int i = 0; i < values.length; i++) {
            if(values[i] > max){
                max = values[i];
            }
        }
        for (int i = 0; i < values.length; i++) {
            if(i == 0){
                min = values[i];
            }
            else{
                if(values[i] < min){
                min = values[i];
                }
            }
        }
        
        int rango = (int) (max - min) / intervalo;
        rango ++;
        return rango;
    }
    
    private String valoresGenerados(float[] valores) {
        //para mostrar los valores generados
        String acum = "";
        DecimalFormat aleat = new DecimalFormat("0.0000");
        for (int i = 0; i < valores.length; i++) {
            acum += "Valor " + (i + 1) + ": " + aleat.format(valores[i]) + ".\n";
        }
        return acum;
    }

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

    private void completarFrecuencia()
    {
        
        DecimalFormat c = new DecimalFormat("0.000");
        //Iterar la tabla... para cada valor:
        TableModel tmOriginal = _tabla.getModel();
        for (int i = 0; i < tmOriginal.getRowCount(); i++)
        {
            float frecObs = Calculator.obtenerValorEnFloat(tmOriginal.getValueAt(i, COL_FREC_OBS));
            float probabilidad = Calculator.obtenerValorEnFloat(tmOriginal.getValueAt(i, COL_PROB));
            
            //Fe = ROUND -> P() * N;
            float frecEsp = Math.round(probabilidad*N);
       
            //Estadistico = estadistico( frecObs , frecEsperada)
            double estadistico = estadistico(frecObs, frecEsp);
            
            tmOriginal.setValueAt(frecEsp, i, COL_FREC_ESP);
            tmOriginal.setValueAt(c.format(estadistico), i, COL_ESTAD);
        }
        
        
        //Al final
        //Grados de libertad
        // Estadistico Total
        txt_grados.setText(gradosLibertadOriginal());
        
        txt_estadistico.setText(estadisticoTotalOriginal(tmOriginal));
    }

    private void agregarAgrupado()
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private double estadistico(float frecuenciaObservada, float frecuenciaEsperada)
    {
        
        return (double) (Math.pow(frecuenciaObservada - frecuenciaEsperada, 2)) / frecuenciaEsperada;
    }

    private String gradosLibertadOriginal()
    {
        return "" + (cantIntervalos - 1 -1);
    }

    private String estadisticoTotalOriginal(TableModel tmOriginal)
    {
        float estadisticoAcumulado = 0;
        for (int i = 0; i < tmOriginal.getRowCount(); i++)
        {
            estadisticoAcumulado += Calculator.obtenerValorEnFloat(tmOriginal.getValueAt(i, COL_ESTAD));
        }
        return "" + estadisticoAcumulado;
    }
}
