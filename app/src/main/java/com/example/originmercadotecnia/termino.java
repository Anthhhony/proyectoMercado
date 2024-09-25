package com.example.originmercadotecnia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class termino extends AppCompatActivity {

    TextView SaldoF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_termino);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Bundle paquete = getIntent().getExtras();
        if (paquete != null) {
            String valor = paquete.getString("saldo");
            Toast.makeText(this, valor, Toast.LENGTH_SHORT).show();
            SaldoF = findViewById(R.id.saldoFinal);
            SaldoF.setText(valor);

        };
    };
    public void Volver(View v){
        Intent i = new Intent(this, Inicio.class);
        startActivity(i);
    }
};