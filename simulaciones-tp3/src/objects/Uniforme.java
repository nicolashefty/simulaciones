/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

/**
 *
 * @author gabrielneil
 */
public class Uniforme {

    float[] vecValores;
    int desde, hasta;

    public Uniforme(float[] vecValores, int desde, int hasta) {
        this.vecValores = vecValores;
        this.desde = desde;
        this.hasta = hasta;
    }

    public float[] getVecValores() {
        return vecValores;
    }

    public void setVecValores(float[] vecValores) {
        this.vecValores = vecValores;
    }

    public int getDesde() {
        return desde;
    }

    public void setDesde(int desde) {
        this.desde = desde;
    }

    public int getHasta() {
        return hasta;
    }

    public void setHasta(int hasta) {
        this.hasta = hasta;
    }

}
