package fr.eni.encheres.bll;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ArticlesVendus implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int no_article;
	private String nom_article;	
	private String description;
	private String date_debut_encheres;
	private String date_fin_encheres;
	private int prix_initial;
	private int prix_vente;
	private int no_utilisateur;
	private int no_categorie;
	private String image_path;
	
	
	public ArticlesVendus(String nom_article, String description, String date_debut_encheres, String date_fin_encheres,
			int prix_initial, int prix_vente, int no_utilisateur, int no_categorie) {
		super();
		this.nom_article = nom_article;
		this.description = description;
		this.date_debut_encheres = date_debut_encheres;
		this.date_fin_encheres = date_fin_encheres;
		this.prix_initial = prix_initial;
		this.prix_vente = prix_vente;
		this.no_utilisateur = no_utilisateur;
		this.no_categorie = no_categorie;
	}


	public Map<String, Object> getArgMap() {
		Map<String, Object> argMap = new HashMap<>();
		argMap.put("nom_article", nom_article);
		argMap.put("description", description);
		argMap.put("date_debut_encheres", date_debut_encheres);
		argMap.put("date_fin_encheres", date_fin_encheres);
		argMap.put("prix_initial", prix_initial);
		argMap.put("prix_vente", prix_vente);
		argMap.put("no_utilisateur", no_utilisateur);
		argMap.put("no_categorie", no_categorie);
		argMap.put("no_article", no_article);
		return argMap;
	}


	public String getImage_path() {
		return image_path;
	}


	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}


	public int getNo_article() {
		return no_article;
	}


	public void setNo_article(int no_article) {
		this.no_article = no_article;
	}


	@Override
	public String toString() {
		return "ArticlesVendus [no_article=" + no_article + ", nom_article=" + nom_article + ", description="
				+ description + ", date_debut_encheres=" + date_debut_encheres + ", date_fin_encheres="
				+ date_fin_encheres + ", prix_initial=" + prix_initial + ", prix_vente=" + prix_vente
				+ ", no_utilisateur=" + no_utilisateur + ", no_categorie=" + no_categorie + "]";
	}


	public String getNom_article() {
		return nom_article;
	}


	public String getDescription() {
		return description;
	}


	public String getDate_debut_encheres() {
		return date_debut_encheres;
	}


	public String getDate_fin_encheres() {
		return date_fin_encheres;
	}


	public int getPrix_initial() {
		return prix_initial;
	}


	public int getPrix_vente() {
		return prix_vente;
	}


	public int getNo_utilisateur() {
		return no_utilisateur;
	}


	public int getNo_categorie() {
		return no_categorie;
	}
	
}
