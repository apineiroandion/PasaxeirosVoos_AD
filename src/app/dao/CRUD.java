package app.dao;

import app.model.Pasaxeiro;
import app.model.Reserva;
import app.model.Vuelo;
import app.model.datos.Datos;

import java.sql.*;
import java.util.ArrayList;

public class CRUD {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public CRUD() {
    }

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

    public void getPasaxeirosWhitoutQuery(Datos datos){
        ArrayList<Pasaxeiro> pasaxeiros = new ArrayList<>();
        try {
            connection = Connexion.getConnection();
            statement = connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery("SELECT * FROM pasaxeiros");
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
        datos.setPasaxeiros(pasaxeiros);
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

    public Integer getReservas(Pasaxeiro pasaxeiro){
        String selectQuery = "SELECT * FROM reservasfeitas WHERE dni = ?";
        Integer totalReservas = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setString(1, pasaxeiro.getDni());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                totalReservas++;
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener reservas: " + e.getMessage());
        }
        return totalReservas;
    }

    public void updatePasaxeiros(ArrayList<Pasaxeiro> pasaxeiros){
        String updateQuery = "UPDATE pasaxeiros SET nreservas = ? WHERE dni = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            for (Pasaxeiro pasaxeiro : pasaxeiros) {
                preparedStatement.setInt(1, getReservas(pasaxeiro));
                preparedStatement.setString(2, pasaxeiro.getDni());
                preparedStatement.executeUpdate();
            }
            System.out.println("Pasaxeiros actualizados correctamente");
        } catch (SQLException e) {
            System.out.println("Error al actualizar pasaxeiros: " + e.getMessage());
        }
    }

    public void insterReservas (Datos datos) {
        ArrayList<Reserva> reservas = datos.getReservas();
        String insertQuery = "INSERT INTO reservasfeitas (codr, dni, nome, prezoreserva) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            for (Reserva reserva : reservas) {
                preparedStatement.setInt(1, reserva.getCodr());
                preparedStatement.setString(2, reserva.getDni());
                preparedStatement.setString(3, reserva.getNomeByDni(datos));
                preparedStatement.setInt(4, reserva.getPrezoReserva());
                preparedStatement.executeUpdate();
            }
            System.out.println("Reservas insertadas correctamente");
        } catch (SQLException e) {
            System.out.println("Error al insertar reservas: " + e.getMessage());
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
