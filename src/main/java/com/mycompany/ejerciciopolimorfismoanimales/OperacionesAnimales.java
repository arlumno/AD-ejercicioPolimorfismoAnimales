/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ejerciciopolimorfismoanimales;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;

/**
 *
 * @author a20armandocb
 */
public class OperacionesAnimales {

    public static final int limiteArray = 5;
    public static Perro[] perros = new Perro[limiteArray];
    public static byte perrosCount = 0;

    public static Gato[] gatos = new Gato[limiteArray];
    public static byte gatosCount = 0;

    public static Perro[] getPerros() {
        return perros;
    }

    public static void setPerros(Perro[] perros) {
        OperacionesAnimales.perros = perros;
    }

    public static Gato[] getGatos() {
        return gatos;
    }

    public static void setGatos(Gato[] gatos) {
        OperacionesAnimales.gatos = gatos;
    }

    public static boolean addPerro(Perro perro) {
        boolean respuesta = false;
        if (perrosCount < limiteArray) {
            perros[perrosCount] = perro;
            perrosCount++;
            respuesta = true;
        }
        return respuesta;
    }

    public static boolean addGato(Gato gato) {
        boolean respuesta = false;
        if (gatosCount < limiteArray) {
            gatos[gatosCount] = gato;
            gatosCount++;
            respuesta = true;
        }
        return respuesta;
    }

    public static void guardarAnimalesADisco(String rutaFichero) {
        File fichero = new File(rutaFichero);
        ObjectOutputStream oos = null;

        try {
            int i;
            for (i = 0; i < perros.length; i++) {
                if (i == 0) {
                    oos = new ObjectOutputStream(new FileOutputStream(fichero, true)) {
                        @Override
                        protected void writeStreamHeader() {
                        }
                    };
                } else {
                    oos = new ObjectOutputStream(new FileOutputStream(fichero));
                }
                oos.writeObject(perros[i]);
            }

            for (int e = 0; i < perros.length; e++) {
                if (i == 0 && e == 0) {
                    oos = new ObjectOutputStream(new FileOutputStream(fichero, true)) {
                        @Override
                        protected void writeStreamHeader() {
                        }
                    };
                } else {
                    oos = new ObjectOutputStream(new FileOutputStream(fichero));
                }
                oos.writeObject(perros[e]);
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "errorcillo al leer el fichero \n" + e.toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "errorcillo " + e.toString());
        } finally {
            try {
                oos.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "errorcillo " + e.toString());
            }
        }

    }

    public static void leerAnimalesDisco(String rutaFichero) {
        File fichero = new File(rutaFichero);
        if (fichero.exists()) {
            FileInputStream contenido = null;
            try {
                contenido = new FileInputStream(fichero);
                ObjectInputStream ois = new ObjectInputStream(contenido);
                Animal[] animales = new Animal[contenido.available()];
                int longitud = contenido.available();
                int contadorPerros = 0;
                int contadorGatos = 0;
                for(int i = 0; i < longitud; i++){                    
                    animales[i] = (Animal) ois.readObject();
                    if(animales[i] instanceof Perro){
                      contadorPerros++;
                    }else if(animales[i] instanceof Gato){
                        contadorGatos++;
                    }
                }
                Perro[] listaPerros = new Perro[contadorPerros];
                Gato[] listaGatos = new Gato[contadorGatos];
                for(Animal animal: animales){
                    
                    if(animal instanceof Perro){
                        listaPerros[--contadorPerros] = (Perro) animal;
                    }else if(animal instanceof Gato){
                        listaGatos[--contadorGatos] = (Gato) animal;                                                
                    }
                }
                setPerros(listaPerros);
                setGatos(listaGatos);                
                
                
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "errorcillo al leer el fichero \n" + e.toString());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "errorcillo " + e.toString());
            } finally {
                try {
                    contenido.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "errorcillo " + e.toString());
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "El archivo " + rutaFichero + " NO existe ");
        }
    }

}
