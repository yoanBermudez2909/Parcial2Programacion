package model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public abstract class  Sala {
    private  String codigo;
    private  String nombre;
    private  int capacidadMaxima;
    private List<Funcion> listSalaFunciones;
    private List<Asiento> listSalaAsientos;

    public Sala(String codigo, String nombre, int capacidadMaxima) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.capacidadMaxima = capacidadMaxima;

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
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(int capacidadMaxima) {
        capacidadMaxima = capacidadMaxima;
    }

    public List<Funcion> getListSalaFunciones() {
        return listSalaFunciones;
    }

    public void setListSalaFunciones(List<Funcion> listSalaFunciones) {
        this.listSalaFunciones = listSalaFunciones;
    }

    public List<Asiento> getListSalaAsientos() {
        return listSalaAsientos;
    }

    public void setListSalaAsientos(List<Asiento> listSalaAsientos) {
        this.listSalaAsientos = listSalaAsientos;
    }

    @Override
    public String toString() {
        return "Sala{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", capacidadMaxima=" + capacidadMaxima +
                '}';
    }
}
