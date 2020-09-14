package fr.eni.encheres.bll;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Encheres implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int no_utilisateur;
	private String date_enchere;
	private int montant_enchere;
	private int no_article;
	
	public Encheres(int no_utilisateur, String date_enchere, int montant_enchere, int no_article) {
		super();
		this.no_utilisateur = no_utilisateur;
		this.date_enchere = date_enchere;
		this.montant_enchere = montant_enchere;
		this.no_article = no_article;
	}

	public Map<String, Object> getArgMap() {
		Map<String, Object> argMap = new HashMap<>();
		argMap.put("no_utilisateur", no_utilisateur);
		argMap.put("date_enchere", date_enchere);
		argMap.put("montant_enchere", montant_enchere);
		argMap.put("no_article", no_article);
		return argMap;
	}
	
}
