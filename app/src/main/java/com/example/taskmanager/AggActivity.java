package com.example.taskmanager;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

import java.util.Date;

public class AggActivity extends AppCompatActivity {
    EditText nombre, descripcion;
    DatePicker fecha;
    MaterialButton savetask;
    dbh db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agg);

        nombre = findViewById(R.id.nombre);
        descripcion = findViewById(R.id.descripción);
        savetask = findViewById(R.id.savetask);
        fecha = findViewById(R.id.fecha);

        db = new dbh(this);

        savetask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String day, month,year;
                day= String.valueOf(fecha.getDayOfMonth());
                month= String.valueOf(fecha.getMonth());
                year= String.valueOf(fecha.getYear());
                String flimite = day + "/" + month + "/" + year;

                String nombreTXT = nombre.getText().toString();
                String descripcionTXT = descripcion.getText().toString();
                Boolean completadachk = false;

                Boolean checkinsertdata = db.insertdata(nombreTXT, descripcionTXT, flimite, completadachk);

                if(checkinsertdata==true) {
                    Toast.makeText(AggActivity.this, "Nueva Tarea Creada", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(AggActivity.this, "No se pudo crear la tarea compa :(", Toast.LENGTH_LONG).show();
                }
                finish();
            }
        });


    }
}
