package com.diaz.neiser.final_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.diaz.neiser.final_android.paquetes.Adapter;
import com.diaz.neiser.final_android.paquetes.Pokemones;
import com.diaz.neiser.final_android.paquetes.servicio;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class List_pokemon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pokemon);
        RecyclerView rv = findViewById(R.id.rvPokemons);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://upn.lumenes.tk/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        servicio service = retrofit.create(servicio.class);
        Call<List<Pokemones>> pokemon = service.getAll();

        Activity activity;
        //IcomunicaRV icomunicaRV;

        pokemon.enqueue(new Callback<List<Pokemones>>() {
            @Override
            public void onResponse(Call<List<Pokemones>> call, Response<List<Pokemones>> response) {
                if(response.code() == 200){
                    Log.i("Web","Conexion todo ok :D");
                    List<Pokemones> pokemons  = response.body();
                    rv.setAdapter(new Adapter(pokemons));
                }else {
                    Log.i("Web","Conexion nada ok F");
                }
            }

            @Override
            public void onFailure(Call<List<Pokemones>> call, Throwable t) {
                Log.i("Web","NO pudimos conectarnos al servidor");
            }
        });
    }
}