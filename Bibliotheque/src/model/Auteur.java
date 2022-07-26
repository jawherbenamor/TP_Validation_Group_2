package model;

public class Auteur {

	// Attributs:
	private int id;
	private String nom;
	private String prenom;
	private String telephone;
	private String email;

	// constructeurs
	public Auteur() {
		super();
	}
	public Auteur(String nom, String prenom, String telephone, String email) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.email = email;
	}
	
	// Getters setters
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

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
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
		return "L'id " + id + " correspond a l'auteur " + nom + " " + prenom +". Son numero de telephone est le " + telephone + " et son adresse mail est " + email+ ".\n";
	}

}
