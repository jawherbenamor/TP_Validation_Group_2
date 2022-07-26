package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import model.Livre;

public class LivreDaoImpl implements LivreDao {

	private static final String SQL_SELECT = "Select * From livre";
	private static final String SQL_SELECT_BY_ID = "Select * From livre Where id=?";
	private static final String SQL_INSERT = "Insert into livre(id_auteur, titre, nb_pages, categorie) Values (?,?,?,?)";
	private static final String SQL_UPDATE = "Update livre Set id_auteur=?, titre=?, nb_pages=?, categorie=? Where id=?";
	private static final String SQL_DELETE = "Delete From livre Where id=?";

	private DaoFactory factory;

	protected LivreDaoImpl(DaoFactory factory) {
		this.factory = factory;
	}

	@Override
	public List<Livre> list() throws DaoException {
		List<Livre> listeLivres = new ArrayList<Livre>();
		Connection con = null;

		try {
			con = factory.getConnection();

			PreparedStatement pst = con.prepareStatement(SQL_SELECT);

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				listeLivres.add(map(rs));
			}

			rs.close();
			pst.close();

		} catch (SQLException e) {
			throw new DaoException("Erreur de lecture Table Auteur");
		} finally {
			factory.releaseConnection(con);
		}

		return listeLivres;
	}

	@Override
	public Livre read(int id) throws DaoException {
		Livre livre = new Livre();
		Connection con = null;

		try {
			con = factory.getConnection();

			PreparedStatement pst = con.prepareStatement(SQL_SELECT_BY_ID);
			pst.setInt(1, id);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				livre = map(rs);
			} else {
				throw new DaoException("Le livre avec l'id " + id + " n'existe pas.");
			}

			rs.close();
			pst.close();

		} catch (SQLException e) {
			throw new DaoException("Erreur de lecture Table Livre");
		} finally {
			factory.releaseConnection(con);
		}

		return livre;
	}

	@Override
	public void create(Livre livre) throws DaoException {
		Connection con = null;

		try {
			con = factory.getConnection();

			PreparedStatement pst = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			if (livre.getAuteur() != null) {
				pst.setInt(1, livre.getAuteur().getId());
			} else {
				pst.setNull(1, Types.NULL);
			}
			pst.setString(2, livre.getTitre());
			pst.setInt(3, livre.getNb_pages());
			pst.setString(4, livre.getCategorie());

			int result = pst.executeUpdate();

			if (result == 1) {
				ResultSet rsKeys = pst.getGeneratedKeys();
				if (rsKeys.next()) {
					livre.setId(rsKeys.getInt(1));
				}
				rsKeys.close();
			} else {
				throw new DaoException("Impossible d'insérer le livre  : " + livre.getTitre());
			}

			pst.close();

		} catch (SQLException e) {
			throw new DaoException("Impossible d'insérer le livre : " + livre.getTitre());
		} finally {
			factory.releaseConnection(con);
		}

	}

	@Override
	public void update(Livre livre) throws DaoException {
		Connection con = null;

		try {
			con = factory.getConnection();

			PreparedStatement pst = con.prepareStatement(SQL_UPDATE);
			if (livre.getAuteur() != null) {
				pst.setInt(1, livre.getAuteur().getId());
			} else {
				pst.setNull(1, Types.NULL);
			}
			pst.setString(2, livre.getTitre());
			pst.setInt(3, livre.getNb_pages());
			pst.setString(4, livre.getCategorie());
			
			pst.setInt(5, livre.getId());

			int result = pst.executeUpdate();

			if (result != 1) {
				throw new DaoException("Impossible de mettre à jour le livre : " + livre.getTitre());
			}

			pst.close();

		} catch (SQLException e) {
			throw new DaoException("Impossible de mettre à jour le livre  : " + livre.getTitre());
		} finally {
			factory.releaseConnection(con);
		}

	}

	@Override
	public void delete(int id) throws DaoException {
		Connection con = null;

		try {
			con = factory.getConnection();

			PreparedStatement pst = con.prepareStatement(SQL_DELETE);
			pst.setLong(1, id);

			int result = pst.executeUpdate();

			if (result != 1) {
				throw new DaoException("Impossible de supprimer le livre  avec l'id : " + id);
			}

			pst.close();

		} catch (SQLException e) {
			throw new DaoException("Impossible de supprimer le livre  avec l'id : " + id);
		} finally {
			factory.releaseConnection(con);
		}

	}

	private static Livre map(ResultSet rs) throws SQLException {
		Livre livre = new Livre();
		livre.setId(rs.getInt("id"));
		livre.setTitre(rs.getString("titre"));
		livre.setNb_pages(rs.getInt("nb_pages"));
		livre.setCategorie(rs.getString("categorie"));

		try {
			livre.setAuteur(DaoFactory.getInstance().getAuteurDao().read(rs.getInt("id_auteur")));
		} catch (DaoException e) {
			livre.setAuteur(null);
		}
		return livre;
	}

}
