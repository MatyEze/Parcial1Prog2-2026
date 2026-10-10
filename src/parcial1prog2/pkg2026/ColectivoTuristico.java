package parcial1prog2.pkg2026;

public class ColectivoTuristico extends Vehiculo {

    private int cantidadPisos;

    public ColectivoTuristico(String patente, String marca, int capacidadPasajeros, int anioFabricacion, int cantidadPisos) {
        super(patente, marca, capacidadPasajeros, anioFabricacion);
        this.cantidadPisos = cantidadPisos;
    }

    public int getCantidadPisos() {
        return cantidadPisos;
    }

    @Override
    public void realizarServicio() {
        System.out.println("El colectivo " + getPatente() + " realiza una excursion por la ciudad con "
                + getCapacidadPasajeros() + " pasajeros en " + cantidadPisos + " piso(s).");
    }

    @Override
    public String getTipo() {
        return "Colectivo";
    }

    @Override
    public String toString() {
        return super.toString() + " | Pisos: " + cantidadPisos;
    }
}
