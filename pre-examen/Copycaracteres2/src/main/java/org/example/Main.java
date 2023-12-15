package org.example;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        // Defina o nome dos arquivos de entrada e saída
        String arquivoEntrada = "entrada.txt";
        String arquivoSaida = "saida.txt";

        try (
                // Crie um BufferedReader para ler o arquivo de entrada
                BufferedReader br = new BufferedReader(new FileReader(arquivoEntrada));
                // Crie um BufferedWriter para escrever no arquivo de saída
                BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoSaida));
                // Crie um PrintWriter para facilitar a escrita de linhas no BufferedWriter
                PrintWriter pw = new PrintWriter(bw)
        ) {
            String linha;
            // Leia cada linha do arquivo de entrada usando readLine()
            while ((linha = br.readLine()) != null) {
                // Escreva a linha no arquivo de saída usando println(argument)
                pw.println(linha);
            }
            System.out.println("Cópia de caracteres concluída com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao copiar caracteres: " + e.getMessage());
        }
    }
}