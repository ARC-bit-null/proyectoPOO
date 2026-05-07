package Ui;

import Model.Partida;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaGameplay extends JFrame {

    // Variable donde guardaremos la partida cargada o recién creada
    private Partida partida;

    public VentanaGameplay(VentanaPartidas ventanaPartidas, Partida partida) {
        // Guardamos la partida para usarla después en la ventana de gameplay
        this.partida = partida;

        setTitle("Gameplay");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                ventanaPartidas.setVisible(true);
            }
        });

        setVisible(true);
    }
}