package tn.esprit.android_supporti.controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tn.esprit.android_supporti.R;
import tn.esprit.android_supporti.model.Equipe;
import tn.esprit.android_supporti.model.User;
import tn.esprit.android_supporti.service.ApiClient;

public class ReadEquipe extends AppCompatActivity implements EquipeAdapter.ClickedItem{
    private SharedPreferences sharedPreferences;
    public static final String EXTRA_TEXT = "tn.esprit.android_supporti.file";
    Context mContext;
    int id_eq;
    RecyclerView recyclerView;
    EquipeAdapter equipeAdapter;

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
        setContentView(R.layout.equipe);
        sharedPreferences = getSharedPreferences(EXTRA_TEXT, MODE_PRIVATE);
        if(sharedPreferences.contains("User")){

            Gson gson = new Gson();
            String json = sharedPreferences.getString("User","");
            User user = gson.fromJson(json,User.class);
            id_eq=user.getEquipe_favorite();
            if(id_eq !=0){
                Intent intent = new Intent(ReadEquipe.this,MainActualite.class);
                startActivity(intent);
            }
        }

        recyclerView=findViewById(R.id.recycleview);
        equipeAdapter=new EquipeAdapter(this::clickedUser);

        getListEquipe();
    }
    public void getListEquipe(){
        Call<List<Equipe>> equipeList= ApiClient.getUserService().getEquipes();
        equipeList.enqueue(new Callback<List<Equipe>>() {
            @Override
            public void onResponse(Call<List<Equipe>> call, Response<List<Equipe>> response) {
                if (response.isSuccessful()){
                    List<Equipe> equiperesponse=response.body();
                    equipeAdapter=new EquipeAdapter(mContext,equiperesponse);
                    equipeAdapter.setData(equiperesponse);
                    recyclerView.setAdapter(equipeAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(mContext));

                }
            }

            @Override
            public void onFailure(Call<List<Equipe>> call, Throwable t) {
                Log.e("failure",t.getLocalizedMessage());
            }
        });
    }


    @Override
    public void clickedUser(Equipe equipe) {
        Log.e("clicked user",equipe.toString());
    }
}