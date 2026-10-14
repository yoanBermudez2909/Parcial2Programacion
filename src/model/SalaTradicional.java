package model;

public class SalaTradicional extends  Sala{
    public SalaTradicional(String codigo, String nombre, int capacidadMaxima) {
        super(codigo, nombre, capacidadMaxima);
    }

    @Override
    public double calcularPrecioBoleta(double precioBase) {
        return 0;
    }
}
