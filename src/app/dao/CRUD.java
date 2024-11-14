package app.dao;

import app.model.Pasaxeiro;
import app.model.Vuelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CRUD {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public CRUD(String query) throws SQLException {
        connection = Connexion.getConnection();
        statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE
        );
        resultSet = statement.executeQuery(query);
    }

    public ArrayList<Pasaxeiro> getPasaxeiros() {
        ArrayList<Pasaxeiro> pasaxeiros = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Pasaxeiro pasaxeiro = new Pasaxeiro(
                        resultSet.getString("dni"),
                        resultSet.getString("nome"),
                        resultSet.getString("telefono"),
                        resultSet.getString("cidade"),
                        resultSet.getInt("numeroReservas")
                );
                pasaxeiros.add(pasaxeiro);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return pasaxeiros;
    }

    public ArrayList<Vuelo> getVuelos() {
        ArrayList<Vuelo> vuelos = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Vuelo vuelo = new Vuelo(
                        resultSet.getInt("voo"),
                        resultSet.getString("origen"),
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
            resultSet.updateString("telefono", pasaxeiro.getTelefono());
            resultSet.updateString("cidade", pasaxeiro.getCidade());
            resultSet.updateInt("numeroReservas", pasaxeiro.getNumeroResevas());
            resultSet.insertRow();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void setVuelo(Vuelo vuelo) {
        try {
            resultSet.moveToInsertRow();
            resultSet.updateInt("voo", vuelo.getId());
            resultSet.updateString("origen", vuelo.getOrigen());
            resultSet.updateString("destino", vuelo.getDestino());
            resultSet.updateDouble("prezo", vuelo.getPrecio());
            resultSet.insertRow();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


}
