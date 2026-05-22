package src.battle;

import src.model.Habilidad;
import src.model.Pokemon;

public class CalculadoraDanio {

    public static int calcular(Pokemon atacante, Pokemon defensor, Habilidad habilidad) {
        // Obtenemos el modificador por ventaja o desventaja de tipos
        double modificador = SistemaTipos.obtenerEfectividad(habilidad.getTipo(), defensor.getTipo());

        // Evitamos división por cero por seguridad
        int defensa = Math.max(1, defensor.getDefensa());

        // Fórmula inspirada en la fórmula original de Pokémon
        double parteNivel = ((2.0 * atacante.getNivel()) / 5.0) + 2.0;
        double parteBase = (parteNivel * habilidad.getPoderBase() * ((double) atacante.getDano() / defensa)) / 50.0;
        double danioCalculado = (parteBase + 2.0) * modificador;

        // Daño mínimo garantizado
        return Math.max(1, (int) danioCalculado);
    }
}