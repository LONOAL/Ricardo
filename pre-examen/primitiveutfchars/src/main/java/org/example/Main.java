package org.example;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        // Cadena de texto a escribir
        String texto = "Está en casa";

        try {
            // Crear un archivo de salida y un flujo de salida
            FileOutputStream archivo = new FileOutputStream("texto.dat");
            BufferedOutputStream buffer = new BufferedOutputStream(archivo);
            DataOutputStream dataOut = new DataOutputStream(buffer);

            // Primera escritura usando writeUTF
            dataOut.writeUTF(texto);
            System.out.println("writeUTF escribiendo: " + texto);
            System.out.println("Llevamos escritos "+dataOut.size()+"bytes");

            // Segunda escritura usando writeChars
            dataOut.writeChars(texto);
            System.out.println("writeChars escribiendo: " + texto);
            System.out.println("Llevamos escritos "+dataOut.size()+"bytes");

            // Tercera escritura usando writeUTF nuevamente
            dataOut.writeUTF(texto);
            System.out.println("writeUTF escribiendo: " + texto);
            System.out.println("Levamos escritos "+dataOut.size()+"bytes");

            // Cerrar el flujo de salida
            dataOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            // Crear un archivo de entrada y un flujo de entrada
            FileInputStream archivoEntrada = new FileInputStream("texto.dat");
            BufferedInputStream bufferEntrada = new BufferedInputStream(archivoEntrada);
            DataInputStream dataIn = new DataInputStream(bufferEntrada);

            // Leer la primera cadena en formato UTF
            String primeraCadena = dataIn.readUTF();
            System.out.println("Lemos a primeira cadea en UTF: " + primeraCadena);
            System.out.println("Quedan por ler "+dataIn.available()+" bytes");

            // Leer la segunda cadena en formato de caracteres usando readChar en un bucle
            System.out.print("Lemos a segunda cadea con readChar() en bucle: ");
            int longitudSegundaCadena = texto.length();
            for (int i = 0; i < longitudSegundaCadena; i++) {
                char caracter = dataIn.readChar();
                System.out.print(caracter);
            }
            System.out.println();
            System.out.println("Quedan por ler "+dataIn.available()+" bytes");

            // Leer la tercera cadena en formato UTF
            String terceraCadena = dataIn.readUTF();
            System.out.println("Lemos a terceira cadea mediante readUTF: " + terceraCadena);
            System.out.println("Quedan por ler "+dataIn.available()+" bytes");

            // Cerrar el flujo de entrada
            dataIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}