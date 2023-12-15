package org.produtos;

import java.io.Serializable;

public class Product implements Serializable {
    private static final long serialVersionUID = 6303247826283909654L;
    private String codigo;
    private String descricion;
    private int prezo;

    public Product(String codigo, String descricao, int prezo) {
        this.codigo = codigo;
        this.descricion = descricao;
        this.prezo = prezo;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricion() {
        return descricion;
    }

    public int getPrezo() {
        return prezo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setDescricion(String descricion) {
        this.descricion = descricion;
    }

    public void setPrezo(int prezo) {
        this.prezo = prezo;
    }
}