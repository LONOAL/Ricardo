package org.example;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        String[] codes = {"p1", "p2", "p3"};
        String[] descricion = {"parafusos", "cravos", "tachas"};
        int[] prices = {3, 4, 5};

        try {
            // Crear un archivo aleatorio en modo lectura/escritura
            RandomAccessFile file = new RandomAccessFile("productos.dat", "rw");

            // Escribir los registros en el archivo
            for (int i = 0; i < codes.length; i++) {
                // Formatear los campos código y descripción
                String formattedCode = String.format("%-3s", codes[i]);
                String formattedDesc = String.format("%-10s", descricion[i]);

                // Escribir en el archivo
                file.writeChars(formattedCode);
                file.writeChars(formattedDesc);
                file.writeInt(prices[i]);
            }

            // Leer y mostrar el contenido del registro en la posición número 2
            int recordSize = 30; // Tamaño total del registro en bytes
            int position = 2 * recordSize; // Posición del registro número 2

            file.seek(position); // Posicionarse en el registro número 2

            // Leer los campos del registro y eliminar espacios en blanco
            StringBuilder code = new StringBuilder();
            for (int i = 0; i < 3; i++) {
                code.append(file.readChar());
            }
            String description = "";
            for (int i = 0; i < 10; i++) {
                description += file.readChar();
            }
            int price = file.readInt();

            // Mostrar los campos del registro
            System.out.println(code.toString().trim());
            System.out.println(description.trim());
            System.out.println(price);

            // Cerrar el archivo
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}





