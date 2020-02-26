package projecte.functions;

import projecte.classes.*;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Functions{
    public static void benvinguda(){
        System.out.println("*********************************");
        System.out.println("* Benvingut a CirvSocialNetwork *");
        System.out.println("*********************************");
    }
    public static void primermenu(){
        System.out.println("*********************************");
        System.out.println("* 1 - Login                     *");
        System.out.println("* 2 - Registre com a lector     *");
        System.out.println("* 3 - Sortir                    *");
        System.out.println("*********************************");    
    }
    public static int indexdeusuaris(ArrayList<Usuari> usuaris, String username){
        int valor = 0;
        for(Usuari u: usuaris){
            if(u.getNom().equals(username)){
                return usuaris.indexOf(u);
            }
            else{

            }
        }

        return valor;
    }
    public static void menuadmin(){
        System.out.println("************************************");
        System.out.println("* 1 - Crear un post                *");
        System.out.println("* 2 - Llistar tots els posts       *");
        System.out.println("* 3 - Eliminar un post             *");
        System.out.println("* 4 - Modificar un Lector a Editor *");
        System.out.println("* 5 - Llistar Editors              *");
        System.out.println("* 6 - Llistar lectors              *");
        System.out.println("* 0 - Log out                      *");
        System.out.println("************************************"); 
    }
    public static void mostrarposts(ArrayList<Post> posts){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        for(Post p: posts){
            System.out.println("**********************************");
            System.out.println(" Autor: "+p.getAutor()+"  ");
            System.out.println(" Titol: "+p.getNompost());
            System.out.println("Data publicacio: "+p.getHorapublicacio().format(formatter));
            System.out.println("+18? "+p.isPermajors());
            System.out.println("Contingut: "+p.getContingut());
            System.out.println("**********************************");


        }
    }
    public static void mostrarpereliminar(ArrayList<Post> posts){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm");
        for(Post p: posts){
            System.out.println((posts.indexOf(p)+1)+" - Data: "+p.getHorapublicacio().format(formatter)+" - Autor: "+p.getAutor()+" - Titol: "+p.getNompost());


        }
    }
    public static void eliminant(ArrayList<Post> posts, int pereliminar){
        posts.remove(pereliminar-1);
        System.out.println("post eliminat...");
    }
    public static void mostrareditors(ArrayList<Usuari> usuaris){
        for(Usuari u : usuaris){
            if(u.getRol().equals("Editor")){
                System.out.println(u.getNom());
            }
        }
    }
    public static void mostrarlectors(ArrayList<Usuari> usuaris){
        for(Usuari u : usuaris){
            if(u.getRol().equals("Lector")){
                System.out.println(u.getNom());
            }
        }
    }
    public static int[] stringtoint(String[] array){
        int[] arrayint = new int[3];
        arrayint[0] = Integer.parseInt(array[0]);
        arrayint[1] = Integer.parseInt(array[1]);
        arrayint[2] = Integer.parseInt(array[2]);
        return arrayint;
    }
}