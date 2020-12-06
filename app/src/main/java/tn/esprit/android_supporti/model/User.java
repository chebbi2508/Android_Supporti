package tn.esprit.android_supporti.model;

public class User {

    private String nom_user;
    private int id_user;
    private  String prenom_user;
    private String email_user;
    private  int tel_user;
    private String password_user;

    public User(String name, String prenom, String email, int tel_user, String password){
        this.email_user = email;
        this.prenom_user = prenom;
        this.nom_user = name;
        this.tel_user = tel_user;
        this.password_user = password;
    }


    public String getNom_user() {
        return nom_user;
    }

    public void setNom_user(String nom_user) {
        this.nom_user = nom_user;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getPrenom_user() {
        return prenom_user;
    }

    public void setPrenom_user(String prenom_user) {
        this.prenom_user = prenom_user;
    }

    public String getEmail_user() {
        return email_user;
    }

    public void setEmail_user(String email_user) {
        this.email_user = email_user;
    }

    public int getTel_user() {
        return tel_user;
    }

    public void setTel_user(int tel_user) {
        this.tel_user = tel_user;
    }

    public String getPassword_user() {
        return password_user;
    }

    public void setPassword_user(String password_user) {
        this.password_user = password_user;
    }
}
