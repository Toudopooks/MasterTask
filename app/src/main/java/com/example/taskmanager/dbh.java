package com.example.taskmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dbh extends SQLiteOpenHelper {

    public dbh(Context context) {
        super(context, "tareas.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE tareas (nombre TEXT, descripcion TEXT, fechaLimite TEXT, completada TINYINT DEFAULT 0);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop table if exists tareas");
    }

    public Boolean insertdata(String nombre, String descripcion, String fechalimite, boolean completado) {
        int completadof;
        if (completado == true) {
            completadof = 1;
        } else {
            completadof = 0;
        }
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", nombre);
        contentValues.put("descripcion", descripcion);
        contentValues.put("fechaLimite", fechalimite);
        contentValues.put("completada", completadof);
        long result = DB.insert("tareas", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

    public boolean updateCompletada(String nombreTarea, int completada) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("completada", completada);

        // Actualizar la tarea con el nombre proporcionado
        long result = db.update("tareas", contentValues, "nombre=?", new String[]{nombreTarea});
        return result != -1;  // Devuelve true si se actualizó correctamente
    }

    public Cursor getdata(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from tareas", null);
        return cursor;
    }
}
