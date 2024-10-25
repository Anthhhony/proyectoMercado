package com.example.originmercadotecnia;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.ArrayList;

public class Mercaderia extends AppCompatActivity {

    private ArrayList<Producto> listado;


    TextView Saldo;
    Button btn_scan;
    Dialog dialog, dialog2, dialog3;
    Button btnModalLog4, btnModalExit3, btnModalLog5, btnModalExit4, btnModalLog6, btnModalExit5;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mercaderia);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        btn_scan = findViewById(R.id.btn_scan);
        btn_scan.setOnClickListener(v -> {
            scanCode();
        });

        dialog = new Dialog(Mercaderia.this);
        dialog.setContentView(R.layout.modalmercasalir);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        btnModalLog4 = dialog.findViewById(R.id.btnModalLog4);
        btnModalExit3 = dialog.findViewById(R.id.btnModalExit3);

        dialog2 = new Dialog(Mercaderia.this);
        dialog2.setContentView(R.layout.modal_mercado_termino);
        dialog2.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog2.setCancelable(false);
        btnModalLog5 = dialog2.findViewById(R.id.btnModalLog5);
        btnModalExit4 = dialog2.findViewById(R.id.btnModalExit4);

        dialog3 = new Dialog(Mercaderia.this);
        dialog3.setContentView(R.layout.modal_presupuesto_pasado);
        dialog3.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog3.setCancelable(false);
        btnModalLog6 = dialog3.findViewById(R.id.btnModalLog6);
        btnModalExit5 = dialog3.findViewById(R.id.btnModalExit5);


        Saldo = findViewById(R.id.saldo);
        Bundle recibeDatos = getIntent().getExtras();
        String valor = recibeDatos.getString("presupuesto");
        Saldo.setText(valor);
        int valorint = Integer.parseInt(valor);
        if (valorint<0){
            Saldo.setTextColor(Color.RED);
            Saldo.setText(valor);
            dialog3.show();
        }
        else{
            Saldo.setTextColor(Color.GREEN);
        }




        btnModalLog4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Mercaderia.this, Inicio.class);
                startActivity(i);
                dialog.dismiss();
            }
        });
        btnModalExit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnModalExit4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog2.dismiss();
            }
        });

        btnModalLog5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Mercaderia.this, termino.class);
                i.putExtra("saldo", Saldo.getText().toString());
                startActivity(i);
                dialog2.dismiss();
            }
        });
        btnModalExit5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog3.dismiss();
            }
        });

        btnModalLog6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Mercaderia.this, termino.class);
                i.putExtra("saldo", Saldo.getText().toString());
                startActivity(i);
                dialog3.dismiss();
            }
        });


        ImageView b = (ImageView) findViewById(R.id.btnMercaderiaSalir);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
        FloatingActionButton t = (FloatingActionButton) findViewById(R.id.btnTermino);
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog2.show();
            }
        });

        ListView l = (ListView) findViewById(R.id.ListaProducto);
        Adaptador adaptador = new Adaptador(this, cargar_datos());
        l.setAdapter(adaptador);

    };

    private void scanCode() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Enfoque correctamente");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLauncher.launch(options);
    }

    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(), result -> {
        if (result.getContents() != null) {
            Intent i = new Intent(this, agregarProducto.class);
            i.putExtra("informacion", result.getContents());
            i.putExtra("saldo", Saldo.getText().toString());
            startActivity(i);

        }
    });

    public ArrayList<Producto> cargar_datos(){
        Bundle recibeDatos = getIntent().getExtras();
        if (recibeDatos!=null){
            String info = recibeDatos.getString("informacion");
            String v = recibeDatos.getString("vproductos");

            if (v!=null){

                if ((v.equals("delete"))) {
                    Toast.makeText(this, "eliminado", Toast.LENGTH_SHORT).show();
                    listado = new ArrayList<>();
                    return listado;
                }
                else if ((v.equals("none"))) {
                    listado = new ArrayList<>();
                    return listado;
                }
                else{
                    int saldoInt = Integer.parseInt(v);
                    listado = new ArrayList<>();
                    listado.add(new Producto(saldoInt,info));
                    return listado;
                }

            }
            else{
                listado = new ArrayList<>();
                return listado;
            }

            //int saldoInt = Integer.parseInt(v);

        }
        else {
            listado = new ArrayList<>();
            return listado;
        }
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
    public void Volver(View v){
        Intent i = new Intent(this, Inicio.class);
        startActivity(i);
    }
    public void producto(View v){
        Bundle recibeDatos = getIntent().getExtras();
        String info = recibeDatos.getString("informacion");
        String vp = recibeDatos.getString("vproductos");
        Intent i = new Intent(this, info_producto.class);
        i.putExtra("saldo", Saldo.getText().toString());
        i.putExtra("informacion", info);
        i.putExtra("valor", vp);
        //i.putExtra("vproducto",);
        startActivity(i);
    }
    public void notas(View v){
        Intent i = new Intent(this, notas.class);
        startActivity(i);
    }

};