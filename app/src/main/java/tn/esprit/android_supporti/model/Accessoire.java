package tn.esprit.android_supporti.model;

import com.google.gson.annotations.SerializedName;

public class Accessoire {
   // @SerializedName("nom_acc")
    private String nom_acc;
    private int prix_acc;
    private String desc_acc;
    private String image_acc;

    public Accessoire(String nom_acc, String desc_acc,  int prix_acc,String image_acc) {
        this.nom_acc = nom_acc;
        this.desc_acc = desc_acc;
        this.prix_acc = prix_acc;
        this.image_acc = image_acc;
    }

    public String getNom_acc() {
        return nom_acc;
    }

    public void setNom_acc(String nom_acc) {
        this.nom_acc = nom_acc;
    }

    public int getPrix_acc() {
        return prix_acc;
    }

    public void setPrix_acc(int prix_acc) {
        this.prix_acc = prix_acc;
    }

    public String getDesc_acc() {
        return desc_acc;
    }

    public void setDesc_acc(String desc_acc) {
        this.desc_acc = desc_acc;
    }

    public String getImage_acc() {
        return image_acc;
    }

    public void setImage_acc(String image_acc) {
        this.image_acc = image_acc;
    }
}
