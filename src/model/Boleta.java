package model;

import java.util.ArrayList;
import java.util.List;

public class Boleta {
    private String codigo;
    private int fila;
    private int numeroAsientos;
    private double valor;

    private List<Venta> listBoletaVenta;

    public Boleta(String codigo, int fila, int numeroAsientos, double valor) {
        this.codigo = codigo;
        this.fila = fila;
        this.numeroAsientos = numeroAsientos;
        this.valor = valor;

        this.listBoletaVenta = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getNumeroAsientos() {
        return numeroAsientos;
    }

    public void setNumeroAsientos(int numeroAsientos) {
        this.numeroAsientos = numeroAsientos;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Boleta{" +
                "codigo='" + codigo + '\'' +
                ", fila=" + fila +
                ", numeroAsientos=" + numeroAsientos +
                ", valor=" + valor +
                '}';
    }
}
