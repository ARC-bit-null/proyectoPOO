package src.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Pokemon implements Serializable {
    private int xp = 0;
    private int xpMax = 100; // La experiencia necesaria para el nivel 1
    private int defensa;
    private int id;
    private String nombre;
    private TipoPokemon tipo;
    private int hp;
    private int hpMax;
    private int dano;
    private int velocidad;
    private int nivel;
    private int nivelEvolucion;
    private String nombreEvolucion;
    private ArrayList<Habilidad> habilidades;
    private String imagenFrontal;
    private String imagenTrasera;

    // Constructor original para mantener compatibilidad
    public Pokemon(int id, String nombre, TipoPokemon tipo, int hp, int dano, int defensa, int velocidad, int nivel, int nivelEvolucion, String nombreEvolucion) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.hp = hp;
        this.hpMax = hp;
        this.dano = dano;
        this.defensa = defensa;
        this.velocidad = velocidad;
        this.nivel = nivel;
        this.nivelEvolucion = nivelEvolucion;
        this.nombreEvolucion = nombreEvolucion;
        this.habilidades = new ArrayList<>();
        this.imagenFrontal = "";
        this.imagenTrasera = "";
    }

    // Constructor completo pensado para trabajar mejor con Builder
    public Pokemon(int id, String nombre, TipoPokemon tipo, int hp, int hpMax, int dano, int defensa, int velocidad,
                   int nivel, int nivelEvolucion, String nombreEvolucion,
                   ArrayList<Habilidad> habilidades, String imagenFrontal, String imagenTrasera) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.hp = hp;
        this.hpMax = hpMax;
        this.dano = dano;
        this.defensa = defensa;
        this.velocidad = velocidad;
        this.nivel = nivel;
        this.nivelEvolucion = nivelEvolucion;
        this.nombreEvolucion = nombreEvolucion;
        this.habilidades = (habilidades != null) ? new ArrayList<>(habilidades) : new ArrayList<>();
        this.imagenFrontal = imagenFrontal != null ? imagenFrontal : "";
        this.imagenTrasera = imagenTrasera != null ? imagenTrasera : "";
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

    // Metodo para saber si el pokemon fue derrotado
    public boolean estaDerrotado() {
        return hp == 0;
    }

    // Metodo para curar cierta cantidad de vida sin sobrepasar la vida máxima
    public void curarHp(int cantidad) {
        if (hp > 0) {
            hp += cantidad;
            if (hp > hpMax) {
                hp = hpMax;
            }
        }
    }

    // Metodo para revivir al pokemon restaurando toda su vida máxima
    public void revivir() {
        if (hp == 0) {
            hp = hpMax;
        }
    }

    // Metodo para aumentar el daño del pokemon durante el combate
    public void aumentarDano(int cantidad) {
        dano += cantidad;
    }

    // Metodo para aumentar la velocidad del pokemon durante el combate
    public void aumentarVelocidad(int cantidad) {
        velocidad += cantidad;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getXpMax() {
        return xpMax;
    }

    public void setXpMax(int xpMax) {
        this.xpMax = xpMax;
    }

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

    public TipoPokemon getTipo() {
        return tipo;
    }

    public void setTipo(TipoPokemon tipo) {
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

    public int getHpMax() {
        return hpMax;
    }

    public void setHpMax(int hpMax) {
        this.hpMax = hpMax;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
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

    public ArrayList<Habilidad> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(ArrayList<Habilidad> habilidades) {
        this.habilidades = (habilidades != null) ? new ArrayList<>(habilidades) : new ArrayList<>();
    }

    public void agregarHabilidad(Habilidad habilidad) {
        if (habilidad != null) {
            this.habilidades.add(habilidad);
        }
    }

    public String getImagenFrontal() {
        return imagenFrontal;
    }

    public void setImagenFrontal(String imagenFrontal) {
        this.imagenFrontal = imagenFrontal;
    }

    public String getImagenTrasera() {
        return imagenTrasera;
    }

    public void setImagenTrasera(String imagenTrasera) {
        this.imagenTrasera = imagenTrasera;
    }

    public void subirNivel() {
        this.nivel++;
    }

    public Habilidad getAtaqueBasico() {
        for (Habilidad habilidad : habilidades) {
            if (habilidad.getTipo() == TipoPokemon.NORMAL) {
                return habilidad;
            }
        }
        return null;
    }

    public Habilidad getHabilidadPorTipo(TipoPokemon tipoBuscado) {
        for (Habilidad habilidad : habilidades) {
            if (habilidad.getTipo() == tipoBuscado) {
                return habilidad;
            }
        }
        return null;
    }

    // Método para recalcular y escalar estadísticas al subir de nivel
    public void calcularEstadisticasPokemon() {
        this.hpMax = this.hpMax + 8;
        this.dano = this.dano + 3;
        this.velocidad = this.velocidad + 2;
        this.defensa = this.defensa + 2;
        this.hp = this.hpMax;
    }

    // Método para gestionar la experiencia ganada en combate
    public void ganarExperiencia(int cantidadXp) {
        if (this.estaDerrotado()) return;

        this.xp += cantidadXp;
        System.out.println(this.nombre + " obtuvo " + cantidadXp + " puntos de XP.");

        while (this.xp >= this.xpMax) {
            this.xp -= this.xpMax;
            this.nivel++;
            this.xpMax = (int) (this.xpMax * 1.5);

            System.out.println("¡Felicidades! ¡" + this.nombre + " subió al nivel " + this.nivel + "!");

            calcularEstadisticasPokemon();

            if (puedeEvolucionar()) {
                System.out.println("¡Atención! " + this.nombre + " está evolucionando en " + this.nombreEvolucion + "!");
                this.nombre = this.nombreEvolucion;
                this.dano += 10;
                this.hpMax += 20;
                this.hp = this.hpMax;
            }
        }
    }
}