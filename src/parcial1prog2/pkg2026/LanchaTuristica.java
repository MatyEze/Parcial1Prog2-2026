package parcial1prog2.pkg2026;

public class LanchaTuristica extends Vehiculo {

    private TipoMotor tipoMotor;

    public LanchaTuristica(String patente, String marca, int capacidadPasajeros, int anioFabricacion, TipoMotor tipoMotor) {
        super(patente, marca, capacidadPasajeros, anioFabricacion);
        this.tipoMotor = tipoMotor;
    }

    public TipoMotor getTipoMotor() {
        return tipoMotor;
    }

    @Override
    public void realizarServicio() {
        System.out.println("La lancha " + getPatente() + " realiza un paseo nautico con motor "
                + tipoMotor + " para " + getCapacidadPasajeros() + " pasajeros.");
    }

    @Override
    public String getTipo() {
        return "Lancha";
    }

    @Override
    public String toString() {
        return super.toString() + " | Motor: " + tipoMotor;
    }
}
