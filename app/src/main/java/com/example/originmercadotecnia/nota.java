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

public class nota extends AppCompatActivity {

    Dialog dialog;
    Button btnModalExit2, btnModalLog2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nota);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


        });
        dialog = new Dialog(nota.this);
        dialog.setContentView(R.layout.modal_eliminar_nota);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        btnModalExit2 = dialog.findViewById(R.id.btnModalExit2);
        btnModalLog2 = dialog.findViewById((R.id.btnModalLog2));

        btnModalExit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnModalLog2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(nota.this, notas.class);
                dialog.dismiss();
                startActivity(i);
            }
        });
        Button b = (Button) findViewById(R.id.btnEliminarNote);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
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
    public void notas(View v){
        Intent i = new Intent(this, notas.class);
        startActivity(i);
    }
    public void notasSave(View v){
        Intent i = new Intent(this, notas.class);
        Toast.makeText(this, "La nota se guardo con exito", Toast.LENGTH_SHORT).show();
        startActivity(i);
    }
    public void Volver(View v){
        Intent i = new Intent(this, notas.class);
        startActivity(i);
    }
}