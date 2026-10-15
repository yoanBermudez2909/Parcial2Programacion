package model;

import java.util.List;

public class Pelicula {
    private String codigo;
    private String titulo;
    private int duracionMinutos;
    private String clasifiEdad;

    private List<Funcion> listPeliculaFunciones;
    private GeneroPelicula generoPelicula;

    public Pelicula(String codigo, String titulo, int duracionMinutos, String clasifiEdad, GeneroPelicula generoPelicula) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.duracionMinutos = duracionMinutos;
        this.clasifiEdad = clasifiEdad;

        this.generoPelicula = generoPelicula;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(int duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }

    public String getClasifiEdad() {
        return clasifiEdad;
    }

    public void setClasifiEdad(String clasifiEdad) {
        this.clasifiEdad = clasifiEdad;
    }

    public GeneroPelicula getGeneroPelicula() {
        return generoPelicula;
    }

    public void setGeneroPelicula(GeneroPelicula generoPelicula) {
        this.generoPelicula = generoPelicula;
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "codigo='" + codigo + '\'' +
                ", titulo='" + titulo + '\'' +
                ", duracionMinutos=" + duracionMinutos +
                ", clasifiEdad='" + clasifiEdad + '\'' +
                ", generoPelicula=" + generoPelicula +
                '}';
    }
}
