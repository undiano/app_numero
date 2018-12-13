package com.example.cur97.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private int range;
    public static List<Jugador> jugador = new ArrayList<>();
    private int Attempts  = 0;
    private String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            startApp();
        }

    private void startApp() {
        preguntarNombre();
        final Button button = findViewById(R.id.button);
        final Button buttonRecord = findViewById(R.id.button2);
        range = generateRandom();
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                adivinarNumero();
            }
        });

        buttonRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tablaRecord();
            }
        });

    }
    private int generateRandom() {

        int Number = (int) (Math.random() * 100 + 1);

        return Number;
    }

    public void adivinarNumero() {

        final EditText editText = findViewById(R.id.editText);
        String st = String.valueOf(editText.getText());
        int number = Integer.parseInt(st);
        if (number > range) {
            Attempts++;
            Context context = getApplicationContext();
            CharSequence text = "Es un numero mas pequeño";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            editText.setText("");
        } else if (number < range) {
            Context context = getApplicationContext();
            CharSequence text = "Es un numero mas grande";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            editText.setText("");
            Attempts++;
        } else if (number == range) {
            jugador.add(new Jugador(name,Attempts));
            Jugador j1 = new Jugador(name,Attempts);
            escribirFichero(j1);
            Context context = getApplicationContext();
            CharSequence text = "Has acertado";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            Attempts = 0;
            range = generateRandom();
            Attempts = 0;
            preguntarNombre();
        }
    }

    public void tablaRecord() {
        Intent i = new Intent(this, FameActivity.class);

        startActivity(i);
    }

    private String preguntarNombre(){
        final Dialog dialog = new Dialog(MainActivity.this);

        dialog.setContentView(R.layout.dialogo);
        dialog.setTitle("Registro Usuarios");
        dialog.show();
        Button register = dialog.findViewById(R.id.butonDialog);
        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText textName = dialog.findViewById(R.id.etName);
                name = textName.getText().toString();
                dialog.dismiss();
            }
        });
        return name;
    }

    private void escribirFichero(Jugador j) {
        try {
            OutputStreamWriter fout =
                    new OutputStreamWriter(
                            openFileOutput("players.txt",Context.MODE_APPEND));

            fout.write(j.getName() + "," + j.getIntentos());
            fout.append("\r\n");
            fout.close();

        } catch (Exception  e) {
            e.printStackTrace();
        }
    }
}