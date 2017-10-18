/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.montecarlo;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;


/**
 *
 * @author heftyn
 */
public class GraficoEvolutivo extends javax.swing.JFrame {

    private static final String TITULO_FRAME = "Grafico Evolutivo";
    private static final String TITULO_GRAFICO = "Evolución del Costo Promedio";
    private static final String TITL_EJE_X = "Tiempo (Días)";
    private static final String TITL_EJE_Y = "Costo ($)";
    private double costosPromedio[][];
    
   public GraficoEvolutivo( String applicationTitle , String chartTitle, double[][] costos) {
      super(applicationTitle);
      costosPromedio = costos;
      JFreeChart lineChart = ChartFactory.createLineChart(
         chartTitle,
         TITL_EJE_X,TITL_EJE_Y,
         createDataset(),
         PlotOrientation.VERTICAL,
         true,true,false);
         
      ChartPanel chartPanel = new ChartPanel( lineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension(1300, 733) );
      setContentPane( chartPanel );
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
   }

   private DefaultCategoryDataset createDataset( ) {
      DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
      for (int politica = 0; politica < costosPromedio.length; politica++)
      {
          for (int dia = 0; dia < costosPromedio[politica].length; dia++)
          {
              switch (politica)
              {
                  case 0:
                  {
                      dataset.addValue( costosPromedio[politica][dia] , "Politica A" , ""+dia );
                      break;
                  }
                  case 1:
                  {
                      dataset.addValue( costosPromedio[politica][dia] , "Politica B" , ""+dia );
                      break;
                  }
                  case 2:
                  {
                      dataset.addValue( costosPromedio[politica][dia] , "Politica Alternativa" , ""+dia );
                      break;
                  }
              }
          }
      }
//      dataset.addValue( 15 , "schools" , "1970" );
//      dataset.addValue( 30 , "schools" , "1980" );
//      dataset.addValue( 60 , "schools" ,  "1990" );
//      dataset.addValue( 120 , "schools" , "2000" );
//      dataset.addValue( 240 , "schools" , "2010" );
//      dataset.addValue( 300 , "schools" , "2014" );
//      dataset.addValue( 15 , "schools2" , "-5" );
//      dataset.addValue( 30 , "schools2" , "19" );
//      dataset.addValue( 60 , "schools2" ,  "199" );
//      dataset.addValue( 120 , "schools2" , "200" );
//      dataset.addValue( 240 , "schools2" , "201" );
//      dataset.addValue( 300 , "schools2" , "204" );
      return dataset;
   }
   
   
    static void mostrar(double[][] costosPromedio) 
    {
      GraficoEvolutivo chart = new GraficoEvolutivo(
         TITULO_FRAME ,
         TITULO_GRAFICO,
         costosPromedio);

      chart.pack( );
      RefineryUtilities.centerFrameOnScreen( chart );
      chart.setVisible( true );
   }
    
}
