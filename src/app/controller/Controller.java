package app.controller;

import app.dao.CRUD;
import app.model.Pasaxeiro;
import app.model.Reserva;
import app.model.datos.Datos;

import java.sql.SQLException;

public class Controller {
    public String queryVuelos = "SELECT * FROM voos";
    public String queryPasaxeiro = "SELECT * FROM pasaxeiros";
    Datos datos = new Datos();
    CRUD crudVuelos;
    CRUD crudPasaxeiro;

    public Controller() throws SQLException {
        this.crudVuelos = new CRUD(queryVuelos);
        this.crudPasaxeiro = new CRUD(queryPasaxeiro);
    }

    public void iniciarApp(){
        datos.setPasaxeiros(crudPasaxeiro.getPasaxeiros());
        datos.setVuelos(crudVuelos.getVuelos());

        datos.guardarReservas(datos.cargarReservas());
        datos.serializeReservas();
        datos.deserializeReservas();
        datos.imprimirReservas();

        updateNumeroReservas();
        datos.imprimirPasaxeiros();


    }

    public void updateNumeroReservas(){
        for (Pasaxeiro pasaxeiro : datos.getPasaxeiros()) {
            for (Reserva reserva : datos.getReservas()) {
                if (pasaxeiro.getDni().equals(reserva.getDni())) {
                    crudPasaxeiro.updateNumeroReservas(pasaxeiro);
                }
            }
        }
        datos.setPasaxeiros(crudPasaxeiro.getPasaxeiros());
    }



}
