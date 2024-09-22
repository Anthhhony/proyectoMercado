package com.example.originmercadotecnia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class pantalla_principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pantalla_principal);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        /*Toolbar tb = (Toolbar) findViewById(R.id.toolbar4);
        setSupportActionBar(tb);*/

    }
    public void notes(View v){
        Intent i = new Intent(this, notas.class);
        startActivity(i);
    }
    public void TUnimarc(View v){
        Intent i = new Intent(this, Inicio.class);
        startActivity(i);
    }
    public void TLider(View v){
        Intent i = new Intent(this, Inicio.class);
        startActivity(i);
    }
    public void TTottus(View v){
        Intent i = new Intent(this, Inicio.class);
        startActivity(i);
    }
}