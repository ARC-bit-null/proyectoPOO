import Model.DataManager;
import Model.Pokedex;
import Model.Pokemon;
import Ui.VentanaPrincipal;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipal());

        /*
        1. Intentar cargar partida
        Pokemon miPokemon = DataManager.cargarPartida();

        if (miPokemon == null) {
            System.out.println("Creando nueva partida...");
            miPokemon = Pokedex.crearPokemon("charmander", 15);
        } else {
            System.out.println("Cargado: " + miPokemon.getNombre() + " Nivel: " + miPokemon.getNivel());
        }

        // 2. Simular subida de nivel
        miPokemon.subirNivel();
        System.out.println("¡Subiste al nivel " + miPokemon.getNivel() + "!");

        // 3. Revisar evolución
        if (miPokemon.puedeEvolucionar()) {
            System.out.println("Evolucionando a " + miPokemon.getNombreEvolucion());
            miPokemon = Pokedex.crearPokemon(miPokemon.getNombreEvolucion(), miPokemon.getNivel());
        }

        // 4. Guardar progreso
        DataManager.guardarPartida(miPokemon);
        System.out.println("Progreso guardado.");
         */
    }
}
