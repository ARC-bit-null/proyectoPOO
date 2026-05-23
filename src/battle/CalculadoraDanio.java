package src.battle;

import src.model.Habilidad;
import src.model.Pokemon;

public class CalculadoraDanio {

    public static int calcular(Pokemon atacante, Pokemon defensor, Habilidad habilidad) {
        // Sacamos la efectividad del tipo del ataque
        double modificador = SistemaTipos.obtenerEfectividad(habilidad.getTipo(), defensor.getTipo());

        // Evitamos que la defensa sea 0
        int defensa = Math.max(1, defensor.getDefensa());

        // Usamos el daño efectivo del atacante para que cuenten los buffs temporales
        int danoAtacante = atacante.getDanoEfectivo();

        // Formula base del daño
        double parteNivel = ((2.0 * atacante.getNivel()) / 5.0) + 2.0;
        double parteBase = (parteNivel * habilidad.getPoderBase() * ((double) danoAtacante / defensa)) / 50.0;
        double danioCalculado = (parteBase + 2.0) * modificador;

        // El daño minimo siempre será 1
        return Math.max(1, (int) danioCalculado);
    }
}