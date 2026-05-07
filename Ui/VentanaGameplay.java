package Ui;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaGameplay extends JFrame {

    public VentanaGameplay(VentanaPartidas ventanaPartidas) {
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