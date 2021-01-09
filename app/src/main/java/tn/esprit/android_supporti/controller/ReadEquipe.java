package tn.esprit.android_supporti.controller;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tn.esprit.android_supporti.R;
import tn.esprit.android_supporti.model.Equipe;
import tn.esprit.android_supporti.service.ApiClient;

public class ReadEquipe extends AppCompatActivity implements EquipeAdapter.ClickedItem{

    Context mContext;
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