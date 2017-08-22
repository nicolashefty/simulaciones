package jfree;

import java.io.*;
import javax.swing.*;

import org.jfree.chart.*;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.xy.*;
import org.jfree.data.statistics.*;
import org.jfree.data.xy.*;
import org.jfree.ui.*;

public class Histograma extends JFrame
{

    double[] valores;
    int cantIntervalos;
    JPanel chartPanel;
    double valorMinimo = 0.0;
    double valorMaximo = 1.0;
    String tituloGrafico;
    String labelFrecuencia;
    
    public Histograma(String titleGrafico, String labelFrecuencia, double[] valores, int cantIntervalos)
    {
        super(titleGrafico);
        this.valores = valores;
        this.cantIntervalos = cantIntervalos;
        this.tituloGrafico = titleGrafico;
        this.labelFrecuencia = labelFrecuencia;
        chartPanel = crearPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 475));
        setContentPane(chartPanel);
        pack();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        RefineryUtilities.centerFrameOnScreen(this);
        setVisible(true);
    }

    public Histograma(String titleGrafico, String labelFrecuencia, double[] valores, int cantIntervalos, double valorMinimo, double valorMaximo)
    {
        super(titleGrafico);
        this.valorMinimo = valorMinimo;
        this.valorMaximo = valorMaximo;
        this.valores = valores;
        this.cantIntervalos = cantIntervalos;
        this.tituloGrafico = titleGrafico;
        this.labelFrecuencia = labelFrecuencia;
        chartPanel = crearPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 475));
        setContentPane(chartPanel);
        pack();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        RefineryUtilities.centerFrameOnScreen(this);
        setVisible(true);
    }
    
    private  IntervalXYDataset crearDataset()
    {
        HistogramDataset dataset = new HistogramDataset();

        dataset.addSeries(labelFrecuencia, valores, cantIntervalos, valorMinimo, valorMaximo);
        dataset.setType(HistogramType.FREQUENCY);
        return dataset;
    }
    //saque el static
    private JFreeChart crearChart(IntervalXYDataset dataset)
    {
        JFreeChart chart = ChartFactory.createHistogram(
                tituloGrafico,
                "Intervalo",
                "Frecuencia",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        XYPlot plot = (XYPlot) chart.getPlot();
        NumberAxis range = (NumberAxis) plot.getDomainAxis();
        range.setTickUnit(new NumberTickUnit(valorMaximo/cantIntervalos));
        XYBarRenderer renderer = (XYBarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        return chart;
    }

    public  JPanel crearPanel()
    {
        JFreeChart chart = crearChart(crearDataset());
        return new ChartPanel(chart);
    }

    public JPanel obtenerPanel()
    {
      return chartPanel;   
    }
}