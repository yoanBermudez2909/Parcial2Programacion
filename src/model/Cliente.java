package model;

public class Cliente extends Persona{
    private String codigoAfilacion;
    private double porcentajeDescuento;

    public Cliente(String documento, String nombre, String correo, String telefono, String codigoAfilacion, double porcentajeDescuento) {
        super(documento, nombre, correo, telefono);
        this.codigoAfilacion = codigoAfilacion;
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public String getCodigoAfilacion() {
        return codigoAfilacion;
    }

    public void setCodigoAfilacion(String codigoAfilacion) {
        this.codigoAfilacion = codigoAfilacion;
    }

    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "codigoAfilacion='" + codigoAfilacion + '\'' +
                ", porcentajeDescuento=" + porcentajeDescuento +
                '}';
    }
}
