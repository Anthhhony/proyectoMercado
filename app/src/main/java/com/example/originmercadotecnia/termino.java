package com.example.originmercadotecnia;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class termino extends AppCompatActivity {

    TextView SaldoF, textAhorro;
    ImageView Symbol;

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
            textAhorro = findViewById(R.id.textAhorrado);
            Symbol = findViewById(R.id.SymbolDolarAhorrado);
            String p = "Perdiste:";
            int valorint = Integer.parseInt(valor);
            if (valorint<0){
                SaldoF.setTextColor(Color.RED);
                Symbol.setColorFilter(Color.RED);
                int valorintSaldo = Math.abs(valorint);
                String valorStrSaldo = String.valueOf(valorintSaldo);
                SaldoF.setText(valorStrSaldo);
                textAhorro.setText(p);
            }
            else{
                SaldoF.setText(valor);
            }

        };
    };
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            );
        }
    }
    public void Volver(View v){
        Intent i = new Intent(this, Inicio.class);
        startActivity(i);
    }
};