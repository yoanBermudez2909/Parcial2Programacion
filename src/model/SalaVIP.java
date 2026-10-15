package model;

public class SalaVIP extends Sala{
    public SalaVIP(String codigo, String nombre, int capacidadMaxima) {
        super(codigo, nombre, capacidadMaxima);
    }

    @Override
    public double calcularPrecioBoleta(double precioBase) {
        return precioBase * 1.30;
    }
}
