package model;

public class Sala3D extends Sala{
    /**
     * Método constructor de la clase Sala3D
     * @param codigo de la sala 3D
     * @param nombre de la sala 3D
     * @param capacidadMaxima de la sala 3D
     */
    public Sala3D(String codigo, String nombre, int capacidadMaxima) {
        super(codigo, nombre, capacidadMaxima);
    }

    @Override
    public double calcularPrecioBoleta(double precioBase) {
        return 0;
    }
}
