package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Cine {
    private String nombreComercial;
    private String nit;
    private String direccion;

    private List<Sala> listCineSalas;
    private List<Pelicula> listCinePeliculas;
    private List<Funcion> listCineFunciones;
    private List<Persona> listCinePersonas;
    private List<Venta> listCineVentas;

    /**
     * Metodo constructor de la clase Cine
     * @param nombreComercial nombre comercial del cine
     * @param nit NIT del cine
     * @param direccion direccion del cine
     */
    public Cine(String nombreComercial, String nit, String direccion) {
        this.nombreComercial = nombreComercial;
        this.nit = nit;
        this.direccion = direccion;

        this.listCineSalas = new ArrayList<>();
        this.listCinePeliculas = new ArrayList<>();
        this.listCineFunciones = new ArrayList<>();
        this.listCinePersonas = new ArrayList<>();
        this.listCineVentas = new ArrayList<>();
    }

    /**
     * Metodo que permite registrar un cliente en el cine
     * @param documento documento de identidad del cliente
     * @param nombre nombre del cliente
     * @param correo correo electronico del cliente
     * @param telefono telefono del cliente
     * @param codigoAfilacion codigo de afiliacion del cliente
     * @param porcentajeDescuento porcentaje de descuento del cliente
     */
    public void registrarCliente(String documento, String nombre, String correo, String telefono, String codigoAfilacion, double porcentajeDescuento) {
        if (buscarPersona(documento) == null) {
            Cliente cliente = new Cliente(documento, nombre, correo, telefono, codigoAfilacion, porcentajeDescuento);
            listCinePersonas.add(cliente);
        }
    }

    /**
     * Metodo que permite registrar un operario en el cine
     * @param documento documento de identidad del operario
     * @param nombre nombre del operario
     * @param correo correo electronico del operario
     * @param telefono telefono del operario
     * @param codigoEmpleado codigo de identificacion del empleado
     * @param fechaContratacion fecha de contratacion del operario
     * @param salarioBasico salario basico del operario
     */
    public void registrarOperario(String documento, String nombre, String correo, String telefono, String codigoEmpleado, String fechaContratacion, double salarioBasico) {
        if (buscarPersona(documento) == null) {
            Operario operario = new Operario(documento, nombre, correo, telefono, codigoEmpleado, fechaContratacion, salarioBasico);
            listCinePersonas.add(operario);
        }
    }

    /**
     * Metodo que permite buscar una persona registrada en el cine
     * @param documento documento de identidad de la persona a buscar
     * @return persona encontrada o null si no existe
     */
    public Persona buscarPersona(String documento) {
        for (Persona p : listCinePersonas) {
            if (p.getDocumento().equals(documento)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Metodo que permite buscar una pelicula registrada en el cine
     * @param codigo codigo de la pelicula a buscar
     * @return pelicula encontrada o null si no existe
     */
    public Pelicula buscarPelicula(String codigo) {
        for (Pelicula peli : listCinePeliculas) {
            if (peli.getCodigo().equals(codigo)) {
                return peli;
            }
        }
        return null;
    }

    /**
     * Metodo que permite agregar una pelicula al cine
     * @param codigo codigo de identificacion de la pelicula
     * @param titulo titulo de la pelicula
     * @param duracionMinutos duracion de la pelicula en minutos
     * @param clasifiEdad clasificacion por edad de la pelicula
     * @param generoPelicula genero al que pertenece la pelicula
     */
    public void agregarPelicula(String codigo, String titulo, int duracionMinutos, String clasifiEdad, GeneroPelicula generoPelicula) {
        if (buscarPelicula(codigo) == null) {
            Pelicula peli = new Pelicula(codigo, titulo, duracionMinutos, clasifiEdad, generoPelicula);
            listCinePeliculas.add(peli);
        }
    }

    /**
     * Metodo que permite programar una funcion en una sala del cine
     * @param codigo codigo de identificacion de la funcion
     * @param fehca fecha en la que se realizara la funcion
     * @param horaInicio hora de inicio de la funcion
     * @param precioBase precio base de la funcion
     * @param pelicula pelicula que se proyectara
     * @param sala sala en la que se proyectara la pelicula
     */
    public void programarFuncion(String codigo, LocalDate fehca, LocalTime horaInicio, double precioBase, Pelicula pelicula, Sala sala) {
        if (!detectarConflicto(sala, fehca, horaInicio, pelicula)) {
            Funcion funcion = new Funcion(codigo, fehca, horaInicio, precioBase, sala, pelicula);
            listCineFunciones.add(funcion);
        }
    }

    /**
     * Metodo que permite detectar conflictos de horario en una sala
     * @param sala sala en la que se desea programar la funcion
     * @param fehca fecha de la nueva funcion
     * @param horaInicio hora de inicio de la nueva funcion
     * @param pelicula pelicula que se proyectara en la nueva funcion
     * @return true si existe un conflicto de horario, false en caso contrario
     */
    public boolean detectarConflicto(Sala sala, LocalDate fehca, LocalTime horaInicio, Pelicula pelicula) {
        LocalTime horaFinNueva = horaInicio.plusMinutes(pelicula.getDuracionMinutos());

        for (Funcion funcion : listCineFunciones) {
            if (funcion.getSala().getCodigo().equals(sala.getCodigo()) && funcion.getFehca().equals(fehca)) {
                LocalTime inicioExistente = funcion.getHoraInicio();
                LocalTime finExistente = funcion.calcularHoraFinal();

                if (horaInicio.isBefore(finExistente) && horaFinNueva.isAfter(inicioExistente)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Metodo que permite buscar una venta registrada en el cine
     * @param codigo codigo de identificacion de la venta
     * @return venta encontrada o null si no existe
     */
    public Venta buscarVenta(String codigo) {
        for (Venta venta : listCineVentas) {
            if (venta.getCodigo().equals(codigo)) {
                return venta;
            }
        }
        return null;
    }

    /**
     * Metodo que permite realizar una venta de boletas
     * @param codigo codigo de identificacion de la venta
     * @param fecha fecha en la que se realiza la venta
     * @param cliente cliente que realiza la compra
     * @param operario operario que registra la venta
     * @param funcion funcion para la cual se venden las boletas
     * @param asientos matriz con las filas y columnas de los asientos seleccionados
     * @return true si la venta se realiza correctamente, false si existen datos invalidos o asientos ocupados
     */
    public boolean realizarVenta(String codigo, String fecha, Cliente cliente, Operario operario, Funcion funcion, int[][] asientos) {
        if (cliente == null || operario == null || funcion == null || asientos.length == 0) {
            return false;
        }

        if (buscarVenta(codigo) != null) {
            return false;
        }


        for (int i = 0; i < asientos.length; i++) {
            if (!funcion.asientoDisponible(asientos[i][0], asientos[i][1])) {
                return false;
            }


            for (int j = i + 1; j < asientos.length; j++) {
                if (asientos[i][0] == asientos[j][0] && asientos[i][1] == asientos[j][1]) {
                    return false;
                }
            }
        }

        Venta venta = new Venta(codigo, fecha, operario, cliente, funcion);

        for (int[] a : asientos) {
            venta.agregarBoleta(a[0], a[1]);
        }

        listCineVentas.add(venta);
        funcion.getListFuncionVentas().add(venta);

        return true;
    }

    /**
     * Metodo que permite calcular el total de una venta
     * @param codigoVenta codigo de identificacion de la venta
     * @return valor total de la venta o -1 si la venta no existe
     */
    public double calcularTotalVenta(String codigoVenta) {
        Venta venta = buscarVenta(codigoVenta);

        if (venta == null) {
            return -1;
        }

        return venta.calcularTotal();
    }

    /**
     * Metodo que permite calcular el porcentaje de ocupacion de una funcion
     * @param codigoFuncion codigo de identificacion de la funcion
     * @return porcentaje de ocupacion o -1 si la funcion no existe
     */
    public double calcularOcupacionSalas(String codigoFuncion) {
        Funcion funcion = buscarFuncion(codigoFuncion);

        if (funcion == null) {
            return -1;
        }

        return funcion.calcularPorcentajeOcupacion();
    }

    public Funcion buscarFuncion(String codigoFuncion){
        for (Funcion funcion : listCineFunciones){
            if(funcion.getCodigo().equals(codigoFuncion)){
                return funcion;
            }
        }
        return null;
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

    public List<Funcion> getListCineFunciones() {
        return listCineFunciones;
    }

    public void setListCineFunciones(List<Funcion> listCineFunciones) {
        this.listCineFunciones = listCineFunciones;
    }

    public List<Persona> getListCinePersonas() {
        return listCinePersonas;
    }

    public void setListCinePersonas(List<Persona> listCinePersonas) {
        this.listCinePersonas = listCinePersonas;
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
                ", listCineFuncion=" + listCineFunciones +
                ", listCinePersona=" + listCinePersonas +
                ", listCineVentas=" + listCineVentas +
                '}';
    }
}