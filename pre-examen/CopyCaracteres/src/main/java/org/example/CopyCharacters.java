package org.example;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CopyCharacters {
        // Define el nombre de los archivos de entrada y salida.
        String inputFile = "src/main/java/org/example/texto1.txt";
        String outputFile = "src/main/java/org/example/texto2.txt";

      public void copyPaste() {

        try (FileReader reader = new FileReader(inputFile);
             FileWriter writer = new FileWriter(outputFile)) {

            int character;
            // Leer el archivo de entrada caracter por caracter y escribirlo en el archivo de salida.
            while ((character = reader.read()) != -1) {
                writer.write(character);
            }

            System.out.println("Se ha copiado el contenido de " + inputFile + " a " + outputFile);
        } catch (IOException e) {
            System.err.println("Error de E/S: " + e.getMessage());
        }
    }
}
