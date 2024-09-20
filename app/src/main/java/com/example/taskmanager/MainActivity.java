package com.example.taskmanager;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.DateKeyListener;
import android.view.View;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.media3.database.DatabaseProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Dao;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.example.taskmanager.R;
import com.google.android.material.button.MaterialButton;


public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> nombre,descripcion, fechalimite,completado;
    dbh db;
    UnAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, new SwipeFragment())
                .commit();

        db = new dbh(this);
        nombre = new ArrayList<>();
        descripcion = new ArrayList<>();
        fechalimite = new ArrayList<>();
        completado = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);
        adapter = new UnAdapter(this,nombre,descripcion,fechalimite,completado);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displaydata();

        MaterialButton aggboton = findViewById(R.id.AgregarTarea);
        aggboton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AggActivity.class));
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    protected void onResume() {
        super.onResume();
        // Limpiar los arrays antes de llenarlos de nuevo
        nombre.clear();
        descripcion.clear();
        fechalimite.clear();
        completado.clear();

        // Volver a cargar los datos de la base de datos
        displaydata();

        // Notificar al adaptador que los datos han cambiado
        adapter.notifyDataSetChanged();
    }

    private void displaydata(){
        Cursor cursor = db.getdata();
        if(cursor.getCount()==0){
            Toast.makeText(this, "No hay datos", Toast.LENGTH_LONG).show();
        }else{
            while (cursor.moveToNext()){nombre.add(cursor.getString(0));
                descripcion.add(cursor.getString(1));
            fechalimite.add(cursor.getString(2));
            completado.add(String.valueOf(cursor.getInt(3)));}
        }

    }
}