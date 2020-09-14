package fr.eni.encheres.bll;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.CategoriesDAO;

public class CategoriesManager {
	private CategoriesDAO categoriesDAO;
	
	public CategoriesManager() {
		this.categoriesDAO = DAOFactory.getCategoriesDAO();
	}
	
	public Map<String, String> getCategories(){
		Map<String, String> categories = new HashMap<>();
		ResultSet result = null;
		try {
			result = this.categoriesDAO.select();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List<Categories> listCategories = new ArrayList<>();
		try {
			while(result.next()) {
				int no_categorie = result.getInt(1);
				String libelle = result.getString(2);

				categories.put(String.valueOf(no_categorie), libelle);;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categories;
	}
}
