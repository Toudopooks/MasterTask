package com.example.taskmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UnAdapter extends RecyclerView.Adapter<UnAdapter.MyViewHolder>{
    private Context context;
    private ArrayList nombre,descripcion,fecha,listo;
    dbh db;

    public UnAdapter(Context context, ArrayList nombre, ArrayList descripcion, ArrayList fecha, ArrayList listo) {
        this.context = context;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.listo = listo;
        this.db = new dbh(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_view,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.nombre.setText(String.valueOf(nombre.get(position)));
        holder.descripcion.setText(String.valueOf(descripcion.get(position)));
        holder.fecha.setText(String.valueOf(fecha.get(position)));

        boolean isCompletada = Integer.parseInt(String.valueOf(listo.get(position))) == 1;
        holder.listo.setChecked(isCompletada);

        // Agregar el listener para actualizar el valor en la base de datos cuando el CheckBox cambie
        holder.listo.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Actualizar la base de datos según el valor de isChecked
            db.updateCompletada((String) nombre.get(position), isChecked ? 1 : 0);
        });

    }

    @Override
    public int getItemCount() {
        return nombre.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nombre,descripcion,fecha;
        CheckBox listo;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.titleoutput);
            descripcion = itemView.findViewById(R.id.descriptionoutput);
            listo = itemView.findViewById(R.id.checkbox);
            fecha = itemView.findViewById(R.id.timeoutput);
        }
    }
}
