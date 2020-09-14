package fr.eni.encheres.ihm;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bll.ArticlesVendus;
import fr.eni.encheres.bll.EncheresManager;
import fr.eni.encheres.bll.OffreManager;
import fr.eni.encheres.bll.User;
import fr.eni.encheres.bll.UserManager;

/**
 * Servlet implementation class Details
 */
@WebServlet("/Details")
public class Details extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Details() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		int uid = -1;
		if (session!=null) {
			try {
				uid = (int) session.getAttribute("uid");
			} catch (Exception e){}
			
		}


		OffreManager OffreManager = new OffreManager();
		int no_article;
		if (request.getParameter("no_article") != null) {
			no_article = Integer.parseInt(request.getParameter("no_article"));
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/Index");
			rd.forward(request, response);
			return;
		}
		ArticlesVendus article = OffreManager.getArticleVendus(no_article);
		request.setAttribute("article", article);
		if (uid == article.getNo_utilisateur()) {
			request.setAttribute("owner", "true");
		} else if (uid != -1) {
			request.setAttribute("owner", "false");
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/details.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("dopost");
		HttpSession session = request.getSession(false);
		int uid = -1;
		if (session!=null) {
			try {
				uid = (int) session.getAttribute("uid");
			} catch (Exception e){}
		} else {
			request.setAttribute("error", "User error");
			doGet(request, response);
			return;
		}
		
		UserManager userManager = new UserManager();
		User user = null;
		OffreManager OffreManager = new OffreManager();
		int no_article;
		if (request.getParameter("no_article") != null) {
			no_article = Integer.parseInt(request.getParameter("no_article"));
		} else {
			doGet(request, response);
			return;
		}
		ArticlesVendus article = OffreManager.getArticleVendus(no_article);
		try {
			user = userManager.getUser(uid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int mise = Integer.parseInt(request.getParameter("enchere"));
		if (mise <= article.getPrix_vente()) {
			request.setAttribute("error", "Invalid price");
			doGet(request, response);
			return;
		}
		if (user.getCredit()<mise) {
			request.setAttribute("error", "Not enoguh credit");
			doGet(request, response);;
			return;
		}
		EncheresManager encheresManager = new EncheresManager();
		encheresManager.addEnchere(no_article, user.getNo_utilisateur(), mise);
		doGet(request, response);
		return;
		
		
	}

}
