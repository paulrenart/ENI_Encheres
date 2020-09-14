package fr.eni.encheres.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import fr.eni.encheres.bll.Retrait;

public class RetraitDAO extends DAOJdbcImpl {
	public ResultSet insert(Retrait retrait) throws SQLException {
		return super.insert("RETRAITS", retrait.getArgMap());
	}
	
	public ResultSet select(Map<String, Object> conditions) throws SQLException {
		return super.select("RETRAITS", conditions);
	}
}
