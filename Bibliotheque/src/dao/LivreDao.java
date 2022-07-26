package dao;

import java.util.List;

import model.Livre;

public interface LivreDao {

	List<Livre> list() throws DaoException;

	Livre read(int id) throws DaoException;

	void create(Livre livre) throws DaoException;

	void update(Livre livre) throws DaoException;

	void delete(int id) throws DaoException;
}
