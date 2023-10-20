package com.example.batallanaval;

import android.media.Image;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import android.view.*;
import java.util.*;



//adaptador se comunica entre la presentacion y la fuente de informacion
public class RecyclerViewAdaptador extends RecyclerView.Adapter<RecyclerViewAdaptador.ViewHolder> {
    //debe extender de una clase recycler view viewholder
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView tituloJuego,descripcionJuego;
        ImageView imagenJuego;

        //vincula cada item con algun atibuto del objeto
        public ViewHolder(View itemView)
        {
            super(itemView);
            tituloJuego=(TextView) itemView.findViewById(R.id.tvTituloJuego);
            descripcionJuego=(TextView) itemView.findViewById(R.id.tvDescripcionJuego);
            imagenJuego=(ImageView) itemView.findViewById(R.id.imagenJuego);
        }
    }

    public List<JuegosModelo> juegosLista;

    public RecyclerViewAdaptador(List<JuegosModelo>juegosLista)
    {
        this.juegosLista=juegosLista;
    }
    //Se utiliza inflet para hacer uso de un layout dentro de otro layout
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType)
    {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_juegos,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    //onbind realiza las modificaciones del contenido para cada item
    @Override
    public void onBindViewHolder (ViewHolder holder,int position)
    {
        holder.tituloJuego.setText(juegosLista.get(position).getTituloJuego());
        holder.descripcionJuego.setText(juegosLista.get(position).getDescripcionJuego());
        holder.imagenJuego.setImageResource(juegosLista.get(position).getImagenJuego());
    }

    @Override
    public int getItemCount()
    {
        return juegosLista.size();
    }

}
