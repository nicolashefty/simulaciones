/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import static java.lang.Math.*;
import java.util.Random;

public class Calculator {
    
    // Generador de variables aleatorias para expNegativo 
    public static float[] calculatorExp(int size, Exponencial distribucion){
        Random random = new Random();
        float[] vec = new float[size];
        float op = 0;
        float randomValue=0; 
        float mediaNegativa = (float) distribucion.getMedia()*-1;
        
        for (int i = 0; i < vec.length; i++) {
            randomValue = random.nextFloat();
            op = mediaNegativa * (float)Math.log(1-randomValue);
            vec[i]=op;
        }
        
        return vec;
    }
    
    //Generador de variables aleatoios para dist Normal por metodo de convolcion
    public static float[] calculatorNormal(int size, Normal distribucion){
        Random random = new Random();
        float[] vec = new float[size];
        float z = 0, randomValue=0, sumaRandom=0, acum=0;
        
        for (int i = 0; i < size; i++) 
        {
            acum = 0;
            for (int j = 0; j < vec.length; j++) 
            {
                acum += random.nextFloat();
            }
            z = ((acum-6)*(float)distribucion.getDesviacionEstandar()) + (float)distribucion.getMedia();
            vec[i]=z;
        }
        return vec;
    }
    
    //Generador de variables aleatorias de dist normal por metodo Box-Muller
    public static float[] calculatorNormalBoxMuller(int size, Normal distribucion)
    {
        Random random = new Random();
        float[] vec = new float[size];
        float rnd1, rnd2;
        boolean yaAgregueElSegundo = false;
        
        for ( int i = 0; i < size ; i++)
        {
            if (yaAgregueElSegundo)
            {
                yaAgregueElSegundo = false;
                continue;
            }
            rnd1 = random.nextFloat();
            rnd2 = random.nextFloat();
            
            vec[i] = n1(rnd1,rnd2, distribucion);
            if (i +1 < size)
            {
                vec [i + 1] = n2(rnd1,rnd2, distribucion);
                yaAgregueElSegundo = true;
            }
        }
        
        return vec;
    }
    // Cslculo de las variables aleatorias que se agregaran al vector de Box Muller
    private static float n1(float rnd1, float rnd2, Normal distribucion)
    {
        float n1 = 0;
        double raiz = sqrt((-2)* log(rnd1));
        double coseno = cos(2*PI*rnd2);
        n1 = (float) ((raiz * coseno) * distribucion.getDesviacionEstandar() + distribucion.getMedia());
        return n1;
    }

    private static float n2(float rnd1, float rnd2, Normal distribucion)
    {
        float n2 = 0;
        double raiz = sqrt((-2)* log(rnd1));
        double seno = sin(2*PI*rnd2);
        n2 = (float) ((raiz * seno) * distribucion.getDesviacionEstandar() + distribucion.getMedia());
        return n2;
    }
    
    // Acumulador de frecuencias observadas para cada intervalo en dist Uniforme
     public float[][][] matrizFrecuenciaUniforme(Uniforme uniforme, float rango, int intervalos){
        float [][][] m = armadoRangos(uniforme.getDesde(), rango, intervalos);
        float [] randomVec = uniforme.getVecValores();
        
        for (int i = 0; i < randomVec.length; i++) {
            for (int j = 0; j < intervalos; j++) {
                
                //m[j][j][0] tiene el desde y el m[j][j][1] el hasta, y m[j][j][2] la frecuencia obtenida
                if (randomVec[i]<m[j][j][1]) {
                    m[j][j][2]++;
                    break;
                }
            }
        }        
        return m;
    }
     
     //Se obtiene el desde y hasta de cada intervalo para la dist uniforme
     public float[][][] armadoRangos(int desde,float rango,int intervalos){
     float [][][] m = new float [intervalos][intervalos][3];
     float inicio = desde;
     float hasta = desde+rango;
     
         for (int i = 0; i < m.length; i++) {
             m[i][i][0]= inicio;
             m[i][i][1]=hasta;
             inicio = hasta;
             hasta+=rango;
         }
             return m;
     }
     
    
    public static void quicksort(float valores[], int izq, int der)
    {

        float pivote = valores[izq]; // tomamos primer elemento como pivote
        int i = izq; // i realiza la búsqueda de izquierda a derecha
        int j = der; // j realiza la búsqueda de derecha a izquierda
        float aux;

        while (i < j)
        {            // mientras no se crucen las búsquedas
            while (valores[i] <= pivote && i < j)
            {
                i++; // busca elemento mayor que pivote
            }
            while (valores[j] > pivote)
            {
                j--;         // busca elemento menor que pivote
            }
            if (i < j)
            {                      // si no se han cruzado                      
                aux = valores[i];                  // los intercambia
                valores[i] = valores[j];
                valores[j] = aux;
            }
        }
        valores[izq] = valores[j]; // se coloca el pivote en su lugar de forma que tendremos
        valores[j] = pivote; // los menores a su izquierda y los mayores a su derecha
        if (izq < j - 1)
        {
            quicksort(valores, izq, j - 1); // ordenamos subarray izquierdo
        }
        if (j + 1 < der)
        {
            quicksort(valores, j + 1, der); // ordenamos subarray derecho
        }
        
    }
     //Calcula la frecuencia observada para cada intervalo   
     public static float[][] matrizFrecuencia(float valores[], float rango, int intervalos, float minimo)
    {
        //Se arranca con el extremo minimo y vamos agregando el rango definido a cada intervalo
        float[][] vectorFrecuencias = armadoRangos(minimo, rango, intervalos);

        for (int i = 0; i < valores.length; i++)
        {
            for (int j = 0; j < intervalos; j++)
            {

                //vectorFrecuencias[j][0] tiene el desde y el vectorFrecuencias[j][1] el hasta,
                //y vectorFrecuencias[j][2] la frecuencia obtenida
                // Si el valor actual es menor al extremo superior del intervalo
                if (valores[i] < vectorFrecuencias[j][1])
                {
                    // Incremento Frecuencia
                    vectorFrecuencias[j][2]++;
                    break;
                }
            }
        }
        return vectorFrecuencias;
    }
    //Desde y hasta de los intervalos para la dist exponencial
    private static float[][] armadoRangos(float minimo, float rango, int intervalos)
    {
        float[][] vectorFrecuencias = new float[intervalos][3];
        //  [0] es el limite inferior del intervalo
        //  [1] es el limite superior del intervalo
        //  [2] es la frecuencia del intervalo
        
        float inicio = minimo;
        float hasta = minimo + rango;

        for (int i = 0; i < vectorFrecuencias.length; i++)
        {
            vectorFrecuencias[i][0] = inicio;
            vectorFrecuencias[i][1] = hasta;
            inicio = hasta;
            hasta += rango;
        }
        return vectorFrecuencias;
    }
    
    //Generador de variables para la dist de poisson
    public static float[] calcularPoisson(Poisson distribucion, int size)
    {
        Random random = new Random();
        float[] vec = new float[size];
        float p = 0, randomValue = 0;;
        int x = 0;

        double a = (double) Math.exp(((distribucion.getMedia()) * -1));

        for (int i = 0; i < size; i++)
        {
            p = 1;
            x = -1;
            do
            {
                randomValue = random.nextFloat();
                p = p * randomValue;
                x = x + 1;
            }
            while (p >= a);
            vec[i] = x;
        }

        return vec;
    }
    
    //Calculo de frecuencias observadas para la dist de poisson
    public static float[][] matrizFrecuenciaPoisson(float valores[])
    {
        float[] vec = valores;
        int min = 0, max = 0;

        for (int i = 0; i < vec.length; i++)
        {
            if (vec[i] > max)
            {
                max = (int) vec[i];
            }
        }
        for (int i = 0; i < vec.length; i++)
        {
            if (i == 0)
            {
                min = (int) vec[i];
            }
            else
            {
                if (vec[i] < min)
                {
                    min = (int) vec[i];
                }
            }
        }
        int r = max - min;
        float[][] m = new float[r+1][2];
        //  [0] es el valor x
        //  [1] es la frecuencia del valor x
        
        //creacion de la matriz
        for (int i = 0; i < r+1; i++)
        {
            //Con esto solo nos encargamos de poner los valores de x
            // el primero siempre es el minimo
            // y luego va subiendo de uno en uno
            
            if (i == 0)
            {
                m[0][0] = min;
            }
            else
            {
                m[i][0] = m[i-1][0] + 1;
            }
        }
        
        //agregado de las frecuencias
        
        for (int i = 0; i < vec.length; i++)
        {
            for (int j = 0; j < r+1; j++)
            {
                if (vec[i] == m[j][0])
                {
                    //En la posicion uno del vector j estamos
                    // guardando la frecuencia de dicho nro
                    m[j][1]++;
                    break;
                }
            }
        }

        return m;
    }

    public static float obtenerValorEnFloat(Object valueAt)
    {
        float toReturn = 0;
        if (valueAt instanceof String)
        {
            String val = (String) valueAt;
            if (val.indexOf(',') > 0)
            {
                toReturn = Float.parseFloat(val.replace(',', '.'));
            }
            else
            {
                toReturn = Float.parseFloat(val);
            }
        }
        else if (valueAt instanceof Float)
        {
            toReturn = (Float) valueAt;
        }
        return toReturn;
    }
}