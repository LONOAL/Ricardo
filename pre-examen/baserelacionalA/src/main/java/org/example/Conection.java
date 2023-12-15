package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;


public class Conection {

    public Connection conexion() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/produtos", "postgres", "postgres");
    }

    public void insireProduto(String codigo, String descripcion, double prezo, Date data) throws SQLException {
        try (Connection conn = conexion();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO produtos (CODIGO, DESCRICION, PREZO, DATAC) VALUES (?, ?, ?, ?)")) {
            stmt.setString(1, codigo);
            stmt.setString(2, descripcion);
            stmt.setDouble(3, prezo);
            stmt.setDate(4, data);
            stmt.executeUpdate();
        }
    }
    public void listaProdutos() throws SQLException {
        try (Connection conn = conexion();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM produtos");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String codigo = rs.getString("CODIGO");
                String descripcion = rs.getString("DESCRICION");
                double prezo = rs.getDouble("PREZO");
                Date data = rs.getDate("DATAC");
                System.out.println("Código: " + codigo + ", Descripción: " + descripcion + ", Precio: " + prezo + ", Fecha de caducidad: " + data);
            }
        }
    }

    public void actualizaPre(String codigo, double nuevoPrezo) throws SQLException {
        try (Connection conn = conexion();
             PreparedStatement stmt = conn.prepareStatement("UPDATE produtos SET PREZO = ? WHERE CODIGO = ?")) {
            stmt.setDouble(1, nuevoPrezo);
            stmt.setString(2, codigo);
            stmt.executeUpdate();
        }
    }

    public void eliminaProduto(String codigo) throws SQLException {
        try (Connection conn = conexion();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM produtos WHERE CODIGO = ?")) {
            stmt.setString(1, codigo);
            stmt.executeUpdate();
        }
    }

    public void actualizarLista(String codigo, String descripcion, double prezo, Date data) throws SQLException {
        try (Connection conn = conexion();
             PreparedStatement fst = conn.prepareStatement("SELECT * FROM produtos");
             PreparedStatement stmt = conn.prepareStatement("UPDATE produtos SET DESCRICION = ?, PREZO = ?, DATAC = ? WHERE CODIGO = ?");
             ResultSet rs = fst.executeQuery()) {
             while (rs.next()) {
                 if (rs.getString("CODIGO").equals(codigo)) {
                     stmt.setString(1, descripcion);
                     stmt.setDouble(2, prezo);
                     stmt.setDate(3, data);
                     stmt.setString(4, codigo);
                     stmt.executeUpdate();
                 }
             }
        }
    }

    public void addRow(String codigo, String descripcion, double prezo, Date data) throws SQLException {
        try (Connection conn = conexion();
             PreparedStatement fst = conn.prepareStatement("SELECT * FROM produtos");
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO produtos (CODIGO, DESCRICION, PREZO, DATAC) VALUES (?, ?, ?, ?)");
             ResultSet rs = fst.executeQuery()) {
                  while (rs.next()) {
                  if (rs.isLast()) {
                        stmt.setString(1, codigo);
                        stmt.setString(2, descripcion);
                        stmt.setDouble(3, prezo);
                        stmt.setDate(4, data);
                        stmt.executeUpdate();
                  }
                  }
        }
    }

    public void deleteRow(String codigo) throws SQLException {
        try (Connection conn = conexion();
             PreparedStatement fst = conn.prepareStatement("SELECT * FROM produtos");
             ResultSet rs = fst.executeQuery()) {
                  while (rs.next()) {
                  if (rs.getString("CODIGO").equals(codigo)) {
                        rs.deleteRow();
                  }
                  }
        }
    }



}
