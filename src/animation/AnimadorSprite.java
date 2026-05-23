package src.animation;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

// Esta clase tiene helpers sencillos para mover labels con timers
public class AnimadorSprite {

    // Metodo para mover un label poco a poco hasta una posicion
    public static void animarMovimiento(JLabel label, Point destino, int duracionMs, Runnable alFinal) {
        Point origen = label.getLocation();

        int intervalo = 15;
        int pasos = Math.max(1, duracionMs / intervalo);

        double deltaX = (destino.x - origen.x) / (double) pasos;
        double deltaY = (destino.y - origen.y) / (double) pasos;

        final int[] pasoActual = {0};

        Timer timer = new Timer(intervalo, null);
        timer.addActionListener(e -> {
            pasoActual[0]++;

            int nuevoX = (int) Math.round(origen.x + (deltaX * pasoActual[0]));
            int nuevoY = (int) Math.round(origen.y + (deltaY * pasoActual[0]));

            label.setLocation(nuevoX, nuevoY);
            label.repaint();

            if (pasoActual[0] >= pasos) {
                timer.stop();
                label.setLocation(destino);

                if (alFinal != null) {
                    alFinal.run();
                }
            }
        });

        timer.start();
    }

    // Metodo para hacer ida y vuelta de un sprite
    public static void animarIdaYVuelta(JLabel label, Point puntoIntermedio, int duracionIda, int duracionVuelta, Runnable alFinal) {
        Point origen = label.getLocation();

        animarMovimiento(label, puntoIntermedio, duracionIda, () ->
                animarMovimiento(label, origen, duracionVuelta, alFinal)
        );
    }

    // Metodo para hacer una sacudida simple
    public static void animarSacudida(JLabel label, int distancia, int repeticiones, Runnable alFinal) {
        Point origen = label.getLocation();

        final int[] paso = {0};
        Timer timer = new Timer(45, null);

        timer.addActionListener(e -> {
            if (paso[0] >= repeticiones * 2) {
                timer.stop();
                label.setLocation(origen);

                if (alFinal != null) {
                    alFinal.run();
                }
                return;
            }

            int direccion = (paso[0] % 2 == 0) ? 1 : -1;
            label.setLocation(origen.x + (direccion * distancia), origen.y);
            paso[0]++;
        });

        timer.start();
    }

    // Metodo para hacer caer un sprite cuando es derrotado
    public static void animarCaida(JLabel label, int distanciaY, int duracionMs, Runnable alFinal) {
        Point origen = label.getLocation();
        Point destino = new Point(origen.x, origen.y + distanciaY);

        animarMovimiento(label, destino, duracionMs, alFinal);
    }

    // Metodo para hacer aparecer un sprite desde un punto inicial
    public static void animarEntrada(JLabel label, Point origen, Point destino, int duracionMs, Runnable alFinal) {
        label.setLocation(origen);
        label.setVisible(true);

        animarMovimiento(label, destino, duracionMs, alFinal);
    }
}