package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Auteur;

public class AuteurDaoImpl implements AuteurDao {

	private static final String SQL_SELECT = "Select * From Auteur";
	private static final String SQL_SELECT_BY_ID = "Select * From Auteur Where id=?";
	private static final String SQL_INSERT = "Insert into Auteur(nom, prenom, telephone, email) Values (?,?,?,?)";
	private static final String SQL_UPDATE = "Update Auteur Set nom=?, prenom=?, telephone=?, email=? Where id=?";
	private static final String SQL_DELETE = "Delete From Auteur Where id=?";
	
	
	private DaoFactory factory;
	
	protected AuteurDaoImpl(DaoFactory factory) {
		this.factory = factory;
	}
	
	@Override
	public List<Auteur> list() throws DaoException {
		List<Auteur> listeAuteurs = new ArrayList<Auteur>();
		Connection con = null;
		
		try {
			con = factory.getConnection();
			
			PreparedStatement pst = con.prepareStatement(SQL_SELECT);
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				listeAuteurs.add(map(rs));
			}
			
			rs.close();
			pst.close();
			
		} catch (SQLException e) {
			throw new DaoException("Erreur de lecture Table Auteur");
		} finally {
			factory.releaseConnection(con);
		}
				
		
		return listeAuteurs;
	}

	@Override
	public Auteur read(int id) throws DaoException {
		Auteur a = new Auteur();
		Connection con = null;
		
		try {
			con = factory.getConnection();
			
			PreparedStatement pst = con.prepareStatement(SQL_SELECT_BY_ID);
			pst.setInt(1, id);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				a = map(rs);
			} else {
				throw new DaoException("L'auteur avec l'id "+id+" n'existe pas.");
			}
			
			rs.close();
			pst.close();
			
		} catch (SQLException e) {
			throw new DaoException("Erreur de lecture Table Realisateur");
		} finally {
			factory.releaseConnection(con);
		}
		
		return a;
	}

	@Override
	public void create(Auteur auteur) throws DaoException {
		Connection con = null;
		
		try {
			con = factory.getConnection();
			
			PreparedStatement pst = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, auteur.getNom());
			pst.setString(2,auteur.getPrenom());
			pst.setString(3, auteur.getTelephone());
			pst.setString(4, auteur.getEmail());
			
			int result = pst.executeUpdate();
			
			if(result == 1) {
				ResultSet rsKeys = pst.getGeneratedKeys();
				if(rsKeys.next()) {
					auteur.setId(rsKeys.getInt(1));
				}
				rsKeys.close();
			} else {
				throw new DaoException("Impossible d'insérer l'auteur  : "+auteur.getNom());
			}
			
			pst.close();
			
		} catch (SQLException e) {
			throw new DaoException("Impossible d'insérer l'auteur : "+auteur.getNom());
		} finally {
			factory.releaseConnection(con);
		}
	}

	@Override
	public void update(Auteur auteur) throws DaoException {
		Connection con = null;
		
		try {
			con = factory.getConnection();
			
			PreparedStatement pst = con.prepareStatement(SQL_UPDATE);
			pst.setString(1, auteur.getNom());
			pst.setString(2, auteur.getPrenom());
			pst.setString(3, auteur.getTelephone());
			pst.setString(4, auteur.getEmail());
			pst.setInt(5, auteur.getId());
			
			int result = pst.executeUpdate();
			
			if(result != 1) {
				throw new DaoException("Impossible de mettre à jour l'auteur : "+auteur.getNom());
			}
			
			pst.close();
			
		} catch (SQLException e) {
			throw new DaoException("Impossible de mettre à jour l'auteur  : "+auteur.getNom());
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
			
			if(result != 1) {
				throw new DaoException("Impossible de supprimer l'auteur  avec l'id : "+id);
			}
			
			pst.close();
			
		} catch (SQLException e) {
			throw new DaoException("Impossible de supprimer l'auteur  avec l'id : "+id);
		} finally {
			factory.releaseConnection(con);
		}
	}
	
	
	private static Auteur map(ResultSet rs) throws SQLException {
		Auteur a = new Auteur();
		a.setId(rs.getInt("id"));
		a.setNom(rs.getString("nom"));
		a.setprenom(rs.getString("prenom"));
		a.setTelephone(rs.getString("telephone"));
		a.setEmail(rs.getString("email"));
		
		return a;
	}

}