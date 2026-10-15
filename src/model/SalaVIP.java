package model;

public class SalaVIP extends Sala{
    /**
     * Método constructor de la clase SalaVIP
     * @param codigo de la sala VIP
     * @param nombre de la sala VIP
     * @param capacidadMaxima de la sala VIP
     */
    public SalaVIP(String codigo, String nombre, int capacidadMaxima) {
        super(codigo, nombre, capacidadMaxima);
    }

    @Override
    public double calcularPrecioBoleta(double precioBase) {
        return 0;
    }
}
