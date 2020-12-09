package com.diaz.neiser.final_android;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.diaz.neiser.final_android.paquetes.Pokemones;
import com.diaz.neiser.final_android.paquetes.servicio;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class new_pokemon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pokemon);
        EditText nombre = findViewById(R.id.name);
        EditText tipo = findViewById(R.id.tipe);
        EditText imagen = findViewById(R.id.image);
        EditText latitud = findViewById(R.id.latitud);
        EditText longitud = findViewById(R.id.longitud);
        Button crear = findViewById(R.id.newPokemon);

        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nombre.getText().toString().trim();
                String tipos = tipo.getText().toString().trim();
                String image = imagen.getText().toString().trim();
                String latitude = latitud.getText().toString().trim();
                String longitude = longitud.getText().toString().trim();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://upn.lumenes.tk")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                servicio service = retrofit.create(servicio.class);
                Pokemones pokemon = new Pokemones();
                pokemon.setNombre(name);
                pokemon.setTipo(tipos);
                pokemon.setUrl_imagen(image);
                pokemon.setLatitude(latitude);
                pokemon.setLongitude(longitude);

                Call<Pokemones> call = service.create(pokemon);
                call.enqueue(new Callback<Pokemones>() {
                    @Override
                    public void onResponse(Call<Pokemones> call, Response<Pokemones> response) {
                        if(response.code() == 200){
                            Log.i("Web","Conexion buena :)");
                        }else {
                            Log.i("Web","Conexion mala :c");
                        }
                    }
                    @Override
                    public void onFailure(Call<Pokemones> call, Throwable t) {
                        Log.i("Web","NO pudimos conectarnos al servidor");
                    }
                });
                Intent intent = new Intent(new_pokemon.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}