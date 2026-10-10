package parcial1prog2.pkg2026;

public class VanEjecutiva extends Vehiculo {

    private boolean aireAcondicionado;

    public VanEjecutiva(String patente, String marca, int capacidadPasajeros, int anioFabricacion, boolean aireAcondicionado) {
        super(patente, marca, capacidadPasajeros, anioFabricacion);
        this.aireAcondicionado = aireAcondicionado;
    }

    public boolean isAireAcondicionado() {
        return aireAcondicionado;
    }

    @Override
    public void realizarServicio() {
        String confort = aireAcondicionado ? "con" : "sin";
        System.out.println("La van " + getPatente() + " realiza un traslado ejecutivo " + confort
                + " aire acondicionado para " + getCapacidadPasajeros() + " pasajeros.");
    }

    @Override
    public String getTipo() {
        return "Van";
    }

    @Override
    public String toString() {
        return super.toString() + " | Aire: " + (aireAcondicionado ? "Si" : "No");
    }
}
