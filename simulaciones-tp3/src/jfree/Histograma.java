package jfree;

import javax.swing.*;

import org.jfree.chart.*;
import org.jfree.chart.axis.*;
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

    /**
     * Crea el Histograma con el Titulo <code>titleGrafico</code>, como leyenda 
     * coloca <code>labelFrecuencia</code>. Los valores que utiliza deben ser double
     * <br>Los parametros: <code>cantIntervalos</code>, <code>valorMinimo</code> y <code>valorMaximo</code>
     * se utilizan para definir las marcas de clases para cada barra.
     * @param titleGrafico
     * @param labelFrecuencia
     * @param valores
     * @param cantIntervalos
     * @param valorMinimo
     * @param valorMaximo 
     */
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
    
    /**
     * Genera el dataset que necesita el Histograma.
     * Se encarga solito de calcular las frecuencias, 
     * intervalos y marcas de clase.
     * @return 
     */
    private  IntervalXYDataset crearDataset()
    {
        HistogramDataset dataset = new HistogramDataset();

        dataset.addSeries(labelFrecuencia, valores, cantIntervalos, valorMinimo, valorMaximo);
        dataset.setType(HistogramType.FREQUENCY);
        return dataset;
    }
    
    /**
     * Utilizando el Factory Method de JFree.
     * Pero nosotros le definimos que tiene que mostrar la marca de clase en el eje X.
     * @param dataset
     * @return 
     */
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
        //Para mostrar la marca de clase
        range.setTickUnit(new NumberTickUnit((valorMaximo - valorMinimo)/cantIntervalos));
        XYBarRenderer renderer = (XYBarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        return chart;
    }

    /**
     * Simplemente crea el objeto del panel pero delega la creacion de otros componentes a otros metodos
     * @return 
     */
    public  JPanel crearPanel()
    {
        JFreeChart chart = crearChart(crearDataset());
        return new ChartPanel(chart);
    }

    /**
     * Getter.
     * @return 
     */
    public JPanel obtenerPanel()
    {
      return chartPanel;   
    }
}