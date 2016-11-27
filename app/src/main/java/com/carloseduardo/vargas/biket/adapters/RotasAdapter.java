package com.carloseduardo.vargas.biket.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.carloseduardo.vargas.biket.R;
import com.carloseduardo.vargas.biket.RotaDetalhesActivity;
import com.carloseduardo.vargas.biket.holders.RotasHolder;
import com.carloseduardo.vargas.biket.models.Rota;
import com.carloseduardo.vargas.biket.utils.Utils;

import java.util.List;

/**
 * Created by CarlosEduardode on 27/11/2016.
 */
public class RotasAdapter extends RecyclerView.Adapter<RotasHolder> {

    private Context mContext;
    private List<Rota> rotaList;

    public RotasAdapter(Context context, List<Rota> rotas) {
        this.rotaList = rotas;
        this.mContext = context;
    }

    @Override
    public RotasHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_itens_rotas, null);
        return new RotasHolder(view);
    }

    @Override
    public void onBindViewHolder(RotasHolder rotasHolder, final int i) {

        final Rota rota = rotaList.get(i);

        rotasHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (i != RecyclerView.NO_POSITION) {
                    Intent intent = new Intent(mContext, RotaDetalhesActivity.class);
                    intent.putExtra("id", rota.getId());
                    mContext.startActivity(intent);
                }
            }
        });

        if(rota.getData() != null) {
            rotasHolder.rotaData.setText(Utils.dbDateToString(rota.getData()));
        }

        if(rota.getTotalPercurso() != null) {
            rotasHolder.rotaRotaKm.setText(rota.getTotalPercurso().toString());
        }
    }

    @Override
    public int getItemCount() {
        return (null != rotaList ? rotaList.size() : 0);
    }

    @Override
    public long getItemId(int position) {
        return rotaList.get(position).getId();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
