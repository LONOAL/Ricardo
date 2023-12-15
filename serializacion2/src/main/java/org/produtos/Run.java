package org.produtos;

import java.io.*;

public class Run {
    public static void exec() {
        String[] cod = {"p1", "p2", "p3"};
        String[] desc = {"parafusos", "cravos", "tachas"};
        int[] prezo = {3, 4, 5};

        // Serializar obxectos e escribilos no arquivo
        try (FileOutputStream fos = new FileOutputStream("produtos.dat");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            for (int i = 0; i < cod.length; i++) {
                Product produto = new Product(cod[i], desc[i], prezo[i]);
                oos.writeObject(produto);
            }

            // Escribir un obxecto nulo para marcar o final
            oos.writeObject(null);

            System.out.println("Obxectos serializados con éxito.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Ler obxectos do arquivo
        try (FileInputStream fis = new FileInputStream("produtos.dat");
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            Product produto;
            while ((produto = (Product) ois.readObject()) != null) {
                System.out.println("Código: " + produto.getCodigo());
                System.out.println("Descrición: " + produto.getDescricion());
                System.out.println("Prezo: " + produto.getPrezo());
                System.out.println("----------------------");
            }
        } catch (EOFException e) {
            // Fin do arquivo alcanzado
            System.out.println("Fin do arquivo alcanzado.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}