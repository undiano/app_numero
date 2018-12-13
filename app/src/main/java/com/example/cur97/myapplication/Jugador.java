package com.example.cur97.myapplication;

public class Jugador implements Comparable<Jugador> {
    private String name;
    private int intentos;

    public Jugador(String name, int intentos) {
        this.name = name;
        this.intentos = intentos;
    }

    public String getName() {
        return name;
    }

    public int getIntentos() {
        return intentos;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "name='" + name + '\'' +
                ", intentos=" + intentos +
                '}';
    }

    @Override
    public int compareTo(Jugador jug){
        return this.intentos - jug.intentos;

    }

}