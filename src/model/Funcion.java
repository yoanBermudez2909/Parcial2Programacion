package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Funcion {
    private String codigo;
    private LocalDate fehca;
    private LocalTime horaInicio;
    private double precioBase;

    private List<Sala>listFuncionSala;
    private List<Pelicula>listFuncionPelicula;

    public Funcion(String codigo, LocalDate fehca, LocalTime horaInicio, double precioBase, List<Sala> listFuncionSala, List<Pelicula> listFuncionPelicula) {
        this.codigo = codigo;
        this.fehca = fehca;
        this.horaInicio = horaInicio;
        this.precioBase = precioBase;

        this.listFuncionSala = new ArrayList<>();
        this.listFuncionPelicula = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDate getFehca() {
        return fehca;
    }

    public void setFehca(LocalDate fehca) {
        this.fehca = fehca;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    public List<Sala> getListFuncionSala() {
        return listFuncionSala;
    }

    public void setListFuncionSala(List<Sala> listFuncionSala) {
        this.listFuncionSala = listFuncionSala;
    }

    public List<Pelicula> getListFuncionPelicula() {
        return listFuncionPelicula;
    }

    public void setListFuncionPelicula(List<Pelicula> listFuncionPelicula) {
        this.listFuncionPelicula = listFuncionPelicula;
    }

    @Override
    public String toString() {
        return "Funcion{" +
                "codigo='" + codigo + '\'' +
                ", fehca=" + fehca +
                ", horaInicio=" + horaInicio +
                ", precioBase=" + precioBase +
                ", listFuncionSala=" + listFuncionSala +
                ", listFuncionPelicula=" + listFuncionPelicula +
                '}';
    }
}
