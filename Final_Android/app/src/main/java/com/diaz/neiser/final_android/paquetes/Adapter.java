package com.diaz.neiser.final_android.paquetes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.diaz.neiser.final_android.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.PokemonViewHolder>{

    List<Pokemones> mData;
    private View.OnClickListener listener;

    public Adapter(List<Pokemones> mData) {
        this.mData = mData;
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent, false);
        Adapter.PokemonViewHolder viewHolder = new Adapter.PokemonViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        TextView name = holder.itemView.findViewById(R.id.nombre);
        TextView tipe = holder.itemView.findViewById(R.id.tipo);
        ImageView image = holder.itemView.findViewById(R.id.imageAnime);

        String nombre = String.valueOf(mData.get(position).getNombre());
        String tipo = String.valueOf(mData.get(position).getTipo());
        String imagen = String.valueOf(mData.get(position).getUrl_imagen());

        name.setText(nombre);
        tipe.setText(tipo);
        Picasso.get().load(imagen).into(image);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class PokemonViewHolder extends RecyclerView.ViewHolder{
        public PokemonViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
