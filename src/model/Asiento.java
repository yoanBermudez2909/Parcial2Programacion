package model;

public class Asiento {
    /**
     * Método constructor de la clase Asiento
     * @param fila del asiento
     * @param columna del asiento
     */

    private int filas;
    private int columnas;

    public Asiento(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
    }

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    @Override
    public String toString() {
        return "Asiento{" +
                "filas=" + filas +
                ", columnas=" + columnas +
                '}';
    }
}
