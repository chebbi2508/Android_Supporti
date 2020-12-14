package tn.esprit.android_supporti.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Accessoire implements Serializable {
    private int id_acc;
    @SerializedName("nom_acc")
    private String nom_acc;
    @SerializedName("prix_acc")
    private int prix_acc;
    @SerializedName("desc_acc")
    private String desc_acc;
    private String image_acc;

    public Accessoire(int id_acc,String nom_acc, String desc_acc,  int prix_acc,String image_acc) {
        this.id_acc=id_acc;
        this.nom_acc = nom_acc;
        this.desc_acc = desc_acc;
        this.prix_acc = prix_acc;
        this.image_acc = image_acc;
    }

    public int getId_acc() {
        return id_acc;
    }

    public void setId_acc(int id_acc) {
        this.id_acc = id_acc;
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

    @Override
    public String toString() {
        return "Accessoire{" +
                "nom_acc='" + nom_acc + '\'' +
                ", prix_acc=" + prix_acc +
                ", desc_acc='" + desc_acc + '\'' +
                ", image_acc='" + image_acc + '\'' +
                '}';
    }
}
