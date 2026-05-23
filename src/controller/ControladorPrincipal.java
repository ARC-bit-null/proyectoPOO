package src.controller;

import src.view.VentanaPartidas;
import src.view.VentanaPokedex;
import src.view.VentanaPrincipal;

public class ControladorPrincipal {

    private VentanaPrincipal ventana;

    public ControladorPrincipal(VentanaPrincipal ventana) {
        this.ventana = ventana;
        inicializarEventos();
    }

    private void inicializarEventos() {
        ventana.getBtnPeleas().addActionListener(e -> abrirVentanaPartidas());
        ventana.getBtnPokedex().addActionListener(e -> abrirVentanaPokedex());
    }

    private void abrirVentanaPartidas() {
        ventana.setVisible(false);
        VentanaPartidas ventanaPartidas = new VentanaPartidas(ventana);
        new ControladorPartidas(ventanaPartidas);
    }

    private void abrirVentanaPokedex() {
        ventana.setVisible(false);
        VentanaPokedex ventanaPokedex = new VentanaPokedex(ventana);
        new ControladorPokedex(ventanaPokedex);
    }
}
