package tn.esprit.android_supporti.controller;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


//import java.util.Date;

import java.util.regex.Pattern;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import tn.esprit.android_supporti.R;
import tn.esprit.android_supporti.service.RetrofitClient;
import tn.esprit.android_supporti.service.UserClient;

import static java.lang.Integer.parseInt;

public class Register extends AppCompatActivity {

    UserClient myApi5;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    Button buttonAjoutAnnonce;
    EditText email,nom,prenom,tlf,password,passwordConfirmer;


    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile(
                    "^"+
                            "?=.*[0-9]"+ //at list one number
                            "?=.*[a-z]"+ //at list one a-z
                            "?=.*[A-Z]"+ //at list one A-Z
                            //no white spaces
                            ".{6,}"+ //at least 6 characters
                            "$");

    private boolean isValidEmailId(String email){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }

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
        // setContentView(R.layout.activity_2);
      //  this.requestWindowFeature(Window.FEATURE_NO_TITLE);

//Remove notification bar
       // this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

//set content view AFTER ABOVE sequence (to avoid crash)
        this.setContentView(R.layout.register);
       /* try {
            this.getSupportActionBar().hide();

        } catch (NullPointerException e) {
        }*/
        Retrofit retrofit = RetrofitClient.getInstance();
        myApi5 = retrofit.create(UserClient.class);
        //view
        buttonAjoutAnnonce = (Button) findViewById(R.id.registerbtn2);
        email = (EditText) findViewById(R.id.emailtxt);
        nom = (EditText) findViewById(R.id.nomtxt);
        prenom = (EditText) findViewById(R.id.prenomtxt);

        tlf = (EditText) findViewById(R.id.teltxt);
        password = (EditText) findViewById(R.id.passwordtxt);
        passwordConfirmer = (EditText) findViewById(R.id.confirmerpasswordtxt);
        String emaill = email.getText().toString().trim();
        //EVENT
        buttonAjoutAnnonce.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tel = parseInt(tlf.getText().toString(), 10);
                // int cat = parseInt( catAnnonce.getText().toString(),10);
                if(password.getText().toString().equals(passwordConfirmer.getText().toString()))
                {

                    if (validatePhone()==true&&isValidEmailId(email.getText().toString().trim())) {
                    //
                    registerUser(
                            nom.getText().toString(),
                            prenom.getText().toString(),
                            email.getText().toString(),
                            //passwordConfirmer.getText().toString(),
                            password.getText().toString(),
                            tel);
                    Toast.makeText(Register.this, "Bienvenu à SUPPORTI !", Toast.LENGTH_SHORT).show();

                    openActivity();
                    /////
                }else{
                    Toast.makeText(Register.this, "Email ou telephone pas valide!", Toast.LENGTH_SHORT).show();

                }



                }else {
                    Toast.makeText(Register.this, "password et password confirmer ne sont pas equivalents", Toast.LENGTH_SHORT).show();

                }
               // Toast.makeText(Register.this, "Bienvenu à SUPPORTI !", Toast.LENGTH_SHORT).show();

            }
        }));
    }

    public void  openActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    private boolean validatePhone(){
        String  PhoneInput = tlf.getText().toString().trim();
        if(PhoneInput.isEmpty()){
            tlf.setError("Field can't be empty");
            return false;
        }else if(PhoneInput.length()!=8){
            tlf.setError("Enter a valid phone number!!");
            return false;
        }else{
            tlf.setError(null);
            return true;
        }
    }
        private void registerUser (String nom, String prenom, String email, String password,int tel)
        {
            compositeDisposable.add(myApi5.registerUser(nom, prenom, email, password, tel)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<String>() {
                        @Override
                        public void accept(String s) throws Exception {
                            if (s.contains("enregistré")) {
                                Toast.makeText(Register.this, "user ajoutée Avec Succès", Toast.LENGTH_SHORT).show();
                                // OpenLogin();
                            }

                        }
                    })

            );
        }

        private void OpenLogin () {

        }

    }