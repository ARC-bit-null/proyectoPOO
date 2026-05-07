package Model;

import java.util.ArrayList;

public class Pokedex {

    public static Pokemon crearPokemon(String nombre, int nivel) {
        switch (nombre.toLowerCase()) {
            // Línea de Bulbasaur
            case "bulbasaur": return new Pokemon(1, "Bulbasaur", "Planta", 45,10, 0, 16, "Ivysaur");
            case "ivysaur":   return new Pokemon(2, "Ivysaur", "Planta", 60,10, 16, 32, "Venusaur");
            case "venusaur":  return new Pokemon(3, "Venusaur", "Planta", 80,10, 32, 0, "Ninguna");

            // Línea de Charmander
            case "charmander": return new Pokemon(4, "Charmander", "Fuego", 39,10, 0, 16, "Charmeleon");
            case "charmeleon": return new Pokemon(5, "Charmeleon", "Fuego", 58,10, 16, 36, "Charizard");
            case "charizard":  return new Pokemon(6, "Charizard", "Fuego", 78,10, 36, 0, "Ninguna");

            // Línea de Squirtle
            case "squirtle":   return new Pokemon(7, "Squirtle", "Agua", 44,10, 0, 16, "Wartortle");
            case "wartortle":  return new Pokemon(8, "Wartortle", "Agua", 59,10, 16, 36, "Blastoise");
            case "blastoise":  return new Pokemon(9, "Blastoise", "Agua", 79,10, 36, 0, "Ninguna");

            // Línea de Pikachu
            case "pichu":      return new Pokemon(10, "Pichu", "Electrico", 20,10, 0, 10, "Pikachu");
            case "pikachu":    return new Pokemon(11, "Pikachu", "Electrico", 35,10, 10, 20, "Raichu");
            case "raichu":     return new Pokemon(12, "Raichu", "Electrico", 60,10, 20, 0, "Ninguna");

            default: return new Pokemon(0, "MissingNo", "Normal", 10, 1, 10, 0, "Ninguna");
        }
    }

    // Metodo para traer a todos los pokemones que tenemos actualmente y mostrarlos en la ventana de Pokedex
    public static ArrayList<Pokemon> obtenerTodosLosPokemon() {
        ArrayList<Pokemon> listaPokemon = new ArrayList<>();

        listaPokemon.add(new Pokemon(1, "Bulbasaur", "Planta", 45,10, 1, 16, "Ivysaur"));
        listaPokemon.add(new Pokemon(2, "Ivysaur", "Planta", 60,10, 1, 32, "Venusaur"));
        listaPokemon.add(new Pokemon(3, "Venusaur", "Planta", 80,10, 1, 0, "Ninguna"));

        listaPokemon.add(new Pokemon(4, "Charmander", "Fuego", 39,10, 1, 16, "Charmeleon"));
        listaPokemon.add(new Pokemon(5, "Charmeleon", "Fuego", 58,10, 1, 36, "Charizard"));
        listaPokemon.add(new Pokemon(6, "Charizard", "Fuego", 78,10, 1, 0, "Ninguna"));

        listaPokemon.add(new Pokemon(7, "Squirtle", "Agua", 44,10, 1, 16, "Wartortle"));
        listaPokemon.add(new Pokemon(8, "Wartortle", "Agua", 59,10, 1, 36, "Blastoise"));
        listaPokemon.add(new Pokemon(9, "Blastoise", "Agua", 79,10, 1, 0, "Ninguna"));

        listaPokemon.add(new Pokemon(10, "Pichu", "Electrico", 20,10, 1, 10, "Pikachu"));
        listaPokemon.add(new Pokemon(11, "Pikachu", "Electrico", 35,10, 1, 20, "Raichu"));
        listaPokemon.add(new Pokemon(12, "Raichu", "Electrico", 60,10, 1, 0, "Ninguna"));

        return listaPokemon;
    }
}