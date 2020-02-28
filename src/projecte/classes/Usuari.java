package projecte.classes;

import java.util.ArrayList;

public class Usuari {
    protected String nom, contrasenya, rol;
    protected ArrayList<Usuari> seguits;

    public Usuari(String nom, String contrasenya, ArrayList<Usuari> seguits) {
        this.nom = nom;
        this.contrasenya = contrasenya;
        this.seguits = seguits;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public ArrayList<Usuari> getSeguits() {
        return seguits;
    }

    public void setSeguits(ArrayList<Usuari> seguits) {
        this.seguits = seguits;
    }


    
}