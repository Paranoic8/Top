package com.example.pauloski;

import android.content.Context;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class ArtistaAdapter extends RecyclerView.Adapter<ArtistaAdapter.ViewHolder>{

   private List<Artista> artistas;
   private Context context;
   private OnItemClickListener listener;


    public ArtistaAdapter(List<Artista> artistas, OnItemClickListener listener) {
        this.artistas = artistas;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_artista,parent,false);
        this.context=parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //vincula vista con cada elemento y dar valor a los componentes
        final Artista artista=artistas.get(position);

        holder.setListener(artista,listener);

        holder.nombre.setText(artista.getNombreCompleto());
        holder.orden.setText(String.valueOf(artista.getOrden()));

        if (artista.getFotoUrl() !=null){

            /**
             *
             Personalizar uso de las imagens**/
            RequestOptions options=new RequestOptions();
            options.diskCacheStrategy(DiskCacheStrategy.ALL);//almaccena en cache la imagen de origen , despues de transformadada
            options.centerCrop();
            options.placeholder(R.drawable.ic_sentiment_satisfied);//mostrar mientras la imagen carga

            Glide.with(context).load(artista.getFotoUrl()).apply(options).into(holder.img_artista);
        }else{
            holder.img_artista.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_account_box_black_24dp));
        }

    }

    @Override
    public int getItemCount() {
        return this.artistas.size();
    }

    //metodo agregar artistas

    public void add(Artista artista){
        if (!artistas.contains(artista)) {//si no contiene el artista agregar a la lista
            artistas.add(artista);
            notifyDataSetChanged();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        AppCompatTextView nombre,orden;
        AppCompatImageView img_artista;
        RelativeLayout contentMain;

        public ViewHolder(View itemView) {
            super(itemView);
            nombre = (AppCompatTextView) itemView.findViewById(R.id.tv_nombre);
            orden = (AppCompatTextView) itemView.findViewById(R.id.tv_orden);
            img_artista = (AppCompatImageView)itemView.findViewById(R.id.img_artist);
            contentMain=(RelativeLayout)itemView.findViewById(R.id.contentMain);
        }

        void setListener(final Artista artista ,final OnItemClickListener listener){
            contentMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    listener.onItemClick(artista);
                }
            });

            contentMain.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onLongItemClick(artista);
                    return true;
                }
            });
        }
    }
}
