package src.animation;

import javax.swing.*;
import java.awt.*;

// Esta clase junta las animaciones simples del combate
public class AnimadorCombate {

    private final JLabel spriteJugador1;
    private final JLabel spriteJugador2;

    private final JComponent flashJugador1;
    private final JComponent flashJugador2;

    private Point posicionBaseJ1;
    private Point posicionBaseJ2;

    public AnimadorCombate(JLabel spriteJugador1, JLabel spriteJugador2, JComponent flashJugador1, JComponent flashJugador2) {
        this.spriteJugador1 = spriteJugador1;
        this.spriteJugador2 = spriteJugador2;
        this.flashJugador1 = flashJugador1;
        this.flashJugador2 = flashJugador2;
    }

    // Metodo para guardar las posiciones base
    public void guardarPosicionesBase() {
        this.posicionBaseJ1 = spriteJugador1.getLocation();
        this.posicionBaseJ2 = spriteJugador2.getLocation();
    }

    public void animarEntradaJugador1(Runnable alFinal) {
        if (posicionBaseJ1 == null) {
            if (alFinal != null) alFinal.run();
            return;
        }

        Point origen = new Point(posicionBaseJ1.x - 120, posicionBaseJ1.y);
        AnimadorSprite.animarEntrada(spriteJugador1, origen, posicionBaseJ1, 260, alFinal);
    }

    public void animarEntradaJugador2(Runnable alFinal) {
        if (posicionBaseJ2 == null) {
            if (alFinal != null) alFinal.run();
            return;
        }

        Point origen = new Point(posicionBaseJ2.x + 120, posicionBaseJ2.y);
        AnimadorSprite.animarEntrada(spriteJugador2, origen, posicionBaseJ2, 260, alFinal);
    }

    public void animarAtaqueJugador1(Runnable alFinal) {
        if (posicionBaseJ1 == null) {
            if (alFinal != null) alFinal.run();
            return;
        }

        Point puntoAtaque = new Point(posicionBaseJ1.x + 35, posicionBaseJ1.y);
        AnimadorSprite.animarIdaYVuelta(spriteJugador1, puntoAtaque, 120, 120, alFinal);
    }

    public void animarAtaqueJugador2(Runnable alFinal) {
        if (posicionBaseJ2 == null) {
            if (alFinal != null) alFinal.run();
            return;
        }

        Point puntoAtaque = new Point(posicionBaseJ2.x - 35, posicionBaseJ2.y);
        AnimadorSprite.animarIdaYVuelta(spriteJugador2, puntoAtaque, 120, 120, alFinal);
    }

    // Metodo para hacer flash rojo y luego sacudir
    public void animarDanioJugador1(Runnable alFinal) {
        animarFlash(flashJugador1, () -> AnimadorSprite.animarSacudida(spriteJugador1, 12, 4, alFinal));
    }

    // Metodo para hacer flash rojo y luego sacudir
    public void animarDanioJugador2(Runnable alFinal) {
        animarFlash(flashJugador2, () -> AnimadorSprite.animarSacudida(spriteJugador2, 12, 4, alFinal));
    }

    public void animarDerrotaJugador1(Runnable alFinal) {
        AnimadorSprite.animarCaida(spriteJugador1, 70, 220, () -> {
            spriteJugador1.setVisible(false);
            if (alFinal != null) {
                alFinal.run();
            }
        });
    }

    public void animarDerrotaJugador2(Runnable alFinal) {
        AnimadorSprite.animarCaida(spriteJugador2, 70, 220, () -> {
            spriteJugador2.setVisible(false);
            if (alFinal != null) {
                alFinal.run();
            }
        });
    }

    public void animarCambioJugador1(Runnable alFinal) {
        if (posicionBaseJ1 == null) {
            if (alFinal != null) alFinal.run();
            return;
        }

        Point salida = new Point(posicionBaseJ1.x - 120, posicionBaseJ1.y);

        AnimadorSprite.animarMovimiento(spriteJugador1, salida, 180, () -> {
            spriteJugador1.setVisible(false);
            spriteJugador1.setLocation(posicionBaseJ1);
            spriteJugador1.setVisible(true);
            animarEntradaJugador1(alFinal);
        });
    }

    public void animarCambioJugador2(Runnable alFinal) {
        if (posicionBaseJ2 == null) {
            if (alFinal != null) alFinal.run();
            return;
        }

        Point salida = new Point(posicionBaseJ2.x + 120, posicionBaseJ2.y);

        AnimadorSprite.animarMovimiento(spriteJugador2, salida, 180, () -> {
            spriteJugador2.setVisible(false);
            spriteJugador2.setLocation(posicionBaseJ2);
            spriteJugador2.setVisible(true);
            animarEntradaJugador2(alFinal);
        });
    }

    public void restaurarPosicionesBase() {
        if (posicionBaseJ1 != null) {
            spriteJugador1.setLocation(posicionBaseJ1);
        }

        if (posicionBaseJ2 != null) {
            spriteJugador2.setLocation(posicionBaseJ2);
        }

        flashJugador1.setVisible(false);
        flashJugador2.setVisible(false);
    }

    // Metodo para prender y apagar el flash rojo rapidito
    private void animarFlash(JComponent flash, Runnable alFinal) {
        flash.setVisible(true);
        flash.repaint();

        Timer timer = new Timer(90, null);
        timer.addActionListener(e -> {
            timer.stop();
            flash.setVisible(false);

            if (alFinal != null) {
                alFinal.run();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
}