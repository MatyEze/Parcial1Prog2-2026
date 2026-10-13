package parcial1prog2.pkg2026;

public class LanchaTuristica extends Vehiculo {

    private String tipoMotor;

    public LanchaTuristica(String patente, String marca, int capacidadPasajeros, int anioFabricacion, String tipoMotor) throws VehiculoException {
        super(patente, marca, capacidadPasajeros, anioFabricacion);
        this.tipoMotor = validarTipoMotor(tipoMotor);
    }

    public String getTipoMotor() {
        return tipoMotor;
    }

    public static boolean esTipoMotorValido(String tipoMotor) {
        if (tipoMotor == null) {
            return false;
        }
        String normalizado = tipoMotor.trim().toUpperCase();
        return normalizado.equals("NAFTA") || normalizado.equals("DIESEL") || normalizado.equals("ELECTRICO");
    }

    private String validarTipoMotor(String tipoMotor) throws VehiculoException {
        if (tipoMotor == null || tipoMotor.trim().isEmpty()) {
            throw new VehiculoException("El tipo de motor es obligatorio.");
        }
        if (!esTipoMotorValido(tipoMotor)) {
            throw new VehiculoException("Tipo de motor invalido: " + tipoMotor + ". Debe ser NAFTA, DIESEL o ELECTRICO.");
        }
        return tipoMotor.trim().toUpperCase();
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
