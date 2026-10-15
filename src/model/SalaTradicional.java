package model;

public class SalaTradicional extends  Sala{
    /**
     * Método constructor de la clase SalaTradicional
     * @param codigo de la sala tradicional
     * @param nombre de la sala tradicional
     * @param capacidadMaxima de la sala tradicional
     */
    public SalaTradicional(String codigo, String nombre, int capacidadMaxima) {
        super(codigo, nombre, capacidadMaxima);
    }

    @Override
    public double calcularPrecioBoleta(double precioBase) {
        return 0;
    }
}
