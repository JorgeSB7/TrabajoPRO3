package com.mycompany.mavenproject1.model;

import java.util.Objects;

/**
 *
 * @author Jorge
 */
public class Sesion {

    protected int CS;
    protected int CD;
    protected int CF;
    protected int CD2;
    protected int CF2;
    protected String nombre;
    protected int duracion;

    public Sesion(int CS, int CD, int CF, int CD2, int CF2, String nombre, int duracion) {
        this.CS = CS;
        this.CD = CD;
        this.CF = CF;
        this.CD2 = CD2;
        this.CF2 = CF2;
        this.nombre = nombre;
        this.duracion = duracion;
    }

    

    public Sesion() {
        this(-1, -1, -1, -1, -1, "", -1);
    }

    public int getCS() {
        return CS;
    }

    public void setCS(int CS) {
        this.CS = CS;
    }

    public int getCD() {
        return CD;
    }

    public void setCD(int CD) {
        this.CD = CD;
    }

    public int getCF() {
        return CF;
    }

    public void setCF(int CF) {
        this.CF = CF;
    }

    public int getCD2() {
        return CD2;
    }

    public void setCD2(int CD2) {
        this.CD2 = CD2;
    }

    public int getCF2() {
        return CF2;
    }

    public void setCF2(int CF2) {
        this.CF2 = CF2;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    @Override
    public String toString() {
        return "Sesion{" + "CS=" + CS + ", CD=" + CD + ", CF=" + CF + ", CD2=" + CD2 + ", CF2=" + CF2 + ", nombre=" + nombre + ", duracion=" + duracion + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sesion other = (Sesion) obj;
        if (this.CS != other.CS) {
            return false;
        }
        return true;
    }
    

}
