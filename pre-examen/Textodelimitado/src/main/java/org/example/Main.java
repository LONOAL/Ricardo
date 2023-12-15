package org.example;

import java.io.*;
import java.text.NumberFormat;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        String[] cod = {"p1", "p2", "p3"};
        String[] desc = {"parafusos", "cravos", "tachas"};
        int[] prezo = {3, 4, 5};

        String nombreArchivo = "productos.txt";

        try (
                PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(nombreArchivo)));
        ) {
            // Escribir los datos en el archivo utilizando tabuladores como separadores y saltos de línea como separadores de registros
            for (int i = 0; i < cod.length; i++) {
                pw.print(cod[i]);
                pw.print("\t"); // Separador de campo
                pw.print(desc[i]);
                pw.print("\t"); // Separador de campo
                // Formatear el precio como moneda
                NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(new Locale("es", "ES"));
                pw.print(formatoMoneda.format(prezo[i]));
                pw.println(); // Separador de registro (salto de línea)
            }
            System.out.println("Datos escritos con éxito en el archivo " + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al escribir datos en el archivo: " + e.getMessage());
        }

        // Ahora, vamos a leer los datos del archivo e imprimir los registros
        try (
                BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
        ) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Dividir la línea en campos utilizando el separador de tabulación (\t)
                String[] campos = linea.split("\t");
                if (campos.length == 3) {
                    String codigo = campos[0];
                    String descripcion = campos[1];
                    // Eliminar el símbolo de la moneda y convertir el precio en un valor numérico
                    double precio = NumberFormat.getCurrencyInstance(new Locale("es", "ES"))
                            .parse(campos[2]).doubleValue();

                    // Imprimir los valores de los campos formateados
                    System.out.println("Código: " + codigo);
                    System.out.println("Descripción: " + descripcion);
                    System.out.println("Precio: " + String.format("%.2f €", precio)); // Formato de moneda
                    System.out.println();
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer datos del archivo: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error al procesar los datos: " + e.getMessage());
        }
    }
}
