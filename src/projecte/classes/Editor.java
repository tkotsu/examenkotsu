package projecte.classes;

import java.util.ArrayList;

public class Editor extends Usuari {

    public Editor(String nom, String contrasenya, ArrayList<Usuari> seguits) {
        super(nom, contrasenya, seguits);
        
    }

    public boolean comprovasiesmajor(){
        return true;
    }

    
    
}