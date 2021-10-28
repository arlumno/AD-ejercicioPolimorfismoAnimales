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
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author a20armandocb
 */
public class OperacionesAnimales {

    public static final int limiteArray = 5;
    public static Perro[] perros = new Perro[limiteArray];
    public static int perrosCount = 0;

    public static Gato[] gatos = new Gato[limiteArray];
    public static int gatosCount = 0;

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
        int cantidadObjectos = perrosCount + gatosCount;
        ObjectOutputStream oos;
        ObjectOutputStream oosT;
        boolean cabeceraObjetos = true;
        try {
            //añadir cantidad objectos
            //FileOutputStream fos = new FileOutputStream(fichero);
            oos = new ObjectOutputStream( new FileOutputStream(fichero));
            oos.writeInt(cantidadObjectos);            

            //oos.writeObject(perros[0]);                    
//            oosT.writeObject(perros[1]);                    
//            oosT.flush();
//            oosT.writeObject(perros[2]);                    
//            oosT.flush();
//            
//            oosT.writeObject(gatos[0]);                    
//            oosT.flush();
//            oosT.writeObject(gatos[1]);                    
//            oosT.flush();
//            oosT.writeObject(gatos[2]);           
//            oosT.flush();
//            
//            oos.close();
//            oosT.close();
//           
            int i;
            for (i = 0; i < perrosCount; i++) {
                if(i == 0){
                    oos.writeObject(perros[i]);                    
                }else{                    
                    oosT = new ObjectOutputStream( new FileOutputStream(fichero,true)) {
                        @Override
                        protected void writeStreamHeader() {
                            try{
                                reset();                        
                            }catch(Exception e){
                                System.out.println(e.toString());
                            }

                        }
                    };
                    oosT.writeObject(perros[i]);
                }
            }

            //añadir gatos
            for (int e = 0; e < gatosCount; e++) {
                if(i == 0 && e == 0){
                    oos.writeObject(gatos[e]);                                
                }else{  
                    oosT = new ObjectOutputStream( new FileOutputStream(fichero,true)) {
                        @Override
                        protected void writeStreamHeader() {
                            try{
                                reset();                        
                            }catch(Exception e){
                                System.out.println(e.toString());
                            }

                        }
                    };
                    oosT.writeObject(gatos[e]);            
                }
            }
            
            oos.close();
//            oosT.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error al leer el fichero en guardarAnimalesADisco()\n" + e.toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en guardarAnimalesADisco():\n" + e.toString());
        } finally {
//            try {
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, "errorcillo " + e.toString());
//            }
        }

    }

    public static void leerAnimalesDisco(String rutaFichero) {
        File fichero = new File(rutaFichero);
        int cantidadObjectos;
        int contadorPerros = 0;
        int contadorGatos = 0;

        if (fichero.exists()) {
            FileInputStream contenido = null;
            try {
                contenido = new FileInputStream(fichero);
                ObjectInputStream ois = new ObjectInputStream(contenido);
                //cantidadObjectos = ((Integer) ois.readObject()).intValue();
                cantidadObjectos = ois.readInt();
//              cantidadObjectos = 5;

                Animal[] animales = new Animal[cantidadObjectos];

                for (int i = 0; i < cantidadObjectos && (contenido.available() > 0); i++) {
                    //  System.out.println(ois.readObject().toString());                    
                    Object entrada = ois.readObject();
                    animales[i] = (Animal) entrada;
                    // System.out.println(ejemplo.toString());
                    if (animales[i] instanceof Perro) {
                        contadorPerros++;
                    } else if (animales[i] instanceof Gato) {
                        contadorGatos++;
                    }
                }

                Perro[] listaPerros = new Perro[contadorPerros];
                Gato[] listaGatos = new Gato[contadorGatos];
                perrosCount = contadorPerros;
                gatosCount = contadorGatos;

                for (Animal animal : animales) {
                    if (animal instanceof Perro) {
                        listaPerros[--contadorPerros] = (Perro) animal;
                    } else if (animal instanceof Gato) {
                        listaGatos[--contadorGatos] = (Gato) animal;
                    }
                }
                setPerros(listaPerros);
                setGatos(listaGatos);

            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Error al leer el archivo en leerAnimalesDisco() \n" + e.toString());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en leerAnimalesDisco():\n " + e.toString());
                System.out.println("Error en leerAnimalesDisco(): \n" + e.toString());
            } finally {
                try {
                    contenido.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error en leerAnimalesDisco() al cerrar el flujo " + e.toString());
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "El archivo " + rutaFichero + " NO existe ");
        }

    }

    public static String listar() {
        StringBuilder resultado = new StringBuilder();
        //Arrays.asList(perros).stream(i -> resultado.append(cosa.toString
        if(perrosCount == 0 && gatosCount == 0){
            resultado.append("--Lista vacia--");
        }
        for (int i = 0; i < perrosCount; i++) {
            resultado.append(perros[i].toString() + "\n");
        }
        for (int i = 0; i < gatosCount; i++) {
            resultado.append(gatos[i].toString() + "\n");
        }
//        Arrays.stream(perros).forEach(perro -> resultado.append(perro.toString()));
//        Arrays.stream(gatos).forEach(gato -> resultado.append(gato.toString()));
        return resultado.toString();
    }
    public static void vaciarListas(){
        perrosCount = 0;
        gatosCount = 0;
        perros = new Perro[0];
        gatos = new Gato[0];

    }
}
