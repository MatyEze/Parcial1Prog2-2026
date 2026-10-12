package parcial1prog2.pkg2026;

import java.util.Scanner;

public class Parcial1Prog22026 {

    private static GestorVehiculos gestor = new GestorVehiculos();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion = 0;
        while (opcion != 8) {
            mostrarMenu();
            opcion = leerEntero("Opcion: ");
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
                    gestor.mostrarConCapacidadMayorA(leerEntero("Mostrar vehiculos con capacidad mayor a: "));
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

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
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
        int tipo = leerEntero("Tipo: ");
        if (tipo < 1 || tipo > 3) {
            System.out.println("Tipo invalido. Ingrese 1, 2 o 3.");
            return;
        }
        String patente = leerTexto("Patente: ");
        String marca = leerTexto("Marca: ");
        int capacidad = leerEntero("Capacidad de pasajeros: ");
        int anio = leerEntero("Anio de fabricacion: ");
        try {
            Vehiculo vehiculo;
            switch (tipo) {
                case 1:
                    int pisos = leerEntero("Cantidad de pisos (1 o 2): ");
                    vehiculo = new ColectivoTuristico(patente, marca, capacidad, anio, pisos);
                    break;
                case 2:
                    boolean aire = leerBooleano("Tiene aire acondicionado?");
                    vehiculo = new VanEjecutiva(patente, marca, capacidad, anio, aire);
                    break;
                default:
                    String motor = leerTexto("Tipo de motor (NAFTA, DIESEL o ELECTRICO): ");
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
