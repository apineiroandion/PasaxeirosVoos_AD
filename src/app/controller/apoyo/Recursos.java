package app.controller.apoyo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Recursos {
    private FileInputStream fis;
    private FileOutputStream fos;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private String ruta;

    public Recursos(String ruta) {
        this.ruta = ruta;
    }

    public FileInputStream getFis() {
        try {
            fis = new FileInputStream(ruta);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return fis;
    }

    public FileOutputStream getFos() {
        try {
            fos = new FileOutputStream(ruta);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return fos;
    }

    public ObjectInputStream getOis() {
        try {
            ois = new ObjectInputStream(fis);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return ois;
    }

    public ObjectOutputStream getOos() {
        try {
            oos = new ObjectOutputStream(fos);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return oos;
    }

}
