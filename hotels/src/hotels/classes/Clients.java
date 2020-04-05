package hotels.classes;

public class Clients{
	protected String dni, nom, cognom;

	public Clients(String dni) {
		super();
		this.dni = dni;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCognom() {
		return cognom;
	}

	public void setCognom(String cognom) {
		this.cognom = cognom;
	}
	
	@Override
	public String toString() {
		return this.nom.substring(0,1)+". "+this.getCognom()+" - "+this.getDni();
	}
	
}