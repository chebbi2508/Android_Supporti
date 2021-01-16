package tn.esprit.android_supporti.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import tn.esprit.android_supporti.R;
import tn.esprit.android_supporti.model.User;
import tn.esprit.android_supporti.service.ApiClient;
import tn.esprit.android_supporti.service.RetrofitClient;
import tn.esprit.android_supporti.service.UserClient;

public class MainActivity extends AppCompatActivity {

    UserClient myApi;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    EditText email,password;
    Button btn_login;
    Button btn_register;

    private SharedPreferences sharedPreferences;
    public static final String EXTRA_TEXT = "tn.esprit.android_supporti.file";


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
        sharedPreferences = getSharedPreferences(EXTRA_TEXT, MODE_PRIVATE);
        if(sharedPreferences.contains("User")){
            openActivity2();
        }
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
        //sharedPreferences = getSharedPreferences("testt", Context.MODE_PRIVATE);
        //sharedPreferences
       // sharedPreferences = getSharedPreferences("testt", Context.MODE_PRIVATE);
        //email.setText(sharedPreferences.getString("test", ""));
        //password.setText(sharedPreferences.getString("test1", ""));
        //Actions
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.getText().toString().equals("")||password.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Vérifier Votre Données",Toast.LENGTH_SHORT).show();

                }else{
                    /*
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("test", email.getText().toString());
                    editor.putString("test1", password.getText().toString());
                    editor.apply();
                    */
                     
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
    public void getListUser(){
        Call<List<User>> userList= ApiClient.getUserService().getUsers();
        userList.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.isSuccessful()){
                    List<User> userresponse=response.body();

                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }
    //functions
    private void loginUser(String email, String password) {
        User userreq = new User();
        User userres = new User();
        userreq.setEmail_user(email);
        userreq.setPassword_user(password);
        Call<User>userCall = ApiClient.getUserService().loginUserr(userreq);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                System.out.println(response.body().getId_user());
                if(response.body().getNom_user()==null){
                    Toast.makeText(MainActivity.this,"Account not found",Toast.LENGTH_SHORT);

                }
                if (response.isSuccessful()){
                    SharedPreferences.Editor preferencesEditor = sharedPreferences.edit();
                   User userr= response.body();

                    Gson gson = new Gson();

                    String json = gson.toJson(userr);
                    System.out.println(json.toString());
                    preferencesEditor.putString("User", json);
                    preferencesEditor.apply();
                    if(response.body().getEquipe_favorite()==0)
                    openActivity2();
                    else {
                        Intent i = new Intent(MainActivity.this, MainActualite.class);
                        startActivity(i);
                    }

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this,"check our netword conn",Toast.LENGTH_SHORT);

            }
        });
        /*User u = new User();
        compositeDisposable.add(myApi.loginUser(email,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        //Si el JSON String devuelto contiene encrypted_password quiere decir que se ha obtenido el user correctamente desde la DB
                        if(s.contains("password_user")){
                            Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                           // Call<List<User>> userList= ApiClient.getUserService().getUsers();
                            System.out.print("teeeeeeeeeeeeeeeeeeeeeeeeeeessssssttt"+email);





                        }
                        else
                            Toast.makeText(MainActivity.this, "Error: " +s, Toast.LENGTH_SHORT).show(); //Just show error From API
                    }
                }));*/

    }
    public void  openActivity2(){
        Intent intent = new Intent(this, ReadEquipe.class);
        startActivity(intent);
    }
    public void loadClientData(){
        //sharedPreferences =getApplicationContext().getSharedPreferences("CurrentUser", Context.MODE_PRIVATE);

    }

}