package fr.eni.encheres.bll;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.ArticlesVendusDAO;
import fr.eni.encheres.dal.RetraitDAO;

public class OffreManager {
	private ArticlesVendusDAO ArticlesVendusDAO;
	private RetraitDAO RetraitDAO;
	
	public OffreManager() {
		this.ArticlesVendusDAO = DAOFactory.getArticlesVendusDAO();
		this.RetraitDAO = DAOFactory.getRetraitDAO();
	}
	
	public ArticlesVendus addArticlesVendus(ArticlesVendus articlesVendus) throws SQLException {
		ResultSet result = this.ArticlesVendusDAO.insert(articlesVendus);
		result.last();
		int no_article = result.getInt(1);
		articlesVendus.setNo_article(no_article);
		return articlesVendus;
	}
	
	public void updateImagePath(int no_article, String path ) {
		try {
			this.ArticlesVendusDAO.update(no_article, "image_path", path);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Offre addOffre(Offre offre) throws SQLException {
		this.RetraitDAO.insert(offre.getRetrait());

		return offre;
	}
	
	public List<ArticlesVendus> getAllArticlesVendus(){
		ResultSet result = null;
		try {
			result = this.ArticlesVendusDAO.select();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List<ArticlesVendus> listArticles = new ArrayList<>();
		try {
			while(result.next()) {
				int no_article = result.getInt(1);
				String nom_article = result.getString(2);
				String description = result.getString(3);
				String date_debut_encheres = result.getString(4);
				String date_fin_encheres = result.getString(5);
				int prix_initial = result.getInt(6);
				int prix_vente = result.getInt(7);
				int no_utilisateur = result.getInt(8);
				int no_categorie = result.getInt(9);
				String image_path = result.getString(10);

				ArticlesVendus article = new ArticlesVendus(nom_article, description, date_debut_encheres, date_fin_encheres,
						prix_initial, prix_vente, no_utilisateur, no_categorie);
				article.setNo_article(no_article);
				article.setImage_path(image_path);
				listArticles.add(article);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listArticles;
	}
	
	public ArticlesVendus getArticleVendus(int no_article) {
		Map<String, Object> argMap = new HashMap<>();
		argMap.put("no_article", no_article);
		ResultSet result = null;
		try {
			result = this.ArticlesVendusDAO.select(argMap);
			result.last();
			if (result.getRow() == 1) {
				String nom_article = result.getString(2);
				String description = result.getString(3);
				String date_debut_encheres = result.getString(4);
				String date_fin_encheres = result.getString(5);
				int prix_initial = result.getInt(6);
				int prix_vente = result.getInt(7);
				int no_utilisateur = result.getInt(8);
				int no_categorie = result.getInt(9);
				String image_path = result.getString(10);;
				ArticlesVendus article = new ArticlesVendus(nom_article, description, date_debut_encheres, date_fin_encheres,
						prix_initial, prix_vente, no_utilisateur, no_categorie);
				article.setNo_article(no_article);
				article.setImage_path(image_path);
				return article;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
