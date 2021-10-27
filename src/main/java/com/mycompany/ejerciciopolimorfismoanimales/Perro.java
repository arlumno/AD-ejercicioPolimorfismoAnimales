/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ejerciciopolimorfismoanimales;

/**
 *
 * @author a20armandocb
 */
public class Perro extends Animal {

    private boolean raboLargo;

    public Perro(String nombre) {
        this.nombre = nombre;
        setTipo("Perro");
    }

    public boolean isRaboLargo() {
        return raboLargo;
    }

    public void setRaboLargo(boolean raboLargo) {
        this.raboLargo = raboLargo;
    }

    @Override
    protected String expresarse() {
        return "GUAU";
    }
}
