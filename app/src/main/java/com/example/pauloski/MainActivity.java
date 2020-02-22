package com.example.pauloski;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnItemClickListener{

    private ArtistaAdapter adapter;
    CoordinatorLayout contentmain;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contentmain=(CoordinatorLayout)findViewById(R.id.containermain);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        configToolbar();
        configAdapter();
        configRecyclerView();

        generateArtist();
    }

    private void generateArtist() {
        String [] nombres={"Rachel","Megan","Renée","Margot"};
        String [] apellidos={"Matthews","Fox","Zellweger","Robbie"};
        long[] nacimientos={27216000000L,689385600000L,779155200000L,526262400000L};
        String[] lugares={"Canada","Peru","Rusia","EEUU"};
        short[] estaturas={173,183,168,175};
        String[] notas={"Rachel Matthews was born as Rachel Lynn Matthews. She is an actress,known for Feliz día de tu muerte (2017), Feliz día de tu muerte 2 (2019) and Frozen II (2019)."
        ,"Megan Fox nació Megan Denise Fox el 16 de mayo de 1986 en Oak Ridge, Tennessee, de Gloria Darlene Tonachio (nacida: Gloria Darlene Cisson), gerente de bienes raíces y Franklin Thomas Fox, oficial de libertad condicional; Fue criada en Rockwood, Tennessee durante su primera infancia. Comenzó su formación en teatro y danza a los 5 años y a los 10 años",
                "Renée Kathleen Zellweger nació el 25 de abril de 1969 en Katy, Texas, EE. UU. Su madre, Kjellfrid Irene (Andreassen), es una ex enfermera y partera nacida en Noruega, de ascendencia noruega, Kven (finlandesa) y sueca. Su padre, Emil Erich Zellweger, es un ingeniero nacido en Suiza. Los dos se casaron en 1963"
        ,"Margot Elise Robbie nació el 2 de julio de 1990 en Dalby, Queensland, Australia, de padres escoceses. Su madre, Sarie Kessler , es fisioterapeuta, y su padre, Doug Robbie. Ella viene de una familia de cuatro hijos, que tiene dos hermanos y una hermana."
        };
        String[] fotos={"https://upload.wikimedia.org/wikipedia/commons/9/9e/Julia_Roberts_2011_Shankbone.JPG",
                "https://upload.wikimedia.org/wikipedia/commons/e/e9/Megan_Fox_2014.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/9/90/Ren%C3%A9e_Zellweger_Face.PNG",
                "https://upload.wikimedia.org/wikipedia/commons/3/37/Margot_Robbie_at_Somerset_House_in_2013.jpg"};

        for (int i=0;i<4;i++){
            Artista artista=new Artista(i+1,nombres[i],apellidos[i],
                    nacimientos[i],lugares[i],estaturas[i],notas[i],i+1,fotos[i]);
            adapter.add(artista);
        }

    }

    private void configRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
    private void configAdapter() {
        adapter=new ArtistaAdapter(new ArrayList<Artista>(),this);
    }

    private void configToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**********
     *  Metodos implementador por la interfaz onitemCLicklistener
     * **********/
    @Override
    public void onItemClick(Artista artista) {

    }

    @Override
    public void onLongItemClick(Artista artista) {

    }
}
