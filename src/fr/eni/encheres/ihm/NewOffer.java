package fr.eni.encheres.ihm;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import fr.eni.encheres.bll.ArticlesVendus;
import fr.eni.encheres.bll.Offre;
import fr.eni.encheres.bll.OffreManager;
import fr.eni.encheres.bll.User;
import fr.eni.encheres.bll.UserManager;

/**
 * Servlet implementation class NewOffer
 */
@WebServlet("/NewOffer")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
				 maxFileSize=1024*1024*10,      // 10MB
				 maxRequestSize=1024*1024*50)   // 50MB
public class NewOffer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewOffer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int uid;
		User user = null;
		
		HttpSession session = request.getSession();
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/new_offer.jsp");
		if (request.getParameter("uid") != null) {
			uid = Integer.parseInt(request.getParameter("uid"));
		} else if (session.getAttribute("uid") != null) {
			uid = (int) session.getAttribute("uid");
			request.setAttribute("owner", "true");
		} else {
			request.setAttribute("error", "User not found");
			rd.forward(request, response);
			return;
		}
		
		UserManager userManager = new UserManager();
		try {
			user = userManager.getUser(uid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("uid", uid);
		request.setAttribute("rue", user.getRue());
		request.setAttribute("code_postal", user.getCode_postal());
		request.setAttribute("ville", user.getVille());
		rd = request.getRequestDispatcher("/WEB-INF/new_offer.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OffreManager OffreManager = new OffreManager();
		Offre offre;
		String article = request.getParameter("article");
		String description = request.getParameter("description");
		//String categorie = request.getParameter("categorie");
		int categorie = 1;
		int prix = Integer.parseInt(request.getParameter("prix"));
		String debut = request.getParameter("debut");
		String fin = request.getParameter("fin");
		String rue = request.getParameter("rue");
		String ville = request.getParameter("ville");
		String code_postal = request.getParameter("code_postal");
		
		int uid;
		User user = null;
		HttpSession session = request.getSession();
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/new_offer.jsp");
		UserManager userManager = new UserManager();
		
		final String savePath = "/usr/local/tomcat/webapps/datas";
		String filePath;
		
		if (request.getParameter("uid") != null) {
			uid = Integer.parseInt(request.getParameter("uid"));
		} else if (session.getAttribute("uid") != null) {
			uid = (int) session.getAttribute("uid");
			request.setAttribute("owner", "true");
		} else {
			request.setAttribute("error", "User not found");
			rd.forward(request, response);
			return;
		}
		try {
			user = userManager.getUser(uid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		int no_utilisateur = user.getNo_utilisateur();
		
		ArticlesVendus articlesVendus = new ArticlesVendus(article, description, debut, fin,
				prix, prix, no_utilisateur, categorie);

		try {
			
			articlesVendus = OffreManager.addArticlesVendus(articlesVendus);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
         
        // creates the save directory if it does not exists
        File datas = new File(savePath);
        

        Part filePart = request.getPart("photo");
        String fileName = Integer.toString(articlesVendus.getNo_article()) + "_" + Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        try {
        	File file = new File(datas, fileName);
        	try (InputStream input = filePart.getInputStream()) {
            	Files.copy(input, file.toPath());
            }
        	
        } catch (FileAlreadyExistsException e) {
        	// do nothing
		}
        filePath = "/datas/" + fileName;
        OffreManager.updateImagePath(articlesVendus.getNo_article(), filePath);
        articlesVendus.setImage_path(filePath.toString());
        
		offre = new Offre(articlesVendus, no_utilisateur, debut, prix, articlesVendus.getNo_article(), code_postal, ville, rue);
		try {
			offre = OffreManager.addOffre(offre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rd = request.getRequestDispatcher("Index");
		rd.forward(request, response);


	}

}
