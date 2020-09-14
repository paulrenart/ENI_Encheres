package fr.eni.encheres.bll;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UserDAO;

public class UserManager {
	private UserDAO userDAO;
	
	public UserManager() {
		this.userDAO = DAOFactory.getUserDAO();
	}
	
	public User getUser(String username, String password) throws SQLException {
		Map<String, Object> argMap = new HashMap<>();
		argMap.put("pseudo", username);
		argMap.put("mot_de_passe", password);
		ResultSet result = this.userDAO.select(argMap);
		result.last();
		
		if (result.getRow() == 1) {
			int no_utilisateur = result.getInt(1);
			String pseudo = result.getString(2);	
			String nom = result.getString(3);
			String prenom = result.getString(4);
			String email = result.getString(5);
			String telephone = result.getString(6);
			String rue = result.getString(7);
			String code_postal = result.getString(8);
			String ville = result.getString(9);
			String mot_de_passe = result.getString(10);
			int credit = result.getInt(11);
			int administrateur = result.getInt(12);
			User user = new User(pseudo, nom, prenom, email, telephone,
					rue, code_postal, ville, mot_de_passe, credit, administrateur);
			user.setNo_utilisateur(no_utilisateur);
			return user;
		}
		return null;
	}
	
	public User getUser(int uid) throws SQLException {
		Map<String, Object> argMap = new HashMap<>();
		argMap.put("no_utilisateur", uid);
		ResultSet result = this.userDAO.select(argMap);
		result.last();
		
		if (result.getRow() == 1) {
			int no_utilisateur = result.getInt(1);
			String pseudo = result.getString(2);	
			String nom = result.getString(3);
			String prenom = result.getString(4);
			String email = result.getString(5);
			String telephone = result.getString(6);
			String rue = result.getString(7);
			String code_postal = result.getString(8);
			String ville = result.getString(9);
			String mot_de_passe = result.getString(10);
			int credit = result.getInt(11);
			int administrateur = result.getInt(12);
			User user = new User(pseudo, nom, prenom, email, telephone,
					rue, code_postal, ville, mot_de_passe, credit, administrateur);
			user.setNo_utilisateur(no_utilisateur);
			return user;
		}
		return null;
	}
	
	public User addUser(User user) throws SQLException {
		ResultSet result = this.userDAO.insert(user);
		result.last();
		user.setNo_utilisateur(result.getInt(1));
		return user;
	}

	public boolean checkUniqueFields(String username, String email) throws SQLException {
		Map<String, Object> argMap = new HashMap<>();
		argMap.put("pseudo", username);
		ResultSet result = this.userDAO.select(argMap);
		result.last();
		if (result.getRow() != 0) {
			return false;
		}
		argMap = new HashMap<>();
		argMap.put("email", email);
		result = this.userDAO.select(argMap);
		result.last();
		if (result.getRow() != 0) {
			return false;
		}
		return true;
	}
	
	public Map<String, String> getLoginMap() {
		Map<String, String> argMap = new HashMap<>();
		ResultSet result = null;
		try {
			result = this.userDAO.select();
			while(result.next()) {
				int no_utilisateur = result.getInt(1);
				String pseudo = result.getString(2);
				argMap.put(Integer.toString(no_utilisateur), pseudo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return argMap;
	}
}
