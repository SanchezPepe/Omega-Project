/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.Serializable;

/**
 *
 * @author fabia
 */
public class Mensaje implements Serializable{
    private String quienManda;
    private String texto;

    public Mensaje(String quienManda, String texto) {
        this.quienManda = quienManda;
        this.texto = texto;
    }

    public String getQuienManda() {
        return quienManda;
    }

    public void setQuienManda(String quienManda) {
        this.quienManda = quienManda;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        return "Mensaje{" + "quienManda=" + quienManda + ", texto=" + texto + '}';
    }
    
    
}
