/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import front.*;
import java.text.DecimalFormat;
import java.util.Random;

/**
 *
 * @author nicolashefty
 */
public class Controller {

    private static Controller controller;
    static Main main;
    static Metodo metodo;
    static Test test;
    static Datos datos;
    GeneradorCongruencial generadorCongruencial;

    protected Controller(Main menu) {
        main = menu;
        metodo = new Metodo(this);
        test = new Test(this);
    }

    public static Controller getInstance(Main menu) {
        if (controller == null) {
            controller = new Controller(menu);
        } else {
            controller.setMain(menu);
        }

        return controller;
    }
//show menu, hide metodo

    public void showMenu() {
        metodo.setVisible(false);
        main.setVisible(true);
    }

    public void setMain(Main menu) {
        main = menu;
    }

    //sends method type to metodo
    public void selectedMethod(String metodo) {
        main.setVisible(false);
        this.metodo.setVisible(true);
        this.metodo.setMethodType(metodo);
    }

    public void calculate(int a, int b, int m, int seed, int size) {
        datos = new Datos();
        datos.setValues(a, b, m, seed);
        String[][] response = Calculator.calculate(datos, size);
//        Table table = new Table(this, response, "Mixto");
        String[] datosDelMetodo = {"" + a, "" + b, "" + m};
        generadorCongruencial = new GeneradorCongruencial(this, response, "Mixto", datosDelMetodo);
        generadorCongruencial.setVisible(true);
        metodo.setVisible(false);
    }

    public String[][] calculateOneMore() {
        return Calculator.calculate(datos, 1);
    }

    public Datos getDatos() {
        return datos;
    }

    //Genera un vector de los numeros aleatorios del generador de Java
    public float[] randomFloat(int size) {
        Random random = new Random();

        float[] vecValores = new float[size];
        float randomValue = 0;

        for (int i = 0; i < vecValores.length; i++) {
            randomValue = random.nextFloat();
            vecValores[i] = randomValue;
        }

        return vecValores;
    }

    /**
     *
     * @param randomVec
     * @param intervalo 5, 10 o 20
     * @return
     */
    public int[][] matrizFrecuencia(float[] randomVec, int intervalo) {
        int[][] matrizFrecuencia = new int[intervalo][2];

        float rango = 1f / intervalo;
        float comparador;

        for (int i = 0; i < randomVec.length; i++) {
            comparador = rango;
            for (int j = 0; j < intervalo; j++) {
                if (randomVec[i] < comparador) {
                    matrizFrecuencia[j][1]++;
                    break;
                } else {
                    comparador = comparador + rango;
                }
            }
        }
        return matrizFrecuencia;
    }

    public float getRango(int intervalo) {
        float rango = 1f / intervalo;
        return rango;
    }

    public void selectedTest(String selectedTest) {
        main.setVisible(false);
        test.setVisible(true);
    }

    public void volverDeTestRandomJava() {
        main.setVisible(true);
    }

    public void volverDeGeneradorCongruencial() {
        generadorCongruencial.dispose();
        metodo.setVisible(true);
    }
}
