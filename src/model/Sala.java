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

    public void agregarAsiento(Asiento asiento) {
        listSalaAsientos.add(asiento);
    }
    public void generarAsientos(int filas, int columnas) {
        for (int f = 1; f <= filas; f++) {
            for (int c = 1; c <= columnas; c++) {
                agregarAsiento(new Asiento(f, c));
            }
        }
    }
    public Asiento buscarAsiento(int fila, int columna) {
        for (Asiento a : listSalaAsientos) {
            if (a.getFilas() == fila && a.getColumnas() == columna) {
                return a;
            }
        }
        return null;
    }
    public boolean estaDisponible(Funcion nueva, List<Funcion> funcionesProgramadas) {
        for (Funcion f : funcionesProgramadas) {
            if (f.getSala().getCodigo().equals(codigo) && f.seCruzaCon(nueva)) {
                return false;
            }
        }
        return true;
    }
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
                ", CapacidadMaxima=" + CapacidadMaxima +
                '}';
    }
}
