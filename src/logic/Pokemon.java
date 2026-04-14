package logic;

import java.io.Serializable;

// Implementamos Serializable para que sea fácil hacer el SaveData después
public class Pokemon implements Serializable {
    private String nombre;
    private String tipo;
    private int hp;
    private int hpMax;
    private int nivel;
    private int nivelEvolucion;
    private String nombreEvolucion;

    public Pokemon(String nombre, String tipo, int hp, int nivel, int nivelEvolucion, String nombreEvolucion) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.hp = hp;
        this.hpMax = hp;
        this.nivel = nivel;
        this.nivelEvolucion = nivelEvolucion;
        this.nombreEvolucion = nombreEvolucion;
    }

    // Lógica de daño
    public void recibirDano(int dano) {
        this.hp -= dano;
        if (this.hp < 0) this.hp = 0;
    }

    // Lógica de evolución
    public boolean puedeEvolucionar() {
        return nivel >= nivelEvolucion && !nombreEvolucion.equals("Ninguna");
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public String getTipo() { return tipo; }
    public int getHp() { return hp; }
    public int getHpMax() { return hpMax; }
    public int getNivel() { return nivel; }
    public void setNivel(int nivel) { this.nivel = nivel; }
    public String getNombreEvolucion() { return nombreEvolucion; }
    
    public void subirNivel() { this.nivel++; }
}

