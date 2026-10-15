package model;

import java.util.ArrayList;
import java.util.List;

public class Venta {
    /**
     * Método constructor de la clase Venta
     * @param codigo de la venta
     * @param fecha de la venta
     * @param theFuncion de la venta
     * @param theCliente de la venta
     * @param theOperario de la venta
     */
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
        this.theCliente = theCliente;
        this.theOperario = theOperario;
        this.listVentaBoletas = new ArrayList<>();
    }
    // Intenta agregar una boleta para el asiento (fila, numero).
    // Devuelve false si el asiento no existe en la sala o si ya fue
    // vendido para esta funcion
    public boolean agregarBoleta(int fila, int numero) {
        Sala sala = theFuncion.getSala();

        if (sala.buscarAsiento(fila, numero) == null) {
            return false;
        }
        if (!theFuncion.asientoDisponible(fila, numero)) {
            return false;
        }

        double conRecargo = sala.calcularPrecioBoleta(theFuncion.getPrecioBase());
        double valorFinal = theCliente.aplicarDescuento(conRecargo);

        String codigoBoleta = codigo + "-" + (listVentaBoletas.size() + 1);
        Boleta boleta = new Boleta(codigoBoleta, fila, numero, valorFinal);

        boleta.setTheVenta(this);
        listVentaBoletas.add(boleta);
        theFuncion.registrarBoleta(boleta);
        return true;
    }
    // Suma el valor de todas las boletas de esta venta: total a pagar
    public double calcularTotal() {
        double total = 0;
        for (Boleta b : listVentaBoletas) {
            total += b.getValor();
        }
        return total;
    }
    // Getters y setters de cada atributo
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
        return theCliente;
    }

    public void setTheCliente(Cliente theCliente) {
        this.theCliente = theCliente;
    }

    public Operario getTheOperario() {
        return theOperario;
    }

    public void setTheOperario(Operario theOperario) {
        this.theOperario = theOperario;
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
