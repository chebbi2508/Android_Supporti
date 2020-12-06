package tn.esprit.android_supporti.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import tn.esprit.android_supporti.R;
import tn.esprit.android_supporti.controller.Register;
import tn.esprit.android_supporti.service.RetrofitClient;
import tn.esprit.android_supporti.service.UserClient;

public class MainActivity extends AppCompatActivity {

    UserClient myApi;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    EditText email,password;
    Button btn_login;
    Button btn_register;

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
        setContentView(R.layout.activity_main);

        //Init API
        Retrofit retrofit = RetrofitClient.getInstance();
        myApi = retrofit.create(UserClient.class);

        //View
        //buttons
        btn_login = findViewById(R.id.loginbtn);
        btn_register = findViewById(R.id.registerbtn);
        //textfields
        email = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);

        //Actions
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.getText().toString().equals("")||password.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Vérifier Votre Données",Toast.LENGTH_SHORT).show();

                }else{
                    loginUser(email.getText().toString(),password.getText().toString());

                }
            }
        });


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity();
               // Toast.makeText(MainActivity.this, "test", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void  openActivity(){
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }
    //functions
    private void loginUser(String email, String password) {
        compositeDisposable.add(myApi.loginUser(email,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        //Si el JSON String devuelto contiene encrypted_password quiere decir que se ha obtenido el user correctamente desde la DB
                        if(s.contains("password_user")){
                            Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                        openActivity2();
                        }
                        else
                            Toast.makeText(MainActivity.this, "Error: " +s, Toast.LENGTH_SHORT).show(); //Just show error From API
                    }
                }));

    }
    public void  openActivity2(){
        Intent intent = new Intent(this, Accessoire.class);
        startActivity(intent);
    }

}