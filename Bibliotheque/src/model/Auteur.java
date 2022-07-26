package model;

public class Auteur {
	
	
	//Attributs:
	
	private int id ;
	private String nom;
	private String prenon;
	private String telephone;
	private String email;
	
	
	//constructeurs
	
	
	public Auteur() {
		super();
	}


	public Auteur(int id, String nom, String prenon, String telephone, String email) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenon = prenon;
		this.telephone = telephone;
		this.email = email;
	}


	public Auteur(String nom, String prenon, String telephone, String email) {
		super();
		this.nom = nom;
		this.prenon = prenon;
		this.telephone = telephone;
		this.email = email;
	}
	//Getters setters

	public int getId() {
		return id;
	}


	


	public void setId(int id) {
		this.id = id;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenon() {
		return prenon;
	}


	public void setPrenon(String prenon) {
		this.prenon = prenon;
	}


	public String getTelephone() {
		return telephone;
	}


	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Auteur [id=" + id + ", nom=" + nom + ", prenon=" + prenon + ", telephone=" + telephone + ", email="
				+ email + "]";
	}
	
	
	
	

}
