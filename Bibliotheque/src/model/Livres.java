package model;

public class Livres {
	
	
	
	private int id ;
	private Auteur auteur ;
	private String titre;
	private int nb_pages;
	private String categorie;
	
	public Livres() {
		super();
	}

	public Livres(int id, Auteur auteur, String titre, int nb_pages, String categorie) {
		super();
		this.id = id;
		this.auteur = auteur;
		this.titre = titre;
		this.nb_pages = nb_pages;
		this.categorie = categorie;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Auteur getAuteur() {
		return auteur;
	}

	public void setAuteur(Auteur auteur) {
		this.auteur = auteur;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public int getNb_pages() {
		return nb_pages;
	}

	public void setNb_pages(int nb_pages) {
		this.nb_pages = nb_pages;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	@Override
	public String toString() {
		return "Livres [id=" + id + ", auteur=" + auteur + ", titre=" + titre + ", nb_pages=" + nb_pages
				+ ", categorie=" + categorie + "]";
	}
	
	
	

}
