/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.montecarlo;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import logica.montecarlo.demanda.Demanda;
import logica.montecarlo.demanda.DemandaRow;
import logica.montecarlo.demora.Demora;
import logica.montecarlo.demora.DemoraRow;
import logica.montecarlo.politicas.IPolitica;

/**
 *
 * @author heftyn
 */
public class VistaMontecarloSimulacion extends javax.swing.JFrame {

    private double[][] costosPromedio;

    /**
     * Creates new form VistaMontecarloSimulacion
     */
    public VistaMontecarloSimulacion() {
        initComponents();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    public void addRowToTable(Object[] row, IPolitica politica)
    {
        DefaultTableModel tm = null;
        switch (politica.getIDPolitica())
        {
            case IPolitica.A:
            {
                //agregar a la tabla de A;
                tm = (DefaultTableModel) tblPoliticaA.getModel();
                break;
            }
            case IPolitica.B:
            {
                //Agregar a la de B
                tm = (DefaultTableModel) tblPoliticaB.getModel();
                break;
            }
            case IPolitica.ALTR:
            {
                //Agregar a la nuestra
                tm = (DefaultTableModel) tblPoliticaAlternativa.getModel();
                break;
            }
        }
        if (tm != null)
        {
            tm.addRow(row);
        }
    }
    
    public void addRowToDemanda(Demanda demanda){
        List<DemandaRow> listaDemanda = demanda.getMapaProbabilidades();
        
        DefaultTableModel tDemanda = (DefaultTableModel) tblDemanda.getModel();
        for (int i = 0; i < listaDemanda.size(); i++) {
            tDemanda.addRow(new Object[]{listaDemanda.get(i).getDemanda(),listaDemanda.get(i).getProbabilidad()});
        }
    }
    
    public void addRowToDemora(Demora demora){
        List<DemoraRow> listaDemora = demora.getMapaProbabilidades();
        
        DefaultTableModel tDemora = (DefaultTableModel) tblDemora.getModel();
        for (int i = 0; i < listaDemora.size(); i++) {
            tDemora.addRow(new Object[]{listaDemora.get(i).getDemora(),listaDemora.get(i).getProbabilidad()});
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTblDemanda = new javax.swing.JPanel();
        scpTablaDemanda = new javax.swing.JScrollPane();
        tblDemanda = new javax.swing.JTable();
        pnlTblDemora = new javax.swing.JPanel();
        scpTblDemora = new javax.swing.JScrollPane();
        tblDemora = new javax.swing.JTable();
        pnlCostoPedido = new javax.swing.JPanel();
        scpCostoPedido = new javax.swing.JScrollPane();
        tblCostoPedido = new javax.swing.JTable();
        pnlCostosCtes = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        lblCostoPorAlmacenamiento = new javax.swing.JLabel();
        txtCostoAlmacenamiento = new javax.swing.JTextField();
        lblUnidCostoAlmacenamiento = new javax.swing.JLabel();
        lblCostoPorFaltante = new javax.swing.JLabel();
        txtCostoFaltante = new javax.swing.JTextField();
        lblUnidCostoFaltante = new javax.swing.JLabel();
        pnlStockInicial = new javax.swing.JPanel();
        lblStockInicial = new javax.swing.JLabel();
        txtStockInicial = new javax.swing.JTextField();
        tbpMontecarlo = new javax.swing.JTabbedPane();
        tabPoliticaA = new javax.swing.JScrollPane();
        tblPoliticaA = new javax.swing.JTable();
        tabPoliticaB = new javax.swing.JScrollPane();
        tblPoliticaB = new javax.swing.JTable();
        tabPoliticaAlternativa = new javax.swing.JScrollPane();
        tblPoliticaAlternativa = new javax.swing.JTable();
        btnGrafico = new javax.swing.JButton();
        lblTtlDemanda = new javax.swing.JLabel();
        lblTtlDemora = new javax.swing.JLabel();
        lblTitCostoPedido = new javax.swing.JLabel();
        lblCostosTtl = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Simulaciones - TP N° 4");

        tblDemanda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Demanda", "Probabilidad"
            }
        ));
        scpTablaDemanda.setViewportView(tblDemanda);

        javax.swing.GroupLayout pnlTblDemandaLayout = new javax.swing.GroupLayout(pnlTblDemanda);
        pnlTblDemanda.setLayout(pnlTblDemandaLayout);
        pnlTblDemandaLayout.setHorizontalGroup(
            pnlTblDemandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scpTablaDemanda, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        pnlTblDemandaLayout.setVerticalGroup(
            pnlTblDemandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scpTablaDemanda, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        tblDemora.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Demora", "Probabilidad"
            }
        ));
        scpTblDemora.setViewportView(tblDemora);

        javax.swing.GroupLayout pnlTblDemoraLayout = new javax.swing.GroupLayout(pnlTblDemora);
        pnlTblDemora.setLayout(pnlTblDemoraLayout);
        pnlTblDemoraLayout.setHorizontalGroup(
            pnlTblDemoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scpTblDemora, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        pnlTblDemoraLayout.setVerticalGroup(
            pnlTblDemoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scpTblDemora, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        tblCostoPedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Costo Pedido", "Cantidad Pedida"
            }
        ));
        scpCostoPedido.setViewportView(tblCostoPedido);

        javax.swing.GroupLayout pnlCostoPedidoLayout = new javax.swing.GroupLayout(pnlCostoPedido);
        pnlCostoPedido.setLayout(pnlCostoPedidoLayout);
        pnlCostoPedidoLayout.setHorizontalGroup(
            pnlCostoPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scpCostoPedido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        pnlCostoPedidoLayout.setVerticalGroup(
            pnlCostoPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scpCostoPedido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        lblCostoPorAlmacenamiento.setText("Costo por Almacenamiento");

        txtCostoAlmacenamiento.setText("3.00");
        txtCostoAlmacenamiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCostoAlmacenamientoActionPerformed(evt);
            }
        });

        lblUnidCostoAlmacenamiento.setText("$ por unidad por día");

        lblCostoPorFaltante.setText("Costo por Faltante");

        txtCostoFaltante.setText("10.00");

        lblUnidCostoFaltante.setText("$ por unidad por día");

        javax.swing.GroupLayout pnlCostosCtesLayout = new javax.swing.GroupLayout(pnlCostosCtes);
        pnlCostosCtes.setLayout(pnlCostosCtesLayout);
        pnlCostosCtesLayout.setHorizontalGroup(
            pnlCostosCtesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCostosCtesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCostosCtesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCostosCtesLayout.createSequentialGroup()
                        .addComponent(lblCostoPorAlmacenamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCostoAlmacenamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblUnidCostoAlmacenamiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jSeparator1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCostosCtesLayout.createSequentialGroup()
                        .addComponent(lblCostoPorFaltante, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCostoFaltante, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblUnidCostoFaltante, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlCostosCtesLayout.setVerticalGroup(
            pnlCostosCtesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCostosCtesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCostosCtesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCostoPorAlmacenamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCostoAlmacenamiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUnidCostoAlmacenamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlCostosCtesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCostoPorFaltante)
                    .addComponent(txtCostoFaltante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUnidCostoFaltante, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        lblStockInicial.setText("Stock inicial");

        txtStockInicial.setText("20");

        javax.swing.GroupLayout pnlStockInicialLayout = new javax.swing.GroupLayout(pnlStockInicial);
        pnlStockInicial.setLayout(pnlStockInicialLayout);
        pnlStockInicialLayout.setHorizontalGroup(
            pnlStockInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStockInicialLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblStockInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtStockInicial, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlStockInicialLayout.setVerticalGroup(
            pnlStockInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStockInicialLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(pnlStockInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStockInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStockInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblPoliticaA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Dia", "RND", "Demanda", "Stock", "RND", "Demora", "Demanda Acum", "Dias faltantes para pedir", "Hay pedido en curso", "Cant Pedida 1", "Cant Pedida 2", "Dia Llegada pedido 1", "Dia Llegada pedido 2", "Costo faltante", "Costo pedido", "Costo mantenimiento", "Costo total hoy", "Costo diario Acum", "Costo diario Prom"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabPoliticaA.setViewportView(tblPoliticaA);

        tbpMontecarlo.addTab("Política A", tabPoliticaA);

        tblPoliticaB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Dia", "RND", "Demanda", "Stock", "RND", "Demora", "Demanda Acum", "Dias faltantes para pedir", "Hay pedido en curso", "Cant Pedida 1", "Cant Pedida 2", "Dia Llegada Pedido 1", "Dia Llegada Pedido 2", "Costo faltante", "Costo pedido", "Costo mantenimiento", "Costo total hoy", "Costo diario Acum", "Costo diario Prom"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabPoliticaB.setViewportView(tblPoliticaB);

        tbpMontecarlo.addTab("Política B", tabPoliticaB);

        tblPoliticaAlternativa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Dia", "RND", "Demanda", "Stock", "RND", "Demora", "Demora Acum", "Dias faltantes para pedir", "Hay pedido en curso", "Cant Pedida 1", "Cant Pedida 2", "Dia Llegada pedido 1", "Dia Llegada pedido 2", "Costo faltante", "Costo pedido", "Costo mantenimiento", "Costo total hoy", "Costo diario Acum", "Costo diario Prom"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabPoliticaAlternativa.setViewportView(tblPoliticaAlternativa);

        tbpMontecarlo.addTab("Política Alternativa", tabPoliticaAlternativa);

        btnGrafico.setText("Gráfico Evolutivo del Costo Promedio Diario");

        lblTtlDemanda.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTtlDemanda.setText("Tabla de Demanda");

        lblTtlDemora.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTtlDemora.setText("Tabla de Demora");

        lblTitCostoPedido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitCostoPedido.setText("Costo del Pedido");

        lblCostosTtl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCostosTtl.setText("Costos definidos (constantes)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGrafico)
                .addGap(54, 54, 54))
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tbpMontecarlo, javax.swing.GroupLayout.PREFERRED_SIZE, 1248, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblTtlDemanda, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                            .addComponent(pnlTblDemanda, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pnlTblDemora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTtlDemora, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pnlCostoPedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTitCostoPedido, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addComponent(pnlCostosCtes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(98, 98, 98)
                                .addComponent(pnlStockInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(145, 145, 145)
                                .addComponent(lblCostosTtl, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTtlDemanda)
                    .addComponent(lblTtlDemora)
                    .addComponent(lblTitCostoPedido)
                    .addComponent(lblCostosTtl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlStockInicial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlCostosCtes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlCostoPedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlTblDemora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlTblDemanda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(tbpMontecarlo, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnGrafico)
                .addContainerGap(142, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCostoAlmacenamientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCostoAlmacenamientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCostoAlmacenamientoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGrafico;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblCostoPorAlmacenamiento;
    private javax.swing.JLabel lblCostoPorFaltante;
    private javax.swing.JLabel lblCostosTtl;
    private javax.swing.JLabel lblStockInicial;
    private javax.swing.JLabel lblTitCostoPedido;
    private javax.swing.JLabel lblTtlDemanda;
    private javax.swing.JLabel lblTtlDemora;
    private javax.swing.JLabel lblUnidCostoAlmacenamiento;
    private javax.swing.JLabel lblUnidCostoFaltante;
    private javax.swing.JPanel pnlCostoPedido;
    private javax.swing.JPanel pnlCostosCtes;
    private javax.swing.JPanel pnlStockInicial;
    private javax.swing.JPanel pnlTblDemanda;
    private javax.swing.JPanel pnlTblDemora;
    private javax.swing.JScrollPane scpCostoPedido;
    private javax.swing.JScrollPane scpTablaDemanda;
    private javax.swing.JScrollPane scpTblDemora;
    private javax.swing.JScrollPane tabPoliticaA;
    private javax.swing.JScrollPane tabPoliticaAlternativa;
    private javax.swing.JScrollPane tabPoliticaB;
    private javax.swing.JTable tblCostoPedido;
    private javax.swing.JTable tblDemanda;
    private javax.swing.JTable tblDemora;
    private javax.swing.JTable tblPoliticaA;
    private javax.swing.JTable tblPoliticaAlternativa;
    private javax.swing.JTable tblPoliticaB;
    private javax.swing.JTabbedPane tbpMontecarlo;
    private javax.swing.JTextField txtCostoAlmacenamiento;
    private javax.swing.JTextField txtCostoFaltante;
    private javax.swing.JTextField txtStockInicial;
    // End of variables declaration//GEN-END:variables

    public void setCostosPromedios(double[][] costosPromedio) {
        this.costosPromedio = costosPromedio;
    }

}
