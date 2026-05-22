package src.model;

// Esta clase representa el inventario temporal de objetos que un jugador puede usar en una batalla
public class InventarioCombate {

    // Objetos disponibles durante el combate
    private int sprayCurativo;
    private int revivir;
    private int bandaEspecial;
    private int xSpeed;

    public InventarioCombate() {
        // Inicializamos las cantidades por defecto al comenzar una batalla
        this.sprayCurativo = 3;
        this.revivir = 3;
        this.bandaEspecial = 1;
        this.xSpeed = 1;
    }

    // Metodo para saber si todavía se puede usar Spray curativo
    public boolean puedeUsarSprayCurativo() {
        return sprayCurativo > 0;
    }

    // Metodo para saber si todavía se puede usar Revivir
    public boolean puedeUsarRevivir() {
        return revivir > 0;
    }

    // Metodo para saber si todavía se puede usar Banda especial
    public boolean puedeUsarBandaEspecial() {
        return bandaEspecial > 0;
    }

    // Metodo para saber si todavía se puede usar X Speed
    public boolean puedeUsarXSpeed() {
        return xSpeed > 0;
    }

    // Metodo para consumir un Spray curativo si todavía hay disponibles
    public boolean usarSprayCurativo() {
        if (puedeUsarSprayCurativo()) {
            sprayCurativo--;
            return true;
        }
        return false;
    }

    // Metodo para consumir un Revivir si todavía hay disponibles
    public boolean usarRevivir() {
        if (puedeUsarRevivir()) {
            revivir--;
            return true;
        }
        return false;
    }

    // Metodo para consumir una Banda especial si todavía está disponible
    public boolean usarBandaEspecial() {
        if (puedeUsarBandaEspecial()) {
            bandaEspecial--;
            return true;
        }
        return false;
    }

    // Metodo para consumir un X Speed si todavía está disponible
    public boolean usarXSpeed() {
        if (puedeUsarXSpeed()) {
            xSpeed--;
            return true;
        }
        return false;
    }

    // Metodo para reiniciar el inventario a sus valores iniciales
    public void reiniciarInventario() {
        this.sprayCurativo = 3;
        this.revivir = 3;
        this.bandaEspecial = 1;
        this.xSpeed = 1;
    }

    // Getter y Setter de Spray curativo
    public int getSprayCurativo() {
        return sprayCurativo;
    }

    public void setSprayCurativo(int sprayCurativo) {
        this.sprayCurativo = sprayCurativo;
    }

    // Getter y Setter de Revivir
    public int getRevivir() {
        return revivir;
    }

    public void setRevivir(int revivir) {
        this.revivir = revivir;
    }

    // Getter y Setter de Banda especial
    public int getBandaEspecial() {
        return bandaEspecial;
    }

    public void setBandaEspecial(int bandaEspecial) {
        this.bandaEspecial = bandaEspecial;
    }

    // Getter y Setter de X Speed
    public int getXSpeed() {
        return xSpeed;
    }

    public void setXSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }
}