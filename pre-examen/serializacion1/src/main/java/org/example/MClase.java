package org.example;

import java.io.Serializable;

public class MClase implements Serializable {
    private String nome;

    private int numero1;
    //private transient int numero1;
    private double numero2;

    public MClase(String nome, int numero1, double numero2) {
        this.nome = nome;
        this.numero1 = numero1;
        this.numero2 = numero2;
    }

    @Override
    public String toString() {
        return "s=" + nome + "; i=" + numero1 + "; d=" + numero2;
    }
}
