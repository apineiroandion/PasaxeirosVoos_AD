package app.model;

public class Vuelo {
    private Integer id;
    private String origen;
    private String destino;
    private Double precio;

    public Vuelo(Integer id, String origen, String destino, Double precio) {
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.precio = precio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
