package tn.esprit.android_supporti.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import tn.esprit.android_supporti.R;
import tn.esprit.android_supporti.service.RetrofitClient;
import tn.esprit.android_supporti.service.UserClient;

import static java.lang.Integer.parseInt;

public class Accessoire extends AppCompatActivity {
    UserClient myApi5;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    Button ajouter;
    EditText nom,prix,desc,image;


    @Override
    protected void onStop() {
        compositeDisposable.clear();

        super.onStop();
    }
    @Override
    protected void onDestroy() {
        compositeDisposable.clear();

        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.addaccessoire);

        Retrofit retrofit = RetrofitClient.getInstance();
        myApi5 = retrofit.create(UserClient.class);

        ajouter = (Button) findViewById(R.id.addAcc);
        nom =(EditText) findViewById(R.id.nomtxt);
        prix =(EditText) findViewById(R.id.prixtxt);
        desc=(EditText) findViewById(R.id.desctxt);
        image =(EditText) findViewById(R.id.imagetxt);



        ajouter.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int p = parseInt(prix.getText().toString(), 10);
                // int cat = parseInt( catAnnonce.getText().toString(),10);
                if(nom.getText().toString().equals("")||prix.getText().toString().equals("")||desc.getText().toString().equals("")||image.getText().toString().equals(""))
                {

                    Toast.makeText(Accessoire.this, "Vérifier Votre Données", Toast.LENGTH_SHORT).show();

                   // openActivity();

                }else {
                    ajouterAcc(
                            nom.getText().toString(),
                            desc.getText().toString(),p,
                            image.getText().toString());
                    Toast.makeText(Accessoire.this, "L'accessoire ajouter avec succés", Toast.LENGTH_SHORT).show();
                    nom.setText("");
                    desc.setText("");
                    prix.setText("");
                    image.setText("");
                    openActivityAc();
                }
                // Toast.makeText(Register.this, "Bienvenu à SUPPORTI !", Toast.LENGTH_SHORT).show();

            }
        }));



    }
    public void  openActivityAc(){
        Intent intent = new Intent(this, ReadAccessoire.class);
        startActivity(intent);
    }

    private void ajouterAcc (String nom, String desc,int p,String image)
    {
        compositeDisposable.add(myApi5.ajouterAcc(nom, desc, p, image)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        if (s.contains("enregistré")) {
                            Toast.makeText(Accessoire.this, "Accessoire ajouté Avec Succès", Toast.LENGTH_SHORT).show();
                            // OpenLogin();
                        }

                    }
                })

        );
    }

}
