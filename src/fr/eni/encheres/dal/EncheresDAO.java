package fr.eni.encheres.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.eni.encheres.bll.Encheres;

public class EncheresDAO extends DAOJdbcImpl {
	public ResultSet select(Map<String, Object> conditions) throws SQLException {
		return super.select("ENCHERES", conditions);
	}
	public boolean addEncheres(int no_article, int no_utilisateur, int mise) {
		List<String> querys = new ArrayList<String>();
		querys.add("UPDATE ARTICLES_VENDUS SET prix_vente = ? WHERE no_article=?;");
		querys.add("UPDATE UTILISATEURS SET credit = credit - ? WHERE no_utilisateur=?;");
		querys.add("INSERT INTO ENCHERES VALUES (?,?,NOW(),?);");
		List<List<Object>> args = new ArrayList<List<Object>>();
		ArrayList<Object> list;
		
		list = new ArrayList<Object>();
		list.add(mise);
		list.add(no_article);
		args.add(list);
		
		list = new ArrayList<Object>();
		list.add(mise);
		list.add(no_utilisateur);
		args.add(list);
		
		list = new ArrayList<Object>();
		list.add(no_utilisateur);
		list.add(no_article);
		list.add(mise);
		args.add(list);
		
		try {
			return this.transaction(querys, args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	public boolean addEncheres(int no_article, int no_utilisateur, int mise, int previous_bidder, int previous_mise) {
		if (this.addEncheres(no_article, no_utilisateur, previous_mise)) {
			List<String> querys = new ArrayList<String>();
			querys.add("UPDATE UTILISATEURS SET credit = credit + ? WHERE no_utilisateur=?;");
			List<List<Object>> args = new ArrayList<List<Object>>();
			ArrayList<Object> list;
			
			list = new ArrayList<Object>();
			list.add(previous_mise);
			list.add(previous_bidder);
			args.add(list);
			
			try {
				return this.transaction(querys, args);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	
}
