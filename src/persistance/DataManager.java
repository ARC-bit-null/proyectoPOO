package src.persistance;

import src.model.Partida;

import java.io.*;

public class DataManager {

    // Método para guardar la información de la partida del jugador en un archivo
    public static void guardarPartida(Partida partida) {
        if (partida == null) {
            System.out.println("No se puede guardar una partida nula.");
            return;
        }

        String rutaArchivo = obtenerRutaArchivo(partida.getSlot());

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
            oos.writeObject(partida);
            System.out.println("Partida guardada con éxito en slot " + partida.getSlot());
        } catch (IOException e) {
            System.out.println("Error al guardar la partida: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para cargar la información de cada partida
    public static Partida cargarPartida(int slot) {
        String rutaArchivo = obtenerRutaArchivo(slot);

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            Partida partida = (Partida) ois.readObject();

            if (partida == null) {
                System.out.println("La partida cargada es nula para el slot " + slot);
            } else {
                System.out.println("Partida cargada correctamente desde el slot " + slot);
            }

            return partida;
        } catch (FileNotFoundException e) {
            System.out.println("No existe una partida guardada en el slot " + slot);
            e.printStackTrace();
        } catch (InvalidClassException e) {
            System.out.println("La partida guardada no es compatible con la versión actual de las clases.");
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar la partida del slot " + slot + ": " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    // Método para verificar si la partida ya esta creada, para evitar crear mas partidas de lo necesario
    public static boolean existePartida(int slot) {
        String rutaArchivo = obtenerRutaArchivo(slot);
        File archivo = new File(rutaArchivo);
        return archivo.exists();
    }

    // Método para borrar el archivo donde guardamos la información de la partida
    public static boolean eliminarPartida(int slot) {
        String rutaArchivo = obtenerRutaArchivo(slot);
        File archivo = new File(rutaArchivo);

        if (archivo.exists()) {
            return archivo.delete();
        }

        return false;
    }

    // Método para obtener la ruta del archivo correspondiente a un slot de partida
    private static String obtenerRutaArchivo(int slot) {
        String nombreCarpeta = "PartidasSaves";

        File carpeta = new File(nombreCarpeta);

        if (!carpeta.exists()) {
            carpeta.mkdir();
        }

        return nombreCarpeta + "/partida" + slot + ".dat";
    }
}