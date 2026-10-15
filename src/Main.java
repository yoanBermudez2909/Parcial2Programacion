import model.*;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static Cine cine;

    public static void main(String[] args) {

        cine = new Cine("CinePlus","900123456-1","Armenia, Quindio");

        cargarDatosPrueba();

        String opcion;

        do {
            opcion = JOptionPane.showInputDialog(
                    null,
                    "CINEPLUS - SISTEMA DE GESTION\n\n" +
                            "1. Registrar pelicula\n" +
                            "2. Registrar cliente\n" +
                            "3. Registrar operario\n" +
                            "4. Programar funcion\n" +
                            "5. Realizar venta de boletas\n" +
                            "6. Consultar total de una venta\n" +
                            "7. Consultar ocupacion de una funcion\n" +
                            "8. Consultar peliculas\n" +
                            "9. Consultar funciones\n" +
                            "10. Consultar personas\n" +
                            "11. Consultar ventas\n" +
                            "12. Consultar salas y asientos\n" +
                            "0. Salir\n\n" +
                            "Seleccione una opcion:",
                    "CinePlus",
                    JOptionPane.QUESTION_MESSAGE
            );

            if (opcion == null) {
                break;
            }

            try {
                switch (opcion) {
                    case "1":
                        registrarPelicula();
                        break;
                    case "2":
                        registrarCliente();
                        break;
                    case "3":
                        registrarOperario();
                        break;
                    case "4":
                        programarFuncion();
                        break;
                    case "5":
                        realizarVenta();
                        break;
                    case "6":
                        consultarTotalVenta();
                        break;
                    case "7":
                        consultarOcupacion();
                        break;
                    case "8":
                        consultarPeliculas();
                        break;
                    case "9":
                        consultarFunciones();
                        break;
                    case "10":
                        consultarPersonas();
                        break;
                    case "11":
                        consultarVentas();
                        break;
                    case "12":
                        consultarSalas();
                        break;
                    case "0":
                        JOptionPane.showMessageDialog(null,"Gracias por utilizar CinePlus.");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null,"Opcion no valida.");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"No se pudo completar la operacion.\n" + "Detalle: " + e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            }

        } while (!opcion.equals("0"));
    }

    private static void cargarDatosPrueba() {

        Sala tradicional = new SalaTradicional("S01","Sala Tradicional",40);

        Sala vip = new SalaVIP("S02","Sala VIP",30);

        Sala sala3D = new Sala3D("S03","Sala 3D",40);

        tradicional.generarAsientos(5,8);
        vip.generarAsientos(5,6);
        sala3D.generarAsientos(5,8);

        cine.getListCineSalas().add(tradicional);
        cine.getListCineSalas().add(vip);
        cine.getListCineSalas().add(sala3D);

        cine.agregarPelicula("P01","Interestelar",169,"12+",GeneroPelicula.DRAMA);

        cine.agregarPelicula("P02","Toy Story",100,"Todo publico",GeneroPelicula.ANIMADA);

        cine.agregarPelicula("P03","El Conjuro",112,"18+",GeneroPelicula.TERROR);

        cine.registrarCliente("1001","Juan Perez","juan@gmail.com","3001111111","AF001",10);

        cine.registrarCliente("1002","Maria Gomez","maria@gmail.com","3002222222","AF002",0);

        cine.registrarOperario("2001","Carlos Ramirez","carlos@cineplus.com","3101111111","OP001","2025-01-15",1800000);

        cine.registrarOperario("2002","Laura Torres","laura@cineplus.com","3102222222","OP002","2025-03-10",1900000);

        LocalDate fecha = LocalDate.now().plusDays(1);

        agregarFuncionPrueba("F01",fecha,LocalTime.of(10,0),12000,"P01","S01");

        agregarFuncionPrueba("F02",fecha,LocalTime.of(10,0),15000,"P02","S02");

        agregarFuncionPrueba("F03",fecha,LocalTime.of(10,0),14000,"P03","S03");
        Funcion funcion = cine.buscarFuncion("F01");
        Cliente cliente = (Cliente) cine.buscarPersona("1001");
        Operario operario = (Operario) cine.buscarPersona("2001");

        if (cine.realizarVenta("V001",LocalDate.now().toString(),cliente,operario,funcion,new int[][]{{1,1},{1,2}})) {
            Venta venta = cine.buscarVenta("V001");
            cliente.getListClienteVentas().add(venta);
            operario.getListOperarioVenta().add(venta);
        }
    }

    private static void agregarFuncionPrueba(String codigo,LocalDate fecha,LocalTime hora,double precio,String codigoPelicula,String codigoSala) {

        Pelicula pelicula = cine.buscarPelicula(codigoPelicula);
        Sala sala = buscarSala(codigoSala);

        cine.programarFuncion(codigo,fecha,hora,precio,pelicula,sala);

        Funcion funcion = cine.buscarFuncion(codigo);

        if (funcion != null) {
            sala.getListSalaFunciones().add(funcion);
        }
    }

    private static void registrarPelicula() {

        String codigo = pedirTexto("Codigo de la pelicula:");
        if (codigo == null) return;

        if (cine.buscarPelicula(codigo) != null) {
            mensaje("Ya existe una pelicula con ese codigo.");
            return;
        }

        String titulo = pedirTexto("Titulo:");
        if (titulo == null) return;

        int duracion = pedirEntero("Duracion en minutos:");
        if (duracion <= 0) {
            mensaje("La duracion debe ser mayor que cero.");
            return;
        }

        String clasificacion = pedirTexto("Clasificacion por edad:");
        if (clasificacion == null) return;

        GeneroPelicula genero = (GeneroPelicula) JOptionPane.showInputDialog(null,"Seleccione el genero:","Genero",JOptionPane.QUESTION_MESSAGE,null,GeneroPelicula.values(),GeneroPelicula.ACCION);

        if (genero == null) return;

        cine.agregarPelicula(codigo,titulo,duracion,clasificacion,genero);

        mensaje("Pelicula registrada correctamente.");
    }

    private static void registrarCliente() {

        String documento = pedirTexto("Documento:");
        if (documento == null) return;

        if (cine.buscarPersona(documento) != null) {
            mensaje("Ya existe una persona con ese documento.");
            return;
        }

        String nombre = pedirTexto("Nombre completo:");
        if (nombre == null) return;

        String correo = pedirTexto("Correo electronico:");
        if (correo == null) return;

        String telefono = pedirTexto("Telefono:");
        if (telefono == null) return;

        String afiliacion = pedirTexto("Codigo de afiliacion:");
        if (afiliacion == null) return;

        double descuento = pedirDouble("Porcentaje de descuento:");

        if (descuento < 0 || descuento > 100) {
            mensaje("El descuento debe estar entre 0 y 100.");
            return;
        }

        cine.registrarCliente(documento,nombre,correo,telefono,afiliacion,descuento);

        mensaje("Cliente registrado correctamente.");
    }

    private static void registrarOperario() {

        String documento = pedirTexto("Documento:");
        if (documento == null) return;

        if (cine.buscarPersona(documento) != null) {
            mensaje("Ya existe una persona con ese documento.");
            return;
        }

        String nombre = pedirTexto("Nombre completo:");
        if (nombre == null) return;

        String correo = pedirTexto("Correo electronico:");
        if (correo == null) return;

        String telefono = pedirTexto("Telefono:");
        if (telefono == null) return;

        String codigo = pedirTexto("Codigo de empleado:");
        if (codigo == null) return;

        String fecha = pedirTexto("Fecha de contratacion (AAAA-MM-DD):");
        if (fecha == null) return;

        double salario = pedirDouble("Salario basico:");

        if (salario < 0) {
            mensaje("El salario no puede ser negativo.");
            return;
        }

        cine.registrarOperario(documento,nombre,correo,telefono,codigo,fecha,salario);

        mensaje("Operario registrado correctamente.");
    }

    private static void programarFuncion() {

        if (cine.getListCinePeliculas().isEmpty() || cine.getListCineSalas().isEmpty()) {
            mensaje("Primero debe haber peliculas y salas registradas.");
            return;
        }

        String codigo = pedirTexto("Codigo de la funcion:");
        if (codigo == null) return;

        if (cine.buscarFuncion(codigo) != null) {
            mensaje("Ya existe una funcion con ese codigo.");
            return;
        }

        mostrarPeliculas();
        String codigoPelicula = pedirTexto("Codigo de la pelicula:");
        if (codigoPelicula == null) return;

        Pelicula pelicula = cine.buscarPelicula(codigoPelicula);

        if (pelicula == null) {
            mensaje("La pelicula no existe.");
            return;
        }

        String codigoSala = pedirTexto("Codigo de la sala:");
        if (codigoSala == null) return;

        Sala sala = buscarSala(codigoSala);

        if (sala == null) {
            mensaje("La sala no existe.");
            return;
        }

        String fechaTexto = pedirTexto("Fecha (AAAA-MM-DD):");
        if (fechaTexto == null) return;

        String horaTexto = pedirTexto("Hora de inicio (HH:MM):");
        if (horaTexto == null) return;

        LocalDate fecha = LocalDate.parse(fechaTexto);
        LocalTime hora = LocalTime.parse(horaTexto);

        double precio = pedirDouble("Precio base de la boleta:");

        if (precio < 0) {
            mensaje("El precio no puede ser negativo.");
            return;
        }

        boolean conflicto = cine.detectarConflicto(sala,fecha,hora,pelicula);

        if (conflicto) {
            mensaje("La sala ya tiene una funcion en ese horario.");
            return;
        }

        cine.programarFuncion(codigo,fecha,hora,precio,pelicula,sala);

        Funcion funcion = cine.buscarFuncion(codigo);

        if (funcion != null) {
            sala.getListSalaFunciones().add(funcion);
            mensaje("Funcion programada correctamente.");
        } else {
            mensaje("No fue posible programar la funcion.");
        }
    }

    private static void realizarVenta() {

        String codigo = pedirTexto("Codigo de la venta:");
        if (codigo == null) return;

        if (cine.buscarVenta(codigo) != null) {
            mensaje("Ya existe una venta con ese codigo.");
            return;
        }

        mostrarPersonas();

        String documentoCliente = pedirTexto("Documento del cliente:");
        if (documentoCliente == null) return;

        Persona personaCliente = cine.buscarPersona(documentoCliente);

        if (!(personaCliente instanceof Cliente)) {
            mensaje("El documento no corresponde a un cliente.");
            return;
        }

        Cliente cliente = (Cliente) personaCliente;

        mostrarOperarios();

        String documentoOperario = pedirTexto("Documento del operario:");
        if (documentoOperario == null) return;

        Persona personaOperario = cine.buscarPersona(documentoOperario);

        if (!(personaOperario instanceof Operario)) {
            mensaje("El documento no corresponde a un operario.");
            return;
        }

        Operario operario = (Operario) personaOperario;

        mostrarFunciones();

        String codigoFuncion = pedirTexto("Codigo de la funcion:");
        if (codigoFuncion == null) return;

        Funcion funcion = cine.buscarFuncion(codigoFuncion);

        if (funcion == null) {
            mensaje("La funcion no existe.");
            return;
        }

        Sala sala = funcion.getSala();

        int cantidad = pedirEntero("Cantidad de boletas:");

        if (cantidad <= 0) {
            mensaje("Debe comprar al menos una boleta.");
            return;
        }

        int[][] asientos = new int[cantidad][2];

        for (int i = 0; i < cantidad; i++) {

            mensaje("Seleccionando asiento " + (i + 1) + " de " + cantidad);

            int fila = pedirEntero("Numero de fila:");
            int columna = pedirEntero("Numero de columna:");

            if (fila <= 0 || columna <= 0 || sala.buscarAsiento(fila,columna) == null) {
                mensaje("El asiento no existe en esta sala.");
                return;
            }

            if (!funcion.asientoDisponible(fila,columna)) {
                mensaje("El asiento ya esta ocupado.");
                return;
            }

            for (int j = 0; j < i; j++) {
                if (asientos[j][0] == fila && asientos[j][1] == columna) {
                    mensaje("No puede seleccionar el mismo asiento dos veces.");
                    return;
                }
            }

            asientos[i][0] = fila;
            asientos[i][1] = columna;
        }

        String fechaVenta = LocalDate.now().toString();

        boolean realizada = cine.realizarVenta(codigo,fechaVenta,cliente,operario,funcion,asientos);

        if (realizada) {

            Venta venta = cine.buscarVenta(codigo);

            cliente.getListClienteVentas().add(venta);
            operario.getListOperarioVenta().add(venta);

            mensaje("Venta realizada correctamente.\n\n" + "Codigo: " + venta.getCodigo() + "\n" + "Cliente: " + cliente.getNombre() + "\n" + "Funcion: " + funcion.getCodigo() + "\n" + "Cantidad de boletas: " + venta.getListVentaBoletas().size() + "\n" + "Total: $" + venta.calcularTotal());

        } else {
            mensaje("No fue posible realizar la venta.");
        }
    }

    private static void consultarTotalVenta() {

        String codigo = pedirTexto("Codigo de la venta:");
        if (codigo == null) return;

        Venta venta = cine.buscarVenta(codigo);

        if (venta == null) {
            mensaje("No existe una venta con ese codigo.");
            return;
        }

        mensaje("VENTA: " + venta.getCodigo() + "\n" + "Fecha: " + venta.getFecha() + "\n" + "Cliente: " + venta.getCliente().getNombre() + "\n" + "Funcion: " + venta.getTheFuncion().getCodigo() + "\n\n" + "Boletas:\n" + venta.getListVentaBoletas() + "\n\n" + "TOTAL: $" + cine.calcularTotalVenta(codigo));
    }

    private static void consultarOcupacion() {

        mostrarFunciones();

        String codigo = pedirTexto("Codigo de la funcion:");
        if (codigo == null) return;

        double ocupacion = cine.calcularOcupacionSalas(codigo);

        if (ocupacion == -1) {
            mensaje("No existe una funcion con ese codigo.");
            return;
        }

        Funcion funcion = cine.buscarFuncion(codigo);

        mensaje("Pelicula: " + funcion.getPelicula().getTitulo() + "\n" + "Sala: " + funcion.getSala().getNombre() + "\n" + "Boletas vendidas: " + contarBoletas(funcion) + "\n" + "Capacidad: " + funcion.getSala().getCapacidadMaxima() + "\n" + "Ocupacion: " + String.format("%.2f",ocupacion) + "%");
    }

    private static int contarBoletas(Funcion funcion) {
        int total = 0;

        for (Venta venta : funcion.getListFuncionVentas()) {
            total += venta.getListVentaBoletas().size();
        }

        return total;
    }

    private static void consultarPeliculas() {
        mostrarPeliculas();
    }

    private static void mostrarPeliculas() {

        String texto = "PELICULAS REGISTRADAS\n\n";

        for (Pelicula pelicula : cine.getListCinePeliculas()) {
            texto += pelicula.toString() + "\n\n";
        }

        if (cine.getListCinePeliculas().isEmpty()) {
            texto += "No hay peliculas registradas.";
        }

        mensaje(texto);
    }

    private static void consultarFunciones() {
        mostrarFunciones();
    }

    private static void mostrarFunciones() {

        String texto = "FUNCIONES PROGRAMADAS\n\n";

        for (Funcion funcion : cine.getListCineFunciones()) {
            texto += "Codigo: " + funcion.getCodigo() + "\n" + "Pelicula: " + funcion.getPelicula().getTitulo() + "\n" + "Sala: " + funcion.getSala().getNombre() + "\n" + "Fecha: " + funcion.getFehca() + "\n" + "Hora: " + funcion.getHoraInicio() + "\n" + "Finaliza: " + funcion.calcularHoraFinal() + "\n\n";
        }

        if (cine.getListCineFunciones().isEmpty()) {
            texto += "No hay funciones programadas.";
        }

        mensaje(texto);
    }

    private static void consultarPersonas() {
        mostrarPersonas();
    }

    private static void mostrarPersonas() {

        String texto = "PERSONAS REGISTRADAS\n\n";

        for (Persona persona : cine.getListCinePersonas()) {
            texto += persona.toString() + "\n";

            if (persona instanceof Cliente) {
                Cliente cliente = (Cliente) persona;
                texto += "Tipo: Cliente\n" + "Afiliacion: " + cliente.getCodigoAfilacion() + "\n" + "Descuento: " + cliente.getPorcentajeDescuento() + "%\n\n";
            } else if (persona instanceof Operario) {
                Operario operario = (Operario) persona;
                texto += "Tipo: Operario\n" + "Codigo empleado: " + operario.getCodigoEmpleado() + "\n\n";
            }
        }

        if (cine.getListCinePersonas().isEmpty()) {
            texto += "No hay personas registradas.";
        }

        mensaje(texto);
    }

    private static void mostrarOperarios() {

        String texto = "OPERARIOS\n\n";

        for (Persona persona : cine.getListCinePersonas()) {
            if (persona instanceof Operario) {
                texto += persona.getDocumento() + " - "
                        + persona.getNombre() + "\n";
            }
        }

        mensaje(texto);
    }

    private static void consultarVentas() {

        String texto = "VENTAS REGISTRADAS\n\n";

        for (Venta venta : cine.getListCineVentas()) {
            texto += "Codigo: " + venta.getCodigo() + "\n" + "Fecha: " + venta.getFecha() + "\n" + "Cliente: " + venta.getCliente().getNombre() + "\n" + "Operario: " + venta.getOperario().getNombre() + "\n" + "Total: $" + venta.calcularTotal() + "\n\n";
        }

        if (cine.getListCineVentas().isEmpty()) {
            texto += "No hay ventas registradas.";
        }

        mensaje(texto);
    }

    private static void consultarSalas() {

        String texto = "SALAS DEL CINE\n\n";

        for (Sala sala : cine.getListCineSalas()) {
            texto += sala.toString() + "\n" + "Asientos registrados: "
                    + sala.getListSalaAsientos().size() + "\n\n";
        }

        mensaje(texto);
    }

    private static Sala buscarSala(String codigo) {

        for (Sala sala : cine.getListCineSalas()) {
            if (sala.getCodigo().equals(codigo)) {
                return sala;
            }
        }

        return null;
    }

    private static void mensaje(String texto) {
        JOptionPane.showMessageDialog(null,texto);
    }

    private static String pedirTexto(String mensaje) {
        String texto = JOptionPane.showInputDialog(null,mensaje);

        if (texto == null) {
            return null;
        }

        texto = texto.trim();

        if (texto.isEmpty()) {
            JOptionPane.showMessageDialog(null,"El campo no puede estar vacio.");
            return null;
        }

        return texto;
    }

    private static int pedirEntero(String mensaje) {
        String texto = pedirTexto(mensaje);

        if (texto == null) {
            throw new IllegalArgumentException("Entrada cancelada o vacia.");
        }

        return Integer.parseInt(texto);
    }

    private static double pedirDouble(String mensaje) {
        String texto = pedirTexto(mensaje);

        if (texto == null) {
            throw new IllegalArgumentException("Entrada cancelada o vacia.");
        }

        return Double.parseDouble(texto.replace(",","."));
    }
}
