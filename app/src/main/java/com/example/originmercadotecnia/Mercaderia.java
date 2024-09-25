package com.example.originmercadotecnia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class Mercaderia extends AppCompatActivity {

    TextView Saldo;
    Button btn_scan;

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

        Saldo = findViewById(R.id.saldo);
        Bundle recibeDatos = getIntent().getExtras();
        String valor = recibeDatos.getString("presupuesto");
        Saldo.setText(valor);
    }

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
            Toast.makeText(this, result.getContents(), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, agregarProducto.class);
            i.putExtra("informacion", result.getContents());
            i.putExtra("saldo", Saldo.getText().toString());
            startActivity(i);

        }
    });
    public void notas(View v){
        Intent i = new Intent(this, notas.class);
        startActivity(i);
    }
    public void termino(View v) {
        Intent i = new Intent(this, termino.class);
        i.putExtra("saldo", Saldo.getText().toString());
        startActivity(i);
    };
};