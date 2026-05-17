package battle;

import model.Jugador;

// Esta clase representa cualquier acción que un jugador pueda realizar en combate
public abstract class AccionCombate {

    // Guardamos el jugador que realiza la acción
    protected Jugador jugador;

    public AccionCombate(Jugador jugador) {
        this.jugador = jugador;
    }

    // Metodo para obtener el jugador que realiza la acción
    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    // Metodo abstracto para obtener el nombre o tipo de la acción
    public abstract String getNombreAccion();

    // Metodo para saber si esta acción tiene prioridad sobre un ataque normal
    public abstract boolean esAccionPrioritaria();
}