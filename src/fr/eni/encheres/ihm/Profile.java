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
 * Servlet implementation class Profile
 */
@WebServlet("/Profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int uid = -1;
		User user = null;
		
		HttpSession session = request.getSession();
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/profile.jsp");
		if (request.getParameter("uid") != null) {
			uid = Integer.parseInt(request.getParameter("uid"));
		} else if (session.getAttribute("uid") != null) {
			uid = (int) session.getAttribute("uid");
			request.setAttribute("owner", "true");
		} else {
			request.setAttribute("error", "User not found");
			rd = request.getRequestDispatcher("/WEB-INF/profile.jsp");
			rd.forward(request, response);
		}
		
		UserManager userManager = new UserManager();
		try {
			user = userManager.getUser(uid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("uid", uid);
		request.setAttribute("pseudo", user.getPseudo());
		request.setAttribute("nom", user.getNom());
		request.setAttribute("prenom", user.getPrenom());
		request.setAttribute("email", user.getEmail());
		request.setAttribute("telephone", user.getTelephone());
		request.setAttribute("rue", user.getRue());
		request.setAttribute("code_postal", user.getCode_postal());
		request.setAttribute("ville", user.getVille());
		rd = request.getRequestDispatcher("/WEB-INF/profile.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
