package projecte.classes;

import java.util.ArrayList;

public class Lector extends Usuari {
    boolean majordedat;

    public Lector(String nom, String contrasenya, ArrayList<Usuari> seguits, boolean majordedat) {
        super(nom, contrasenya, seguits);
        this.majordedat = majordedat;
    }

    public boolean isMajordedat() {
        return majordedat;
    }

    public void setMajordedat(boolean majordedat) {
        this.majordedat = majordedat;
    }

    
    
}