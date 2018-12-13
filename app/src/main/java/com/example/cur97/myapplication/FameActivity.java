package com.example.cur97.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class FameActivity extends Activity {
    private List<Jugador> players;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fame_activity);
        showPlayers();
    }
    private void showPlayers() {
        players = new ArrayList<>();
        readFile();
        final TextView tablaRecord = findViewById(R.id.record);
        tablaRecord.setText("");
        if (players.size() > 0) {
            Collections.sort(players);
            for (Jugador jug : players) {
                tablaRecord.setText(tablaRecord.getText() + "Nombre: "+jug.getName()+"    "+"Intentos: "+jug.getIntentos()+"\n");
            }
        } else {
            tablaRecord.setText(tablaRecord.getText() + "No hay datos guardados");
        }
    }
        private void readFile(){
            try
            {
                BufferedReader fin =
                        new BufferedReader(
                                new InputStreamReader(
                                        openFileInput("players.txt")));

                String texto;
                while((texto = fin.readLine())!=null){
                    String[] cadena = texto.split(",");
                    players.add(new Jugador(cadena[0],Integer.parseInt(cadena[1])));
                }
                fin.close();
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
}
