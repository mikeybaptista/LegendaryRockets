package com.nopearino.legendaryrockets;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nopearino.legendaryrockets.model.Rockets;

import java.util.List;

import static android.content.ContentValues.TAG;

public class RocketAdapter extends RecyclerView.Adapter<RocketAdapter.RocketViewHolder>{

    List<Rockets> rockets;
    Context ctx;

    @Override
    public RocketAdapter.RocketViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        RocketViewHolder rvh = new RocketViewHolder(v);
        return rvh;
    }

    @Override
    public void onBindViewHolder(RocketAdapter.RocketViewHolder viewHolder, int i) {
        viewHolder.manuf.setText(rockets.get(i).getManuf());
        viewHolder.nome.setText(rockets.get(i).getNome());
        switch (rockets.get(i).getNome()){
            case ("Badaboom"):viewHolder.img.setImageResource(R.drawable.badaboom);break;
            case ("Bunny"):viewHolder.img.setImageResource(R.drawable.bunny);break;
            case ("Mongol"):viewHolder.img.setImageResource(R.drawable.mongol);break;
            case ("Norfleet"):viewHolder.img.setImageResource(R.drawable.norfleet);break;
            case ("Nukem"):viewHolder.img.setImageResource(R.drawable.nukem);break;
            case ("Pyrophobia"):viewHolder.img.setImageResource(R.drawable.pyrophobia);break;
            default:viewHolder.img.setImageResource(R.drawable.none);break;
        }
    }

    @Override
    public int getItemCount() {
        return rockets.size();
    }

    RocketAdapter(List<Rockets> rockets,Context ctx){
        this.ctx=ctx;
        this.rockets=rockets;
    }

    public static class RocketViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView nome;
        TextView manuf;
        ImageView img;

        public RocketViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            nome = (TextView) itemView.findViewById(R.id.nome);
            manuf = (TextView) itemView.findViewById(R.id.manuf);
            img = (ImageView) itemView.findViewById(R.id.img);
        }

        @Override
        public void onClick(View view) {
            Log.d(TAG, "onClick " + getPosition() + nome);
        }
    }
}
