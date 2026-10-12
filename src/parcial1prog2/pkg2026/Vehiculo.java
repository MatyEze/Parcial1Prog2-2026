package parcial1prog2.pkg2026;

import java.util.Calendar;

public abstract class Vehiculo {

    private static final int ANIO_MINIMO = 1990;

    private String patente;
    private String marca;
    private int capacidadPasajeros;
    private int anioFabricacion;

    public Vehiculo(String patente, String marca, int capacidadPasajeros, int anioFabricacion) throws VehiculoException {
        this.patente = validarPatente(patente);
        this.marca = marca == null ? "" : marca.trim();
        this.capacidadPasajeros = validarCapacidad(capacidadPasajeros);
        this.anioFabricacion = validarAnio(anioFabricacion);
    }

    public String getPatente() {
        return patente;
    }

    public String getMarca() {
        return marca;
    }

    public int getCapacidadPasajeros() {
        return capacidadPasajeros;
    }

    public int getAnioFabricacion() {
        return anioFabricacion;
    }

    public void setCapacidadPasajeros(int capacidadPasajeros) throws VehiculoException {
        this.capacidadPasajeros = validarCapacidad(capacidadPasajeros);
    }

    private String validarPatente(String patente) throws VehiculoException {
        if (patente == null || patente.trim().isEmpty()) {
            throw new VehiculoException("La patente no puede estar vacia.");
        }
        return patente.trim().toUpperCase();
    }

    private int validarCapacidad(int capacidad) throws VehiculoException {
        if (capacidad <= 0) {
            throw new VehiculoException("La capacidad de pasajeros debe ser mayor a cero.");
        }
        return capacidad;
    }

    private int validarAnio(int anio) throws VehiculoException {
        int anioActual = Calendar.getInstance().get(Calendar.YEAR);
        if (anio < ANIO_MINIMO) {
            throw new VehiculoException("El anio de fabricacion no puede ser menor a " + ANIO_MINIMO + ".");
        }
        if (anio > anioActual) {
            throw new VehiculoException("El anio de fabricacion no puede ser mayor a " + anioActual + ".");
        }
        return anio;
    }

    public abstract void realizarServicio();

    public abstract String getTipo();

    @Override
    public String toString() {
        return getTipo() 
                + " | Patente: " + patente
                + " | Marca: " + marca
                + " | Capacidad: " + capacidadPasajeros
                + " | Anio: " + anioFabricacion;
    }
}
