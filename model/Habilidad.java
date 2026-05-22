package model;

import battle.Tipo;

public interface Habilidad {
    String getNombre();
    Tipo getTipo();
    int getPoderBase();
    void aplicarEfectoEspecial(Pokemon atacante, Pokemon defensor);
}
