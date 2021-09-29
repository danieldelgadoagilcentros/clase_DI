package com.example.ui_basica;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
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
        indice = listaAgenda.size();


        //Añadir elementos de la interfaz de usuario.
        nombre = (EditText) findViewById(R.id.nombre);
        apellido = (EditText) findViewById(R.id.apellido);
        telefono = (EditText) findViewById(R.id.telf);

        izda = (Button) findViewById(R.id.izda);
        dcha = (Button) findViewById(R.id.dcha);
        guardar = (Button) findViewById(R.id.guardar);

        actualizaUI();
    }

    private void actualizaUI() {
        if(indice<listaAgenda.size()) {
            nombre.setText(listaAgenda.get(indice).nombre);
            apellido.setText(listaAgenda.get(indice).apellido);
            telefono.setText(listaAgenda.get(indice).telf);
        }
        else{
            nombre.setText("");
            apellido.setText("");
            telefono.setText("");
        }


        izda.setEnabled(indice!=0);
        dcha.setEnabled(indice< listaAgenda.size());
        /********************************/
        /* LO ANTERIOR EQUIVALE A ESTO: */
        /********************************/

        //if(indice==0) izda.setEnabled(false);
        //else izda.setEnabled(true);
        //if(indice== listaAgenda.size()) dcha.setEnabled(false);
        //else dcha.setEnabled(true);
    }

    public void izdaBtn(View view) {
        //indice=(indice+listaAgenda.size()-1)%listaAgenda.size();
        indice--;
        actualizaUI();
    }

    public void dchaBtn(View view) {
        //indice=(indice+1)%listaAgenda.size();
        indice++;
        actualizaUI();
    }

    public void guardarBtn(View view) {
        if(indice== listaAgenda.size()){
            listaAgenda.add(
                    new Persona(
                            String.valueOf(nombre.getText()),
                            String.valueOf(apellido.getText()),
                            String.valueOf(telefono.getText())
                    )
            );
            actualizaUI();
        }
        else{
            //Actualizar un elemento de la lista
            listaAgenda.get(indice).nombre = String.valueOf(nombre.getText());
            listaAgenda.get(indice).apellido = String.valueOf(apellido.getText());
            listaAgenda.get(indice).telf = String.valueOf(telefono.getText());
        }
    }

    private class Persona{

        public String nombre, apellido, telf;

        public Persona(String nombre, String apellido, String telf){
            this.nombre = nombre;
            this.apellido = apellido;
            this.telf = telf;
        }
    }
}




