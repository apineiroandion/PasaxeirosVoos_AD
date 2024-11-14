package app.model.datos;

import app.controller.apoyo.Recursos;
import app.model.Pasaxeiro;
import app.model.Reserva;
import app.model.Vuelo;

import java.util.ArrayList;

public class Datos {
    private ArrayList<Pasaxeiro> pasaxeiros;
    private ArrayList<Reserva> reservas;
    private ArrayList<Vuelo> vuelos;

    public Datos(ArrayList<Pasaxeiro> pasaxeiros, ArrayList<Reserva> reservas, ArrayList<Vuelo> vuelos) {
        this.pasaxeiros = pasaxeiros;
        this.reservas = reservas;
        this.vuelos = vuelos;
    }

    public ArrayList<Reserva> cargarReservas(){
        ArrayList<Reserva> reservas = new ArrayList<>();
        reservas.add(new Reserva(1, "361a", 1, 2));
        reservas.add(new Reserva(2, "362b", 3, 4));
        reservas.add(new Reserva(3, "361a", 5, 6));
        return reservas;
    }

    public void serializeReservas(){
        Recursos recursos = new Recursos("reservas.dat");
        try {
            recursos.getOos().writeObject(reservas);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public ArrayList<Reserva> deserializeReservas(){
        Recursos recursos = new Recursos("reservas.dat");
        ArrayList<Reserva> reservas = new ArrayList<>();
        try {
            reservas = (ArrayList<Reserva>) recursos.getOis().readObject();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return reservas;
    }

    public ArrayList<Pasaxeiro> getPasaxeiros() {
        return pasaxeiros;
    }

    public void setPasaxeiros(ArrayList<Pasaxeiro> pasaxeiros) {
        this.pasaxeiros = pasaxeiros;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(ArrayList<Reserva> reservas) {
        this.reservas = reservas;
    }

    public ArrayList<Vuelo> getVuelos() {
        return vuelos;
    }

    public void setVuelos(ArrayList<Vuelo> vuelos) {
        this.vuelos = vuelos;
    }
}
