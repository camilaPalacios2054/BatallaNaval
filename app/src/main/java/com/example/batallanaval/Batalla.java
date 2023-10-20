package com.example.batallanaval;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Batalla extends AppCompatActivity {

    private ImageView[][] matrizImagenesEnemigo=new ImageView[10][10];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batalla);

        Button volver=findViewById(R.id.volver2);
        Button volverMenu2=findViewById(R.id.volverMenu2);

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Batalla.this,Juego.class);
                startActivity(i);
            }
        });

        volverMenu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Batalla.this,MainActivity.class);
                startActivity(i);
            }
        });

    }
}