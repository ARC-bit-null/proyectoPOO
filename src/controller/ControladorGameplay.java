package src.controller;

import src.model.Partida;
import src.view.VentanaEquipo;
import src.view.VentanaGameplay;
import src.view.VentanaPve;
import src.view.VentanaPvp;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ControladorGameplay {

    private VentanaGameplay ventana;
    private Partida partida;

    public ControladorGameplay(VentanaGameplay ventana, Partida partida) {
        this.ventana = ventana;
        this.partida = partida;

        inicializarEventos();
    }

    // Metodo para conectar todos los eventos de la ventana gameplay
    private void inicializarEventos() {
        inicializarEventoEquipo();
        inicializarEventoPve();
        inicializarEventoPvp();
    }

    private void inicializarEventoEquipo() {
        ventana.getPanelEquipo().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                ventana.resaltarPanel(ventana.getPanelEquipo());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ventana.restaurarPanel(ventana.getPanelEquipo());
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                ventana.setVisible(false);
                VentanaEquipo ventanaEquipo = new VentanaEquipo(ventana, partida);
                new ControladorEquipo(ventanaEquipo);
            }
        });
    }

    private void inicializarEventoPve() {
        ventana.getPanelPve().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                ventana.resaltarPanel(ventana.getPanelPve());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ventana.restaurarPanel(ventana.getPanelPve());
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                ventana.setVisible(false);
                VentanaPve ventanaPve = new VentanaPve(ventana, partida);
                new ControladorPve(ventanaPve, partida);
            }
        });
    }

    private void inicializarEventoPvp() {
        ventana.getPanelPvp().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                ventana.resaltarPanel(ventana.getPanelPvp());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ventana.restaurarPanel(ventana.getPanelPvp());
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                ventana.setVisible(false);
                VentanaPvp ventanaPvp = new VentanaPvp(ventana, partida);
                new ControladorPvp(ventanaPvp);
            }
        });
    }
}