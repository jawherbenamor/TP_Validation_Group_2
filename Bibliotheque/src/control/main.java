package control;

import dao.DaoException;
import dao.DaoFactory;
import dao.AuteurDao;
import model.Auteur;

public class main {
	public static void main(String[] args) {

		try {

			System.out.println("---------READ------------");
			AuteurDao auteurDao = DaoFactory.getInstance().getAuteurDao();

			System.out.println(auteurDao.list());
			System.out.println(auteurDao.read(2));

			System.out.println("--------CREATE---------");

			Auteur auteur = new Auteur("Ben Amor", "Jawher", " +04555127", "fbehbfhzeb@gmail.com");
			System.out.println(auteur);
			auteurDao.create(auteur);
			System.out.println(auteur);

			System.out.println("--------UPDATE---------");

			auteur.setPrenom("Maryline");
			auteurDao.update(auteur);
			System.out.println(auteur);

			
			  System.out.println("-------DELETE----------");
			  System.out.println(auteurDao.read(6)); 
			  auteurDao.delete(6);
			  System.out.println(auteurDao.read(6));
			 

		} catch (DaoException e) {
			System.out.println(e.getMessage());
		}
	}
}
