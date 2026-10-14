package model;

public class Venta {
    private String codigo;
    private String fecha;

    public Venta(String codigo, String fecha) {
        this.codigo = codigo;
        this.fecha = fecha;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "codigo='" + codigo + '\'' +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}
