package tn.esprit.android_supporti.controller;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tn.esprit.android_supporti.R;
import tn.esprit.android_supporti.model.Accessoire;
import tn.esprit.android_supporti.model.Equipe;
import tn.esprit.android_supporti.service.RetrofitClient2;

public class ReadEquipe extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.equipe);

        listView = findViewById(R.id.listeq);

        //calling the method to display the heroes
        getEquipe();
    }

    private void getEquipe() {
        Call<List<Equipe>> call = RetrofitClient2.getInstance().getMyApi().getEquipes();
        call.enqueue(new Callback<List<Equipe>>() {
            @Override
            public void onResponse(Call<List<Equipe>> call, Response<List<Equipe>> response) {
                List<Equipe> heroList = response.body();

                //Creating an String array for the ListView
                String[] heroes = new String[heroList.size()];

                //looping through all the heroes and inserting the names inside the string array
                for (int i = 0; i < heroList.size(); i++) {
                    heroes[i] = heroList.get(i).getNom_eq();
                }

                //displaying the string array into listview
                listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, heroes));
            }

            @Override
            public void onFailure(Call<List<Equipe>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
