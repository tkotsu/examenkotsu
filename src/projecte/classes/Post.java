package projecte.classes;

import java.time.LocalDateTime;

public class Post{
    private String nompost, contingut, autor;
    private LocalDateTime horapublicacio;
    private boolean permajors;

    public Post(String nompost, String contingut, LocalDateTime horapublicacio, boolean permajors, String autor) {
        this.nompost = nompost;
        this.contingut = contingut;
        this.horapublicacio = horapublicacio;
        this.permajors = permajors;
        this.autor = autor;
    }

    public String getNompost() {
        return nompost;
    }

    public void setNompost(String nompost) {
        this.nompost = nompost;
    }

    public String getContingut() {
        return contingut;
    }

    public void setContingut(String contingut) {
        this.contingut = contingut;
    }

    public LocalDateTime getHorapublicacio() {
        return horapublicacio;
    }

    public void setHorapublicacio(LocalDateTime horapublicacio) {
        this.horapublicacio = horapublicacio;
    }

    public boolean isPermajors() {
        return permajors;
    }

    public void setPermajors(boolean permajors) {
        this.permajors = permajors;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    
    
}