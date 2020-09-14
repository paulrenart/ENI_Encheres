package fr.eni.encheres.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import fr.eni.encheres.bll.Encheres;

public class CategoriesDAO extends DAOJdbcImpl {
	public ResultSet insert(Encheres encheres) throws SQLException {
		return super.insert("CATEGORIES", encheres.getArgMap());
	}
	
	public ResultSet select() throws SQLException {
		return super.select("CATEGORIES");
	}
}
