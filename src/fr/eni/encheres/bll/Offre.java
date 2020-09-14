package fr.eni.encheres.bll;

public class Offre {
	ArticlesVendus articles_vendus;
	Retrait retrait;
	
	
	public Offre(
			ArticlesVendus articles_vendus,
			int no_utilisateur,
			String date_enchere,
			int montant_enchere,
			int no_article,
			String code_postal,
			String ville,
			String rue
			) {
		this.articles_vendus = articles_vendus;
		this.retrait = new Retrait(no_article, code_postal, ville, rue);
	}



	public ArticlesVendus getArticles_vendus() {
		return articles_vendus;
	}



	public void setArticles_vendus(ArticlesVendus articles_vendus) {
		this.articles_vendus = articles_vendus;
	}


	public Retrait getRetrait() {
		return retrait;
	}



	public void setRetrait(Retrait retrait) {
		this.retrait = retrait;
	}
}
