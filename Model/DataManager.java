package Model;

import java.io.*;

public class DataManager {

    // Método para GUARDAR el Pokémon del jugador
    public static void guardarPartida(Pokemon miPokemon) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("progreso.dat"))) {
            oos.writeObject(miPokemon);
            System.out.println("Partida guardada con éxito.");
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    // Método para CARGAR el Pokémon guardado
    public static Pokemon cargarPartida() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("progreso.dat"))) {
            return (Pokemon) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No hay partida guardada, empieza una nueva.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar datos: " + e.getMessage());
        }
        return null; // Si no hay archivo o hay error, devuelve nulo
    }
}
