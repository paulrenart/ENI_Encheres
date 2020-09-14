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

import fr.eni.encheres.bll.User;
import fr.eni.encheres.bll.UserManager;

/**
 * Servlet implementation class Inscription
 */
@WebServlet("/Inscription")
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Inscription() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/inscription.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserManager userManager = new UserManager();
		String username = request.getParameter("login");
		String email = request.getParameter("email");
		boolean validUser = false;
		try {
			validUser = userManager.checkUniqueFields(username, email);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (validUser) {
			User user = new User(username, request.getParameter("nom"), request.getParameter("prenom"), email, request.getParameter("telephone"),
					request.getParameter("rue"), request.getParameter("code_postal"), request.getParameter("ville"), request.getParameter("mot_de_passe"),
					0, 0);
			try {
				user = userManager.addUser(user);
				HttpSession session = request.getSession();
				session.setAttribute("username", user.getPseudo());
				session.setAttribute("uid", user.getNo_utilisateur());
				RequestDispatcher rd = request.getRequestDispatcher("Index");
				rd.forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			request.setAttribute("error", "Something went wrong");
			doGet(request, response);
		}
	}

}
