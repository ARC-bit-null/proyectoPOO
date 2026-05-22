package src.battle;

import src.model.Jugador;

// Esta clase representa cualquier acción que un jugador pueda realizar en combate
public abstract class AccionCombate {

    // Guardamos el jugador que realiza la acción
    protected Jugador jugador;

    public AccionCombate(Jugador jugadorHumano) {
        this.jugador = jugadorHumano;
    }

    // Metodo para obtener el jugador que realiza la acción
    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugadorHumano) {
        this.jugador = jugadorHumano;
    }

    // Metodo abstracto para obtener el nombre o tipo de la acción
    public abstract String getNombreAccion();

    // Metodo para saber si esta acción tiene prioridad sobre un ataque normal
    public abstract boolean esAccionPrioritaria();
}