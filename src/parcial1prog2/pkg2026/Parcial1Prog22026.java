package parcial1prog2.pkg2026;

import java.util.Calendar;
import java.util.Scanner;

public class Parcial1Prog22026 {

    private static GestorVehiculos gestor = new GestorVehiculos();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion = 0;
        while (opcion != 8) {
            mostrarMenu();
            opcion = leerEntero("Opcion: ");
            if (opcion >= 2 && opcion <= 7 && !gestor.hayVehiculos()) {
                System.out.println("No hay vehiculos cargados. Agregue un vehiculo primero (opcion 1).");
                continue;
            }
            switch (opcion) {
                case 1:
                    altaVehiculo();
                    break;
                case 2:
                    gestor.mostrarTodos();
                    break;
                case 3:
                    gestor.realizarServicios();
                    break;
                case 4:
                    buscarVehiculo();
                    break;
                case 5:
                    gestor.mostrarConCapacidadMayorA(leerEnteroMinimo("Mostrar vehiculos con capacidad mayor a: ", 1));
                    break;
                case 6:
                    gestor.mostrarOrdenadosPorAnioDesc();
                    break;
                case 7:
                    gestor.mostrarOrdenadosPorCapacidadDesc();
                    break;
                case 8:
                    System.out.println("Hasta luego.");
                    break;
                default:
                    System.out.println("Opcion invalida. Ingrese un numero entre 1 y 8.");
            }
        }
        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("===== GESTION DE VEHICULOS TURISTICOS =====");
        System.out.println("1. Agregar vehiculo");
        System.out.println("2. Mostrar todos los vehiculos");
        System.out.println("3. Realizar servicios turisticos");
        System.out.println("4. Buscar vehiculo por patente");
        System.out.println("5. Mostrar vehiculos con capacidad mayor a un valor");
        System.out.println("6. Mostrar vehiculos ordenados por anio (desc)");
        System.out.println("7. Mostrar vehiculos ordenados por capacidad (desc)");
        System.out.println("8. Salir");
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida: debe ingresar un numero entero.");
            }
        }
    }

    private static int leerEnteroMinimo(String mensaje, int minimo) {
        while (true) {
            int valor = leerEntero(mensaje);
            if (valor >= minimo) {
                return valor;
            }
            System.out.println("Entrada invalida: el valor no puede ser menor a " + minimo + ".");
        }
    }

    private static int leerEnteroEnRango(String mensaje, int minimo, int maximo) {
        while (true) {
            int valor = leerEnteroMinimo(mensaje, minimo);
            if (valor <= maximo) {
                return valor;
            }
            System.out.println("Entrada invalida: el valor no puede ser mayor a " + maximo + ".");
        }
    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    private static String leerTextoObligatorio(String mensaje) {
        while (true) {
            String entrada = leerTexto(mensaje);
            if (!entrada.isEmpty()) {
                return entrada;
            }
            System.out.println("Entrada invalida: este dato no puede estar vacio.");
        }
    }

    private static String leerPatenteNueva() {
        while (true) {
            String patente = leerTextoObligatorio("Patente: ");
            if (gestor.buscarPorPatente(patente) == null) {
                return patente;
            }
            System.out.println("Ya existe un vehiculo con la patente " + patente.toUpperCase() + ". Ingrese otra.");
        }
    }

    private static String leerTipoMotor() {
        while (true) {
            String motor = leerTexto("Tipo de motor (NAFTA, DIESEL o ELECTRICO): ");
            if (LanchaTuristica.esTipoMotorValido(motor)) {
                return motor;
            }
            System.out.println("Tipo de motor invalido. Debe ser NAFTA, DIESEL o ELECTRICO.");
        }
    }

    private static boolean leerBooleano(String mensaje) {
        while (true) {
            String entrada = leerTexto(mensaje + " (s/n): ").toLowerCase();
            if (entrada.equals("s") || entrada.equals("si")) {
                return true;
            }
            if (entrada.equals("n") || entrada.equals("no")) {
                return false;
            }
            System.out.println("Entrada invalida: responda s o n.");
        }
    }

    private static void altaVehiculo() {
        System.out.println("Tipo de vehiculo: 1. Colectivo  2. Van  3. Lancha");
        int tipo = leerEnteroEnRango("Tipo: ", 1, 3);
        String patente = leerPatenteNueva();
        String marca = leerTextoObligatorio("Marca: ");
        int capacidad = leerEnteroMinimo("Capacidad de pasajeros: ", 1);
        int anioActual = Calendar.getInstance().get(Calendar.YEAR);
        int anio = leerEnteroEnRango("Anio de fabricacion: ", Vehiculo.ANIO_MINIMO, anioActual);
        try {
            Vehiculo vehiculo;
            switch (tipo) {
                case 1:
                    int pisos = leerEnteroEnRango("Cantidad de pisos (1 o 2): ", 1, 2);
                    vehiculo = new ColectivoTuristico(patente, marca, capacidad, anio, pisos);
                    break;
                case 2:
                    boolean aire = leerBooleano("Tiene aire acondicionado?");
                    vehiculo = new VanEjecutiva(patente, marca, capacidad, anio, aire);
                    break;
                default:
                    String motor = leerTipoMotor();
                    vehiculo = new LanchaTuristica(patente, marca, capacidad, anio, motor);
                    break;
            }
            gestor.agregarVehiculo(vehiculo);
            System.out.println("Vehiculo agregado correctamente.");
        } catch (VehiculoException e) {
            System.out.println("No se pudo agregar el vehiculo: " + e.getMessage());
        }
    }

    private static void buscarVehiculo() {
        String patente = leerTexto("Patente a buscar: ");
        if (patente.isEmpty()) {
            System.out.println("Debe ingresar una patente.");
            return;
        }
        Vehiculo vehiculo = gestor.buscarPorPatente(patente);
        if (vehiculo == null) {
            System.out.println("No se encontro ningun vehiculo con la patente " + patente + ".");
        } else {
            System.out.println(vehiculo);
        }
    }
}
