package fr.eni.encheres.bll;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.EncheresDAO;

public class EncheresManager {
	private EncheresDAO EncheresDAO;
	
	public EncheresManager() {
		this.EncheresDAO = DAOFactory.getEncheresDAO();
	}
	public void addEnchere(int no_article, int no_utilisateur, int mise) {
		List<Integer> previousBid = this.EncheresDAO.getLastBid(no_article);
		if(previousBid.size() > 0) {
			int previous_bidder = previousBid.get(0);
			int previous_mise = previousBid.get(1);
			EncheresDAO.addEncheres(no_article, no_utilisateur, mise, previous_bidder, previous_mise);
		} else {
			EncheresDAO.addEncheres(no_article, no_utilisateur, mise);
		}
	}
}
