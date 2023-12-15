package org.example;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        try {
            Conection app = new Conection();

            // Insertar productos
            java.sql.Date fecha1 = java.sql.Date.valueOf("2020-12-27");
            app.insireProduto("p1", "parafusos", 3.0, fecha1);

            java.sql.Date fecha2 = java.sql.Date.valueOf("2020-04-06");
            app.insireProduto("p2", "cravos", 4.0, fecha2);

            java.sql.Date fecha3 = java.sql.Date.valueOf("2020-07-03");
            app.insireProduto("p3", "tachas", 6.0, fecha3);

            // Listar productos
            app.listaProdutos();

            // Actualizar precio
            app.actualizaPre("p2", 8.0);

            // Eliminar producto
            app.eliminaProduto("p3");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}