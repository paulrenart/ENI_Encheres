package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.eni.encheres.bll.User;

class DAOJdbcImpl {

	private String sqlQuery;
	
	public ResultSet insert(String table, Map<String, Object> args) throws SQLException{
		try (Connection cnx = ConnectionProvider.getConnection();) {
			cnx.setAutoCommit(true);
			sqlQuery = "INSERT INTO " + table + " (";
			for (String key : args.keySet()) {
			    Object value = args.get(key);
			    if (value != null) {
		            sqlQuery += key + ", ";
			    }
			}
			
			sqlQuery = sqlQuery.substring(0, sqlQuery.length() - 2) + ") VALUES (";
			sqlQuery += "?, ".repeat(args.size());
			sqlQuery = sqlQuery.substring(0, sqlQuery.length() - 2) + ");";
			
			PreparedStatement ps = cnx.prepareStatement(sqlQuery, PreparedStatement.RETURN_GENERATED_KEYS);
			int i = 1;
			for (String key : args.keySet()) {
			    Object value = args.get(key);
			    
			    if (value != null) {
			    	if (value instanceof Integer) {
			            ps.setInt(i++, (Integer) value);
			        } else if (value instanceof Long) {
			            ps.setLong(i++, (Long) value);
			        } else if (value instanceof Double) {
			            ps.setDouble(i++, (Double) value);
			        } else if (value instanceof Float) {
			            ps.setFloat(i++, (Float) value);
			        } else {
			            ps.setString(i++, (String) value);
			        }
			    }
			}
			ps.execute();
			return ps.getGeneratedKeys();
		}
	}
	
	public ResultSet select(String table, Map<String, Object> conditions) throws SQLException{
		try (Connection cnx = ConnectionProvider.getConnection();) {
			cnx.setAutoCommit(true);
			sqlQuery = "SELECT * FROM " + table;
			if (conditions.size() < 1) {
				sqlQuery += ";";
			} else {
				boolean first = true;
				for (String key : conditions.keySet()) {
				    Object value = conditions.get(key);
				    if (value!=null) {
					    if (first){
				            sqlQuery += " where " + key + "=?";
				            first = false;
				        } else {
				            sqlQuery += " and " + key + "=?";
				        }
				    }
				}
				sqlQuery += ";";
			}
			PreparedStatement ps = cnx.prepareStatement(sqlQuery);
			int i = 1;
			for (String key : conditions.keySet()) {
			    Object value = conditions.get(key);
			    if (value != null) {
			    	if (value instanceof Integer) {
			            ps.setInt(i++, (Integer) value);
			        } else if (value instanceof Long) {
			            ps.setLong(i++, (Long) value);
			        } else if (value instanceof Double) {
			            ps.setDouble(i++, (Double) value);
			        } else if (value instanceof Float) {
			            ps.setFloat(i++, (Float) value);
			        } else {
			            ps.setString(i++, (String) value);
			        }
			    }
			}
			return ps.executeQuery();
		}
	}
	
	public ResultSet select(String table) throws SQLException {
		return select(table, new HashMap<String, Object>());
	}
	
	public void update(String table, String index_key, int index, String key, String value) throws SQLException{
		try (Connection cnx = ConnectionProvider.getConnection()) {
			cnx.setAutoCommit(true);
			sqlQuery = "UPDATE " + table + " SET " + key + "=? where " + index_key + "=?;";
			
			PreparedStatement ps = cnx.prepareStatement(sqlQuery, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, value);
			ps.setInt(2, index);
			ps.executeUpdate();
		}
	}
	
	public boolean transaction(List<String> querys, List<List<Object>> args) throws SQLException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			cnx.setAutoCommit(false);
			try {
				for(int j = 0; j < querys.size(); j++) {
				    String query = querys.get(j);
				    PreparedStatement ps = cnx.prepareStatement(query);
				    int i = 1;
				    for (Object value: args.get(j)) {			    
					    if (value != null) {
					    	if (value instanceof Integer) {
					            ps.setInt(i++, (Integer) value);
					        } else if (value instanceof Long) {
					            ps.setLong(i++, (Long) value);
					        } else if (value instanceof Double) {
					            ps.setDouble(i++, (Double) value);
					        } else if (value instanceof Float) {
					            ps.setFloat(i++, (Float) value);
					        } else {
					            ps.setString(i++, (String) value);
					        }
					    }
					}
				    ps.execute();
				}
				cnx.commit();
				return true;
			} catch (Exception e) {
				System.out.println("roll back");
				e.printStackTrace();
				cnx.rollback();
				return false;
			}
			
		}
	}

	public List<Integer> getLastBid(int no_article) {
		String query = "SELECT no_utilisateur, montant_enchere FROM ENCHERES WHERE no_article = ? and montant_enchere IN (SELECT MAX(montant_enchere) FROM ENCHERES GROUP BY no_article)";
		List<Integer> out = new ArrayList<Integer>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			cnx.setAutoCommit(true);
			PreparedStatement ps = cnx.prepareStatement(query);
			ps.setInt(1, no_article);
			ResultSet result = ps.executeQuery();
			result.last();
			if (result.getRow() == 1) {
				int no_utilisateur = result.getInt(1);
				int mise = result.getInt(2);
				out.add(no_utilisateur);
				out.add(mise);
				
			} else {
				throw new SQLException();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out;
	}
}
