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
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(OperacionesAnimales.addPerro(new Perro("Rufo")));
        
        System.out.println(OperacionesAnimales.addPerro(new Perro("pichi")));
        
        System.out.println(OperacionesAnimales.addPerro(new Perro("lobo")));
        
        System.out.println(OperacionesAnimales.addPerro(new Perro("Daisy")));
        
        System.out.println(OperacionesAnimales.addGato(new Gato("mico")));
        System.out.println(OperacionesAnimales.addGato(new Gato("duque")));
        System.out.println(OperacionesAnimales.addGato(new Gato("neko")));
        System.out.println(OperacionesAnimales.addGato(new Gato("michu")));
        System.out.println(OperacionesAnimales.addGato(new Gato("puka")));
        System.out.println(OperacionesAnimales.addGato(new Gato("elgato6")));
        String ruta = "src/main/java/com/mycompany/ejerciciopolimorfismoanimales/ficheros/animales.txt";
        OperacionesAnimales.guardarAnimalesADisco(ruta);
        
        OperacionesAnimales.leerAnimalesDisco(ruta);
        System.out.println(OperacionesAnimales.perros.toString());
        System.out.println(OperacionesAnimales.gatos.toString());
    }
    
}
