package com.example.originmercadotecnia;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class agregarProducto extends AppCompatActivity {

    TextView Saldo, Producto, TProducto;
    EditText etText;
    Dialog dialog;
    Button btnModalLog3;


    String[] item = {"UNIMARC", "LIDER", "JUMBO", "TOTTUS"};


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
        etText = findViewById(R.id.editPresupuesto2);

        dialog = new Dialog(agregarProducto.this);
        dialog.setContentView(R.layout.modal1btn);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        btnModalLog3 = dialog.findViewById((R.id.btnModalLog3));

        Saldo = findViewById(R.id.saldo2);
        Bundle paquetem = getIntent().getExtras();
        if (paquetem!=null){
            String valor = paquetem.getString("saldo");
            int valorint = Integer.parseInt(valor);
            if (valorint<0) {
                Saldo.setTextColor(Color.RED);
                Saldo.setText(valor);
            }
        }



        btnModalLog3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        Bundle paquete = getIntent().getExtras();
        if (paquete != null) {
            String valor = paquete.getString("saldo");
            String info = paquete.getString("informacion");
            Saldo = findViewById(R.id.saldo2);
            Producto = findViewById(R.id.InfoProduct);
            TProducto = findViewById(R.id.TitleInfoProduct);
            Saldo.setText(valor);
            TProducto.setText(info);
            Producto.setText(info);

        };
        Button b = (Button) findViewById(R.id.btnAgregarProducto);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etText.getText().toString().isEmpty()) {
                    dialog.show();

                }
                else{
                    Intent i = new Intent(agregarProducto.this, Mercaderia.class);
                    i.putExtra("presupuesto", etText.getText().toString());
                    i.putExtra("informacion", etText.getText().toString());
                    Bundle paquete = getIntent().getExtras();
                    if(paquete!=null){
                        String valor = paquete.getString("saldo");
                        String info = paquete.getString("informacion");
                        Saldo = findViewById(R.id.saldo2);
                        int saldoInt = Integer.parseInt(valor);
                        int saldoresta = Integer.parseInt(etText.getText().toString());
                        int valornuevo = saldoInt - saldoresta;
                        String valorNuevoStr = String.valueOf(valornuevo);

                        i.putExtra("presupuesto", valorNuevoStr);
                        i.putExtra("informacion", info);
                        i.putExtra("vproductos", etText.getText().toString());
                        startActivity(i);
                    }


                }
            }
        });
    }
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

        public void Quit(View v){
            Intent i = new Intent(agregarProducto.this, Mercaderia.class);
            i.putExtra("presupuesto", Saldo.getText().toString());
            startActivity(i);
        }
    }

