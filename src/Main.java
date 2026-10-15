import model.*;

import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        Cine cine = new Cine("CinePlus","900123456-1","Armenia, Quindio");

        {
            Sala tradicional = new SalaTradicional("S01","Sala Tradicional",40);
            Sala vip = new SalaVIP("S02","Sala VIP",30);
            Sala sala3D = new Sala3D("S03","Sala 3D",40);

            tradicional.generarAsientos(5,8);
            vip.generarAsientos(5,6);
            sala3D.generarAsientos(5,8);

            cine.agregarSala(tradicional);
            cine.agregarSala(vip);
            cine.agregarSala(sala3D);

            cine.agregarPelicula("P01","Interestelar",169,"12+",GeneroPelicula.DRAMA);
            cine.agregarPelicula("P02","Toy Story",100,"Todo publico",GeneroPelicula.ANIMADA);
            cine.agregarPelicula("P03","El Conjuro",112,"18+",GeneroPelicula.TERROR);

            cine.registrarCliente("1001","Juan Perez","juan@gmail.com","3001111111","AF001",10);
            cine.registrarCliente("1002","Maria Gomez","maria@gmail.com","3002222222","AF002",0);

            cine.registrarOperario("2001","Carlos Ramirez","carlos@cineplus.com","3101111111","OP001","2025-01-15",1800000);
            cine.registrarOperario("2002","Laura Torres","laura@cineplus.com","3102222222","OP002","2025-03-10",1900000);

            LocalDate fecha = LocalDate.now().plusDays(1);

            cine.programarFuncion("F01",fecha,LocalTime.of(10,0),12000,cine.buscarPelicula("P01"),cine.buscarSala("S01"));
            cine.programarFuncion("F02",fecha,LocalTime.of(10,0),15000,cine.buscarPelicula("P02"),cine.buscarSala("S02"));
            cine.programarFuncion("F03",fecha,LocalTime.of(10,0),14000,cine.buscarPelicula("P03"),cine.buscarSala("S03"));

            Funcion funcion = cine.buscarFuncion("F01");
            Cliente cliente = (Cliente) cine.buscarPersona("1001");
            Operario operario = (Operario) cine.buscarPersona("2001");

            cine.realizarVenta("V001",LocalDate.now().toString(),cliente,operario,funcion,new int[][]{{1,1},{1,2}});
        }

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
                    case "1": {
                        String codigo = pedirTexto("Codigo de la pelicula:");
                        if (codigo == null) break;

                        if (cine.buscarPelicula(codigo) != null) {
                            JOptionPane.showMessageDialog(null,"Ya existe una pelicula con ese codigo.");
                            break;
                        }

                        String titulo = pedirTexto("Titulo:");
                        if (titulo == null) break;

                        int duracion = pedirEntero("Duracion en minutos:");
                        if (duracion <= 0) {
                            JOptionPane.showMessageDialog(null,"La duracion debe ser mayor que cero.");
                            break;
                        }

                        String clasificacion = pedirTexto("Clasificacion por edad:");
                        if (clasificacion == null) break;

                        GeneroPelicula genero = (GeneroPelicula) JOptionPane.showInputDialog(null,"Seleccione el genero:","Genero",JOptionPane.QUESTION_MESSAGE,null,GeneroPelicula.values(),GeneroPelicula.ACCION);
                        if (genero == null) break;

                        cine.agregarPelicula(codigo,titulo,duracion,clasificacion,genero);

                        JOptionPane.showMessageDialog(null,"Pelicula registrada correctamente.");
                        break;
                    }

                    case "2": {
                        String documento = pedirTexto("Documento:");
                        if (documento == null) break;

                        if (cine.buscarPersona(documento) != null) {
                            JOptionPane.showMessageDialog(null,"Ya existe una persona con ese documento.");
                            break;
                        }

                        String nombre = pedirTexto("Nombre completo:");
                        if (nombre == null) break;

                        String correo = pedirTexto("Correo electronico:");
                        if (correo == null) break;

                        String telefono = pedirTexto("Telefono:");
                        if (telefono == null) break;

                        String afiliacion = pedirTexto("Codigo de afiliacion:");
                        if (afiliacion == null) break;

                        double descuento = pedirDouble("Porcentaje de descuento:");
                        if (descuento < 0 || descuento > 100) {
                            JOptionPane.showMessageDialog(null,"El descuento debe estar entre 0 y 100.");
                            break;
                        }

                        cine.registrarCliente(documento,nombre,correo,telefono,afiliacion,descuento);

                        JOptionPane.showMessageDialog(null,"Cliente registrado correctamente.");
                        break;
                    }

                    case "3": {
                        String documento = pedirTexto("Documento:");
                        if (documento == null) break;

                        if (cine.buscarPersona(documento) != null) {
                            JOptionPane.showMessageDialog(null,"Ya existe una persona con ese documento.");
                            break;
                        }

                        String nombre = pedirTexto("Nombre completo:");
                        if (nombre == null) break;

                        String correo = pedirTexto("Correo electronico:");
                        if (correo == null) break;

                        String telefono = pedirTexto("Telefono:");
                        if (telefono == null) break;

                        String codigoEmpleado = pedirTexto("Codigo de empleado:");
                        if (codigoEmpleado == null) break;

                        String fechaContratacion = pedirTexto("Fecha de contratacion (AAAA-MM-DD):");
                        if (fechaContratacion == null) break;

                        double salario = pedirDouble("Salario basico:");
                        if (salario < 0) {
                            JOptionPane.showMessageDialog(null,"El salario no puede ser negativo.");
                            break;
                        }

                        cine.registrarOperario(documento,nombre,correo,telefono,codigoEmpleado,fechaContratacion,salario);

                        JOptionPane.showMessageDialog(null,"Operario registrado correctamente.");
                        break;
                    }

                    case "4": {
                        if (cine.getListCinePeliculas().isEmpty() || cine.getListCineSalas().isEmpty()) {
                            JOptionPane.showMessageDialog(null,"Primero debe haber peliculas y salas registradas.");
                            break;
                        }

                        String codigo = pedirTexto("Codigo de la funcion:");
                        if (codigo == null) break;

                        if (cine.buscarFuncion(codigo) != null) {
                            JOptionPane.showMessageDialog(null,"Ya existe una funcion con ese codigo.");
                            break;
                        }

                        String listaPeliculas = "";
                        for (Pelicula p : cine.getListCinePeliculas()) {
                            listaPeliculas += p.getCodigo() + " - " + p.getTitulo() + " (" + p.getDuracionMinutos() + " min)\n";
                        }

                        String codigoPelicula = pedirTexto("PELICULAS\n" + listaPeliculas + "\nCodigo de la pelicula:");
                        if (codigoPelicula == null) break;

                        Pelicula pelicula = cine.buscarPelicula(codigoPelicula);
                        if (pelicula == null) {
                            JOptionPane.showMessageDialog(null,"La pelicula no existe.");
                            break;
                        }

                        String listaSalas = "";
                        for (Sala s : cine.getListCineSalas()) {
                            listaSalas += s.getCodigo() + " - " + s.getNombre() + "\n";
                        }

                        String codigoSala = pedirTexto("SALAS\n" + listaSalas + "\nCodigo de la sala:");
                        if (codigoSala == null) break;

                        Sala sala = cine.buscarSala(codigoSala);
                        if (sala == null) {
                            JOptionPane.showMessageDialog(null,"La sala no existe.");
                            break;
                        }

                        String fechaTexto = pedirTexto("Fecha (AAAA-MM-DD):");
                        if (fechaTexto == null) break;

                        String horaTexto = pedirTexto("Hora de inicio (HH:MM):");
                        if (horaTexto == null) break;

                        LocalDate fecha = LocalDate.parse(fechaTexto);
                        LocalTime hora = LocalTime.parse(horaTexto);

                        double precio = pedirDouble("Precio base de la boleta:");
                        if (precio < 0) {
                            JOptionPane.showMessageDialog(null,"El precio no puede ser negativo.");
                            break;
                        }

                        if (cine.detectarConflicto(sala,fecha,hora,pelicula)) {
                            JOptionPane.showMessageDialog(null,"La sala ya tiene una funcion en ese horario.");
                            break;
                        }

                        cine.programarFuncion(codigo,fecha,hora,precio,pelicula,sala);

                        if (cine.buscarFuncion(codigo) != null) {
                            JOptionPane.showMessageDialog(null,"Funcion programada correctamente.");
                        } else {
                            JOptionPane.showMessageDialog(null,"No fue posible programar la funcion.");
                        }
                        break;
                    }

                    case "5": {
                        String codigo = pedirTexto("Codigo de la venta:");
                        if (codigo == null) break;

                        if (cine.buscarVenta(codigo) != null) {
                            JOptionPane.showMessageDialog(null,"Ya existe una venta con ese codigo.");
                            break;
                        }

                        String listaClientes = "";
                        for (Persona p : cine.getListCinePersonas()) {
                            if (p instanceof Cliente) {
                                listaClientes += p.getDocumento() + " - " + p.getNombre() + "\n";
                            }
                        }

                        String documentoCliente = pedirTexto("CLIENTES\n" + listaClientes + "\nDocumento del cliente:");
                        if (documentoCliente == null) break;

                        Persona personaCliente = cine.buscarPersona(documentoCliente);
                        if (!(personaCliente instanceof Cliente)) {
                            JOptionPane.showMessageDialog(null,"El documento no corresponde a un cliente.");
                            break;
                        }

                        Cliente cliente = (Cliente) personaCliente;

                        String listaOperarios = "";
                        for (Persona p : cine.getListCinePersonas()) {
                            if (p instanceof Operario) {
                                listaOperarios += p.getDocumento() + " - " + p.getNombre() + "\n";
                            }
                        }

                        String documentoOperario = pedirTexto("OPERARIOS\n" + listaOperarios + "\nDocumento del operario:");
                        if (documentoOperario == null) break;

                        Persona personaOperario = cine.buscarPersona(documentoOperario);
                        if (!(personaOperario instanceof Operario)) {
                            JOptionPane.showMessageDialog(null,"El documento no corresponde a un operario.");
                            break;
                        }

                        Operario operario = (Operario) personaOperario;

                        String listaFunciones = "";
                        for (Funcion f : cine.getListCineFunciones()) {
                            listaFunciones += f.getCodigo() + " - " + f.getPelicula().getTitulo() + " | " + f.getSala().getNombre() + " | " + f.getFehca() + " " + f.getHoraInicio() + "\n";
                        }

                        String codigoFuncion = pedirTexto("FUNCIONES\n" + listaFunciones + "\nCodigo de la funcion:");
                        if (codigoFuncion == null) break;

                        Funcion funcion = cine.buscarFuncion(codigoFuncion);
                        if (funcion == null) {
                            JOptionPane.showMessageDialog(null,"La funcion no existe.");
                            break;
                        }

                        Sala sala = funcion.getSala();

                        int cantidad = pedirEntero("Cantidad de boletas:");
                        if (cantidad <= 0) {
                            JOptionPane.showMessageDialog(null,"Debe comprar al menos una boleta.");
                            break;
                        }

                        int[][] asientos = new int[cantidad][2];
                        boolean asientosValidos = true;

                        for (int i = 0; i < cantidad && asientosValidos; i++) {
                            JOptionPane.showMessageDialog(null,"Seleccionando asiento " + (i + 1) + " de " + cantidad);

                            int fila = pedirEntero("Numero de fila:");
                            int columna = pedirEntero("Numero de columna:");

                            boolean repetido = false;
                            for (int j = 0; j < i; j++) {
                                if (asientos[j][0] == fila && asientos[j][1] == columna) {
                                    repetido = true;
                                }
                            }

                            if (fila <= 0 || columna <= 0 || sala.buscarAsiento(fila,columna) == null) {
                                JOptionPane.showMessageDialog(null,"El asiento no existe en esta sala.");
                                asientosValidos = false;
                            } else if (!funcion.asientoDisponible(fila,columna)) {
                                JOptionPane.showMessageDialog(null,"El asiento ya esta ocupado.");
                                asientosValidos = false;
                            } else if (repetido) {
                                JOptionPane.showMessageDialog(null,"No puede seleccionar el mismo asiento dos veces.");
                                asientosValidos = false;
                            } else {
                                asientos[i][0] = fila;
                                asientos[i][1] = columna;
                            }
                        }

                        if (!asientosValidos) break;

                        boolean realizada = cine.realizarVenta(codigo,LocalDate.now().toString(),cliente,operario,funcion,asientos);

                        if (realizada) {
                            Venta venta = cine.buscarVenta(codigo);

                            JOptionPane.showMessageDialog(null,"Venta realizada correctamente.\n\n" + "Codigo: " + venta.getCodigo() + "\n" + "Cliente: " + cliente.getNombre() + "\n" + "Funcion: " + funcion.getCodigo() + "\n" + "Cantidad de boletas: " + venta.getListVentaBoletas().size() + "\n" + "Total: $" + venta.calcularTotal());
                        } else {
                            JOptionPane.showMessageDialog(null,"No fue posible realizar la venta.");
                        }
                        break;
                    }

                    case "6": {
                        String codigo = pedirTexto("Codigo de la venta:");
                        if (codigo == null) break;

                        Venta venta = cine.buscarVenta(codigo);
                        if (venta == null) {
                            JOptionPane.showMessageDialog(null,"No existe una venta con ese codigo.");
                            break;
                        }

                        JOptionPane.showMessageDialog(null,"VENTA: " + venta.getCodigo() + "\n" + "Fecha: " + venta.getFecha() + "\n" + "Cliente: " + venta.getCliente().getNombre() + "\n" + "Funcion: " + venta.getTheFuncion().getCodigo() + "\n\n" + "Boletas:\n" + venta.getListVentaBoletas() + "\n\n" + "TOTAL: $" + cine.calcularTotalVenta(codigo));
                        break;
                    }

                    case "7": {
                        String listaFunciones = "";
                        for (Funcion f : cine.getListCineFunciones()) {
                            listaFunciones += f.getCodigo() + " - " + f.getPelicula().getTitulo() + " | " + f.getSala().getNombre() + "\n";
                        }

                        String codigo = pedirTexto("FUNCIONES\n" + listaFunciones + "\nCodigo de la funcion:");
                        if (codigo == null) break;

                        double ocupacion = cine.calcularOcupacionSalas(codigo);
                        if (ocupacion == -1) {
                            JOptionPane.showMessageDialog(null,"No existe una funcion con ese codigo.");
                            break;
                        }

                        Funcion funcion = cine.buscarFuncion(codigo);

                        int vendidas = 0;
                        for (Venta v : funcion.getListFuncionVentas()) {
                            vendidas += v.getListVentaBoletas().size();
                        }

                        JOptionPane.showMessageDialog(null,"Pelicula: " + funcion.getPelicula().getTitulo() + "\n" + "Sala: " + funcion.getSala().getNombre() + "\n" + "Boletas vendidas: " + vendidas + "\n" + "Capacidad: " + funcion.getSala().getCapacidadMaxima() + "\n" + "Ocupacion: " + String.format("%.2f",ocupacion) + "%");
                        break;
                    }

                    case "8": {
                        String texto = "PELICULAS REGISTRADAS\n\n";

                        for (Pelicula pelicula : cine.getListCinePeliculas()) {
                            texto += pelicula.toString() + "\n\n";
                        }

                        if (cine.getListCinePeliculas().isEmpty()) {
                            texto += "No hay peliculas registradas.";
                        }

                        JOptionPane.showMessageDialog(null,texto);
                        break;
                    }

                    case "9": {
                        String texto = "FUNCIONES PROGRAMADAS\n\n";

                        for (Funcion funcion : cine.getListCineFunciones()) {
                            texto += "Codigo: " + funcion.getCodigo() + "\n" + "Pelicula: " + funcion.getPelicula().getTitulo() + "\n" + "Sala: " + funcion.getSala().getNombre() + "\n" + "Fecha: " + funcion.getFehca() + "\n" + "Hora: " + funcion.getHoraInicio() + "\n" + "Finaliza: " + funcion.calcularHoraFinal() + "\n\n";
                        }

                        if (cine.getListCineFunciones().isEmpty()) {
                            texto += "No hay funciones programadas.";
                        }

                        JOptionPane.showMessageDialog(null,texto);
                        break;
                    }

                    case "10": {
                        String texto = "PERSONAS REGISTRADAS\n\n";

                        for (Persona persona : cine.getListCinePersonas()) {
                            texto += "Documento: " + persona.getDocumento() + "\n" + "Nombre: " + persona.getNombre() + "\n" + "Correo: " + persona.getCorreo() + "\n" + "Telefono: " + persona.getTelefono() + "\n";

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

                        JOptionPane.showMessageDialog(null,texto);
                        break;
                    }

                    case "11": {
                        String texto = "VENTAS REGISTRADAS\n\n";

                        for (Venta venta : cine.getListCineVentas()) {
                            texto += "Codigo: " + venta.getCodigo() + "\n" + "Fecha: " + venta.getFecha() + "\n" + "Cliente: " + venta.getCliente().getNombre() + "\n" + "Operario: " + venta.getOperario().getNombre() + "\n" + "Total: $" + venta.calcularTotal() + "\n\n";
                        }

                        if (cine.getListCineVentas().isEmpty()) {
                            texto += "No hay ventas registradas.";
                        }

                        JOptionPane.showMessageDialog(null,texto);
                        break;
                    }

                    case "12": {
                        String texto = "SALAS DEL CINE\n\n";

                        for (Sala sala : cine.getListCineSalas()) {
                            texto += sala.toString() + "\n" + "Asientos registrados: " + sala.getListSalaAsientos().size() + "\n\n";
                        }

                        JOptionPane.showMessageDialog(null,texto);
                        break;
                    }

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
 