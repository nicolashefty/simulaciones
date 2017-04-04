/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import front.*;

/**
 *
 * @author gabrielneil
 */
public class Controller {

    private static Controller controller;
    static Main main;
    static Metodo metodo;
    static Test test;
    static Datos datos;

    protected Controller(Main menu) {
        main = menu;
        metodo = new Metodo(this);
        test = new Test(this);
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
//show menu, hide metodo

    public void showMenu() {
        metodo.setVisible(false);
        main.setVisible(true);
    }

    public void setMain(Main menu)
    {
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
        Table table = new Table(this, response, "Mixto");
    }

    public String[][] calculateOneMore()
    {
        return Calculator.calculate(datos, 1);
    }
    
    public Datos getDatos() {
        return datos;
    }

    public void selectedTest(String selectedTest) {
        main.setVisible(false);
        test.setVisible(true);
    }
}
