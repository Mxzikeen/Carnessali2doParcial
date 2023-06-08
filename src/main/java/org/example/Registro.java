package org.example;

import java.io.Serializable;

public class Registro implements Serializable {
    private Personas personas;
    private Integer temp;

    public Registro(Personas personas, Integer temp) {
        this.personas = personas;
        this.temp = temp;
    }

    public Personas getPersonas() {
        return personas;
    }

    public void setPersonas(Personas personas) {
        this.personas = personas;
    }

    public Integer getTemp() {
        return temp;
    }

    public void setTemp(Integer temp) {
        this.temp = temp;
    }

    @Override
    public String toString() {
        return "Registro{" +
                "DNI=" + personas.getDni() +
                ", temp=" + temp +
                '}';
    }
}
