/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.util.Random;
import front.*;
import front.exponencial.*;
import front.poisson.*;
/**
 *
 * @author federico
 */
public class Controller {
    private static Controller controller;
    private String tipoG;
    static Main main;
    static Normal normal;
    static Exponencial expo;
    static Poisson poisson;
    static UniformeGenerator uniforme;
    static IGenerador generator;
    
    public static final String EXPONENCIAL = "Exponencial";
    private static final String UNIFORME = "Uniforme";
    public static final String POISSON = "Poisson";
    public static final String NORMAL = "Normal";
    
    
    protected Controller(Main menu) {
        main = menu;
        uniforme = new UniformeGenerator(this);
        generator = new Generator(this);
        tipoG = "";
    }
    
    public static Controller getInstance(Main menu) {
        if (controller == null)
        {
            controller = new Controller(menu);
        }
        else
        {
            controller.setMain(menu);
        }

        return controller;
    }
    
    public void setMain(Main menu)
    {
        main = menu;
    }
    
    public void showMenu() {
        main.setVisible(true);
    }
    
    public void selectedUniforme() {
        main.setVisible(false);
        this.uniforme.setVisible(true);
    }
    
    public void selectedGenerator(String tipo) {
        tipoG = tipo;
        main.setVisible(false);
        switch (tipo)
        {
            case EXPONENCIAL: 
                crearGeneradorExponencial();
                break;
            case POISSON:
                crearGeneradorPoisson();
                break;
            case NORMAL:
                crearGeneradorNormal();
                break;
        }
        this.generator.setVisible(true);
        this.generator.setGeneratorType(tipo);
    }
    public String getTipo(){
        return tipoG;
    }
    
     public void randomFloatUniforme(int desde, int hasta, int size){
        Random random = new Random();
        float[] vecValores = new float[size];
        float randomValue=0, operacion = 0;
        
        
        for (int i = 0; i < vecValores.length; i++) {
            randomValue = random.nextFloat();
            operacion = desde + (randomValue*(hasta - desde));
            vecValores[i]=operacion;
        }
        Uniforme uniformeValues = new Uniforme(vecValores, desde, hasta);
        UniformeTable table = new UniformeTable(this, uniformeValues);
        table.setVisible(true);
    }
    
    public void randomFloatExponencial(int size, float media){
        Exponencial e = new Exponencial(media);
        float[] vec = Calculator.calculatorExp(size, e);
        String[] datosUsados = new String[3];
        datosUsados[0] = ""+size;
        datosUsados[1] = ""+media;
        //datosUsados[2] = ""+desviacion
        GeneratorTable exp = new GeneratorTable(this,vec,"Exponencial",datosUsados);
        exp.setVisible(true);
        generator.setVisible(false);
    }
    
    public void randomFloatNormal(int size, float media, float desviacion)
    {
        Normal n = new Normal(media,desviacion);
        
        float[] vec = Calculator.calculatorNormalBoxMuller(size,n);
        String[] datosUsados = new String[3];
        datosUsados[0] = ""+size;
        datosUsados[1] = ""+media;
        datosUsados[2] = ""+desviacion;
        
        GeneratorTable exp = new GeneratorTable(this,vec,"Normal",datosUsados);
        exp.setVisible(true);
        generator.setVisible(false);
    }

    private void crearGeneradorExponencial()
    {
        generator = new ExponencialGenerator(this);
    }

    private void crearGeneradorPoisson()
    {
        generator = new PoissonGenerator(this);
    }

    private void crearGeneradorNormal()
    {
        generator = new NormalGenerator(this);
    }
    
     public void randomFloatPoisson(int size, float media)
    {
        Poisson p = new Poisson(media);
        float[] vec = Calculator.calcularPoisson(p, size);
        String[] datosUsados = new String[3];
        datosUsados[0] = "" + size;
        datosUsados[1] = "" + media;

        GeneratorTable po = new GeneratorTable(this, vec, "Poisson", datosUsados);
        po.setVisible(true);
        generator.setVisible(false);
    }
}
