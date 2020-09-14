package fr.eni.encheres.ihm;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bll.ArticlesVendus;
import fr.eni.encheres.bll.CategoriesManager;
import fr.eni.encheres.bll.OffreManager;
import fr.eni.encheres.bll.UserManager;

@WebServlet({ "/Index", "/" })
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Index() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		Object uid;
		OffreManager OffreManager = new OffreManager();
		request.setAttribute("listArticles", OffreManager.getAllArticlesVendus());
		
		UserManager UserManager = new UserManager();
		request.setAttribute("users", UserManager.getLoginMap());
		
		CategoriesManager CategoriesManager = new CategoriesManager();
		request.setAttribute("categories", CategoriesManager.getCategories());
		
		try {
			uid = session.getAttribute("uid");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			uid = null;
		}
		if(session == null || uid == null){
			// request.getRequestDispatcher(req.getContextPath() + "/index.jsp").forward( request, response );
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/index.jsp");
			rd.forward(request, response);
		}else{
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/restricted/index.jsp");
			rd.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
