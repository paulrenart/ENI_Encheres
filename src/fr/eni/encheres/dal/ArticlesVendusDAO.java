package fr.eni.encheres.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import fr.eni.encheres.bll.ArticlesVendus;
import fr.eni.encheres.bll.User;

public class ArticlesVendusDAO extends DAOJdbcImpl {
	public ResultSet insert(ArticlesVendus articlesVendus) throws SQLException {
		return super.insert("ARTICLES_VENDUS", articlesVendus.getArgMap());
	}
	
	public ResultSet select(Map<String, Object> conditions) throws SQLException {
		return super.select("ARTICLES_VENDUS", conditions);
	}
	public ResultSet select() throws SQLException {
		return super.select("ARTICLES_VENDUS");
	}
	public void update(int index, String key, String value) throws SQLException {
		super.update("ARTICLES_VENDUS", "no_article", index, key, value);
	}
}
