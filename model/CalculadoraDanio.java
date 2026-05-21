package model;

public class CalculadoraDanio {

    public static int calcular(Pokemon atacante, Pokemon defensor, Habilidad habilidad) {
        // 1. Validamos la ventaja o desventaja de tipos
        double efectividad = habilidad.getTipo().obtenerEfectividadContra(defensor.getTipo());
        
        // 2. Sumamos la fuerza del atacante más la potencia propia del ataque
        int poderTotal = atacante.getDano() + habilidad.getPoderBase();
        
        // 3. Obtenemos la defensa del rival (asumiendo que agregaste getDefensa en Pokemon)
        int defensaRival = defensor.getDefensa();
        
        // 4. Aplicamos la fórmula: (Ataque - Defensa/2) multiplicado por la efectividad elemental
        int danioBase = (int) ((poderTotal - (defensaRival / 2)) * efectividad);
        
        // Regla de seguridad: Si el rival tiene demasiada defensa, el golpe no da valores
        // negativos ni se queda en cero. Al menos le quitará 5 puntos de HP.
        return Math.max(5, danioBase);
    }
}
