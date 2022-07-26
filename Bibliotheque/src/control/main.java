package control;

import dao.AuteurDao;
import dao.DaoException;
import dao.DaoFactory;
import dao.LivreDao;
import model.Auteur;
import model.Livre;

public class main {
	public static void main(String[] args) {

		AuteurDao auteurDao = DaoFactory.getInstance().getAuteurDao();
		LivreDao livreDao = DaoFactory.getInstance().getLivreDao();
		try {

			System.out.println("---------READ ALL AUTEUR------------");
			System.out.println(auteurDao.list());
			System.out.println("---------READ  AUTEUR BY ID = 2 ------------");
			System.out.println(auteurDao.read(2));
			System.out.println("--------CREATE AUTEUR---------");
			Auteur auteur = new Auteur("Ben Amor", "Jawher", " +04555127", "fbehbfhzeb@gmail.com");
			auteurDao.create(auteur);
			System.out.println(auteur);
			System.out.println("--------UPDATE AUTEUR (Prénom)---------");
			auteur.setPrenom("Maryline");
			auteurDao.update(auteur);
			System.out.println(auteur);

			System.out.println("---------READ ALL LIVRE------------");
			System.out.println(livreDao.list());
			System.out.println("---------READ LIVRE BY ID = 2------------");
			System.out.println(livreDao.read(2));
			System.out.println("--------CREATE LIVRE---------");
			Livre livre = new Livre(auteurDao.read(2), " Introduction Java", 120, "Informatique");
			livreDao.create(livre);
			System.out.println(livre);
			System.out.println("--------UPDATE LIVRE (Catégorie)---------");
			livre.setCategorie("Livre d'informatique");
			livreDao.update(livre);
			System.out.println(livre);
			System.out.println("-------DELETE LIVRE----------");
			System.out.println();
			livreDao.delete(livre.getId());
			System.out.println("---------READ ALL LIVRE APRES DELETE------------");
			System.out.println(livreDao.list());

			System.out.println("-------DELETE AUTEUR----------");
			auteurDao.delete(auteur.getId());

		} catch (DaoException e) {
			System.out.println(e.getMessage());
		}
	}
}
