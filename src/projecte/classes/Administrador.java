package projecte.classes;

import java.util.ArrayList;

public class Administrador extends Usuari {


    public Administrador(String nom, String contrasenya, ArrayList<Usuari> seguits) {
        super(nom, contrasenya, seguits);
    }
    
    public boolean comprovasiesmajor(){
        return true;
    }
    
}