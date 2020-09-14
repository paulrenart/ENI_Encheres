package fr.eni.encheres.dal;

public abstract class DAOFactory {
	
	public static UserDAO getUserDAO()
	{
		return new UserDAO();
	}
	public static ArticlesVendusDAO getArticlesVendusDAO()
	{
		return new ArticlesVendusDAO();
	}
	public static EncheresDAO getEncheresDAO()
	{
		return new EncheresDAO();
	}
	public static RetraitDAO getRetraitDAO()
	{
		return new RetraitDAO();
	}
	public static CategoriesDAO getCategoriesDAO()
	{
		return new CategoriesDAO();
	}
}
