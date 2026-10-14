package model;

import java.awt.*;

public class Operario extends Persona {
    private String codigoEmpleado;
    private String fechaContratacion;
    private double salarioBasico;

    public Operario(String documento, String nombre, String correo, String telefono, String codigoEmpleado, String fechaContratacion, double salarioBasico) {
        super(documento, nombre, correo, telefono);

        this.codigoEmpleado = codigoEmpleado;
        this.fechaContratacion = fechaContratacion;
        this.salarioBasico = salarioBasico;
    }

    public String getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public void setCodigoEmpleado(String codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    public String getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(String fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public double getSalarioBasico() {
        return salarioBasico;
    }

    public void setSalarioBasico(double salarioBasico) {
        this.salarioBasico = salarioBasico;
    }

    @Override
    public String toString() {
        return "Operario{" +
                "codigoEmpleado='" + codigoEmpleado + '\'' +
                ", fechaContratacion='" + fechaContratacion + '\'' +
                ", salarioBasico=" + salarioBasico +
                '}';
    }
}
