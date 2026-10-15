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
    private Sala sala;
    private Pelicula pelicula;

    private List<Venta> listFuncionVentas;


    public Funcion(String codigo, LocalDate fehca, LocalTime horaInicio, double precioBase, Sala sala, Pelicula pelicula) {
        this.codigo = codigo;
        this.fehca = fehca;
        this.horaInicio = horaInicio;
        this.precioBase = precioBase;
        this.sala = sala;
        this.pelicula = pelicula;

        this.listFuncionVentas = new ArrayList<>();

    }


    public boolean seCruzaCon(Funcion otra) {
        if (!fehca.equals(otra.getFehca())) {
            return false;
        }
        return horaInicio.isBefore(otra.calcularHoraFinal())
                && calcularHoraFinal().isAfter(otra.getHoraInicio());
    }


    public boolean asientoDisponible(int fila, int numero) {

        for(Venta venta : listFuncionVentas){

            for(Boleta boleta : venta.getListVentaBoletas()){

                if(boleta.getFila() == fila && boleta.getNumeroAsientos() == numero){

                    return false;
                }
            }
        }

        return true;
    }

    public LocalTime calcularHoraFinal() {
        return horaInicio.plusMinutes(pelicula.getDuracionMinutos());
    }

    public double calcularPorcentajeOcupacion() {
        int boletasVendidas = 0;
        for (Venta venta : listFuncionVentas) {
            boletasVendidas += venta.getListVentaBoletas().size();
        }
        int capacidad = sala.getCapacidadMaxima();
        if (capacidad == 0) {
            return 0;
        }
        return (boletasVendidas * 100.0) / capacidad;
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

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public List<Venta> getListFuncionVentas() {
        return listFuncionVentas;
    }

    public void setListFuncionVentas(List<Venta> listFuncionVenta) {
        this.listFuncionVentas = listFuncionVenta;
    }

    @Override
    public String toString() {
        return "Funcion{" +
                "codigo='" + codigo + '\'' +
                ", fehca=" + fehca +
                ", horaInicio=" + horaInicio +
                ", precioBase=" + precioBase +
                ", sala=" + sala +
                ", pelicula=" + pelicula +
                ", listFuncionVentas=" + listFuncionVentas +
                '}';
    }
}



