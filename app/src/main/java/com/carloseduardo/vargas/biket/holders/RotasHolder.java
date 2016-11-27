package com.carloseduardo.vargas.biket.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.carloseduardo.vargas.biket.R;

/**
 * Created by CarlosEduardode on 27/11/2016.
 * Holder para lista de rotas
 */
public class RotasHolder extends RecyclerView.ViewHolder {

    public TextView rotaRotaKm;
    public TextView rotaData;

    public RotasHolder(View view) {
        super(view);

        this.rotaRotaKm = (TextView) view.findViewById(R.id.rotaRotaKm);
        this.rotaData = (TextView) view.findViewById(R.id.rotaData);
    }
}
