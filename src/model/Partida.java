package src.model;

import java.io.Serializable;

// Implementamos Serializable para poder guardar la partida en archivo
public class Partida implements Serializable {

    // Atributos principales de la partida
    private int slot;
    private JugadorHumano jugadorHumano;

    public Partida(int slot, JugadorHumano jugadorHumano) {
        this.slot = slot;
        this.jugadorHumano = jugadorHumano;
    }

    // Getter y Setter del slot de la partida
    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    // Getter y Setter del jugador de la partida
    public JugadorHumano getJugador() {
        return jugadorHumano;
    }

    public void setJugador(JugadorHumano jugadorHumano) {
        this.jugadorHumano = jugadorHumano;
    }
}