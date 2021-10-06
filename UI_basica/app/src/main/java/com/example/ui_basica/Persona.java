package com.example.ui_basica;

import java.util.Objects;

public class Persona implements java.io.Serializable{

    public String nombre, apellido, telf;

    public Persona(String nombre, String apellido, String telf){
        this.nombre = nombre;
        this.apellido = apellido;
        this.telf = telf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return nombre.equals(persona.nombre) && apellido.equals(persona.apellido) && telf.equals(persona.telf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, apellido, telf);
    }
}
