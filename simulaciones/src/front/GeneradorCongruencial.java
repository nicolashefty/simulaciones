/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import objects.*;

/**
 *
 * @author nicolashefty
 */
public class GeneradorCongruencial extends javax.swing.JFrame
{

    Controller controller;
    Calculator calculator = new Calculator();
    int contador;
    DefaultTableModel _tmNumerosAleatorios;
    ChiCuadradoTableModel _tmChiCuadrado;
    String[][] response;
    /**
     * Creates new form GeneradorCongruencial
     */
    public GeneradorCongruencial(Controller cont, String[][] response, String metodo, String[] valoresDelMetodo)
    {
        controller = cont;
        contador = response.length;
        
        this.response = response;
        setTitle("Resultados metodo " + metodo);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
        
        crearTablaNumerosAleatorios();
        
        crearTablaChiCuadrado();
        
        agregarListeners();
        
        setTexts(valoresDelMetodo);
        agregarHistograma();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        _scpNumerosAleatorios = new javax.swing.JScrollPane();
        _tblNumerosAleatorios = new javax.swing.JTable();
        _btnSiguiente = new javax.swing.JToggleButton();
        _btnChiCuadrado = new javax.swing.JButton();
        _cboIntervalos = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        _btnVolver = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        _txtA = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        _txtB = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        _txtM = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        _tblNumerosAleatorios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        _scpNumerosAleatorios.setViewportView(_tblNumerosAleatorios);

        _btnSiguiente.setText("Siguiente Numero");
        _btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _btnSiguienteActionPerformed(evt);
            }
        });

        _btnChiCuadrado.setText("Test Chi Cuadrado");
        _btnChiCuadrado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _btnChiCuadradoActionPerformed(evt);
            }
        });

        _cboIntervalos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "5", "10", "20" }));

        jLabel1.setText("Seleccione cantidad de");

        jLabel2.setText("intervalos");

        _btnVolver.setText("Volver");
        _btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _btnVolverActionPerformed(evt);
            }
        });

        jLabel4.setLabelFor(_txtA);
        jLabel4.setText("A:");

        _txtA.setEditable(false);
        _txtA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _txtAActionPerformed(evt);
            }
        });

        jLabel5.setLabelFor(_txtB);
        jLabel5.setText("B:");

        _txtB.setEditable(false);
        _txtB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _txtBActionPerformed(evt);
            }
        });

        jLabel6.setLabelFor(_txtM);
        jLabel6.setText("M:");

        _txtM.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(_txtA, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(_txtB, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(_txtM, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(_btnVolver, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(_scpNumerosAleatorios, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(59, 59, 59)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(_btnChiCuadrado)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(_btnSiguiente))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(90, 90, 90)
                                .addComponent(_cboIntervalos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(_txtA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(_txtB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(_txtM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(_scpNumerosAleatorios, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(_btnSiguiente)
                        .addGap(129, 129, 129)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(_cboIntervalos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addComponent(_btnChiCuadrado)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(_btnVolver)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void _btnVolverActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event__btnVolverActionPerformed
    {//GEN-HEADEREND:event__btnVolverActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event__btnVolverActionPerformed

    private void _txtAActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event__txtAActionPerformed
    {//GEN-HEADEREND:event__txtAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event__txtAActionPerformed

    private void _txtBActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event__txtBActionPerformed
    {//GEN-HEADEREND:event__txtBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event__txtBActionPerformed

    private void _btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event__btnSiguienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event__btnSiguienteActionPerformed

    private void _btnChiCuadradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event__btnChiCuadradoActionPerformed
        //        new TestTable(controller,
            //                        controller.matrizFrecuencia(vecRandom,Integer.parseInt((String)jComboBox1.getSelectedItem())),
            //                        vecRandom,
            //                        controller.getRango(Integer.parseInt((String)jComboBox1.getSelectedItem())),
            //                        Integer.parseInt((String)jComboBox1.getSelectedItem())).setVisible(true);
        
        int cantIntervalos = Integer.parseInt((String) _cboIntervalos.getSelectedItem());
        int cantNumeros = _tmNumerosAleatorios.getRowCount();
//        if (cantNumeros >= cantIntervalos) {
//            new TestCongruencial(controller, cantIntervalos,
//                    controller.getRango(cantIntervalos),
//                    _tblNumerosAleatorios).setVisible(true);
//        } else {
//            JOptionPane.showMessageDialog(null, "Se necesitan más numeros para esa cantidad de intervalos!",
//                    "Error", JOptionPane.ERROR_MESSAGE);
//        }
    }//GEN-LAST:event__btnChiCuadradoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton _btnChiCuadrado;
    private javax.swing.JToggleButton _btnSiguiente;
    private javax.swing.JButton _btnVolver;
    private javax.swing.JComboBox<String> _cboIntervalos;
    private javax.swing.JScrollPane _scpNumerosAleatorios;
    private javax.swing.JTable _tblNumerosAleatorios;
    private javax.swing.JTextField _txtA;
    private javax.swing.JTextField _txtB;
    private javax.swing.JTextField _txtM;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables

    private void crearTablaNumerosAleatorios()
    {
        _tmNumerosAleatorios = new MetodoTableModel(new Object[]{"Nro", "Xi", "Xi+1", "Numero Aleatorio"}, 0);
        _tblNumerosAleatorios.setModel(_tmNumerosAleatorios);

        for (int i = 0; i < response.length; i++) 
        {
            _tmNumerosAleatorios.addRow(new Object[]{i + 1, response[i][0], response[i][1], response[i][2]});
        }
    }

    private void crearTablaChiCuadrado()
    {
        Object[] colNames =
        {
            "Intervalo", "# Intervalo", "Frec. Obs", "Frec. Esp",
            "Estadistico"
        };
        _tmChiCuadrado = new ChiCuadradoTableModel(colNames, 0);
//        _tblChiCuadrado.setModel(_tmChiCuadrado);
//        _tblChiCuadrado.setEnabled(false);
//        _scpChiCuadrado.setEnabled(false);
        _tmChiCuadrado.setAllDisabled();
    }

    private void agregarListeners()
    {
        _btnSiguiente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[][] nextStep = controller.calculateOneMore();
                contador++;
                _tmNumerosAleatorios.addRow(new Object[]{contador, nextStep[0][0], nextStep[0][1], nextStep[0][2]});
            }
        });
        
        _btnVolver.addActionListener(ae -> {
            controller.volverDeGeneradorCongruencial();
        });
    }

    private void setTexts(String[] datos)
    {
        _txtA.setText(datos[0]);
        _txtB.setText(datos[1]);
        _txtM.setText(datos[2]);
    }
    
    private void agregarHistograma()
    {
        // Tenemos que convertir los numeros generados a un vector de double.
//        double[] valoresGeneradosEnDouble = obtenerValoresEnDouble();
//        Histograma histograma = new Histograma("Frecuencias del generador con el Metodo Congruencial", valoresGeneradosEnDouble, Integer.parseInt((String)_cboIntervalos.getSelectedItem()));
//        JPanel histoPanel = histograma.obtenerPanel();
//        histoPanel.setVisible(true);
//        _pnlHistograma.add(histoPanel);
        
//        _pnlHistograma.validate();
    }

    private double[] obtenerValoresEnDouble()
    {
        double[] ret = new double[_tmNumerosAleatorios.getRowCount()];
        for (int i = 0; i < _tmNumerosAleatorios.getRowCount(); i++)
        {
            ret[i] = Double.parseDouble((String) _tmNumerosAleatorios.getValueAt(i, MetodoTableModel.COL_NUM_ALEATORIO));
        }
        return ret;
    }
}
