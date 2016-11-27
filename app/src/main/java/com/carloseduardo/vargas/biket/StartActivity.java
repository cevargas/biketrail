package com.carloseduardo.vargas.biket;

import android.content.Intent;
import android.os.Bundle;

/**
 * Classe Activity de inicializacao do aplicativo
 * Bem vindo.
 */
public class StartActivity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
