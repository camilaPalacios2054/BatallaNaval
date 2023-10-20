package com.example.batallanaval;

class Barco extends Juego
{
    private String nombre;
    private boolean esHorizontal;
    private int longitud;
    private int valorIdentificador;
    private boolean hundido;

    public Barco (String nombre, int longitud,int valorIdentificador,boolean esHorizontal)
    {
        this.nombre=nombre;
        this.longitud=longitud;
        this.valorIdentificador=valorIdentificador;
        this.esHorizontal=esHorizontal;
        hundido=false;
    }
    //Método GETTS
    public String getNombre()
    {
        return nombre;
    }
    public int getLongitud()
    {
        return longitud;
    }
    public int getValorIdentificador()
    {
        return valorIdentificador;
    }
    public boolean getEsHorizontal()
    {
        return esHorizontal;
    }
}

