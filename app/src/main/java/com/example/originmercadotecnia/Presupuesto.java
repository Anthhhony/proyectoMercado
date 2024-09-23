package com.example.originmercadotecnia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Presupuesto extends AppCompatActivity {

    EditText etEnviar;
    Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_presupuesto);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnEnviar = findViewById(R.id.btnPresupuesto);
        etEnviar = findViewById(R.id.editPresupuesto);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle enviaValor = new Bundle();
                enviaValor.putString("presupuesto", etEnviar.getText().toString());
                Intent i = new Intent(Presupuesto.this, Mercaderia.class);
                i.putExtras(enviaValor);
                Toast.makeText(Presupuesto.this, etEnviar.getText(), Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });
    }

}