package tn.esprit.android_supporti.controller;

import android.os.Bundle;
import android.util.Log;
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
import tn.esprit.android_supporti.service.RetrofitClient;
import tn.esprit.android_supporti.service.RetrofitClient2;

public class ReadAccessoire extends AppCompatActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_accessoire);

       // listView = findViewById(R.id);

        //calling the method to display the heroes
        getAccessoires();
    }


    private void getAccessoires() {
        Call<List<Accessoire>> call = RetrofitClient2.getInstance().getMyApi().getAccessoires();
        call.enqueue(new Callback<List<Accessoire>>() {
            @Override
            public void onResponse(Call<List<Accessoire>> call, Response<List<Accessoire>> response) {
                List<Accessoire> heroList = response.body();

                //Creating an String array for the ListView
                String[] heroes = new String[heroList.size()];

                //looping through all the heroes and inserting the names inside the string array
                for (int i = 0; i < heroList.size(); i++) {
                    heroes[i] = heroList.get(i).getNom_acc();
                   // heroes[i] = heroList.get(i).getDesc_acc();
                    //heroes[i] = heroList.get(i).getPrix_acc();
                   // heroes[i] = heroList.get(i).getImage_acc();

                }

                //displaying the string array into listview
                listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, heroes));
            }

            @Override
            public void onFailure(Call<List<Accessoire>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }






}
