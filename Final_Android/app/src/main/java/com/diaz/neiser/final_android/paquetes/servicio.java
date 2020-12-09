package com.diaz.neiser.final_android.paquetes;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface servicio {
    @GET("pokemons/N00022213")
    Call<List<Pokemones>> getAll();

    @POST("pokemons/N00022213/crear")
    Call<Pokemones> create(@Body Pokemones pokemon);
}
