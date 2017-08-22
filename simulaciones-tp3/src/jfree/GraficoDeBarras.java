/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfree;

import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.category.*;
import org.jfree.ui.*;

/**
 *
 * @author nicolashefty
 */
public class GraficoDeBarras extends ApplicationFrame
{

    double[][] valores;
    String label;

    public GraficoDeBarras(String tituloGrafico, String label, float[][] valores)
    {
        super(tituloGrafico);
        convertirValoresADouble(valores);
        this.label = label;
        JFreeChart barChart = ChartFactory.createBarChart(
                tituloGrafico,
                "Intervalo",
                "Frecuencia",
                createDataset(),
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        setContentPane(chartPanel);
        pack();
        RefineryUtilities.centerFrameOnScreen(this);
        setVisible(true);
    }

    private CategoryDataset createDataset()
    {
        final DefaultCategoryDataset dataset
                = new DefaultCategoryDataset();

        for (double[] valor : valores)
        {
            String column = String.valueOf(valor[0]);
            dataset.addValue(valor[1], label, column);
        }
        // frecuencia - referencia - intervalo/nro
        
        //  [0] es el valor x
        //  [1] es la frecuencia del valor x
        return dataset;
    }

//    public static void abrirGrafico(String[] args)
//    {
//        GraficoDeBarras chart = new GraficoDeBarras("Car Usage Statistics",
//                                                    "Which car do you like?",
//                new float[][]{{5.0f, 4.0f}, {4.0f, 3.0f}});
//        chart.pack();
//        RefineryUtilities.centerFrameOnScreen(chart);
//        chart.setVisible(true);
//    }

    private void convertirValoresADouble(float[][] valoresEnFloat)
    {
        if (valoresEnFloat != null
                && valoresEnFloat.length > 0)
        {
            valores = new double[valoresEnFloat.length][valoresEnFloat[0].length];
            for (int i = 0; i < valoresEnFloat.length; i++)
            {
                for (int j = 0; j < valoresEnFloat[i].length; j++)
                {
                    valores[i][j] = (double) valoresEnFloat[i][j];
                }
            }
        }
    }

}
