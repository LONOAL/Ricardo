package org.example;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Metodos {
    File file;

    public boolean eDirectorio(String directorio) {
        File file = new File(directorio);
        if (file.isDirectory()){
            System.out.println("e un directorio");
            return true;
            }
        else {
            System.out.println("non e un directorio");
            return false;
            }
    }

    public boolean eFichero(String fichero) {
        File file = new File(fichero);
        if (file.isFile()){
            System.out.println("e un fichero");
            return true;
        }
        else {
            System.out.println("non e un fichero");
            return false;
        }
    }

    public boolean creaDirectorio(String directorio){
        File file = new File(directorio);
        if (file.isDirectory()){
            System.out.println("xa existe");
            return false;
        }
        else {
            file.mkdir();
            return true;
        }
    }

    public boolean creaFichero(String dirName, String fileName){
        File file = new File(dirName+fileName);
        if (file.isDirectory()){
            System.out.println("xa existe");
            return false;
        }
        else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return true;
        }
    }

    public void modoAcceso (String dirName, String fileName){
        File file = new File(dirName+fileName);
        if (file.canWrite()) {
            System.out.println("escritura si");
        }else {
            System.out.println("escritura non");
        }
        if (file.canRead()){
            System.out.println("lectura si");
        }else {
            System.out.println("lectura non");
        }
    }

    public void calculaLonxitude(String dirName, String fileName){
        File file = new File(dirName+fileName);
        if (file.isFile())
        System.out.println(file.length());
    }

    public void mLectura(String dirName, String fileName){
        File file = new File(dirName+fileName);
        if (file.isFile())
        file.setReadOnly();
    }

    public void mEscritura(String dirName, String fileName){
        File file = new File(dirName+fileName);
        if (file.isFile())
            file.setWritable(true);
    }

    public void borraFicheiro(String dirName, String fileName){
        File file = new File(dirName+fileName);
        if (file.isFile())
            file.delete();
    }

    public void borraDirectorio(String dirName){
        File file = new File(dirName);
        if (file.isDirectory())
            file.delete();
        else
            System.out.println("ruta inexistente ou con descencencia");
    }

    public void mContido(String dirName){
        File file = new File(dirName);
        if (file.isDirectory()) {
            String [] lista = file.list();
            for (String dir:lista) {
                System.out.println(dir);
            }
        }
    }

    public void recur(File file) {
        if (file.exists() && file.isDirectory()) {
            listarDescendientes(file);
        } else {
            System.out.println("El directorio especificado no existe o no es un directorio válido.");
        }
    }

    public static void listarDescendientes(File directorio) {
        // Obtiene una lista de archivos y directorios en el directorio actual
        File[] archivosYDirectorios = directorio.listFiles();

        if (archivosYDirectorios != null) {
            for (File archivoODirectorio : archivosYDirectorios) {
                System.out.println(archivoODirectorio.getAbsolutePath()); // Muestra la ruta completa
                if (archivoODirectorio.isDirectory()) {
                    // Si es un directorio, llama a la función de manera recursiva
                    listarDescendientes(archivoODirectorio);
                }
            }
        }
    }

}
