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
    float desde, hasta;

    public Uniforme(float[] vecValores, float desde, float hasta) {
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

    public float getDesde() {
        return desde;
    }

    public void setDesde(float desde) {
        this.desde = desde;
    }

    public float getHasta() {
        return hasta;
    }

    public void setHasta(float hasta) {
        this.hasta = hasta;
    }

}
