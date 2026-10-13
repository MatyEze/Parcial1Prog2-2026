package parcial1prog2.pkg2026;

import java.util.ArrayList;

public class GestorVehiculos {

    private ArrayList<Vehiculo> vehiculos;

    public GestorVehiculos() {
        this.vehiculos = new ArrayList<>();
    }

    public void agregarVehiculo(Vehiculo v) throws VehiculoException {
        if (existePatente(v.getPatente())) {
            throw new VehiculoException("Ya existe un vehiculo con la patente " + v.getPatente() + ".");
        }
        vehiculos.add(v);
    }

    public boolean hayVehiculos() {
        return !vehiculos.isEmpty();
    }

    public void mostrarTodos() {
        mostrarLista(vehiculos);
    }

    public void realizarServicios() {
        if (vehiculos.isEmpty()) {
            System.out.println("No hay vehiculos cargados.");
            return;
        }
        for (int i = 0; i < vehiculos.size(); i++) {
            vehiculos.get(i).realizarServicio();
        }
    }

    public Vehiculo buscarPorPatente(String patente) {
        for (int i = 0; i < vehiculos.size(); i++) {
            Vehiculo v = vehiculos.get(i);
            if (v.getPatente().equalsIgnoreCase(patente.trim())) {
                return v;
            }
        }
        return null;
    }

    public void mostrarConCapacidadMayorA(int valor) {
        ArrayList<Vehiculo> filtrados = new ArrayList<>();
        for (int i = 0; i < vehiculos.size(); i++) {
            Vehiculo v = vehiculos.get(i);
            if (v.getCapacidadPasajeros() > valor) {
                filtrados.add(v);
            }
        }
        if (filtrados.isEmpty()) {
            System.out.println("No hay vehiculos con capacidad mayor a " + valor + ".");
            return;
        }
        mostrarLista(filtrados);
    }

    public void mostrarOrdenadosPorAnioDesc() {
        ArrayList<Vehiculo> copia = copiarLista();
        for (int i = 0; i < copia.size() - 1; i++) {
            for (int j = 0; j < copia.size() - 1 - i; j++) {
                if (copia.get(j).getAnioFabricacion() < copia.get(j + 1).getAnioFabricacion()) {
                    intercambiar(copia, j, j + 1);
                }
            }
        }
        mostrarLista(copia);
    }

    public void mostrarOrdenadosPorCapacidadDesc() {
        ArrayList<Vehiculo> copia = copiarLista();
        for (int i = 0; i < copia.size() - 1; i++) {
            for (int j = 0; j < copia.size() - 1 - i; j++) {
                if (copia.get(j).getCapacidadPasajeros() < copia.get(j + 1).getCapacidadPasajeros()) {
                    intercambiar(copia, j, j + 1);
                }
            }
        }
        mostrarLista(copia);
    }

    private boolean existePatente(String patente) {
        return buscarPorPatente(patente) != null;
    }

    private ArrayList<Vehiculo> copiarLista() {
        ArrayList<Vehiculo> copia = new ArrayList<>();
        for (int i = 0; i < vehiculos.size(); i++) {
            copia.add(vehiculos.get(i));
        }
        return copia;
    }

    private void intercambiar(ArrayList<Vehiculo> lista, int i, int j) {
        Vehiculo aux = lista.get(i);
        lista.set(i, lista.get(j));
        lista.set(j, aux);
    }

    private void mostrarLista(ArrayList<Vehiculo> lista) {
        if (lista.isEmpty()) {
            System.out.println("No hay vehiculos cargados.");
            return;
        }
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i));
        }
    }
}
