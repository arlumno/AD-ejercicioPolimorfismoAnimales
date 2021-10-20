/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ejerciciopolimorfismoanimales;

import java.io.Serializable;

/**
 *
 * @author a20armandocb
 */
public abstract class Animal implements Serializable{
    private String tipo;
    protected String nombre;
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    protected abstract String expresarse();
    
}
