package model;

import java.util.ArrayList;
import java.util.List;

public class Venta {
    private String codigo;
    private String fecha;
    private Operario operario;
    private Cliente cliente;
    private Funcion funcion;
    private List<Boleta> listVentaBoletas;

    public Venta(String codigo, String fecha, Operario operario, Cliente cliente, Funcion funcion) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.operario = operario;
        this.cliente = cliente;
        this.funcion = funcion;
        this.listVentaBoletas = new ArrayList<>();
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

    public Operario getOperario() {
        return operario;
    }

    public void setOperario(Operario operario) {
        this.operario = operario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcion getFuncion() {
        return funcion;
    }

    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
    }

    public List<Boleta> getListVentaBoletas() {
        return listVentaBoletas;
    }

    public void setListVentaBoletas(List<Boleta> listVentaBoletas) {
        this.listVentaBoletas = listVentaBoletas;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "codigo='" + codigo + '\'' +
                ", fecha='" + fecha + '\'' +
                ", operario=" + operario +
                ", cliente=" + cliente +
                ", funcion=" + funcion +
                ", listVentaBoletas=" + listVentaBoletas +
                '}';
    }
}
