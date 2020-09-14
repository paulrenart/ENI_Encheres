package fr.eni.encheres.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import fr.eni.encheres.bll.User;

public class UserDAO extends DAOJdbcImpl {
	
	public ResultSet insert(User user) throws SQLException {
		return super.insert("UTILISATEURS", user.getArgMap());
	}
	
	public ResultSet select(Map<String, Object> conditions) throws SQLException {
		return super.select("UTILISATEURS", conditions);
	}
	
	public ResultSet select() throws SQLException {
		return super.select("UTILISATEURS");
	}
}
