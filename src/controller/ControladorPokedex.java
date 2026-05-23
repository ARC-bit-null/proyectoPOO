package src.controller;

import src.view.VentanaPokedex;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ControladorPokedex {

    private VentanaPokedex ventana;

    public ControladorPokedex(VentanaPokedex ventana) {
        this.ventana = ventana;
        inicializarEventos();
    }

    private void inicializarEventos() {
        ventana.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                if (ventana.getVentanaAnterior() != null) {
                    ventana.getVentanaAnterior().setVisible(true);
                }
            }
        });
    }
}