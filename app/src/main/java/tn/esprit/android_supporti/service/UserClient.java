package tn.esprit.android_supporti.service;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import tn.esprit.android_supporti.model.Accessoire;
import tn.esprit.android_supporti.model.Equipe;


public interface UserClient {


    @POST("/login/")
    @FormUrlEncoded
    Observable<String> loginUser (@Field("email_user") String email,
                                  @Field("password_user") String password);


    @GET("/accessoire/")
    Call <List<Accessoire>> getAccessoires();


    @GET("/equipe/")
    Call <List<Equipe>> getEquipes();

    @POST ("/register/")
    @FormUrlEncoded
    Observable<String> registerUser(@Field("nom_user") String nom_user,
                                    @Field("prenom_user") String prenom_user,
                                    @Field("email_user") String email_user,
                                    @Field("password_user") String password_user,
                                    @Field("tel_user") int tel_user);
                                   // @Field("idCategorie") int idCategorie);


    @POST ("/accessoire/")
    @FormUrlEncoded
    Observable<String> ajouterAcc(@Field("nom_acc") String nom_acc,
                                    @Field("desc_acc") String desc_acc,
                                    @Field("prix_acc") int prix_acc,
                                    @Field("image_acc") String image_acc);



}
