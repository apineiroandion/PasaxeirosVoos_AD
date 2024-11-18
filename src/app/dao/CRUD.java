package app.dao;

import app.model.Pasaxeiro;
import app.model.Vuelo;

import java.sql.*;
import java.util.ArrayList;

public class CRUD {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public CRUD(String query) throws SQLException {
        connection = Connexion.getConnection();
        statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        resultSet = statement.executeQuery(query);
    }

    public ArrayList<Pasaxeiro> getPasaxeiros() {
        ArrayList<Pasaxeiro> pasaxeiros = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Pasaxeiro pasaxeiro = new Pasaxeiro(
                        resultSet.getString("dni"),
                        resultSet.getString("nome"),
                        resultSet.getString("telf"),
                        resultSet.getString("cidade"),
                        resultSet.getInt("nreservas")
                );
                System.out.println("Cargando pasaxeiro: " + pasaxeiro);
                pasaxeiros.add(pasaxeiro);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener pasaxeiros: " + e.getMessage());
        }
        System.out.println("Total de pasaxeiros cargados: " + pasaxeiros.size());
        return pasaxeiros;
    }

    public ArrayList<Vuelo> getVuelos() {
        ArrayList<Vuelo> vuelos = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Vuelo vuelo = new Vuelo(
                        resultSet.getInt("voo"),
                        resultSet.getString("orixe"),
                        resultSet.getString("destino"),
                        resultSet.getDouble("prezo")
                );
                vuelos.add(vuelo);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return vuelos;
    }

    public void setPasaixeiro(Pasaxeiro pasaxeiro) {
        try {
            resultSet.moveToInsertRow();
            resultSet.updateString("dni", pasaxeiro.getDni());
            resultSet.updateString("nome", pasaxeiro.getNome());
            resultSet.updateString("telf", pasaxeiro.getTelefono());
            resultSet.updateString("cidade", pasaxeiro.getCidade());
            resultSet.updateInt("nreservas", pasaxeiro.getNumeroResevas());
            resultSet.insertRow();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void setVuelo(Vuelo vuelo) {
        try {
            resultSet.moveToInsertRow();
            resultSet.updateInt("voo", vuelo.getId());
            resultSet.updateString("orixe", vuelo.getOrigen());
            resultSet.updateString("destino", vuelo.getDestino());
            resultSet.updateDouble("prezo", vuelo.getPrecio());
            resultSet.insertRow();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void updateNumeroReservas(Pasaxeiro pasaxeiro) {
        String updateQuery = "UPDATE pasaxeiros SET nreservas = ? WHERE dni = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setInt(1, pasaxeiro.getNumeroResevas());
            preparedStatement.setString(2, pasaxeiro.getDni());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar número de reservas: " + e.getMessage());
        }
    }

    public void close() {
        try {
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }



}
