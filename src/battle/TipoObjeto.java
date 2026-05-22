package src.battle;

// Usamos enum porque los objetos de combate son valores fijos del sistema
public enum TipoObjeto {

    SPRAY_CURATIVO("Spray curativo"),
    REVIVIR("Revivir"),
    BANDA_ESPECIAL("Banda especial"),
    X_SPEED("X Speed");

    // Guardamos el nombre bonito del objeto para mostrarlo en pantalla
    private String nombreMostrado;

    TipoObjeto(String nombreMostrado) {
        this.nombreMostrado = nombreMostrado;
    }
}