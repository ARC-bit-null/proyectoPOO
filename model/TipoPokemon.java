package model;

// Usamos enum porque los tipos pokemon son valores fijos y no se deben crear libremente
public enum TipoPokemon {

    AGUA("Agua"),
    FUEGO("Fuego"),
    PLANTA("Planta"),
    TIERRA("Tierra"),
    ELECTRICO("Eléctrico"),
    NORMAL("Normal");

    // Variable donde guardamos el nombre bonito del tipo para mostrarlo en pantalla
    private String nombreMostrado;

    // Constructor privado del enum para asignar el nombre visual de cada tipo
    TipoPokemon(String nombreMostrado) {
        this.nombreMostrado = nombreMostrado;
    }

    // Metodo para obtener el nombre visual del tipo
    public String getNombreMostrado() {
        return nombreMostrado;
    }

    // Sobrescribimos toString para que al mostrar el tipo salga el nombre bonito
    @Override
    public String toString() {
        return nombreMostrado;
    }
}