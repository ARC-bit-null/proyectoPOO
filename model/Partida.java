package model;

import java.io.Serializable;

// Implementamos Serializable para poder guardar la partida en archivo
public class Partida implements Serializable {

    // Atributos principales de la partida
    private int slot;
    private Jugador jugador;

    public Partida(int slot, Jugador jugador) {
        this.slot = slot;
        this.jugador = jugador;
    }

    // Getter y Setter del slot de la partida
    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    // Getter y Setter del jugador de la partida
    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
}