package projecte.classes;


public class Lector extends Usuari{
    boolean majordedat;

    public Lector(String nom, String contrasenya, boolean majordedat) {
        super(nom, contrasenya);
        this.majordedat = majordedat;
    }

    public boolean isMajordedat() {
        return majordedat;
    }

    public void setMajordedat(boolean majordedat) {
        this.majordedat = majordedat;
    }

    
    
}