package src.controller;

import src.model.Partida;
import src.persistance.DataManager;
import src.view.VentanaGameplay;
import src.view.VentanaInfoPartida;
import src.view.VentanaPartidas;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ControladorPartidas {

    private VentanaPartidas ventana;

    public ControladorPartidas(VentanaPartidas ventana) {
        this.ventana = ventana;
        inicializarEventos();
    }

    private void inicializarEventos() {
        ventana.getSlot1().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                abrirSlot(1);
            }
        });

        ventana.getSlot2().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                abrirSlot(2);
            }
        });

        ventana.getSlot3().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                abrirSlot(3);
            }
        });
    }

    // Método para abrir la ventana dependiendo si es que hay una partida guardada o no
    private void abrirSlot(int slot) {
        ventana.setVisible(false);

        try {
            if (DataManager.existePartida(slot)) {
                Partida partida = DataManager.cargarPartida(slot);

                if (partida == null) {
                    ventana.mostrarDialogo("No se pudo cargar la partida del slot " + slot + ".");
                    ventana.setVisible(true);
                    return;
                }

                VentanaGameplay ventanaGameplay = new VentanaGameplay(ventana, partida);
                new ControladorGameplay(ventanaGameplay, partida);
            } else {
                VentanaInfoPartida ventanaInfoPartida = new VentanaInfoPartida(ventana, slot);
                new ControladorInfoPartida(ventanaInfoPartida, ventana);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ventana.mostrarDialogo("Ocurrió un error al abrir el slot.");
            ventana.setVisible(true);
        }
    }
}