package Model;

import java.io.Serializable;

// Implementamos Serializable para que sea fácil hacer el SaveData después
public class Pokemon implements Serializable {
    private int id;
    private String nombre;
    private String tipo;
    private int hp;
    private int dano;
    private int velocidad;
    private int nivel;
    private int nivelEvolucion;
    private String nombreEvolucion;

    public Pokemon(int id, String nombre, String tipo, int hp, int dano, int velocidad, int nivel, int nivelEvolucion, String nombreEvolucion) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.hp = hp;
        this.dano = dano;
        this.velocidad = velocidad;
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
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDano() {
        return dano;
    }

    public void setDano(int dano) {
        this.dano = dano;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getNivelEvolucion() {
        return nivelEvolucion;
    }

    public void setNivelEvolucion(int nivelEvolucion) {
        this.nivelEvolucion = nivelEvolucion;
    }

    public String getNombreEvolucion() {
        return nombreEvolucion;
    }

    public void setNombreEvolucion(String nombreEvolucion) {
        this.nombreEvolucion = nombreEvolucion;
    }

    public void subirNivel() {
        this.nivel++;
    }
}
