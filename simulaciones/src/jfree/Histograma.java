package jfree;

import javax.swing.*;

import org.jfree.chart.*;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.xy.*;
import org.jfree.data.statistics.*;
import org.jfree.data.xy.*;

public class Histograma
{

    double[] valores;
    int cantIntervalos;
    JPanel chartPanel;
    
    public Histograma(String title, double[] valores, int cantIntervalos)
    {
        this.valores = valores;
        this.cantIntervalos = cantIntervalos;
        chartPanel = crearPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 475));
    }

    private  IntervalXYDataset crearDataset()
    {
        HistogramDataset dataset = new HistogramDataset();

        dataset.addSeries("Frecuencia observada", valores, cantIntervalos, 0.0, 1.0);
        dataset.setType(HistogramType.FREQUENCY);
        return dataset;
    }
    
    private JFreeChart crearChart(IntervalXYDataset dataset)
    {
        JFreeChart chart = ChartFactory.createHistogram(
                "Histograma",
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
        range.setTickUnit(new NumberTickUnit(1.0/cantIntervalos));
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
