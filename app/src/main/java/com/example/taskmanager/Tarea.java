package com.example.taskmanager;

import java.util.Date;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Tarea  {
    @PrimaryKey(autoGenerate = true)
    int id;
    private String nombre;
    private String descripcion;
    private String fechaLimite;
    private boolean completada;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(String fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }


}
