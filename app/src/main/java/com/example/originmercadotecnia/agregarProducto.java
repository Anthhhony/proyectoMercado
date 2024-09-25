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

public class agregarProducto extends AppCompatActivity {

    TextView Saldo, Producto, TProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_agregar_producto);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Bundle paquete = getIntent().getExtras();
        if (paquete != null) {
            String valor = paquete.getString("saldo");
            String info = paquete.getString("informacion");
            Toast.makeText(this, valor, Toast.LENGTH_SHORT).show();
            Saldo = findViewById(R.id.saldo2);
            Producto = findViewById(R.id.InfoProduct);
            TProducto = findViewById(R.id.TitleInfoProduct);
            Saldo.setText(valor);
            TProducto.setText(info);
            Producto.setText(info);

        };
    }
    public void Add(View v){
        Intent i = new Intent(this, Mercaderia.class);
        startActivity(i);
    }
    public void Quit(View v){
        Intent i = new Intent(this, Mercaderia.class);
        startActivity(i);
    }
    }

