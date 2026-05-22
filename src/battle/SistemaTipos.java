package src.battle;

import src.model.TipoPokemon;

// Esta clase se encarga de centralizar la lógica de efectividad entre tipos
public class SistemaTipos {

    // Metodo estatico para obtener el multiplicador de efectividad
    public static double obtenerEfectividad(TipoPokemon atacante, TipoPokemon defensor) {

        // Ataques de FUEGO
        if (atacante == TipoPokemon.FUEGO) {
            if (defensor == TipoPokemon.PLANTA) return 2.0;
            if (defensor == TipoPokemon.AGUA || defensor == TipoPokemon.FUEGO || defensor == TipoPokemon.TIERRA) return 0.5;
        }

        // Ataques de AGUA
        if (atacante == TipoPokemon.AGUA) {
            if (defensor == TipoPokemon.FUEGO || defensor == TipoPokemon.TIERRA) return 2.0;
            if (defensor == TipoPokemon.PLANTA || defensor == TipoPokemon.AGUA || defensor == TipoPokemon.ELECTRICO) return 0.5;
        }

        // Ataques de PLANTA
        if (atacante == TipoPokemon.PLANTA) {
            if (defensor == TipoPokemon.AGUA || defensor == TipoPokemon.TIERRA) return 2.0;
            if (defensor == TipoPokemon.FUEGO || defensor == TipoPokemon.PLANTA) return 0.5;
        }

        // Ataques de ELECTRICO
        if (atacante == TipoPokemon.ELECTRICO) {
            if (defensor == TipoPokemon.AGUA) return 2.0;
            if (defensor == TipoPokemon.PLANTA || defensor == TipoPokemon.ELECTRICO || defensor == TipoPokemon.TIERRA) return 0.5;
        }

        // Ataques de TIERRA
        if (atacante == TipoPokemon.TIERRA) {
            if (defensor == TipoPokemon.FUEGO || defensor == TipoPokemon.ELECTRICO) return 2.0;
            if (defensor == TipoPokemon.PLANTA || defensor == TipoPokemon.AGUA) return 0.5;
        }

        // Tipo NORMAL no tiene ventajas ni desventajas especiales
        if (atacante == TipoPokemon.NORMAL) {
            return 1.0;
        }

        // Si no hay relación especial, el daño es normal
        return 1.0;
    }
}