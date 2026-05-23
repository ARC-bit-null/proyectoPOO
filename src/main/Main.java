package src.main;

import src.controller.ControladorPrincipal;
import src.view.VentanaPrincipal;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
            new ControladorPrincipal(ventanaPrincipal);
        });
    }
}
