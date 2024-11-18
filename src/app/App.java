package app;

import app.controller.Controller;
import app.dao.CRUD;
import app.model.datos.Datos;

import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException {
        Controller controller = new Controller();
        controller.iniciarApp();
    }
}
