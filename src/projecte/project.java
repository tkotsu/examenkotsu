package projecte;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import projecte.classes.*;
import projecte.functions.*;

public class project {
    public static void main(String[] args) {
        ArrayList<Usuari> usuaris = new ArrayList<>();
        ArrayList<Post> posts = new ArrayList<>();

        Functions.benvinguda();

        System.out.println("Introdueix el nom d'usuari que tindrà el rol d'administrador: ");
        String nomadmin = System.console().readLine();
        boolean contrasenya = false;
        while (!contrasenya) {
            System.out.println("intodueix la contrasenya: ");
            String pass = System.console().readLine();
            System.out.println("Torna a introduir la contrasenya: ");
            String pass2 = System.console().readLine();
            if (pass.equals(pass2)) {
                System.out.println("Usuari administrador creat, ja pots començar a utilitzar l'aplicació...");
                Administrador admin = new Administrador(nomadmin, pass2);
                admin.setRol(admin.getClass().getSimpleName());
                usuaris.add(admin);
                contrasenya = true;
            } else {
                System.out.println("Les contrasenyes no coincideixen... Torna-hi...");
            }

        }
        boolean programa = true;
        while (programa) {
            boolean menuadmin = false;
            boolean menulector = false;
            boolean menueditor = false;
            boolean menu1 = true;
            String nom=" ";
            while (menu1) {
                Functions.primermenu();
                int opcio = Integer.parseInt(System.console().readLine());
                switch (opcio) {
                    case 1:
                        System.out.println("Introdueix el teu usuari: ");
                        String username = System.console().readLine();
                        if(usuaris.get(Functions.indexdeusuaris(usuaris, username)).getNom().equals(username)){
                            System.out.println("Introdueix la teva contrasenya: ");
                            String enterpass = System.console().readLine();
                            if (usuaris.get(Functions.indexdeusuaris(usuaris, username)).getContrasenya().equals(enterpass)){
                                System.out.println("Usuari autenticat amb èxit!");
                                System.out.println("El rol assignat al teu usuari es: "+usuaris.get(Functions.indexdeusuaris(usuaris, username)).getRol());
                                menu1=false;
                                nom=username;
                                if(usuaris.get(Functions.indexdeusuaris(usuaris, username)).getRol().equals("Administrador")){
                                    menuadmin=true;
                                }
                                else if(usuaris.get(Functions.indexdeusuaris(usuaris, username)).getRol().equals("Editor")){
                                    menueditor=true;
                                }
                                else if(usuaris.get(Functions.indexdeusuaris(usuaris, username)).getRol().equals("Lector")){
                                    menulector=true;
                                }
                            }
                            else{
                                System.out.println("les contrasenyes no coincideixen!!");
                            }
                        }
                        else{
                            System.out.println("Aquest usuari no existeix.");
                        }
                        break;
                    case 2:
                        System.out.println("Introdueix un nom d'usuari unic: ");
                        String nomnouuser = System.console().readLine();
                        System.out.println("Introdueix la contrasenya: ");
                        String nouuserpass = System.console().readLine();
                        System.out.println("Introdueix la data de neixament: (dd/MM/yyyy)");
                        String dataneixament = System.console().readLine();
                        String[] array = dataneixament.split("/");
                        int[] arrayint = Functions.stringtoint(array);
                        LocalDate dianeixament = LocalDate.of(arrayint[2], arrayint[1], arrayint[0]);
                        Period p = Period.between(dianeixament, LocalDate.now());
                        System.out.println("Anys: "+p.getYears());
                        if(p.getYears()>=18){
                            Lector noulector = new Lector(nomnouuser, nouuserpass, true);
                            noulector.setRol(noulector.getClass().getSimpleName());
                            usuaris.add(noulector);
                        }
                        else{
                            Lector noulector = new Lector(nomnouuser, nouuserpass, false);
                            noulector.setRol(noulector.getClass().getSimpleName());
                            usuaris.add(noulector);
                        }
                        break;
                    case 3:
                        programa=false;
                        menu1=false;
                        break;
                }
            }
            
            while(menuadmin){
                Functions.menuadmin();
                int opcioadmin = Integer.parseInt(System.console().readLine());
                switch(opcioadmin){
                    case 1:
                        System.out.println("Introdueix el titol: ");
                        String titol = System.console().readLine();
                        System.out.println("Introdueix el contingut: ");
                        String contingut = System.console().readLine();
                        System.out.println("El contingut es per majors de 18? (S/N)");
                        String permajors = System.console().readLine();
                        if(permajors.equals("S")){
                            Post postadmin = new Post(titol, contingut, LocalDateTime.now(), true, nom);
                            posts.add(postadmin);
                        }
                        else{
                            Post postadmin = new Post(titol, contingut, LocalDateTime.now(), false, nom);
                            posts.add(postadmin);
                        }
                        break;
                    case 2:
                        Functions.mostrarposts(posts);
                        break;
                    case 3:
                        Functions.mostrarpereliminar(posts);
                        int pereliminar = Integer.parseInt(System.console().readLine());
                        Functions.eliminant(posts, pereliminar);
                        Functions.mostrarpereliminar(posts);
                        break;
                    case 4:

                        break;
                    case 5:
                        System.out.println("Llistat d'usuaris amb rol d'Editor: ");
                        System.out.println("--------------------------------------");
                        Functions.mostrareditors(usuaris);

                        break;
                    case 6:
                        System.out.println("Llistat d'usuaris amb rol de lector: ");
                        System.out.println("--------------------------------------");
                        Functions.mostrarlectors(usuaris);
                        break;
                    case 0:
                        menuadmin=false;
                        break;
                }

            }

        }
    }
}