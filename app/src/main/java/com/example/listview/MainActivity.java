package com.example.listview;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    // Model: Record (intents=puntuación, nom)
    class Record {
        public int intents;
        public String nom;
        public int imagenResource;

        public Record(int _intents, String _nom, int _imagenResource) {
            intents = _intents;
            nom = _nom;
            imagenResource = _imagenResource;
        }
    }


    ArrayList<Record> records;

    ArrayAdapter<Record> adapter;


    String[] nombres = {
            "Juan", "María", "Pedro", "Ana", "Luis", "Laura", "Carlos", "Sofía", "Diego", "Elena",
            "Javier", "Isabel", "Pablo", "Marta", "Alejandro", "Andrés", "Carmen", "Roberto", "Lucía", "Sara"
    };

    String[] apellidos = {
            "Gómez", "Pérez", "López", "Rodríguez", "Fernández", "Martínez", "Sánchez", "González",
            "Ramírez", "Torres", "Díaz", "Ortega", "Vargas", "Jiménez", "Ruíz", "García", "Hernández",
            "Romero", "Molina"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        records = new ArrayList<Record>();

        adapter = new ArrayAdapter<Record>( this, R.layout.list_item, records )
        {
            @Override
            public View getView(int pos, View convertView, ViewGroup container)
            {
                if( convertView == null ) {
                    convertView = getLayoutInflater().inflate(R.layout.list_item, container, false);
                }
                ((TextView) convertView.findViewById(R.id.nom)).setText(getItem(pos).nom);
                ((TextView) convertView.findViewById(R.id.intents)).setText(Integer.toString(getItem(pos).intents));
                ((ImageView) convertView.findViewById(R.id.imagen)).setImageResource(getItem(pos).imagenResource);
                return convertView;
            }
        };


        ListView lv = (ListView) findViewById(R.id.recordsView);
        lv.setAdapter(adapter);
        Button ordenar = (Button) findViewById(R.id.ordenar);
        ordenar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ordenarListaAlfabeticamente();
            }
        });

        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numIntentos = new Random().nextInt(101);
                String nombreAleatorio = nombres[new Random().nextInt(nombres.length)];
                String apellidoAleatorio = apellidos[new Random().nextInt(apellidos.length)];
                String nombreCompleto = nombreAleatorio + " " + apellidoAleatorio;


                int imagenAleatoria = new Random().nextInt(3);

                int[] imagenes = {R.drawable.img, R.drawable.img2, R.drawable.img3};

                records.add(new Record(numIntentos, nombreCompleto, imagenes[imagenAleatoria]));
                adapter.notifyDataSetChanged();
            }
        });
    }
    private void ordenarListaAlfabeticamente() {
        Collections.sort(records, new Comparator<Record>() {
            public int compare(Record record1, Record record2) {
                return record1.nom.compareTo(record2.nom);
            }
        });

        adapter.notifyDataSetChanged(); // Notifica al adaptador sobre los cambios en la lista
    }
}
