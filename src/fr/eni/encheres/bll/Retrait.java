package fr.eni.encheres.bll;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Retrait implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int no_article;
	private String code_postal;	
	private String ville;
	private String rue;

	public Retrait(int no_article, String code_postal, String ville, String rue) {
		super();
		this.no_article = no_article;
		this.code_postal = code_postal;
		this.ville = ville;
		this.rue = rue;
	}

	public Map<String, Object> getArgMap() {
		Map<String, Object> argMap = new HashMap<>();
		argMap.put("code_postal", code_postal);
		argMap.put("ville", ville);
		argMap.put("rue", rue);
		argMap.put("no_article", no_article);
		return argMap;
	}
	
}
