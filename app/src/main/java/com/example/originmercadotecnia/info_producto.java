package com.example.originmercadotecnia;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class info_producto extends AppCompatActivity {

    TextView Saldo, Producto, TProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_info_producto);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Saldo = findViewById(R.id.saldo4);
        Bundle paquetem = getIntent().getExtras();
        if (paquetem!=null){
            String valor = paquetem.getString("saldo");
            int valorint = Integer.parseInt(valor);
            if (valorint<0) {
                Saldo.setTextColor(Color.RED);
                Saldo.setText(valor);
            }
        }


        Bundle paquete = getIntent().getExtras();
        if (paquete != null) {
            String valor = paquete.getString("saldo");
            String info = paquete.getString("informacion");
            Toast.makeText(this, valor, Toast.LENGTH_SHORT).show();
            Saldo = findViewById(R.id.saldo4);
            Producto = findViewById(R.id.InfoProduct2);
            TProducto = findViewById(R.id.TitleInfoProduct2);
            Saldo.setText(valor);
            TProducto.setText(info);
            Producto.setText(info);

        };
        Button b = (Button) findViewById(R.id.btnProductoQuitar);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent i = new Intent(info_producto.this, Mercaderia.class);
                    Bundle paquete = getIntent().getExtras();
                    if(paquete!=null){
                        //String info = paquete.getString("informacion");
                        String valor = paquete.getString("saldo");
                        Saldo = findViewById(R.id.saldo4);
                        int saldoInt = Integer.parseInt(valor);
                        int precio = 300;
                        int valornuevo = saldoInt + precio;
                        String valorNuevoStr = String.valueOf(valornuevo);
                        i.putExtra("presupuesto", valorNuevoStr);
                        //i.putExtra("informacion", valorNuevoStr);
                        startActivity(i);


                    }
            }
        });
    }
    public void Volver(View v){
        Intent i = new Intent(info_producto.this, Mercaderia.class);
        i.putExtra("presupuesto", Saldo.getText().toString());
        startActivity(i);
    }
}