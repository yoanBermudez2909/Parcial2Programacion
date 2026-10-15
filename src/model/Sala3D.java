package model;

public class Sala3D extends Sala{

    public Sala3D(String codigo, String nombre, int capacidadMaxima) {
        super(codigo, nombre, capacidadMaxima);
    }

    @Override
    public double calcularPrecioBoleta(double precioBase) {
        return precioBase * 1.20;
    }
}
