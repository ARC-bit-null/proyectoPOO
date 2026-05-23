package src.model;

import java.io.Serializable;

public interface Habilidad extends Serializable {
    String getNombre();
    TipoPokemon getTipo();
    int getPoderBase();
    void aplicarEfectoEspecial(Pokemon atacante, Pokemon defensor);
}

