package com.carloseduardo.vargas.biket;

import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.carloseduardo.vargas.biket.dao.RotaDAO;
import com.carloseduardo.vargas.biket.models.Rota;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by CarlosEduardode on 27/11/2016.
 */
public class RotaDetalhesActivity extends AbstractActivity implements OnMapReadyCallback {

    public static final String TAG = MapsActivity.class.getSimpleName();

    private GoogleMap gMap;
    private LocationManager lm;
    private Location myLocation;
    private ArrayList<LatLng> points;
    private Rota rota = new Rota();
    static final Long defaultValue = 0L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rota_detalhes);

        // Set a toolbar to replace the action bar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarDetalhes);
        setSupportActionBar(toolbar);

        Long id = getIntent().getLongExtra("ID", defaultValue);
        rota = new RotaDAO(this).getRotaById(id);

        if(rota != null) {

        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_rota_detalhe, menu);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Detalhes da Rota");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                super.onBackPressed();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
