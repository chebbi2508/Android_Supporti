package tn.esprit.android_supporti.model;

public class Equipe {
    private String nom_eq;
    private String logo_eq;

    public Equipe(String nom_eq, String logo_eq) {
        this.nom_eq = nom_eq;
        this.logo_eq = logo_eq;
    }

    public String getNom_eq() {
        return nom_eq;
    }

    public void setNom_eq(String nom_eq) {
        this.nom_eq = nom_eq;
    }

    public String getLogo_eq() {
        return logo_eq;
    }

    public void setLogo_eq(String logo_eq) {
        this.logo_eq = logo_eq;
    }
}
