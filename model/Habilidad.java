package model;

import battle.SistemaTipos;

public interface Habilidad {
    String getNombre();
    SistemaTipos getTipo();
    int getPoderBase();
    void aplicarEfectoEspecial(Pokemon atacante, Pokemon defensor);
}
