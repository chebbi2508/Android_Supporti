package tn.esprit.android_supporti.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import tn.esprit.android_supporti.R;

public class Boutons extends AppCompatActivity {

    Button eq,act;

    @Override
    protected void onStop() {
        super.onStop();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.boutons);

eq = findViewById(R.id.eq);
act = findViewById(R.id.act);


        eq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityEq();
                // Toast.makeText(MainActivity.this, "test", Toast.LENGTH_SHORT).show();
            }
        });

        act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityAct();
                // Toast.makeText(MainActivity.this, "test", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void  openActivityEq(){
        Intent intent = new Intent(this, ReadEquipe.class);
        startActivity(intent);
    }
    public void  openActivityAct(){
        Intent intent = new Intent(this, Accessoire.class);
        startActivity(intent);
    }
}
