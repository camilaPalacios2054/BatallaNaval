package com.example.batallanaval;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.*;
import java.util.*;


public class Juego extends AppCompatActivity {
    //ImageView[][] matrizVisualJugador = new ImageView[10][10];
    //ImageView[][] matrizVisualEnemigo = new ImageView[10][10];
    boolean turno = true;
    GridLayout tablero1;
    GridLayout tablero2;
    TextView text1;
    TextView text2;

    boolean eventoCumplido=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        Button volver = findViewById(R.id.volverMenu);
        Button battle = findViewById(R.id.batalla);
        text1=findViewById(R.id.tTableroJugador1);
        text2=findViewById(R.id.tTableroJugador2);

        tablero1 = findViewById(R.id.gTableroJugador);
        tablero2 = findViewById(R.id.gTableroEnemigo);


        ////////////////////
        /////////////////
        /////////////////




        battle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Juego.this, Batalla.class);
                startActivity(i);
            }
        });
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Juego.this, MainActivity.class);
                startActivity(i);
            }
        });

        creacionVariablesDelJuego();
    }

    public void creacionVariablesDelJuego() {

        //Creacion de tableros
        Tablero tableroJugador = new Tablero("TABLERO JUGADOR");
        Tablero tableroEnemigo = new Tablero("TABLERO ENEMIGO");
        tableroJugador.crearTableroVisual(tablero1);
        tableroEnemigo.crearTableroVisual(tablero2);

        tableroJugador.imprimirTablero();
        tableroEnemigo.imprimirTablero();


        //creacion de barcos
        Barco a1 = new Barco("Portaviones", 5, 1, true);
        Barco a2 = new Barco("Acorazado", 4, 2, false);
        Barco a3 = new Barco("Crucero", 3, 3, true);
        Barco a4 = new Barco("Submarino", 3, 4, false);
        Barco a5 = new Barco("Destructor", 2, 5, true);
        tableroJugador.agregarBarco(a1);
        tableroJugador.agregarBarco(a2);
        tableroJugador.agregarBarco(a3);
        tableroJugador.agregarBarco(a4);
        tableroJugador.agregarBarco(a5);

        Barco b1 = new Barco("Portaviones", 5, 1, true);
        Barco b2 = new Barco("Acorazado", 4, 2, false);
        Barco b3 = new Barco("Crucero", 3, 3, true);
        Barco b4 = new Barco("Submarino", 3, 4, false);
        Barco b5 = new Barco("Destructor", 2, 5, true);
        tableroEnemigo.agregarBarco(b1);
        tableroEnemigo.agregarBarco(b2);
        tableroEnemigo.agregarBarco(b3);
        tableroEnemigo.agregarBarco(b4);
        tableroEnemigo.agregarBarco(b5);

        tableroJugador.imprimirBarcosEnTablero();
        tableroEnemigo.imprimirBarcosEnTablero();

        //Ubicacion de barcos
        cambiarTexto("Ubica tu "+a1.getNombre()+" al tablero, este es horizontal y ocupa "+a1.getLongitud()+" de espacio");
        tableroJugador.añadirBarco(a1);


        cambiarTexto("Ubica tu "+b1.getNombre()+" al tablero, este es horizontal y ocupa "+b1.getLongitud()+" de espacio");
        tableroEnemigo.añadirBarco(b1);
    }

    public void cambiarTexto(String nuevoTexto)
    {
        text1.setText(nuevoTexto);
        text2.setText(nuevoTexto);
    }
    class Tablero {
        private String nombre;
        private int[][] tableroLogico;
        private ImageView[][] matrizImagenes;
        private ArrayList<Barco> listaDeBarcos;

        private int fila;
        private int columna;
        private boolean onClickEnabled = false;


        public Tablero(String nombre) {
            this.nombre = nombre;
            tableroLogico = new int[10][10];
            matrizImagenes = new ImageView[10][10];
            listaDeBarcos = new ArrayList<>();
        }

        public void agregarBarco(Barco barco) {
            listaDeBarcos.add(barco);
        }

        public void imprimirBarcosEnTablero() {
            System.out.println(nombre + " tiene estos barcos :");
            for (Barco barco : listaDeBarcos) {
                System.out.println(barco.getNombre());
            }
        }

        public void imprimirTablero() {
            System.out.println(nombre);
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    System.out.print(tableroLogico[i][j] + " ");
                }
                System.out.println();
            }
        }

        //Crea ambos tableros el logico y el visual en la misma funcion
        public void crearTableroVisual(GridLayout tablero) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    matrizImagenes[i][j] = new ImageView(Juego.this);
                    matrizImagenes[i][j].setImageResource(R.drawable.mar); // Asigna una imagen predeterminada
                    matrizImagenes[i][j].setLayoutParams(new GridLayout.LayoutParams());
                    matrizImagenes[i][j].getLayoutParams().width = 100;
                    matrizImagenes[i][j].getLayoutParams().height = 100;
                    matrizImagenes[i][j].setScaleType(ImageView.ScaleType.FIT_CENTER);
                    tablero.addView(matrizImagenes[i][j]);
                    tableroLogico[i][j] = 0;
                }
            }
        }

        public void detectarPosicion(ImageView[][] matrizImagenes, Barco barco) {
            {
                    for (int i = 0; i < 10; i++) {
                        for (int j = 0; j < 10; j++) {
                            matrizImagenes[i][j].setTag(String.valueOf(i) + "-" + String.valueOf(j));
                            matrizImagenes[i][j].setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (onClickEnabled == true) {
                                        String tag = v.getTag().toString();
                                        String[] parts = tag.split("-");
                                        fila = Integer.parseInt(parts[0]);
                                        columna = Integer.parseInt(parts[1]);
                                        System.out.println("fila: " + fila + " columna: " + columna);
                                        completarEspaciosEnTablero(fila, columna, barco, matrizImagenes);
                                        imprimirTablero();
                                        onClickEnabled = false;
                                    }
                                }
                            });
                        }
                    }
            }
        }

        public void añadirBarco(Barco barco) {
                onClickEnabled = true;
                detectarPosicion(matrizImagenes, barco);
            }

        public void completarEspaciosEnTablero(int fila, int columna, Barco barco,ImageView[][] matrizImagenes)
            //inicio
        {
                int opcion = barco.getValorIdentificador();
                if (barco.getEsHorizontal()) {
                    boolean espaciosDisponibles = true;
                    //para i igual a columna, i menor a columna + longitud del barco i++
                    for (int i = columna; i < columna + barco.getLongitud(); i++) {
                        //si i es mayor o igual a 10 o columna de tablero de matriz es diferente de 0
                        if (i >= 10 || tableroLogico[fila][i] != 0) {
                            // Fuera de los límites o espacio ocupado
                            espaciosDisponibles = false;
                            break;
                        }
                    }
//Si se cumplen los espacios disponibles se coloca el barco
                    if (espaciosDisponibles) {
                        // para i igual a columna, i menor a columna más la longitud del barco i++ , rellena en la fila y columna de la matriz con el número identificador del barco
                        for (int i = columna; i < columna + barco.getLongitud(); i++) {

                            tableroLogico[fila][i] = barco.getValorIdentificador();

                            switch (opcion) {
                                case 1:
                                    matrizImagenes[fila][i].setImageResource(R.drawable.uno);
                                    break;
                                case 2:
                                    matrizImagenes[fila][i].setImageResource(R.drawable.dos);
                                    break;
                                case 3:
                                    matrizImagenes[fila][i].setImageResource(R.drawable.tres);
                                    break;
                                case 4:
                                    matrizImagenes[fila][i].setImageResource(R.drawable.cuatro);
                                    break;
                                case 5:
                                    matrizImagenes[fila][i].setImageResource(R.drawable.cinco);
                                    break;
                            }

                        }
                    } else {
                        System.out.println("No hay suficientes espacios horizontales disponibles para el barco.");
                    }
                } else {
                    // Verificar si los espacios verticales están disponibles
                    boolean espaciosDisponibles = true;
                    for (int i = fila; i < fila + barco.getLongitud(); i++) {
                        if (i >= 10 || tableroLogico[i][columna] != 0) { // Fuera de los límites o espacio ocupado
                            espaciosDisponibles = false;
                            break;
                        }
                    }

                    if (espaciosDisponibles) {
                        // Colocar el barco en orientación vertical
                        for (int i = fila; i < fila + barco.getLongitud(); i++) {
                            tableroLogico[i][columna] = barco.getValorIdentificador();
                            switch (opcion) {
                                case 1:
                                    matrizImagenes[i][columna].setImageResource(R.drawable.uno);
                                    break;
                                case 2:
                                    matrizImagenes[i][columna].setImageResource(R.drawable.dos);
                                    break;
                                case 3:
                                    matrizImagenes[i][columna].setImageResource(R.drawable.tres);
                                    break;
                                case 4:
                                    matrizImagenes[i][columna].setImageResource(R.drawable.cuatro);
                                    break;
                                case 5:
                                    matrizImagenes[i][columna].setImageResource(R.drawable.cinco);
                                    break;
                            }
                        }
                    } else {
                        System.out.println("No hay suficientes espacios verticales disponibles para el barco.");
                    }
                }
                imprimirTablero();

                //fin
//}
                //  else
                //   {
                // La ubicación no está vacía o está fuera de los límites de la matriz
                //   System.out.println("La ubicación está ocupada o esta fuera de los límites");
                //   }
            }
        }
}


