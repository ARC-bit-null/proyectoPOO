package model;

public enum Tipo {
    FUEGO, AGUA, PLANTA, ELECTRICO, NORMAL;
   
    public double obtenerEfectividadContra(Tipo defensor) {
        // Ataques de FUEGO
        if (this == FUEGO) {
            if (defensor == PLANTA) return 2.0; // Súper efectivo
            if (defensor == AGUA || defensor == FUEGO) return 0.5; // Poco efectivo
        }
        
        // Ataques de AGUA
        if (this == AGUA) {
            if (defensor == FUEGO) return 2.0;
            if (defensor == PLANTA || defensor == AGUA) return 0.5;
        }
        
        // Ataques de PLANTA
        if (this == PLANTA) {
            if (defensor == AGUA) return 2.0;
            if (defensor == FUEGO || defensor == PLANTA) return 0.5;
        }
        
        // Ataques ELÉCTRICOS
        if (this == ELECTRICO) {
            if (defensor == AGUA) return 2.0;
            if (defensor == PLANTA || defensor == ELECTRICO) return 0.5;
        }

       
        return 1.0; 
    }
}
