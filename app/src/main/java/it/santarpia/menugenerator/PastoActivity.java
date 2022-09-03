package it.santarpia.menugenerator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PastoActivity extends AppCompatActivity {
    ConstraintSet set = new ConstraintSet();
    ConstraintLayout constraintLayout;
    LinearLayout llIngredienti;
    TextView tvNome, tvIngredienti, tvRicetta;
    String pasto, nome, ingredienti, ricetta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasto);

        Log.d("kiwi", getClass().getSimpleName());

        constraintLayout = findViewById(R.id.main);
        llIngredienti = findViewById(R.id.llIngredienti);
        tvNome = findViewById(R.id.tvNome);
        tvIngredienti = findViewById(R.id.tvIngredienti);
        tvRicetta = findViewById(R.id.tvRicetta);

        //Setto la view con il pasto
        pasto = getIntent().getStringExtra("pasto");

        nome = pasto.substring(0, pasto.indexOf("("));
        ingredienti = pasto.substring(pasto.indexOf("(") + 1, pasto.indexOf(")") + 1);

        Log.d("kiwi", getClass().getSimpleName() + ingredienti);

        while(ingredienti.contains(")")) {
            String ingrediente;

            if(ingredienti.contains(","))
                ingrediente = ingredienti.substring(0, ingredienti.indexOf(','));
            else
                ingrediente = ingredienti.substring(0, ingredienti.indexOf(')'));
            int dose = 0;

            Log.d("kiwi", getClass().getSimpleName() + ingredienti);

            if (ingrediente.contains(":")) {
                if(ingrediente.contains("#"))
                    dose = Integer.parseInt(ingrediente.substring(ingrediente.indexOf("#") + 1));
                else
                    dose = Integer.parseInt(ingrediente.substring(ingrediente.indexOf(":") + 1));

                ingrediente = ingrediente.substring(0, ingrediente.indexOf(":"));
            }

            TextView tvNew = new TextView(this);

            if (dose != 0)
                tvNew.setText("> " + ingrediente + ": " + dose + "g");
            else
                tvNew.setText("> " + ingrediente);

            llIngredienti.addView(tvNew);

            if (ingredienti.contains(","))
                ingredienti = ingredienti.substring(ingredienti.indexOf(',') + 1, ingredienti.indexOf(')') + 1);
            else
                break;

//            Log.d("kiwi", getClass().getSimpleName() + "11111" + ingredienti);
        }

        if(pasto.contains("=")) {
            ricetta = pasto.substring(pasto.indexOf("=") + 1, pasto.indexOf("/") - 1);
            tvRicetta.setText(ricetta);
        }

        tvNome.setText(nome);

        //
    }
}