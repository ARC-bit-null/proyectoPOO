package logic;

import java.util.ArrayList;
import java.util.List;

public class Pokedex {
    public static Pokemon crearPokemon(String nombre, int nivel) {
        switch (nombre.toLowerCase()) {
            // Línea de Bulbasaur
            case "bulbasaur": return new Pokemon("Bulbasaur", "Planta", 45, nivel, 16, "Ivysaur");
            case "ivysaur":   return new Pokemon("Ivysaur", "Planta", 60, nivel, 32, "Venusaur");
            case "venusaur":  return new Pokemon("Venusaur", "Planta", 80, nivel, 0, "Ninguna");

            // Línea de Charmander
            case "charmander": return new Pokemon("Charmander", "Fuego", 39, nivel, 16, "Charmeleon");
            case "charmeleon": return new Pokemon("Charmeleon", "Fuego", 58, nivel, 36, "Charizard");
            case "charizard":  return new Pokemon("Charizard", "Fuego", 78, nivel, 0, "Ninguna");

            // Línea de Squirtle
            case "squirtle": return new Pokemon("Squirtle", "Agua", 44, nivel, 16, "Wartortle");
            case "wartortle": return new Pokemon("Wartortle", "Agua", 59, nivel, 36, "Blastoise");
            case "blastoise": return new Pokemon("Blastoise", "Agua", 79, nivel, 0, "Ninguna");

            // Línea de Pikachu (Ejemplo)
            case "pichu":   return new Pokemon("Pichu", "Electrico", 20, nivel, 10, "Pikachu");
            case "pikachu": return new Pokemon("Pikachu", "Electrico", 35, nivel, 20, "Raichu");
            case "raichu":  return new Pokemon("Raichu", "Electrico", 60, nivel, 0, "Ninguna");

            // Añadiremos más hasta completar los 20, hice una lista sencilla por ahora
            default: return new Pokemon("MissingNo", "Normal", 10, 1, 0, "Ninguna");
        }
    }
}
