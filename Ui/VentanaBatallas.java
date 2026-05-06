package Ui;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaBatallas extends JFrame {

    public VentanaBatallas(VentanaPrincipal ventanaPrincipal) {
        setTitle("Batallas Pokemon");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        // Metodo para al momento de cerrar la ventana abrir la principal
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                ventanaPrincipal.setVisible(true);
            }
        });

        setVisible(true);
    }
}