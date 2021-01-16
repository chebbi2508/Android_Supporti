package tn.esprit.android_supporti.service;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import tn.esprit.android_supporti.model.Accessoire;
import tn.esprit.android_supporti.model.Actualite;
import tn.esprit.android_supporti.model.AffectRequest;
import tn.esprit.android_supporti.model.AffectResponse;
import tn.esprit.android_supporti.model.Equipe;
import tn.esprit.android_supporti.model.Matchs;
import tn.esprit.android_supporti.model.User;


public interface UserClient {


    @POST("/login/")
    @FormUrlEncoded
    Observable<String> loginUser (@Field("email_user") String email,
                                  @Field("password_user") String password);


    @GET("/accessoire/")
    Call <List<Accessoire>> getAccessoires();
    @GET("/actualite/")
    Call <List<Actualite>> getActualites();

    @GET("/user/")
    Call <List<User>> getUsers();
    @POST("/login/")
    Call<User> loginUserr (@Body User user);


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

    @GET("/match/")
    Call<List<Matchs>> getAllMatchs();

    @POST("user/affected_equipe")
    Call<AffectResponse> affecter_equipe(@Body AffectRequest affectRequest);

    @PUT("user/{id}")
    @FormUrlEncoded
    Call<Void> modifierProfilC(@Path("id") int id_user,
                               @Field ("nom_user") String nom,
                               @Field ("prenom_user") String prenom,
                               @Field("tel_user") int tel,
                               @Field ("password_user") String password_user,
                               @Field("email_user") String email_user);


}
