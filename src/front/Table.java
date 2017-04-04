package front;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.*;
import objects.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author gneil
 */
public class Table extends javax.swing.JFrame {

    Controller controller;
    JButton next_btn;
    Calculator calculator = new Calculator();
    int contador;
    int lastA;
    int lastB;
    int lastM;
    int lastSeed;

    /**
     * Creates new form Table
     */
    public Table(Controller cont, String[][] response, String metodo) {
        controller = cont;
        contador = response.length;
        initComponents();

        JFrame frame = new JFrame("Resultados metodo " + metodo);
        frame.setPreferredSize(new Dimension (700, 700));
        next_btn = new JButton();
        next_btn.setSize(70, 30);
        next_btn.setVisible(true);
        next_btn.setText("Next");
        JPanel jp = new JPanel();

        jp.setPreferredSize(new Dimension(400, 400));
        JTable table = new JTable();
        JScrollPane tableContainer = new JScrollPane(table);
        jp.add(tableContainer, BorderLayout.CENTER);
        DefaultTableModel tm = new MetodoTableModel(new Object[]{"Step", "Xi", "Xi+1", "Numero Aleatorio"}, 0);
        table.setModel(tm);

        for (int i = 0; i < response.length; i++) {
            tm.addRow(new Object[]{i + 1, response[i][0], response[i][1], response[i][2]});
        }

        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setResizable(false);
        next_btn.setLocation(590, 85);
        frame.add(next_btn);
        frame.getContentPane().add(jp);
        frame.pack();
        frame.setVisible(true);

        next_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[][] nextStep = controller.calculateOneMore();
                contador++;
                tm.addRow(new Object[]{contador, nextStep[0][0], nextStep[0][1], nextStep[0][2]});
            }
        });

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setPreferredSize(new java.awt.Dimension(500, 517));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 605, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 517, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
