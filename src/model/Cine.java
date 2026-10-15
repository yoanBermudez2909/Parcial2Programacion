package model;

import java.util.ArrayList;
import java.util.List;

public class Cine {
    private String nombreComercial;
    private String nit;
    private String direccion;

    private List<Sala> listCineSalas;
    private List<Pelicula> listCinePeliculas;
    private List<Funcion> listCineFuncion;
    private List<Persona> listCinePersona;
    private List<Venta> listCineVentas;

    public Cine(String nombreComercial, String nit, String direccion) {
        this.nombreComercial = nombreComercial;
        this.nit = nit;
        this.direccion = direccion;

        this.listCineSalas = new ArrayList<>();
        this.listCinePeliculas = new ArrayList<>();
        this.listCineFuncion = new ArrayList<>();
        this.listCinePersona = new ArrayList<>();
        this.listCineVentas = new ArrayList<>();
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Sala> getListCineSalas() {
        return listCineSalas;
    }

    public void setListCineSalas(List<Sala> listCineSalas) {
        this.listCineSalas = listCineSalas;
    }

    public List<Pelicula> getListCinePeliculas() {
        return listCinePeliculas;
    }

    public void setListCinePeliculas(List<Pelicula> listCinePeliculas) {
        this.listCinePeliculas = listCinePeliculas;
    }

    public List<Funcion> getListCineFuncion() {
        return listCineFuncion;
    }

    public void setListCineFuncion(List<Funcion> listCineFuncion) {
        this.listCineFuncion = listCineFuncion;
    }

    public List<Persona> getListCinePersona() {
        return listCinePersona;
    }

    public void setListCinePersona(List<Persona> listCinePersona) {
        this.listCinePersona = listCinePersona;
    }

    public List<Venta> getListCineVentas() {
        return listCineVentas;
    }

    public void setListCineVentas(List<Venta> listCineVentas) {
        this.listCineVentas = listCineVentas;
    }

    @Override
    public String toString() {
        return "Cine{" +
                "nombreComercial='" + nombreComercial + '\'' +
                ", nit='" + nit + '\'' +
                ", direccion='" + direccion + '\'' +
                ", listCineSalas=" + listCineSalas +
                ", listCinePeliculas=" + listCinePeliculas +
                ", listCineFuncion=" + listCineFuncion +
                ", listCinePersona=" + listCinePersona +
                ", listCineVentas=" + listCineVentas +
                '}';
    }
}


