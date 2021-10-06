package com.example.ui_basica;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    List<Persona> listaAgenda;
    int indice;

    EditText nombre, apellido, telefono;
    Button izda, dcha, guardar;
    Button topIzda, topDcha, borrar;
    private boolean borrable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaAgenda = cargarDeArchivo();
/*
        listaAgenda = new LinkedList<>();
        listaAgenda.add(new Persona("Ana", "Romero", "666666666"));
        listaAgenda.add(new Persona("Juan", "Ramero", "666666667"));
        listaAgenda.add(new Persona("Tolomeo", "Romerales", "675666666"));
*/
        indice = listaAgenda.size();


        //Añadir elementos de la interfaz de usuario.
        nombre = (EditText) findViewById(R.id.nombre);
        apellido = (EditText) findViewById(R.id.apellido);
        telefono = (EditText) findViewById(R.id.telf);

        izda = (Button) findViewById(R.id.izda);
        dcha = (Button) findViewById(R.id.dcha);
        guardar = (Button) findViewById(R.id.guardar);

        topIzda = (Button) findViewById(R.id.topIzda);
        topDcha = (Button) findViewById(R.id.topDcha);
        borrar = (Button) findViewById(R.id.borrar);

        nombre.addTextChangedListener(new myTextChangedListener());
        apellido.addTextChangedListener(new myTextChangedListener());
        telefono.addTextChangedListener(new myTextChangedListener());


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
        topIzda.setEnabled(indice!=0);
        if(indice==listaAgenda.size()) guardar.setEnabled(true);
        topDcha.setEnabled(indice< listaAgenda.size());
        borrar.setEnabled(indice< listaAgenda.size());


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
        if(!guardarEnArchivo(listaAgenda)){
            Log.e("Guardar-->", "Error al guardar" );
        }
    }

    public void borrarBtn(View view) {
        if(borrable)
            listaAgenda.remove(indice);

        actualizaUI();
    }

    public void topIzdaBtn(View view) {
        indice = 0;
        actualizaUI();
    }

    public void topDchaBtn(View view) {
        indice=listaAgenda.size();
        actualizaUI();
    }

    private class myTextChangedListener implements TextWatcher{

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if(indice<listaAgenda.size()) {
                String textoActual0 = String.valueOf(nombre.getText());
                String textoActual1 = String.valueOf(apellido.getText());
                String textoActual2 = String.valueOf(telefono.getText());
                guardar.setEnabled(!(borrable = listaAgenda.get(indice).equals(new Persona(textoActual0,textoActual1,textoActual2))));
                borrar.setText(borrable?"Borrar":"Reset");
            }
        }
    }

    private boolean guardarEnArchivo(List<Persona> listaAgenda){
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            //fos = new FileOutputStream(new File("./archivo.txt"));
            fos = new FileOutputStream(new File(
                    getExternalFilesDir(null),
                    "archivo.txt"
            ));
            out = new ObjectOutputStream(fos);
            out.writeObject(listaAgenda);

            out.close();
            fos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    private List<Persona> cargarDeArchivo(){
        List<Persona> devolver;

        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream(new File(
                    getExternalFilesDir(null),"archivo.txt"
            ));
            in = new ObjectInputStream(fis);
            devolver = (LinkedList<Persona>) in.readObject();
            in.close();
            fis.close();
        } catch (Exception ex) {
            devolver = new LinkedList<Persona>();
            ex.printStackTrace();
        }

        return devolver;
    }

}




