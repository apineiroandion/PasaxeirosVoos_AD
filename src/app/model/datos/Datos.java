package app.model.datos;

import app.controller.apoyo.Recursos;
import app.model.Pasaxeiro;
import app.model.Reserva;
import app.model.Vuelo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Datos {
    private ArrayList<Pasaxeiro> pasaxeiros;
    private ArrayList<Reserva> reservas;
    private ArrayList<Vuelo> vuelos;
    private Recursos recursos;

    public Datos() {
        this.pasaxeiros = new ArrayList<>();
        this.reservas = new ArrayList<>();
        this.vuelos = new ArrayList<>();
        this.recursos = new Recursos("reservas.dat");
    }

    public void guardarReservas (ArrayList<Reserva> reservas){
        this.reservas = reservas;
    }

    public ArrayList<Reserva> cargarReservas(){
        ArrayList<Reserva> reservas = new ArrayList<>();
        reservas.add(new Reserva(1, "361a", 1, 2));
        reservas.add(new Reserva(2, "362b", 3, 4));
        reservas.add(new Reserva(3, "361a", 5, 6));
        return reservas;
    }

    public void serializeReservas() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(recursos.getRuta()))) {
            oos.writeObject(reservas);
        } catch (Exception e) {
            System.out.println("Error al serializar reservas: " + e.getMessage());
        }
    }

    public ArrayList<Reserva> deserializeReservas() {
        ArrayList<Reserva> reservas = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(recursos.getRuta()))) {
            reservas = (ArrayList<Reserva>) ois.readObject();
        } catch (Exception e) {
            System.out.println("Error al deserializar reservas: " + e.getMessage());
        }
        return reservas;
    }

    public void imprimirReservas(){
        for (Reserva reserva : reservas) {
            System.out.println(reserva.toString());
        }
        System.out.println("Total de reservas: " + reservas.size());
    }

    public void imprimirPasaxeiros() {
        for (Pasaxeiro pasaxeiro : pasaxeiros) {
            System.out.println(pasaxeiro.toString());
        }
        System.out.println("Total de pasaxeiros: " + pasaxeiros.size());
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
