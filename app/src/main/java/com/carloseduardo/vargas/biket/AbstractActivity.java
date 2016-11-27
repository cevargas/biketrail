package com.carloseduardo.vargas.biket;

import android.support.v7.app.AppCompatActivity;

import com.carloseduardo.vargas.biket.utils.Utils;

public class AbstractActivity extends AppCompatActivity {

    final String TAG = this.getClass().getSimpleName();

    Utils utils = new Utils(this);

}
