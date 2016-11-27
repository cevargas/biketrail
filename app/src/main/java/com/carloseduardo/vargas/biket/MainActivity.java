package com.carloseduardo.vargas.biket;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.carloseduardo.vargas.biket.adapters.RotasAdapter;
import com.carloseduardo.vargas.biket.dao.RotaDAO;
import com.carloseduardo.vargas.biket.models.Rota;
import com.carloseduardo.vargas.biket.utils.DividerItemDecoration;
import com.carloseduardo.vargas.biket.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe activity com Opcao para iniciar a rota
 * Deveria ter um menu com opcoes...historico..amigos. rotas publicas..etc
 *
 */
public class MainActivity extends AbstractActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<Rota> rotaList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Aqui é instanciado o Recyclerview
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_activity_main);
        mRecyclerView.setHasFixedSize(true);

        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        mRecyclerView.addItemDecoration(itemDecoration);

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mLayoutManager.scrollToPosition(0);
        mRecyclerView.setLayoutManager(mLayoutManager);

        rotaList = new RotaDAO(this).list();

        mAdapter = new RotasAdapter(this, rotaList);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setHomeButtonEnabled(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.iniciar_btn:
                if(utils.checkGPS()) {
                    Intent startIntent = new Intent(MainActivity.this, MapsActivity.class);
                    startActivity(startIntent);
                } else {
                    Toast.makeText(
                            getApplicationContext(),
                            "GPS está inativo.",
                            Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
