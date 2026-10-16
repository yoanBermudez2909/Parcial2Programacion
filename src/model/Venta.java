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
        this.cliente = cliente;
        this.operario = operario;
        this.listVentaBoletas = new ArrayList<>();
    }

    public boolean agregarBoleta(int fila, int numero) {
        Sala sala = funcion.getSala();

        if (sala.buscarAsiento(fila, numero) == null) {
            return false;
        }
        if (!funcion.asientoDisponible(fila, numero)) {
            return false;
        }

        double conRecargo = sala.calcularPrecioBoleta(funcion.getPrecioBase());
        double valorFinal = cliente.aplicarDescuento(conRecargo);

        String codigoBoleta = codigo + "-" + (listVentaBoletas.size() + 1);
        listVentaBoletas.add(new Boleta(codigoBoleta, fila, numero, valorFinal));
        return true;
    }

    public double calcularTotal() {
        double total = 0;
        for (Boleta b : listVentaBoletas) {
            total += b.getValor();
        }
        return total;
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

    public Funcion getTheFuncion() {
        return funcion;
    }

    public void setTheFuncion(Funcion funcion) {
        this.funcion = funcion;
    }
    public Cliente getTheCliente() {
        return cliente;
    }

    public void setTheCliente(Cliente theCliente) {
        this.cliente = theCliente;
    }

    public Operario getTheOperario() {
        return operario;
    }

    public void setTheOperario(Operario theOperario) {
        this.operario = theOperario;
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
