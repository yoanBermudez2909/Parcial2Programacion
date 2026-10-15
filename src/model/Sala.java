package model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public abstract class  Sala {
    private  String codigo;
    private  String nombre;
    private  int CapacidadMaxima;
    private List<Funcion> listSalaFunciones;
    private List<Asiento> listSalaAsientos;

    public Sala(String codigo, String nombre, int capacidadMaxima) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.CapacidadMaxima = capacidadMaxima;

        this.listSalaAsientos = new ArrayList<>();
    }

    public abstract double calcularPrecioBoleta(double precioBase);

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapacidadMaxima() {
        return CapacidadMaxima;
    }

    public void setCapacidadMaxima(int capacidadMaxima) {
        CapacidadMaxima = capacidadMaxima;
    }

    @Override
    public String toString() {
        return "Sala{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", CapacidadMaxima=" + CapacidadMaxima +
                '}';
    }
}
