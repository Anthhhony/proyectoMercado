package com.example.originmercadotecnia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class notas extends AppCompatActivity {

    private ArrayList<Nota_ind> listado;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notas);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        listado = new ArrayList<>();

        ListView l = (ListView) findViewById(R.id.listaNotas);
        Adaptador_notas adaptador = new Adaptador_notas(this, cargar_datos(new FirebaseCallback() {
            @Override
            public void onCallback(ArrayList<Nota_ind> listado) {
                // Aquí puedes actualizar la interfaz de usuario, el adaptador o la lista
                Adaptador_notas adaptador = new Adaptador_notas(notas.this, listado);
                ListView l = findViewById(R.id.listaNotas);
                l.setAdapter(adaptador);
            }
        }));
        l.setAdapter(adaptador);
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtener el documento en la posición seleccionada
                Nota_ind notaSeleccionada = listado.get(position);


                // Ejemplo: Iniciar una nueva actividad con los datos de la nota
                Intent intent = new Intent(notas.this, nota.class);
                intent.putExtra("titulo", notaSeleccionada.getNombre());
                intent.putExtra("descripcion", notaSeleccionada.getDescripcion());
                Toast.makeText(notas.this, notaSeleccionada.getNombre(), Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });


    }


    public ArrayList<Nota_ind> cargar_datos(FirebaseCallback callback){
        listado.clear();
        db.collection("Notas")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                listado.add(new Nota_ind(document.getString("TituloNota"), ""));
                            }
                            callback.onCallback(listado);

                        } else {
                            Toast.makeText(notas.this, "No se pudo cargar", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        return listado;
    }
    public interface FirebaseCallback {
        void onCallback(ArrayList<Nota_ind> listado);
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
    public void nota(View v){
        Intent i = new Intent(this, nota.class);
        startActivity(i);
    }
    public void Volver(View v){
        Intent intent = new Intent(this, Inicio.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();  // Finaliza Activity1
    }
}