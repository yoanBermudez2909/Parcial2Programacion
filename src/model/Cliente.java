package model;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Persona {
    private String codigoAfilacion;
    private double porcentajeDescuento;

    private List<Venta> listClienteVentas;

    public Cliente(String documento, String nombre, String correo, String telefono, String codigoAfilacion, double porcentajeDescuento) {
        super(documento, nombre, correo, telefono);
        this.codigoAfilacion = codigoAfilacion;
        this.porcentajeDescuento = porcentajeDescuento;
        this.listClienteVentas = new ArrayList<>();
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

    public List<Venta> getListClienteVentas() {
        return listClienteVentas;
    }

    public void setListClienteVentas(List<Venta> listClienteVentas) {
        this.listClienteVentas = listClienteVentas;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "codigoAfilacion='" + codigoAfilacion + '\'' +
                ", porcentajeDescuento=" + porcentajeDescuento +
                '}';
    }
}
