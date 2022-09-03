package it.santarpia.menugenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btnPranzo, btnCena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPranzo = findViewById(R.id.btnPranzo);
        btnCena = findViewById(R.id.btnCena);

        btnPranzo.setOnClickListener(this::generaPasto);
        btnCena.setOnClickListener(this::generaPasto);

    }

    public void generaPasto(View view) {
        String nome = (String) view.getTag();

        Log.d("kiwi", getClass().getSimpleName() + ": chiamato " + nome);

        if(nome.equalsIgnoreCase("pranzo")) {
            nome = "pranzo";
            Log.d("kiwi", getClass().getSimpleName() + ": chiamato per " + nome);
        } else if(nome.equalsIgnoreCase("cena")) {
            nome = "cena";
            Log.d("kiwi", getClass().getSimpleName() + ": chiamato per " + nome);
        }

        //Carica il file dalla cartella src/main/assets
        AssetManager am = getApplicationContext().getAssets();
        try {
            InputStream is = am.open(nome);

            Log.d("kiwi", getClass().getSimpleName() + is.toString());

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            String line;
            Random rn = new Random();

            line = reader.readLine();
            int bound = Integer.parseInt(line);

            int rnInt = rn.nextInt(bound);
            int i = 1;

            Log.d("kiwi", getClass().getSimpleName() + ": generato " + rnInt);

            while ((line = reader.readLine()) != null && rnInt != i) {
                i++;
            }

            reader.close();

            Log.d("kiwi", getClass().getSimpleName() + ":\n" + line);

            Intent intent = new Intent(this, PastoActivity.class);
            intent.putExtra("pasto", line);
            startActivity(intent);
        } catch (IOException e) {
            Log.d("kiwi", getClass().getSimpleName() + ": problema con l'apertura del file: " + nome + ": " + e);
        }
    }
}