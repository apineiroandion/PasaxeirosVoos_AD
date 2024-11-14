package app.model;

public class Pasaxeiro {
    private  String dni;
    private String nome;
    private String telefono;
    private String cidade;
    private Integer numeroResevas;

    public Pasaxeiro() {
    }

    public Pasaxeiro(String dni, String nome, String telefono, String cidade, Integer numeroResevas) {
        this.dni = dni;
        this.nome = nome;
        this.telefono = telefono;
        this.cidade = cidade;
        this.numeroResevas = numeroResevas;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Integer getNumeroResevas() {
        return numeroResevas;
    }

    public void setNumeroResevas(Integer numeroResevas) {
        this.numeroResevas = numeroResevas;
    }
}
