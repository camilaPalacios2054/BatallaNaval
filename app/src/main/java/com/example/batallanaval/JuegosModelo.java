package com.example.batallanaval;

public class JuegosModelo {
    private String tituloJuego, descripcionJuego;
    private int imagenJuego;

    public JuegosModelo() {
    }

    public JuegosModelo(String tituloJuego, String descripcionJuego, int imagenJuego) {
        this.tituloJuego = tituloJuego;
        this.descripcionJuego = descripcionJuego;
        this.imagenJuego = imagenJuego;
    }

    public String getTituloJuego() {
        return tituloJuego;
    }

    public void setTituloJuego(String tituloJuego) {
        this.tituloJuego = tituloJuego;
    }

    public String getDescripcionJuego() {
        return descripcionJuego;
    }

    public void setDescripcionJuego(String descripcionJuego) {
        this.descripcionJuego = descripcionJuego;
    }

    public int getImagenJuego() {
        return imagenJuego;
    }

    public void setImagenJuego(int imagenJuego) {
        this.imagenJuego = imagenJuego;
    }
}