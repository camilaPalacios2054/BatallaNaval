package com.example.batallanaval;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerJuegos;
    private RecyclerViewAdaptador adaptadorJuegos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        recyclerJuegos=(RecyclerView)findViewById(R.id.recyclerJuegos);
        recyclerJuegos.setLayoutManager(new LinearLayoutManager(this));

        adaptadorJuegos=new RecyclerViewAdaptador(obtenerJuegos());
        recyclerJuegos.setAdapter(adaptadorJuegos);

        Button iniciar=findViewById(R.id.play);
        Button salir=findViewById(R.id.exit);

        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this, Juego.class);
                startActivity(i);
            }
        });

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    public List<JuegosModelo>obtenerJuegos()
    {
        List<JuegosModelo> tituloJuego=new ArrayList<>();
        tituloJuego.add(new JuegosModelo("tic tac toe","Juego de estrategia donde tendras que usar la lógica para crear un tres en raya contra tus amigos o la máquina ! ",R.drawable.tictactoe));
        tituloJuego.add(new JuegosModelo("busca minas","Deberás confiar un tus instintos y evitar a toda costa explotar con una bomba . ",R.drawable.buscaminas));
        tituloJuego.add(new JuegosModelo("memory","prueba3",R.drawable.cruz));

        return tituloJuego;
    }
}