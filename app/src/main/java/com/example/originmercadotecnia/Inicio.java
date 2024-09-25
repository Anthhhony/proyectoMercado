package com.example.originmercadotecnia;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Inicio extends AppCompatActivity {

    Dialog dialog;
    Button btnModalExit, btnModalLog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inicio);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dialog = new Dialog(Inicio.this);
        dialog.setContentView(R.layout.modal);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        btnModalExit = dialog.findViewById(R.id.btnModalExit);
        btnModalLog = dialog.findViewById((R.id.btnModalLog));

        btnModalExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnModalLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle enviaValor = new Bundle();
                enviaValor.putString("presupuesto", "0");
                Intent i = new Intent(Inicio.this, Mercaderia.class);
                i.putExtras(enviaValor);
                Toast.makeText(Inicio.this, "presupuesto", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                startActivity(i);
            }
        });


        Button b = (Button) findViewById(R.id.btnEmpezar);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    dialog.show();


            }
        });

    }


    public void Presupuesto(View v){
        Intent i = new Intent(this, Presupuesto.class);
        startActivity(i);
    }
    public void MercaInfo(View v){
        Intent i = new Intent(this, descripcion_app.class);
        startActivity(i);
    }
    public void Historial(View v){
        Intent i = new Intent(this, historial.class);
        startActivity(i);
    }

}