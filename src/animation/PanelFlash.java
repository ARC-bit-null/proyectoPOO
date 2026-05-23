package src.animation;

import javax.swing.*;
import java.awt.*;

// Este panel pinta un color semitransparente encima del sprite
public class PanelFlash extends JPanel {

    private Color colorFlash;

    public PanelFlash(Color colorFlash) {
        this.colorFlash = colorFlash;
        setOpaque(false);
        setVisible(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(colorFlash);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.dispose();
    }
}