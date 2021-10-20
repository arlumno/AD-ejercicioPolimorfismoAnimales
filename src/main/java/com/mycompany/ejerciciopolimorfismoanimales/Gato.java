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
public class Gato extends Animal {

    private boolean bigotesLargos;

    public Gato(String nombre) {
        this.nombre = nombre;
        setTipo("Gato");
    }

    public boolean isBigotesLargos() {
        return bigotesLargos;
    }

    public void setBigotesLargos(boolean bigotesLargos) {
        this.bigotesLargos = bigotesLargos;
    }

    @Override
    protected String expresarse() {
        return "MIAU";
    }

}
