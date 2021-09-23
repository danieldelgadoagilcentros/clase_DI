package com.example.ui_basica;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Persona> listaAgenda;
    int indice;

    EditText nombre, apellido, telefono;
    Button izda, dcha, guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaAgenda = new LinkedList<>();

        listaAgenda.add(new Persona("Ana", "Romero", "666666666"));
        listaAgenda.add(new Persona("Juan", "Ramero", "666666667"));
        listaAgenda.add(new Persona("Tolomeo", "Romerales", "675666666"));
        indice = 0;

        nombre = (EditText) findViewById(R.id.nombre);

    }

    private class Persona{
        String nombre, apellido, telf;

        Persona(String nombre, String apellido, String telf){
            this.nombre = nombre;
            this.apellido = apellido;
            this.telf = telf;
        }

        String[] getDatos(){
            return new String[]{nombre, apellido, telf};
        }
    }
}




